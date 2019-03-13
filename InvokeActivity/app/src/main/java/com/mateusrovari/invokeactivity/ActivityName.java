package com.mateusrovari.invokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityName extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_name);

    }

    public void next(View view) {
//        Intent i = new Intent(this, ActivityEmail.class);
//        startActivity(i);

        Intent i = new Intent("com.mateusrovari.intent.action.Email");
        startActivity(i);
    }

    public void settings(View view) {
        Intent i = new Intent(Settings.ACTION_SETTINGS);
        startActivity(i);
    }
}
