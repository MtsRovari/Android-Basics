package com.example.mateusrovari.activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OpenSecond(View v){
        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(i, 1);
        textView1 = (TextView) findViewById(R.id.textView1);
        editText2 = findViewById(R.id.editText2);
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 2
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            String receivedMessage = data.getStringExtra("Data");
            editText2.setText(receivedMessage);

        }

    }

}
