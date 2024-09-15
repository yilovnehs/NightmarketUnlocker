package com.example.nightmarketunlocker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class Search2Activity extends AppCompatActivity  { //implements AdapterClass.OnStoreListener
    DatabaseReference ref;
    ArrayList<Stores> list;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterClass adapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        ref = FirebaseDatabase.getInstance().getReference().child("Stores");
        recyclerView = findViewById(R.id.rv);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this)); //add
        //adapterClass = new AdapterClass(list);
        //recyclerView.setAdapter(adapterClass);
        searchView = findViewById(R.id.searchView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterClass);



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Stores.class));
                        }
                        //AdapterClass adapterClass = new AdapterClass(Search2Activity.this, list, this);
                        recyclerView.setHasFixedSize(true); //add
                        //recyclerView.setLayoutManager(new LinearLayoutManager(this));  //add
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Search2Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str)
    {
        ArrayList<Stores> myList = new ArrayList<>();
        for(Stores object : list)
        {
            if(object.getStoreDesc().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
            if(object.getStoreName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }

        AdapterClass adapterClass = new AdapterClass(Search2Activity.this, myList);
        //recyclerView = findViewById(R.id.rv); //add
        recyclerView.setHasFixedSize(true); //add
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));  //add
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterClass);
        //AdapterClass.setOnItemClickListener(Search2Activity.this);
    }

    //@Override
    public void OnStoreClick(int position) {
        //list.get(position);
        //Intent intent = new Intent(getActivity(), MenuFragment.class);
        //startActivity(intent);
        Log.d(TAG, "OnStoreClick: clicked");
    }

    //@Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(Search2Activity.this, ScrollingActivity.class);
        Stores clickedItem = list.get(position);

        //detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        //detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getCreator());
        //detailIntent.putExtra(EXTRA_LIKES, clickedItem.getLikeCount());
        Toast.makeText(Search2Activity.this,"Successfully Clicked",Toast.LENGTH_SHORT).show();

        startActivity(detailIntent);
    }
}


