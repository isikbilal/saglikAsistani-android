package com.example.muslu.saglikasistani;

import android.content.Context;

/**
 * Created by Muslu on 15.01.2017.
 */

public class kronometre implements Runnable {
    public static  final  long dakika =60000;
    public static  final  long saat =3600000;

    private Context mcontext;
    private  long startTime;
    private boolean kos;

    public kronometre(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void start(){
        startTime=System.currentTimeMillis();
        kos=true;
    }
    public void stop(){
        kos=false;
    }

    @Override
    public void run() {
        while (kos){
            long sonuc=System.currentTimeMillis()-startTime;

            int saniye=(int)(((sonuc/1000))%60);
            int dakikalar=(int)((sonuc/(dakika))%60);
            int saatler=(int)((sonuc/(saat))%24);
            int millisaniye=(int)sonuc%1000;

            ((adimGrafigi)mcontext).guncelle(String.format("%02d:%02d:%02d:%03d",saatler,dakikalar,saniye,millisaniye));
        }
    }
}
