package com.example.muslu.saglikasistani;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{
    public static String uyeAdi;
    private SensorManager sensorManager;
    public static TextView textView;
    boolean kos=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        textView=(TextView) findViewById(R.id.Txt_id);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }
    public void Tiklandi(View view) {
        if (view.getId() == R.id.yemek_id) {
            Intent intent = new Intent(getApplicationContext(), dahaSaglikliYiyin.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.kosu_id) {
            Intent intent = new Intent(getApplicationContext(), dahaAktifOlun.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.veri_id) {
            Intent intent = new Intent(getApplicationContext(), dahaDinlenmisHisset.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.adim_id){
            Intent intent = new Intent(getApplicationContext(), adimGrafigi.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.baslat_id){
            //Intent intent = new Intent(getApplicationContext(), kosu.class);
            //startActivity(intent);
        }
        if (view.getId()==R.id.yemek_ekle_id){
            Intent intent = new Intent(getApplicationContext(), dahaSaglikliYiyin.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.ogeleri_yonet_id){
            Intent intent = new Intent(getApplicationContext(), ogeleri_yonet.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.uykuEkle){
            Intent intent = new Intent(getApplicationContext(), dahaDinlenmisHisset.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saglik_uygulamasi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_ayarlar) {
            Intent intent = new Intent(getApplicationContext(), Ayarlar.class);
            startActivity(intent);

        }
        if (id == R.id.menu_ogeleriYonet) {
            Intent intent = new Intent(this, ogeleri_yonet.class);
            startActivity(intent);
        }
        if (id == R.id.kullanici_profili) {

            Intent intent = new Intent(getApplicationContext(), giris.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kos=true;
        Sensor sensorSayisi=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensorSayisi !=null){
            sensorManager.registerListener(this,sensorSayisi,SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(getApplicationContext(), "Sensör bulunamadı",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        kos=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (kos){
            textView.setText(String.valueOf(event.values[0]));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
