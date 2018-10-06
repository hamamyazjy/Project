

package com.example.hamam.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnSignUp  , btnSignIn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
