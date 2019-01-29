package com.android.mateusrovari.buildingactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void next(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra("text", "this text came from the First activity");
        //startActivity(intent);
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            int num = data.getIntExtra("num", -1);
            Toast.makeText(this, "The return was: " + num, Toast.LENGTH_LONG).show();

            Button button = findViewById(R.id.btnFirst);
            button.setEnabled(false);
        }
    }
}
