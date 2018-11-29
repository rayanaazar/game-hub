package fall2018.csc2017.GameCentre.games.puzzle.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.games.puzzle.presenter.BoardManager;
import fall2018.csc2017.GameCentre.games.view.GameActivity;

/**
 * The difficulty activity_tiles_scores class
 */

//TODO find out a way template off of 1 xml activity for difficulty but rebase it to meet each game's requirement
public class DifficultyActivity extends AppCompatActivity {

    private LinearLayout easy, okay, difficult, extreme;

    private int undos, numRows, numCols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        createBindings();

        add3x3OnClickListener();
        add4x4OnClickListener();
        add5x5OnClickListener();
        noUndosOnClickListener();
    }


    private void createBindings()
    {
        easy = findViewById(R.id.nineTiles);
        okay = findViewById(R.id.sixteenTiles);
        difficult = findViewById(R.id.twentyFiveTiles);
        extreme = findViewById(R.id.thirtySixTiles);
    }

    /**
     * Click listener for the 9 tile difficulty with 5 undo's.
     */
    private void add3x3OnClickListener() {
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undos = 5;
                numRows = 3;
                numCols = 3;
                beginGame(v);
            }
        });
    }

    /**
     * Click listener for the 16 tile difficulty with 3 undo's.
     */
    private void add4x4OnClickListener() {
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undos = 3;
                numRows = 4;
                numCols = 4;
                beginGame(v);
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    private void add5x5OnClickListener() {
        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undos = 1;
                numRows = 5;
                numCols = 5;
                beginGame(v);
            }
        });
    }

    private void noUndosOnClickListener() {
        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undos = 0;
                numRows = 5;
                numCols = 5;
                beginGame(v);
            }
        });
    }

    /**
     * Begin the GameActivity to play the game.
     */
    public void beginGame(View v) {
        Intent game = new Intent(this, GameActivity.class);
        game.putExtra("undos", undos);
        game.putExtra("cols", numRows);
        game.putExtra("rows", numCols);
        startActivity(game);
        finish();
    }
}


