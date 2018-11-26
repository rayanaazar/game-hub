package fall2018.csc2017.GameCentre.games;


import android.support.annotation.NonNull;

import java.util.Observable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.GameCentre.games.Tile;

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
    public Board(List<Tile> tiles, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.tiles = new Tile[numRows][numCols];

        Iterator<Tile> iterator = tiles.iterator();

        for (int row = 0; row != this.getNumRows(); row++) {
            for (int col = 0; col != this.getNumCols(); col++) {
                this.tiles[row][col] = iterator.next();
            }
        }
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
     * Get the array of tiles
     * @return the array of tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Set the array of tiles
     * @param tiles a 2D array of tiles
     */
    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numTiles() {
        return numRows * numCols;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }


    @NonNull
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
