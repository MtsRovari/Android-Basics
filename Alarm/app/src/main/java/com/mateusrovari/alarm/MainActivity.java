package com.mateusrovari.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager manager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public void schedule(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void cancel(View view) {
        if (pendingIntent != null){
            manager.cancel(pendingIntent);
        }
    }
}
