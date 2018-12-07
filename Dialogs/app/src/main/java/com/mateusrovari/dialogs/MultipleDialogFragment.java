package com.mateusrovari.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MultipleDialogFragment extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        boolean[] checked = {true, false, true, false};

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choise a language")
                .setMultiChoiceItems(R.array.languages, checked, this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        String[] languages = getActivity().getResources().getStringArray(R.array.languages);
        String language = languages[which];

        if (isChecked){
            Toast.makeText(getActivity(), "Language: " + language, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "!Language: " + language, Toast.LENGTH_SHORT).show();
        }
    }
}

