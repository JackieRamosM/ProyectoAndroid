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

			//TODO EL CODIGO DE P1 AQUI	private static final int PICK_FROM_CAMERA = 1;
		private static final int CROP_FROM_CAMERA = 2;
		private static final int PICK_FROM_FILE = 3;
		private int TAKE_PICTURE = 1;
		private int SELECT_PICTURE = 2;
		private Uri mImageCaptureUri;		
		private String name = "";
		
		ImageView imagen1,imagen2,imagen3,imagen4,imagen5,imagen6, iv, img1, img2, img3, img;
		Button btnBurbujas, btn_tomado, compartir, guardar, aceptar, plus;	
		Boolean cambio1 = false, cambio2 = false, cambio3 = false;
		ImageView icono1, icono2, icono3;
		View selected_item = null;
		Bitmap ima, photo, bitmap;
		Intent intent;
		Drawable d;
		
		public static ArrayList<ImageView> imagenes = new ArrayList<ImageView>();
		public static ViewGroup vg1, vg2, vg3, vg4, vg5, vg6, vg7, vg8, vg9, vg10, vg11, vg12, vg13, vg14, vg15;
		public static ImageView over_1, over_2, over_3, over_4, over_5, over_6, over_7, over_8, over_9, over_10, over_11, over_12, over_13, over_14, over_15;
				  		
		int bundle_comic,bundle_camara,i=0,offset_x = 0,offset_y = 0;						
		

		@Override
	    public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	        bundle_comic = getIntent().getExtras().getInt("cargarPlantilla");
	        
	        final String [] items = new String [] {"Camara", "Galeria"};				
			ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
			AlertDialog.Builder builder	= new AlertDialog.Builder(this);
			
			builder.setTitle("Seleccion de Imagen");
			builder.setAdapter(adapter, new DialogInterface.OnClickListener(){
				public void onClick( DialogInterface dialog, int item ){ 
					if(item == 0){
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);							
						mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
										   "Avatar" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
						intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
						try{								
							intent.putExtra("return-data", true);
							startActivityForResult(intent, PICK_FROM_CAMERA);								
						}catch(ActivityNotFoundException e){
							e.printStackTrace();
						}
					}else{
						Intent intent = new Intent();							
		                intent.setType("image/*");
		                intent.setAction(Intent.ACTION_GET_CONTENT);			                
		                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
					}
				}
			});
			
			final AlertDialog dialog = builder.create();
			
	        switch(bundle_comic){
	        	case R.id.plantilla1:
	        		setContentView(R.layout.plantilla_1);
			        imagen1=(ImageView) findViewById(R.id.p1_img1);
			        imagen2=(ImageView) findViewById(R.id.p1_img2);
			        imagen3=(ImageView) findViewById(R.id.p1_img3);
			        imagen4=(ImageView) findViewById(R.id.p1_img4);
			        imagen5=(ImageView) findViewById(R.id.p1_img5);			        
			        		        				     		       
			        imagen1.setOnClickListener(new View.OnClickListener(){				
						public void onClick(View v){
							dialog.show();
							i=1;
						}
					});		        
			        imagen2.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=2;
						}
					});
			        imagen3.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=3;
						}
					});
					imagen4.setOnClickListener(new View.OnClickListener(){							
						public void onClick(View v) {
							dialog.show();
							i=4;
						}
					});
					imagen5.setOnClickListener(new View.OnClickListener(){						
						public void onClick(View v){
							dialog.show();
							i=5;
						}
					});	
		
					over_1 = (ImageView) findViewById(R.id.vg_icono1_1); imagenes.add(over_1);
					over_2 = (ImageView) findViewById(R.id.vg_icono2_1); imagenes.add(over_2);
					over_3 = (ImageView) findViewById(R.id.vg_icono3_1); imagenes.add(over_3);
					over_4 = (ImageView) findViewById(R.id.vg_icono4_1); imagenes.add(over_4);
					over_5 = (ImageView) findViewById(R.id.vg_icono5_1); imagenes.add(over_5);
					over_6 = (ImageView) findViewById(R.id.vg_icono6_1); imagenes.add(over_6);
					over_7 = (ImageView) findViewById(R.id.vg_icono7_1); imagenes.add(over_7);
					over_8 = (ImageView) findViewById(R.id.vg_icono8_1); imagenes.add(over_8);
					over_9 = (ImageView) findViewById(R.id.vg_icono9_1); imagenes.add(over_9);
					over_10 = (ImageView) findViewById(R.id.vg_icono10_1); imagenes.add(over_10);
					over_11 = (ImageView) findViewById(R.id.vg_icono11_1); imagenes.add(over_11);
					over_12 = (ImageView) findViewById(R.id.vg_icono12_1); imagenes.add(over_12);
					over_13 = (ImageView) findViewById(R.id.vg_icono13_1); imagenes.add(over_13);
					over_14 = (ImageView) findViewById(R.id.vg_icono14_1); imagenes.add(over_14);
					over_15 = (ImageView) findViewById(R.id.vg_icono15_1); imagenes.add(over_15);
										
					vg1 = (ViewGroup) findViewById(R.id.vg1_1);
			        	agregarDrag_and_Drop(vg1,over_1);
			        vg2 = (ViewGroup) findViewById(R.id.vg2_1);
			        	agregarDrag_and_Drop(vg2,over_2);
			        vg3 = (ViewGroup) findViewById(R.id.vg3_1);
			        	agregarDrag_and_Drop(vg3,over_3);
			        vg4 = (ViewGroup) findViewById(R.id.vg4_1);
			        	agregarDrag_and_Drop(vg4,over_4);
			        vg5 = (ViewGroup) findViewById(R.id.vg5_1);
			        	agregarDrag_and_Drop(vg5,over_5);
			        vg6 = (ViewGroup) findViewById(R.id.vg6_1);
			        	agregarDrag_and_Drop(vg6,over_6);
			        vg7 = (ViewGroup) findViewById(R.id.vg7_1);
			        	agregarDrag_and_Drop(vg7,over_7);
			        vg8 = (ViewGroup) findViewById(R.id.vg8_1);
			        	agregarDrag_and_Drop(vg8,over_8);
			        vg9 = (ViewGroup) findViewById(R.id.vg9_1);
			        	agregarDrag_and_Drop(vg9,over_9);
			        vg10 = (ViewGroup) findViewById(R.id.vg10_1);
			        	agregarDrag_and_Drop(vg10,over_10);
			        vg11 = (ViewGroup) findViewById(R.id.vg11_1);
			        	agregarDrag_and_Drop(vg11,over_11);
			        vg12 = (ViewGroup) findViewById(R.id.vg12_1);
			        	agregarDrag_and_Drop(vg12,over_12);
			        vg13 = (ViewGroup) findViewById(R.id.vg13_1);
			        	agregarDrag_and_Drop(vg13,over_13);
			        vg14 = (ViewGroup) findViewById(R.id.vg14_1);
			        	agregarDrag_and_Drop(vg14,over_14);
			        vg15 = (ViewGroup) findViewById(R.id.vg15_1);
			        	agregarDrag_and_Drop(vg15,over_15);			        
			        			
	    		break;
	        	case R.id.plantilla2:
	        		setContentView(R.layout.plantilla_2);
			        imagen1=(ImageView) findViewById(R.id.p2_img1);
			        imagen2=(ImageView) findViewById(R.id.p2_img2);
			        imagen3=(ImageView) findViewById(R.id.p2_img3);
			        imagen4=(ImageView) findViewById(R.id.p2_img4);
			        
			        imagen1.setOnClickListener(new View.OnClickListener(){				
						public void onClick(View v){
							dialog.show();
							i=1;
						}
					});		        
			        imagen2.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=2;
						}
					});
			        imagen3.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=3;
						}
					});
					imagen4.setOnClickListener(new View.OnClickListener(){							
						public void onClick(View v) {
							dialog.show();
							i=4;
						}
					});			
	    		break;
	        	case R.id.plantilla3:
	        		setContentView(R.layout.plantilla_3);
			        imagen1=(ImageView) findViewById(R.id.p3_img1);
			        imagen2=(ImageView) findViewById(R.id.p3_img2);

			        imagen1.setOnClickListener(new View.OnClickListener(){				
						public void onClick(View v){
							dialog.show();
							i=1;
						}
					});		        
			        imagen2.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();						
							i=2;
						}
					});		        
	    		break;
	        	case R.id.plantilla4:
	        		setContentView(R.layout.plantilla_4);
			        imagen1=(ImageView) findViewById(R.id.p4_img1);
			        imagen2=(ImageView) findViewById(R.id.p4_img2);
			        imagen3=(ImageView) findViewById(R.id.p4_img3);
			        imagen4=(ImageView) findViewById(R.id.p4_img4);
			        
			        imagen1.setOnClickListener(new View.OnClickListener(){				
						public void onClick(View v){
							dialog.show();
							i=1;
						}
					});		        
			        imagen2.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=2;
						}
					});
			        imagen3.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=3;
						}
					});
					imagen4.setOnClickListener(new View.OnClickListener(){							
						public void onClick(View v) {
							dialog.show();
							i=4;
						}
					});
	    		break;
	        	case R.id.plantilla5:
	        		setContentView(R.layout.plantilla_5);
			        imagen1=(ImageView) findViewById(R.id.p5_img1);
			        imagen2=(ImageView) findViewById(R.id.p5_img2);
			        imagen3=(ImageView) findViewById(R.id.p5_img3);
			        imagen4=(ImageView) findViewById(R.id.p5_img4);
			        imagen5=(ImageView) findViewById(R.id.p5_img5);
			        imagen6=(ImageView) findViewById(R.id.p5_img6);
			        
			        imagen1.setOnClickListener(new View.OnClickListener(){				
						public void onClick(View v){
							dialog.show();
							i=1;
						}
					});		        
			        imagen2.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=2;
						}
					});
			        imagen3.setOnClickListener(new View.OnClickListener(){					
						public void onClick(View v){
							dialog.show();
							i=3;
						}
					});
					imagen4.setOnClickListener(new View.OnClickListener(){							
						public void onClick(View v) {
							dialog.show();
							i=4;
						}
					});
					imagen5.setOnClickListener(new View.OnClickListener(){						
						public void onClick(View v){
							dialog.show();
							i=5;
						}
					});
					imagen6.setOnClickListener(new View.OnClickListener(){						
						public void onClick(View v){
							dialog.show();
							i=6;
						}
					});
	    		break;	        
	        }
	        
	        guardar = (Button) findViewById(R.id.guardar);
			guardar.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0){
	 				View ve = findViewById(R.id.comic);
	 		        ve.setDrawingCacheEnabled(true);
	 		        Bitmap b = ve.getDrawingCache();
	 		        String extr = Environment.getExternalStorageDirectory().toString();
	 		        File myPath = new File(extr);
	 		        if (!myPath.exists()){
	 		            boolean result = myPath.mkdir();
	 		        } 		        
	 		        myPath = new File(extr, getString(R.string.app_name) + ".jpg");
	 		        FileOutputStream fos = null;
	 		        try {
	 		            fos = new FileOutputStream(myPath);
	 		            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
	 		            fos.flush();
	 		            fos.close();
	 		            MediaStore.Images.Media.insertImage(getContentResolver(), b,
	 		                    "Screen", "screen");
	 		        }
	 		        catch (FileNotFoundException e) {
	 		            Log.e("Error", e + "");
	 		        }
	 		        catch (Exception e) {
	 		            Log.e("Error", e + "");
	 		        } 		 		        
	 		        crearDialogoAlerta("¡Enhorabuena!","¡Ya puedes mostrar tu comic!").show();
				} 			
			});
			
			compartir = (Button) findViewById(R.id.twitter);			
			compartir.setOnClickListener(new OnClickListener(){
				public void onClick(View arg){
					intent = new Intent(P1_comic.this,AndroidTwitterActivity.class);		        
					startActivity(intent);
				} 			
			});
			
			plus = (Button) findViewById(R.id.about);			
			plus.setOnClickListener(new OnClickListener(){
				public void onClick(View arg){
					crearDialogoAlerta("Comic It!","Desarrollada por Ana Arias, Liliana Ramos y, Denny Schuldt.").show();
				} 			
			});
		}
		
		/* Método: agregarDrag_and_Drop(ViewGroup vg, final ImageView img)
		 * Uso: agregarDrag_and_Drop(vg,img);
		 * ----------------------------------
		 * Descripción: Permite agregar la funcionalidad de Dran and Drop tanto al
		 * 				layout contenedor, como a la imagen dentro de el.
		 */		
		
		
		
		
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
