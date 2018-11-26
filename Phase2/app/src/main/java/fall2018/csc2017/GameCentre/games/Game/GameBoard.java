package fall2018.csc2017.GameCentre.games.Game;
import android.support.annotation.NonNull;

import java.util.Observable;



abstract public class GameBoard extends Observable {
    /**
     * The number of rows.
     */
    protected int numRows;

    /**
     * The number of rows.
     */
    protected int numCols;



    public GameBoard(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }


    /**
     * Return the number of columns in board.
     *
     * @return the number of columns in board.
     */
    protected int getNumCols() {
        return this.numCols;
    }

    /**
     * Return the number of rows in the board.
     *
     * @return the number of rows in board.
     */
    protected int getNumRows() {
        return this.numRows;
    }

    /**
     * Return the number of game pieces in the board.
     *
     * @return the number of game pieces in the board.
     */
    public int numPieces() {
        return numRows * numCols;
    }


    @NonNull
    @Override
    abstract public String toString();

}
















