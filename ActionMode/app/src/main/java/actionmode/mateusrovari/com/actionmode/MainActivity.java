package actionmode.mateusrovari.com.actionmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, ActionMode.Callback{

    private TextView mView;
    private int count;
    private boolean actionModeActive;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.number);
        mView.setText("0");
        mView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {

        if (!actionModeActive){
            startActionMode(this);
            actionModeActive = true;
        }

        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        Log.d(TAG, "onActionItemClicked: Clicked on Item");
        if (item.getItemId() == R.id.act_add){
            count++;
            mView.setText(String.valueOf(count));
            mode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        actionModeActive = false;
    }
}
