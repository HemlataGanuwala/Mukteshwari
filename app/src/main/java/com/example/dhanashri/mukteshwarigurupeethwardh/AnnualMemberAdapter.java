package com.example.dhanashri.mukteshwarigurupeethwardh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnnualMemberAdapter extends ArrayAdapter<AnnualMemberPlanet> implements Filterable {

    private Context mContext;
    private List<AnnualMemberPlanet> mProductList;
    private List<AnnualMemberPlanet> origPlanetList;
    private PlanetFilter filter;

    public AnnualMemberAdapter(@NonNull Context mContext, List<AnnualMemberPlanet> productList) {
        super(mContext, R.layout.list_item, productList);
        this.mContext = mContext;
        this.mProductList = new ArrayList<AnnualMemberPlanet>();
        this.mProductList.addAll(productList);
        this.origPlanetList = mProductList;
        getFilter();
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public AnnualMemberPlanet getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        PlanetHolder holder = new PlanetHolder();

        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);

            // Now we can fill the layout with the right value
            TextView tvName = (TextView) v.findViewById(R.id.tvname);
            TextView tvMobile = (TextView) v.findViewById(R.id.mobileno);
            TextView tvcity = (TextView) v.findViewById(R.id.tvcity);

            holder.name = tvName;
            holder.mobile = tvMobile;
            holder.city = tvcity;


            v.setTag(holder);
        } else
            holder = (PlanetHolder) v.getTag();

        AnnualMemberPlanet planet = mProductList.get(position);
        holder.name.setText(planet.getNAME());
        holder.mobile.setText("" + planet.getMOBILE_NO());
        holder.city.setText("" + planet.getCITY());

        return v;
    }

    public static class PlanetHolder {
        public TextView name;
        public TextView mobile;
        public TextView city;

    }

    public void resetData() {
        mProductList = origPlanetList;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)

            filter = new PlanetFilter();

        return filter;
    }

    private class PlanetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = origPlanetList;
                results.count = origPlanetList.size();
            }
            else {
                String prefixString = constraint.toString();
                List<AnnualMemberPlanet> nPlanetList = new ArrayList<AnnualMemberPlanet>();
                List<AnnualMemberPlanet> nPlanetListlocal = new ArrayList<AnnualMemberPlanet>();
                nPlanetListlocal.addAll(origPlanetList);

                final int count = nPlanetListlocal.size();

                for (int i = 0; i < count; i++) {
                    final AnnualMemberPlanet item = nPlanetListlocal.get(i);

//                    final String itemName = item.getNAME().toLowerCase();
//                    if (itemName.contains(prefixString)) {
//                        nPlanetList.add(item);
//                    }
//                    else { }


//                    final String itemMobileno = item.getMOBILE_NO()
//                            .toLowerCase();
//                    if (itemMobileno.contains(prefixString)) {
//                        nPlanetList.add(item);
//                    }
//                    else { }

                    final String itemCity = item.getCITY();
                    if (itemCity.contains(prefixString)) {
                        nPlanetList.add(item);
                    }
                    else { }

                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                mProductList = (List<AnnualMemberPlanet>) results.values;
                notifyDataSetChanged();

            }

        }
    }

//    @Override
//    public Filter getFilter() {
//        if (filter == null) {
//            filter = new AddressFilter();
//        }
//        return filter;
//    }



// InnerClass for enabling Search feature by implementing the methods

//    private class AddressFilter extends Filter
//    {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
////below checks the match for the cityId and adds to the filterlist
//            FilterResults results = new FilterResults();
////            // We implement here the filter logic
//            if (constraint == null || constraint.length() == 0) {
//                // No filter implemented we return all the list
//                results.values = origPlanetList;
//                results.count = origPlanetList.size();
//            }
//            else {
//                //String prefixString = constraint.toString().toLowerCase();
//
//
//                String city = constraint.toString();
////                FilterResults results1 = new FilterResults();
//
//                if (city != null) {
//                    List<AnnualMemberPlanet> nPlanetList = new ArrayList<AnnualMemberPlanet>();
//                    List<AnnualMemberPlanet> nPlanetListlocal = new ArrayList<AnnualMemberPlanet>();
//                    nPlanetListlocal.addAll(origPlanetList);
//                    //ArrayList<AnnualMemberPlanet> filterList = new ArrayList<AnnualMemberPlanet>();
//                    final int count = nPlanetListlocal.size();
//                    for (int i = 0; i < count; i++) {
//                        final AnnualMemberPlanet item = nPlanetListlocal.get(i);
//                        if ((mProductList.get(i).getCITY()) == item.getCITY()) {
//
//                            AnnualMemberPlanet cityp = mProductList.get(i);
//                            nPlanetList.add(cityp);
//                        }
//                    }
//
//                    results.count = nPlanetList.size();
//                    results.values = nPlanetList;
//
//                } else {
//
//                    results.count = mProductList.size();
//                    results.values = mProductList;
//
//                }
//            }
//            return results;
//        }
//        //Publishes the matches found, i.e., the selected cityids
//        @Override
//        protected void publishResults(CharSequence constraint,
//                                      FilterResults results) {
//
//            mProductList = (ArrayList<AnnualMemberPlanet>)results.values;
//            notifyDataSetChanged();
//        }
//    }
}






