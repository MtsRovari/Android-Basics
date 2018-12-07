package com.mateusrovari.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SimpleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choise a language")
                .setItems(R.array.languages, this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] languages = getActivity().getResources().getStringArray(R.array.languages);
        String language = languages[which];

        Toast.makeText(getActivity(), "Language: " + language, Toast.LENGTH_SHORT).show();
    }
}
