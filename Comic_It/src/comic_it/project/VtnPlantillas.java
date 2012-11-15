package comic_it.project;

import android.app.Activity;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.widget.Button;

/**
 *
 * @author Denny
 */
public class VtnPlantillas extends Activity{    
	
	Button aceptar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantillas);
        aceptar = (Button) findViewById(R.id.aceptar);
        aceptar.getBackground().setColorFilter(0xFFFF0000, Mode.MULTIPLY);
    }
}