package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WordGame extends AppCompatActivity {
    private static long START_TIME_IN_MILLIS = 120000;
    TextView wordInfo, wordMain, scoreCount, levelTimer;
    EditText wordInput;
    Button checkAnswer, newWord, startGame;
    int scoreCounter = 0;
    Random r;
    String currentWord;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private static long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private String [] dictionary ={ "data" , "internet", "computer", "software", "database", "system", "hardware",
            "programming", "application", "technology", "programmer", "developer", "object", "network", "code",
            "program", "file" , "device", "mobile", "website", "binary" ,"user" , "client" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_game);

        levelTimer = findViewById(R.id.wordTimer);
        wordInfo = findViewById(R.id.info);
        wordMain = findViewById(R.id.theWord);
        wordInput = findViewById(R.id.answerInput);
        checkAnswer = findViewById(R.id.answerBtn);
        newWord = findViewById(R.id.newBtn);
        startGame = findViewById(R.id.startGameBtn);
        scoreCount = findViewById(R.id.score);
        r = new Random();
        levelTimer.setVisibility(View.INVISIBLE);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                newGame();
                startTimer();
                scoreCount.setText("");
                wordInput.setVisibility(View.VISIBLE);
                startGame.setVisibility(View.INVISIBLE);
            }
        });


        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(wordInput.getText().toString().equalsIgnoreCase(currentWord)){
                    wordInfo.setText("NICE! CORRECT ANSWER \n" + "The word :" + currentWord);
                    checkAnswer.setVisibility(View.INVISIBLE);
                    newWord.setVisibility(View.VISIBLE);
                    scoreCounter++;
                    scoreCount.setText("Score:" + scoreCounter);
                }else{
                    wordInfo.setText("INCORRECT ! TRY AGAIN");
                }

            }
        });

        newWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
    }


    private String shuffleWord(String word){
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled ="";
        for(String letter: letters){
            shuffled += letter;
        }
        return shuffled;

    }

    private void newGame(){
        levelTimer.setVisibility(View.VISIBLE);
        wordMain.setVisibility(View.VISIBLE);
        currentWord = dictionary[r.nextInt(dictionary.length)];
        wordMain.setText(shuffleWord(currentWord));
        wordInput.setText("");
        wordInfo.setText("");
        newWord.setVisibility(View.INVISIBLE);
        checkAnswer.setVisibility(View.VISIBLE);

    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {

                mTimeLeftInMillis = l;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                wordInfo.setText("Time is up!");
                scoreCount.setText("Total score in 2 minutes: " + scoreCounter);
                scoreCounter = 0;
                resetTimer();
                startGame.setVisibility(View.VISIBLE);
                newWord.setVisibility(View.INVISIBLE);
                checkAnswer.setVisibility(View.INVISIBLE);
                wordInput.setVisibility(View.INVISIBLE);
                wordMain.setVisibility(View.INVISIBLE);
                startGame.setText("New Game");


            }
        }.start();
        mTimerRunning = true;
    }

    private void resetTimer(){
    mTimeLeftInMillis = START_TIME_IN_MILLIS;
    updateCountdownText();

    }

    private void updateCountdownText() {
        int minutes = (int) mTimeLeftInMillis /1000/60;
        int seconds = (int) mTimeLeftInMillis /1000 % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes , seconds);
        levelTimer.setText(timeLeftFormatted);
    }
}