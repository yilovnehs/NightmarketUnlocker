package com.example.nightmarketunlocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterComment extends BaseAdapter {

    Context context;
    ArrayList<Comment> data;

    public AdapterComment(Context c, ArrayList<Comment> data){
        this.context = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, null);
            holder.comment_name = (TextView) convertView.findViewById(R.id.comment_item_userName);
            holder.comment_content = (TextView) convertView.findViewById(R.id.comment_item_content);
            holder.comment_date=convertView.findViewById(R.id.comment_item_time);
            holder.userlogo=convertView.findViewById(R.id.comment_item_logo);
            holder.ratingBar=convertView.findViewById(R.id.ratingBar2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        holder.comment_name.setText(data.get(i).getUserName());
        holder.comment_content.setText(data.get(i).getReviewContent());
        holder.comment_date.setText(data.get(i).getReviewDate());
        Glide.with(holder.userlogo.getContext())
                .load(data.get(i).getUserImage())
                .into(holder.userlogo);
        holder.ratingBar.setRating(data.get(i).getReviewRate());

        return convertView;
    }

    /*
     *
     * 添加一条评论,刷新列表
     * @param comment
     */


    public void addComment(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }
    public void clear()
    {
        data.clear();
        notifyDataSetChanged();}
    /**
     * 静态类，便于GC回收
     */

    public static class ViewHolder{
        TextView comment_name;
        TextView comment_content;
        TextView comment_date;
        ImageView userlogo;
        RatingBar ratingBar;
    }
}
