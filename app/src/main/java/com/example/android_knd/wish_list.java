package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wish_list extends AppCompatActivity {

    TextView display;

    //private String wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        display=(TextView)findViewById(R.id.tvDisplay);

        //start here
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wishlist");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> wishlist_item = new ArrayList<String>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    String data = postSnapshot.getValue(String.class);
                    wishlist_item.add(data);//got all wish list item from database

                    //String a = wishlist_item.get(0);
                    //String b = wishlist_item.get(1);

                    //display.setText("Hi" + b);
                }
                //Log.v("asdf", "First data" +wishlist_item.get(0));
                String a = wishlist_item.get(0);
                String b = wishlist_item.get(1);
                //display.setText("Hi" + wishlist_item.get(1));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}