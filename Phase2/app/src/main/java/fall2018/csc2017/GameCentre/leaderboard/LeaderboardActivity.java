package fall2018.csc2017.GameCentre.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fall2018.csc2017.GameCentre.R;

//TODO: Fix XML, sometimes it goes off screen. Its ugly anyways, needs to look nice
public class LeaderboardActivity extends AppCompatActivity implements LeaderboardContract.View {

    private LeaderboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_tiles_scores);
        presenter = new LeaderboardPresenter(this);

        displayName();
        displayScores();
    }

    /**
     * Displays the name of the game
     */
    public void displayName() {
        presenter.updateName();
    }

    /**
     * Displays the top 10 scores for the current game
     */
    public void displayScores() {
        presenter.updateScores();
    }
}
