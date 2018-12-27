package com.mateusrovari.broadcast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mateusrovari.broadcast.TOAST");
        registerReceiver(myReceiver, intentFilter);
    }

    public void send(View view) {
        startActivity(new Intent(MainActivity.this, ButtonActivity.class));
    }

    public void changeColor() {
        RelativeLayout rel = findViewById(R.id.relative);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        rel.setBackgroundColor(color);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myReceiver);
    }
}
