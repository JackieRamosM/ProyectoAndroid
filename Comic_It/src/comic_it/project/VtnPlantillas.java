package comic_it.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.text.style.UpdateLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 *
 * @author Denny
 */
public class VtnPlantillas extends Activity {    
	
	Button aceptar;
	ImageView img1, img2, img3;
	Boolean cambio1 = false, cambio2 = false, cambio3 = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantillas);
        aceptar = (Button) findViewById(R.id.aceptar);
        aceptar.getBackground().setColorFilter(0xFFFF0000, Mode.MULTIPLY);
        addListenerOnImages();
        aceptar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();        
		        Intent intent = new Intent(VtnPlantillas.this,VtnEdicion.class);
		        startActivity(intent);
			}
		});
        
    }
    
    public void addListenerOnImages(){
   	 
 		img1 = (ImageView) findViewById(R.id.plantilla1);
  		img1.setOnClickListener(new OnClickListener(){
 			public void onClick(View arg0){
 				cambio1 = !cambio1;
 				if(cambio1)
 					img1.setPadding(20,20,20,20);
 				else
 					img1.setPadding(0,0,0,0); 				
 			} 			
 		});
  		
  		img2 = (ImageView) findViewById(R.id.plantilla2);
  		img2.setOnClickListener(new OnClickListener(){ 
 			public void onClick(View arg0){
 				cambio2 = !cambio2;
 				if(cambio2)
 					img2.setPadding(20,20,20,20);
 				else
 					img2.setPadding(0,0,0,0); 				
 			} 
 		});
  		
  		img3 = (ImageView) findViewById(R.id.plantilla3);
  		img3.setOnClickListener(new OnClickListener(){ 
 			public void onClick(View arg0){
 				cambio3 = !cambio3;
 				if(cambio3)
 					img3.setPadding(20,20,20,20);
 				else
 					img3.setPadding(0,0,0,0); 				
 			} 
 		});  		
    }

	
    

}