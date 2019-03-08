package com.codetest.app.monty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Could benefit from something like Butter Knife, or databinding
    TextView numberOfGamesTextView;
    TextView stayWinsTextView;
    TextView switchWinsTextView;
    SeekBar seekBar;

    private final Random rand = new Random();
    private final DecimalFormat df = new DecimalFormat(".##");
    private int totalGames = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    private void setup() {
        numberOfGamesTextView = findViewById(R.id.number_of_games_text);
        stayWinsTextView = findViewById(R.id.stay_wins_text);
        switchWinsTextView = findViewById(R.id.switch_wins_text);
        seekBar = findViewById(R.id.seek_bar);

        setNumberOfGamesText();
        seekBar.setProgress(totalGames);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                totalGames = i;
                setNumberOfGamesText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResultTexts();
            }
        });
    }

    private int montyHallSwitchWins(int totalGames) {
        int wins = 0;

        for (int i = 0; i < totalGames; i++) {
            int prizeBox = rand.nextInt(3);
            int pick = rand.nextInt(3);

            int shownBox = remainingBox(prizeBox, pick);
            int pick2 = remainingBox(shownBox, pick);

            if (pick2 == prizeBox) wins++;
        }

        return wins;
    }

    private int remainingBox(int boxA, int boxB) {
        int otherBox;
        do {
            otherBox = rand.nextInt(3);
        }
        while (otherBox == boxA || otherBox == boxB);
        return otherBox;
    }

    private void setNumberOfGamesText() {
        String numberOfGamesText = getResources().getString(R.string.number_of_games, totalGames);
        numberOfGamesTextView.setText(numberOfGamesText);
    }

    private void setResultTexts() {
        int switchWins = montyHallSwitchWins(totalGames);
        int stayWins = totalGames - switchWins;

        double switchWinsPercentage = (double) switchWins / totalGames * 100;
        double stayWinsPercentage = (double) stayWins / totalGames * 100;

        String stayWinsText = getResources().getString(R.string.wins_by_staying, stayWins, df.format(stayWinsPercentage));
        String switchWinsText = getResources().getString(R.string.wins_by_switching, switchWins, df.format(switchWinsPercentage));

        stayWinsTextView.setText(stayWinsText);
        switchWinsTextView.setText(switchWinsText);
    }
}
