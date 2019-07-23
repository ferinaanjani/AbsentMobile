package com.example.company.absentmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3,e4,e5;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        e1= (EditText)findViewById(R.id.nik);
        e2= (EditText)findViewById(R.id.email);
        e3= (EditText)findViewById(R.id.nama);
        e4= (EditText)findViewById(R.id.pass);
        e5= (EditText)findViewById(R.id.cpass);
        b1= (Button)findViewById(R.id.register);
        b2= (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //s1 nik, s2 nama, s3 email, s4 pass
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                UserModel user = new UserModel(s1, s3, s2, s4);
                if(s1.equals("")||s2.equals("")||s3.equals("")) {
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                } else{
                    if (s4.equals(s5)) {
                        Boolean chknik = db.chknik(s1);
                        if (chknik == true) {
                            long insert = db.insert(user);
                            if (insert == 1) {
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Nik already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}