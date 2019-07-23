package com.example.company.absentmobile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Absensi extends AppCompatActivity {
    private final int LOCATION_PERMISSION_CODE = 99;
    static final double COMPANY_LONGITUDE = 112.6426086425;
    static final double COMPANY_LATITUDE = -7.1462450027;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location userLocation;
    private float[] distances = new float[2];
    private boolean isInside;

    Button absensii, cuti;
    TextView tvDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.absensi);

        //getLocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getUserLocation();

        //event button
        absensii = findViewById(R.id.button3);
        tvDetail = findViewById(R.id.keteranganAbsen);
        absensii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDistanceBetween();
                if (!isInside){
                    Toast.makeText(Absensi.this,"Out Distance",Toast.LENGTH_SHORT).show();
                } else {
                    if (absensii.getText().equals("Absensi Datang")){
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        tvDetail.setText(df.format(date));
                        absensii.setText("Absensi Pulang");
                    } else {
                        absensii.setText("Absensi Datang");
                        tvDetail.setText("");
                    }
                }
            }
        });
        cuti = findViewById(R.id.btPermohonanCuti);
        cuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToCutiPage = new Intent(Absensi.this, Cuti.class);
                Absensi.this.startActivity(moveToCutiPage);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this,new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    userLocation = location;
                }
            }
        });
    }

    private void getDistanceBetween(){
        Location.distanceBetween(userLocation.getLatitude(), userLocation.getLongitude(),
                COMPANY_LATITUDE, COMPANY_LONGITUDE, distances);
        if (distances[0] > 50){
            isInside = false;
        } else {
            isInside = true;
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getUserLocation();
        }
    }
}
