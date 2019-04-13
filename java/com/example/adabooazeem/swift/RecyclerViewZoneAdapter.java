package com.example.adabooazeem.swift;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewZoneAdapter extends RecyclerView.Adapter<RecyclerViewZoneAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> OrderNumber = new ArrayList<>();
    private ArrayList<String> companyName = new ArrayList<>();

    private ArrayList<String> primaryPerson = new ArrayList<>();
    private ArrayList<String> primaryPhone = new ArrayList<>();

    //private ArrayList<String> secondaryPerson = new ArrayList<>();
    //private ArrayList<String> secondaryPhone = new ArrayList<>();

    private ArrayList<String> datePickup = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();

    private ArrayList<String> businesslocation = new ArrayList<>();

    private ArrayList<String> landMark = new ArrayList<>();

    private ArrayList<String> mImageNames = new ArrayList<>();

    private Context mContext;

    public RecyclerViewZoneAdapter(Context context, ArrayList<String> imageNames) {
        mImageNames =  imageNames;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deliveryitemzone, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        holder.zoname.setText(mImageNames.get(position));
        //onclick of the view button
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DriverMain.class);
                intent.putExtra("zone_name", mImageNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView zoname;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            zoname = itemView.findViewById(R.id.pickup_zone);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

