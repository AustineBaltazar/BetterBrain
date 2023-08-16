package com.example.projectchecking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MatchingGameActivity extends AppCompatActivity implements View.OnClickListener{
    private int countPair = 1;
    private Button retry;
    private int numberOfElements;
    private MemoryButton[] buttons;
    private int [] buttonGraphicLocations;
    private int [] buttonGraphics;
    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;
    private boolean isBusy = false;
    GridLayout gridLayout;
    int numRows;
    int numColumns;
    //timer code
    private static long START_TIME_IN_MILLIS = 200000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private static long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    TextView matchTimer, matchScore;
    int matchScoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);


         gridLayout = findViewById(R.id.gridLayout);
        matchTimer = findViewById(R.id.matchTimer);
        matchScore = findViewById(R.id.matchScore);

       numColumns = gridLayout.getColumnCount();
       numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];

        buttonGraphics = new int[numberOfElements /2];

        buttonGraphics [0] = R.drawable.matchblack;
        buttonGraphics [1] = R.drawable.matchbrown;
        buttonGraphics [2] = R.drawable.matchblue;
        buttonGraphics [3] = R.drawable.matchgreen;
        buttonGraphics [4] = R.drawable.matchyellow;
        buttonGraphics [5] = R.drawable.matchpurple;
        buttonGraphics [6] = R.drawable.matchorange;
        buttonGraphics [7] = R.drawable.matchred;

        buttonGraphicLocations = new int[numberOfElements];
        retry = (Button) findViewById(R.id.retry);


        shuffleButtonGraphics();
        startTimer();

        for (int r = 0; r < numRows; r++)
        {
            for(int c= 0; c < numColumns; c++)
            {
                MemoryButton tempButton = new MemoryButton(this, r, c,buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons [r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }

    }
    protected void shuffleButtonGraphics()
    {
        Random rand = new Random();

        for (int i = 0; i < numberOfElements; i++)
        {
            buttonGraphicLocations[i]= i % (numberOfElements / 2);
        }

        for (int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonGraphicLocations[i];

            int swapIndex = rand.nextInt(16);

            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];

            buttonGraphicLocations[swapIndex] = temp;
        }
    }


    @Override
    public void onClick(View view) {


        if(isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

        if (button.isMatched)
            return;

        if (selectedButton1 == null)
        {
            selectedButton1 = button;
            selectedButton1.flip();

            return;
        }

        if (selectedButton1.getId() == button.getId())
        {
            return;
        }
        if (countPair == 8){
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newGame();
                }
            });
            Toast.makeText(this, "Congratulation you win!", Toast.LENGTH_SHORT).show();
            matchScoreCount++;
            matchScore.setText("Score :" + matchScoreCount);

        }


        if (selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()){
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            button.setEnabled(false);
            selectedButton1 = null;
            countPair++;
            return;
        }
        else {
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton2 = null;
                    selectedButton1 = null;
                    isBusy = false;

                }
            }, 500);
        }
    }
    public void newGame(){
        retry.setVisibility(View.INVISIBLE);
       shuffleButtonGraphics();
        for (int r = 0; r < numRows; r++)
        {
            for(int c= 0; c < numColumns; c++)
            {
                MemoryButton tempButton = new MemoryButton(this, r, c,buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons [r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }

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
                matchTimer.setText("Time is up!");
                matchScore.setText("Total score: " + matchScoreCount);
                matchScoreCount = 0;
                resetTimer();
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
        matchTimer.setText(timeLeftFormatted);
    }
}



