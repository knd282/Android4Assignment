package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Button clear, L1, L2, L3, L4, L5;

    //back press animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
    }

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
                            Toast.makeText(wish_list.this, "Removed", Toast.LENGTH_SHORT).show();
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
                                Intent launchWish = new Intent(wish_list.this, wishlist_webview.class);
                                String a = wishlist_item.get(0);
                                launchWish.putExtra("k",a);
                                startActivity(launchWish);
                                //animation
                                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                            }else{
                                Toast.makeText(wish_list.this, "Favourite 1 is empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                //}
                //list 2
                L2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=2){
                            Intent launchWish = new Intent(wish_list.this, wishlist_webview.class);
                            String b = wishlist_item.get(1);
                            launchWish.putExtra("k",b);
                            startActivity(launchWish);
                            //animation
                            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite 2 is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //list 3

                L3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=3){
                            Intent launchWish = new Intent(wish_list.this, wishlist_webview.class);
                            String c = wishlist_item.get(2);
                            launchWish.putExtra("k",c);
                            startActivity(launchWish);
                            //animation
                            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite 3 is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //list 4
                L4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=4){
                            Intent launchWish = new Intent(wish_list.this, wishlist_webview.class);
                            String d = wishlist_item.get(3);
                            launchWish.putExtra("k",d);
                            startActivity(launchWish);
                            //animation
                            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite 4 is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //list 5
                L5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wishlist_item.size()>=5){
                            Intent launchWish = new Intent(wish_list.this, wishlist_webview.class);
                            String e = wishlist_item.get(4);
                            launchWish.putExtra("k",e);
                            startActivity(launchWish);
                            //animation
                            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                        }else{
                            Toast.makeText(wish_list.this, "Favourite 5 is empty", Toast.LENGTH_SHORT).show();
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