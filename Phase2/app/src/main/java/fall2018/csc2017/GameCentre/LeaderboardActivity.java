package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//TODO: Fix XML, sometimes it goes off screen. Its ugly anyways, needs to look nice
public class LeaderboardActivity extends AppCompatActivity {

    private String game;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_tiles_scores);
        game = getIntent().getStringExtra("game");

        updateName();
        updateScores();
    }

    /**
     * Updates the name based on what game's top scores we're looking at.
     */
    private void updateName() {
        TextView title = findViewById(R.id.topTitleID);
        title.setText(getString(R.string.topTitle, game));
    }

    /**
     * Updates the scores based on the leaderboard for this game in the database.
     * Doesn't record ties because if two people tied, the person who got the score first is the
     * winner.
     * TODO: Hook up to database
     * TODO: Make look not ugly, use iteration and maybe a sparse int array
     */
    private void updateScores() {
        String user = "Spencer";
        int score = 12000;

        TextView first = findViewById(R.id.firstID);
        first.setText(getString(R.string.first, user, score));

        TextView second = findViewById(R.id.secondID);
        second.setText(getString(R.string.second, user, score));

        TextView third = findViewById(R.id.thirdID);
        third.setText(getString(R.string.third, user, score));

        TextView forth = findViewById(R.id.forthID);
        forth.setText(getString(R.string.forth, user, score));

        TextView fifth = findViewById(R.id.fifthID);
        fifth.setText(getString(R.string.fifth, user, score));

        TextView sixth = findViewById(R.id.sixthID);
        sixth.setText(getString(R.string.sixth, user, score));

        TextView seventh = findViewById(R.id.seventhID);
        seventh.setText(getString(R.string.seventh, user, score));

        TextView eighth = findViewById(R.id.eighthID);
        eighth.setText(getString(R.string.eighth, user, score));

        TextView ninth = findViewById(R.id.ninthID);
        ninth.setText(getString(R.string.ninth, user, score));

        TextView tenth = findViewById(R.id.tenthID);
        tenth.setText(getString(R.string.tenth, user, score));
    }
}
