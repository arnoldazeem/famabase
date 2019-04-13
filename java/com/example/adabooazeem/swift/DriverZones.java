package com.example.adabooazeem.swift;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DriverZones extends AppCompatActivity {

    private static final String TAG = "DriverZones";
    RequestQueue requestQueue;
    static final String REQ_TAG = "VACTIVITY";

    private ArrayList<String> OrderNumber = new ArrayList<>();
    private ArrayList<String> companyName = new ArrayList<>();

    private ArrayList<String> primaryPerson = new ArrayList<>();
    private ArrayList<String> primaryPhone = new ArrayList<>();

    private ArrayList<String> secondaryPerson = new ArrayList<>();
    private ArrayList<String> secondaryPhone = new ArrayList<>();

    private ArrayList<String> datePickup = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();

    private ArrayList<String> businesslocation = new ArrayList<>();

    private ArrayList<String> landMark = new ArrayList<>();
    private ArrayList<List> pikupZone = new ArrayList<>();
    ArrayList<String> listorder;
    String gotten;
    private Toolbar mTopToolbar;
    private final static String JSON_FILE_ANDROID_WEAR = "file.json";
    Map<String, Map<String, List<ZoneOrders>>> dictionaryfinal;
    Map<String, List<ZoneOrders>> dictionaryOrders;

    ArrayList<String> mImageUrls;
    RecyclerViewZoneAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliverymain);
        Log.d(TAG, "onCreate: started.");

        setTitle(null);

        mTopToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        //topToolBar.setLogo(R.drawable.logo);
        //topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));


        SharedPreferences prefs = getSharedPreferences(Utils.SHARED_PREF_NAME, MODE_PRIVATE);
        //Adding values to editor
        Boolean keep =  prefs.getBoolean(Utils.LOGGEDIN_SHARED_PREF, false);
        gotten = prefs.getString(Utils.PHONE_SHARED_PREF, null);
        String name = prefs.getString(Utils.NAME_SHARED_PREF, null);

        Toast.makeText(DriverZones.this, "Welcome" + " " + name, Toast.LENGTH_LONG).show();

        loadOrders();
    }


    //this onclick fills the zones
    public void onclickzone(ArrayList list){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinnerzone);
        //spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        spinner.setItems(list);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                Map<String, List<ZoneOrders>> ordinaryold  = new LinkedHashMap<>();

                ordinaryold = dictionaryfinal.get(item);
                ArrayList<String> landzone=new ArrayList<String>();
                landzone.addAll(ordinaryold.keySet());
                onclickarea(landzone);
              }
        });


        return ;
    }

    public void onclickarea(ArrayList list){
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinnerarea);
        spinner.setItems(list);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                List<ZoneOrders> ordinaryfinal  = new ArrayList<ZoneOrders>();

                ordinaryfinal = dictionaryOrders.get(item);

                Toast.makeText(DriverZones.this, String.valueOf(ordinaryfinal.size()), Toast.LENGTH_LONG).show();


                mImageUrls = new ArrayList<>();
                mImageUrls.clear();


                for (ZoneOrders value : ordinaryfinal)
                {
                    Log.i("Value of element ", value.getVendor());

                    mImageUrls.add(value.getVendor());

                }

                initRecyclerView();

            }
        });
    }


    private void initRecyclerView(){
            Log.d(TAG, "initRecyclerView: init recyclerview.");
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            adapter = new RecyclerViewZoneAdapter(this, mImageUrls);
            adapter.notifyDataSetChanged();
            recyclerView.invalidate();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

   /*//https://github.com/luizgrp/SectionedRecyclerViewAdapter
    private void initRecyclerView(){

        // Create an instance of SectionedRecyclerViewAdapter
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        // Create your sections with the list of data from your HashMap
        for(Map.Entry<String, List<ZoneOrders>> entry : dictionaryOrders.entrySet()) {

            MySection section = new MySection(this, entry.getKey(), entry.getValue());
            // add your section to the adapter
            sectionAdapter.addSection(section);
        }

        // Set up your RecyclerView with the SectionedRecyclerViewAdapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(DriverZones.this));
        recyclerView.setAdapter(sectionAdapter);

    }*/



    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(REQ_TAG);
        }
    }


    //signup to take json format and return
    private void loadOrders() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
        postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Utils.GET_ORDERS_URL, new JSONObject(postParam),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        //dictionaryfinal = new HashMap<String, Map<String, List<ZoneOrders>>>() ;


                            Gson gson = new Gson();

                            Orders orders = gson.fromJson(response.toString(), Orders.class);

                            String status = Integer.toString(orders.getStatusCode());

                            if(status.equalsIgnoreCase("200")){

                                List<Datum> datums = new ArrayList<Datum>();
                                datums = orders.getData();

                                //Creating arraylist
                                ArrayList<String> listzone=new ArrayList<String>();

                                dictionaryfinal = new LinkedHashMap<>();
                                dictionaryOrders = new LinkedHashMap<>();

                                for (Datum datum : datums) {
                                      //loop again in data to get those in zones
                                      List<ZoneOrders> zoneorder = new ArrayList<ZoneOrders>();
                                      String  zonetype;
                                      String  zoneland;

                                      zonetype = datum.getZoneType();
                                      zoneland = datum.getpickupZoneName();
                                      zoneorder = datum.getZoneOrders();

                                      if (zoneorder.isEmpty()){

                                      }else{

                                          dictionaryOrders.computeIfAbsent(zoneland, k -> new ArrayList<ZoneOrders>())
                                                  .addAll(zoneorder);


                                          dictionaryfinal.computeIfAbsent(zonetype, k -> new HashMap<>()).put(zoneland, zoneorder);

                                      }
                                  }

                                listzone.addAll(dictionaryfinal.keySet());
                                onclickzone(listzone);

                           }else{
                               Toast.makeText(DriverZones.this, "Network Error", Toast.LENGTH_LONG).show();
                            }

                        progressDialog.dismiss();
                        //progressDialog.hide();
                    }
                }, new Response.ErrorListener() {

            //@Override
            //public void onErrorResponse(VolleyError error) {
            //	VolleyLog.d(TAG, "Error: " + error.getMessage());
            //hideProgressDialog();
            //}

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Log.e("Volly Error", error.toString());

                    Toast.makeText(DriverZones.this, error.toString(), Toast.LENGTH_LONG).show();
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);
                    progressDialog.dismiss();

                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(DriverZones.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);
                    progressDialog.dismiss();
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(DriverZones.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);
                    progressDialog.dismiss();

                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(DriverZones.this, error.toString(), Toast.LENGTH_LONG).show();
                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);
                    progressDialog.dismiss();

                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(DriverZones.this, error.toString(), Toast.LENGTH_LONG).show();
                    Log.e("Volly Error", error.toString());
                    progressDialog.dismiss();

                }
            }

        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };


        RequestQueue queue = Volley.newRequestQueue(DriverZones.this);
        jsonObjReq.setTag(TAG);
        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.close){
            Toast.makeText(DriverZones.this, "Refresh App", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
