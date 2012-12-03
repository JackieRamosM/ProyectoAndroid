package comic_it.project;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class VtnPlantillas extends Activity implements OnClickListener{    
	
	Button aceptar;
	ImageView img1, img2, img3, img4, img5;
	Boolean cambio1 = false, cambio2 = false, cambio3 = false;
	Intent intent;

	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantillas);
        
        img1 = (ImageView)findViewById(R.id.plantilla1);
        img2 = (ImageView)findViewById(R.id.plantilla2);
        img3 = (ImageView)findViewById(R.id.plantilla3);
        img4 = (ImageView)findViewById(R.id.plantilla4);
        img5 = (ImageView)findViewById(R.id.plantilla5);        
        
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);        
    }
   
	public void onClick(View arg0){
		switch(arg0.getId()){
			case R.id.plantilla1:
				finish();        
				intent = new Intent(VtnPlantillas.this,VtnTabs.class);
				intent.putExtra("plantilla",R.id.plantilla1);
				startActivity(intent); 	
			break;
			case R.id.plantilla2:
				finish();        
				intent = new Intent(VtnPlantillas.this,VtnTabs.class);
				intent.putExtra("plantilla",R.id.plantilla2);
				startActivity(intent); 	
			break;
			case R.id.plantilla3:
				finish();        
				intent = new Intent(VtnPlantillas.this,VtnTabs.class);
				intent.putExtra("plantilla",R.id.plantilla3);
				startActivity(intent); 	
			break;
			case R.id.plantilla4:
				finish();        
				intent = new Intent(VtnPlantillas.this,VtnTabs.class);
				intent.putExtra("plantilla",R.id.plantilla4);
				startActivity(intent); 	
			break;
			case R.id.plantilla5:
				finish();        
				intent = new Intent(VtnPlantillas.this,VtnTabs.class);
				intent.putExtra("plantilla",R.id.plantilla5);
				startActivity(intent); 	
			break;			
		}
	} 	
}    
