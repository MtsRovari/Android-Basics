package com.example.mateusrovari.adapters;

import android.app.ListActivity;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    //private ArrayAdapter<CharSequence> adapter;
    //private ArrayAdapter<String> adapter;
    private  ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adapter = ArrayAdapter.createFromResource(this, R.array.produtos, android.R.layout.simple_list_item_1);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //adapter.add("Coffe");
        //adapter.add("Bean");
        //adapter.add("Rice");

        adapter = new ProductsAdapter(this, Product.getProducts());

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //String item = adapter.getItem(position);
        Product product = (Product) adapter.getItem(position);
        Toast.makeText(this, "Item Clicado" + product.getNome(), Toast.LENGTH_SHORT).show();
    }
}
