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
    }

    public void buttonClick(View button) {
        EditText guessBox = findViewById(R.id.word_guess);

        String guess = guessBox.getText().toString();
    }

    //Set up the word list
    public void setUpWordList(){
        if (wordList==null)
        {

        }

    }

    //Score a word
    public void scoreWord() {
        // reset wordScore
        if (wordScore!=0)
        {
            wordScore=0;
        }
        for (int c = 0; c < guess.length(); c++)
        {
            // if char in word
            if (chosenWord.indexOf(guess.charAt(c))!=-1)
            {
                wordScore++;
            }
        }
    }

    // select a word from the word list
    public void selectWord()
    {
        //reset wordgit 
        if (chosenWord!=null)
        {
            chosenWord="";
        }
        int wordListLength = wordList.size();
        Random randomGenerator = new Random();
        int randomPosition = randomGenerator.nextInt(wordListLength);
        chosenWord = wordList.get(randomPosition);

    }
}
