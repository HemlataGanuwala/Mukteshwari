package com.example.dhanashri.mukteshwarigurupeethwardh;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DikshasInfoFragment extends Fragment {

    View view;
    EditText editTextdod, editTextannual_member, editTextno_of_annual_attempt, editTextlast_insentive_date, editTextlocation, editTextarea_of_zone, editTextzonal_head, editTextzonal_head_contact_no, editTextdestination;
    String dod, annual_member, no_of_annual_attempt, last_insentive_date, location, area_of_zone, zonal_head, zonal_head_contact_no, destination;
    String path, person_name;
    ServiceHandler shh;
    ProgressDialog progressDialog;

    public DikshasInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_dikshas_info, container, false);

        final GlobalClass globalClass = (GlobalClass)getActivity().getApplicationContext();
        path=globalClass.getconstr();

        editTextdod=(EditText)view.findViewById(R.id.etdod);
        editTextannual_member=(EditText)view.findViewById(R.id.etannualmember);
        editTextno_of_annual_attempt=(EditText)view.findViewById(R.id.etattempt);
        editTextlast_insentive_date=(EditText)view.findViewById((R.id.etlastinsentivedate));
        editTextlocation=(EditText)view.findViewById(R.id.etlocation);
        editTextarea_of_zone=(EditText)view.findViewById(R.id.etareazone);
        editTextzonal_head=(EditText)view.findViewById(R.id.etzonalhead);
        editTextzonal_head_contact_no=(EditText)view.findViewById(R.id.etzonalheadno);
        editTextdestination=(EditText)view.findViewById(R.id.etdestination);

        Display();

        new GetPersonData().execute();

        return view;
    }

    public void Display()
    {

        Intent intent= getActivity().getIntent();
        Bundle bundle= intent.getExtras();
        if (bundle!=null)
        {
            person_name=(String)bundle.get("a1");
        }
    }

    public class GetPersonData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog= new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            shh = new ServiceHandler();

            String url = path + "InformationApi/SearchPersonalInfo";

            Log.d("Url: ", "> " + url);

            try {
                // Making a request to url and getting response

                List<NameValuePair> para = new ArrayList<>();
                para.add(new BasicNameValuePair("nameofperson", person_name));


                String jsonStr = shh.makeServiceCall(url, ServiceHandler.POST, para);
                if (jsonStr != null) {
                    JSONObject jObj = new JSONObject(jsonStr);
                    JSONArray jsonArray=jObj.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a1 = jsonArray.getJSONObject(i);

                        dod= a1.getString("dateofdiksha");
                        annual_member= a1.getString("anuualmember");
                        no_of_annual_attempt= a1.getString("noofattempt");
                        last_insentive_date= a1.getString("lastintensivedate");
                        location= a1.getString("intensivelocation");
                        area_of_zone= a1.getString("areofzone");
                        zonal_head= a1.getString("zonalhead");
                        zonal_head_contact_no= a1.getString("zonalheadcontact");
                        destination= a1.getString("designation");

                    }

                }

                else {
                    Toast.makeText(getActivity(), "Data not Found", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ServiceHandler", "Json Error ");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
            editTextdod.setText(dod);
            editTextannual_member.setText(annual_member);
            editTextno_of_annual_attempt.setText(no_of_annual_attempt);
            editTextlast_insentive_date.setText(last_insentive_date);
            editTextlocation.setText(location);
            editTextarea_of_zone.setText(area_of_zone);
            editTextzonal_head.setText(zonal_head);
            editTextzonal_head_contact_no.setText(zonal_head_contact_no);
            editTextdestination.setText(destination);

        }
    }

}
