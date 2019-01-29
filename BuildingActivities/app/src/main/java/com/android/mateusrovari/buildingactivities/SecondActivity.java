package com.android.mateusrovari.buildingactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String message = getIntent().getStringExtra("text");
        TextView textView = findViewById(R.id.txt);

        textView.setText(message);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("num", 1000);

        setResult(RESULT_OK, intent);
        finish();
    }
}
