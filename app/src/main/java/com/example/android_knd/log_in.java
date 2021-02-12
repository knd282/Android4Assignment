package com.example.android_knd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class log_in extends AppCompatActivity implements View.OnClickListener {
    private TextView reg, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button sIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        //check user is login or not
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            //if user is signed in
            startActivity(new Intent(getApplicationContext(),search_book.class));
            overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
            finish();
        }
        //end
        reg = (TextView) findViewById(R.id.register);
        reg.setOnClickListener(this);
        sIn = (Button) findViewById(R.id.signIn);
        sIn.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(getApplicationContext(),sign_up.class));
                //animation
                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(getApplicationContext(),forgot_password.class));
                overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                break;}
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //check email is empty or not
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;}
        //check email format
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Valid email is required");
            editTextEmail.requestFocus();
            return;}
        //check password is empty or not
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;}
        //check password length
        if(password.length()<5){
            editTextPassword.setError("Passwords must have at least 5 characters");
            editTextPassword.requestFocus();
            return;}

        //for login
        progressBar.setVisibility(View.GONE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        //redirect to user profile(search book page)
                        startActivity(new Intent(getApplicationContext(),search_book.class));
                        overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out);
                        finish();
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(log_in.this, "Please check your email, follow the link and change your password", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(log_in.this,"Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}