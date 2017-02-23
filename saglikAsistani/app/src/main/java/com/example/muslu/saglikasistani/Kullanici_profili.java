package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;

public class Kullanici_profili extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_profili);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            Intent intent = new Intent(this,giris.class);
            startActivity(intent);
            return;
        }

        String kullaniciAdi = bundle.getString("ad");
        veritabani veritabani = new veritabani(this);
        kullanici kullanici = new kullanici();
        kullanici = veritabani.getKullanici(kullaniciAdi);

        if(kullanici==null){
            Intent intent = new Intent(this,profiliDuzenle.class);
            startActivity(intent);
            return;
        }
        MainActivity.uyeAdi = kullanici.getAd();

        listView = (ListView) findViewById(R.id.liste_id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(kullanici.getTarih());


        String[] dizi ={
                "Adınız:"+kullanici.getAd(),
                "Boyunuz:"+kullanici.getBoy(),
                "Cinsiyet:"+((kullanici.getCinsiyet()==0)? "Bay":"Bayan"),
                "Doğum tarihi: "+calendar.get(Calendar.DAY_OF_MONTH)+"."+((int)calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR),
                "Kilonuz:"+kullanici.getKilo()

        };
        listView.setAdapter(new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,dizi));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), profiliDuzenle.class);
                startActivity(intent);
            }
        });


    }
    public void  Tiklandi2(View view){
        if (view.getId() == R.id.liste_id) {
            Intent intent = new Intent(getApplicationContext(), profiliDuzenle.class);
            startActivity(intent);
        }
    }

}