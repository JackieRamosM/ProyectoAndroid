package comic_it.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
	
	public class P2_symbols extends Activity{
		Button btn_okk;
		Intent intent;
		@Override
	    public void onCreate(Bundle savedInstanceState){    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.p2_symbols);  
	        btn_okk= (Button) findViewById(R.id.btn_ok);
	        btn_okk.setOnClickListener((OnClickListener) this);
    }
		
		public void onClick(View arg0) {
			intent = new Intent(P2_symbols.this,VtnEdicion.class);		
			intent.putExtra("texto seleccionado",R.id.ivPrincipal);
		    startActivity(intent); 
			}
}
