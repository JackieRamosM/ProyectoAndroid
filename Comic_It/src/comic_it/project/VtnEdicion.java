package comic_it.project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class VtnEdicion extends Activity {
	
	Camera foto;
	ImageView img1, img2, img3, img4, img5;
	Boolean cambio1 = false, cambio2 = false, cambio3 = false, cambio4 = false, cambio5 = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_plantillas);
        
        addListenerOnButton();
    }

    public void addListenerOnButton() {
    	 
		img1 = (ImageView) findViewById(R.id.img1);
 		img1.setOnClickListener(new OnClickListener(){ 
			public void onClick(View arg0){
				cambio1 = !cambio1;
				if(cambio1)
				{
					img1.setImageResource(R.drawable.in12);
					CargarCamara();
				}
				else
					img1.setImageResource(R.drawable.in1);
			} 
		});
 		
 		img1.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				cambio1 = !cambio1;
				if(cambio1){
					img1.setImageResource(R.drawable.in12);
					CargarCamara();
				}
				else
					img1.setImageResource(R.drawable.in1);
				return false;
			} 			
 		});
 		
 		img2 = (ImageView) findViewById(R.id.img2);
 		img2.setOnClickListener(new OnClickListener(){ 
			public void onClick(View arg0){
				cambio2 = !cambio2;
				if(cambio2){
					img2.setImageResource(R.drawable.in22);
					CargarCamara();
					}
				else
					img2.setImageResource(R.drawable.in2);
			} 
		});
 		
 		img2.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				cambio2 = !cambio2;
				if(cambio2){
					img2.setImageResource(R.drawable.in22);
					CargarCamara();
				}
				else
					img2.setImageResource(R.drawable.in2);
				return false;
			} 			
 		});
 		
 		img3 = (ImageView) findViewById(R.id.img3);
 		img3.setOnClickListener(new OnClickListener(){ 
			public void onClick(View arg0){
				cambio3 = !cambio3;
				if(cambio3){
					img3.setImageResource(R.drawable.in32);
					CargarCamara();
				}
				else
					img3.setImageResource(R.drawable.in3);
			} 
		});
 		
 		img3.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				cambio3 = !cambio3;
				if(cambio3){
					img3.setImageResource(R.drawable.in32);
					CargarCamara();
				}
				else
					img3.setImageResource(R.drawable.in3);
				return false;
			} 			
 		});
 		
 		img4 = (ImageView) findViewById(R.id.img4);
 		img4.setOnClickListener(new OnClickListener(){ 
			public void onClick(View arg0){
				cambio4 = !cambio4;
				if(cambio4){
					img4.setImageResource(R.drawable.in42);
					CargarCamara();
				}
				else
					img4.setImageResource(R.drawable.in4);
			} 
		});
 		
 		img4.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				cambio4 = !cambio4;
				if(cambio4){
					img4.setImageResource(R.drawable.in42);
					CargarCamara();
				}
				else
					img4.setImageResource(R.drawable.in4);
				return false;
			} 			
 		});
 		
 		img5 = (ImageView) findViewById(R.id.img5);
 		img5.setOnClickListener(new OnClickListener(){ 
			public void onClick(View arg0){
				cambio5 = !cambio5;
				if(cambio5){
					img5.setImageResource(R.drawable.in52);
					CargarCamara();
				}
				else
					img5.setImageResource(R.drawable.in5);
			} 
		});
 		
 		img5.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View arg0, MotionEvent arg1) {
				cambio5 = !cambio5;
				if(cambio5){
					img5.setImageResource(R.drawable.in52);
					CargarCamara();
				}
				else
					img5.setImageResource(R.drawable.in5);
				return false;
			} 			
 		});

	}
    
    public void CargarCamara(){
    	Intent intent = new Intent(VtnEdicion.this,Camera.class);
        startActivity(intent);
    }
    
 

}
