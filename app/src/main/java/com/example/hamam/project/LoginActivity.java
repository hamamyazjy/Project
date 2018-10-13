

package com.example.hamam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button btnSignUp  , btnSignIn ;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.child("asd").setValue("a");

        myRef.setValue("Hello, World!Alex");
        Log.d(TAG, "onCreate: sssssssssssssssssssssssssssssssssssssssssss");
        btnSignUp  =  findViewById(R.id.btnSignUp);
        btnSignIn  = findViewById(R.id.btnSignIn);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SignUp =new Intent(LoginActivity.this,  SignUpActivity.class);
                startActivity(SignUp);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SignIn =new Intent(LoginActivity.this,  SignInActivity.class);

                startActivity(SignIn);
                finish();

            }
        });

    }

}
