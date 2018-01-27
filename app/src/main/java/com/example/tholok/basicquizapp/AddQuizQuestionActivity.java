package com.example.tholok.basicquizapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddQuizQuestionActivity extends Activity {

    // the alternative that is correct (specified by the user through radio buttons)
    private int correctAnswer = -1;

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
                alt4ET.getText().toString(),
                correctAnswer
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

        // early return if not checked correct answer
        if (question.correct == -1) {
            Toast.makeText(this, "Check off the correct answer!", Toast.LENGTH_LONG)
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

    /**
     * function that is fired when user presses one of the radio buttons. Changes what answer is
     * correct and makes the radio buttons do radio button things
     * @param view
     */
    void correctChange(View view) {

        // turn all radio buttons off:

        //  get radio widgets
        RadioButton radioAlt1 = (RadioButton) findViewById(R.id.radioAlt1);
        RadioButton radioAlt2 = (RadioButton) findViewById(R.id.radioAlt2);
        RadioButton radioAlt3 = (RadioButton) findViewById(R.id.radioAlt3);
        RadioButton radioAlt4 = (RadioButton) findViewById(R.id.radioAlt4);

        // turn them off
        radioAlt1.setChecked(false);
        radioAlt2.setChecked(false);
        radioAlt3.setChecked(false);
        radioAlt4.setChecked(false);

        // set radio button that was clicked
        switch (view.getId()) {
            case R.id.radioAlt1:
                radioAlt1.setChecked(true);
                correctAnswer = 1;
                break;
            case R.id.radioAlt2:
                radioAlt2.setChecked(true);
                correctAnswer = 2;
                break;
            case R.id.radioAlt3:
                radioAlt3.setChecked(true);
                correctAnswer = 3;
                break;
            case R.id.radioAlt4:
                radioAlt4.setChecked(true);
                correctAnswer = 4;
                break;
            default:
                Log.d("AddQuizQuestionActivity", "CorrectChanged function was called "+
                        "with unknown view with id: " + view.getId());
        }
    }
}
