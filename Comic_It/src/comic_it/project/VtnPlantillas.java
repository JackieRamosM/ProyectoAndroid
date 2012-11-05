package comic_it.project;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class VtnPlantillas extends Activity implements ViewFactory {
  Integer[] imageIDs = { R.drawable.plantilla1, R.drawable.plantilla2,R.drawable.plantilla3,R.drawable.plantilla5 };
  private ImageSwitcher imageSwitcher;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.plantillas);
    imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher1);
    imageSwitcher.setFactory(this);
    imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
        android.R.anim.slide_in_left));
    imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
        android.R.anim.slide_out_right));

    Gallery gallery = (Gallery) findViewById(R.id.gallery1);
    gallery.setAdapter(new ImageAdapter(this));
    gallery.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v,
          int position, long id) {
        imageSwitcher.setImageResource(imageIDs[position]);
      }
    });
  }

  public View makeView() {
    ImageView imageView = new ImageView(this);
    imageView.setBackgroundColor(0xFF000000);
    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
        LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    return imageView;
  }

  public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int itemBackground;

    public ImageAdapter(Context c) {
      context = c;

     TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
      itemBackground = a.getResourceId(
          R.styleable.Gallery1_android_galleryItemBackground, 0);
      a.recycle();
    }

    public int getCount() {
      return imageIDs.length;
    }

    public Object getItem(int position) {
      return position;
    }

    public long getItemId(int position) {
      return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView = new ImageView(context);

      imageView.setImageResource(imageIDs[position]);
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
      imageView.setBackgroundResource(itemBackground);

      return imageView;
    }
  }
}