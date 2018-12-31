package com.mateusrovari.actionmodemulti;

import android.app.ListActivity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements AbsListView.MultiChoiceModeListener {

    private ArrayAdapter<String> mAdapter;
    private ListView mListView;
    private List<String> mSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mAdapter.add("A");
        mAdapter.add("B");
        mAdapter.add("C");
        mAdapter.add("D");
        mAdapter.add("E");
        mAdapter.add("F");
        mAdapter.add("G");
        mAdapter.add("H");
        mAdapter.add("I");
        mAdapter.add("J");
        mAdapter.add("K");

        setListAdapter(mAdapter);

        mListView = getListView();
        mListView.setMultiChoiceModeListener(this);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        String s = mAdapter.getItem(position);
        View mView = mListView.getChildAt(position);

        if (checked) {
            mSelected.add(s);
            mView.setBackgroundColor(Color.GREEN);
        } else {
            mSelected.remove(s);
            mView.setBackgroundColor(Color.TRANSPARENT);
        }
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

        if (item.getItemId() == R.id.delete) {
            for (String s : mSelected) {
                mAdapter.remove(s);
            }
            mode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        int count = mListView.getChildCount();

        for (int i = 0; i < count; i++) {
            View mView = mListView.getChildAt(i);
            mView.setBackgroundColor(Color.TRANSPARENT);
        }
        mSelected.clear();
    }
}