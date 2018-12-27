package com.mateusrovari.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void send(View view) {
        Intent i = new Intent("com.mateusrovari.broadcast.TOAST");
        i.putExtra("msg", "This is a broadcast");
        sendBroadcast(i);
    }

    public void mainactivity(View view) {
        startActivity(new Intent(ButtonActivity.this, MainActivity.class));
    }

}
