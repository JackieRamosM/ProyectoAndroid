package comic_it.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import comic_it.project.R.id;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class VtnTabs extends TabActivity{	

	private ImageView foto_principal;
	public static TabHost tabHost;
	Intent intent;	
	int bundle_plantilla;
	int bundle_interrogacion;
	int bundle_camara;
	int bundle_texto;
	 
	@Override
    public void onCreate(Bundle savedInstanceState){    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla);

        tabHost = getTabHost();
    
        //Tab for the comic
        TabSpec comicspec = tabHost.newTabSpec("Comic");
        comicspec.setIndicator("Comic", getResources().getDrawable(R.drawable.pes1_comic));
        
        Intent comicIntent = new Intent(VtnTabs.this,P1_comic.class);  
        bundle_plantilla = getIntent().getExtras().getInt("plantilla");
    
        /*
		 *  CODIGO DEL SWITCH DEL BUNDLE
		 *	AQUI.
		 */
		 
        comicspec.setContent(comicIntent);               
  
        //Tab for Symbols
        TabSpec symbolsspec = tabHost.newTabSpec("Symbol");
        symbolsspec.setIndicator("Symbol",this.getResources().getDrawable(R.drawable.pes2_symbols));        
        Intent symbolsIntent = new Intent(VtnTabs.this,P1_comic.P2_symbols.class);
        symbolsspec.setContent(symbolsIntent);
        //RECUPERANDO EL ID DEL TEXTO ESCOGIDO
        bundle_texto=getIntent().getExtras().getInt("texto seleccionado");
        //INSTANCIANDO UNA IMAGEN Y CAMBIANDOLE EL RECURSO CON EL TEXTO ESCOGIDO
        ImageView texto= new ImageView(this);
        texto.setImageResource(bundle_texto);
        //AGREGAR EL DRAG AND DROP A TEXTO
 
        // Tab for Bubbles
        TabSpec bubblesspec = tabHost.newTabSpec("Bubble");
        bubblesspec.setIndicator("Bubble",this.getResources().getDrawable(R.drawable.pes3_bubbles));
        Intent bubblesIntent = new Intent(VtnTabs.this,P1_comic.P3_bubbles.class);
        bubblesspec.setContent(bubblesIntent);        
                           
        tabHost.addTab(comicspec);  
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.rgb(235,222,6));
        tabHost.addTab(symbolsspec);
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.rgb(235,222,6));
        tabHost.addTab(bubblesspec);
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.rgb(235,222,6));
    }
	
	
	/*
	 * Clase interna: P1_comic
	 * -----------------------
	 * Descripción: Esta clase contiene los controladores para agregar imágenes a la plantilla del comic,
	 * 				así como los botones de Guardar, Compartir y '+'. También contiene las ImageView de 
	 *				los diferentes íconos y/o textos que pueden ser agregados.	 
	 */
	public static class P1_comic extends Activity{
		
		
		
			//TODO EL CODIGO DE P1 AQUI
		
		
		
		
		
		
		/*
		 * Clase interna: P2_symbols
		 * -----------------------
		 * Descripción: Esta clase contiene los controladores que permiten referenciar una imagen para 
		 *				que sea agregada al Comic. El usuario puede seleccionar la imagen que le agrade,
		 *				y a continuación dar click en el botón 'seleccionar'.
 		 */
		public static class P2_symbols extends Activity{			
				
			Button aceptar;
			ImageView principal, img1, img2, img3, img4, img5, img6, img7;
			int selected = 0;
			
			@Override
			public void onCreate(Bundle savedInstanceState){    	
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.p2_symbols);
		        cargarIconos_y_Aceptar();
		    }
			
			public void cargarIconos_y_Aceptar(){
				
				principal = (ImageView)findViewById(R.id.seleccionIcono);
				img1 = (ImageView)findViewById(R.id.img_icono1);
				img2 = (ImageView)findViewById(R.id.img_icono2);
				img3 = (ImageView)findViewById(R.id.img_icono3);
				img4 = (ImageView)findViewById(R.id.img_icono4);
				img5 = (ImageView)findViewById(R.id.img_icono5);
				img6 = (ImageView)findViewById(R.id.img_icono6);
				img7 = (ImageView)findViewById(R.id.img_icono7);
				
				img1.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono1);
						selected = 1;
					}
				});
				img2.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono2);
						selected = 2;
					}
				});
				img3.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono3);
						selected = 3;
					}
				});
				img4.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono4);
						selected = 4;
					}
				});
				img5.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono5);
						selected = 5;
					}
				});
				img6.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono6);
						selected = 6;
					}
				});
				img7.setOnClickListener(new OnClickListener(){					
					public void onClick(View v) {
						principal.setImageResource(R.drawable.icono7);
						selected = 7;
					}
				});
				
				aceptar = (Button) findViewById(R.id.iconoAceptar);
				aceptar.setOnClickListener(new OnClickListener(){				
					public void onClick(View v){								 		      		 		       
		 		        switch(selected){
		 		        	case 1:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono1);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 2:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono2);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 3:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono3);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 4:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono4);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 5:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono5);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 6:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono6);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        	case 7:
		 		        		imagenes.get(0).setImageResource(R.drawable.icono7);
		 		        		imagenes.remove(0);
		 		        	break;
		 		        }
		 		        tabHost.setCurrentTab(0);
					}
				});
			} 
		}
		
		
		/*
		 * Clase interna: P3_bubbles
		 * -----------------------
		 * Descripción: Esta clase contiene los controladores que le permiten al usuario editar el texto que 
		 * 				será agregado al comic. El usuario puede seleccionar el color que quiere para el fondo
		 * 				del texto, y agregarlo al Comic.
 		 */
		public static class P3_bubbles extends Activity implements OnClickListener{
			
			EditText edit;
			ImageView btnblack, btnblue, btncyan, btndarkgray, btngray, btnlightgray, btnmagenta, btnred, btnverde, btnwhite, btnyellow; 
			int color, selected = 0;
			Button aceptar;
			boolean bandera=false;
				
			@Override
		    public void onCreate(Bundle savedInstanceState){    	
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.p3_bubbles);
		        CrearBurbujas_y_Aceptar();	       
		    }
						
			@SuppressLint({"NewApi","NewApi"})
			public void CrearBurbujas_y_Aceptar(){			
				edit = (EditText) findViewById(R.id.Burbuja);
				btnblack = (ImageView) findViewById(R.id.c_black);
		        btnblue = (ImageView) findViewById(R.id.c_blue);
		        btncyan = (ImageView) findViewById(R.id.c_cyan);
		        btndarkgray = (ImageView) findViewById(R.id.c_darkgray);
		        btngray = (ImageView) findViewById(R.id.c_gray);
		        btnlightgray = (ImageView) findViewById(R.id.c_lightgray);
		        btnmagenta = (ImageView) findViewById(R.id.c_magenta);
		        btnred = (ImageView) findViewById(R.id.c_red);
		        btnverde = (ImageView) findViewById(R.id.c_verde);
		        btnwhite = (ImageView) findViewById(R.id.c_white);
		        btnyellow = (ImageView) findViewById(R.id.c_yellow);	        
		        btnblack.setOnClickListener(this);
		        btnblue.setOnClickListener(this);
		        btncyan.setOnClickListener(this);
		        btndarkgray.setOnClickListener(this);
		        btngray.setOnClickListener(this);
		        btnlightgray.setOnClickListener(this);
		        btnmagenta.setOnClickListener(this);
		        btnred.setOnClickListener(this);
		        btnverde.setOnClickListener(this);
		        btnwhite.setOnClickListener(this);
		        btnyellow.setOnClickListener(this);
		        
		        aceptar = (Button) findViewById(R.id.textoAceptar);
				aceptar.setOnClickListener(new OnClickListener(){				
					public void onClick(View v){						
						View ve = findViewById(R.id.LayoutBurbuja);						
						ve.setVisibility(1);
						ve.setVisibility(0);
		 		        ve.setDrawingCacheEnabled(true);		 		        
		 		        Bitmap b = ve.getDrawingCache();		 		        
		 		        imagenes.get(0).setImageBitmap(b);
		 		       	imagenes.remove(0);		 		
		 		       	EditText et = (EditText)findViewById(R.id.Burbuja);
						et.clearComposingText();
		 		        tabHost.setCurrentTab(0);
					}
				});
		    }
			
			public void onClick(View arg0){
				switch(arg0.getId()){
					case R.id.c_black:{
						this.color = -16777216;
				      	this.edit.setBackgroundColor(color);
				      	this.color = -1;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_blue:{
						this.color = -16776961;
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_cyan:{
						this.color = -16711681;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;				
					case R.id.c_darkgray:{
						this.color = -12303292;
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_gray:{
						this.color = -7829368;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_lightgray:{
						this.color = -3355444;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_magenta:{
						this.color = -65281;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_red:{
						this.color = -65536;
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_verde:{
						this.color = -16711936;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_white:{
						this.color = -1;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
					case R.id.c_yellow:{
						this.color = -256;		
				      	this.edit.setBackgroundColor(color);
				      	this.color = -16777216;
				      	this.edit.setTextColor(color);
					}break;
				}
			}	
		}	
			
		/*
		 * Método: crearDialogoAlerta.
		 * Uso: crearDialogoAlerta();
		 * ---------------------------
		 * Descripción:
		 * Muestra un mensaje de alerta al usuario.
		 */
		private Dialog crearDialogoAlerta(String title, String message){
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);		 
		    builder.setTitle(title);
		    builder.setMessage(message);
		    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
		        public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		        }
		    });		 
		    return builder.create();
		}
	}	
}
