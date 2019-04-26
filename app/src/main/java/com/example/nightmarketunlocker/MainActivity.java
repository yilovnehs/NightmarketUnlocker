package com.example.nightmarketunlocker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference().child("nightmarket-1552321619125");
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(ref != null){
            ref.addValueEventListener()
        }
    }
}
