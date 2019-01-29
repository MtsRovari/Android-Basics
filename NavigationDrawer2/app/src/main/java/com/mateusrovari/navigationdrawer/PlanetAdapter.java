package com.mateusrovari.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PlanetAdapter extends BaseAdapter {

    private List<Planet> planets;
    private OnItemClickListner listener;

    public PlanetAdapter(Context context, OnItemClickListner listener) {
        planets = Planet.buildPlanets(context);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int position) {
        return planets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh;
        final Planet p = planets.get(position);

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(parent.getContext());
            view = vi.inflate(R.layout.drawer_list_item, parent, false);

            vh = new ViewHolder();
                vh.mTextView = view.findViewById(android.R.id.text1);
            vh.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(p);
                }
            });
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.mTextView.setText(p.getName());
        return view;
    }

    public interface OnItemClickListner {
        public void onClick(Planet planet);
    }

    private static class ViewHolder {
        public TextView mTextView;
    }
}
