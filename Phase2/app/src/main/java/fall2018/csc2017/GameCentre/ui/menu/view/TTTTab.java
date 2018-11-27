package fall2018.csc2017.GameCentre.ui.menu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fall2018.csc2017.GameCentre.R;

public class TTTTab extends Fragment {

    private TabPresenter presenter;
    private final String game = "Tic Tac Toe";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leaderboard_tab_fragment, container,false);
        presenter = new TabPresenter(v, this, game);

        presenter.updateMyScore();
        presenter.updateScores();
        return v;
    }
}
