package com.example.nightmarketunlocker;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CommentFragment extends Fragment {

    private AdapterComment adapter;
    private DatabaseReference myRef;
    private Button button;
    private ArrayList<Comment> data ;
    /* List<String> c_user_name=new ArrayList<>();
     List<String> c_content=new ArrayList<>();
     List<String> c_date=new ArrayList<>();
     List<String> c_user_logo=new ArrayList<>();*/
    Button button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1=inflater.inflate(R.layout.fragment_review,container,false);
        ListView list = view1.findViewById(R.id.listview);
        button2=view1.findViewById(R.id.button2);
        button2.setOnClickListener(listner);

        data = new ArrayList<>();
        //AdapterComment adapter=new AdapterComment(this.getContext());
        adapter = new AdapterComment(this.getContext(), data);
        list.setAdapter(adapter);





        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            //parent 代表listView View 代表 被点击的列表项 position 代表第几个 id 代表列表编号
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), data.get(position).getReviewStoreName()+String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });


        myRef = FirebaseDatabase.getInstance().getReference("reviews");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Comment comment = ds.getValue(Comment.class);

                    if (comment.getReviewStoreName().equals("Beans Village 豆花莊")){
                        data.add(comment);
                        adapter.notifyDataSetChanged();}
                 /*data.add(comment);
                 adapter.notifyDataSetChanged();*/
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view1;
    }
    private Button.OnClickListener listner=new Button.OnClickListener(){


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            commentdialog a=new commentdialog(getContext(),R.style.Dialog);
            a.setTitle("评论");
            a.create();
            a.show();
        }
    };


    /*long size = dataSnapshot.getChildrenCount();

                for (int i = 0; i <=size ; i++) {
                    String name = dataSnapshot.child("userName").getValue().toString();
                    String content=dataSnapshot.child("reviewContent").getValue().toString();
                    String date=dataSnapshot.child("reviewDate")
                }*/

 /*public class AdapterComment extends BaseAdapter {
        private LayoutInflater myInflater;
        public AdapterComment(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return c_user_name.size();
        }
        @Override
        public Object getItem(int position){
            return c_user_name.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.comment_item_layout, null);
            TextView user_name = (TextView) convertView.findViewById(R.id.comment_item_userName);
            TextView content = (TextView) convertView.findViewById(R.id.comment_item_content);
            TextView date=(TextView) convertView.findViewById(R.id.comment_item_time);
            ImageView userlogo=(ImageView) convertView.findViewById(R.id.comment_item_logo);
            // 取得 mylayout.xml 中的元件


            // 設定元件內容
            Glide.with(userlogo.getContext())
                    .load(c_user_logo.get(position))
                    .into(userlogo);
            user_name.setText(c_user_name.get(position));
            //或 txtName.setText(""+getItem(position));
            content.setText(c_content.get(position));
            date.setText(c_date.get(position));

            return convertView;
        }*/


}
