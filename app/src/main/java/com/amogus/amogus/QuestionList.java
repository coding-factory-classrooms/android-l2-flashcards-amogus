package com.amogus.amogus;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {
    private int level;
    private int questionsNumbers;
    private List<Question> questionsList;
    private List<Question> easyQuestions;

    public QuestionList(int level, int questionsNumbers) {
        this.level = level;
        this.questionsNumbers = questionsNumbers;
    }



    public int getLevel() {
        return level;
    }

    public int getQuestionsNumbers() {
        return questionsNumbers;
    }




}
