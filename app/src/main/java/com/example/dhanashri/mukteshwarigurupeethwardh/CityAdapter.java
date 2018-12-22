package com.example.dhanashri.mukteshwarigurupeethwardh;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<SpinnerCityPlanet> implements Filterable {

    private Context mContext;
    private List<SpinnerCityPlanet> cityList;


    public CityAdapter(Context context, int textViewResourceId,
                       List<SpinnerCityPlanet> values) {
        super(context, textViewResourceId, values);
        this.mContext = context;
        this.cityList = values;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public SpinnerCityPlanet getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(mContext);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText(cityList.get(position).getCity_name());

        return view;
    }

    //View of Spinner on dropdown Popping

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(mContext);
        view.setTextColor(Color.BLACK);
        view.setText(cityList.get(position).getCity_name());
        view.setHeight(60);

        return view;
    }
}






