package com.istia.groupe.myapplication;

import java.util.ArrayList;

/**
 * Created by cgachet on 14/01/2018.
 */

public class CoordsXY {

    public static int[][] List2Array(ArrayList<CoordsXY> xyList) {
        int[][] xyArray = new int[xyList.size()][2];
        for(int i=0; i<xyList.size(); i++) {
            xyArray[i][0] = xyList.get(i).getX();
            xyArray[i][1] = xyList.get(i).getY();
        }
        return xyArray;
    }

    private int X;
    private int Y;

    public CoordsXY(int x, int y) {
        X=x;
        Y=y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean egal(CoordsXY xy) {
        if(xy.getX() == this.getX() && xy.getY() == this.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inArray(ArrayList<CoordsXY> arr) {
        for(CoordsXY xy : arr) {
            if(this.egal(xy)) {
                return true;
            }
        }
        return false;
    }

    public int getXYCoordsIndexInArray(ArrayList<CoordsXY> arr) {
        // Retourne l'index
        for(int i=0; i<arr.size(); i++) {
            if(this.egal(arr.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return "("+this.getX()+" , "+this.getY()+")";
    }
}
