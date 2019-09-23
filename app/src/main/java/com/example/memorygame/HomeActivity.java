package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView level1;
    private TextView level2;
    private TextView level3;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gameViewModel = ViewModelProviders.of(HomeActivity.this).get(GameViewModel.class);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
    }


    // Clicking each level will take to the corresponding game page.

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.level1:
                String message = "You should match 2 similar images and find atleast 10 pair to win";
                String title = "CLICK AND MATCH TWO IMAGES";
                gameViewModel.levelAlert(HomeActivity.this,message,2,title);
                break;
            case R.id.level2:
                String message1 = "You should match 3 similar images and find atleast 10 pair to win";
                String title1 = "CLICK AND MATCH THREE IMAGES";
                gameViewModel.levelAlert(HomeActivity.this,message1,3,title1);
                break;
            case R.id.level3:
                String message2 = "You should match 4 similar images and find atleast 10 pair to win";
                String title2 = "CLICK AND MATCH FOUR IMAGES";
                gameViewModel.levelAlert(HomeActivity.this,message2,4,title2);
                break;
        }
    }
}
