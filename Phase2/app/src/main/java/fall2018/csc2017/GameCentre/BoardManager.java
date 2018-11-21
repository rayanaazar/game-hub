package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */

    private Board board;

    /**
     * The current number of undos left
     */
    private int undos;

    /**
     * Maximum number of tiles on the board.
     */

    private List<Tile> tiles = new ArrayList<>();


    /**
     * The number of rows in the Board.
     */

    private int numRows;

    /**
     * The number of columns in the Board.
     */

    private int numCols;

    /**
     * The stack representing the moves
     */
    private Stack<Board> moveStack;

    /**
     * Managing board positions
     * col: describes the column of the board
     * row: describes the row of the board
     * blankIdCol; captures the column position of the blank tile
     * blankIdRow: captures the row position of the blank tile.
     */

    private int col, row, blankIdCol, blankIdRow;

    /**
     * Manage a board that has been loaded.
     *
     * @param board the board
     */
    BoardManager(Board board, int undos) {
        this.board = board;
        this.numCols = board.getNumCols();
        this.numRows = board.getNumRows();
        this.undos = undos;
        this.moveStack = new Stack<>();
    }

    /**
     * Manage a new shuffled board with 0 undos.
     */
    BoardManager(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        createBoard();
        this.board = new Board(tiles, numRows, numCols);
        this.undos = 0;
        this.moveStack = new Stack<>();
    }

    /**
     * Manage a new shuffled board of a given size and number of undos.
     */
    BoardManager(int numRows, int numCols, int undos) {
        this.numRows = numRows;
        this.numCols = numCols;
        createBoard();
        this.board = new Board(tiles, numRows, numCols);
        this.undos = undos;
        this.moveStack = new Stack<>();
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
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

    /**
     * Create a Board and shuffle it given the size of the board.
     */
    private void createBoard() {
        for (int tileNum = 0; tileNum != (numCols * numRows); tileNum++) {
            tiles.add(new Tile(tileNum, numCols * numRows));
        }
        Collections.shuffle(tiles.subList(0, (numCols * numCols) - 1));
        // Check if current board is solvable, and if not, create another board
        if (!isSolvable()) {
            createBoard();
        }
    }

    /**
     * Return whether the current board is solvable following the conditions from
     * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     *
     * @return whether the current board is solvable
     */
    private boolean isSolvable() {
        int inversions = getInversions();

        if (board.getNumCols() % 2 != 0) {
            return inversions % 2 == 0;
        } else {
            if (blankIdRow % 2 == 0) {
                return inversions % 2 == 0;
            } else {
                return inversions % 2 != 0;
            }
        }
    }

    /**
     * Return the number of inversions in this board
     *
     * @return the number of inversions in this board
     */
    private int getInversions() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile tile : board) {
            tiles.add(tile);
        }

        int inversions = 0;
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                if (tiles.get(i).getId() > tiles.get(j).getId()) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    /**
     * Loads the current board to the stack
     */
    public void loadStack() {
        this.moveStack.push(this.getBoard());
    }

    public void undo() {
        this.moveStack.pop();
        this.board = moveStack.pop();
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        boolean solved = true;
        int count = 1;
        for (Tile curr : board) {
            int currentID = curr.getId();
            if (currentID != count || currentID > board.numTiles()) {
                solved = false;
                break;
            }
            count++;
        }

        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        getBoardPositions(position);
        if (checkBlankTile(row == 0 ? null : board.getTile(row - 1, col))) {
            return prepareForSwap(col, row - 1);
        }
        if (checkBlankTile(row == numRows - 1 ? null : board.getTile(row + 1, col))) {
            return prepareForSwap(col, row + 1);
        }
        if (checkBlankTile(col == 0 ? null : board.getTile(row, col - 1))) {
            return prepareForSwap(col - 1, row);
        }
        if (checkBlankTile(col == numCols - 1 ? null : board.getTile(row, col + 1))) {
            return prepareForSwap(col + 1, row);
        }
        return false;
    }

    /**
     * Prepares for swapping by recording the location of the blank tile.
     *
     * @param emptyCol the tile to check
     * @param emptyRow shiftedCol the tile to check
     * @return true by default
     */
    private boolean prepareForSwap(int emptyCol, int emptyRow) {
        blankIdCol = emptyCol;
        blankIdRow = emptyRow;
        return true;
    }

    /**
     * Returns whether or not the current tile is the blank tile
     *
     * @param tileToCheck the tile to check
     * @return whether the tile provided is the blank tile
     */

    private boolean checkBlankTile(Tile tileToCheck) {
        boolean status = false;
        if (tileToCheck != null && tileToCheck.getId() == getBlankID()) {
            status = true;
        }
        return status;

    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {
        getBoardPositions(position);
        if (isValidTap(position)) {
            board.swapTiles(row, col, blankIdRow, blankIdCol);
        }
    }

    /**
     * Calculates the row and column given the current position.
     *
     * @param position the position
     */
    private void getBoardPositions(int position) {
        row = position / numRows;
        col = position % numCols;
    }

    /**
     * Returns the Blank Tiles ID.
     */

    private int getBlankID() {
        return board.numTiles();
    }

    /**
     * Returns the current score.
     *
     * @return the current score.
     */
    public int getScore() {
        return this.moveStack.size();
    }

    public Stack<Board> getMoveStack() {
        return moveStack;
    }

    public void setMoveStack(Stack<Board> moveStack) {
        this.moveStack = moveStack;
    }

}
