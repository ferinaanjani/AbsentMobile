package com.example.company.absentmobile;

public class CutiModel {

    private String tanggal, lama, alasan;

    public CutiModel(String tanggal,String lama,String alasan) {
        this.tanggal = tanggal;
        this.lama = lama;
        this.alasan = alasan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLama() {
        return lama;
    }

    public void setLama(String lama) {
        this.lama = lama;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
