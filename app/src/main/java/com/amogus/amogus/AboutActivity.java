package com.amogus.amogus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView versionTextView = findViewById(R.id.versionTextView);
        try {
            String versionName = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
            versionTextView.setText("v" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}