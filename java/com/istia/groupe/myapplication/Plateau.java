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
        for(int i=0; i<this.plateau.length; i++) {
            for(int j=0; j<this.plateau[i].length; j++) {
                this.plateau[i][j] = 0;
            }
        }
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
            for(int k=-1; k<2; k++) {
                for(int h=-1; h<2; h++) {
                    if(k==0 && h==0) {
                        continue;
                    } else {
                        try {
                            if (this.plateau[tabCoordBombes[i][0] + k][tabCoordBombes[i][1] + h] != -1) {
                                this.plateau[tabCoordBombes[i][0] + k][tabCoordBombes[i][1] + h] += 1;
                            }
                        } catch(ArrayIndexOutOfBoundsException ex) {
                            continue;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String plat = "";
        for(int i=0; i<this.plateau.length; i++) {
            for(int j=0; j<this.plateau[i].length; j++) {
                if(this.plateau[i][j] == -1) {
                    plat = plat + " " + "B";
                } else if(this.plateau[i][j] == 0) {
                    plat = plat + " " + ".";
                } else {
                    plat = plat + " " + this.plateau[i][j];
                }

            }
            plat = plat + "\n";
        }
        return plat;
    }


}
