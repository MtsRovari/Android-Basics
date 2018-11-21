package com.example.mateusrovari.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductsAdapter extends BaseAdapter {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private List<Product> products;
    private LayoutInflater inflater;

    public  ProductsAdapter(Context context, List<Product> products){
        this.products = products;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder vh;

        if (view == null){
            view = inflater.inflate(R.layout.adapter_products, parent, false);

            vh = new ViewHolder();
            vh.txtNome = view.findViewById(R.id.txt_nome);
            vh.txtPrice = view.findViewById(R.id.txt_price);
            view.setTag(vh);

        }else{
            vh = (ViewHolder) view.getTag();
        }

        Product product = products.get(position);

        vh.txtNome.setText(product.getNome());
        vh.txtPrice.setText(nf.format(product.getPrice()));

        return view;
    }

    public static class ViewHolder{
        public TextView txtNome;
        public TextView txtPrice;
    }
}
