package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GameSelection extends AppCompatActivity {
    private Button vGames, nGames, tGames, wGames, gGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_selection);
        vGames = (Button) findViewById(R.id.vGames);
        vGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openMatchingGame();
            }
        });
        tGames = (Button) findViewById(R.id.tGame);
        tGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTimeGame();
            }
        });
        wGames = (Button) findViewById(R.id.wGame);
        wGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWordGame();
            }
        });
        nGames = (Button) findViewById(R.id.nGame);
        nGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNumberGame();
            }
        });
        gGames = (Button) findViewById(R.id.gGame);
        gGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGuessGame();
            }
        });
    }
    public void openMatchingGame() {
        Intent intent = new Intent(this, MatchingGame.class);
        startActivity(intent);
    }
    public void  openTimeGame() {
        Intent intent = new Intent(this, TimeGame.class);
        startActivity(intent);
    }
    public void  openWordGame() {
        Intent intent = new Intent(this, WordGame.class);
        startActivity(intent);
    }
    public void openNumberGame() {
        Intent intent = new Intent(this, NumberGame.class);
        startActivity(intent);
    }
    public void openGuessGame() {
        Intent intent = new Intent(this, GuessGame.class);
        startActivity(intent);
    }
}