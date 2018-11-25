package fall2018.csc2017.GameCentre.games.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.GameCentre.games.ActivityReaderWriter;
import fall2018.csc2017.GameCentre.R;

/**
 * The difficulty activity_tiles_scores class
 */
public class DifficultyActivity extends ActivityReaderWriter {

    /**
     * The current boardManager
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        add3x3Button();
        add4x4Listener();
        add5x5Listener();
    }

    /**
     * Activate the 3x3 button.
     */
    private void add3x3Button() {
        Button easyButton = findViewById(R.id.EasyButton);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(3, 3, 5);
                switchToGame(v);
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    private void add4x4Listener() {
        Button mediumButton = findViewById(R.id.MediumButton);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(4, 4, 3);
                switchToGame(v);
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    private void add5x5Listener() {
        Button hardButton = findViewById(R.id.HardButton);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new BoardManager(5, 5, 1);
                switchToGame(v);
            }
        });
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    public void switchToGame(View v) {
        Intent data = new Intent(this, GameActivity.class);
        data.putExtra("DIMENSION", boardManager.getNumCols());
        startActivityForResult(data, 1);
        finish();
    }
}


