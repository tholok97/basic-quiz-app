package com.example.tholok.basicquizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Store for questions. Provides methods for storing and retrieving quizzes
 */

public class QuizStore {

    private static final String QUIZ_PREF_NAME = "quiz";
    private static final String QUIZ_PREF_FILE = "quiz_preferences";

    /**
     * Store new quiz question
     * @param question
     * @param ctx Context to take preferenes from (temporary work-around)
     * @return True on success
     */
    static boolean storeQuiz(Question question, Context ctx) {

        // get pref and editor
        SharedPreferences prefs = ctx.getSharedPreferences(QUIZ_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // get quiz string from prefs
        String quizString = prefs.getString(QUIZ_PREF_NAME, "");

        // add | if not empty
        if (!quizString.isEmpty()) {
            quizString = quizString.concat("|");
        }

        // append this quiz
        quizString = quizString.concat(question.toPrefFormat());

        // store it back again
        editor.putString(QUIZ_PREF_NAME, quizString);
        editor.apply();

        return true;
    }

    /**
     * Load quiz and return all questions in store
     * @param ctx Context to take preferenes from (temporary work-around)
     * @return
     */
    static ArrayList<Question> loadQuiz(Context ctx) {

        // arrayliist to fill with quizzes
        ArrayList<Question> quiz = new ArrayList<>();

        // get pref string
        SharedPreferences prefs = ctx.getSharedPreferences(QUIZ_PREF_FILE, Context.MODE_PRIVATE);
        String quizString = prefs.getString(QUIZ_PREF_NAME, "");

        // if empty -> early return
        if (quizString.isEmpty()) {
            return quiz;
        }

        // PARSE:

        // split on | (per question)
        String[] questions = quizString.split("\\|");


        // DEBUG
        Log.d("question", "questions incomming.. (" + quizString + ")");

        // build quiz from questions
        for (String questionString : questions) {

            // split on & (per property)
            String properties[] = questionString.split("&");

            // DEBUG
            Log.d("question", ">" + questionString);
            for (String propertyString : properties) {
                // DEBUG
                Log.d("question", ">>" + propertyString);
            }

            // build questino from properties
            Question question = new Question(
                    properties[0],
                    properties[1],
                    properties[2],
                    properties[3],
                    properties[4],
                    Integer.parseInt(properties[5])
            );

            // add question to quiz
            quiz.add(question);
        }

        // DEBUG
        Log.d("question", "questions done..");

        return quiz;
    }

    static boolean isEmpty(Context ctx) {
        return loadQuiz(ctx).isEmpty();
    }

    /**
     * Clear preferences to delete quiz store
     * @param ctx
     */
    static void clearQuiz(Context ctx) {

        // wipe preferences clean
        SharedPreferences prefs = ctx.getSharedPreferences(QUIZ_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
