package com.example.mateusrovari.fragmentcompatibillity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //foi criada sem activity e nao foi exportada appcompatactivity
    }
}
