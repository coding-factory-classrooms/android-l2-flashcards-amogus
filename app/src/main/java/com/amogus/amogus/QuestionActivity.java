package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent srcIntent = getIntent();
        QuestionList questionList = srcIntent.getParcelableExtra("questions");
        int currentQuestionId = srcIntent.getIntExtra("currentQuestion", 0);
        Question currentQuestion = questionList.getQuestion(currentQuestionId);


        ImageView questionImageView = findViewById(R.id.questionImageView);
        questionImageView.setImageResource(currentQuestion.getImageId());

        TextView questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(currentQuestion.getQuestion());

        questionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("QuestionActivity","image click");

                Intent showPictureIntent = new Intent(QuestionActivity.this, ShowPictureActivity.class);
                showPictureIntent.putExtra("image",currentQuestion.getImageId());
                startActivity(showPictureIntent);



                // finish(); To finish current activity

            }
        });

    }

}