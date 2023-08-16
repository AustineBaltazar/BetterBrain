package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessGame extends AppCompatActivity implements View.OnClickListener {
    public static int MAX_NUMBER = 0;
    public static final Random RANDOM = new Random();
    private TextView hint;
    private TextView level;
    private EditText input;
    private Button guess;
    int glevel = 1;
    private int numberToFind, numberTries=0;
    Button guessBtn1,guessBtn2,guessBtn3,guessBtn4,guessBtn5,guessBtn6,guessBtn7,guessBtn8,guessBtn9,guessBtn0,
            guessBtnPeriod;
    ImageButton guessBtnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess_game);
        hint = findViewById(R.id.hint);
        input = findViewById(R.id.input);
        guess = findViewById(R.id.guess);
        level = findViewById(R.id.levIndicator);

        guessBtn1 = findViewById(R.id.guessBtn1);
        guessBtn2 = findViewById(R.id.guessBtn2);
        guessBtn3 = findViewById(R.id.guessBtn3);
        guessBtn4 = findViewById(R.id.guessBtn4);
        guessBtn5 = findViewById(R.id.guessBtn5);
        guessBtn6 = findViewById(R.id.guessBtn6);
        guessBtn7 = findViewById(R.id.guessBtn7);
        guessBtn8 = findViewById(R.id.guessBtn8);
        guessBtn9 = findViewById(R.id.guessBtn9);
        guessBtn0 = findViewById(R.id.guessBtn0);
        guessBtnPeriod = findViewById(R.id.guessBtnPeriod);
        guessBtnDel = findViewById(R.id.guessBtnDel);



        guess.setOnClickListener(this);
        guessBtn1.setOnClickListener(this);
        guessBtn2.setOnClickListener(this);
        guessBtn3.setOnClickListener(this);
        guessBtn4.setOnClickListener(this);
        guessBtn5.setOnClickListener(this);
        guessBtn6.setOnClickListener(this);
        guessBtn7.setOnClickListener(this);
        guessBtn8.setOnClickListener(this);
        guessBtn9.setOnClickListener(this);
        guessBtn0.setOnClickListener(this);
        guessBtnPeriod.setOnClickListener(this);
        guessBtnDel.setOnClickListener(this);




        newGame();



    }




    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        if (view == guess){
            guess();
        }
        if (view == guessBtn1){
            input.setText(input.getText()+"1");
        }
        if (view == guessBtn2){
            input.setText(input.getText()+"2");
        }
        if (view == guessBtn3){
            input.setText(input.getText()+"3");
        }
        if (view == guessBtn4){
            input.setText(input.getText()+"4");
        }
        if (view == guessBtn5){
            input.setText(input.getText()+"5");
        }
        if (view == guessBtn6){
            input.setText(input.getText()+"6");
        }
        if (view == guessBtn7){
            input.setText(input.getText()+"7");
        }
        if (view == guessBtn8){
            input.setText(input.getText()+"8");
        }
        if (view == guessBtn9){
            input.setText(input.getText()+"9");
        }
        if (view == guessBtn0){
            input.setText(input.getText()+"0");
        }
        if (view == guessBtnPeriod){
            input.setText(input.getText()+".");
        }
        if (view == guessBtnDel){

            int textLength = input.getText().length();
            if(textLength != 0){
                SpannableStringBuilder select = (SpannableStringBuilder) input.getText();
                select.replace(textLength-1, textLength,"");
                input.setText(select);
                input.setSelection(textLength-1);
            }


        }




    }

    public void guess (){

        try {
            int n = Integer.parseInt(input.getText().toString());
            numberTries++;
            if (numberTries < 5) {
                if (numberTries == 4) {
                    Toast.makeText(this, "You have one last chance to guess!"
                            , Toast.LENGTH_SHORT).show();
                }
                if (numberTries == 1 && n == numberToFind) {
                    Toast.makeText(this, "Nice Guess! You guessed " + numberToFind + " in " + numberTries
                            + " try", Toast.LENGTH_SHORT).show();
                    MAX_NUMBER = MAX_NUMBER + 5;
                    glevel++;
                    nextLevel();
                }

                if (n == numberToFind) {
                    Toast.makeText(this, "Nice Guess! You guessed " + numberToFind + " in " + numberTries
                            + " tries!", Toast.LENGTH_SHORT).show();

                    glevel++;
                    nextLevel();
                } else if (n > numberToFind) {
                    hint.setText(n + " is too high");
                } else if (n < numberToFind) {
                    hint.setText(n + " is too low");

                }
            } else {
                Toast.makeText(this, "You lost! The number is " + numberToFind, Toast.LENGTH_SHORT).show();
                MAX_NUMBER = 5;
                glevel = 1;
                newGame();
            }
        }catch (Exception e){
            e.printStackTrace();
            hint.setText("input an integer");
        }

    }
    private void nextLevel(){
        numberToFind = RANDOM.nextInt(MAX_NUMBER + 5);
        hint.setText("Guess number from 0 to " + MAX_NUMBER);
        level.setText("Level " + glevel);
        input.setText("");
        numberTries=0;

    }


    private void newGame(){
        numberToFind = RANDOM.nextInt(MAX_NUMBER=5);
        hint.setText("Guess number from 0 to " + MAX_NUMBER);
        level.setText("Level " + glevel);
        input.setText("");
        numberTries=0;
    }







}