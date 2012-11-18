package comic_it.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 *
 * @author Denny
 */
public class VtnPlantillas extends Activity {    
	
	Button aceptar;
	ImageView img1, img2, img3, img;
	Boolean cambio1 = false, cambio2 = false, cambio3 = false;
	Intent intent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantillas);
        addListenerOnImage(img1,1);
        addListenerOnImage(img2,2);
        addListenerOnImage(img2,3);
        img = (ImageView) findViewById(R.id.diseno);
        img.setBaselineAlignBottom(true);
    }
    
    public void addListenerOnImage(ImageView img, int numero){
    	if(numero == 1)
    		img = (ImageView) findViewById(R.id.plantilla1);
    	else if(numero == 2)
    		img = (ImageView) findViewById(R.id.plantilla2);
    	else if(numero == 3)
    		img = (ImageView) findViewById(R.id.plantilla3);
    	img.setOnClickListener(new OnClickListener(){
 			public void onClick(View arg0){
 				finish();        
		        intent = new Intent(VtnPlantillas.this,VtnEdicion.class);		        
		        startActivity(intent); 				
 			} 			
 		});
    }    
}