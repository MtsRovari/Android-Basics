package com.mateusrovari.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //divider item (line)
        DividerItemDecoration dic = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dic);

        //set adapter
        ItemAdapter adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);

    }
}
