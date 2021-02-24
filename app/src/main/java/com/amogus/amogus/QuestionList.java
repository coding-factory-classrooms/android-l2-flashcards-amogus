package com.amogus.amogus;

import java.util.ArrayList;

public class QuestionList {
    private String level;
    private ArrayList questions;
    private Question question;

    public QuestionList(String level, ArrayList questions, Question question) {
        this.level = level;
        this.questions = questions;
        this.question = question;
    }

    public String getLevel() {
        return level;
    }

    public ArrayList getQuestions() {
        return questions;
    }

    public Question getQuestion() {
        return question;
    }
}
