package fall2018.csc2017.GameCentre.ui;

import android.util.SparseIntArray;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.games.Game.GamePiece;

/**
 * A card in a matching cards game.
 */
public class Card extends GamePiece {
    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background
     */
    Card(int backgroundId, int boardSize) {
        super(backgroundId,boardSize);}


    /**
     * Returns a SparseIntArray that maps each card key to a drawable integer image pointer
     *
     * @return returns an object has a mapped range of ints to images
     */
    public SparseIntArray CreateLookUp() {
        SparseIntArray backgroundIDLookup = new SparseIntArray();
        int[] drawables = new int[]{ R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4,
                R.drawable.card_5, R.drawable.card_6, R.drawable.card_7, R.drawable.card_8,
                R.drawable.card_w
        };

        for (int i = 0; i < (Math.log(boardSize)/Math.log(2.0)) - 1; i++) {
            backgroundIDLookup.append(i + 1, drawables[i]);
        }
        return backgroundIDLookup;
    }
}


