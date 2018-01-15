package com.istia.groupe.myapplication;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Dev on 14/01/2018.
 */

public class DemineurButton extends android.support.v7.widget.AppCompatImageButton {

    private int coordX;
    private int coordY;

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public DemineurButton(Context context) {
        super(context);
    }

    public DemineurButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemineurButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isMarked(){
        if(this.getDrawable() == null)
            return false;
        else
            return true;
    }


}
