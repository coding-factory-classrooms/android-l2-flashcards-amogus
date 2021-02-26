package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Get QuestionList information from 
        Intent srcIntent = getIntent();
        QuestionList questionList = srcIntent.getParcelableExtra("questions");
        int goodAnswers = questionList.getGoodAnswers();
        int questionsNumbers = questionList.getQuestionsNumbers();

        setTitle("Amogus - Résultats");

        // Set the level depending on the int we get
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
                levelSuccessTextView.setText("Inconnu");
                break;
        }

        // Display good answers number with the number of total questions
        TextView goodAnswersTextView = findViewById(R.id.goodAnswersTextView);
        goodAnswersTextView.setText(goodAnswers + "/" + questionsNumbers);

        // Displays percentage of correct answers
        TextView successTextView = findViewById(R.id.successTextView);
        double percentage = (double) ((goodAnswers * 100) / questionsNumbers);
        successTextView.setText((int) percentage + "%");

        // Button to return to main menu
        Button questionsList = findViewById(R.id.returnButton);
        questionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(StatsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}