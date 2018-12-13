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
public class PersonalInfoFragment extends Fragment {

    View view;
    EditText name, address, city, district, state, country, mobileno, lindlineno, emailid, occupation, education, dob, age;


    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        name=(EditText)view.findViewById(R.id.etname);
        address=(EditText)view.findViewById(R.id.etaddress);
        city=(EditText) view.findViewById(R.id.etcity);
        district=(EditText) view.findViewById(R.id.etdistrict);
        state=(EditText)view.findViewById(R.id.etstate);
        country=(EditText)view.findViewById(R.id.etcountry);
        mobileno=(EditText)view.findViewById(R.id.etmobileno);
        lindlineno=(EditText)view.findViewById(R.id.etlandlin);
        emailid=(EditText)view.findViewById(R.id.etemailid);
        occupation=(EditText)view.findViewById(R.id.etoccupation);
        education=(EditText)view.findViewById(R.id.eteducation);
        age=(EditText)view.findViewById(R.id.etage);
        dob=(EditText)view.findViewById(R.id.etdob);

        return view;
    }

}
