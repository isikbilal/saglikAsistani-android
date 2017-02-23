package com.example.muslu.saglikasistani;

/**
 * Created by Muslu on 28.12.2016.
 */

public class yemekBilgi {
    int id;
    int toplam_kalori;
    long tarih;
    int kul_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToplam_kalori() {
        return toplam_kalori;
    }

    public void setToplam_kalori(int toplam_kalori) {
        this.toplam_kalori = toplam_kalori;
    }

    public long getTarih() {
        return tarih;
    }

    public void setTarih(long tarih) {
        this.tarih = tarih;
    }

    public int getKul_id() {
        return kul_id;
    }

    public void setKul_id(int kul_id) {
        this.kul_id = kul_id;
    }
}
