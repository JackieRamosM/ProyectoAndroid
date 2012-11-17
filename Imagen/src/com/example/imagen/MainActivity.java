package com.example.imagen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	String s;
	EditText et;
	Button bt;
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);                
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = new TextView(this);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.l1);
        RelativeLayout rl = new RelativeLayout(this);    
        
        Lienzo fondo=new Lienzo(this);
        et= (EditText) findViewById(R.id.editText1);
        bt=(Button)findViewById(R.id.button1);
        
        
       	
        RelativeLayout.LayoutParams parametros = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(parametros);
        rl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
         
        
	
       	bt.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				s = et.getText().toString();
				tv.setText(s);		
				
			}
		});
       
       	linearLayout.addView(rl);
        rl.addView(fondo);
       	rl.addView(tv);
    }
    
    
    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
        }
        
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0,0,255);
            int ancho=canvas.getWidth();
           
            int alto=canvas.getHeight();
          
            Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.burbuja);
            canvas.drawBitmap(bmp, (ancho-250)/2,(alto-200)/2, null);
        }
    }    

}