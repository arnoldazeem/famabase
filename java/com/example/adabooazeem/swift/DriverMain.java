package com.example.adabooazeem.swift;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class DriverMain extends AppCompatActivity {


        private static final String TAG = "MainActivity";

        RequestQueue requestQueue;
        static final String REQ_TAG = "VACTIVITY";
        //vars
        private ArrayList<String> mNames = new ArrayList<>();
        private ArrayList<String> mImageUrls = new ArrayList<>();
        private ArrayList<String> zones = new ArrayList<>();
        private ArrayList<String> mOrderId = new ArrayList<>();
        String gotten;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.deliverymain);
            Log.d(TAG, "onCreate: started.");

            SharedPreferences prefs = getSharedPreferences(Utils.SHARED_PREF_NAME, MODE_PRIVATE);

            //Adding values to editor
            Boolean keep =  prefs.getBoolean(Utils.LOGGEDIN_SHARED_PREF, false);
            gotten = prefs.getString(Utils.PHONE_SHARED_PREF, null);
            String name = prefs.getString(Utils.NAME_SHARED_PREF, null);

            Intent intent = getIntent();
            String zone_name = intent.getStringExtra("zone_name");

            //Toast.makeText(DriverMain.this, gotten, Toast.LENGTH_LONG).show();

            loadOrders(zone_name);
        }


        /*private void initRecyclerView(){
            Log.d(TAG, "initRecyclerView: init recyclerview.");
            RecyclerView recyclerView = findViewById(R.id.recycler);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mOrderId);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }*/


    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(REQ_TAG);
        }
    }


    //signup to take json format and return
    private void loadOrders(final String zone) {

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
                        //msgResponse.setText(response.toString());
                        //msgResponse.setText(response.toString());
                        JSONObject arr = null;
                        JSONArray arn = null;

                        try {

                            if(response.getString("StatusCode").equalsIgnoreCase("200")){
                                String res = response.getString("Data");
                                JSONObject obj2 = new JSONObject(res);
                                JSONArray jarray1 = obj2.getJSONArray("data");
                                //loop through the array to get other arrays
                                //first one is clustered zones
                              //  for (int i = 0; i < jarray1.length(); i++) {
                                    //json array should have a name
                                    //content of each zone
                                    int zonnne = Integer.parseInt(zone);
                                    JSONArray jarray2 = jarray1.getJSONArray(zonnne);

                                    // arrange them in zones
                                  for (int e = 0; e < jarray2.length(); e++) {
                                        //loop through the array to get each jsonobject
                                       JSONObject jsonObject1 = jarray2.getJSONObject(e);
                                       //get the information and keep
                                       mOrderId.add(jsonObject1.getString("order-id"));
                                       mImageUrls.add(jsonObject1.getString("imgae"));
                                       mNames.add(jsonObject1.getString("vendor"));

                                       //Toast.makeText(DriverMain.this,  jsonObject1.getString("order-id"), Toast.LENGTH_LONG).show();
                                   }
                              //  }

                              //  initRecyclerView();

                            }else{

                                Toast.makeText(DriverMain.this, "Network Error", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                           // Toast.makeText(DriverMain.this, e.toString(), Toast.LENGTH_LONG).show();

                            Log.e(TAG, e.toString());
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

                    Toast.makeText(DriverMain.this, error.toString(), Toast.LENGTH_LONG).show();

                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(DriverMain.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());

                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(DriverMain.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(DriverMain.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(DriverMain.this, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());

                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);
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


        RequestQueue queue = Volley.newRequestQueue(DriverMain.this);

        jsonObjReq.setTag(TAG);
        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */
    }


}
