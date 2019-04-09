package com.example.nightmarket;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> mfoodName;
    ArrayList<String> mfoodRate;
    ArrayList<String> mfoodImage;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, foodRate;

        public SearchViewHolder(View itemView) {
            super(itemView);
            foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
            foodName = (TextView) itemView.findViewById(R.id.foodName);
            foodRate = (TextView) itemView.findViewById(R.id.foodRate);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> userNameList, ArrayList<String> profilePicList) {
        this.context = context;
        this.mfoodName = fullNameList;
        this.mfoodRate = userNameList;
        this.mfoodImage = profilePicList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.foodName.setText(mfoodName.get(position));
        holder.foodRate.setText(mfoodRate.get(position));
        Glide.with(context).load(mfoodImage.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.foodImage);

        holder.foodName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fullNameList.size();
    }
}
