package proyecto.ventanas_android;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
ImageView icono1,icono2,icono3,principal;
ImageView r1,r2,r3,r4,v1,v2,v3,v4,n1,n2,n3,principal_bur;
ImageButton btnAction_text, btnAction_bur, btn_ok_bur,btn_ok_text;
RadioGroup rg;
RadioButton rb1,rb2;
Button bt_continuar;
private String name="pict-web.png";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camara);
       
        rg= (RadioGroup) findViewById(R.id.rgrupo);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton) findViewById(R.id.rb2);
        bt_continuar=(Button) findViewById(R.id.btn_continuar);
        bt_continuar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	public void onClick(View arg0) {
		switch(arg0.getId()){
		
		case R.id.btn_continuar:
			if(rb1.isChecked()){
			setContentView(R.layout.activity_main);
			 btnAction_text = (ImageButton)findViewById(R.id.imageButton1);
		    btnAction_bur = (ImageButton)findViewById(R.id.imageButton2);
	        btnAction_text.setOnClickListener(this);
	        btnAction_bur.setOnClickListener(this);	}
			if(rb2.isChecked()){
				setContentView(R.layout.activity_main);
				 btnAction_text = (ImageButton)findViewById(R.id.imageButton1);
			    btnAction_bur = (ImageButton)findViewById(R.id.imageButton2);
		        btnAction_text.setOnClickListener(this);
		        btnAction_bur.setOnClickListener(this);
				/*
				FUNCIONALIDAD QUE FALTA TERMINAR DE IMPLEMENTAR
				
				Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
				Uri selectedImage = intent.getData();
	            InputStream is;
	                try {
	                    is = getContentResolver().openInputStream(selectedImage);
	                    BufferedInputStream bis = new BufferedInputStream(is);
	                    Bitmap bitmap = BitmapFactory.decodeStream(bis);
	                    ImageView iv = (ImageView)findViewById(R.id.ivR1);
	                    iv.setImageBitmap(bitmap);
	                } catch (FileNotFoundException e) {}
				*/
			}
		break;
		case R.id.imageButton2:
			setContentView(R.layout.burbuja);
			principal_bur=(ImageView) findViewById(R.id.ivPrincipal_bur);
			r1=(ImageView) findViewById(R.id.ivR1);
			r2=(ImageView) findViewById(R.id.ivR2);
			r3=(ImageView) findViewById(R.id.ivR3);
			r4=(ImageView) findViewById(R.id.ivR4);
			v1=(ImageView) findViewById(R.id.ivV1);
			v2=(ImageView) findViewById(R.id.ivV2);
			v3=(ImageView) findViewById(R.id.ivV3);
			v4=(ImageView) findViewById(R.id.ivV4);
			n1=(ImageView) findViewById(R.id.ivN1);
			n2=(ImageView) findViewById(R.id.ivN2);
			n3=(ImageView) findViewById(R.id.ivN3);
		
				
			btnAction_bur.setOnClickListener(this);	
			r1.setOnClickListener(this);
			r2.setOnClickListener(this);
			r3.setOnClickListener(this);
			r4.setOnClickListener(this);
			v1.setOnClickListener(this);
			v2.setOnClickListener(this);
			v3.setOnClickListener(this);
			v4.setOnClickListener(this);
			n1.setOnClickListener(this);
			n2.setOnClickListener(this);
			n3.setOnClickListener(this);
			
		break;
		
		case R.id.ivR1:
			principal_bur.setImageResource(R.drawable.r1);
			
			break;
		case R.id.ivR2:
			principal_bur.setImageResource(R.drawable.r2);
			
			break;
		case R.id.ivR3:
			principal_bur.setImageResource(R.drawable.r3);
			
			break;
		case R.id.ivR4:
			principal_bur.setImageResource(R.drawable.r4);
			
			break;
		case R.id.ivV1:
			principal_bur.setImageResource(R.drawable.v1);
			
			break;
		case R.id.ivV2:
			principal_bur.setImageResource(R.drawable.v2);
			
			break;
		case R.id.ivV3:
			principal_bur.setImageResource(R.drawable.v3);
			
			break;
		case R.id.ivV4:
			principal_bur.setImageResource(R.drawable.v4);
			
			break;
		case R.id.ivN1:
			principal_bur.setImageResource(R.drawable.n1);
			
			break;
		case R.id.ivN2:
			principal_bur.setImageResource(R.drawable.n2);
			
			break;
		case R.id.ivN3:
			principal_bur.setImageResource(R.drawable.n3);
			
			break;
			
		case R.id.imageButton1:
			setContentView(R.layout.views);
			principal=(ImageView) findViewById(R.id.ivPrincipal);
			icono1=(ImageView) findViewById(R.id.ivIcono1);
			icono2=(ImageView) findViewById(R.id.ivIcono2);
			icono3=(ImageView) findViewById(R.id.ivIcono3);
			icono1.setOnClickListener(this);
			icono2.setOnClickListener(this);
			icono3.setOnClickListener(this);
		
			btnAction_text.setOnClickListener(this);
			
		case R.id.ivIcono1:
			principal.setImageResource(R.drawable.icono1);
			
			break;
			
		case R.id.ivIcono2:
			principal.setImageResource(R.drawable.icono2);
			
			break;
			
		case R.id.ivIcono3:
			principal.setImageResource(R.drawable.icono3);
	
			break;

		
		}
	}
}
