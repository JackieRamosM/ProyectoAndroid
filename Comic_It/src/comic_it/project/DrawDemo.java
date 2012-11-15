package comic_it.project;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DrawDemo extends Activity {
    InternalView myView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        myView = new InternalView(this);
        setContentView(myView);
    }
    private class InternalView extends View{
        public InternalView(Context context){
            super(context);
        }
 
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            canvas.drawRect(16, 16, getWidth()-16, getHeight()-16, paint);
 
        }
    }
}