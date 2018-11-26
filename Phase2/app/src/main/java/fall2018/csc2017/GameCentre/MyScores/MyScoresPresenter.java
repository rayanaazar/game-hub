package fall2018.csc2017.GameCentre.MyScores;

import android.widget.TextView;

import fall2018.csc2017.GameCentre.R;

public class MyScoresPresenter implements MyScoresContract.Presenter{

    private MyScoresActivity view;
    private UserScore user;

    MyScoresPresenter(MyScoresActivity view) {
        this.view = view;
        this.user = new UserScore("12");
    }

    /**
     * Update the screen to display all of the user's scores
     * TODO: Connect with firebase
     * TODO: Object orient those ugly arrays
     */
    public void updateScores() {
        int[] idArr = {R.id.mySTScoreID, R.id.myTTTScoreID, R.id.myMGScoreID};
        int[] nameArr = {R.string.mySTScore, R.string.myTTTScore, R.string.myMGScore};
        int[] scoreArr = {user.getSTScore(), user.getTTTScore(), user.getMTScore()};

        for(int i=0; i<idArr.length; i++) {
            // Update the current game's score
            TextView score = view.findViewById(idArr[i]);
            score.setText(view.getString(nameArr[i], scoreArr[i]));
        }
    }
}
