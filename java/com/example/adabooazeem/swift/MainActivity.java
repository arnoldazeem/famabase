package com.example.adabooazeem.swift;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.md5simply.MD5;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static EditText clientname;
    protected static EditText alternativename;

    protected static EditText locationpickup;
    protected static EditText locationdelivery;

    protected static TextView deliverydate;

    protected static Button request;

    String value;

    String spinner_value;

    String getclientname;
    String getalternativename;
    String getlocationpickup;
    String getlocationdelivery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        clientname = (EditText) findViewById(R.id.client_name);
        alternativename = (EditText) findViewById(R.id.alternative_name);
        locationpickup = (EditText) findViewById(R.id.location_pickup);
        locationdelivery = (EditText) findViewById(R.id.location_delivery);

        deliverydate = (TextView) findViewById(R.id.delivery_date);



        request = (Button) findViewById(R.id.send_request);

        String[] PICKSPINNERLIST = {"TUESDAY", "FRIDAY"};

        spinner_value = "";

        //final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());

       // value =(mSharedPreference.getString(Utils.EMAIL_SHARED_PREF , "defaultValue"));

        Toast.makeText(getBaseContext(), "" + value, Toast.LENGTH_LONG).show();




        request.setOnClickListener(this);
       // pickmonth.setOnClickListener(this);

        //setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //                 .setAction("Action", null).show();
        //    }
       // });


        //MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinnerpick);
        //spinner.setItems("Pickup Time","30mins", "1hour", "2hours");
        //spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

        //    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
       //         Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
        //    }
       // });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PICKSPINNERLIST);

        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);


        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                spinner_value = adapterView.getItemAtPosition(position).toString();


                if (spinner_value.equals("TUESDAY")) {

                    deliverydate.setText("WEDNESDAY", TextView.BufferType.EDITABLE);

                } else {

                    deliverydate.setText("SUNDAY", TextView.BufferType.EDITABLE);

                }

            }
        });


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
        if (id == R.id.action) {
            return true;
        }

        if (id == R.id.close) {

            logout();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Utils.SHARED_PREF_NAME, getApplication().MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Utils.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Utils.PHONE_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainActivity.this, Authentication.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
         AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.send_request:
                // it was the first button
              //  TimePicker mTimePicker = new TimePicker();
              //  mTimePicker.show(getFragmentManager(), "Select time");
                checkValidation();
                break;


        }

    }





    private void confirmAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setTitle("Confirm Request !");
     builder.setMessage("You are about to delete all records of database. Do you really want to proceed ?");
     builder.setCancelable(false);
     builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(getApplicationContext(), "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
                signup();
            }
        });

     builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
            }
        });

     builder.show();
     }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        getclientname = clientname.getText().toString();
        getalternativename = alternativename.getText().toString();
        getlocationpickup = locationpickup.getText().toString();
        getlocationdelivery = locationdelivery.getText().toString();


        // Check if all strings are null or not
        if (getclientname.equals("") || getclientname.length() == 0
                || getalternativename.equals("") || getalternativename.length() == 0
                || getlocationpickup.equals("") || getlocationpickup.length() == 0
                || getlocationdelivery.equals("") || getlocationdelivery.length() == 0)


        Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT)
                .show();


        else if (spinner_value.equals("")|| spinner_value.length() == 0)


            Toast.makeText(this, "Please select pickup day", Toast.LENGTH_SHORT)
                    .show();
        else {

            try {


                confirmAlert();

            }catch (Exception E){
                E.printStackTrace();
            }

        }

    }



    private void signup(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server

                        if(response.equalsIgnoreCase(Utils.signup_SUCCESS)){
                            //Creating a shared preference
                            //Starting profile activity
                            //
                            Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();

                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(getBaseContext(), "" + error, Toast.LENGTH_LONG).show();

                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request

                params.put(Utils.Delivery_clientname, getclientname);
                params.put(Utils.Delivery_alternativename, getalternativename);
                params.put(Utils.Delivery_locationpickup, getlocationpickup);
                params.put(Utils.Delivery_locationdelivery, getlocationdelivery);
                params.put(Utils.Delivery_spinner_value, spinner_value);

                params.put(Utils.KEY_EMAIL, value);
                //params.put(Utils.Delivery_spinner_value, businessname);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
