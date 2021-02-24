package com.amogus.amogus;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    private String level;
    private String question;
    private int imageId;
    private String[] answers;

    public Question(String level, String question, int imageId, String[] answers) {
        this.level = level;
        this.question = question;
        this.imageId = imageId;
        this.answers = answers;
    }

    protected Question(Parcel in) {
        imageId = in.readInt();
        level = in.readString();
        question = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(level);
        dest.writeString(question);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getLevel() {
        return level;
    }

    public String getQuestion() {
        return question;
    }

    public int getImageId() {
        return imageId;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return answers[0];
    }

    @Override
    public String toString() {
        return "Question{" +
                "level='" + level + '\'' +
                ", question='" + question + '\'' +
                ", imageId=" + imageId +
                ", answers=" + answers +
                '}';
    }
}
