package fall2018.csc2017.GameCentre.games.matchingGame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.GameCentre.games.GameBoardManager;

public class CardSetManager extends GameBoardManager {

    /**
     * The set of cards  being managed.
     */

    private CardSet cardSet;

    /**
     * Maximum number of cards on the board.
     */
    private List<Card> cards = new ArrayList<>();


    /**
     * Manage a new shuffled card set of a given size and number of undos.
     */
    public CardSetManager(int numRows, int numCols) {
        super(numRows,numCols);
        createCardSet();
        Card blankCard = new Card(numCols * numRows, numCols * numRows);
        this.cardSet = new CardSet(cards, numRows, numCols, blankCard);
    }

    /**
     * Returns the current score
     * @return the current score
     * TODO: Implement this!
     */
    public int getScore() { return 3; }

    /**
     * Return the current cardSet.
     */
    private CardSet getCardSet() {
        return cardSet;
    }


    /**
     * Create a cardSet and shuffle it given the size of the cardSet.
     */
    private void createCardSet() {
        for (int cardNum = 0; cardNum != (getCardSetSize()); cardNum++) {
            cards.add(new Card(cardNum, getCardSetSize()));
            cards.add(new Card(cardNum, getCardSetSize()));
        }
        Collections.shuffle(cards);
    }



    /**
     * Return whether the cards are in row-major order.
     *
     * @return whether the cards are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Card> iter = cardSet.iterator();
        while (iter.hasNext()){
            Card curr = iter.next();
            if (curr.getId() == getCardSetSize() + 1){
                solved = false;
            }
        }
        return solved;
    }



    /**
     * Return the size of the card set.
     *
     * @return size of card set.
     */
    private int getCardSetSize(){
        return getCardSet().numPieces();
    }

    /**
     * Return the tap tried is a valid one.
     *
     * @param position the card to check
     * @return whether the card at position isn't already turned.
     */
    public boolean isValidTap(int position) {
        int[] positions = getCardSetPositions(position);
        Card card = getCardSet().getCard(positions[0], positions[1]);
        return card.getId() == (getCardSetSize() + 1);
    }


    /**
     * Process a touch at position in the card set, turning it as appropriate.
     *
     * @param position the position
     */
    @Override
    public void touchMove(int position) {
        int[] positions = getCardSetPositions(position);
        if (isValidTap(position)) {
            getCardSet().swapCards(positions[0],positions[1]);
        }
    }

    /**
     * Calculates the row and column given the current position.
     *
     * @param position the position
     */
    private int[] getCardSetPositions(int position) {
        int[] rowCol = new int[2];
        int row = position / numRows;
        int col  = position % numCols;
        rowCol[0] = row;
        rowCol[1] = col;
        return rowCol;

    }


}
