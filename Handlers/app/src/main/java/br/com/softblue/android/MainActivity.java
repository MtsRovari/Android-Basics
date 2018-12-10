package br.com.softblue.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView textView;
    private Button btnProcessar;
    //private Handler handler = new Handler();
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_status);
        btnProcessar = (Button) findViewById(R.id.btn_processar);

        handler = new MyHandler(textView, btnProcessar);
    }

    public void processar(View view) {
        textView.setText(R.string.processando);
        btnProcessar.setEnabled(false);
        executarAlgoDemorado();
    }

    private void executarAlgoDemorado() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(15000);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(R.string.finalizado);
//                        btnProcessar.setEnabled(true);
//                    }
//                });

//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(R.string.finalizado);
//                       btnProcessar.setEnabled(true);
//                    }
//                });

                Message msg = Message.obtain();
                msg.what = MyHandler.MSG_UPDATE_UI;
                handler.sendMessage(msg);
            }
        }).start();
    }
}
