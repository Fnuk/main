package com.istia.groupe.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cgachet on 15/01/2018.
 */

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder>{

    String[] dataSet = {"a", "b", "c", "d"};

    public static class HighScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public HighScoreViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public HighScoreAdapter(String[] datas) {
        this.dataSet = datas;
    }

    @Override
    public HighScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        HighScoreViewHolder vh = new HighScoreViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(HighScoreViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(dataSet[position]);

    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }


}
