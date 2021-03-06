package comic_it.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        mostrarToast();        
        esperar();
    }   
    
    public void mostrarToast(){
        Toast carga = new Toast(getApplicationContext());        
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
        (ViewGroup) findViewById(R.id.layoutToast));
        TextView mensaje = (TextView) layout.findViewById(R.id.txtMensaje);
        mensaje.setText("Inicializando contenido...");
        carga.setView(layout);        
        carga.show();
    }
    
    public void esperar(){    	
        Thread hilo = new Thread(new Runnable(){
            public void run(){
                try{
                    vtnPlantillas();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        hilo.start();
    }
    
    public void vtnPlantillas(){        
        try{            
            Thread.sleep(3500);            
            cargarActivity();                
        }catch(Exception e){
            e.printStackTrace();            
        }
    }           
    
    public void cargarActivity(){
        finish();        
        Intent intent = new Intent(this.getApplicationContext(),VtnPlantillas.class);
        startActivity(intent);
    }
}
