package com.allyberms.fives;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String chosenWord;
    private ArrayList<String> wordList;
    private int wordScore=0;
    private HashMap<String,Integer> guesses;
    private String guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startGame();
    }

    public void buttonClick(View button) {
        EditText guessBox = findViewById(R.id.word_guess);

        String entered_guess = guessBox.getText().toString().toLowerCase();
        guess = entered_guess;
        scoreWord();

    }


    public void addToGuesses() {
        guesses.put(guess,wordScore);
    }

    public void startGame(){

        if (wordList==null) {
            setUpWordList();
        }
        selectWord();

    }

    // reset wordScore
    public void reset() {
        wordScore = 0;
    }

    //Set up the word list
    public void setUpWordList(){

    }

    //Score a word
    public void scoreWord() {
        reset();
        for (int c = 0; c < guess.length(); c++)
        {
            // if char in word
            if (chosenWord.indexOf(guess.charAt(c))!=-1)
            {
                wordScore++;
            }
        }
        //check if won game
        if (wordScore==5)
        {
            boolean outcome = checkWord();
            if (outcome){
                wonGame();
            }
        }
        addToGuesses();
    }

    public void wonGame() {
        //to do
    }

    // select a word from the word list
    public void selectWord()
    {
        int wordListLength = wordList.size();
        Random randomGenerator = new Random();
        int randomPosition = randomGenerator.nextInt(wordListLength);
        chosenWord = wordList.get(randomPosition);

    }

    public boolean checkWord() {

        if (guess.equalsIgnoreCase(chosenWord)){
            return true;
        }
        return false;
    }
}
