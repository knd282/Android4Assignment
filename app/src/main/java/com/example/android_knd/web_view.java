package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class web_view extends AppCompatActivity {
    //for fav
    FloatingActionButton fab;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        fab = findViewById(R.id.btn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start
                String passURL = getIntent().getStringExtra("key");

                if(passURL.matches("[0-9]+") && passURL.length()>= 10){
                    //add wishlist to firebase with auto generate ID
                    DatabaseReference myRef = FirebaseDatabase.getInstance()
                            .getReference().child("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Wishlist");
                    myRef.push().setValue("http://www.librarything.com/isbn/" + passURL).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){//show it if complete
                                Toast.makeText(web_view.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                            }else{//just error handler
                                Toast.makeText(web_view.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //End send wishlist to firebase
                }else{
                    Toast.makeText(web_view.this, "You can only add the book with ISBN", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //start for web view
        webView = (WebView) findViewById(R.id.webview);
        //fit screen
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        //end for web view

        //received data from search book
        String passURL = getIntent().getStringExtra("key");
        //check ISNB or Title
        if(passURL.matches("[0-9]+") && passURL.length()>= 10){
            webView.loadUrl("http://www.librarything.com/isbn/" + passURL); //load website
        }else if (passURL.matches("[a-zA-Z ]+")){
            webView.loadUrl("http://www.librarything.com/title/" + passURL); //load website
        }else {
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_LONG).show();
        }
    }
}