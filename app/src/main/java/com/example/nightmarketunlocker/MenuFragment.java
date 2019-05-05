package com.example.nightmarketunlocker;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    ListView lstPrefer;
    private DatabaseReference myRef;
    final List<String> resIds=new ArrayList<>();
    final List<String> name=new ArrayList<>();
    final List<String> description=new ArrayList<>();
    final List<String> price=new ArrayList<>();
    //int[] resIds = new int[]{ R.drawable.chou1, R.drawable.chou2,R.drawable.chou3,R.drawable.shop};
    // String[] name= new String[] {"臭豆腐","臭豆腐2","臭豆腐3","臭豆腐4"};
    //String[] description = { "臭豆腐好吃", "臭豆腐好吃哈哈","臭豆腐好吃嘻嘻","hah"};
    //String[] price ={"5","6","7","7"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_menu, container, false);
        lstPrefer = view1.findViewById(R.id.lst);
        final MyAdapter adapter = new MyAdapter(this.getActivity());
        lstPrefer.setAdapter(adapter);



        myRef = FirebaseDatabase.getInstance().getReference("Food");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Food food=ds.getValue(Food.class);

                    if (food.getFoodStore()==7) {
                        resIds.add(food.getFoodImage());
                        name.add(food.getFoodName());
                        description.add(food.getFoodDesc());
                        price.add(String.valueOf(food.getFoodPrice()));
                        adapter.notifyDataSetChanged();

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return view1;
    }




    public class MyAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        public MyAdapter(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return name.size();
        }
        @Override
        public Object getItem(int position){
            return name.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.item, null);

            // 取得 mylayout.xml 中的元件
            ImageView imgLogo = (ImageView) convertView.findViewById(R.id.img);
            TextView txtName = ((TextView) convertView.findViewById(R.id.name));
            TextView txtengName = ((TextView) convertView.findViewById(R.id.description));
            TextView txtprice = ((TextView) convertView.findViewById(R.id.price));

            // 設定元件內容
            Glide.with(imgLogo.getContext())
                    .load(resIds.get(position))
                    .into(imgLogo);

            txtName.setText(name.get(position));
            //或 txtName.setText(""+getItem(position));
            txtengName.setText(description.get(position));
            txtprice.setText(price.get(position));

            return convertView;
        }
    }
}
