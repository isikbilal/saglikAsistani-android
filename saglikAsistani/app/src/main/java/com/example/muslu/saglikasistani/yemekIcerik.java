package com.example.muslu.saglikasistani;

/**
 * Created by Muslu on 28.12.2016.
 */

public class yemekIcerik {
    int id;
    String ad;
    int kalori;

    @Override
    public String toString(){
        return ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }
}
