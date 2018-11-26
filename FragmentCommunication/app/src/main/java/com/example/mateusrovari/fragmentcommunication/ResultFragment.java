package com.example.mateusrovari.fragmentcommunication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment{

    private TextView txtInverted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        txtInverted = view.findViewById(R.id.txt_text);

        return view;
    }

    public void invert(String text){
        if (text != null){
          String inverted = new StringBuilder(text).reverse().toString();
          txtInverted.setText(inverted);
        }
    }
}
