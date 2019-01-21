package com.example.mateusrovari.notification2;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String ACTION_BUTTON_1 = "com.mateusrovari.android.action.ACTION_BUTTON_1";
    public static final String ACTION_BUTTON_2 = "com.mateusrovari.android.action.ACTION_BUTTON_2";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String msg;

        switch (action) {
            case ACTION_BUTTON_1:
                msg = "Clicked! #1";
                break;
            case ACTION_BUTTON_2:
                msg = "Clicked! #2";
                break;
            default:
                msg = "Unknown action";
                break;
        }

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancel(MainActivity.NOTIFICATION_ID);
    }
}
