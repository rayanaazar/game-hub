package fall2018.csc2017.GameCentre.ui.menu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fall2018.csc2017.GameCentre.R;

public class MainFragment extends Fragment {

    private static final String TAG = MenuActivity.class.getSimpleName();

    // address this code duplication smell later in development cycle
    private ArrayList<String> games = new ArrayList<>();
    private ArrayList<String> gameImages = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        Log.d(TAG, "preparing bitmaps");
        gameImages.add(getString(R.string.tictactoeIcon));
        games.add("Tic-Tac-Toe");

        gameImages.add(getString(R.string.slidingTilesIcon));
        games.add("Puzzle");

        gameImages.add(getString(R.string.matchingGameIcon));
        games.add("Matching Cards");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), games, gameImages);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
