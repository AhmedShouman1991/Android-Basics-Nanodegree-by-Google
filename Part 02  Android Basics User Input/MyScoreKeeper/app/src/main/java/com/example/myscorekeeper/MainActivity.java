package com.example.myscorekeeper;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final int TOUCHDOWN_POINTS = 6;
    final int FIELD_GOAL_POINTS = 3;
    final int EXTRA_POINT_1_POINT = 1;
    final int EXTRA_POINT_2_POINTS = 2;
    final int SAFETY_POINTS = 2;

    int team_a_score = 0;
    int team_b_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Update Scores
     */
    public void resetScores(View v) {
        team_a_score = 0;
        team_b_score = 0;
        displayTeamAScore(team_a_score);
        displayTeamBScore(team_b_score);
    }

    public void updateTeamScore(View v) {
        switch(v.getId()) {
            case R.id.team_a_touchdown:
                team_a_score += TOUCHDOWN_POINTS;
                break;
            case R.id.team_a_field_goal:
                team_a_score += FIELD_GOAL_POINTS;
                break;
            case R.id.team_a_extra_point_1:
                team_a_score += EXTRA_POINT_1_POINT;
                break;
            case R.id.team_a_extra_point_2:
                team_a_score += EXTRA_POINT_2_POINTS;
                break;
            case R.id.team_a_safety:
                team_a_score += SAFETY_POINTS;
                break;

            case R.id.team_b_touchdown:
                team_b_score += TOUCHDOWN_POINTS;
                break;
            case R.id.team_b_field_goal:
                team_b_score += FIELD_GOAL_POINTS;
                break;
            case R.id.team_b_extra_point_1:
                team_b_score += EXTRA_POINT_1_POINT;
                break;
            case R.id.team_b_extra_point_2:
                team_b_score += EXTRA_POINT_2_POINTS;
                break;
            case R.id.team_b_safety:
                team_b_score += SAFETY_POINTS;
                break;

            default:
                break;
        }

        displayTeamAScore(team_a_score);
        displayTeamBScore(team_b_score);
    }

    /**
     * Display Scores
     */
    public void displayTeamAScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayTeamBScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
