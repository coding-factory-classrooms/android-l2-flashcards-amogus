package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class QuestionActivity extends AppCompatActivity {
    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator currentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Hook up clicks on the thumbnail views.

        View imageButton = findViewById(R.id.imageButton);
        View largeImageButton = findViewById(R.id.expandedPictureImageView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("QuestionActivity","image click");
                Log.i("QuestionActivity", String.valueOf(largeImageButton.getVisibility()));

                largeImageButton.clearAnimation();
                if (largeImageButton.getVisibility() == View.INVISIBLE) {
                    largeImageButton.setVisibility(View.VISIBLE);
                    largeImageButton.bringToFront();
                } else {
                    largeImageButton.setVisibility(View.INVISIBLE);

                }

            }
        });

        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
    }

}