package com.example.tholok.basicquizapp;

import android.util.Log;

/**
 * Represents single question in quiz
 */

class Question {

    String question;
    String alt1;
    String alt2;
    String alt3;
    String alt4;

    /**
     * auto-generated constructor
     * @param question
     * @param alt1
     * @param alt2
     * @param alt3
     * @param alt4
     */
    Question(String question, String alt1, String alt2, String alt3, String alt4) {
        this.question = question;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
    }

    String toPrefFormat() {

        return question +
                "&" + alt1 +
                "&" + alt2 +
                "&" + alt3 +
                "&" + alt4;
    }

    /**
     * Returns true if no property is "". False if not
     * @return
     */
    public boolean properlyFormatted() {

        return
                !question.equals("") &&
                !alt1.equals("") &&
                !alt2.equals("") &&
                !alt3.equals("") &&
                !alt4.equals("");

    }
}
