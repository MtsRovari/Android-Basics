package br.com.softblue.android;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MyHandler extends Handler {

    public static final int MSG_UPDATE_UI = 100;

    private TextView textView;
    private Button btnProcessar;

    public MyHandler(TextView textView, Button btnProcessar) {
        this.textView = textView;
        this.btnProcessar = btnProcessar;
    }

    @Override
    public void handleMessage(Message msg) {

        if (msg.what == MSG_UPDATE_UI) {
            btnProcessar.setEnabled(true);
            textView.setText(R.string.finalizado);
        }
    }
}
