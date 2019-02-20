package com.mateusrovari.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service {

    private TimeWorker timeWorker;

    @Override
    public void onCreate() {
        super.onCreate();

        timeWorker = new TimeWorker();

        Log.e("***", "onCreate: TimeWorker");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(timeWorker).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeWorker.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
