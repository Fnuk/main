package com.istia.groupe.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cgachet on 16/01/2018.
 */

public class PreferenceManager {

    private static PreferenceManager instance = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized PreferenceManager getInstance()
    {
        if (instance == null)
        {   instance = new PreferenceManager();
        }
        return instance;
    }

    private PreferenceManager() {}

    public void saveScore(long time, String difficulty, Activity context) {
        time = time/1000;
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.high_score_save_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        ArrayList<Long> ranking = getHighScores(difficulty, context, 10);
        ranking.add(time);
        // On trie la nouvelle liste des classements
        Collections.sort(ranking);
        // on limite la taille du classement
//        if(ranking.size() >= maxNumberOfHighScore) {
//            ranking.remove(ranking.size()-1);
//        }
        for(int i=1;i<ranking.size();i++) {
            // Début à 1 car le -1L (pas de temps négatif donc -1L se retrouve en 1er)
            // par défaut a été ajouté à la liste
            // on ajoute les temps dans le fichier de préférences avec la clé corespondant à leur classement
            editor.putLong(difficulty+i, ranking.get(i));
        }
        editor.commit();
    }

    public ArrayList<Long> getHighScores(String dif, Activity context, int max) {
        String key;
        Long t = 0L;
        int place = 1;
        ArrayList<Long> ranking = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.high_score_save_file), Context.MODE_PRIVATE);

        if(!dif.equals("custom")) {
            // difficulty : facile, moyen, difficile
            while (!t.equals(-1L) && place < max) {
                // la clé prend la forme : facile1, facile2, facile3, ... (remplacer facile par la
                // difficulté choisie) ; le nombre étant le classement du temps (score) correspondant
                key = dif + place;
                // on récupère le temps correspondant à la clé
                // Si la clé n'existe pas, la valeur rendue par défaut est -1L : condition de sortie
                t = sharedPref.getLong(key, -1L);
                // ajout dans une liste temporaire des scores
                    ranking.add(t);
                // on incrémente la variable du classement pour l'itération suivante
                place++;
            }
//            ranking.remove(ranking.size()-1);
            return ranking;
        } else {
            return null;
        }
    }

    public void clearHighScores(Activity context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.high_score_save_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

}
