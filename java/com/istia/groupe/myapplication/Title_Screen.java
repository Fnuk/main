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
    view = inflater.inflate(R.layout.fragment_fragment__plateau, container, false);

    return view;
  }

  @Override
  public void onClick(View v) {

  }
}
