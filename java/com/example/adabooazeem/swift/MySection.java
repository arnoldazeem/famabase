package com.example.adabooazeem.swift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MySection extends StatelessSection {

    String title;
    List<ZoneOrders> list;
    private Context mContext;

    public MySection(Context context, String title, List<ZoneOrders> list) {
        // call constructor with layout resources for this Section header and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.item_layout)
                .headerResourceId(R.layout.header_layout)
                .build());

        this.title = title;
        this.list = list;
        mContext = context;
    }


        @Override
        public int getContentItemsTotal() {
            return list.size();

        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }



    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        // bind your view here
        itemHolder.txtItem.setText(list.get(position).getVendor());
       //  itemHolder.txtItem.setText(land);
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.txtHeader.setText(title);
        headerViewHolder.imgHeader.setImageResource(R.drawable.zone);

        headerViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, String.format("Clicked on more button from the header of Section %s",
                        title),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }


        protected class HeaderViewHolder extends RecyclerView.ViewHolder {

            protected TextView txtHeader;
            protected ImageView imgHeader;
            LinearLayout parentLayout;

            private HeaderViewHolder(View itemView) {
                super(itemView);
                txtHeader = (TextView) itemView.findViewById(R.id.header_id);
                imgHeader = (ImageView) itemView.findViewById(R.id.imageView);
                parentLayout = itemView.findViewById(R.id.layou);
            }
        }



        protected class ItemViewHolder extends RecyclerView.ViewHolder {

            protected TextView txtItem;

            private ItemViewHolder(View itemView) {
                super(itemView);
                txtItem = (TextView) itemView.findViewById(R.id.item_content);
            }
        }
    }