package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent srcIntent = getIntent();
        QuestionList questionList = srcIntent.getParcelableExtra("questions");
        int currentQuestionId = srcIntent.getIntExtra("currentQuestion", 0);
        Question currentQuestion = questionList.getQuestion(currentQuestionId);
        Log.i("QuestionActivity", String.valueOf(currentQuestion));


        // Setting question picture
        ImageView questionImageView = findViewById(R.id.questionImageView);
        questionImageView.setImageResource(currentQuestion.getImageId());

        // Setting question text
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(currentQuestion.getQuestion());

        // Creating question answers 
        String[] questionAnswers = currentQuestion.getAnswers();

        List<String> tempList = Arrays.asList(questionAnswers);
        Collections.shuffle(tempList);

        String[] shuffledQuestionAnswers = tempList.toArray(new String[tempList.size()]);

        RadioButton button;

        RadioGroup answersRadioGroup = findViewById(R.id.answersRadioGroup);
        answersRadioGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (String answerText : shuffledQuestionAnswers) {
            button = new RadioButton(this);
            button.setText(answerText);
            answersRadioGroup.addView(button);
        }

        questionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showPictureIntent = new Intent(QuestionActivity.this, ShowPictureActivity.class);
                showPictureIntent.putExtra("image",currentQuestion.getImageId());
                startActivity(showPictureIntent);
                // finish(); To finish current activity
            }
        });

    }

}