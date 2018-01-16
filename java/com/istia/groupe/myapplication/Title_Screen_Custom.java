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
import android.widget.EditText;
import android.widget.Toast;

public class Title_Screen_Custom extends Fragment implements View.OnClickListener
{

  EditText rowEditText, colEditText, bombEditText;

  public Title_Screen_Custom()
  {
    //Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view;
    Button playButton;

    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_title_screen_custom, container, false);
    setHasOptionsMenu(true);

    Toolbar toolbar  = (Toolbar) view.findViewById(R.id.toolbar_titlecustom);
    if(toolbar != null)
    {
      ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    playButton = (Button) view.findViewById(R.id.customplay_button);
    playButton.setOnClickListener(this);
    rowEditText = (EditText) view.findViewById(R.id.edittext_rows);
    colEditText = (EditText) view.findViewById(R.id.edittext_cols);
    bombEditText = (EditText) view.findViewById(R.id.edittext_bombs);

    return view;
  }

  @Override
  public void onClick(View v) {
    Plateau plat = Plateau.getInstance();

    String row, col, bombs;
    boolean isOneEmpty = true;

    switch (v.getId())
    {
      case R.id.customplay_button:
        row = rowEditText.getText().toString();
        col = colEditText.getText().toString();
        bombs = bombEditText.getText().toString();

        isOneEmpty = row.isEmpty() || col.isEmpty() || bombs.isEmpty();

        if(!isOneEmpty) plat.init(Integer.parseInt(row), Integer.parseInt(col), Integer.parseInt(bombs));
        else
        {
          Toast.makeText(getContext(), getResources().getText(R.string.pleasefillall), Toast.LENGTH_LONG).show();
          return;
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_Plateau fragment = new Fragment_Plateau();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        break;
    }
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
