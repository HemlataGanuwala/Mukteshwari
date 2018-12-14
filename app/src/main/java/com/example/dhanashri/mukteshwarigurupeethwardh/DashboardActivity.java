package com.example.dhanashri.mukteshwarigurupeethwardh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    Button search;
    Spinner spinner;
    String path, name;
    ServiceHandler shh;
    SpinnerPlanet planet;
    ArrayList<SpinnerPlanet> namelist = new ArrayList<SpinnerPlanet>();
    ArrayAdapter<String> spinnerAdapter;
    ProgressDialog progressDialog;
    CardView cardViewprogram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final GlobalClass globalClass= (GlobalClass)getApplicationContext();
        path=globalClass.getconstr();

        cardViewprogram=(CardView) findViewById(R.id.cvpragramschedule);
        search=(Button) findViewById(R.id.btnsearch);
        spinner=(Spinner) findViewById(R.id.spinner);


        cardViewprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DashboardActivity.this,ProgramScheduleActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, PersonalInfoActivity.class);
                intent.putExtra("a1",name);
                startActivity(intent);

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                name= namelist.get(position).getPerson_name();
//                name= spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new GetPersonData().execute();
    }


    public class GetPersonData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog= new ProgressDialog(DashboardActivity.this);
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

            String url = path + "InformationApi/PersonalInfo";

            Log.d("Url: ", "> " + url);

            try {
                // Making a request to url and getting response

//                List<NameValuePair> para = new ArrayList<>();
                // para.add(new BasicNameValuePair("CustBal", balance));


                String jsonStr = shh.makeServiceCall(url, ServiceHandler.GET, null);
                if (jsonStr != null) {
                    JSONObject jObj = new JSONObject(jsonStr);
                    JSONArray jsonArray=jObj.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a1 = jsonArray.getJSONObject(i);

                        planet = new SpinnerPlanet(a1.getString("nameofperson"));
                        namelist.add(planet);

                    }

                } else {
                    Toast.makeText(DashboardActivity.this, "Data not Found", Toast.LENGTH_LONG).show();
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
            List<String> lables = new ArrayList<String>();

            for (int i = 0; i < namelist.size(); i++) {
                lables.add(namelist.get(i).getPerson_name());
            }

            // Creating adapter for spinner
            spinnerAdapter = new ArrayAdapter<String>(DashboardActivity.this,
                    android.R.layout.simple_spinner_item, lables);

            // Drop down layout style - list view with radio button
            spinnerAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(spinnerAdapter);


        }
    }
}
