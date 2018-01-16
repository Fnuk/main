package com.istia.groupe.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Title_Screen_ChooseDif extends Fragment implements View.OnClickListener
{
  public Title_Screen_ChooseDif()
  {
    //Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view;
    Button easyButton, mediumButton, hardButton, customButton;

    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_title_screen_choosedifficulty, container, false);
    setHasOptionsMenu(true);

    Toolbar toolbar  = (Toolbar) view.findViewById(R.id.toolbar_titledif);
    if(toolbar != null)
    {
      ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    easyButton = (Button) view.findViewById(R.id.easy_titleButton);
    easyButton.setOnClickListener(this);
    mediumButton = (Button) view.findViewById(R.id.medium_titleButton);
    mediumButton.setOnClickListener(this);
    hardButton = (Button) view.findViewById(R.id.hard_titleButton);
    hardButton.setOnClickListener(this);
    customButton = (Button) view.findViewById(R.id.custom_titleButton);
    customButton.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    Plateau plat = Plateau.getInstance();

    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    switch (v.getId())
    {
      case R.id.easy_titleButton:
        plat.init(8, 8, 10, "facile");
        break;
      case R.id.medium_titleButton:
        plat.init(16, 16, 40, "moyen");
          Toast.makeText(getContext(), "ça se corse... surtout visuellement", Toast.LENGTH_SHORT).show();
        break;
      case R.id.hard_titleButton:
        plat.init(32, 16, 99, "difficile");
          Toast.makeText(getContext(), "A vos risques et périls...n'oubliez pas de scroller", Toast.LENGTH_SHORT).show();
        break;
      case R.id.custom_titleButton:
        fragmentTransaction = fragmentManager.beginTransaction();
        Title_Screen_Custom fragment_titlecustom = new Title_Screen_Custom();
        fragmentTransaction.replace(R.id.fragment_container, fragment_titlecustom);
        fragmentTransaction.commit();
        return;
    }
    fragmentTransaction = fragmentManager.beginTransaction();
    Fragment_Plateau fragment_plateau = new Fragment_Plateau();
    fragmentTransaction.replace(R.id.fragment_container, fragment_plateau);
    fragmentTransaction.commit();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.toolbar_title_choosedif, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId()) {
      case R.id.back_toolbarAction:
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Title_Screen fragment = new Title_Screen();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        break;
    }
    return true;
  }
}
