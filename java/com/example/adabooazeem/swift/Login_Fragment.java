package com.example.adabooazeem.swift;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kosalgeek.android.md5simply.MD5;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;
    private boolean loggedIn = false;
    String getEmailId,getPassword;
    String getencryptedpassword;
	ProgressBar pb;

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initiate Views
	private void initViews() {

		fragmentManager = getActivity().getSupportFragmentManager();
        //session = new SessionManager(getActivity().getApplicationContext());

		emailid = (EditText) view.findViewById(R.id.input_email);
		password = (EditText) view.findViewById(R.id.input_password);

		loginButton = (Button) view.findViewById(R.id.loginBtn);
		forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
		signUp = (TextView) view.findViewById(R.id.link_signup);
		show_hide_password = (CheckBox) view
				.findViewById(R.id.show_hide_password);

		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

        // progressDialog = new DelayedProgressDialog();
		pb = (ProgressBar) view.findViewById(R.id.loadingProgressBar);

	}



	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);

		// Set check listener over checkbox for showing and hiding password
		show_hide_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
							boolean isChecked) {

						// If it is checkec then show password else hide
						// password
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);// change
																			// checkbox// text
							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());// show password
						} else {
							show_hide_password.setText(R.string.show_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());// hide password

						}

					}
				});
	}


    @Override
    public void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Utils.SHARED_PREF_NAME, getActivity().MODE_PRIVATE);

        sharedPreferences.getString(Utils.PHONE_SHARED_PREF, null);
        sharedPreferences.getString(Utils.NAME_SHARED_PREF, null);
        //editor.commit(); // commit changes

		//Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Utils.LOGGEDIN_SHARED_PREF, false);


        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(getActivity(), DriverZones.class);
            startActivity(intent);
        }
    }



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			checkValidation();
			break;

		case R.id.forgot_password:

			// Replace forgot password fragment with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer,
							new ForgotPassword_Fragment(),
							Utils.ForgotPassword_Fragment).commit();
			break;

		case R.id.link_signup:
			// Replace signup frgament with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new SignUp_Fragment(),
							Utils.SignUp_Fragment).commit();
			break;
		}

	}



	// Check Validation before login
	private void checkValidation() {
		// Get email id and password
         getEmailId = emailid.getText().toString();
		 getPassword = password.getText().toString();

		// Check patter for email id
		//Pattern p = Pattern.compile(Utils.regEx);

		//Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view,
					"Enter both credentials.");

		}else{

			//Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
			//		.show();
            try {

				login();

            }catch (Exception E){
                E.printStackTrace();
            }


        }

	}


	//signup to take json format and return
	private void login() {

		//showProgressDialog();

		pb.setVisibility(ProgressBar.VISIBLE);
		Map<String, String> postParam= new HashMap<String, String>();
		getencryptedpassword = MD5.encrypt(getPassword);

		postParam.put(Utils.KEY_MOBILE, getEmailId);
		postParam.put(Utils.KEY_PASSWORD, getencryptedpassword);
		postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
		postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");

		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
				Utils.LOGIN_URL, new JSONObject(postParam),
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						//msgResponse.setText(response.toString());
                        JSONObject arr = null;
                        JSONArray arn = null;

                        try {

                            if(response.getString("StatusCode").equalsIgnoreCase("200")){

								String res =  response.getString("Data");

								JSONObject obj2 = new JSONObject(res);

								JSONArray jarray1 = obj2.getJSONArray("data");

                                arr = jarray1.getJSONObject(0);

                                String su = arr.getString("name");

                               SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Utils.SHARED_PREF_NAME, getActivity().MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(Utils.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Utils.PHONE_SHARED_PREF, getEmailId);
                                editor.putString(Utils.NAME_SHARED_PREF, su);

                                //Saving values to editor
                                editor.commit();

                                //Starting profile activity
                                Intent intent = new Intent(getActivity(), DriverZones.class);
                                startActivity(intent);

                            }else{

                                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                        }

						//hideProgressDialog();
						pb.setVisibility(ProgressBar.INVISIBLE);
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

					Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

					// run a background job and once complete
					pb.setVisibility(ProgressBar.INVISIBLE);

				} else if (error instanceof AuthFailureError) {
					//TODO
					Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

					Log.e("Volly Error", error.toString());

					// run a background job and once complete
					pb.setVisibility(ProgressBar.INVISIBLE);

				} else if (error instanceof ServerError) {
					//TODO
					Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

					Log.e("Volly Error", error.toString());
					// run a background job and once complete
					pb.setVisibility(ProgressBar.INVISIBLE);

				} else if (error instanceof NetworkError) {
					//TODO
					Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

					Log.e("Volly Error", error.toString());
					// run a background job and once complete
					pb.setVisibility(ProgressBar.INVISIBLE);

				} else if (error instanceof ParseError) {
					//TODO
					Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

					Log.e("Volly Error", error.toString());

					// run a background job and once complete
					pb.setVisibility(ProgressBar.INVISIBLE);
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


		RequestQueue queue = Volley.newRequestQueue(getActivity());
		jsonObjReq.setTag(TAG);
		// Adding request to request queue
		queue.add(jsonObjReq);
		// Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */
	}

}
