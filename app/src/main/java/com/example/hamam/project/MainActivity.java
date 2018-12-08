package com.example.hamam.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamam.project.Model.User;
import com.example.hamam.project.common.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtcourse,edtPhone,edtFacebook,edtAttendence,edtcourse_grade;
    TextView tvcourse_grade , tv_comment,edtID;
    String  user_id;
    Button btn_save;
    private DatabaseReference getUserDataReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        getUserDataReference =database.getReference().child("user");


        btn_save=findViewById(R.id.btn_save);
        edtName=findViewById(R.id.edtName);
        edtID=findViewById(R.id.edtID);
        edtcourse=findViewById(R.id.edtcourse);
        edtPhone=findViewById(R.id.edtPhone);
        edtFacebook=findViewById(R.id.edtFacebook);
        edtAttendence=findViewById(R.id.edtattendence);
        edtcourse_grade=findViewById(R.id.std_course_grade);
        tvcourse_grade=findViewById(R.id.tvcourse_grade);
        tv_comment=findViewById(R.id.tv_comment);

        user_id =getIntent().getStringExtra( "id");


        getUserDataReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                  String name = dataSnapshot.child("name").getValue().toString();
               //  String id =dataSnapshot.child("id").getValue().toString();
                 String coursename =dataSnapshot.child("coursename").getValue().toString();
                 String phone =dataSnapshot.child("phone").getValue().toString();
                 String fbacoune =dataSnapshot.child("fbacoune").getValue().toString();
                 String attendence =dataSnapshot.child("attendence").getValue().toString();
                 String marks =dataSnapshot.child("marks").getValue().toString();
                 String grade =dataSnapshot.child("grade").getValue().toString();
                  String comment =dataSnapshot.child("comment").getValue().toString();

                User user= new User();
                user.setAttendence(attendence);
                user.setComment(comment);
                user.setPhone(phone);
                user.setGrade(grade);
                user.setMarks(marks);
                user.setCoursename(coursename);
                 user.setFbacoune(fbacoune);
                user.setName(name);
                Common.user=user;



                edtName.setText(name);
                edtcourse.setText(coursename);
                edtID.setText(getUserDataReference.child(user_id).getKey());
                edtPhone.setText(phone);
                edtFacebook.setText(fbacoune);
                edtAttendence.setText(attendence);
                edtcourse_grade.setText(marks);
               tvcourse_grade.setText( grade);
                tv_comment.setText( comment);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("name", edtName.getText().toString());
               // map.put("id", edtID.getText().toString());
                map.put("coursename", edtcourse.getText().toString());
                map.put("phone", edtPhone.getText().toString());
                map.put("attendence", edtAttendence.getText().toString());
                map.put("marks", edtcourse_grade.getText().toString());
               // map.put("grade", tvcourse_grade.getText().toString());
               // map.put("comment", tv_comment.getText().toString());
                map.put("fbacoune", edtFacebook.getText().toString());
                getUserDataReference.child(user_id).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Save Complete !!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Save Not Complete !!", Toast.LENGTH_LONG).show();

                        }
                    }
                });


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

        if (item.getItemId()==R.id.password_main_button){
            Intent i = new Intent(getApplicationContext(),ChangePassActivity.class);
            i.putExtra("user_id",edtID.getText().toString());
            startActivity(i);

        }

        if (item.getItemId() == R.id.logout_main_button){
            Intent i = new Intent(MainActivity.this,SignInActivity.class);
            startActivity(i);
            finish();

        }
        return super.onOptionsItemSelected(item);



//        if (item.getItemId()== R.id.logout_main_button)
    }
}
