package fall2018.csc2017.GameCentre.games.matchingGame;


import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import fall2018.csc2017.GameCentre.games.GameBoardManager;

public class CardSetManager extends GameBoardManager {

    /**
     * The set of cards  being managed.
     */

    private CardSetModel cardSetModel;

    /**
     * Maximum number of cards on the board.
     */
    private List<CardPresenter> cards = new ArrayList<>();

    /**
     * Indicates whether user is about to select the first card.
     */
    private boolean firstSelection;


    /**
     * The selected card
     */
    private CardPresenter firstCard;

    /**
     * The row and column of the selected card.
     */
    private int[] firstCardInfo;

    /**
     * TODO: make this work
     * @return
     */
    public int getScore() { return 3; }

    /**
     * Manage a new shuffled card set of a given size and number of undos.
     */
    public CardSetManager(int numRows, int numCols, ArrayList<CardView> cards) {
        super(numRows, numCols);
        createCardSet(cards);
        firstSelection = true;
        this.cardSetModel = new CardSetModel(this.cards, numRows, numCols);
    }

    /**
     * Return the current cardSetModel.
     */
    public CardSetModel getCardSetModel() {
        return cardSetModel;
    }


    /**
     * Create a cardSetModel and shuffle it given the size of the cardSetModel.
     */
    private void createCardSet(ArrayList<CardView> cardViews) {
        Stack<Integer> positions = getPositionStack();
        for (int cardNum = 0; cardNum != (numCols * numRows); cardNum++) {
            cards.add(new CardPresenter((positions.pop()), numCols, cardViews.get(cardNum)));
        }
    }

    private Stack<Integer> getPositionStack() {
        Stack<Integer> positions = new Stack<>();
        int position = 1;
        while (position != numCols * numRows + 1) {
            if (position > 8) {
                int offset = 8;
                positions.push(position - offset);
                position++;
            } else {
                positions.push(position);
                position++;
            }
        }
        Collections.shuffle(positions);
        return positions;
    }


    /**
     * Return whether the cards are in row-major order.
     *
     * @return whether the cards are in row-major order
     */
    public boolean puzzleSolved() {
        boolean solved = true;
        Iterator<CardPresenter> iter = cardSetModel.iterator();
        while (iter.hasNext()) {
            CardPresenter curr = iter.next();
            if (!curr.isMatched()) {
                solved = false;
                break;
            }
        }
        return solved;
    }


    /**
     * Return the tap tried is a valid one.
     *
     * @param position the card to check
     * @return whether the card at position isn't already turned.
     */
    public boolean isValidTap(int position) {
        int[] positions = getCardSetPositions(position);
        CardPresenter card = getCardSetModel().getCard(positions[0], positions[1]);
        return !card.isMatched();
    }

    /**
     * Returns the current score.
     *
     * @return the current score.
     */
    @Override
    public int getScore() {
        return 0;
    }


    /**
     * Process a touch at position in the card set, turning it as appropriate.
     *
     * @param position the position
     */
    @Override
    public void touchMove(int position) {
        final int[] positions = getCardSetPositions(position);
        if (isValidTap(position)) {
            if (firstSelection) {
                firstCard = getCardSetModel().getCard(positions[0], positions[1]);
                firstCardInfo = positions.clone();
                getCardSetModel().swapCards(positions[0], positions[1]);
                firstSelection = false;
            } else {
                CardPresenter selectedCard2 = getCardSetModel().getCard(positions[0], positions[1]);
                getCardSetModel().swapCards(positions[0], positions[1]);
                firstSelection = true;
                if (selectedCard2.compareTo(firstCard) == 0) {
                    firstCard.setMatched(true);
                    selectedCard2.setMatched(true);
                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getCardSetModel().swapCards(firstCardInfo[0], firstCardInfo[1]);
                            getCardSetModel().swapCards(positions[0], positions[1]);
                        }
                    }, 1000);

                }
                firstCard = null;
            }
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
        int col = position % numCols;
        rowCol[0] = row;
        rowCol[1] = col;
        return rowCol;

    }


}
