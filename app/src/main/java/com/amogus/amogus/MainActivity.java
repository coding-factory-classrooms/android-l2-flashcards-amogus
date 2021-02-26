package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyDialog();
            }
        });

        Button questionsList = findViewById(R.id.listButton);
        questionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, QuestionsListActivity.class);
                startActivity(listIntent);
            }
        });


        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });
    }

    private void showDifficultyDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Choisissez votre difficulté");
        String[] items = {"Facile", "Normal", "Difficile"};
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuestionList questions;

                switch (which) {
                    case 0:
                        questions = new QuestionList(0, 3, true);
                        break;
                    case 1:
                        questions = new QuestionList(1, 3, true);
                        break;
                    case 2:
                        questions = new QuestionList(2, 4, true);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + which);
                }

                Intent listIntent = new Intent(MainActivity.this, QuestionActivity.class);
                listIntent.putExtra("questions", questions);
                listIntent.putExtra("currentQuestion", 0);
                //listIntent.putExtra("goodAnswers", 0);
                startActivity(listIntent);
                finish();
            }
        });
        alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();

    }
}