package com.istia.groupe.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentTableauHighScore extends Fragment {

    private static final String ARG_PARAM1 = "difficulty"; // facile, moyen, difficile
    private String difficulty;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int maxNumberOfHighScore = 10;
    private ArrayList<Long> highScores;
    private ImageButton leftArrow, rightArrow;
    private TextView txtViewDifficulty;

    public static FragmentTableauHighScore newInstance(String difficulty) {
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, difficulty);
        FragmentTableauHighScore fragment = new FragmentTableauHighScore();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTableauHighScore() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.difficulty = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tableau_high_score, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tab_high_score_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Toolbar
        setHasOptionsMenu(true);

        Toolbar toolbar  = (Toolbar) view.findViewById(R.id.toolbar_highscore);
        if(toolbar != null)
        {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // specify an adapter
        this.difficulty = "moyen";
        highScores = PreferenceManager.getInstance()
                .getHighScores(this.difficulty, getActivity(), maxNumberOfHighScore);
        highScores.remove(highScores.size()-1);
        adapter = new HighScoreAdapter(highScores);
        recyclerView.setAdapter(adapter);

        txtViewDifficulty = (TextView) view.findViewById(R.id.tab_high_score_difficulty);
        txtViewDifficulty.setText(getActivity().getString(R.string.mediummode_button));
        leftArrow = (ImageButton) view.findViewById(R.id.tab_high_score_btn_left_arrow);
        rightArrow = (ImageButton) view.findViewById(R.id.tab_high_score_btn_right_arrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (difficulty) {
                    case "moyen":
                        difficulty = "facile";
                        highScores = PreferenceManager.getInstance()
                                .getHighScores(difficulty, getActivity(),maxNumberOfHighScore);
                        txtViewDifficulty.setText(getActivity().getString(R.string.easymode_button));
                        break;
                    case "difficile":
                        difficulty = "moyen";
                        highScores = PreferenceManager.getInstance()
                                .getHighScores(difficulty, getActivity(), maxNumberOfHighScore);
                        adapter.notifyDataSetChanged();
                        txtViewDifficulty.setText(getActivity().getString(R.string.mediummode_button));
                        break;
                }
                highScores.remove(highScores.size()-1);
                adapter = new HighScoreAdapter(highScores);
                recyclerView.setAdapter(adapter);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (difficulty) {
                    case "facile":
                        difficulty = "moyen";
                        highScores = PreferenceManager.getInstance()
                                .getHighScores(difficulty, getActivity(), maxNumberOfHighScore);
                        adapter.notifyDataSetChanged();
                        txtViewDifficulty.setText(getActivity().getString(R.string.mediummode_button));
                        Log.i("TAG", ""+System.currentTimeMillis());
                        break;
                    case "moyen":
                        difficulty = "difficile";
                        highScores = PreferenceManager.getInstance()
                                .getHighScores(difficulty, getActivity(), maxNumberOfHighScore);
                        adapter.notifyDataSetChanged();
                        txtViewDifficulty.setText(getActivity().getString(R.string.hardmode_button));
                        break;
                }
                highScores.remove(highScores.size()-1);
                adapter = new HighScoreAdapter(highScores);
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_title_highscore, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.back_hstoolbarAction:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Title_Screen fragment = new Title_Screen();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;

            case R.id.reset_hstoolbarAction:
                PreferenceManager.getInstance().clearHighScores(getActivity());
                adapter = new HighScoreAdapter(highScores);
                recyclerView.setAdapter(adapter);
                break;
        }
        return true;
    }

}
