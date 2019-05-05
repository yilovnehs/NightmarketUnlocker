package com.example.nightmarketunlocker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import android.content.Context;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    Context context;
    ArrayList<Stores> list;
    //private OnStoreListener mOnStoreListener = null;

    public  AdapterClass(Context context, ArrayList<Stores> list){ //, OnStoreListener onStoreListener
        this.list = list;
        this.context = context;
        //this.mOnStoreListener = onStoreListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        MyViewHolder mvh = new MyViewHolder(view);
        //view.setOnClickListener((View.OnClickListener) this);
        return mvh; // , mOnStoreListener
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(list.get(i).getStoreName());
        myViewHolder.desc.setText(list.get(i).getStoreDesc());
        Glide.with(context)
                .load(list.get(i).getStoreUrl())
                .asBitmap()
                .into(myViewHolder.url);
        myViewHolder.ratingbar.setRating(list.get(i).getStoreRate());

       if(list.get(i).getStoreCategoryId() == 1){
           myViewHolder.storecategoryid.setText("Taiwanese");
       }else if(list.get(i).getStoreCategoryId() == 2){
           myViewHolder.storecategoryid.setText("Chinese");
       }else if(list.get(i).getStoreCategoryId() == 3){
           myViewHolder.storecategoryid.setText("Bubble Tea");
       }else if(list.get(i).getStoreCategoryId() == 4){
           myViewHolder.storecategoryid.setText("Juice");
       }else if(list.get(i).getStoreCategoryId() == 5){
           myViewHolder.storecategoryid.setText("Japanese");
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        // implements View.OnClickListener
        TextView name, desc, storecategoryid;
        ImageView url;
        RatingBar ratingbar;
        //OnStoreListener onStoreListener;

        public MyViewHolder(View itemView) {  //, OnStoreListener onStoreListener
            super(itemView);
            name = itemView.findViewById(R.id.storeName_text);
            desc = itemView.findViewById(R.id.storeDesc_text);
            url = itemView.findViewById(R.id.storeUrl_text);
            storecategoryid = itemView.findViewById(R.id.storeCategoryId_text);
            ratingbar = itemView.findViewById(R.id.ratingBar);
            //this.onStoreListener = onStoreListener;

            //itemView.setOnClickListener((View.OnClickListener) this);
        }

/*
        public void onClick(View v) {
            onStoreListener.OnStoreClick(getAdapterPosition());
        }*/
    }


    public static interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

}
