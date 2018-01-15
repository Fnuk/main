package com.istia.groupe.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

//      Test classe plateau
      Plateau plat = Plateau.getInstance();
      plat.init(4,4,3);
      Log.i("PLATEAU", plat.toString());
      ArrayList<CoordsXY> arr = plat.getBombesListe();
      boolean hey = true;
      for(int i=0; i<arr.size(); i++){
          plat.placeBombFlag(arr.get(i).getX(), arr.get(i).getY());
          if(hey) {
              plat.placeBombFlag(0, 0);
              hey = false;
          }
      }
      Log.i("VICTOIRE", ""+plat.checkVictory());


      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      Fragment_Plateau fragment = new Fragment_Plateau();
      fragmentTransaction.add(R.id.fragment_container, fragment);
      fragmentTransaction.commit();



  }
}