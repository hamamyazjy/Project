package com.example.hamam.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtcourse,edtPhone,edtFacebook,edt,edtAttendence,edtcourse_grade;
    TextView tvcourse_grade , tv_comment;
    String  user_id;
    private DatabaseReference getUserDataReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        getUserDataReference =database.getReference().child("User");


        edtName=findViewById(R.id.edtName);
        edtcourse=findViewById(R.id.edtcourse);
        edtPhone=findViewById(R.id.edtPhone);
        edtFacebook=findViewById(R.id.edtFacebook);
        edt=findViewById(R.id.edtID);
        edtAttendence=findViewById(R.id.edtattendence);
        edtcourse_grade=findViewById(R.id.edtcourse);
        tvcourse_grade=findViewById(R.id.tvcourse_grade);
        tv_comment=findViewById(R.id.tv_comment);

        user_id =getIntent().getExtras().get("id").toString();


        getUserDataReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 String name = dataSnapshot.child("name").getValue().toString();
                 String phone =dataSnapshot.child("phone").getValue().toString();

           StringBuilder nameId =new StringBuilder(user_id).append("    ").append(name);

                edtName.setText(nameId);
                edtPhone.setText(phone);
           tvcourse_grade.setText("Grade:");
                tv_comment.setText("Comment:");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

//        if (item.getItemId()== R.id.logout_main_button)
    }
}
