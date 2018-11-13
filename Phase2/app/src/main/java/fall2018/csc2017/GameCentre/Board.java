package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    private int numRows;

    /**
     * The number of rows.
     */
    private int numCols;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;


    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.tiles = new Tile[numRows][numCols];

        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != this.getNumRows(); row++) {
            for (int col = 0; col != this.getNumCols(); col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Get the size of each dimension. Works with square boards only.
     * @param area the area (length*width)
     * @return the size of one side
     */
    private int predictSizeFromArea(int area) {
        if(area % 5 == 0) { return 5; }
        if(area % 4 == 0) { return 4; }
        return 3;
    }

    /**
     * Return the number of columns on the board.
     *
     * @return the number of columns on the board.
     */
    public int getNumCols() {
        return this.numCols;
    }

    /**
     * Return the number of rows on the board.
     *
     * @return the number of rows on the board.
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return numRows * numCols;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tmp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tmp;

        setChanged();
        notifyObservers();
    }


    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    private class TileIterator implements Iterator<Tile> {
        /**
         * Initial starting row of the 2D Tile Array
         */

        private int row = 0;

        /**
         * Initial starting column of the 2D Tile Array
         */

        private int col = 0;

        /**
         * Current tile object for Iterator.
         */
        private Tile currT;

        @Override
        public boolean hasNext() {
            return row < numRows && col < numCols;
        }

        @Override
        public Tile next() {
            if (!hasNext()) {
                return null;
            }
            while (row < numRows) {
                while (col < numCols) {
                    if (gettingCurrentAndNextTiles()) {
                        return currT;
                    }
                }
                row++;
            }
            return null;
        }

        /**
         * Performs operations in iterating through the 2D array and assigning the next tile
         * object for the Iterable
         *
         * @return whether the operation to assign a current tile and increment the column count
         * is possible
         */

        private boolean gettingCurrentAndNextTiles() {
            Tile currGetTile = getTile(row, col);
            if (currGetTile != null) {
                currT = currGetTile;
                col++;
                reset();
                return true;
            }
            return false;
        }

        /**
         * Checks whether the column tile is the last column tile in the row. Increments the row
         * if condition is satisfied.
         */
        private void reset() {
            if (col == numCols) {
                col = 0;
                row++;
            }
        }

    }

}
