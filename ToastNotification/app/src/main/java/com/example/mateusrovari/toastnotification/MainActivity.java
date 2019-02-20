package com.example.mateusrovari.toastnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notify1(View view) {
        Toast.makeText(this, "Simple Toast", Toast.LENGTH_SHORT).show();
    }

    public void notify2(View view) {
        Toast t =  Toast.makeText(this, "Toast with gravity", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    public void notify3(View view) {
        Toast t = new Toast(this);
        t.setGravity(Gravity.CENTER, 0, 0);

        View v = getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView textView = view.findViewById(R.id.txt_toast);
        textView.setText("Custom Toast");

        t.setView(v);
        t.show();
    }
}
