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

public class Title_Screen extends Fragment implements View.OnClickListener
{
  public  Title_Screen()
  {
    //Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view;
    Button playButton, highscoresButton;
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_title_screen, container, false);
    setHasOptionsMenu(true);

    Toolbar toolbar  = (Toolbar) view.findViewById(R.id.toolbar_title);
    if(toolbar != null)
    {
      ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    playButton = (Button) view.findViewById(R.id.play_titleButton);
    playButton.setOnClickListener(this);
    highscoresButton = (Button) view.findViewById(R.id.highscores_titleButton);
    highscoresButton.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId())
    {
      case R.id.play_titleButton:
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Title_Screen_ChooseDif fragment = new Title_Screen_ChooseDif();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        break;
      case R.id.highscores_titleButton:
        Toast.makeText(getContext(), "Score clicked", Toast.LENGTH_SHORT).show();
        break;
    }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.toolbar_title, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId()) {
      case R.id.home_toolbarAction:
        break;
    }
    return true;
  }
}
