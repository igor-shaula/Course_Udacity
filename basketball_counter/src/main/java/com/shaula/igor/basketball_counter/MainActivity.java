package com.shaula.igor.basketball_counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA, scoreTeamB, pricePerCup = 5;
    private TextView tvCountTeamA, tvCountTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCountTeamA = (TextView) findViewById(R.id.tvCountTeamA);
        tvCountTeamB = (TextView) findViewById(R.id.tvCountTeamB);
    }

    public void plus3A(View view) {
        scoreTeamA += 3;
        displayResultA();
    }

    public void plus2A(View view) {
        scoreTeamA += 2;
        displayResultA();
    }

    public void plus1A(View view) {
        scoreTeamA++;
        displayResultA();
    }

    private void displayResultA() {
        int total = pricePerCup * scoreTeamA;
        tvCountTeamA.setText(String.valueOf(total));
    }

    public void plus3B(View view) {
        scoreTeamB += 3;
        displayResultB();
    }

    public void plus2B(View view) {
        scoreTeamB += 2;
        displayResultB();
    }

    public void plus1B(View view) {
        scoreTeamB++;
        displayResultB();
    }

    private void displayResultB() {
        int total = pricePerCup * scoreTeamB;
        tvCountTeamB.setText(String.valueOf(total));
    }

    public void resetScore(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayResultA();
        displayResultB();
    }
}