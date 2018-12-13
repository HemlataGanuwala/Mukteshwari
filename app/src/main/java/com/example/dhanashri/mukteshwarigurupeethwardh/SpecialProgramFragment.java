package com.example.dhanashri.mukteshwarigurupeethwardh;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialProgramFragment extends Fragment {
    View view;
    WebView webView;
    WebSettings webSettings;
    public SpecialProgramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_special_program, container, false);
        webView = (WebView)view.findViewById(R.id.webview);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://spcialprogram.skyvisioncables.com/");
        // Inflate the layout for this fragment
        return view ;
    }

}
