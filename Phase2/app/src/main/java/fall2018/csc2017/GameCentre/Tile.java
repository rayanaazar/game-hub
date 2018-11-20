package fall2018.csc2017.GameCentre;

import android.util.SparseIntArray;

import java.io.Serializable;


/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {


    /**
     * The numbers of tiles that needs to be mapped.
     */
    private int boardSize;

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    Tile(int id, int background, int boardSize) {
        this.boardSize = boardSize;
        this.id = id;
        this.background = background;
    }


    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background
     */
    Tile(int backgroundId, int boardSize) {
        this.boardSize = boardSize;
        this.id = backgroundId + 1;
        SparseIntArray lookup = CreateLookUp(); // Set up the background based on id.
        this.background = lookup.get(id);
    }

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }


    /**
     * Returns a SparseIntArray that maps each tile key to a drawable integer image pointer
     *
     * @return returns an object has a mapped range of ints to images
     */

    private SparseIntArray CreateLookUp() {
        SparseIntArray backgroundIDLookup = new SparseIntArray();
        int[] drawables = new int[]{
                R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3, R.drawable.tile_4,
                R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7, R.drawable.tile_8,
                R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11, R.drawable.tile_12,
                R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15, R.drawable.tile_16,
                R.drawable.tile_17, R.drawable.tile_18, R.drawable.tile_19, R.drawable.tile_20,
                R.drawable.tile_21, R.drawable.tile_22, R.drawable.tile_23, R.drawable.tile_24,
                R.drawable.tile_w
        };

        for (int i = 0; i < boardSize - 1; i++) {
            backgroundIDLookup.append(i + 1, drawables[i]);
        }
        return backgroundIDLookup;
    }

    /**
     * Compares two tiles
     * @param o other Tile
     * @return int based on the values compared
     */
    @Override
    public int compareTo( Tile o) {
        return o.id - this.id;
    }
}
