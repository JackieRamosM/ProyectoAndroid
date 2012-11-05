package comic_it.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VtnPrincipal extends Activity  {
	
	Button btnComenzar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //mostrarToast();
        //activarCarga();
        btnComenzar= (Button)findViewById(R.id.comenzar);
        btnComenzar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent vtnplantillas = new Intent(VtnPrincipal.this,VtnPlantillas.class);
				startActivity(vtnplantillas); 

			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
    
   


    
}
