package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        LinearLayout linearLayout= new LinearLayout(this);// le pasamos this porque nos pide que le pasemos un context, y la clase ventana que extienden de activity heredan de context
        TextView tv=new TextView(this);
        TextView tv1=new TextView(this);
        tv1.setText(R.string.hello);
        tv.setText(R.string.app_name);
        linearLayout.addView(tv1);
        linearLayout.addView(tv);
        Button btn= new Button(this);
        linearLayout.addView(btn);
        setContentView(linearLayout);
       
    }
}
