package twitter.comicit;

import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//http://codehenge.net/blog/2011/06/android-development-tutorial-asynchronous-lazy-loading-and-caching-of-listview-images/
public class TweetItemAdapter extends ArrayAdapter<Tweetc> {
	  private ArrayList<Tweetc> tweets;

	  public TweetItemAdapter(Context context, int textViewResourceId, ArrayList<Tweetc> tweets) {
	    super(context, textViewResourceId, tweets);
	    this.tweets = tweets;
	  }

	  @Override
	  public View getView(  int position, View convertView, ViewGroup parent) {
	    View v = convertView;
	//      Toast.makeText(v.getContext(), "formateando", Toast.LENGTH_SHORT );
	   
	    	if (v == null) {
	      LayoutInflater vi =
	         (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      v = vi.inflate(R.layout.listitem, null);
	    }

	    Tweetc tweet = tweets.get(position);
	    if (tweet != null) {
	      TextView username = (TextView) v.findViewById(R.id.username);
	      TextView message = (TextView) v.findViewById(R.id.message);
	      ImageView image = (ImageView) v.findViewById(R.id.avatar);

	      if (username != null) {
	        username.setText(tweet.username);
	        
	      }

	      if(message != null) {
	        message.setText(tweet.message);
	      }
	       
	      if(image != null) {
	        image.setImageBitmap(getBitmap(tweet.image_url));
	      }
	    }
	    return v;
	  }
	  public Bitmap getBitmap(String bitmapUrl) {
		  try {
		    URL url = new URL(bitmapUrl);
		    return BitmapFactory.decodeStream(url.openConnection().getInputStream());
		  }
		  catch(Exception ex) {return null;}
		}
	}