package com.example.adabooazeem.swift;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adabooazeem.swift.Interface.ILoadMore;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row

    public ProgressBar progressBar;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public LoadingViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        progressBar = (ProgressBar) itemView.findViewById(R.id.progress);

    }
}


class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView nameTextView;
    public TextView messageButton;

    public ItemViewHolder(View itemView) {
        super(itemView);
        //nameTextView = (TextView) itemView.findViewById(R.id.person_name);
        //messageButton = (TextView) itemView.findViewById(R.id.person_age);
    }
}

public class delivery_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<DeliveryPojo> items;
    int visiblethreshold=5;
    int lastvisibleItem,totalItemCount;




    public delivery_adapter(RecyclerView recyclerView, Activity activity,  List<DeliveryPojo> items){

        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastvisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastvisibleItem+visiblethreshold)){

                    if(loadMore != null)
                        loadMore.onLoadMore();

                    isLoading = true;
                }

            }
        });
    }


    @Override
    public int getItemViewType(int position) {

        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;

    }

    public void setLoadMore(ILoadMore loadMore) {

        this.loadMore = loadMore;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       if (viewType == VIEW_TYPE_ITEM){
           View view = LayoutInflater.from(activity).inflate(R.layout.delivery_item_recycle, parent,false);

           return new ItemViewHolder(view);
       }
        else if (viewType == VIEW_TYPE_LOADING){

           View view = LayoutInflater.from(activity).inflate(R.layout.delivery_item_recycle, parent,false);

           return new LoadingViewHolder(view);

       }

        return  null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder){

            DeliveryPojo item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;

            viewHolder.messageButton.setText(items.get(position).getName());


            //click happens here


        }
        else if(holder instanceof LoadingViewHolder){

            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;

            loadingViewHolder.progressBar.setIndeterminate((true));

        }
    }

    @Override
    public int getItemCount() {
        return items.size();

    }


    public void setLoaded() {

        isLoading = false;
    }
}
