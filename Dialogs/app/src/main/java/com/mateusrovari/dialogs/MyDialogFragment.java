package com.mateusrovari.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pergunta")
                .setMessage("Did you understand how dialogs works")
                .setPositiveButton("Yes", this)
                .setNegativeButton("No", this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        String msg = null;
        if (which == DialogInterface.BUTTON_POSITIVE){
            msg = "YES";
        }else if (which == Dialog.BUTTON_NEGATIVE){
            msg = "NO";
        }

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
