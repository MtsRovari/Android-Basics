package com.example.mateusrovari.progressbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtContent;
    private ProgressBar progress;
    private int animationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        animationTime = getResources().getInteger(android.R.integer.config_longAnimTime);

        setContentView(R.layout.activity_main);

        txtContent = (TextView) findViewById(R.id.txtContent);
        progress = (ProgressBar) findViewById(R.id.progress);
        txtContent.setVisibility(View.GONE);

        new  Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showContent();
                    }
                });
            }
        }).start();
    }

    private void showContent(){
        //txtContent.setVisibility(View.VISIBLE);
        //progress.setVisibility(View.GONE);

        txtContent.setVisibility(View.VISIBLE);
        txtContent.setAlpha(0.0f);

        txtContent.animate()
                .alpha(1.0f)
                .setDuration(animationTime)
                .setListener(null);
        progress.animate()
                .alpha(0.0f)
                .setDuration(animationTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.setVisibility(View.GONE);
                    }
                });
    }
}
