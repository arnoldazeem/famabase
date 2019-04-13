package com.example.adabooazeem.swift;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class ForgotPassword_Fragment extends Fragment implements
        OnClickListener {
	private static View view;

	private static EditText emailId;
	private static Button submit, back;
    String getEmailId;
	ProgressBar pb;
    String getcode ;
    String getpassword;
    String getconfirmpassword;

	public ForgotPassword_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container,
				false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize the views
	private void initViews() {
		emailId = (EditText) view.findViewById(R.id.registered_emailid);
		submit = (Button) view.findViewById(R.id.forgot_button);
		back = (Button) view.findViewById(R.id.backToLoginBtn);

        pb = (ProgressBar) view.findViewById(R.id.loadingProgressBar);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			back.setTextColor(csl);
			submit.setTextColor(csl);

		} catch (Exception e) {
		}

	}

	// Set Listeners over buttons
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backToLoginBtn:
			// Replace Login Fragment on Back Presses
			new Authentication().replaceLoginFragment();
			break;

		case R.id.forgot_button:
			// Call Submit button task
			submitButtonTask();
			break;
		}
	}

	private void submitButtonTask() {
		 getEmailId = emailId.getText().toString();

		// Pattern for email id validation
		Pattern p = Pattern.compile(Utils.regEx);

		// Match the pattern
		Matcher m = p.matcher(getEmailId);

		// First check if email id is not null else show error toast
		if (getEmailId.equals("") || getEmailId.length() == 0){

			new CustomToast().Show_Toast(getActivity(), view,
					"Please enter your Email Id.");

        }
		// Check if email id is valid or not
		else if (!m.find()){
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

        }
        // Else submit email id and fetch passwod or do your stuff
		else{
		    //submit
		submitEmail();
    }
}


	//signup to take json format and return
	private void submitEmail() {

		//showProgressDialog();

        pb.setVisibility(ProgressBar.VISIBLE);

		Map<String, String> postParam= new HashMap<String, String>();
		postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
		postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");
        postParam.put(Utils.KEY_EMAIL, getEmailId);


		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
				Utils.REQUEST_URL, new JSONObject(postParam),
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						//msgResponse.setText(response.toString());

						 Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

						 //hideProgressDialog();
						pb.setVisibility(ProgressBar.INVISIBLE);


						//call the alert dialog
						showResetPasswordDialog();

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

	public void showResetPasswordDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = this.getLayoutInflater();
		final View dialogView = inflater.inflate(R.layout.resetpassword_layout, null);
		dialogBuilder.setView(dialogView);

		final EditText code = (EditText) dialogView.findViewById(R.id.input_code);
		final EditText password = (EditText) dialogView.findViewById(R.id.input_password);
		final EditText confirmpassword = (EditText) dialogView.findViewById(R.id.input_passwordconfirm);




		dialogBuilder.setTitle("Reset Password");
		dialogBuilder.setMessage("Enter text below");
		dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//do something with edt.getText().toString();

                getcode = code.getText().toString();
                getpassword = password.getText().toString();
                getconfirmpassword = confirmpassword.getText().toString();


                if (getcode.equals("") || getcode.length() == 0
                        || getconfirmpassword.equals("") || getconfirmpassword.length() == 0
                        || getpassword.equals("") || getpassword.length()== 0){

                    Toast.makeText(getActivity(), "All fields required.", Toast.LENGTH_SHORT)
                            .show();

                }else if (!getpassword.equals(getconfirmpassword)) {
                    Toast.makeText(getActivity(), "Mismatch of Paasword.", Toast.LENGTH_SHORT)
                            .show();
                }else
                   submitResetPassword();

			}
		});
		dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				//pass
			}
		});
		AlertDialog b = dialogBuilder.create();
		b.show();
	}


    //signup to take json format and return
    private void submitResetPassword() {

        //showProgressDialog();

        pb.setVisibility(ProgressBar.VISIBLE);

        String getencryptedpassword = MD5.encrypt(getpassword);

        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
        postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");
        postParam.put(Utils.KEY_EMAIL, getEmailId);
        postParam.put(Utils.KEY_CODE, getcode);
        postParam.put(Utils.KEY_PASSWORD, getencryptedpassword);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Utils.RESET_URL, new JSONObject(postParam),
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