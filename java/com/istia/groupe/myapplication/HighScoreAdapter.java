package com.istia.groupe.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cgachet on 15/01/2018.
 */

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder>{

    ArrayList<Long> times;

    public static class HighScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewNum;
        public TextView textViewTime;
        public HighScoreViewHolder(View v) {
            super(v);
            this.textViewNum = (TextView) v.findViewById(R.id.text_view_high_score_number);
            this.textViewTime = (TextView) v.findViewById(R.id.text_view_high_score_time);
        }
    }

    public HighScoreAdapter(ArrayList<Long> datas) {
        this.times = datas;
    }

    @Override
    public HighScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_high_score_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        HighScoreViewHolder vh = new HighScoreViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(HighScoreViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewTime.setText(Long.toString(times.get(position)));
        holder.textViewNum.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return times.size();
    }


}
