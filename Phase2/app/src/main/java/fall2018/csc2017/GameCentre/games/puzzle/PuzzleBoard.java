package fall2018.csc2017.GameCentre.games.puzzle;

import java.util.List;

import fall2018.csc2017.GameCentre.games.Board;
import fall2018.csc2017.GameCentre.games.Tile;

public class PuzzleBoard extends Board {
    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles   the tiles for the board
     * @param numRows
     * @param numCols
     */
    public PuzzleBoard(List<Tile> tiles, int numRows, int numCols) {
        super(tiles, numRows, numCols);
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void exchangeTiles(int row1, int col1, int row2, int col2) {
        Tile tmp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tmp;

        setChanged();
        notifyObservers();
    }
}
