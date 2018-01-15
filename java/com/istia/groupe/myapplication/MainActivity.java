package com.istia.groupe.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_title_screen);
    //NE PAS EFFACER
    Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
    if(toolbar != null)
    {
      setSupportActionBar(toolbar);
    }
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    //FIN NE PAS EFFACER

//      Test classe plateau
      /*
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
      }*/



      /*FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      Title_Screen fragment = new Title_Screen();
      fragmentTransaction.add(R.id.fragment_container, fragment);
      fragmentTransaction.commit();*/



  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    getMenuInflater().inflate(R.menu.toolbar, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    int id = item.getItemId();
    switch(id)
    {
      case R.id.home_toolbarAction:
        Toast.makeText(this, "Home clicked", Toast.LENGTH_LONG).show();
        return true;
      case R.id.back_toolbarAction:
        Toast.makeText(this, "Back clicked", Toast.LENGTH_LONG).show();
        return true;
    }
    return true;
  }
}