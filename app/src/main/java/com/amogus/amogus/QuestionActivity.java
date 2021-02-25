package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        Log.i("QuestionActivity","Current Question ID : " + currentQuestionId);

        Question currentQuestion = questionList.getQuestion(currentQuestionId);

        // get correct answer from question
        String correctAnswer = currentQuestion.getRightAnswer();

        // Setting question picture
        ImageView questionImageView = findViewById(R.id.questionImageView);
        questionImageView.setImageResource(currentQuestion.getImageId());

        // Setting question text
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(currentQuestion.getQuestion());

        // Creating question answers 
        String[] questionAnswers = currentQuestion.getAnswers();

        // Shuffling question answers
        List<String> tempList = Arrays.asList(questionAnswers);
        Collections.shuffle(tempList);
        String[] shuffledQuestionAnswers = tempList.toArray(new String[tempList.size()]);

        // Creation radioButtons for answers
        RadioButton button;
        RadioGroup answersRadioGroup = findViewById(R.id.answersRadioGroup);
        answersRadioGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for (String answerText : shuffledQuestionAnswers) {
            button = new RadioButton(this);
            button.setText(answerText);
            answersRadioGroup.addView(button);
        }

        // Creation of textViews for results
        TextView feedbackTextView = findViewById(R.id.feedbackTextView);
        TextView infoTextView = findViewById(R.id.infoTextView);

        Button actionButton = findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodAnswers = srcIntent.getIntExtra("goodAnswers", 0);
                // get current selected option IF SELECTED, ELSE RETURN
                int radioButtonID = answersRadioGroup.getCheckedRadioButtonId();

                if (actionButton.getText() == "Question suivante") {
                    if (currentQuestionId +1 == questionList.getQuestionsNumbers() ) {
                        Intent listIntent = new Intent(QuestionActivity.this, StatsActivity.class);
                        startActivity(listIntent);
                        finish();
                    }
                    Intent listIntent = new Intent(QuestionActivity.this, QuestionActivity.class);
                    listIntent.putExtra("questions", questionList);
                    listIntent.putExtra("currentQuestion", currentQuestionId + 1);
                    listIntent.putExtra("goodAnswers", goodAnswers);
                    startActivity(listIntent);
                    finish();
                }
                else if (radioButtonID > -1) {
                    Log.i("QuestionActivity","radioButtonID = " + radioButtonID + " (fixed) " + (radioButtonID - 1));

                    //Log.i("QuestionActivity", "selected answer " + radioButtonID + " = " + shuffledQuestionAnswers[radioButtonID - 1]);
                    Log.i("QuestionActivity", Arrays.toString(shuffledQuestionAnswers));

                    // Get selected radioButton and grab text
                    RadioButton selectedRadioButton = (RadioButton) answersRadioGroup.findViewById(radioButtonID);
                    String selectedAnswer = (String) selectedRadioButton.getText();

                    // lock the radio buttons + user feedback
                    for (int i = 0; i < answersRadioGroup.getChildCount(); i++) {
                        answersRadioGroup.getChildAt(i).setEnabled(false);
                    }

                    // set user feedback text to visible
                    feedbackTextView.setVisibility(View.VISIBLE);
                    infoTextView.setVisibility(View.VISIBLE);

                    // compare the two strings
                    if (selectedAnswer == correctAnswer) {
                        Log.i("QuestionActivity", selectedAnswer + " == " + correctAnswer + " - good!");
                        feedbackTextView.setText("Bonne réponse ! 👏");
                        goodAnswers++;

                    } else if (!(selectedAnswer == correctAnswer)) {
                        Log.i("QuestionActivity", selectedAnswer + " != " + correctAnswer + " - bad.");
                        feedbackTextView.setText("Mauvaise réponse... ❌");
                    }

                    infoTextView.setText("La bonne réponse était \"" + correctAnswer + "\".");

                    // change button action
                    actionButton.setText("Question suivante");
                }
            }
        });

        // If image is clicked
        questionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showPictureIntent = new Intent(QuestionActivity.this, ShowPictureActivity.class);
                showPictureIntent.putExtra("image", currentQuestion.getImageId());
                startActivity(showPictureIntent);
                // finish(); To finish current activity
            }
        });

    }

}