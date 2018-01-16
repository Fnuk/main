package com.istia.groupe.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cgachet on 08/01/2018.
 */

public class Plateau {

    private int[][] plateau; // Case vide : 0 ; Case bombe : -1 ; Case indic : {1,2,3,4,5,6,7,8}
    private ArrayList<CoordsXY> bombesListe; // Liste contenant les cases des bombes
    private ArrayList<CoordsXY> correctFlaggedCases; // Liste des cases BOMBES ayant un indicateur flag(bombe) placé par le joueur
    private ArrayList<CoordsXY> incorrectFlaggedCases; // Liste des cases NON BOMBES ayant un indicateur flag(bombe) placé par le joueur
    private int rows, cols;
    private int nbBombe;
    private String difficulty;

    private static Plateau instance = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Plateau getInstance()
    {
        if (instance == null)
        {   instance = new Plateau();
        }
        return instance;
    }

    private Plateau() {}

    public void init(int rows, int cols, int nbBombe, String dif) {
        // Création des caracs du plateau
        this.difficulty = dif;
        this.rows = rows;
        this.cols = cols;
        this.setNbBombe(nbBombe);
        bombesListe = new ArrayList<>();
        correctFlaggedCases = new ArrayList<>();
        incorrectFlaggedCases = new ArrayList<>();
        this.plateau = new int[rows][cols];
        for(int i=0; i<this.plateau.length; i++) {
            for(int j=0; j<this.plateau[i].length; j++) {
                this.plateau[i][j] = 0;
            }
        }
        // Initialisation des bombes
        Random randGen = new Random();
        int row, col;
        for(int i=0; i<nbBombe; i++) {
            do {
                row = randGen.nextInt(this.rows);
                col = randGen.nextInt(this.cols);
            }while(plateau[row][col] == -1);
            plateau[row][col] = -1;
            bombesListe.add(new CoordsXY(row, col));
        }

        // Initialisation des cases indic : parcours des cases bombes pour mettre à jour les cases adjacentes
        for(CoordsXY xy : bombesListe) {
            //Parcours des cases adjacentes
            for(int k=-1; k<2; k++) {
                for(int h=-1; h<2; h++) {
                    // k=0, h=0 -> case bombe
                    if(k==0 && h==0) {
                        continue;
                    } else {
                        try {
                            // Si la case est une bombe on ne fait rien sinon on ajoute 1 à la case
                            if (this.plateau[xy.getX() + k][xy.getY() + h] != -1) {
                                this.plateau[xy.getX() + k][xy.getY() + h] += 1;
                            }
                        } catch(ArrayIndexOutOfBoundsException ex) {
                            // Si la case est sur le bord
                            continue;
                        }
                    }
                }
            }
        }
    }

    public int[][] getSafeZone(int xpos, int ypos) {
        // Méthode prenant en entrée une case de coordonnées (x,y) et renvoyant sous forme de int[][]:
        // null si case entrée == -1
        // une seule case si 0 < case entrée < 9
        // un tableau de cases si case entrée == 0
        if(this.plateau[xpos][ypos] == 0) {
            ArrayList<CoordsXY> casesSafe = new ArrayList<>();
            getSafeNeighbours(xpos, ypos, casesSafe);
            return CoordsXY.List2Array(casesSafe);
        } else if(this.plateau[xpos][ypos] == -1) {
            return null;
        } else {
            int[][] safeZone = new int[1][2];
            safeZone[0][0] = xpos;
            safeZone[0][1] = ypos;
            return safeZone;
        }
    }

    private void getSafeNeighbours(int xpos, int ypos, ArrayList<CoordsXY> safeCoordslist) {
        CoordsXY xy;
        for(int k=-1; k < 2; k++) {
            for (int h = -1; h < 2; h++) {
                try {
                    xy = new CoordsXY(xpos+k, ypos+h);
                    if(!xy.inArray(safeCoordslist)) {
                        if(this.plateau[xpos+k][ypos+h] == 0) {
                            safeCoordslist.add(new CoordsXY(xpos+k, ypos+h));
                            getSafeNeighbours(xpos+k, ypos+h, safeCoordslist);
                        } else if(this.plateau[xpos+k][ypos+h] > 0 && this.plateau[xpos+k][ypos+h] < 9) {
                            safeCoordslist.add(new CoordsXY(xpos+k, ypos+h));
                        }
                    }
                } catch(ArrayIndexOutOfBoundsException ex) {

                    continue;
                }
            }
        }
    }

    public void placeBombFlag(int xpos, int ypos) {
        try {
            if (this.plateau[xpos][ypos] == -1) {
                correctFlaggedCases.add(new CoordsXY(xpos, ypos));
            } else {
                incorrectFlaggedCases.add(new CoordsXY(xpos, ypos));
            }
        } catch(IndexOutOfBoundsException ex) {}
    }

    public void removeBombFlag(int xpos, int ypos) {
        CoordsXY xy = new CoordsXY(xpos, ypos);
        try {
            if (this.plateau[xpos][ypos] == -1) {
                int index = xy.getXYCoordsIndexInArray(correctFlaggedCases);
                if (index != -1) {
                    correctFlaggedCases.remove(index);
                }
            } else {
                int index = xy.getXYCoordsIndexInArray(incorrectFlaggedCases);
                if (index != -1) {
                    correctFlaggedCases.remove(index);
                }
            }
        }catch(IndexOutOfBoundsException ex) {}
    }

    public boolean checkVictory() {
        if(correctFlaggedCases.size() == bombesListe.size() && incorrectFlaggedCases.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

//    GETTERS ; SETTERS

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(int[][] plateau) {
        this.plateau = plateau;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getNbBombe() {
        return nbBombe;
    }

    public void setNbBombe(int nbBombe) {
        if(nbBombe < this.rows * this.cols){
            this.nbBombe = nbBombe;
        } else {
            this.nbBombe = (int) this.rows * this.cols / 2;
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
