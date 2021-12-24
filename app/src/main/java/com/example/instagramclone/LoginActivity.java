package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    EditText tvUserName;
    EditText tvpassword;
    Button logInBTN;
    Button btnSignIn;
    public static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser()!=null) goMainActivity();
        btnSignIn = findViewById(R.id.btnSignIn);
        tvUserName = findViewById(R.id.etUserName);
        tvpassword = findViewById(R.id.etPassword);
        logInBTN = findViewById(R.id.btnLogin);
        logInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = tvUserName.getText().toString();
                String password = tvpassword.getText().toString();
                logIn(userName, password);
            }


        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = tvUserName.getText().toString();
                String password = tvpassword.getText().toString();
                signIn(userName,password);
            }
        });
    }

    private void logIn(String userName, String password) {
        Log.i(TAG, "logIn: attempting to log in: "+userName+" password: "+password);
        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "issue logging in",e );
                    Toast.makeText(LoginActivity.this,"error logging in",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "done: successfully logged in: "+userName);
                Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                //navigate to main activity
                goMainActivity();

            }
        });
    }

    private void signIn(String userName, String password) {
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(userName);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if(e!=null){
                    Log.e(TAG, "issue signing up; could be username already taken",e );
                    Toast.makeText(LoginActivity.this,"esigning up; could be username already taken",Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.i(TAG, "done: successfully logged in: "+userName);
                Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                //navigate to main activity
                goMainActivity();

            }
        });
    }



    private void goMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}