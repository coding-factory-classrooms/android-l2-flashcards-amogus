package com.amogus.amogus;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private QuestionList questions;

    public QuestionAdapter(QuestionList questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_questions, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.getQuestion(position);
        // Set question Image and Text
        holder.questionImage.setImageResource(question.getImageId());
        holder.question.setText(question.getQuestion());

        // Set the answers with a joined array
        holder.answers.setText(String.join(", ", question.getAnswers()));

        // Set the level depending on the int we get
        switch (question.getLevel()) {
            case 0:
                holder.level.setText("Facile");
                break;
            case 1:
                holder.level.setText("Normal");
                break;
            case 2:
                holder.level.setText("Difficile");
                break;
            default:
                holder.level.setText("Inconnu");
                break;
        }
    }


    @Override
    public int getItemCount() {
        return questions.getQuestionsNumbers();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView questionImage;
        final TextView level;
        final TextView question;
        final TextView answers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Get components from items_questions.xml
            questionImage = itemView.findViewById(R.id.questionImageView);
            level = itemView.findViewById(R.id.levelItemTextView);
            question = itemView.findViewById(R.id.questionItemTextView);
            answers = itemView.findViewById(R.id.answersItemTextView);
        }
    }
}
