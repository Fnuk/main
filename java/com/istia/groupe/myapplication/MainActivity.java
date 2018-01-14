package com.istia.groupe.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //      Test classe plateau
      Plateau plat = new Plateau(15, 15, 50);
      Log.i("PLATEAU", plat.toString());

      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      Fragment_Plateau fragment = new Fragment_Plateau();
      fragmentTransaction.add(R.id.fragment_container, fragment);
      fragmentTransaction.commit();



  }
}
