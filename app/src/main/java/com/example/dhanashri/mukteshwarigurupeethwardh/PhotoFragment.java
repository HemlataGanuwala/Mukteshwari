package com.example.dhanashri.mukteshwarigurupeethwardh;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {

    String person_name, path;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    View view;


    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

       imageView1=(ImageView)view.findViewById(R.id.imgview1);
       imageView2=(ImageView)view.findViewById(R.id.imgview2);
       imageView3=(ImageView)view.findViewById(R.id.imgview3);
       imageView4=(ImageView)view.findViewById(R.id.imgview4);
       imageView5=(ImageView)view.findViewById(R.id.imgview5);

        return view;
    }

}
