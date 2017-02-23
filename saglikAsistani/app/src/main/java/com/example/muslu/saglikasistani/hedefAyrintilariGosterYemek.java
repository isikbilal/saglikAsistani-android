package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class hedefAyrintilariGosterYemek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedef_ayrintilari_goster_yemek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void  Tiklandi6(View view) {
        if (view.getId() == R.id.hedefIptalYemek_id) {
            Intent intent = new Intent(getApplicationContext(), dahaSaglikliYiyin.class);
            startActivity(intent);
        }
    }
}
