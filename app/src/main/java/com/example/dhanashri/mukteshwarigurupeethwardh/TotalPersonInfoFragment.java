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
public class TotalPersonInfoFragment extends Fragment {

    View view;
    EditText editTexttotal_family_member, editTexttotal_sadhak, editTexttotal_mahashivratri_expected, editTexttotal_mahashivratri_taken, editTexttotal_gurupornima_expected, editTexttotal_gurupormina_taken, editTexttotal_navratri_expected, editTexttotal_naratri_taken, editTexttotal_local_expected, editTexttotal_local_taken, editText_status;
    String total_family_member, total_sadhak, total_mahashivratri_expected, total_mahashivratri_taken, total_gurupornima_expected, total_gurupormina_taken, total_navratri_expected, total_naratri_taken, total_local_expected, total_local_taken, status;
    String path, person_name;
    ServiceHandler shh;
    ProgressDialog progressDialog;

    public TotalPersonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_total_person_info, container, false);

        final GlobalClass globalClass = (GlobalClass)getActivity().getApplicationContext();
        path=globalClass.getconstr();

        editTexttotal_family_member=(EditText)view.findViewById(R.id.ettotalfamilymember);
        editTexttotal_sadhak=(EditText)view.findViewById(R.id.ettotalsadhak);
        editTexttotal_mahashivratri_expected=(EditText)view.findViewById(R.id.etmahashivratriexpecte);
        editTexttotal_mahashivratri_taken=(EditText)view.findViewById(R.id.etmahashivtatritaken);
        editTexttotal_gurupornima_expected=(EditText)view.findViewById(R.id.etgurupornimaexpected);
        editTexttotal_gurupormina_taken=(EditText)view.findViewById(R.id.etgurupornimataken);
        editTexttotal_navratri_expected=(EditText)view.findViewById(R.id.etnavratriexpect);
        editTexttotal_naratri_taken=(EditText)view.findViewById(R.id.etnavratritaken);
        editTexttotal_local_expected=(EditText)view.findViewById(R.id.etlocalexpected);
        editTexttotal_local_taken=(EditText)view.findViewById(R.id.etlocaltaken);
        editText_status=(EditText)view.findViewById(R.id.etstatus);

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

                        total_family_member= a1.getString("totalfamilymembers");
                        total_sadhak= a1.getString("totalsadhak");
                        total_mahashivratri_expected= a1.getString("mahashivratritotal");
                        total_mahashivratri_taken= a1.getString("mahashivratriatmpt");
                        total_gurupornima_expected= a1.getString("gurupurnimatotal");
                        total_gurupormina_taken= a1.getString("gurupurnimaatmpt");
                        total_navratri_expected= a1.getString("navtratritotal");
                        total_naratri_taken= a1.getString("navtratriatmpt");
                        total_local_expected= a1.getString("totalattempts");
                        status= a1.getString("status");



                    }

                } else {
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
            editTexttotal_family_member.setText(total_family_member);
            editTexttotal_sadhak.setText(total_sadhak);
            editTexttotal_mahashivratri_expected.setText(total_mahashivratri_expected);
            editTexttotal_mahashivratri_taken.setText(total_mahashivratri_taken);
            editTexttotal_gurupornima_expected.setText(total_gurupornima_expected);
            editTexttotal_gurupormina_taken.setText(total_gurupormina_taken);
            editTexttotal_navratri_expected.setText(total_navratri_expected);
            editTexttotal_naratri_taken.setText(total_naratri_taken);
            editTexttotal_local_expected.setText(total_local_expected);
            editText_status.setText(status);


        }
    }

}
