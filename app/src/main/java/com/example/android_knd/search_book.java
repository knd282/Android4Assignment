package com.example.android_knd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class search_book extends AppCompatActivity {

    private FloatingActionButton fab_wish;
    private EditText tBook;
    private Button btnDisplay, logout, btnISBNum;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//--------------------------------------------------ISBN
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(scanResult.getContents() != null){ //if not null
            String isbncodeR = scanResult.getContents();
            //get isbn and send to web_v
            String passURL = isbncodeR;
            //send data to web activity
            Intent launchResult = new Intent(search_book.this, web_view.class);
            launchResult.putExtra("key", passURL);
            startActivity(launchResult);
            //animation
            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
        }else {

        }
    }
//--------------------------------------------------ISBN
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        //wishlist
        fab_wish = (FloatingActionButton)findViewById(R.id.fab_wishL);
        fab_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),wish_list.class));
                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
            }
        });
        //wish list
//--------------------------------------------------logout
        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.slide_down_in,R.anim.slide_down_out);
                finish();
            }
        });
//--------------------------------------------------logout
//--------------------------------------------------for ISBN
        btnISBNum = (Button)findViewById(R.id.btnISBN);
        btnISBNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent integration
                IntentIntegrator intentIntegrator = new IntentIntegrator(search_book.this);
                intentIntegrator.setPrompt("Volume up to open flash");
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setCaptureActivity(cam.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.EAN_13);
                intentIntegrator.initiateScan();
                //animation
                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
            }
        });
//--------------------------------------------------for ISBN
//--------------------------------------------------for book search start
        tBook = (EditText) findViewById(R.id.txtBook);
        btnDisplay = (Button)findViewById(R.id.btnSearch);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tBook.getText().toString().isEmpty()){
                    Toast.makeText(search_book.this, "Please enter ISBN or Title", Toast.LENGTH_SHORT).show();
                }else {
                    String txtBook = tBook.getText().toString();
                    //get data
                    String passURL = txtBook;
                    //send data to web activity
                    Intent launchResult = new Intent(search_book.this, web_view.class);
                    launchResult.putExtra("key", passURL);
                    startActivity(launchResult);
                    //animation
                    overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                }
            }
        });
//--------------------------------------------------for book search end
    }
}