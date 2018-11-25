package fall2018.csc2017.GameCentre.ui.menu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.ui.menu.MenuContract;

public class MenuActivity extends AppCompatActivity implements MenuContract.View {
    private static final String TAG = MenuActivity.class.getSimpleName();

    // address this code duplication smell later in development cycle
    private ArrayList<String> games = new ArrayList<>();
    private ArrayList<String> gameImages = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelist);
        craeteRecyclerView();
    }

    private void craeteRecyclerView() {
        Log.d(TAG, "preparing bitmaps");
        gameImages.add(getString(R.string.tictactoeIcon));
        games.add("Tic-Tac-Toe");

        gameImages.add(getString(R.string.slidingTilesIcon));
        games.add("Sliding Tiles");

        gameImages.add(getString(R.string.matchingGameIcon));
        games.add("Matching Cards");


        LinearLayoutManager layoutManager = new
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, games, gameImages);
        recyclerView.setAdapter(adapter);


    }


}
