package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SabahAtistirma extends AppCompatActivity {

    EditText kaloriEditText;
    Button kaydetButton;
    Spinner yemekListe;
    List<yemekIcerik> yemekler;
    veritabani veritabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabah_atistirma);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        veritabani = new veritabani(this);

        kaloriEditText = (EditText) findViewById(R.id.activity_sabah_atistirma_kalori_editText);
        yemekListe = (Spinner) findViewById(R.id.activity_yemek_liste_spinner);
        kaydetButton = (Button) findViewById(R.id.activity_sabah_atistirma_kaydet);
        yemekler = veritabani.getAllYemek();


        yemekListe.setAdapter(new ArrayAdapter<yemekIcerik>(this,R.layout.support_simple_spinner_dropdown_item,yemekler));

        yemekListe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yemekIcerik yemekIcerik = yemekler.get(position);
                kaloriEditText.setText(""+yemekIcerik.getKalori());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if(MainActivity.uyeAdi == null)
            return;

        yemekBilgi yemekBilgi = veritabani.getSonYemekBilgisi(veritabani.getKullaniciId(MainActivity.uyeAdi));



        final yemekBilgi finalYemekBilgi = yemekBilgi;
        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veritabani.yemekBilgisiGuncelle(finalYemekBilgi.getId(), Integer.parseInt(kaloriEditText.getText().toString()));
                Toast.makeText(SabahAtistirma.this,"Yemek eklendi!", Toast.LENGTH_SHORT).show();

            }
        });



    }
    public void Tiklandi9(View view) {
        if (view.getId() == R.id.SAIptal_id) {
            Intent intent = new Intent(getApplicationContext(), dahaSaglikliYiyin.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.activity_yemek_ekle) {
            Intent intent = new Intent(getApplicationContext(), yemekEkle.class);
            startActivity(intent);
        }
    }
}
