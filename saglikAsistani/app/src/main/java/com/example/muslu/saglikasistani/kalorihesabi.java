package com.example.muslu.saglikasistani;

import android.content.Context;

/**
 * Created by Muslu on 16.01.2017.
 */

public class kalorihesabi {
    private Context mcontext;
    private  int boy,kilo,yas;
    private static String cinsiyet;
    private double  idealkilo=0;

    public kalorihesabi(Context mcontext, int boy, int kilo, int yas, double idealkilo) {
        this.mcontext = mcontext;
        this.boy = boy;
        this.kilo = kilo;
        this.yas = yas;
        this.idealkilo = idealkilo;
    }

    public void hesaplama(){
      if (cinsiyet=="Bayan")
      {
          idealkilo = (boy - 100 + yas / 10) * 0.8;
      }
      if (cinsiyet=="Bay")
      {
          idealkilo = (boy - 100 + yas / 10) * 0.9;
      }

  }


}
