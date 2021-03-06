package com.istia.groupe.myapplication;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Dev on 16/01/2018.
 */

public class CellAdapter extends BaseAdapter {

    private Context mContext;
    private List<DemineurButton> buttons;
    private int height;

    public CellAdapter(Context context, List<DemineurButton> buttons) {
        this.mContext = context;
        this.buttons = buttons;

        //Getting the size of the screen
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
    }

    @Override
    public int getCount() {
        return buttons.size();
    }

    @Override
    public Object getItem(int position) {
        return buttons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return buttons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //adapt size of cell to screen
        buttons.get(position).setScaleType(ImageView.ScaleType.CENTER);
        buttons.get(position).setMinimumHeight(((height/2)/((int)Math.sqrt(buttons.size())))+10);
        return buttons.get(position);
    }

    
}
