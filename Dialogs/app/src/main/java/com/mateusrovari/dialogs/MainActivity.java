package com.mateusrovari.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Open(View view){

        MyDialogFragment dialog = new MyDialogFragment();
        dialog.show(getFragmentManager(), "dialog");
    }

    public void OpenSimple(View view) {
        SimpleDialogFragment dialog = new SimpleDialogFragment();
        dialog.show(getFragmentManager(), "dialogSimple");
    }

    public void OpenRadio(View view) {
        RadioDialogFragment dialog = new RadioDialogFragment();
        dialog.show(getFragmentManager(), "dialogRadio");
    }

    public void OpenMultiple(View view) {
        MultipleDialogFragment dialog = new MultipleDialogFragment();
        dialog.show(getFragmentManager(), "dialogMultiple");
    }
}
