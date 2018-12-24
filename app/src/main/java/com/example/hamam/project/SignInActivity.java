package com.example.hamam.project;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamam.project.Model.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
   EditText edtId, edtPassword;
    Button btnSignIn;
String CurrentUserID= "";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    private Boolean mLocationPermissionsGranted = false;
     private FusedLocationProviderClient mFusedLocationProviderClient;
     private DatabaseReference table_use;
     private String  saveCurrentDate, saveCurrentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


         edtPassword= findViewById(R.id.edtPassword);
        edtId= findViewById(R.id.edtId);
        btnSignIn = findViewById(R.id.btnSignIn);

      //Init Firebase
        FirebaseDatabase database= FirebaseDatabase.getInstance();
            table_use =database.getReference("User");


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
                 if (dataSnapshot.child(edtId.getText().toString()).exists()) {

                     User user =dataSnapshot.child(edtId.getText().toString()).getValue(User.class);

                   if(user.getPassword().equals(edtPassword.getText().toString())) {
                        // Get User information
                         mDialog.dismiss();
                         Log.d(TAG, "onDataChange: ");
                        Log.d(TAG, user.getMarks());

                        Intent startMainActivity = new Intent(SignInActivity.this, MainActivity.class);
                        startMainActivity.putExtra("user_id", edtId.getText().toString());
                        startActivity(startMainActivity);
                       CurrentUserID=edtId.getText().toString();

                       getDeviceLocation();

                    } else{
                       Toast.makeText(SignInActivity.this, "Worning password", Toast.LENGTH_SHORT).show();
                    }
                    }else {

                    mDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "User not exist 2", Toast.LENGTH_SHORT).show();
                }

                    }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
        });

    }


    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();
        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();


//                            Toast.makeText(MapActivity.this, ""+ currentLocation.getLatitude() +  currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();


                            Calendar calFordDate = Calendar.getInstance();
                            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
                            saveCurrentDate = currentDate.format(calFordDate.getTime());

                            Calendar calFordTime = Calendar.getInstance();
                            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                            saveCurrentTime = currentTime.format(calFordTime.getTime());



                            Map<String, Object> map = new HashMap<String, Object>();

                            map.put("lat", currentLocation.getLatitude() );
                            map.put("lang", currentLocation.getLongitude());
                            map.put("date", saveCurrentDate);
                            map.put("time", saveCurrentTime);


                            table_use.child(CurrentUserID).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignInActivity.this, "Save hkhk!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignInActivity.this, "Save Not lkjj !!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });



                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(SignInActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
             } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    //initialize our map
                 }
            }
        }
    }


}
