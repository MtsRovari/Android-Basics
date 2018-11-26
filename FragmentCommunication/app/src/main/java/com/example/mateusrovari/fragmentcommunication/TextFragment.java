package com.example.mateusrovari.fragmentcommunication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TextFragment extends Fragment implements View.OnClickListener {

    private EditText edtText;
    private OnInvertListener listner;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof OnInvertListener)){
            throw new RuntimeException("A activity deve implementar a interface TextFragment.OnInvertListner");
        }

        listner = (OnInvertListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        edtText = (EditText) view.findViewById(R.id.edt_texto);

        Button btnInverter = (Button) view.findViewById(R.id.btn_inverter);
        btnInverter.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if (listner != null){
            String texto = edtText.getText().toString();
            listner.OnInvert(texto);
        }
    }

    public interface OnInvertListener{
        public void OnInvert(String text);

    }
}
