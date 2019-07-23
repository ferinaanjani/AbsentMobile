package com.example.company.absentmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{


    DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(nik text primary key,email text,nama text, password text)");
        db.execSQL("Create table cuti(idCuti INTEGER PRIMARY KEY AUTOINCREMENT, tanggal TEXT, lama TEXT, alasan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists cuti");
    }

    public long insert(UserModel model){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nik", model.getNik());
        contentValues.put("nama", model.getNama());
        contentValues.put("email", model.getEmail());
        contentValues.put("password", model.getPassword());
        return getWritableDatabase().insert("user", null, contentValues);
    }

    public long insertCuti(CutiModel model){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tanggal", model.getTanggal());
        contentValues.put("lama", model.getLama());
        contentValues.put("alasan", model.getAlasan());
        return getWritableDatabase().insert("cuti", null, contentValues);
    }

    //menambah ke database
//    boolean insert(String nik,String email,String nama,String password)[
//            SQLiteDatabase db = this.getWritableDatabase();
//            private ContentValues contentValues = new ContentValues();
//            contentValues.put("nik",nik);
//            contentValues.put("email",email);
//            contentValues.put("nama",nama);
//            contentValues.put("password",password);
//            long ins = db.insert("user", null,contentValues);
//            if(ins==-1) return false;
//            else return true;
//            ]

    //cek nik yang sudah ada
    public Boolean chknik(String nik) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where nik=?",new String[]{nik});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    //cek email dan password;
    public Boolean nikpassword(String nik,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where nik=? and password=?",new String[]{nik,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
