package com.example.company.absentmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cuti extends AppCompatActivity {

    EditText txtdate, txtlamacuti, txtalasancuti;
    Button buttoncuti;
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuti);
        db = new DatabaseHelper(this);
        txtdate = findViewById(R.id.txtdatecuti);
        txtlamacuti = findViewById(R.id.txtlamacuti);
        txtalasancuti = findViewById(R.id.txtalasancuti);
        buttoncuti = findViewById(R.id.buttoncuti);
        buttoncuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(txtdate.getText()) && !TextUtils.isEmpty(txtalasancuti.getText()) && !TextUtils.isEmpty(txtlamacuti.getText())){
                    CutiModel model = new CutiModel(txtdate.getText().toString(), txtlamacuti.getText().toString(), txtalasancuti.getText().toString());
                    long response = db.insertCuti(model);
                    if (response == 1){
                        Toast.makeText(Cuti.this,"Cuti berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Cuti.this,"Harap isi semua data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
