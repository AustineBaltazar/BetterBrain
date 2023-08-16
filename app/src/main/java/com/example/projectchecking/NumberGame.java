package com.example.projectchecking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class NumberGame extends AppCompatActivity {

    TextView level_text, the_number;
    EditText number_input;
    Button confirm_button;
    Random r;
    int currentLevel = 1;
    String generatedNumber;
    Button numberBtn1,numberBtn2,numberBtn3,numberBtn4,numberBtn5,numberBtn6,numberBtn7,numberBtn8,numberBtn9,numberBtn0,
            numberBtnPeriod;
    ImageButton numberBtnDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_game);


        level_text = findViewById(R.id.tv_level1);
        the_number = findViewById(R.id.tv_number0);
        number_input = findViewById(R.id.et_number);
        confirm_button = findViewById(R.id.b_confirm );
        r = new Random();

        number_input.setVisibility(View.GONE);
        confirm_button.setVisibility(View.INVISIBLE);
        the_number.setVisibility(View.VISIBLE);

        level_text.setText("Level:" + currentLevel);

        generatedNumber= (generatedNumber(currentLevel));
        the_number.setText(generatedNumber);

        numberBtn1 = findViewById(R.id.numBtn1);
        numberBtn2 = findViewById(R.id.numBtn2);
        numberBtn3 = findViewById(R.id.numBtn3);
        numberBtn4 = findViewById(R.id.numBtn4);
        numberBtn5 = findViewById(R.id.numBtn5);
        numberBtn6 = findViewById(R.id.numBtn6);
        numberBtn7 = findViewById(R.id.numBtn7);
        numberBtn8 = findViewById(R.id.numBtn8);
        numberBtn9 = findViewById(R.id.numBtn9);
        numberBtn0 = findViewById(R.id.numBtn0);
        numberBtnPeriod = findViewById(R.id.numBtnPeriod);
        numberBtnDelete = findViewById(R.id.numBtnDel);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                number_input.setVisibility(View.VISIBLE);
                confirm_button.setVisibility(View.VISIBLE);
                the_number.setVisibility(View.GONE);
                number_input.requestFocus();
            }
        },2000);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check  if the number  are the same
                if (generatedNumber.equals(number_input.getText().toString())) {

                    // hide the input and the button and show the number

                    number_input.setVisibility(View.GONE);
                    confirm_button.setVisibility(View.INVISIBLE);
                    the_number.setVisibility(View.VISIBLE);

                    //remove text from input
                    number_input.setText("");

                    // increase the level

                    currentLevel++;

                    // display the current level
                    level_text.setText("Level:" + currentLevel);

                    //display random numbers according to the level

                    generatedNumber = (generatedNumber(currentLevel));
                    the_number.setText(generatedNumber);

                    //display the elements after two seconds and hide the number
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            number_input.setVisibility(View.VISIBLE);
                            confirm_button.setVisibility(View.VISIBLE);
                            the_number.setVisibility(View.GONE);

                            number_input.requestFocus();
                        }
                    }, 2000);
                } else {
                    level_text.setText("Game Over! The number was :\n" + generatedNumber);
                    confirm_button.setText("New Game");
                    confirm_button = (Button) findViewById(R.id.b_confirm);
                    confirm_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            newGame();
                        }
                    });
                }
            }
        });

        // Number buttons

        numberBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"1");
            }
        });
        numberBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"2");
            }
        });
        numberBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"3");
            }
        });
        numberBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"4");
            }
        });
        numberBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"5");
            }
        });
        numberBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"6");
            }
        });
        numberBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"7");
            }
        });
        numberBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"8");
            }
        });
        numberBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"9");
            }
        });
        numberBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+"0");
            }
        });
        numberBtnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_input.setText(number_input.getText()+".");
            }
        });
        numberBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int textLen = number_input.getText().length();
                if(textLen != 0){
                    SpannableStringBuilder select = (SpannableStringBuilder) number_input.getText();
                    select.replace(textLen-1, textLen,"");
                    number_input.setText(select);
                    number_input.setSelection(textLen-1);
                }
            }
        });
    }



    private String generatedNumber(int digits) {
        String output ="";
        for (int i= 0; i < digits; i++){
            int randomDigits = r.nextInt(10);
            output = output + "" + randomDigits;
        }
        return output;
    }
private void newGame(){

    startActivity(getIntent());
    finish();
    overridePendingTransition(0, 0);
}


}