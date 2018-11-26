package fall2018.csc2017.GameCentre;

import android.util.SparseIntArray;

import java.io.Serializable;

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
     * Collection of drawables for the Card game.
     */
    private int[] drawables = new int[]{
            R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4,
            R.drawable.card_5, R.drawable.card_6, R.drawable.card_7, R.drawable.card_8,
            R.drawable.card_w
    };


    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background
     */
    Card(int backgroundId, int boardSize) {
        this.boardSize = boardSize;
        this.id = backgroundId + 1;
        this.background = drawables[backgroundId - 1];
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
     * Compares two cards
     * @param o other Card
     * @return int based on the values compared
     */
    public int compareTo( fall2018.csc2017.GameCentre.Card o) {
        return o.id - this.id;
    }
}
