package com.mateusrovari.dialogcomm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class ExitDialog extends DialogFragment implements DialogInterface.OnClickListener{

    private ExitListner listner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof ExitListner)){
            throw new RuntimeException("A activity precisa implementar a interface ExitDialog.ExitListner");
        }

        listner = (ExitListner) getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Deseja sair?")
                .setPositiveButton("Sim", this)
                .setNegativeButton("Não", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE && listner != null){
            listner.onExit();
        }
    }

    public interface ExitListner{

        public void onExit();

    }
}
