package twitter.comicit;



import java.util.ArrayList;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ShowActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_show);
        setContentView(R.layout.main);  
        ArrayList<Tweetc> tweets =  ActivitiesBringe.getObject();
		  ListView listView = (ListView) findViewById(R.id.ListViewId);
	  //	 Windowprocessing wp  = new   Windowprocessing ( this );
	 //	 wp.start();
		  listView.setAdapter(new TweetItemAdapter(this  , R.layout.listitem, tweets));
		 
		  //    wp.dismiss();
      //   wp.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show, menu);
        return true;
    }
}

