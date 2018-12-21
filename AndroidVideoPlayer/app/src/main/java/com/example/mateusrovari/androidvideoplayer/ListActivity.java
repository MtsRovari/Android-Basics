package com.example.mateusrovari.androidvideoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;

public class ListActivity extends AppCompatActivity {

    private RelativeLayout mVideoLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initComponents();
        setupWidgets();
    }

    private void initComponents() {
        mVideoLayout = findViewById(R.id.video_layout);
    }

    private void setupWidgets() {
        mVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, VideoActivity.class));
            }
        });
    }
}
