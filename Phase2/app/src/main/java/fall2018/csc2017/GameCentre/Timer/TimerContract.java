package fall2018.csc2017.GameCentre.Timer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import fall2018.csc2017.GameCentre.R;

/**
 * Outlines the basic methods a Timer should have in the MVP model.
 */
public interface TimerContract {

    interface View extends BaseTimerView<Presenter>{

        /**
         * Get the current time.
         * @return A string representation of the current time.
         */
        String getCurTime();

        /**
         * Update the GUI with the new values.
         * @param time A string object representing the time in minute minute : second second format
         */
        void update(String time);
    }


    interface Presenter extends BaseTimerPresenter {

        /**
         * Update the view with the new data.
         */
        void updateView();
    }
}
