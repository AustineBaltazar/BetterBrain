package com.example.projectchecking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatchingGame extends AppCompatActivity {
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game);

        start = (Button) findViewById(R.id.retry);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();


            }
        });
    }
    public void openGame(){
        Intent intent = new Intent(this, MatchingGameActivity.class);
        startActivity(intent);
    }
}