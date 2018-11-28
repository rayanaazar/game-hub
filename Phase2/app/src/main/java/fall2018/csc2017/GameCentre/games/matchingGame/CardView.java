package fall2018.csc2017.GameCentre.games.matchingGame;

import android.content.Context;
import android.util.AttributeSet;


public class CardView extends android.support.v7.widget.AppCompatButton {

    CardPresenter cardPresenter;

    public CardView(final Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    void setPresenter(CardPresenter cardPresenter) {
        this.cardPresenter = cardPresenter;
    }


    public void flip(){
        cardPresenter.flip();
    }

}