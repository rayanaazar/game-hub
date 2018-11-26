package fall2018.csc2017.GameCentre;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class CardSetManager {

    /**
     * The set of cards  being managed.
     */

    private CardSet cardSet;

    /**
     * The current number of undos Left
     */
    private int undos;

    /**
     * Maximum number of cards on the board.
     */
    private List<Card> cards = new ArrayList<>();


    /**
     * The number of rows in the card set.
     */
    private int numRows;

    /**
     * The number of columns in the card set.
     */

    private int numCols;

    /**
     * The stack representing the moves
     */
    private Stack<CardSet> moveStack;



    /**
     * Manage a new shuffled card set of a given size and number of undos.
     */
    CardSetManager(int numRows, int numCols, int undos) {
        this.numRows = numRows;
        this.numCols = numCols;
        createCardSet();
        Card blankCard = new Card(numCols * numRows, numCols * numRows);
        this.cardSet = new CardSet(cards, numRows, numCols, blankCard);
        this.undos = undos;
        this.moveStack = new Stack<>();
    }

    /**
     * Return the current cardSet.
     */
    CardSet getCardSet() {
        return cardSet;
    }

    /**
     * Return the number of rows in the cardSet.
     */
    private int getNumRows() {
        return this.numRows;
    }

    /**
     * Return the number of columns in the cardSet.
     */

    private int getNumCols() {
        return this.numCols;
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
    boolean puzzleSolved() {
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
        return getNumCols() * getNumRows();
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
    void touchMove(int position) {
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


    /**
     * Returns the current score.
     *
     * @return the current score.
     */
    public int getScore() {
        return this.moveStack.size();
    }

    public Stack<CardSet> getMoveStack() {
        return moveStack;
    }

    public void setMoveStack(Stack<CardSet> moveStack) {
        this.moveStack = moveStack;
    }

}
