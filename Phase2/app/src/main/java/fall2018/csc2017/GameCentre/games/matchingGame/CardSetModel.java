package fall2018.csc2017.GameCentre.games.matchingGame;


import android.databinding.Observable;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.GameCentre.games.GameBoard;
import fall2018.csc2017.GameCentre.games.GamePiece;


/**
 * The set of cards to be matched.
 */
public class CardSetModel extends GameBoard {


    /**
     * The cards on the card set in row-major order.
     */
    private CardPresenter[][] cards;


    /**
     * the order of all the cards in the set
     */
    private CardPresenter[][] setOrder;


    /**
     * A new card set  of cards in row-major order.
     * Precondition: len(cards) == numRows * numCols
     *
     * @param cards the cards for the card set.
     */
    CardSetModel(List<CardPresenter> cards, int numRows, int numCols) {
        super(numRows, numCols);
        this.cards = new CardPresenter[numRows][numCols];
        this.setOrder = new CardPresenter[numRows][numCols];
        createSet(cards, this.cards);
        createSet(cards, this.setOrder);
    }

    /**
     * fill cards with cards.
     *
     * @param c - cards
     */
    private void createSet(List<CardPresenter> c, CardPresenter[][] setToFill) {

        Iterator<CardPresenter> iterator = c.iterator();
        for (int row = 0; row != this.getNumRows(); row++) {
            for (int col = 0; col != this.getNumCols(); col++) {
                setToFill[row][col] = iterator.next();
            }
        }

    }


    /**
     * Return the card at (row, col)
     *
     * @param row the card row
     * @param col the card column
     * @return the card at (row, col)
     */
    public CardPresenter getCard(int row, int col) {
        return cards[row][col];
    }

    /**
     * Swap the card with the back card.
     *
     * @param row the card row
     * @param col the card col
     */
    void swapCards(int row, int col) {
        CardPresenter cardToSwap = getCard(row, col);
        cardToSwap.flip();
    }

    @Override
    @NonNull
    public String toString() {
        return "Set of Cards{" +
                "cards=" + Arrays.toString(cards) +
                '}';
    }


    public Iterator<CardPresenter> iterator() {
        return new CardIterator();
    }

    private class CardIterator implements Iterator<CardPresenter> {
        /**
         * Initial starting row of the 2D Card Array
         */

        private int row = 0;

        /**
         * Initial starting column of the 2D Card Array
         */

        private int col = 0;

        /**
         * Current Card object for Iterator.
         */
        private CardPresenter currT;

        @Override
        public boolean hasNext() {
            return row < numRows && col < numCols;
        }


        @Override
        public CardPresenter next() {
            if (!hasNext()) {
                return null;
            }
            while (row < numRows) {
                while (col < numCols) {
                    if (gettingCurrentAndNextCards()) {
                        return currT;
                    }
                }
                row++;
            }
            return null;
        }

        /**
         * Performs operations in iterating through the 2D array and assigning the next card
         * object for the Iterable
         *
         * @return whether the operation to assign a current card and increment the column count
         * is possible
         */

        private boolean gettingCurrentAndNextCards() {
            CardPresenter currGetTile = getCard(row, col);
            if (currGetTile != null) {
                currT = currGetTile;
                col++;
                reset();
                return true;
            }
            return false;
        }

        /**
         * Checks whether the column card is the last column card in the row. Increments the row
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

