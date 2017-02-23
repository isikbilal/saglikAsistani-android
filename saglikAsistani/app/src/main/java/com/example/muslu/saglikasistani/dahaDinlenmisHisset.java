package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class dahaDinlenmisHisset extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daha_dinlenmis_hisset);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void TiklandiSaat(View view){
        if (view.getId()==R.id.saatIptal_id){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(getApplicationContext(), "İşlemi iptal ettiniz", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else if (view.getId()==R.id.saatKaydet){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(getApplicationContext(), "Kayıt Bşarılı", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }


}
