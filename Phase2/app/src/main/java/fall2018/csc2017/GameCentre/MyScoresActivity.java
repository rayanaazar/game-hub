package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MyScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_my_scores);
        updateScores();
    }

    /**
     * Update the screen to display all of the user's scores
     * TODO: Connect with firebase
     */
    private void updateScores() {
        // Update the Sliding Tiles score
        TextView STScore = findViewById(R.id.mySTScoreID);
        STScore.setText(getString(R.string.mySTScore, getSTScore()));

        // Update the Tic Tac Toe score
        TextView TTTScore = findViewById(R.id.myTTTScoreID);
        TTTScore.setText(getString(R.string.myTTTScore, getTTTScore()));

        // Update the Matching Game score
        TextView MGScore = findViewById(R.id.myMGScoreID);
        MGScore.setText(getString(R.string.myMGScore, getMGScore()));
    }

    /**
     * Get the user's Sliding Tiles score from the database
     * @return The user's score in Sliding Tiles
     */
    private int getSTScore() {
        return 0;
    }

    /**
     * Get the user's Tic Tac Toe score from the database
     * @return The user's score in Tic Tac Toe
     */
    private int getTTTScore() {
        return 0;
    }

    /**
     * Get the user's Matching Game score from the database
     * @return The user's score in Matching Game
     */
    private int getMGScore() {
        return 0;
    }
}
