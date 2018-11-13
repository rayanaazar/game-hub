package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity for working with scores
 */
public class ScoresActivity extends AppCompatActivity {

    private String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        // Setup buttons
        myScoresButton();
        STScoresButton();
        TTTScoresButton();
        MGScoresButton();
    }

    /**
     * Listener for the My Scores button
     */
    private void myScoresButton() {
        Button myScores = findViewById(R.id.MyScores);
        myScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNewView(MyScoresActivity.class);
            }
        });
    }

    /**
     * Listener for the Sliding Tiles Scores button
     */
    private void STScoresButton() {
        Button STScores = findViewById(R.id.SlidingTilesScores);
        STScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = "Sliding Tiles";
                switchToNewView(LeaderboardActivity.class);
            }
        });
    }

    /**
     * Listener for the Tic Tac Toe Scores button
     */
    private void TTTScoresButton() {
        Button TTTScores = findViewById(R.id.TicTacToeScores);
        TTTScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = "Tic Tac Toe";
                switchToNewView(LeaderboardActivity.class);
            }
        });
    }

    /**
     * Listener for the Matching Game Scores button
     */
    private void MGScoresButton() {
        Button MGScores = findViewById(R.id.MatchingGameScores);
        MGScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = "Matching Game";
                switchToNewView(LeaderboardActivity.class);
            }
        });
    }

    /**
     * Ends the current activity_tiles_scores, and switches to the next one given.
     * @param cls The activity_tiles_scores Class object to switch to
     */
    private void switchToNewView(Class cls) {
        Intent data = new Intent(ScoresActivity.this, cls);
        data.putExtra("game", game);
        startActivityForResult(data, 1);
        finish();
    }
}
