package fall2018.csc2017.GameCentre.games;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.GameCentre.games.puzzle.BoardManager;

/**
 * The movement controller class
 */
public class MovementController {

    private BoardManager boardManager = null;

    public MovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                // implement high score here ->
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
