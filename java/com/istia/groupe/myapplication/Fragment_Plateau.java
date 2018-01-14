package com.istia.groupe.myapplication;


import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plateau extends Fragment {

    View view;
    GridLayout gridDemineur = null;
    int rows = 8;
    int columns = 8;
    int height, width;
    Point size = new Point();
    int clickChoice = 0;
    ImageButton handButton = null,
            flagButton = null,
            bombButton = null;


    public Fragment_Plateau() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment__plateau, container, false);

        //Getting the size of the screen
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
        height = size.y;
        width = size.x;

        //Button instantiation
        handButton = (ImageButton)  view.findViewById(R.id.clickButton);
        flagButton = (ImageButton)  view.findViewById(R.id.flagButton);
        bombButton = (ImageButton)  view.findViewById(R.id.bombButton);
        //layout instatiation
        gridDemineur = (GridLayout) view.findViewById(R.id.GridLayoutDemineur);

        gridDemineur.setRowCount(rows);
        gridDemineur.setColumnCount(columns);


        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                ImageButton myButton = new ImageButton(this.getContext());
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                gridDemineur.addView(myButton, (height/rows)/2, (width/columns)/2);
            }
        }

        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChoice = 0;
            }
        });

        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChoice = 1;
            }
        });

        bombButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChoice = 2;
            }
        });
        return view;
    }

    public void onClick(final View view){
        switch(clickChoice){
            case 0:
                break;
            case 1:
                //imgButton.setImageResource(R.drawable.flag);
                break;
            case 2:
                //imgButton.setImageResource(R.drawable.bomb);
                break;
        }


    }


    public void setRows(int rows){
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
