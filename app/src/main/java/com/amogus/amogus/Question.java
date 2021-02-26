package com.amogus.amogus;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Question class. Get information of one question.
 */

public class Question implements Parcelable {
    private int level;
    private String question;
    private int imageId;
    private String[] answers;


    /** Creates a question with those params :
     * @param level the difficulty of question.
     * @param question the question
     * @param question the question's image
     * @param answers the list of suggested answers.
     */
    public Question(int level, String question, int imageId, String[] answers) {
        this.level = level;
        this.question = question;
        this.imageId = imageId;
        this.answers = answers;
    }

    /**
     *
     * @param in the parcebal
     */
    protected Question(Parcel in) {
        level = in.readInt();
        question = in.readString();
        imageId = in.readInt();
        answers = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(level);
        dest.writeString(question);
        dest.writeInt(imageId);
        dest.writeStringArray(answers);
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

    /** Gets the question difficulty
     * @return A int representing the level. (0 for easy, 1 for normal, 2 for hard)
     */
    public int getLevel() {
        return level;
    }

    /** Gets the question
     * @return A string representing the question
     */
    public String getQuestion() {
        return question;
    }

    /** Gets the image by id
     * @return A int representing the image by id
     */
    public int getImageId() {
        return imageId;
    }

    /** Gets the suggested answers
     * @return A array of strings representing the list of suggested answers
     */
    public String[] getAnswers() {
        return answers;
    }

    /** Gets the right answer
     * @return A string representing the right answers at index 0 of getAnswers()
     */
    public String getRightAnswer() {
        return answers[0];
    }

    @Override
    public String toString() {
        return "Question{" +
                "level='" + level + '\'' +
                ", question='" + question + '\'' +
                ", imageId=" + imageId +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
