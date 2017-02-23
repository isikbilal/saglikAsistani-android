package com.example.muslu.saglikasistani;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class profiliDuzenle extends AppCompatActivity{
    Button kamera;
    private static final int CAMERA_REQUEST = 1888;
    ImageView resim_yeri;
    Button btnKayit;
    EditText eTextAd, eTextBoy, eTextKilo,eTextTarih;
    RadioButton radioButton, radioButton2;
    veritabani veritabani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profili_duzenle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        veritabani = new veritabani(this);

        eTextAd = (EditText) findViewById(R.id.ad_id);
        eTextBoy = (EditText) findViewById(R.id.boyunuz_id);
        eTextKilo = (EditText) findViewById(R.id.kilonuz_id);
        eTextTarih = (EditText) findViewById(R.id.dog_id);
        radioButton = (RadioButton) findViewById(R.id.bay_id);
        radioButton2 = (RadioButton) findViewById(R.id.bayan_id);
        kamera= (Button) findViewById(R.id.kamera_id);
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
         resim_yeri=(ImageView)findViewById(R.id.resim_atma_yeri_id);

        btnKayit = (Button) findViewById(R.id.kaydet_id);
        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date;
                try {
                    date = format.parse(eTextTarih.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(profiliDuzenle.this,"23.11.1015 gibi girin", Toast.LENGTH_SHORT);
                    e.printStackTrace();
                    return;
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                Log.i("tarihhhh",calendar.get(Calendar.DAY_OF_MONTH)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.YEAR));


                long sonuc = veritabani.kaydol(

                        eTextAd.getText().toString(),
                        (radioButton.isChecked()) ? 0:1,
                        Integer.parseInt(eTextBoy.getText().toString()),
                        Integer.parseInt(eTextKilo.getText().toString()),
                        calendar.getTimeInMillis()

                );

                if(sonuc != -1){
                    Intent intent = new Intent(profiliDuzenle.this, Kullanici_profili.class);
                    intent.putExtra("id",(int)sonuc);
                    intent.putExtra("ad",eTextAd.getText().toString());
                    startActivity(intent);
                    finish();
                   Toast.makeText(profiliDuzenle.this,"Kullanıcı eklendi!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(profiliDuzenle.this,"Kullanıcı eklenemedi!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void  Tiklandi3(View view) {
        if (view.getId() == R.id.profili_iptal_id) {
            Intent intent = new Intent(getApplicationContext(), Kullanici_profili.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            resim_yeri.setImageBitmap(photo);
        }
    }
}
