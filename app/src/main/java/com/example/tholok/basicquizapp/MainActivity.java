package com.example.tholok.basicquizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Swith to quiz activity
     * @param view
     */
    void clikedQuiz(View view) {

        // if no quizzes, don't allow to enter quiz activity
        if (QuizStore.isEmpty(this)) {
            Toast.makeText(this, "No quizes! Add some first", Toast.LENGTH_LONG).show();
            return;
        }

        // create intent
        Intent intent = new Intent(this, QuizActivity.class);

        // start activity
        startActivity(intent);
    }

    /**
     * Switch to add quiz qustion activity
     * @param view
     */
    void clickedAdd(View view) {

        // create intent
        Intent intent = new Intent(this, AddQuizQuestionActivity.class);

        // start activity
        startActivity(intent);
    }

    /**
     * Clear all stored questions
     * @param view
     */
    void clickedClear(View view) {

        QuizStore.clearQuiz(this);

        // show feedback toast
        Toast.makeText(this, "Questions deleted", Toast.LENGTH_LONG).show();
    }
}
