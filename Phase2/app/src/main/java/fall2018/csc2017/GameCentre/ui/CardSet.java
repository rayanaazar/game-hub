package fall2018.csc2017.GameCentre.ui;


import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.GameCentre.games.Game.GameBoard;


/**
 * The set of cards to be matched.
 */
public class CardSet extends GameBoard {


    /**
     * The cards on the card set in row-major order.
     */
    private Card[][] cards;

    /**
     * The back of all cards in a set of cards
     */
    private Card backCard;

    /**
     * the order of all the cards in the set
     */
    private Card[][] setOrder;


    /**
     * A new card set  of cards in row-major order.
     * Precondition: len(cards) == numRows * numCols
     *
     * @param cards the cards for the card set.
     */
    CardSet(List<Card> cards, int numRows, int numCols,Card backCard) {
        super(numRows,numCols);
        this.cards = new Card[numRows][numCols];
        this.backCard = backCard;
        this.setOrder = new Card[numRows][numCols];
        createSet(cards,this.cards);
        createSet(cards, this.setOrder);
    }

    /**
     * fill cards with cards.
     *
     * @param c  - cards
     */
    private void createSet(List<Card> c, Card[][] setToFill){

        Iterator<Card> iterator = c.iterator();
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
    public Card getCard(int row, int col) {
        return cards[row][col];
    }

    /**
     * Swap the card with the back card.
     * @param row the card row
     * @param col the card col
     */
    void swapCards(int row, int col) {
        Card cardToSwap = cards[row][col];
        if (cardToSwap.getId() == numCols*numRows + 1){
            cards[row][col] = setOrder[row][col];
        }
        else {
            cards[row][row] = backCard;
        }

    }

    @Override
    @NonNull
    public String toString() {
        return "Set of Cards{" +
                "cards=" + Arrays.toString(cards) +
                '}';
    }


    public Iterator<Card> iterator() {
        return new CardIterator();
    }

    private class CardIterator implements Iterator<Card> {
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
        private Card currT;

        @Override
        public boolean hasNext() {
            return row < numRows && col < numCols;
        }


        @Override
        public Card next() {
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
            Card currGetTile = getCard(row, col);
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

