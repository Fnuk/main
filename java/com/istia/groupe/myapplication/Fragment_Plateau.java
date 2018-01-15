package com.istia.groupe.myapplication;


import android.content.Context;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plateau extends Fragment implements View.OnClickListener {

    private View view;
    private GridLayout gridDemineur = null;
    private int rows = 8;
    private int columns = 8;
    private int height, width;
    private Point size = new Point();
    private int clickChoice = 0;
    private ImageButton handButton = null,
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
        //layout instantiation
        gridDemineur = (GridLayout) view.findViewById(R.id.GridLayoutDemineur);

        gridDemineur.setRowCount(rows);
        gridDemineur.setColumnCount(columns);


        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                final ImageButton myButton = new ImageButton(this.getContext());
                myButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch(clickChoice) {
                            case 0:
                                break;
                            case 1:
                                if(myButton.isEnabled()) {
                                    myButton.setImageResource(R.drawable.flag);
                                    myButton.setEnabled(false);
                                }else{
                                    myButton.setImageResource(android.R.color.transparent);
                                    myButton.setEnabled(true);
                                }
                                break;
                            case 2:
                                if(myButton.isEnabled()) {
                                    myButton.setImageResource(R.drawable.bomb);
                                    myButton.setEnabled(false);
                                }else{
                                    myButton.setImageResource(android.R.color.transparent);
                                    myButton.setEnabled(true);
                                }
                                break;
                        }
                        return false;
                    }
                });
                gridDemineur.addView(myButton, (height/rows)/2, (width/columns)/2);
            }
        }

        handButton.setOnTouchListener(listenerSelectType);

        flagButton.setOnTouchListener(listenerSelectType);

        bombButton.setOnTouchListener(listenerSelectType);
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

    View.OnTouchListener listenerSelectType = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()){
                case (R.id.clickButton) :
                    handButton.setPressed(true);
                    flagButton.setPressed(false);
                    bombButton.setPressed(false);
                    clickChoice = 0;
                    break;
                case (R.id.flagButton) :
                    handButton.setPressed(false);
                    flagButton.setPressed(true);
                    bombButton.setPressed(false);
                    clickChoice = 1;
                    break;
                case (R.id.bombButton) :
                    handButton.setPressed(false);
                    flagButton.setPressed(false);
                    bombButton.setPressed(true);
                    clickChoice = 2;
                    break;
            }
            return true;
        }
    };


    public void setRows(int rows){
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
