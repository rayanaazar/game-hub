package fall2018.csc2017.GameCentre.ui.menu.view.leaderboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fall2018.csc2017.GameCentre.R;

// TODO: Generalize?

public class MatchingGameTab extends Fragment {

    private TabPresenter presenter;
    private final String GAME = "matchingGame";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leaderboard_tab_fragment, container,false);
        presenter = new TabPresenter(v, this, GAME);

        presenter.updateMyScore();
        presenter.updateScores();
        return v;
    }
}
