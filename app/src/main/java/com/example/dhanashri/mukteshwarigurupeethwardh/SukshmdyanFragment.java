package com.example.dhanashri.mukteshwarigurupeethwardh;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SukshmdyanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SukshmdyanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SukshmdyanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match



    public SukshmdyanFragment() {
        // Required empty public constructor
    }

    WebView webview;
    WebSettings webSettings;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_sukshmdyan, container, false);
        webview = (WebView)view.findViewById(R.id.webviewSukshmdhyan);

        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadUrl("http://sukshmadhyan.skyvisioncables.com/");
        // Inflate the layout for this fragment
        return view ;


    }


}
