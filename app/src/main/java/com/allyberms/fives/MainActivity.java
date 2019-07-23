package com.allyberms.fives;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String chosenWord;
    private ArrayList<String> wordList;
    private int wordScore=0;
    private ArrayList<String> guesses;
    private String guess;
    private GuessAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        RecyclerView rvGuesses = (RecyclerView) findViewById(R.id.rvGuesses);
        guesses = new ArrayList<String>();
        adapter = new GuessAdapter(guesses);
        // Attach the adapter to the recyclerview to populate items
        rvGuesses.setAdapter(adapter);
        // Set layout manager to position the items
        rvGuesses.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        startGame();
        System.out.println(chosenWord);

    }

    public void buttonClick(View button) {
        EditText guessBox = findViewById(R.id.word_guess);
        String entered_guess = guessBox.getText().toString().toLowerCase();
        guess = entered_guess;
        scoreWord(guess);
        getGuessHistory();
        adapter.notifyDataSetChanged();
    }


    public void addToGuesses(String guess) {
        guesses.add(guess);
    }

    public void startGame(){

        if (wordList==null) {
            try {
                setUpWordList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        chosenWord = selectWord();

    }

    // reset wordScore
    public void reset() {
        wordScore = 0;
    }

    //Set up the word list
    public void setUpWordList() throws IOException {
        wordList = new ArrayList<String>();

        try {
            AssetManager assetManager = getAssets();
            String[] files = assetManager.list("");
            InputStream input = assetManager.open(files[0]);
            BufferedReader inputs =  new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String line;
            String word;
            StringBuffer buffer = new StringBuffer();
            while ((line= inputs.readLine())!=null) {
                buffer.append(line);
                word = buffer.toString();
                wordList.add(word);
                buffer.setLength(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
           } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getGuessHistory(){


        //iterate through guesses hashmap
//        for (Map.Entry<String,Integer> entry : guesses.entrySet()) {
//            guesses_hist.add(entry.getKey()+" : "+entry.getValue().toString());
//        }
//        TextView hist = findViewById(R.id.word_response);
//
//        String historyString = "Guess History:";
//        for (int i=0;i<guessHistory.size();i++) {
//            historyString+="\n"+guessHistory.get(i);
//        }
//        hist.setText(historyString);
    }

    //Score a word
    public void scoreWord(String guess) {
        reset();
        System.out.println(guess+chosenWord);
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
            boolean outcome = checkWord(guess);
            if (outcome){
                wonGame();
            }
        }
        guess+=" : "+String.valueOf(wordScore);
        addToGuesses(guess);

    }

    public void wonGame() {
        //to do
        Toast.makeText(this, "Well done! You win!", Toast.LENGTH_SHORT).show();
    }

    // select a word from the word list
    public String selectWord()
    {
        if (!wordList.isEmpty()) {

            int wordListLength = wordList.size();
            Random randomGenerator = new Random();
            int randomPosition = randomGenerator.nextInt(wordListLength);
            chosenWord = wordList.get(randomPosition);
            System.out.println(chosenWord);
        }
        return chosenWord;
    }

    public boolean checkWord(String guess) {

        return guess.equalsIgnoreCase(chosenWord);

    }
}
