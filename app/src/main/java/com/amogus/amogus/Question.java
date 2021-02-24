package com.amogus.amogus;

import android.widget.ImageView;

import java.util.ArrayList;

public class Question {
    private String level;
    private String question;
    private ImageView image;
    private ArrayList answers;

    public Question(String level, String question, ImageView image, ArrayList answers) {
        this.level = level;
        this.question = question;
        this.image = image;
        this.answers = answers;
    }

    public String getLevel() {
        return level;
    }

    public String getQuestion() {
        return question;
    }

    public ImageView getImage() {
        return image;
    }

    public ArrayList getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return (String) answers.get(0);
    }
}
