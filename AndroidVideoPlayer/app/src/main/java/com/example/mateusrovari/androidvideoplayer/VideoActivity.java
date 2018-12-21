package com.example.mateusrovari.androidvideoplayer;

import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView mVideo;
    private Uri mUri1, mUri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initComponents();
        setupWidgets();
    }

    private void initComponents() {
        mVideo = findViewById(R.id.video_view);
    }

    private void setupWidgets() {

        //fullscreen activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        //from drawable
        mUri2 =  Uri.parse("android.resource://" + getPackageName() + "/");  // + R.draw.yourvideo

        //from a server
        mUri1 = Uri.parse("your video uri");
        mVideo.setVideoURI(mUri1);
        mVideo.start();

        mVideo.setMediaController(new MediaController(this));
    }
}
