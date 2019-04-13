package com.example.adabooazeem.swift;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mOrderId = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images,ArrayList<String> iorder) {
        mImageNames = imageNames;
        mImages = images;
        mOrderId = iorder;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item_recycle, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));
        holder.imageZone.setText(mOrderId.get(position));

        final String order = mOrderId.get(position);

        //onclick of the view button
        holder.acceptName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        Toast.makeText(mContext, "accept to deliver", Toast.LENGTH_SHORT).show();

        AcceptOrders(order);

       // Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
       // Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
       // Intent intent = new Intent(mContext, GalleryActivity.class);
       // intent.putExtra("image_url", mImages.get(position));
       // intent.putExtra("image_name", mImageNames.get(position));
       // mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        TextView imageZone;
        Button acceptName;
        Button viewName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            imageZone = itemView.findViewById(R.id.image_zone);
            acceptName = itemView.findViewById(R.id.accept_name);
            viewName = itemView.findViewById(R.id.view_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }



    //signup to take json format and return
    private void AcceptOrders(String ordd) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        SharedPreferences prefs = mContext.getSharedPreferences(Utils.SHARED_PREF_NAME, mContext.MODE_PRIVATE);
        //Adding values to editor
        //Boolean keep =  prefs.getBoolean(Utils.LOGGEDIN_SHARED_PREF, false);
        String gotten = prefs.getString(Utils.PHONE_SHARED_PREF, null);

        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put(Utils.KEY_APPID, "j8Ue72@lfZy");
        postParam.put(Utils.KEY_APIKEY, "f9da8764b6d8c3413e5503d6bfe91e63");
        postParam.put(Utils.KEY_MOBILE, gotten);
        postParam.put(Utils.KEY_ORDERID, ordd);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Utils.ACCEPT_ORDERS_URL, new JSONObject(postParam),

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //msgResponse.setText(response.toString());
                        //msgResponse.setText(response.toString());
                        try {

                            if(response.getString("StatusCode").equalsIgnoreCase("416")){
                                //content of each zone
                                Toast.makeText(mContext,  "Already Accepted", Toast.LENGTH_LONG).show();
                            }else{

                                Toast.makeText(mContext, "Order Accepted", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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

                    //Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();

                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof AuthFailureError) {
                    //TODO
                    Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());

                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();

                    Log.e("Volly Error", error.toString());
                    // run a background job and once complete
                    //pb.setVisibility(ProgressBar.INVISIBLE);

                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();

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

        RequestQueue queue = Volley.newRequestQueue(mContext);
        jsonObjReq.setTag(TAG);
        // Adding request to request queue
        queue.add(jsonObjReq);

        // Cancelling request
    /* if (queue!= null) {
    queue.cancelAll(TAG);
    } */
    }
}

