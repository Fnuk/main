package com.istia.groupe.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plateau extends Fragment {

  View view;
  GridLayout gridDemineur = null;
  int rows = 8;
  int columns = 8;

  public Fragment_Plateau() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_fragment__plateau, container, false);

    gridDemineur = (GridLayout) view.findViewById(R.id.GridLayoutDemineur);
    gridDemineur.setRowCount(rows);
    gridDemineur.setColumnCount(columns);

    for(int i = 0; i < rows; i++){
      for(int j = 0; j < columns; j++){
        gridDemineur.addView(new Button(this.getContext()));
      }
    }

    return view;
  }

  public void setRows(int rows){
    this.rows = rows;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }
}
