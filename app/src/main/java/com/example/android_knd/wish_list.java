package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wish_list extends AppCompatActivity {


    Button L1;
    Button L2;
    Button L3;
    Button L4;
    Button L5;
    //private String wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);


        L1 = findViewById(R.id.btnList1);
        L2 = findViewById(R.id.btnList2);
        L3 = findViewById(R.id.btnList3);
        L4 = findViewById(R.id.btnList4);
        L5 = findViewById(R.id.btnList5);


        //start here
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wishlist");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> wishlist_item = new ArrayList<String>();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    wishlist_item.add(postSnapshot.getValue().toString());
                }

                //list 1
                if(wishlist_item.size()>=1){
                    L1.setOnClickListener(new View.OnClickListener() {
                        String a = wishlist_item.get(0);
                        @Override
                        public void onClick(View view) {
                            if(a.isEmpty()){
                                Toast.makeText(wish_list.this, "Empty Field", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent launchWish = new Intent(wish_list.this,web_view2.class);
                                launchWish.putExtra("k",a);
                                startActivity(launchWish);
                            }
                        }
                    });
                }
                //list 2
                if(wishlist_item.size()>=2) {
                    L2.setOnClickListener(new View.OnClickListener() {
                        String b = wishlist_item.get(1);

                        @Override
                        public void onClick(View view) {
                            if (b.isEmpty()) {
                                Toast.makeText(wish_list.this, "Empty Field", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent launchWish = new Intent(wish_list.this, web_view2.class);
                                launchWish.putExtra("k", b);
                                startActivity(launchWish);
                            }
                        }
                    });
                }
                //list 3
                if(wishlist_item.size()>=3){
                    L3.setOnClickListener(new View.OnClickListener() {
                        String c = wishlist_item.get(2);
                        @Override
                        public void onClick(View view) {
                            if(c.isEmpty()){
                                Toast.makeText(wish_list.this, "Empty Field", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent launchWish = new Intent(wish_list.this,web_view2.class);
                                launchWish.putExtra("k",c);
                                startActivity(launchWish);
                            }
                        }
                    });
                }
                //list 4
                if(wishlist_item.size()>=4) {
                    L4.setOnClickListener(new View.OnClickListener() {
                        String d = wishlist_item.get(3);

                        @Override
                        public void onClick(View view) {
                            if (d.isEmpty()) {
                                Toast.makeText(wish_list.this, "Empty Field", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent launchWish = new Intent(wish_list.this, web_view2.class);
                                launchWish.putExtra("k", d);
                                startActivity(launchWish);
                            }
                        }
                    });
                }
                //list 5
                if(wishlist_item.size()>=5) {
                    L5.setOnClickListener(new View.OnClickListener() {
                        String e = wishlist_item.get(4);

                        @Override
                        public void onClick(View view) {
                            if (e.isEmpty()) {
                                Toast.makeText(wish_list.this, "Empty Field", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent launchWish = new Intent(wish_list.this, web_view2.class);
                                launchWish.putExtra("k", e);
                                startActivity(launchWish);
                            }
                        }
                    });
                    //the end for 5 lists
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
































    }
}