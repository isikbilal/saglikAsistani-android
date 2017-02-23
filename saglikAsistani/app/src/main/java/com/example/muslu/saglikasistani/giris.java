package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class giris extends AppCompatActivity {

    EditText adEditText;
    Button girisButton;
    Button kayitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adEditText = (EditText) findViewById(R.id.activity_giris_ad_editText);
        girisButton = (Button) findViewById(R.id.activity_giris_button);
        kayitButton = (Button) findViewById(R.id.activity_kayit_button);
        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent=new Intent(giris.this,Kullanici_profili.class);
                ıntent.putExtra("ad", adEditText.getText().toString());
                startActivity(ıntent);
            }
        });

        kayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(giris.this, profiliDuzenle.class);
                startActivity(intent);

            }
        });


    }
}
