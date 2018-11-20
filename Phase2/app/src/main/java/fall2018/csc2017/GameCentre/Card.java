package fall2018.csc2017.GameCentre;

import android.util.SparseIntArray;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.R;

/**
 * A card in a matching cards game.
 */
public class Card implements Comparable<fall2018.csc2017.GameCentre.Card>, Serializable {

    /**
     * The numbers of cards that needs to be mapped.
     */
    private int boardSize;

    /**
     * The background id to find the card image.
     */
    private int background;

    /**
     * The unique id of a card.
     */
    private int id;



    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background
     */
    Card(int backgroundId, int boardSize) {
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
     * Return the card id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }


    /**
     * Returns a SparseIntArray that maps each card key to a drawable integer image pointer
     *
     * @return returns an object has a mapped range of ints to images
     */

    private SparseIntArray CreateLookUp() {
        SparseIntArray backgroundIDLookup = new SparseIntArray();
        int[] drawables = new int[]{
                R.drawable.Card_1, R.drawable.Card_2, R.drawable.Card_3, R.drawable.Card_4,
                R.drawable.Card_5, R.drawable.Card_6, R.drawable.Card_7, R.drawable.Card_8,
                R.drawable.Card_9, R.drawable.Card_10, R.drawable.Card_11, R.drawable.Card_12,
                R.drawable.Card_13, R.drawable.Card_14, R.drawable.Card_15, R.drawable.Card_16,
                R.drawable.Card_W
        };

        for (int i = 0; i < boardSize - 1; i++) {
            backgroundIDLookup.append(i + 1, drawables[i]);
        }
        return backgroundIDLookup;
    }

    /**
     * Compares two cards
     * @param o other Card
     * @return int based on the values compared
     */
    public int compareTo( fall2018.csc2017.GameCentre.Card o) {
        return o.id - this.id;
    }
}
