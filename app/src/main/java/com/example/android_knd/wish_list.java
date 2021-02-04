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

    Button clear;

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

        //start here to clear fav books
        clear = findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRefC = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(FirebaseAuth.getInstance()
                        .getCurrentUser().getUid()).child("Wishlist");

                myRefC.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            postSnapshot.getRef().removeValue();
                            Toast.makeText(wish_list.this, "Clear", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        
        //
        L1 = findViewById(R.id.btnList1);
        L2 = findViewById(R.id.btnList2);
        L3 = findViewById(R.id.btnList3);
        L4 = findViewById(R.id.btnList4);
        L5 = findViewById(R.id.btnList5);


        //start here to retrieve fav books
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
                //if(wishlist_item.size()>=1){
                    L1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(wishlist_item.size()>=1){
                                Intent launchWish = new Intent(wish_list.this,web_view2.class);
                                String a = wishlist_item.get(0);
                                launchWish.putExtra("k",a);
                                startActivity(launchWish);
                            }else{
                                Toast.makeText(wish_list.this, "Favourite is empty 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                //}
                //list 2
                L2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=2){
                            Intent launchWish = new Intent(wish_list.this,web_view2.class);
                            String b = wishlist_item.get(1);
                            launchWish.putExtra("k",b);
                            startActivity(launchWish);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite is empty 2", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //list 3

                L3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=3){
                            Intent launchWish = new Intent(wish_list.this,web_view2.class);
                            String c = wishlist_item.get(2);
                            launchWish.putExtra("k",c);
                            startActivity(launchWish);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite is empty 3", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //list 4
                L4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=4){
                            Intent launchWish = new Intent(wish_list.this,web_view2.class);
                            String d = wishlist_item.get(3);
                            launchWish.putExtra("k",d);
                            startActivity(launchWish);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite is empty 4", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //list 5
                L5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=5){
                            Intent launchWish = new Intent(wish_list.this,web_view2.class);
                            String e = wishlist_item.get(4);
                            launchWish.putExtra("k",e);
                            startActivity(launchWish);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite is empty 5", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
































    }
}