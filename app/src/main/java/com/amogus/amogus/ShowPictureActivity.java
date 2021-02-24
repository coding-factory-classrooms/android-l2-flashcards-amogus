package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        Intent srcIntent = getIntent();
        int imageId = srcIntent.getIntExtra("image",R.drawable.crewmate_red);

        ImageView displayImageView = findViewById(R.id.displayImageView);
        displayImageView.setImageResource(imageId);
    }
}