package com.istia.groupe.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Title_Screen extends Fragment implements View.OnClickListener
{
  private View view;

  public  Title_Screen()
  {
    //Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_title_screen, container, false);

    Toolbar toolbar  = (Toolbar) view.findViewById(R.id.toolbar);
    if(toolbar != null)
    {
      ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    return view;
  }

  @Override
  public void onClick(View v) {

  }

  public Toolbar getToolbar()
  {
    Toolbar tb = (Toolbar) getView().findViewById(R.id.toolbar);

    if(tb != null) return tb;
    else return null;
  }
}
