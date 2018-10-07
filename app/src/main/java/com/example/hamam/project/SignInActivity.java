package com.example.hamam.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamam.project.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

   EditText edtId, edtPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        edtPassword= findViewById(R.id.edtPassword);
        edtId= findViewById(R.id.edtId);
        btnSignIn = findViewById(R.id.btnSignIn);

      //Init Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference table_use =database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog;
                mDialog = new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please Wating....");
                mDialog.show();


                table_use.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                // check if user not exist in database
                if (dataSnapshot.child(edtId.getText().toString()).exists()){


                    // Get User information
                    mDialog.dismiss();
                    User user =dataSnapshot.child(edtId.getText().toString()).getValue(User.class);
                    if(user.getPassword().equals(edtPassword.getText().toString())){


                        Toast.makeText(SignInActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent startMainActivity = new Intent(SignInActivity.this, MainActivity.class);
                        startMainActivity.putExtra("id",edtId.getText().toString());
                        startActivity(startMainActivity);



                    }else{

                        Toast.makeText(SignInActivity.this, "wrong password!!!", Toast.LENGTH_SHORT).show();


                    }}else {
                    mDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "User not exist ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
        });

    }
}
