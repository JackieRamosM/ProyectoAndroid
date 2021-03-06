package comic_it.project;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class Camera extends Activity {

	private static int TAKE_PICTURE = 1;
	private static int SELECT_PICTURE = 2;

	private String name = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        
        name = Environment.getExternalStorageDirectory() + "/test.jpg";

        Button btnAction = (Button)findViewById(R.id.btnPic);
        btnAction.setOnClickListener(new OnClickListener() {       		
       		public void onClick(View v) {
       			
       			RadioButton rbtnFull = (RadioButton)findViewById(R.id.radbtnFull);
       			RadioButton rbtnGallery = (RadioButton)findViewById(R.id.radbtnGall);
       			
       			
       			Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
       			int code = TAKE_PICTURE;
       			
       			
       			if (rbtnFull.isChecked()) {					
       				Uri output = Uri.fromFile(new File(name));
       		    	intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
       			   		    	
       			} else if (rbtnGallery.isChecked()){       				
       				intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
       				code = SELECT_PICTURE;
       			}
       			
       			startActivityForResult(intent, code);				
       		}
       	});        
    }
    
   
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if (requestCode == TAKE_PICTURE) {
    		
    		if (data != null) {
    			
    			if (data.hasExtra("data")) { 
    				ImageView iv = (ImageView)findViewById(R.id.FotoTomada);
    				iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
    			}
    					
    		} else {
    			
    			ImageView iv = (ImageView)findViewById(R.id.FotoTomada);
    			iv.setImageBitmap(BitmapFactory.decodeFile(name));
    			
    			new MediaScannerConnectionClient() {
    				private MediaScannerConnection msc = null; {
    					msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
    				}
    				public void onMediaScannerConnected() { 
    					msc.scanFile(name, null);
    				}
    				public void onScanCompleted(String path, Uri uri) { 
    					msc.disconnect();
    				} 
    			};				
    		}
    	
    	} else if (requestCode == SELECT_PICTURE){
    		Uri selectedImage = data.getData();
    		InputStream is;
    		try {
    			is = getContentResolver().openInputStream(selectedImage);
    	    	BufferedInputStream bis = new BufferedInputStream(is);
    	    	Bitmap bitmap = BitmapFactory.decodeStream(bis);            
    	    	ImageView iv = (ImageView)findViewById(R.id.FotoTomada);
    	    	iv.setImageBitmap(bitmap);						
    		} catch (FileNotFoundException e) {}
    	}
    }

    public void RegresarAEdicion(ImageView iv){
        finish();        
        Intent intent = new Intent(Camera.this,VtnTabs.class);
        intent.putExtra("tomada camara",R.id.FotoTomada);
        startActivity(intent);
        
    }
    
}