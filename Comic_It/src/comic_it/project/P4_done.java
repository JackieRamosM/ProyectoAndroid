package comic_it.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

	
public class P4_done extends Activity{
		
	Button twitter;
	Intent intent;
	
	@Override
    public void onCreate(Bundle savedInstanceState){    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4_done);
        
        twitter = (Button) findViewById(R.id.twitter);
        twitter.setOnClickListener(new OnClickListener(){
 			public void onClick(View arg0){ 				     
		        intent = new Intent(P4_done.this,AndroidTwitterActivity.class);		        
		        startActivity(intent); 				
 			} 			
 		});
    }
}
