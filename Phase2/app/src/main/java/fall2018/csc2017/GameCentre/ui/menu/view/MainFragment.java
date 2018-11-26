package fall2018.csc2017.GameCentre.ui.menu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fall2018.csc2017.GameCentre.games.Game.view.DifficultyActivity;
import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.ui.menu.MenuContract;
import fall2018.csc2017.GameCentre.ui.menu.presenter.RecyclerViewAdapter;

public class MainFragment extends Fragment implements MenuContract.View {

    private static final String TAG = MenuActivity.class.getSimpleName();

    // address this code duplication smell later in development cycle
    private ArrayList<String> games = new ArrayList<>();
    private ArrayList<String> gameImages = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        Log.d(TAG, "preparing bitmaps");
        gameImages.add(getString(R.string.tictactoeIcon));
        games.add("TTT");

        gameImages.add(getString(R.string.slidingTilesIcon));
        games.add("Puzzle");

        gameImages.add(getString(R.string.matchingGameIcon));
        games.add("Matching Cards");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), games, gameImages, this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void openPuzzleGame() {
        startActivity(new Intent(getActivity(), DifficultyActivity.class));

    }

    @Override
    public void openTTT() {
        //TODO
    }

    @Override
    public void openMatchingGame() {
        //TODO
    }

}
