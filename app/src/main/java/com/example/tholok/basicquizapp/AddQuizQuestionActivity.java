package com.example.tholok.basicquizapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuizQuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_question);
    }

    /**
     * Make question based on fields in activity
     * @return
     */
    private Question questionFromFields() {

        // get each of the fields:
        EditText questionET = (EditText) findViewById(R.id.question);
        EditText alt1ET = (EditText) findViewById(R.id.alt1);
        EditText alt2ET = (EditText) findViewById(R.id.alt2);
        EditText alt3ET = (EditText) findViewById(R.id.alt3);
        EditText alt4ET = (EditText) findViewById(R.id.alt4);

        // make question object based on contenst
        Question question = new Question(
                questionET.getText().toString(),
                alt1ET.getText().toString(),
                alt2ET.getText().toString(),
                alt3ET.getText().toString(),
                alt4ET.getText().toString()
        );

        return question;
    }

    /**
     * Add inputted quiz question to collection if it is properly formatted. If not, show toast
     * with info
     * @param view
     */
    void clickedSubmit(View view) {

        // make question object from fields
        Question question = questionFromFields();

        // early return if not properly formatted
        if (!question.properlyFormatted()) {
            Toast.makeText(this, "Fill out all the fields!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // submit
        QuizStore.storeQuiz(question, this.getApplicationContext());

        // show feedback toast
        Toast.makeText(this, "Question added!", Toast.LENGTH_LONG).show();

        // return to previous activity
        finish();
    }
}
