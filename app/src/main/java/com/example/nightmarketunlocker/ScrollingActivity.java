package com.example.nightmarketunlocker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private DatabaseReference myRef1;

    List<Fragment> fragments;
    String[] titles = new String[]{"Menu","Reviews"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        /*Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);*/
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        ImageView imageView=findViewById(R.id.imageview);
        final RatingBar ratingBar=findViewById(R.id.ratingBar);
        final TextView textView4=findViewById(R.id.textView4);





        myRef1 = FirebaseDatabase.getInstance().getReference("Stores");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              /* String name= dataSnapshot.child("1").child("storeName").getValue().toString();
               textView4.setText(name);*/
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String storeid=ds.child("storeId").getValue().toString();
                    String rate=ds.child("storeRate").getValue().toString();
                    String name=ds.child("storeName").getValue().toString();
                    if (storeid.equals("7")){
                        textView4.setText(name);
                        ratingBar.setRating(Float.parseFloat(rate));
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});




        imageView.setScaleType(ImageView.ScaleType. CENTER_CROP);
        fragments=new ArrayList<>();
        fragments.add(new MenuFragment());
        fragments.add(new CommentFragment());
        adapter myadpter=new adapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myadpter);
        tabLayout.setupWithViewPager(viewPager);

    }





    private class adapter extends FragmentPagerAdapter {
        private  List<Fragment>list;

        public adapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
