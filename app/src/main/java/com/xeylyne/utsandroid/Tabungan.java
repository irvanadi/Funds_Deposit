package com.xeylyne.utsandroid;

public class Tabungan {
    String tanggal, uraian, jenis;
    int nominal;

    public Tabungan(){

    }

    public Tabungan(String tanggal, String uraian, String jenis, int nominal) {
        this.tanggal = tanggal;
        this.uraian = uraian;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
}
