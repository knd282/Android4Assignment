package com.example.android_knd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//for logout and searching books
public class search_book extends AppCompatActivity {

//-------------------------------------------------for book search
    EditText tBook;
    Button btnDisplay;
//-------------------------------------------------logout
    private Button logout;
//--------------------------------------------------logout
//--------------------------------------------------ISBN
    Button btnISBNum;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(scanResult.getContents() != null){ //if not null
            String isbncodeR = scanResult.getContents();
            //get isbn and send to web_v
            String passURL = isbncodeR;
            //send data to web activity
            Intent launchResult = new Intent(search_book.this, web_view.class);
            launchResult.putExtra("key", passURL);
            startActivity(launchResult);
        }else {
            //if null
            Toast.makeText(this, "Code isn't found", Toast.LENGTH_LONG).show();
        }
    }
//--------------------------------------------------ISBN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
//--------------------------------------------------logout
        logout = (Button) findViewById(R.id.signOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(search_book.this, MainActivity.class));
            }
        });
//--------------------------------------------------logout
//--------------------------------------------------for ISBN
        btnISBNum = findViewById(R.id.btnISBN);
        btnISBNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent integration
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        search_book.this
                );
                intentIntegrator.setPrompt("Volume Up to Open Flash");
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setCaptureActivity(Cam.class);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.EAN_13);
                intentIntegrator.initiateScan();
            }
        });
//--------------------------------------------------for ISBN
//--------------------------------------------------for book search start
        tBook = findViewById(R.id.txtBook);
        btnDisplay = findViewById(R.id.btnSearch);

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
                    Intent launchResult = new Intent(search_book.this, web_view.class);
                    launchResult.putExtra("key", passURL);
                    startActivity(launchResult);
                }
            }
        });
//--------------------------------------------------for book search end
    }
}