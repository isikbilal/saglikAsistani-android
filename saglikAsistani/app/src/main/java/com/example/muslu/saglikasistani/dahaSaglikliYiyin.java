package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import static com.example.muslu.saglikasistani.R.styleable.Toolbar;

public class dahaSaglikliYiyin extends AppCompatActivity {
    TextView tv;
    yemekBilgi yemekBilgi;
    veritabani veritabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daha_saglikli_yiyin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv = (TextView) findViewById(R.id.activity_daha_saglikli_yiyin_texVi);

        if(MainActivity.uyeAdi == null){
           return;
        }


        veritabani = new veritabani(this);
        yemekBilgi = veritabani.getSonYemekBilgisi(veritabani.getKullaniciId(MainActivity.uyeAdi));
        tv.setText(""+yemekBilgi.getToplam_kalori());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);


        if(yemekBilgi.getTarih() != calendar.getTimeInMillis()){
            veritabani.yemekBilgisiEkle(veritabani.getKullaniciId(MainActivity.uyeAdi),calendar.getTimeInMillis());
            yemekBilgi = veritabani.getSonYemekBilgisi(veritabani.getKullaniciId(MainActivity.uyeAdi));
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_yemek_ekle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.hedefAyrintilariGosterYemek) {
            Intent intent = new Intent(getApplicationContext(), hedefAyrintilariGosterYemek.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
    public void Tiklandi7(View view) {

        if (view.getId() == R.id.Satistirma_id) {
            Intent intent = new Intent(getApplicationContext(), SabahAtistirma.class);
            startActivity(intent);
        }

    }

}
