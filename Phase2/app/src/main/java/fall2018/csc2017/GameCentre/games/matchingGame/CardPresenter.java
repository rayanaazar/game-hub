package fall2018.csc2017.GameCentre.games.matchingGame;


import android.support.annotation.NonNull;
import android.util.SparseIntArray;

import fall2018.csc2017.GameCentre.R;
import fall2018.csc2017.GameCentre.games.GamePiece;

public class CardPresenter extends GamePiece {

    private CardModel cardModel;

    private CardView cardView;


    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the background
     */
    CardPresenter(int backgroundId, int boardSize, @NonNull final CardView cardView) {
        super(backgroundId, boardSize);
        cardModel = new CardModel(CreateLookUp().get(backgroundId));
        this.cardView = cardView;
        this.cardView.setPresenter(this);
        this.cardView.setBackgroundResource(R.drawable.card_w);
    }

    void flip() {
        if (!cardModel.isFlipped() && !cardModel.isMatched()) {
            cardModel.setFlipped(true);
            cardView.setBackgroundResource(getBackground());
        }

        else if (cardModel.isFlipped() && !cardModel.isMatched()) {
            cardModel.setFlipped(false);
            cardView.setBackgroundResource(R.drawable.card_w);
        }
    }

    @Override
    public int getBackground() {
        return cardModel.getFrontImage();
    }

    /**
     * Returns a SparseIntArray that maps each card key to a drawable integer image pointer
     *
     * @return returns an object has a mapped range of ints to images
     */
    public SparseIntArray CreateLookUp() {
        SparseIntArray backgroundIDLookup = new SparseIntArray();
        int[] drawables = new int[] {R.drawable.card_1, R.drawable.card_2,
                R.drawable.card_3, R.drawable.card_4, R.drawable.card_5, R.drawable.card_6,
                R.drawable.card_7, R.drawable.card_8, R.drawable.card_w };

        for (int i = 0; i < (boardSize * boardSize / 2) - 1; i++) {
            backgroundIDLookup.append(i + 1, drawables[i]);
        }
        return backgroundIDLookup;
    }

}

