package com.example.adabooazeem.swift;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.md5simply.MD5;


import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText name, email, location,
            phone,password, confirmPassword ;
	private static TextView login;
	private static Button signUpButton;
    RequestQueue requestQueue;
    String finals;
    String getFullName;
    String getBusinessName;
    String getEmailId;
    String getMobileNumber;
    String getsocialmedia;
    String getLocation;
    String getPassword;
    String getConfirmPassword;
	String getencryptedpassword;
	ProgressBar pb;
    private static FragmentManager fragmentManager;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.register, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
		name = (EditText) view.findViewById(R.id.fullName);
		email = (EditText) view.findViewById(R.id.userEmailId);
		location = (EditText) view.findViewById(R.id.busilocation);
		phone = (EditText) view.findViewById(R.id.mobileNumber);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		 pb = (ProgressBar) view.findViewById(R.id.loadingProgressBar);

	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:
			// Call checkValidation method
			checkValidation();
			break;

		case R.id.already_user:

            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    .replace(R.id.frameContainer, new Login_Fragment(),
                            Utils.Login_Fragment).commit();

			new Authentication().replaceLoginFragment();

			break;
		}

	}

	// Check Validation Method
	private void checkValidation() {
		// Get all edittext texts
		getFullName = name.getText().toString();
		getEmailId = email.getText().toString();
		getMobileNumber = phone.getText().toString();
		getLocation = location.getText().toString();
		getPassword = password.getText().toString();
		getConfirmPassword = confirmPassword.getText().toString();
		getencryptedpassword = MD5.encrypt(getPassword);

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getLocation.equals("") || getLocation.length()== 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")|| getConfirmPassword.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"All fields are required.");

		// Check if email id valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			new CustomToast().Show_Toast(getActivity(), view,
					"Both password doesn't match.");

		// Else do signup or do your stuff
		else {
			Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
					.show();

            try {

               //signup();
               makeJsonObjReq();

            }catch (Exception E){
               E.printStackTrace();
            }

		}

	}


	//signup to take json format and return
	private void makeJsonObjReq() {

		//showProgressDialog();

		pb.setVisibility(ProgressBar.VISIBLE);

		Map<String, String> postParam= new HashMap<String, String>();
        postParam.put(Utils.KEY_FULLNAME, getFullName);
        postParam.put(Utils.KEY_EMAIL, getEmailId);
        postParam.put(Utils.KEY_PASSWORD, getencryptedpassword);
        postParam.put(Utils.KEY_MOBILE, getMobileNumber);
        postParam.put(Utils.KEY_LOCATION, getLocation);
        postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
        postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
				Utils.SIGN_URL, new JSONObject(postParam),
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						//msgResponse.setText(response.toString());


                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

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