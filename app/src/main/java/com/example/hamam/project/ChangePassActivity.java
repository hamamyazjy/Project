package com.example.hamam.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePassActivity extends AppCompatActivity {
    Button btn_confirm;
    EditText newPass,newConfimPass;

    DatabaseReference reference;
    FirebaseDatabase database;

    String user_id = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        btn_confirm =findViewById(R.id.btn_Confirm);
        newPass =findViewById(R.id.edtnewPassword);
        newConfimPass =findViewById(R.id.edtconfiemPassword);



        if (getIntent() != null){
            if (getIntent().hasExtra("user_id")){

                user_id =getIntent().getStringExtra("user_id").toString();
            }

        }
        database =FirebaseDatabase.getInstance();
        reference = database.getReference().child("User").child(user_id);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = newPass.getText().toString();
                String confPass =newConfimPass.getText().toString();

                if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confPass) ) {

                    Toast.makeText(ChangePassActivity.this, "Please enter new password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass.equals(confPass)){


                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("password", pass);

                    reference.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(ChangePassActivity.this, "The Password succesfully changed !", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                                finish();
                            }else {
                                Toast.makeText(ChangePassActivity.this, "Save Not Complete !!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });




                }else{
                    Toast.makeText(ChangePassActivity.this, "no match password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
