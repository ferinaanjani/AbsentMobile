package com.example.company.absentmobile;

public class UserModel {
    private String nik;
    private String nama;
    private String email;
    private String password;

    public UserModel(String nik,String nama,String email,String password) {
        this.nik = nik;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
