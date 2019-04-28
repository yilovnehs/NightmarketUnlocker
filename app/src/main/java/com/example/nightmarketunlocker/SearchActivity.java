package com.example.nightmarketunlocker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> foodNameList;
    ArrayList<String> foodDescList;
    ArrayList<String> foodImageList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
         * Create a array list for each node you want to use
         * */
        foodNameList = new ArrayList<>();
        foodDescList = new ArrayList<>();
        foodImageList = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    foodNameList.clear();
                    foodDescList.clear();
                    foodImageList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("food").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */
                foodNameList.clear();
                foodDescList.clear();
                foodImageList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                 * Search all users for matching searched string
                 * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String foodName = snapshot.child("foodName").getValue(String.class);
                    String foodDesc = snapshot.child("foodDesc").getValue(String.class);
                    String foodImage = snapshot.child("foodImage").getValue(String.class);

                    if (foodName.toLowerCase().contains(searchedString.toLowerCase())) {
                        foodNameList.add(foodName);
                        foodDescList.add(foodDesc);
                        foodImageList.add(foodImage);
                        counter++;
                    } else if (foodDesc.toLowerCase().contains(searchedString.toLowerCase())) {
                        foodNameList.add(foodName);
                        foodDescList.add(foodDesc);
                        foodImageList.add(foodImage);
                        counter++;
                    }

                    /*
                     * Get maximum of 15 searched results only
                     * */
                    if (counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(SearchActivity.this, foodNameList, foodDescList, foodImageList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
