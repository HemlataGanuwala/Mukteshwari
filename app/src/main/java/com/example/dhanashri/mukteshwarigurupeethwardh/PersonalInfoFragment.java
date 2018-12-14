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
import android.widget.ArrayAdapter;
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
public class PersonalInfoFragment extends Fragment {

    View view;
    EditText editTextname, editTextaddress, editTextcity, editTextdistrict, editTextstate, editTextcountry, editTextmobileno, editTextlindlineno, editTextemailid, editTextoccupation, editTexteducation, editTextdob, editTextage;
    String name, address, city, district, state, country, mobileno, landlineno, emailid, occupation, education, age, dob;
    String path, person_name;
    ServiceHandler shh;
    ProgressDialog progressDialog;


    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        final GlobalClass globalClass = (GlobalClass)getActivity().getApplicationContext();
        path=globalClass.getconstr();

        editTextname=(EditText)view.findViewById(R.id.etname);
        editTextaddress=(EditText)view.findViewById(R.id.etaddress);
        editTextcity=(EditText) view.findViewById(R.id.etcity);
        editTextdistrict=(EditText) view.findViewById(R.id.etdistrict);
        editTextstate=(EditText)view.findViewById(R.id.etstate);
        editTextcountry=(EditText)view.findViewById(R.id.etcountry);
        editTextmobileno=(EditText)view.findViewById(R.id.etmobileno);
        editTextlindlineno=(EditText)view.findViewById(R.id.etlandlin);
        editTextemailid=(EditText)view.findViewById(R.id.etemailid);
        editTextoccupation=(EditText)view.findViewById(R.id.etoccupation);
        editTexteducation=(EditText)view.findViewById(R.id.eteducation);
        editTextage=(EditText)view.findViewById(R.id.etage);
        editTextdob=(EditText)view.findViewById(R.id.etdob);

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

                        name= a1.getString("nameofperson");
                        address= a1.getString("addressofcorrespondance");
                        city= a1.getString("city");
                        district= a1.getString("districy");
                        state= a1.getString("state");
                        country= a1.getString("country");
                        mobileno= a1.getString("mobileno");
                        landlineno= a1.getString("landlineno");
                        emailid= a1.getString("emailid");
                        occupation= a1.getString("occupation");
                        education= a1.getString("education");
                        age= a1.getString("age");
                        dob= a1.getString("dateofbirth");



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
            editTextname.setText(name);
            editTextaddress.setText(address);
            editTextcity.setText(city);
            editTextdistrict.setText(district);
            editTextstate.setText(state);
            editTextcountry.setText(country);
            editTextmobileno.setText(mobileno);
            editTextlindlineno.setText(landlineno);
            editTextemailid.setText(emailid);
            editTextoccupation.setText(occupation);
            editTexteducation.setText(education);
            editTextage.setText(age);
            editTextdob.setText(dob);

        }
    }

}
