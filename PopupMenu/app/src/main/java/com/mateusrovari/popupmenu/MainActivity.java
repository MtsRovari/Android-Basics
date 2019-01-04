package com.mateusrovari.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPopup(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        
        if (item.getItemId() == R.id.act_next) {
            Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.act_previous) {
            Toast.makeText(this, "Previous", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
