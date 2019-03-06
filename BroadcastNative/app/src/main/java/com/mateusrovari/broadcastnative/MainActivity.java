package com.mateusrovari.broadcastnative;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EnergyReceiver energyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        energyReceiver = new EnergyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(energyReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        if (energyReceiver != null)
            unregisterReceiver(energyReceiver);

        super.onDestroy();
    }
}
