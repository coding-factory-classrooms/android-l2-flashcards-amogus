package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent srcIntent = getIntent();
        QuestionList questionList = srcIntent.getParcelableExtra("questions");
        int goodAnswers = questionList.getGoodAnswers();
        int questionsNumbers = questionList.getQuestionsNumbers();
        setTitle("Amogus - Résultats");

        TextView levelSuccessTextView = findViewById(R.id.levelSuccessTextView);
        switch (questionList.getLevel()) {
            case 0:
                levelSuccessTextView.setText("Facile");
                break;
            case 1:
                levelSuccessTextView.setText("Normal");
                break;
            case 2:
                levelSuccessTextView.setText("Difficile");
                break;
            default:
                levelSuccessTextView.setText("Iconnu");
                break;
        }

        TextView goodAnswersTextView = findViewById(R.id.goodAnswersTextView);
        goodAnswersTextView.setText(goodAnswers + "/" + questionsNumbers);

        TextView successTextView = findViewById(R.id.successTextView);
        double percentage = (double) ((goodAnswers * 100) / questionsNumbers);
        successTextView.setText((int) percentage + "%");

    }
}