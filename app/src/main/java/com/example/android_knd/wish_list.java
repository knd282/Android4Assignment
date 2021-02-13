package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
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

    private Button clear, L1, L2, L3, L4, L5;

    //shake sensors
    private SensorManager sm;
    private float aceVal, aceLast, shake;


    //back press animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
    }

    //private String wish list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        //sensor
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        aceVal = SensorManager.GRAVITY_EARTH;
        aceLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
        //sensor

        // to clear fav books
        clear = (Button)findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAllLists();
            }
        });

        L1 = (Button)findViewById(R.id.btnList1);
        L2 = (Button)findViewById(R.id.btnList2);
        L3 = (Button)findViewById(R.id.btnList3);
        L4 = (Button)findViewById(R.id.btnList4);
        L5 = (Button)findViewById(R.id.btnList5);

        //start here to retrieve fav books
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wishlist");
        List<String> wishlist_item = new ArrayList<String>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    wishlist_item.add(postSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //list 1
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

    private void removeAllLists() {
        DatabaseReference myRefC = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wishlist");
        myRefC.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren()){ //no children show toast
                    Toast.makeText(wish_list.this, "There is nothing to remove", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    postSnapshot.getRef().removeValue();
                    Toast.makeText(wish_list.this, "Removed", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //sensor
    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            aceLast = aceVal;
            aceVal = (float) Math.sqrt((double)(x*x + y*y + z*z));
            float delta = aceVal - aceLast;
            shake = shake * 0.9f + delta;

            if(shake>14){
                removeAllLists();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

}