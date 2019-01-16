package com.android.mateusrovari.resources;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView t = new TextView(this);
        //t.setText("Android App");
        t.setText(R.string.app_text);
        setContentView(t);
    }
}
