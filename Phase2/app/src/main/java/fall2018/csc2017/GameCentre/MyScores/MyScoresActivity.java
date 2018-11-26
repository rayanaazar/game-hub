package fall2018.csc2017.GameCentre.MyScores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fall2018.csc2017.GameCentre.R;

public class MyScoresActivity extends AppCompatActivity implements MyScoresContract.View {

    private MyScoresPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_my_scores);
        presenter = new MyScoresPresenter(this);

        displayScores();
    }

    public void displayScores() {
        presenter.updateScores();
    }
}
