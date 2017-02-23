package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class yemekEkle extends AppCompatActivity {
    EditText yemekIsmi;
    EditText yemekKalori;
    Button ekle;
    veritabani veritabani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_ekle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        veritabani=new veritabani(this);
        yemekIsmi=(EditText)findViewById(R.id.yemek_ismi);
        yemekKalori=(EditText) findViewById(R.id.yemek_kalori_id);
        ekle= (Button) findViewById(R.id.yemek_ismi_ekle);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              veritabani.yemekEkle(yemekIsmi.getText().toString(), Integer.parseInt(yemekKalori.getText().toString()));
                Toast.makeText(yemekEkle.this,"Yemek eklendi!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SabahAtistirma.class);
                startActivity(intent);
            }
        });

    }


}
