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
public class MantradikshaFragment extends Fragment {

WebView webView;
WebSettings webSettings;
View view;
    public MantradikshaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mantradiksha, container, false);
        webView = (WebView)view.findViewById(R.id.webviewmantra);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://mantradiksha.skyvisioncables.com/");
        // Inflate the layout for this fragment
        return view ;
        // Inflate the layout for this fragment

    }

}
