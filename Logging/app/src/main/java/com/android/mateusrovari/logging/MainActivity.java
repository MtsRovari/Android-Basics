package com.android.mateusrovari.logging;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    //private static final String TAG = "MyApp";

    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.i(TAG, "Info Message");
        //Log.d(TAG, "Debug Message");
        //Log.w(TAG, "Warn Message");
        //Log.e(TAG, "Error Message");
        //Log.v(TAG, "Verbose Message");

        msg = (TextView) findViewById(R.id.msg);

        msg.setText("Error test");
    }
}
