package comic_it.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Plantilla extends Activity {
	private ImageView plantilla_prin;
	ImageView prin1,prin2,prin3,prin4,prin5,imagen;
	Intent intent;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int bundle=getIntent().getExtras().getInt("plantilla");
       if(bundle==R.layout.activity_plantilla){
    	   setContentView(R.layout.activity_plantilla);
    	   
    	   prin1= (ImageView) findViewById(R.id.img1);
           prin2= (ImageView) findViewById(R.id.img2);
           prin3= (ImageView) findViewById(R.id.img3);
           prin4= (ImageView) findViewById(R.id.img4);
           prin5= (ImageView) findViewById(R.id.img5);
           
           prin1.setOnClickListener((OnClickListener) this);
           prin2.setOnClickListener((OnClickListener) this);
           prin3.setOnClickListener((OnClickListener) this);
           prin4.setOnClickListener((OnClickListener) this);
           prin5.setOnClickListener((OnClickListener) this);
  
       }
       /*
       if(bundle==R.layout.activity_plantilla2)
        setContentView(R.layout.activity_plantilla2);
       if(bundle==R.layout.activity_plantilla3)
        setContentView(R.layout.activity_plantilla3);
        */
        
        
    }

   
    public void onClick(View arg0) {
		switch(arg0.getId()){
		
		case R.id.img1:
			finish();        
	        intent = new Intent(Plantilla.this,VtnEdicion.class);	
	        intent.putExtra("imagen escogida",R.id.img1);
	        startActivity(intent); 
			break;
		case R.id.img2:
			finish();        
	        intent = new Intent(Plantilla.this,VtnEdicion.class);	
	        intent.putExtra("imagen escogida",R.id.img2);
	        startActivity(intent); 
			break;
		case R.id.img3:
			finish();        
	        intent = new Intent(Plantilla.this,VtnEdicion.class);	
	        intent.putExtra("imagen escogida",R.id.img3);
	        startActivity(intent); 
			break;
		case R.id.img4:
			finish();        
	        intent = new Intent(Plantilla.this,VtnEdicion.class);	
	        intent.putExtra("imagen escogida",R.id.img4);
	        startActivity(intent); 
			break;
		case R.id.img5:
			finish();        
	        intent = new Intent(Plantilla.this,VtnEdicion.class);	
	        intent.putExtra("imagen escogida",R.id.img5);
	        startActivity(intent); 
			break;
	
		}
			
    }
}
