package com.example.dhanashri.mukteshwarigurupeethwardh;

        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Filter;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;
        import org.apache.http.NameValuePair;
        import org.json.JSONArray;
        import org.json.JSONObject;
        import java.util.ArrayList;
        import java.util.List;

public class AnnualMemberActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listView;
    SpinnerCityPlanet cityPlanet;
    ArrayList<SpinnerCityPlanet> citylist = new ArrayList<SpinnerCityPlanet>();
    CityAdapter cityAdapter;
    List<AnnualMemberPlanet> planetlist = new ArrayList<AnnualMemberPlanet>();
    Spinner citySpinner;
    String name, mobileno, city, spincity;
    AnnualMemberAdapter adapter;
    String path;
    ServiceHandler shh;
    ProgressDialog progressDialog;
    AnnualMemberPlanet planet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_member);

        final GlobalClass globalClass = (GlobalClass) getApplicationContext();
        path = globalClass.getconstr();

        listView = (ListView) findViewById(R.id.listview);
        citySpinner= (Spinner) findViewById(R.id.spinnercity);

        new GetPersonData().execute();
        new GetCityData().execute();

        citySpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


         spincity = cityAdapter.getItem(position).getCity_name();
            //planetlist.clear();
//Here we use the Filtering Feature which we implemented in our Adapter class.
        adapter.getFilter().filter((spincity),new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int count) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class GetPersonData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(AnnualMemberActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
           // progressDialog.setCancelable(false);
            //progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            shh = new ServiceHandler();

            String url = path + "InformationApi/RegInfo";

            Log.d("Url: ", "> " + url);

            try {
                // Making a request to url and getting response

                List<NameValuePair> para = new ArrayList<>();
                // para.add(new BasicNameValuePair("nameofperson", person_name));


                String jsonStr = shh.makeServiceCall(url, ServiceHandler.GET, null);
                if (jsonStr != null) {
                    JSONObject jObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jObj.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a1 = jsonArray.getJSONObject(i);

                        name = a1.getString("Name");
                        mobileno = a1.getString("MobileNo");
                        city = a1.getString("City");

                        planet = new AnnualMemberPlanet(name,mobileno,city);
                        planetlist.add(planet);
                    }


                } else {
                    Toast.makeText(AnnualMemberActivity.this, "Data not Found", Toast.LENGTH_LONG).show();
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    adapter=new AnnualMemberAdapter(AnnualMemberActivity.this, planetlist);
                    listView.setAdapter(adapter);
                }
            });


        }
    }

    public class GetCityData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(AnnualMemberActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
           // progressDialog.setCancelable(false);
           // progressDialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            shh = new ServiceHandler();

            String url = path + "InformationApi/RegInfo";

            Log.d("Url: ", "> " + url);

            try {
                // Making a request to url and getting response

                List<NameValuePair> para = new ArrayList<>();
                // para.add(new BasicNameValuePair("nameofperson", person_name));


                String jsonStr = shh.makeServiceCall(url, ServiceHandler.GET, null);
                if (jsonStr != null) {
                    JSONObject jObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jObj.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a1 = jsonArray.getJSONObject(i);

                        cityPlanet = new SpinnerCityPlanet(a1.getString("City"));
                        citylist.add(cityPlanet);
                    }


                } else {
                    Toast.makeText(AnnualMemberActivity.this, "Data not Found", Toast.LENGTH_LONG).show();
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

            // Creating adapter for spinner
            cityAdapter = new CityAdapter(AnnualMemberActivity.this, android.R.layout.simple_spinner_item, citylist);

            // Drop down layout style - list view with radio button
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            citySpinner.setAdapter(cityAdapter);


        }
    }


}







