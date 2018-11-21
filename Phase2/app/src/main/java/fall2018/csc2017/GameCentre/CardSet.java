package fall2018.csc2017.GameCentre;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



/**
 * The set of cards to be matched.
 */
public class CardSet {


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
    private Card[][] cards;

    /**
     * The back of all cards in a set of cards
     */
    private Card backcard;

    /**
     * the order of all the cards in the set
     */
    private Card[][] setOrder;


    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param cards the tiles for the board
     */
    CardSet(List<Card> cards, int numRows, int numCols,Card backCard) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cards = new Card[numRows][numCols];
        this.backcard = backCard;
        this.setOrder = new Card[numRows][numCols];
        createSet(cards,this.cards);
        createSet(cards, this.setOrder);
    }



    private void createSet(List<Card> c, Card[][] setToFill){

        Iterator<Card> iter = c.iterator();
        for (int row = 0; row != this.getNumRows(); row++) {
            for (int col = 0; col != this.getNumCols(); col++) {
                setToFill[row][col] = iter.next();
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
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numCards() {
        return numRows * numCols;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Card getCard(int row, int col) {
        return cards[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row the card row
     * @param col the card col
     */
    void swapCards(int row, int col) {
        Card cardtoswap = cards[row][col];
        if (cardtoswap.getId() == numCols*numRows){
            cards[row][col] = setOrder[row][col];
        }
        else {
            cards[row][row] = backcard;
        }

    }


    @Override
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
         * Performs operations in iterating through the 2D array and assigning the next tile
         * object for the Iterable
         *
         * @return whether the operation to assign a current tile and increment the column count
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

