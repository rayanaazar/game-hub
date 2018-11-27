package fall2018.csc2017.GameCentre.games;

import java.util.Stack;

public abstract class GameBoardManager {

    /**
     * The number of rows in the board game.
     */
    protected int numRows;

    /**
     * The number of columns in the board game.
     */

    protected int numCols;

    /**
     * The stack representing the moves
     */
    protected Stack<? extends GameBoard> moveStack;

    protected GameBoard board;


    /**
     * Manage a new shuffled set of game pieces of a given size .
     */
    public GameBoardManager(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.moveStack = new Stack<>();
    }


    /**
     * Return the number of rows in the board.
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Return the number of columns in the board.
     */

    public int getNumCols() {
        return this.numCols;
    }


    public abstract boolean puzzleSolved();


    public abstract void touchMove(int position);


    public abstract boolean isValidTap(int position);


    /**
     * Returns the current score.
     *
     * @return the current score.
     */
    public int getScore() {
        return this.moveStack.size();
    }

    public Stack<? extends GameBoard> getMoveStack() {
        return moveStack;
    }

    public void setMoveStack(Stack<? extends GameBoard> moveStack) {
        this.moveStack = moveStack;
    }

}
