package fall2018.csc2017.GameCentre.games.ttt.presenter;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.GameCentre.games.GameBoardManager;
import fall2018.csc2017.GameCentre.games.matchingGame.presenter.CardPresenter;
import fall2018.csc2017.GameCentre.games.ttt.model.CardSetModelTTT;
import fall2018.csc2017.GameCentre.games.ttt.view.TTTCardView;

public class TTTSetManager extends GameBoardManager {

    /**
     * The set of cards being managed.
     */
    private CardSetModelTTT cardSetModel;


    /**
     * The list of CardPresenters as they appear on the CardSet.
     */
    private List<TTTPresenter> cards = new ArrayList<>();

    /**
     * References the card that was selected in the game.
     */
    private TTTPresenter firstCard;

    /**
     * The row and column of the selected card.
     */
    private int[] firstCardInfo;

    /**
     * Works as a controller. When we start, numMoves is 0. When the first card is selected,
     * numMoves will become 1. When user selects the second card, numMoves becomes 2 and resets
     * back to 0.
     */
    private int numMoves;


    /**
     * TODO: make this work
     *
     * @return The score.
     */
    public int getScore() {
        return 3;
    }

    /**
     * Manage a new shuffled card set of a given size and number of undos.
     */
    protected TTTSetManager(int numRows, int numCols, ArrayList<TTTCardView> cards) {
        super(numRows, numCols);
        createCardSet(cards);
        numMoves = 0;
        this.cardSetModel = new CardSetModelTTT(this.cards, numRows, numCols);
    }


    public CardSetModelTTT getCardSetModel() {
        return cardSetModel;
    }

    public List<TTTPresenter> getCards() {
        return cards;
    }


    /**
     * Create a cardSetModel and shuffle it given the size of the cardSetModel. Each TTTPresenter
     * will be present exactly twice on the CardSet.
     */
    private void createCardSet(ArrayList<TTTCardView> cardViews) {
        Stack<Integer> positions = getPositionStack();
        for (int cardNum = 0; cardNum != (numCols * numRows); cardNum++) {
            cards.add(new TTTPresenter((positions.pop()), numCols, cardViews.get(cardNum)));
        }
    }

    /**
     * Create and populate a stack of integers ranging from 1 to the number of necessary different
     * backgroundID's needed for all the CardPresenters on the CardSet. Each position will appear
     * exactly twice. The Stack is then randomized and returned.
     *
     * @return A stack containing integers [1, necessaryCards] exactly twice and in random order.
     */
    private Stack<Integer> getPositionStack() {
        Stack<Integer> positions = new Stack<>();
        int neededCards = numCols * numRows / 2;
        int position = 1;
        while (position <= numCols * numRows) {
            if (position > (neededCards)) {
                positions.push(position - neededCards);
            } else {
                positions.push(position);
            }
            position++;
        }
        Collections.shuffle(positions);
        return positions;
    }


    /**
     * Return whether the game has been completed. Return true if and only if all the cards in the
     * CardSet have been matched.
     *
     * @return A boolean value indicating whether the game is over.
     */
    public boolean puzzleSolved() {
        // reimplement this ->
//        boolean solved = true;
//        Iterator<TTTPresenter> iter = cardSetModel.iterator();
//        while (iter.hasNext()) {
//            TTTPresenter curr = iter.next();
//            if (!curr.isMatched()) {
//                solved = false;
//                break;
//            }
//        }
//        return solved;
        return false;
    }


    /**
     * Process a touch at position in the CardSet, flipping it as appropriate.
     *
     * @param position The position of the user's tap.
     */
    @Override
    public void touchMove(int position) {
        final int[] positions = getCardSetPositions(position);
        if (isValidTap(position)) {

            if (numMoves == 0) { // The First tap
                processFirstTap(positions);
            } else if (numMoves == 1) { // The second tap
                TTTPresenter selectedCard2 = getCardSetModel().getCard(positions[0], positions[1]);
                getCardSetModel().swapCards(positions[0], positions[1]);
                numMoves++;

                if (selectedCard2.compareTo(firstCard) == 0 && selectedCard2 != firstCard) {
                    processGoodMove(selectedCard2);
                }
                firstCard = null;
            }
        }
    }

    /**
     * Process a good move done on firstCard and selectedCard. That is, set both to matched and
     * prevent them from changing.
     *
     * @param selectedCard The selected card which was macthed with firstCard.
     */
    private void processGoodMove(TTTPresenter selectedCard) {
        firstCard.setCard();
        selectedCard.setCard();
        numMoves = 0;
    }

    @Override
    public boolean isValidTap(int position) {
        int[] positions = getCardSetPositions(position);
        TTTPresenter card = getCardSetModel().getCard(positions[0], positions[1]);
        return !card.isSet();
    }

    /**
     * Process the first tap done on the Card located at coordinates (positions[0], positions[1]).
     *
     * @param positions An array containing the row and column of the corresponding card
     *                  respectively.
     */
    private void processFirstTap(int[] positions) {
        firstCard = getCardSetModel().getCard(positions[0], positions[1]);
        getCardSetModel().swapCards(positions[0], positions[1]);
        firstCardInfo = positions.clone();
        numMoves++;
    }

    /**
     * Calculates the row and column of the card located at the current position.
     *
     * @param position The position of the tap.
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
