package com.example.tholok.basicquizapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends Activity {

    private ArrayList<Question> questions;
    private int currentQuestionIDX = -1;
    private int score = 0;

    /**
     * prepare activity for next question, or show score screen if done
     */
    private void next() {

        // if no more questions show score activity
        currentQuestionIDX++;
        if (currentQuestionIDX >= questions.size()) {

            // start points activity (FOR NOW: show score and return to main activity)
            Toast.makeText(this, "Your score was " + score, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // get widgets
        TextView questionNumTxt = (TextView) findViewById(R.id.questionNumTxt);
        TextView question = (TextView) findViewById(R.id.question);
        Button alt1 = (Button) findViewById(R.id.alt1);
        Button alt2 = (Button) findViewById(R.id.alt2);
        Button alt3 = (Button) findViewById(R.id.alt3);
        Button alt4 = (Button) findViewById(R.id.alt4);

        // set values based on question
        questionNumTxt.setText("Question " + (currentQuestionIDX + 1) + "/" + Integer.toString(questions.size()));
        question.setText(questions.get(currentQuestionIDX).question);
        alt1.setText(questions.get(currentQuestionIDX).alt1);
        alt2.setText(questions.get(currentQuestionIDX).alt2);
        alt3.setText(questions.get(currentQuestionIDX).alt3);
        alt4.setText(questions.get(currentQuestionIDX).alt4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // load quiz
        questions = QuizStore.loadQuiz(this);

        // prepare first question
        next();
    }

    /**
     * function that is run when user chooses an alternative. increments score if correct and calls
     * next()
     * @param view
     */
    void onClick(View view) {

        // react to what alternative was chosen
        switch (view.getId()) {
            case R.id.alt1:
                if (questions.get(currentQuestionIDX).correct == 1) {
                    score++;
                }
                break;
            case R.id.alt2:
                if (questions.get(currentQuestionIDX).correct == 2) {
                    score++;
                }
                break;
            case R.id.alt3:
                if (questions.get(currentQuestionIDX).correct == 3) {
                    score++;
                }
                break;
            case R.id.alt4:
                if (questions.get(currentQuestionIDX).correct == 4) {
                    score++;
                }
                break;
            default:
                Log.d("QuizActivity", "onClick received view with invalid id " +
                        view.getId());
        }

        // next question / score
        next();
    }
}
