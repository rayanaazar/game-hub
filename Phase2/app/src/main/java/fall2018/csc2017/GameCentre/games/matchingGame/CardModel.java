package fall2018.csc2017.GameCentre.games.matchingGame;

import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CardModel extends ViewModel {


    private boolean isMatched;

    private boolean isFlipped;

    private int frontImage;

    public CardModel(int frontImage) {
        this.isMatched = false;
        this.isFlipped = true;
        this.frontImage = frontImage;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public int getFrontImage() {
        return frontImage;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
}
