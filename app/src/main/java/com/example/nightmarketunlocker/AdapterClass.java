package com.example.nightmarketunlocker;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    Context context;
    ArrayList<Stores> list;
    private OnItemClickListener mListener;
    Locale locale = Locale.getDefault();


    //define interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

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
        //view.setOnClickListener((View.OnClickListener) this);
        return mvh; // , mOnStoreListener
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {  //i = position
        //myViewHolder.name.setText(list.get(i).getStoreName());
        //myViewHolder.desc.setText(list.get(i).getStoreDesc());

        //add
        if (locale.equals(Locale.JAPAN)) {
            // 日本語環境
            myViewHolder.name.setText(list.get(i).getStoreNameJPN());
            myViewHolder.desc.setText(list.get(i).getStoreDesc());
        } else {
            // その他の言語環境
            myViewHolder.name.setText(list.get(i).getStoreName());
            myViewHolder.desc.setText(list.get(i).getStoreDesc());
        }

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
        RelativeLayout parentLayout;

        public MyViewHolder(View itemView) {  //, OnStoreListener onStoreListener
            super(itemView);
            name = itemView.findViewById(R.id.storeName_text);
            desc = itemView.findViewById(R.id.storeDesc_text);
            url = itemView.findViewById(R.id.storeUrl_text);
            storecategoryid = itemView.findViewById(R.id.storeCategoryId_text);
            ratingbar = itemView.findViewById(R.id.ratingBar);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
