package com.mateusrovari.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Toast;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, 2020, 1, 13);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        String msg = String.format("You chose the date: %02d/%02d/%d", dayOfMonth, month + 1, year);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
