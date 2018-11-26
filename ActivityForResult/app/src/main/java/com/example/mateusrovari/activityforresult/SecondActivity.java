package com.example.mateusrovari.activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity {

    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        editText1 = (EditText) findViewById(R.id.editText1);

    }

    public void SendMeHome(View v){
        Intent i = new Intent();
        String message = editText1.getText().toString();
        i.putExtra("Data", message);
        setResult(RESULT_OK, i);
        finish();
    }

}

