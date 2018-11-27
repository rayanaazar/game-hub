package fall2018.csc2017.GameCentre.ui.menu.view;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.view.View;

import fall2018.csc2017.GameCentre.R;

public class TabPresenter {

    private View view;
    private Fragment frag;
    private String tab;

    TabPresenter(View view, Fragment frag, String tab) {
        this.view = view;
        this.frag = frag;
        this.tab = tab;
    }

    public void updateScores() {
        String user = "Spencer " + tab;
        int score = 12000;

        int[] idArr = {R.id.firstID, R.id.secondID, R.id.thirdID, R.id.forthID, R.id.fifthID,
                R.id.sixthID, R.id.seventhID, R.id.eighthID, R.id.ninthID, R.id.tenthID};
        int[] nameArr = {R.string.first, R.string.second, R.string.third, R.string.forth,
                R.string.fifth, R.string.sixth, R.string.seventh, R.string.eighth,
                R.string.ninth, R.string.tenth};

        for(int i=0; i<idArr.length; i++){
            TextView place = view.findViewById(idArr[i]);
            place.setText(frag.getString(nameArr[i], user, score));
        }
    }

    public void updateMyScore() {
        int score = 10;
        TextView myScore = view.findViewById(R.id.myScore);
        myScore.setText(frag.getString(R.string.myScore, score));
    }
}
