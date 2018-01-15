package com.istia.groupe.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

//      Test classe plateau
      Plateau plat = Plateau.getInstance();
      plat.init(4,4,3);
      Log.i("PLATEAU", plat.toString());
      for(int i=0; i<plat.getRows(); i++) {
          for(int j=0; j<plat.getRows(); j++) {
              int[][] tab = plat.getSafeZone(i, j);
              if(tab != null){
                  Log.i("SAFE POSITION", "("+i+","+j+")");
                  for(int k=0; k<tab.length; k++) {
                      Log.i("SAFE VOISIN",  "("+tab[k][0]+","+tab[k][1]+")");
                  }
              } else {
                  Log.i("BOMBE", "("+i+","+j+")");
              }

          }
      }



  }
}