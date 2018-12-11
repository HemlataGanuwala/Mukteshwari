package com.example.dhanashri.mukteshwarigurupeethwardh;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DikshasInfoFragment extends Fragment {

    View view;
    EditText dod, annual_member, no_of_annual_attempt, last_insentive_date, location, area_of_zone, zonal_head, zonal_head_contact_no, destination;


    public DikshasInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_dikshas_info, container, false);

        dod=(EditText)view.findViewById(R.id.etdob);
        annual_member=(EditText)view.findViewById(R.id.etannualmember);
        no_of_annual_attempt=(EditText)view.findViewById(R.id.etattempt);
        last_insentive_date=(EditText)view.findViewById((R.id.etlastinsentivedate));
        location=(EditText)view.findViewById(R.id.etlocation);
        area_of_zone=(EditText)view.findViewById(R.id.etareazone);
        zonal_head=(EditText)view.findViewById(R.id.etzonalhead);
        zonal_head_contact_no=(EditText)view.findViewById(R.id.etzonalheadno);
        destination=(EditText)view.findViewById(R.id.etdestination);

        return view;
    }

}
