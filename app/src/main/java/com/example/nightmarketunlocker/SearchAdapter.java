package com.example.nightmarketunlocker;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> foodNameList;
    ArrayList<String> foodDescList;
    ArrayList<String> foodImageList;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView foodimage;
        TextView foodname, fooddesc;

        public SearchViewHolder(View itemView) {
            super(itemView);
            foodimage = (ImageView) itemView.findViewById(R.id.foodimage);
            foodname = (TextView) itemView.findViewById(R.id.foodname);
            fooddesc = (TextView) itemView.findViewById(R.id.fooddesc);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> foodNameList, ArrayList<String> foodDescList, ArrayList<String> foodImageList) {
        this.context = context;
        this.foodNameList = foodNameList;
        this.foodDescList = foodDescList;
        this.foodImageList = foodImageList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.foodname.setText(foodNameList.get(position));
        holder.fooddesc.setText(foodDescList.get(position));
        Glide.with(context).load(foodImageList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.foodimage);

        holder.foodname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Food Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }
}
