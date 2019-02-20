package com.mateusrovari.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        intent = new Intent(getApplicationContext(),TimeWorker.class);
        startService(intent);
    }

    public void stop(View view) {
        if (intent != null) {
            stopService(intent);
        }
    }
}
