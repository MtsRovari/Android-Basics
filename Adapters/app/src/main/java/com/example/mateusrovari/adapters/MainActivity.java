package com.example.mateusrovari.adapters;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ItemAdapter(this, Item.getProducts());
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Item product = (Item) mAdapter.getItem(position);
        Toast.makeText(this, "Item: " + product.getNome(), Toast.LENGTH_SHORT).show();
    }
}
