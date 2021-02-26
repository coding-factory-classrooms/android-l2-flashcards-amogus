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

        // Button to start playing
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyDialog();
            }
        });

        // Button to display the question list
        Button questionsList = findViewById(R.id.listButton);
        questionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, QuestionsListActivity.class);
                startActivity(listIntent);
            }
        });

        // Button to display About activity
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });
    }

    // Displays a Dialog that allows the user to start a game with a set difficulty
    private void showDifficultyDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting dialog box title, options, and default value
        alertDialog.setTitle("Choisissez votre difficulté");
        String[] items = {"Facile", "Normal", "Difficile"};
        int checkedItem = -1;

        // Action on item click
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                QuestionList questions;

                // Depending on wich item the user picked, we create a QuestionList
                switch (which) {
                    case 0: // Easy
                        questions = new QuestionList(0, 3, true);
                        break;
                    case 1: // Normal
                        questions = new QuestionList(1, 3, true);
                        break;
                    case 2: // Hard
                        questions = new QuestionList(2, 4, true);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + which);
                }

                // We then start a game with the previously created QuestionList
                Intent listIntent = new Intent(MainActivity.this, QuestionActivity.class);
                listIntent.putExtra("questions", questions);
                listIntent.putExtra("currentQuestion", 0);

                startActivity(listIntent);
                finish();
            }
        });
        alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true); // Allows the user to click outside the Dialog to exit
        alert.show();
    }
}