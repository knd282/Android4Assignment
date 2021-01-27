package com.example.android_knd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
//for logout and searching books
public class search_book extends AppCompatActivity {
    //for book search
    EditText tBook;
    Button btnDisplay;
    //private WebView webView;

    //logout
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        //logout start
        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(search_book.this, MainActivity.class));
            }
        });
        //logout end

        // for book search start
        tBook = findViewById(R.id.txtBook);
        btnDisplay = findViewById(R.id.btnBook);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tBook.getText().toString().isEmpty()){
                    Toast.makeText(search_book.this, "Empty Field", Toast.LENGTH_SHORT).show();
                }else {
                    String txtBook = tBook.getText().toString();
                    //get data
                    String passURL = txtBook;
                    //send data to web activity
                    Intent launchResult = new Intent(search_book.this, web_v.class);
                    launchResult.putExtra("key", passURL);
                    startActivity(launchResult);
                }
            }
        });
    }
}