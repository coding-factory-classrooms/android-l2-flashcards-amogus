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
        Log.i("QuestionActivity", "Current Question ID : " + currentQuestionId);

        // Set top bar title with current question number (with the total nb of questions)
        int questionsNumbers = questionList.getQuestionsNumbers();
        setTitle("Amogus - Question " + (currentQuestionId + 1) + " sur " + questionsNumbers);

        // Grabs the current Question object to display the different elements
        Question currentQuestion = questionList.getQuestion(currentQuestionId);

        // Gets correct answer from question
        String correctAnswer = currentQuestion.getRightAnswer();

        // Setting question picture
        ImageView questionImageView = findViewById(R.id.questionImageView);
        questionImageView.setImageResource(currentQuestion.getImageId());

        // Setting question text
        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(currentQuestion.getQuestion());

        // Getting question answers
        String[] questionAnswers = currentQuestion.getAnswers();

        // Shuffling question answers
        List<String> tempList = Arrays.asList(questionAnswers);
        Collections.shuffle(tempList);
        String[] shuffledQuestionAnswers = tempList.toArray(new String[tempList.size()]);

        // Create radioButtons for answers
        RadioButton button;
        RadioGroup answersRadioGroup = findViewById(R.id.answersRadioGroup);
        answersRadioGroup.setOrientation(RadioGroup.VERTICAL);
        for (String answerText : shuffledQuestionAnswers) {
            button = new RadioButton(this);
            button.setText(answerText);
            answersRadioGroup.addView(button);
        }

        // Creation of textViews for results
        TextView feedbackTextView = findViewById(R.id.feedbackTextView);
        TextView infoTextView = findViewById(R.id.infoTextView);

        // Set listener for bottom Button
        Button actionButton = findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodAnswers = questionList.getGoodAnswers();

                // get current selected option IF SELECTED, ELSE RETURN
                int radioButtonID = answersRadioGroup.getCheckedRadioButtonId();

                if (actionButton.getText() == "Question suivante") { // If the question has been answered
                    if (currentQuestionId + 1 == questionList.getQuestionsNumbers()) { // If we're at the last question
                        Intent statsIntent = new Intent(QuestionActivity.this, StatsActivity.class);
                        statsIntent.putExtra("questions", questionList);
                        statsIntent.putExtra("goodAnswers", goodAnswers);
                        startActivity(statsIntent); // Go to Stats Activity
                        finish();
                    } else { // If we still have questions
                        Intent listIntent = new Intent(QuestionActivity.this, QuestionActivity.class);
                        listIntent.putExtra("questions", questionList);
                        listIntent.putExtra("currentQuestion", currentQuestionId + 1);
                        listIntent.putExtra("goodAnswers", goodAnswers);
                        startActivity(listIntent); // Go to next question
                        finish();
                    }

                } else if (radioButtonID > -1) { // If an option has been selected
                    // Get selected radioButton and grab text
                    RadioButton selectedRadioButton = (RadioButton) answersRadioGroup.findViewById(radioButtonID);
                    String selectedAnswer = (String) selectedRadioButton.getText();

                    // Lock the radio buttons + user feedback
                    for (int i = 0; i < answersRadioGroup.getChildCount(); i++) {
                        answersRadioGroup.getChildAt(i).setEnabled(false);
                    }

                    // Set user feedback text to visible
                    feedbackTextView.setVisibility(View.VISIBLE);
                    infoTextView.setVisibility(View.VISIBLE);

                    // Compare the two strings
                    if (selectedAnswer == correctAnswer) {
                        Log.i("QuestionActivity", selectedAnswer + " == " + correctAnswer + " - good!");
                        feedbackTextView.setText("Bonne réponse ! 👏");
                        questionList.addGoodAnswer();
                    } else if (!(selectedAnswer == correctAnswer)) {
                        Log.i("QuestionActivity", selectedAnswer + " != " + correctAnswer + " - bad.");
                        feedbackTextView.setText("Mauvaise réponse... ❌");
                    }

                    // Display correct answer
                    infoTextView.setText("La bonne réponse était \"" + correctAnswer + "\".");

                    // Change button action
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
            }
        });

    }

}