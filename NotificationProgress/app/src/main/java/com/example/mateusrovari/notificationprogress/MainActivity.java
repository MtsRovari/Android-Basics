package com.example.mateusrovari.notificationprogress;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1;
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager  = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void notify1(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        final Notification.Builder builder = new Notification.Builder(this, NotificationUtils.getChannelId(this));
            builder.setContentTitle("Downloading file")
                    .setContentText("Please, wait...")
                    .setSmallIcon(android.R.drawable.stat_sys_download);
            builder.setProgress(0, 0, true);

            manager.notify(NOTIFICATION_ID, builder.build());

            new Thread() {
                @Override
                public void run() {
                    SystemClock.sleep(5000);
                    builder.setProgress(0, 0, false);
                    builder.setContentTitle("File Downloaded").setContentText("Finished");
                    manager.notify(NOTIFICATION_ID, builder.build());
                }
            }.start();
        }

    }

    public void notify2(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            final Notification.Builder builder = new Notification.Builder(this, NotificationUtils.getChannelId(this));
            builder.setContentTitle("Downloading file")
                    .setContentText("Please, wait...")
                    .setSmallIcon(android.R.drawable.stat_sys_download);

            manager.notify(NOTIFICATION_ID, builder.build());

            new Thread() {
                @Override
                public void run() {

                    for (int i = 1; i <= 100; i += 10) {
                        builder.setProgress(100, i, false);
                        SystemClock.sleep(2000);
                    }

                    SystemClock.sleep(5000);
                    builder.setProgress(0, 0, false);
                    builder.setContentTitle("File Downloaded").setContentText("Finished");
                    manager.notify(NOTIFICATION_ID, builder.build());
                }
            }.start();
        }
    }
}
