package com.amogus.amogus;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionList implements Parcelable {
    private int level;
    private int questionsNumbers;
    private int goodAnswers;
    private ArrayList<Question> questionsList;

    // Used to create questions lists
    private ArrayList<Question> easyQuestions = new ArrayList<>(Arrays.asList(
            new Question(0, "Quelle est la date de sortie du jeu Among Us ?", R.drawable.q_0_0, new String[]{"15 juin 2018", "25 septembre 2018", "15 décembre 2020"}),
            new Question(0, "Comment s'appelle le studio derrière Among Us ?", R.drawable.q_0_1, new String[]{"Innersloth", "Epic Games", "Rockstar"}),
            new Question(0, "Comment s'appelle ce lieu ?", R.drawable.q_0_2, new String[]{"O2", "Medbay", "Admin"}),
            new Question(0, "De quelle tâche vient cette capture d'écran ?", R.drawable.q_0_3, new String[]{"Garbage", "Vent", "Scan"}),
            new Question(0, "Que font les \"pets\" quand un joueur est tué ?", R.drawable.q_0_4, new String[]{"Ils restent là ou le joueur a été tué", "Ils se déplacent de façon aléatoire", "Ils vengent leur joueur"})
    ));

    private ArrayList<Question> normalQuestions = new ArrayList<>(Arrays.asList(
            new Question(1, "Quels sont les noms des deux équipes ?", R.drawable.q_1_0, new String[]{"Imposters & Crewmates", "Alpha & Beta", "Cats & Dogs", "Innocent & Murderers"}),
            new Question(1, "Quel politicien américain s'est \"streamé\" en train de jouer à Among Us ?", R.drawable.q_1_1, new String[]{"Alexandria Ocasio-Cortez", "Joe Biden", "Barack Obama", "Donald Trump"}),
            new Question(1, "Comment l'imposteur peut-il traverser la carte ?", R.drawable.q_1_2, new String[]{"Conduits d'aération", "Des égouts ", "Avec un téléporteur", "Il devient invisible"}),
            new Question(1, "Au 1er janvier 2021, combien de cartes sont présentes dans le jeu ?",R.drawable.q_1_3, new String[]{"3", "4", "2", "5"}),
            new Question(1, "Où se situe cette caféteria ?", R.drawable.q_1_4, new String[]{"The Skeld", "Mira HQ", "Polus", "The Airship"})
    ));

    private ArrayList<Question> hardQuestions = new ArrayList<>(Arrays.asList(
            new Question(2, "Quel est le nombre maximum de joueur dans une partie ?", R.drawable.q_2_0, new String[]{"10", "15", "6", "5", "20"}),
            new Question(2, "Quelle couleur n'est pas disponible dans la création de personage ?", R.drawable.q_2_1, new String[]{"Gris", "Violet", "Orange", "Marron", "Vert foncé"}),
            new Question(2, "Comment s'appelle la couleur \"secrète\" dans le jeu ?", R.drawable.q_2_2, new String[]{"Fortegreen", "Greenbush", "Greempostor", "Verde", "Luigi"}),
            new Question(2, "Au 1er janvier 2021, combien y a-t'il de manières de se faire tuer ?", R.drawable.q_2_3, new String[]{"5", "7", "2", "6", "8"}),
            new Question(2, "Au 1er février 2021, combien de personnes travaillent-elles sur le jeu ?",  R.drawable.q_2_4, new String[]{"5", "3", "6", "1", "8"})
    ));

    public QuestionList(int level, int questionsNumbers, boolean isRandom) {
        this.level = level;
        this.questionsNumbers = questionsNumbers;
        this.goodAnswers = 0;

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

    protected QuestionList(Parcel in) {
        level = in.readInt();
        questionsNumbers = in.readInt();
        goodAnswers = in.readInt();
        questionsList = in.createTypedArrayList(Question.CREATOR);
        easyQuestions = in.createTypedArrayList(Question.CREATOR);
        normalQuestions = in.createTypedArrayList(Question.CREATOR);
        hardQuestions = in.createTypedArrayList(Question.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(level);
        dest.writeInt(questionsNumbers);
        dest.writeInt(goodAnswers);
        dest.writeTypedList(questionsList);
        dest.writeTypedList(easyQuestions);
        dest.writeTypedList(normalQuestions);
        dest.writeTypedList(hardQuestions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionList> CREATOR = new Creator<QuestionList>() {
        @Override
        public QuestionList createFromParcel(Parcel in) {
            return new QuestionList(in);
        }

        @Override
        public QuestionList[] newArray(int size) {
            return new QuestionList[size];
        }
    };

    public int getLevel() {
        return level;
    }

    public int getQuestionsNumbers() {
        return questionsNumbers;
    }

    public Question getQuestion(int position) {
        return questionsList.get(position);
    }

    public int getGoodAnswers() {
        return goodAnswers;
    }

    public void addGoodAnswer() {
        this.goodAnswers++;
    }
}
