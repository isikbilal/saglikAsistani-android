package com.example.muslu.saglikasistani;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Muslu on 23.12.2016.
 */

public class veritabani extends SQLiteOpenHelper {

    private static String VT_Adi = "veritabani";
    private static int version=1;
    private String table_kullanicilar = "kullanicilar";
    private String table_adım_bigisi = "adim_bilgisi";
    private String table_yemek_bilgi = "yemek_bilgi";
    private String table_yemek_liste = "yemek_liste";

    private Context context;

    public veritabani(Context context) {
        super(context, VT_Adi, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sorgu = "create table "+table_kullanicilar+" (id integer primary key AUTOINCREMENT, ad varchar(50) not null," +
                "cinsiyet integer default 0, boy int not null, kilo integer not null, tarih integer not null)";

        db.execSQL(sorgu);

      // sorgu = "create table "+table_adım_bigisi+" (id integer primary key AUTOINCREMENT, adim_sayisi integer not null, tarih integer not null, kullanici_id integer not null)";
        //db.execSQL(sorgu);

        sorgu = "create table "+table_yemek_liste+" (id integer primary key AUTOINCREMENT, yemek_ismi varchar(50) not null, kalori integer not null)";
        db.execSQL(sorgu);

        sorgu = "create table "+table_yemek_bilgi+" (id integer primary key AUTOINCREMENT, toplam_kalori integer default 0 not null,tarih integer not null, kul_id integer not null)";
        db.execSQL(sorgu);


        ContentValues values = new ContentValues();
        values.put("yemek_ismi","yumurta");
        values.put("kalori",155);

        db.insert(table_yemek_liste,null,values);

        values = new ContentValues();
        values.put("yemek_ismi","100g zeytin");
        values.put("kalori",115);

        db.insert(table_yemek_liste,null,values);

        values = new ContentValues();
        values.put("yemek_ismi","100g peynir");
        values.put("kalori",115);

        db.insert(table_yemek_liste,null,values);

       // sorgu = "create table "+table_yemek_bilgi+" (id integer primary key AUTOINCREMENT, kalori integer not null, protein integer not null, yag integer not null," +
               // "karbonhidrat integer not null, tarih integer not null, kullanici_id integer not null)";
      //  db.execSQL(sorgu);

    }

    public void yemekEkle(String yemekAdi, int kalori){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("yemek_ismi",yemekAdi);
        values.put("kalori",kalori);

        db.insert(table_yemek_liste,null,values);
    }

    public List<yemekIcerik> getAllYemek(){
        SQLiteDatabase db = getWritableDatabase();

        List<yemekIcerik> yemekler = new ArrayList<yemekIcerik>();

        String sorgu = "select * from "+table_yemek_liste;
        Cursor cursor = db.rawQuery(sorgu,null);

        while (cursor.moveToNext()){
            yemekIcerik yemekIcerik = new yemekIcerik();
            yemekIcerik.setId(cursor.getInt(0));
            yemekIcerik.setAd(cursor.getString(1));
            yemekIcerik.setKalori(cursor.getInt(2));

            yemekler.add(yemekIcerik);
        }

        return yemekler;
    }

    public long kaydol(String ad, int cinsiyet, int boy, int kilo, long tarih){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ad",ad);
        contentValues.put("cinsiyet",cinsiyet);
        contentValues.put("boy",boy);
        contentValues.put("kilo",kilo);
        contentValues.put("tarih",tarih);

        long sonuc_id = db.insert(table_kullanicilar,null,contentValues);

        if(sonuc_id==-1){
            return sonuc_id;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        yemekBilgisiEkle((int)sonuc_id,calendar.getTimeInMillis());
        return sonuc_id;
    }

    public void yemekBilgisiEkle(int kul_id, long tarih ){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("kul_id",kul_id);
        contentValues.put("tarih",tarih);

        long sonuc = db.insert(table_yemek_bilgi,null,contentValues);
    }

    public yemekBilgi getSonYemekBilgisi(int kul_id ){
        SQLiteDatabase db = getWritableDatabase();

        String sorgu = "select * from "+table_yemek_bilgi+" where kul_id="+kul_id+" order by id desc limit 1";
        Cursor cursor = db.rawQuery(sorgu,null);

        yemekBilgi yemekBilgi = null;
        if(cursor.moveToNext()){
            yemekBilgi = new yemekBilgi();
            yemekBilgi.setId(cursor.getInt(0));
            yemekBilgi.setToplam_kalori(cursor.getInt(1));
            yemekBilgi.setTarih(cursor.getLong(2));
            yemekBilgi.setKul_id(cursor.getInt(3));
        }
        return yemekBilgi;
    }

    public void yemekBilgisiGuncelle(int yemek_bilgi_id, int kalori){
        SQLiteDatabase db = getWritableDatabase();

        String sorgu = "update "+table_yemek_bilgi+" set toplam_kalori= toplam_kalori + "+kalori+" where id="+yemek_bilgi_id;
        db.execSQL(sorgu);

    }

    public int getKullaniciId(String adi){
        SQLiteDatabase db = getWritableDatabase();

        String sorgu = "select id from "+table_kullanicilar+" where ad='"+adi+"'";

        Cursor cursor = db.rawQuery(sorgu,null);

        if(cursor.moveToNext()){
            return cursor.getInt(0);
        }
        else
            return -1;

    }

    public long adimBigisiEkle(int adimSayi, int tarih, int kul_id){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("adimSayi",adimSayi);
        contentValues.put("tarih",tarih);
        contentValues.put("kullanici_id",kul_id);

        long sonuc= db.insert(table_adım_bigisi,null,contentValues);

        return sonuc;
    }
    public adimBilgisi getSonSuBilgisi(int kul_id){
        SQLiteDatabase db = getWritableDatabase();

        String sorgu = "select * from "+table_adım_bigisi+" where kullanici_id="+kul_id+" order by id desc limit 1";

        Cursor cursor = db.rawQuery(sorgu,null);

        if(cursor.moveToNext()){
            adimBilgisi adimBilgisi = new adimBilgisi();
            adimBilgisi.setId(cursor.getInt(0));
            adimBilgisi.setSuMiktari(cursor.getInt(1));
            adimBilgisi.setTarih(cursor.getLong(2));
            adimBilgisi.setKullanici_id(cursor.getInt(3));

            return adimBilgisi;
        }
        return null;
    }

    public void adimSAyisiniGuncelle(int adim_id, int adim_sayisi) {
        SQLiteDatabase db = getWritableDatabase();

        String sorgu = "update " + table_adım_bigisi + " set adimSayi= adim_sayi+" + adim_sayisi + " where id=" + adim_id;
        db.execSQL(sorgu);
    }
    public kullanici getKullanici(String ad){
        SQLiteDatabase db = getWritableDatabase();
        String sorgu = "select * from "+table_kullanicilar+" where ad='"+ad+"'";
        Cursor cursor = db.rawQuery(sorgu,null);

        kullanici kullanici = null;

        if(cursor.moveToNext()){
            kullanici = new kullanici();
            kullanici.setId(cursor.getInt(0));
            kullanici.setAd(cursor.getString(1));
            kullanici.setCinsiyet(cursor.getInt(2));
            kullanici.setBoy(cursor.getInt(3));
            kullanici.setKilo(cursor.getInt(4));
            kullanici.setTarih(cursor.getLong(5));
        }

        return kullanici;
    }
        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
