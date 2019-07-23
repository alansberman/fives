package com.allyberms.fives;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView guess;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            guess = itemView.findViewById(R.id.guess);
        }

    }


    // Store a member variable for the contacts
    private ArrayList<String> mGuesses;

    // Pass in the contact array into the constructor
    public GuessAdapter(ArrayList<String> guesses) {
        mGuesses= guesses;
    }

    @Override
    public GuessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.guess_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(GuessAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String guess = mGuesses.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.guess;
        textView.setText(guess);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mGuesses.size();
    }
}
