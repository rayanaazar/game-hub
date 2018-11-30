package fall2018.csc2017.GameCentre.games.ttt.model;

import android.arch.lifecycle.ViewModel;

public class CardModelTTT extends ViewModel {


    /**
     * Indicates wbether the card is set.
     */
    private boolean isSet;

    /**
     * The id to find the corresponding drawable for the front of the card.
     */
    private int frontImage;

    /**
     * Create a new model for a card, with background ID frontImage. It will start flipped, with
     * the front image displaying.
     *
     * @param frontImage The id, to match this card with the corresponding drawable.
     */
    public CardModelTTT(int frontImage) {
        this.isSet = true;
        this.frontImage = frontImage;
    }

    public boolean isSet() {
        return isSet;
    }

    public int getFrontImage() {
        return frontImage;
    }

    public void setFlipped() {
        isSet = false;
    }
}
