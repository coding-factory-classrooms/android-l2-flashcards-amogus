package com.amogus.amogus;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionList {
    private int level;
    private int questionsNumbers;
    private ArrayList<Question> questionsList;
    private ArrayList<Boolean> resultsList;

    // Used to create questions lists
    private ArrayList<Question> easyQuestions = new ArrayList<>(Arrays.asList(
            new Question(0, "Quelle est la date de sortie du jeu Among Us ?", R.drawable.amongus_logo, new String[]{"15 juin 2018", "25 septembre 2018", "15 décembre 2020"}),
            new Question(0, "Comment s'appelle le studio derrière Among Us ?", R.drawable.amongus_logo, new String[]{"Innersloth", "Epic Games", "Rockstar"}),
            new Question(0, "Comment s'appelle ce lieu ?", R.drawable.amongus_logo, new String[]{"O2", "Medbay", "Admin"}),
            new Question(0, "De quelle tâche vient cette capture d'écran ?", R.drawable.amongus_logo, new String[]{"Garbage", "Vent", "Scan"}),
            new Question(0, "Que font les \"pets\" quand un joueur est tué ?", R.drawable.amongus_logo, new String[]{"Ils restent là ou le joueur a été tué", "Ils se déplacent de façon aléatoire", "Ils vengent leur joueur"})
    ));

    private ArrayList<Question> normalQuestions = new ArrayList<>(Arrays.asList(
            new Question(1, "Quels sont les noms des deux équipes ?", R.drawable.amongus_logo, new String[]{"Imposters & Crewmates", "Alpha & Beta", "Cats & Dogs", "Innocent & Murderers"}),
            new Question(1, "Quel politicien américain s'est \"streamé\" en train de jouer à Among Us ?", R.drawable.amongus_logo, new String[]{"Alexandria Ocasio-Cortez", "Joe Biden", "Barack Obama", "Donald Trump"}),
            new Question(1, "Comment l'imposteur peut-il traverser la carte ?", R.drawable.amongus_logo, new String[]{"Conduits d'aération", "Des égouts ", "Avec un téléporteur", "Il devient invisible"}),
            new Question(1, "Au 1er janvier 2021, combien de cartes sont présentes dans le jeu ?", R.drawable.amongus_logo, new String[]{"3", "4", "2", "5"}),
            new Question(1, "Où se situe cette caféteria ?", R.drawable.amongus_logo, new String[]{"The Skeld", "Mira HQ", "Polus", "The Airship"})
    ));

    private ArrayList<Question> hardQuestions = new ArrayList<>(Arrays.asList(
            new Question(2, "Quel est le nombre maximum de joueur dans une partie ?", R.drawable.amongus_logo, new String[]{"10", "15", "6", "5", "20"}),
            new Question(2, "Quelle couleur n'est pas disponible dans la création de personage ?", R.drawable.amongus_logo, new String[]{"Gris", "Violet", "Noir", "Marron", "Vert foncé"}),
            new Question(2, "Comment s'appelle la couleur \"secrète\" dans le jeu ?", R.drawable.amongus_logo, new String[]{"Fortegreen", "Greenbush", "Greempostor", "Verde", "Luigi"}),
            new Question(2, "Au 1er janvier 2021, combien y a-t'il de manières de se faire tuer ?", R.drawable.amongus_logo, new String[]{"5", "7", "2", "6", "8"}),
            new Question(2, "Au 1er février 2021, combien de personnes travaillent-elles sur le jeu ?", R.drawable.amongus_logo, new String[]{"5", "3", "6", "1", "8"})
    ));

    public QuestionList(int level, int questionsNumbers, boolean isRandom) {
        this.level = level;
        this.questionsNumbers = questionsNumbers;

        Random rand = new Random();

        // Copies the full array of questions to generate a random set
        ArrayList<Question> questionsList;
        switch (level) {
            case 0:
                questionsList = easyQuestions;
                break;
            case 1:
                questionsList = normalQuestions;
                break;
            case 2:
                questionsList = hardQuestions;
                break;
            default:
                questionsList = easyQuestions;
                questionsList.addAll(normalQuestions);
                questionsList.addAll(hardQuestions);
                break;

        }
        Log.i("QuestionList", String.valueOf(questionsList));



        if (isRandom) {
            // Removes random element until we g
            while (questionsList.size() > questionsNumbers) {
                int randomIndex = rand.nextInt(questionsList.size());
                questionsList.remove(randomIndex);
            }
        }


        this.questionsList = questionsList;
    }

    public int getLevel() {
        return level;
    }

    public int getQuestionsNumbers() {
        return questionsNumbers;
    }

    public Question getQuestion(int position) {
        return questionsList.get(position);
    }
}
