package com.example.dhanashri.mukteshwarigurupeethwardh;

        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.support.design.widget.TabLayout;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ListView;
        import android.widget.Toast;
        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONObject;
        import java.util.ArrayList;
        import java.util.List;

public class AnnualMemberActivity extends AppCompatActivity {

    ListView listView;
    List<AnnualMemberPlanet> planetList= new ArrayList<AnnualMemberPlanet>();
    String name, mobileno, city;
    AnnualMemberAdapter adapter;
    String path, person_name;
    ServiceHandler shh;
    ProgressDialog progressDialog;
    AnnualMemberPlanet planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_member);

        final GlobalClass globalClass=(GlobalClass)getApplicationContext();
        path= globalClass.getconstr();

        listView=(ListView)findViewById(R.id.listview);
        new GetPersonData().execute();

    }

    public class GetPersonData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog= new ProgressDialog(AnnualMemberActivity.this);
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

                List<NameValuePair> para = new ArrayList<>();
               // para.add(new BasicNameValuePair("nameofperson", person_name));


                String jsonStr = shh.makeServiceCall(url, ServiceHandler.GET, null);
                if (jsonStr != null) {
                    JSONObject jObj = new JSONObject(jsonStr);
                    JSONArray jsonArray=jObj.getJSONArray("Response");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a1 = jsonArray.getJSONObject(i);

                        name= a1.getString("nameofperson");
                        city= a1.getString("city");
                        mobileno= a1.getString("mobileno");


                        planet = new AnnualMemberPlanet(name,city,mobileno);
                        planetList.add(planet);
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
                    adapter=new AnnualMemberAdapter(AnnualMemberActivity.this, planetList);
                    listView.setAdapter(adapter);
                }
            });

        }
    }}



