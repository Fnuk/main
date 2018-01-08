package com.istia.groupe.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cgachet on 08/01/2018.
 */

public class Plateau {

    private int[][] plateau; // Case vide : 0 ; Case bombe : -1 ; Case indic : {1,2,3,4,5,6,7,8}
    private int[][] tabCoordBombes; // Tableau de dimensions [nbBombe][2] contenant coord des bombes
    private int rows, cols;
    private int nbBombe;


    public Plateau() {
        this.nbBombe = 15;
        tabCoordBombes = new int[this.nbBombe][2];
        this.rows = 10;
        this.cols = 10;
        this.plateau = new int[rows][cols];
        this.init();
    }

    public Plateau(int rows, int cols, int nbBombe) {
        this.nbBombe = nbBombe;
        tabCoordBombes = new int[this.nbBombe][2];
        this.rows = rows;
        this.cols = cols;
        this.plateau = new int[rows][cols];
        this.init();
    }

    private void init() {
        // Initialisation des bombes
        Random randGen = new Random();
        int row, col;
        for(int i=0; i<nbBombe; i++) {
            do {
                row = randGen.nextInt(this.rows);
                col = randGen.nextInt(this.cols);
            }while(plateau[row][col] == -1);
            plateau[row][col] = -1;
            tabCoordBombes[i][0] = row;
            tabCoordBombes[i][1] = col;
        }
        // Initialisation des cases indic
        for(int i=0; i<tabCoordBombes.length; i++) {

        }

    }


}
