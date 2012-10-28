package com.test.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class helloworld extends Activity
{
/** Called when the activity is first created. */
@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView text1 = new TextView(this);
        text1.setText("Hello World");
        setContentView(text1);
    }
}
