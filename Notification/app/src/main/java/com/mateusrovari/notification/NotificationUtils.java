package com.mateusrovari.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {

    private static final String CHANNEL_ID = "com.mateusrovari.android.channel";
    private static final String CHANNEL_NAME = "My channel";
    private static NotificationChannel notificationChannel;

    public static String getChannelId(Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
        }

        if (notificationChannel == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        return CHANNEL_ID;
    }
}
