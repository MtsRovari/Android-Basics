package com.mateusrovari.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class RadioDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choise a language")
                .setSingleChoiceItems(R.array.languages, 1, this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String[] languages = getActivity().getResources().getStringArray(R.array.languages);
        String language = languages[which];

        Toast.makeText(getActivity(), "Language: " + language, Toast.LENGTH_SHORT).show();
    }
}

