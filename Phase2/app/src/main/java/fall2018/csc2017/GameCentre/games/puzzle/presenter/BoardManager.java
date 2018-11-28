package fall2018.csc2017.GameCentre.games.puzzle.presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.GameCentre.games.GameBoardManager;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends GameBoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * Maximum number of tiles on the board.
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * The connection to firebase to allow for saving and loading
     */
    private TileFirebaseConnection firebaseConnection;

    /**
     * Managing board positions
     * col: describes the column of the board
     * row: describes the row of the board
     * blankIdCol; captures the column position of the blank tile
     * blankIdRow: captures the row position of the blank tile.
     * undos: number of undos left for the board     */
    private int col, row, blankIdCol, blankIdRow, undos;

    /**
     * Default constructor
     */
    public BoardManager() {}

    /**
     * Manage a new shuffled board of a given size and number of undos.
     */
    public BoardManager(int numRows, int numCols, int undos) {
        super(numRows, numCols);
        createBoard();
        this.board = new Board(tiles, numRows, numCols);
        this.undos = undos;
    }

    /**
     * Returns the number of undos left
     * @return the number of undos left
     */
    public int getUndos() { return undos; }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the current board to board.
     *
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Create a Board and shuffle it given the size of the board.
     */
    private void createBoard() {
        for (int tileNum = 0; tileNum != (numCols * numRows); tileNum++) {
            tiles.add(new Tile(tileNum, numCols * numRows));
        }
        Collections.shuffle(tiles);
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
//        ArrayList<Tile> tiles = new ArrayList<>();
//        int blankRow = 0;
//        for (Tile tile : board) {
//            tiles.add(tile);
//            if (checkBlankTile(tile)) {
//                blankRow = board.getRow(tile);
//            }
//        }

        int inversions = getInversions(tiles);

        if (numCols % 2 != 0) {
            return inversions % 2 == 0;
        } else {
            if (blankIdRow % 2 == 0) {  // Does blankIdRow work??
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
    private int getInversions(List<Tile> tiles) {
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
     * Gets the current score for the game
     * @return the score
     */
    public int getScore() { return this.firebaseConnection.getScore(); }

    /**
     * Loads the current board to the database
     */
    public void load() {
        // Load most recent data
        TileState state = firebaseConnection.load();
        String[] split = state.getDimensions().split("x");

        // Enforce the data obtained
        this.board = new Board(state.getLatestMoveStr(), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        this.undos = state.getUndos();
        // TODO Add timer
    }

    /**
     * Undos our current movement
     */
    public void undo() {
        // TODO: Add error handling, ie: this is the first state, and no more undos
        // Get the most recent data
        TileState currState = firebaseConnection.load();
        this.undos = currState.getUndos() - 1;

        // Get the move string from before the current one
        String moveStr = firebaseConnection.loadPrevMoves().getLatestMoveStr();

        // Enforce data obtained
        String[] split = currState.getDimensions().split("x");
        this.board = new Board(moveStr, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    /**
     * Saves the current board data to the database
     */
    public void save() {
        firebaseConnection.save(this);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        int count = 1;
        for (Tile curr : board) {
            int currentID = curr.getId();
            if (currentID != count || currentID > board.numPieces()) {
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
    public boolean isValidTap(int position) {

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
        return tileToCheck != null && tileToCheck.getId() == getBlankID();
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    public void touchMove(int position) {
        getBoardPositions(position);
        if (isValidTap(position)) {
            board.exchangeTiles(row, col, blankIdRow, blankIdCol);
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
        return board.numPieces();
    }

}





