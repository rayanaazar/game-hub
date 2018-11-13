package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity_tiles_scores.
 */
public class GameActivity extends ActivityReaderWriter implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * The gesture detector
     */
    private GestureDetectGridView gridView;

    /**
     * column width and height based on device size
     */
    private static int columnWidth, columnHeight;

    /**
     * Updates the on screen score to match the actual score
     */
    public void updateScore() {
        TextView text = findViewById(R.id.autoCompleteTextView);
        text.setText(getString(R.string.move_counter, boardManager.getScore()));
    }

    /**
     * Display everything on screen based on what we currently have access to
     */
    public void display() {
        updateTileButtons();
        updateScore();
        boardManager.loadStack();
        save(boardManager.getMoveStack());
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    /**
     * Initializes the boardManager with these dimensions
     */
    public void getDimensions() {
        Intent intentGetDimensions = getIntent();
        int dimension = intentGetDimensions.getIntExtra("DIMENSION", 4);
        boardManager = new BoardManager(dimension, dimension);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDimensions();
        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity_tiles_scores
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getNumCols());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        updateScore();
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getNumCols();
                        columnHeight = displayHeight / boardManager.getNumRows();

                        display();
                    }
                });
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getNumRows(); row++) {
            for (int col = 0; col != boardManager.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getNumRows();
            int col = nextPos % boardManager.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }
}
