package com.istia.groupe.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plateau extends Fragment {
  View view;
  GridLayout gridDemineur = null;
  public Fragment_Plateau() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_fragment__plateau, container, false);

    gridDemineur = (GridLayout) view.findViewById(R.id.GridLayoutDemineur);

    return view;
  }

}
