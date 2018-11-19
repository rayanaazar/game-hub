package fall2018.csc2017.GameCentre.Timer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import fall2018.csc2017.GameCentre.R;

/**
 * A basic TimerModel activity.
 */
public class TimerActivity extends AppCompatActivity { // implements TimerView {

    private Timer timer = new Timer();
    private Timer updateTimer = new Timer();
    TimerPresenterTask task = new TimerPresenterTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_timer_activity);
        timer.schedule(task, 0, 500);

        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void update() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView view = findViewById(R.id.curTime);
                String time = "Time - " + task.getElapsedTime();
                view.setText(time);
            }
        });
    }


}