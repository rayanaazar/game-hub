package fall2018.csc2017.GameCentre.Timer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import fall2018.csc2017.GameCentre.R;
//Todo Complete class

/**
 * A basic TimerModel activity.
 */
public class TimerActivity extends AppCompatActivity implements TimerView {

    /**
     * The current time
     */
    private String curTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_timer_activity);

        curTime = getTime();
        TimerPresenter presenter = new TimerPresenter(curTime);
    }

    /**
     * Find and return a String version of TextView time.
     */
    private String getTime(){
        return findViewById(R.id.curTime).toString();
    }

    @Override
    public void startTimer() {
        // presenter.startTimer();
    }

    @Override
    public void stopTimer() {
        // presenter.stopTimer();
    }

    @Override
    public void resetTimer() {
        // presenter.resetTimer();
    }

    @Override
    public void updateTimer(boolean lastMove) {

    }
}