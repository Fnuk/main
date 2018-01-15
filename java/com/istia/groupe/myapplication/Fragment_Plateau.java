package com.istia.groupe.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Plateau extends Fragment implements View.OnClickListener {

    private View view;
    private GridLayout gridDemineur = null;
    private int rows = 8;
    private int columns = 8;
    private int height, width, nbBombs;
    private Point size = new Point();
    private int clickChoice = 0;
    private int[][] plateau = Plateau.getInstance().getPlateau();
    private TextView bombsCounter;
    //List de button type DemineurButton
    private List<DemineurButton> casesDemineur = new ArrayList<>();
    private ImageButton handButton = null,
            flagButton = null,
            bombButton = null;

    public Fragment_Plateau() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //On récupère les infos depuis le plateau
        columns = Plateau.getInstance().getCols();
        rows = Plateau.getInstance().getRows();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment__plateau, container, false);

        //Getting the size of the screen
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        display.getSize(size);
        height = size.y;
        width = size.x;

        //Textview
        bombsCounter = (TextView) view.findViewById(R.id.BombsCounter);
        //Button instantiation
        handButton = (ImageButton)  view.findViewById(R.id.clickButton);
        flagButton = (ImageButton)  view.findViewById(R.id.flagButton);
        bombButton = (ImageButton)  view.findViewById(R.id.bombButton);
        //layout instantiation
        gridDemineur = (GridLayout) view.findViewById(R.id.GridLayoutDemineur);

        gridDemineur.setRowCount(rows);
        gridDemineur.setColumnCount(columns);

        //affichage du nombre initial de bombes
        nbBombs = Plateau.getInstance().getNbBombe();
        bombsCounter.setText(String.valueOf(nbBombs));

        //création des différentes cases du plateau (UI)
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                //La case de coordonnées x = j et y = i, matérialisé par un bouton
                final DemineurButton myButton = new DemineurButton(this.getContext());

                //On set les coordonnées du bouton et on l'ajoute à la liste
                myButton.setCoordX(j);
                myButton.setCoordY(i);
                casesDemineur.add(myButton);

                //On ajoute la fonction au bouton
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(clickChoice) {
                            case 0:
                                if(!myButton.isMarked()) {
                                    displaySquare(myButton.getCoordX(), myButton.getCoordY(), casesDemineur.indexOf(myButton));
                                }
                                break;
                            case 1:
                                if(!myButton.isMarked()) {
                                    //On affiche l'indicateur
                                    myButton.setImageResource(R.drawable.flag);
                                    nbBombs--;
                                }else{
                                    //On retire l'image
                                    myButton.setImageResource(0);
                                    nbBombs++;
                                }
                                break;
                            case 2:
                                if(!myButton.isMarked()) {
                                    myButton.setImageResource(R.drawable.question);
                                }else{
                                    myButton.setImageResource(0);
                                }
                                break;
                        }
                        bombsCounter.setText(String.valueOf(nbBombs));
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

    public void onClick(View view){
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


    public void displaySquare(int x, int y, int idx){
        switch(plateau[x][y]){
            case 0 :
                Space space = new Space(getContext());
                space.setBackgroundColor(Color.GREEN);
                casesDemineur.get(idx).setVisibility(View.INVISIBLE);
                gridDemineur.addView(space, gridDemineur.indexOfChild(casesDemineur.get(idx)));
                break;
            case -1 :
                ImageView image = new ImageView(getContext());
                image.setImageResource(R.drawable.bomb);
                image.setBackgroundColor(Color.RED );
                casesDemineur.get(idx).setVisibility(View.INVISIBLE);
                gridDemineur.addView(image, gridDemineur.indexOfChild(casesDemineur.get(idx)));
                break;
            default :
                TextView howManyBombs = new TextView(getContext());
                howManyBombs.setText(String.valueOf(plateau[x][y]));
                howManyBombs.setBackgroundColor(Color.GRAY);
                casesDemineur.get(idx).setVisibility(View.INVISIBLE);
                gridDemineur.addView(howManyBombs, gridDemineur.indexOfChild(casesDemineur.get(idx)));
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
