package com.example.nightmarketunlocker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    DatabaseReference ref;
    ArrayList<Stores> list;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterClass adapterClass;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_search);

        ref = FirebaseDatabase.getInstance().getReference().child("Stores");
        recyclerView = getView().findViewById(R.id.rv);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this)); //add
        //adapterClass = new AdapterClass(list);
        //recyclerView.setAdapter(adapterClass);
        searchView = getView().findViewById(R.id.searchView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterClass);

    }


    @Override
    public void onStart() {
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
                        AdapterClass adapterClass = new AdapterClass(getActivity(), list);
                        recyclerView.setHasFixedSize(true); //add
                        //recyclerView.setLayoutManager(new LinearLayoutManager(this));  //add
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

        AdapterClass adapterClass = new AdapterClass(getActivity(), myList);
        //recyclerView = findViewById(R.id.rv); //add
        recyclerView.setHasFixedSize(true); //add
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));  //add
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapterClass);
    }

}
