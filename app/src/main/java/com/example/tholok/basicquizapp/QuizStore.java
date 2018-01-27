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

        // append this quiz
        quizString = quizString.concat("|" + question.toPrefFormat());

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


        // DUMMY QUESTION FOR TESTING

        ArrayList<Question> quiz = new ArrayList<>();

        quiz.add(new Question(
                "What's the meaning of life?",
                "3",
                "2",
                "923",
                "42",
                3
        ));

        quiz.add(new Question(
                "What's 3+3?",
                "6",
                "3",
                "horse",
                "1",
                1
        ));

        return quiz;
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
