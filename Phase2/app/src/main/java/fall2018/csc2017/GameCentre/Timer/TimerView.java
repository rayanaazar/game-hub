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
 * Outlines the basic methods a TimerModel View should have.
 */
public interface TimerView {

    /**
     * Start the timer where it was left off. If this is a new Timer, start at 0.
     */
    void startTimer();

    /**
     * Stop the timer but keep its value intact.
     */
    void stopTimer();

    /**
     * Reset the timer back to 0s.
     */
    void resetTimer();

    /**
     * Update the timer based on a user move.
     */
    void updateTimer(boolean lastMove);
}
