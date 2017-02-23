package com.example.muslu.saglikasistani;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class adimGrafigi extends AppCompatActivity implements SensorEventListener {
   private Button startButon,stopButon,butonSonuclarim;
   private TextView Textsonuc;
    private Context mContext;
    private  kronometre mKronometre;
    private  Thread mThread;
    private EditText mEtLaps;
    private ScrollView mSvLaps;
    private SensorManager sensorManager;
    public static TextView textView;

    boolean kos=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adim_grafigi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       //sensor
        textView=(TextView) findViewById(R.id.aldigi_yol);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        // kronometre
        mContext=this;
        mEtLaps=(EditText)findViewById(R.id.et_laps);
        mSvLaps= (ScrollView) findViewById(R.id.sv_laps);
        startButon = (Button) findViewById(R.id.start_id);
        stopButon = (Button) findViewById(R.id.stop_id);
        butonSonuclarim=(Button)findViewById(R.id.btnSonuclar_id);
        Textsonuc = (TextView) findViewById(R.id.kro_sonuc_id);
        mEtLaps.setEnabled(false);
        startButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKronometre==null){
                    mKronometre=new kronometre(mContext);
                    mThread=new Thread(mKronometre);
                    mThread.start();
                    mKronometre.start();
                    mEtLaps.setText("");

                }
            }
        });
        stopButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKronometre!=null){
                    mKronometre.stop();
                    mThread.interrupt();
                    mThread=null;
                    mKronometre=null;
                    NotificationManager bidirimYapici=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    Notification.Builder builder=new Notification.Builder(adimGrafigi.this);
                    builder.setContentTitle("Sağlık Asistanı");
                    builder.setContentText(String.valueOf(Textsonuc.getText())+"\n"+"Alınan yol:"+String.valueOf(textView.getText())+"\n");
                    builder.setSmallIcon(R.drawable.icon);
                    builder.setAutoCancel(true);
                    builder.setTicker("Bildirim Geldi");
                    Intent ıntent=new Intent(adimGrafigi.this,MainActivity.class);
                    PendingIntent pendingIntent=PendingIntent.getActivity(adimGrafigi.this,1,ıntent,0);
                    builder.setContentIntent(pendingIntent);
                    Notification notification=builder.getNotification();
                    bidirimYapici.notify(1,notification);

                }

            }
        });
        butonSonuclarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKronometre==null)
                {
                    return;
                }

               mEtLaps.append(String.valueOf(Textsonuc.getText())+"\n"+"Alınan yol:"+String.valueOf(textView.getText())+"\n");

                mSvLaps.post(new Runnable() {
                    @Override
                    public void run() {
                        mSvLaps.smoothScrollTo(0,mEtLaps.getBottom());
                    }
                });
            }
        });
    }
    public void guncelle(final String zaman){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Textsonuc.setText(zaman);
            }
        });
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
