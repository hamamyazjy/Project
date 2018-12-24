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
import com.example.hamam.project.common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText edtId,edtPhone,  edtName, edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtPassword= findViewById(R.id.edtPassword);
        edtId= findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhone =findViewById(R.id.edtPhone);
        btnSignUp = findViewById(R.id.btnSignUp);


        //Init Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference table_use =database.getReference("User");


          btnSignUp.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                final ProgressDialog mDialog =new ProgressDialog(SignUpActivity.this);
                 mDialog.setMessage("Please Wating....");
                 mDialog.show();
                 ValueEventListener valueEventListener = table_use.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {

                          //Check if already user id
                          if (dataSnapshot.child(edtId.getText().toString()).exists()) {
                              mDialog.dismiss();
                              Toast.makeText(SignUpActivity.this, "Id is already exist", Toast.LENGTH_SHORT).show();

                          } else {
                              mDialog.dismiss();
                User user = new User(edtName.getText().toString(), edtId.getText().toString(),"cccc","0592154","sfsfsf","sfs","sfsf","sfssf","5",edtPassword.getText().toString());

                              Common.user=user;
                              table_use.child(edtId.getText().toString()).setValue(user);
                              Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();


                              Intent intent =new Intent(SignUpActivity.this,MainActivity.class);
                              intent.putExtra("user_id",edtId.getText().toString());

                              startActivity(intent);
                              finish();

                          }
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {
                          Toast.makeText(SignUpActivity.this, "Error Register", Toast.LENGTH_SHORT).show();
                      }
                  });

              }
          });


//    }
}
    }
