package comic_it.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.DownloadManager.Query;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;

import java.net.URL;
import java.util.ArrayList;

import twitter4j.QueryResult;
import twitter4j.Twitter;

import comic_it.project.TwitterApp.TwDialogListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class AndroidTwitterActivity extends Activity{

	public static final String TAG = "AndroidTwitterActivity";
	private static final int VOICE_RECOGNITION_REQUEST = 0x10101;

	private TwitterApp mTwitter;
	private CheckBox mTwitterBtn;
	private EditText txtMensaje;
	private Button postBtn;
	private Button btnSpeech;
	private Button btnRead;	
	private Button btnReadA;	
	private TextView txtStatus;
	private String username = "";
	private boolean postToTwitter = false;

	private static final String twitter_consumer_key = "OnZnMvTNYXzXW32lry7A";
	private static final String twitter_secret_key = "V9dfqXZlC9hBm6V1pIdPO6V3TTBnpnV9ECN1fBpLjoM";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		postBtn = (Button) findViewById(R.id.button1);
		txtMensaje = (EditText) findViewById(R.id.txtMensaje);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		Log.d("longitud","=" +txtMensaje.toString().length());
	 				
		postBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String review = txtMensaje.getText().toString();							 
		        if( txtMensaje.getText().toString().length()>=140)
		        	txtMensaje.setError("Tweet muy largo!");
		        else if(txtMensaje.getText().toString().length()<1)
		        	txtMensaje.setError("Ups! No ha twitteado nada");
		        else{				
				   if(review.equals(""))
					   return;
				   if (postToTwitter)
					   postToTwitter(review);			    	
		        }
			}
		});

		mTwitter = new TwitterApp(this,twitter_consumer_key,twitter_secret_key);
		mTwitter.setListener(mTwLoginDialogListener);
		mTwitterBtn = (CheckBox) findViewById(R.id.twitterCheck);
		mTwitterBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(mTwitter.hasAccessToken()){
					postToTwitter = mTwitterBtn.isChecked();
					if(postToTwitter){
						mTwitterBtn.setChecked(true);
						txtStatus.setText("Estado: Conectado");
						username = mTwitter.getUsername();
						username = (username.equals("")) ? "Sin Nombre" : username;
						mTwitterBtn.setText("  Twitter  (" + username + ")");
					}else{
						mTwitterBtn.setChecked(false);
						txtStatus.setText("Estado: No Conectado");
						mTwitterBtn.setText("  Twitter  (Sin Nombre)");
					}
				}else{
					mTwitterBtn.setChecked(false);
					mTwitter.authorize();
				}
			}
		});

		if(mTwitter.hasAccessToken()){
			postToTwitter = true;
			mTwitterBtn.setChecked(true);
			txtStatus.setText("Estado: Conectado");
			username = mTwitter.getUsername();
			username = (username.equals("")) ? "Sin Nombre" : username;
			mTwitterBtn.setText("  Twitter  (" + username + ")");
		}else{
			postToTwitter = false;
			mTwitterBtn.setChecked(false);
			txtStatus.setText("Estado: No Conectado");
			mTwitterBtn.setText("  Twitter  (Sin Nombre)");
		}				
		btnReadA = (Button) findViewById(R.id.btnReadA);		  
		btnReadA.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				int what = 0;
				try{					  
			        if(txtMensaje.getText().toString().length()<5)
			        	txtMensaje.setError("texto de busqueda debe ser mas largo");
			        else{					
			        	Log.d("hola","buton read");							
			        	final ProgressDialog dialog;
			        	ActivitiesBringe.setMtwitter(mTwitter);
			        	dialog = ProgressDialog.show( v.getContext(), "", "Consultando..", true);
			        	Thread thread = new Thread(){
			        		@Override
			 	            public void run(){ 
			        			try{ 	
						            TwitterApp  mTwitter = ActivitiesBringe.getMtwitter();
						            Log.d("dsfds", txtMensaje.getText().toString());
						         	ArrayList<Tweetc> tweets =  mTwitter.readTweets( txtMensaje.getText().toString());							         	
						         	ActivitiesBringe.setObject(tweets);
			        			}catch(Exception e){}
					            dialog.dismiss();  
			        		}
			        	};
			        	thread.start();
						while(thread.isAlive()){};					
						ArrayList<Tweetc> tweets = ActivitiesBringe.getObject(); 
						Log.d("hola","mensaje antes de intent");	
						Intent i = new Intent();
						i.setClass(v.getContext(), ShowActivity.class);
						startActivity(i);	 
		        	}  				
				}catch(Exception e){
					what = 1;
				}				
			}
		});
	}

	private void postToTwitter(final String review){
		new Thread(){
			@Override
			public void run(){
				int what = 0;
				try{					
					mTwitter.updateStatus(review);
				}catch(Exception e){
					what = 1;
				}
				mHandler.sendMessage(mHandler.obtainMessage(what));
			}
		}.start();
	}

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			String text = (msg.what == 0) ? "Tweet Posteado" : "Fallo el posteo en Twitter";
			Toast.makeText(AndroidTwitterActivity.this,text,Toast.LENGTH_SHORT).show();
		}
	};
	
	private final TwDialogListener mTwLoginDialogListener = new TwDialogListener(){
		
		public void onComplete(String value){
			username = mTwitter.getUsername();
			username = (username.equals("")) ? "Sin Nombre" : username;
			mTwitterBtn.setText("  Twitter  (" + username + ")");
			mTwitterBtn.setChecked(true);
			postToTwitter = true;
			Toast.makeText(AndroidTwitterActivity.this,"Conectado a Twitter como " + username, Toast.LENGTH_LONG).show();
			txtStatus.setText("Estado: Conectado");
		}
		
		public void onError(String value){
			mTwitterBtn.setChecked(false);
			Toast.makeText(AndroidTwitterActivity.this,"Fallo conexión con Twitter", Toast.LENGTH_LONG).show();
			txtStatus.setText("Estado: No Conectado");
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK){
			ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			String firstMatch = matches.get(0);
			txtMensaje.setText(firstMatch);
		}
	}
	
	public Bitmap getBitmap(String bitmapUrl){
		try{
			URL url = new URL(bitmapUrl);
		    return BitmapFactory.decodeStream(url.openConnection().getInputStream());
		}
		catch(Exception ex) {return null;}
	}
}