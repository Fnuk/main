package com.istia.groupe.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


public class FragmentTableauHighScore extends Fragment {

    private static final String ARG_PARAM1 = "difficulty";
    private String difficulty;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int maxNumberOfHighScore = 25;
    private Long[] highScores;

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
        // specify an adapter

        adapter = new HighScoreAdapter(new String[] {"coucou"}); // Récuperer les données bia SharedPref
        recyclerView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Long> getHighScores(String dif) {
        String key;
        Long t = 0L;
        int place = 1;
        ArrayList<Long> ranking = new ArrayList<>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.high_score_save_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(!this.difficulty.equals("custom")) {
            // difficulty : facile, intermediaire, difficile
            while (!t.equals(-1L) && place <= maxNumberOfHighScore) {
                // la clé prend la forme : facile1, facile2, facile3, ... (remplacer facile par la
                // difficulté choisie) ; le nombre étant le classement du temps (score) correspondant
                key = difficulty + place;
                // on récupère le temps correspondant à la clé
                // Si la clé n'existe pas, la valeur rendue par défaut est -1L : condition de sortie
                t = sharedPref.getLong(key, -1L);
                // ajout dans une liste temporaire des scores
                ranking.add(t);
                // on incrémente la variable du classement pour l'itération suivante
                place++;
            }
            // On trie la nouvelle liste des classements pour voir où se trouve le nouvel élément
            Collections.sort(ranking);
            return ranking;
        } else {
            return null;
        }
    }

    public void saveScore(long time) {
        ArrayList<Long> ranking = new ArrayList<>();
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.high_score_save_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
            // on limite la taille du classement
        int size = (ranking.size() <= maxNumberOfHighScore) ? ranking.size() : maxNumberOfHighScore;
        for(int i=1;i<size;i++) {
            // Début à 1 car le -1L (pas de temps négatif donc -1L se retrouve en 1er)
            // par défaut a été ajouté à la liste
            // on ajoute les temps dans le fichier de préférences avec la clé corespondant à leur classement
            editor.putLong(difficulty+i, ranking.get(i));
        }
        editor.commit();
    }

}
