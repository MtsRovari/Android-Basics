package com.mateusrovari.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notification(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, NotificationUtils.getChannelId(this));
            builder.setContentTitle("Notification Title");
            builder.setContentText("Notification message");
            builder.setSmallIcon(android.R.drawable.sym_action_chat);

            Intent i = new Intent(this, MessageActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            Notification notification = builder.build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                notificationManager.notify(NOTIFICATION_ID, notification);
            }

        }

    }
}
