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
    setContentView(R.layout.activity_main);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Title_Screen fragment = new Title_Screen();
    //Fragment_Plateau fragment = new Fragment_Plateau();
    fragmentTransaction.add(R.id.fragment_container, fragment);
    fragmentTransaction.commit();

    //NE PAS EFFACER
    /*Toolbar toolbar  = fragment.getToolbar();
    if(toolbar != null)
    {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }*/
    //FIN NE PAS EFFACER
  }

  //NE PAS EFFACER
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
        Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.back_toolbarAction:
        Toast.makeText(this, "Back clicked", Toast.LENGTH_SHORT).show();
        return true;
    }
    return true;
  }
  //FIN NE PAS EFFACER
}