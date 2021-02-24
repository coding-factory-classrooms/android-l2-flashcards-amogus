package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListActivity extends AppCompatActivity {

    private List<Question> questions;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        new QuestionList(0,4);

        questions = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            questions.add(new Question(0, "Quel est la date de sortie?", R.drawable.amongus_logo, new String[]{"15/06/2018", "25/09/2018", "15/12/2020"}));
            questions.add(new Question(1, "Quels sont les noms des deux équipes ?", R.drawable.amongus_logo, new String[]{"Imposters & Crewmates", "Alpha & Beta", "Innocents & Murders", "Dogs and cats"}));
            questions.add(new Question(2, "Quel est le nombre maximum de joueurs dans une partie ?", R.drawable.amongus_logo, new String[]{"10", "15", "5", "20", "6"}));
        }

        adapter = new QuestionAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.questionsRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}