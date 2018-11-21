package com.mateusrovari.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

    private List<Item> items = new ArrayList<>();
    private Context context;

    public  ItemAdapter(Context context){
        this.context = context;

        for (int i = 0; i < 100; i++){
            Item item = new Item("Item " + (i + 1), "Item " + (i + 1)
                    , "" + (i + 1));
            items.add(item);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = items.get(position);

        holder.itemName.setText(item.getItemName());
        holder.itemDescription.setText(item.getItemDescription());
        holder.itemColor.setBackgroundResource(R.drawable.bg_circle_blue);
        holder.itemNumber.setText(item.getItemNumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemName, itemDescription, itemNumber;
        public RelativeLayout itemColor, relButton;

        public ItemHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemNumber = itemView.findViewById(R.id.item_number);
            itemColor = itemView.findViewById(R.id.item_background);
            relButton = itemView.findViewById(R.id.relButton);
            relButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            items.remove(pos);

            Toast.makeText(context, "Item removed!", Toast.LENGTH_SHORT).show();
            notifyItemRemoved(pos);
        }
    }
}
