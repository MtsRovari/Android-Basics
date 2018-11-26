package com.example.mateusrovari.fragmentcommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TextFragment.OnInvertListener {

    private ResultFragment resultFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultFrag = (ResultFragment) getFragmentManager().findFragmentById(R.id.frag_result);

    }

    @Override
    public void OnInvert(String text) {
        resultFrag.invert(text);
    }
}
