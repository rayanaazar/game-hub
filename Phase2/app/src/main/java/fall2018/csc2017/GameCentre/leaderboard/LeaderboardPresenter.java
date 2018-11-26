package fall2018.csc2017.GameCentre.leaderboard;

import android.widget.TextView;

import fall2018.csc2017.GameCentre.R;

public class LeaderboardPresenter implements LeaderboardContract.Presenter {

    private LeaderboardActivity view;
    private String game;

    LeaderboardPresenter(LeaderboardActivity view) {
        this.view = view;
        // TODO: Get game name dynamically
        game = view.getIntent().getStringExtra("game");
    }

    /**
     * Updates the scores based on the leaderboard for this game in the database.
     * Doesn't record ties because if two people tied, the person who got the score first is the
     * winner.
     * TODO: Hook up to database
     * TODO: Make look not ugly, use iteration and maybe a sparse int array
     */
    public void updateScores() {
        String user = "Spencer";
        int score = 12000;

        int[] idArr = {R.id.firstID, R.id.secondID, R.id.thirdID, R.id.forthID, R.id.fifthID,
                       R.id.sixthID, R.id.seventhID, R.id.eighthID, R.id.ninthID, R.id.tenthID};
        int[] nameArr = {R.string.first, R.string.second, R.string.third, R.string.forth,
                         R.string.fifth, R.string.sixth, R.string.seventh, R.string.eighth,
                         R.string.ninth, R.string.tenth};

        for(int i=0; i<idArr.length; i++){
            TextView first = view.findViewById(idArr[i]);
            first.setText(view.getString(nameArr[i], user, score));
        }
    }

    /**
     * Updates the name of the game
     */
    public void updateName() {
        TextView title = view.findViewById(R.id.topTitleID);
        title.setText(view.getString(R.string.topTitle, game));
    }
}
