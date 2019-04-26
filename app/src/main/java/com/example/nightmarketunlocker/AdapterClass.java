package com.example.nightmarketunlocker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<Deal> list;
    public AdapterClass(ArrayList<Deal> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.food.setText(list.get(i).getFoodName());
        myViewHolder.desc.setText(list.get(i).getFoodDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView food,desc;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            food = itemView.findViewById(R.id.foodName_text);
            desc = itemView.findViewById(R.id.foodDesc_text);
        }
    }
}
