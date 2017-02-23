package com.example.muslu.saglikasistani;

/**
 * Created by Muslu on 24.12.2016.
 */

public class adimBilgisi {
        private int id;
        private int adimSayi;
        private long tarih;
        private int kullanici_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSuMiktari() {
            return adimSayi;
        }

        public void setSuMiktari(int suMiktari) {
            this.adimSayi = suMiktari;
        }

        public long getTarih() {
            return tarih;
        }

        public void setTarih(long tarih) {
            this.tarih = tarih;
        }

        public int getKullanici_id() {
            return kullanici_id;
        }

        public void setKullanici_id(int kullanici_id) {
            this.kullanici_id = kullanici_id;
        }

}
