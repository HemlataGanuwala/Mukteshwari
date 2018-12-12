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
public class TotalPersonInfoFragment extends Fragment {

    View view;
    EditText total_family_member, total_sadhak, total_mahashivratri_expected, total_mahashivratri_taken, total_gurupornima_expected, total_gurupormina_taken, total_navratri_expected, total_naratri_taken;


    public TotalPersonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_total_person_info, container, false);

        total_family_member=(EditText)view.findViewById(R.id.ettotalfamilymember);
        total_sadhak=(EditText)view.findViewById(R.id.ettotalsadhak);
        total_mahashivratri_expected=(EditText)view.findViewById(R.id.etmahashivratriexpecte);
        total_mahashivratri_taken=(EditText)view.findViewById(R.id.etmahashivtatritaken);
        total_gurupornima_expected=(EditText)view.findViewById(R.id.etgurupornimaexpected);
        total_gurupormina_taken=(EditText)view.findViewById(R.id.etgurupornimataken);
        total_navratri_expected=(EditText)view.findViewById(R.id.etnavratriexpect);
        total_family_member=(EditText)view.findViewById(R.id.etnavratritaken);

        return view;
    }

}
