package com.amogus.amogus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Question> questions;

    public QuestionAdapter(List<Question> questions) {
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
        Question question = questions.get(position);
        holder.questionImage.setImageResource(question.getImageId());
        holder.level.setText(question.getLevel()+"");
        holder.question.setText(question.getQuestion());

        holder.answers.setText(String.join(", ", question.getAnswers()));

    }

    @Override
    public int getItemCount() {
        return questions.size();
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
