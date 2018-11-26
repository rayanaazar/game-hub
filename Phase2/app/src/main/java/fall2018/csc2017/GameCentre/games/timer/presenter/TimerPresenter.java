package fall2018.csc2017.GameCentre.games.timer.presenter;


import android.os.Looper;
import android.support.annotation.NonNull;

import android.os.Handler;

import java.util.Calendar;

import fall2018.csc2017.GameCentre.games.timer.TimerContract;
import fall2018.csc2017.GameCentre.games.timer.model.TimerModel;


/**
 * A presenter class for timer to communicate between view and model.
 */

public class TimerPresenter implements TimerContract.Presenter {

    /**
     * A TimerModel as a reference to store and manipulate data.
     */

    private final TimerModel timerModel;

    /**
     * A TimerView to visually display data, and to be regularly updated.
     */

    private final TimerContract.View timerView;

    /**
     * A Handler designed to communicate with View and Model.
     */

    private final Handler handler;

    /**
     * A Runnable designed to update the display to the period we want.
     */

    private final Runnable updateRunnable;


    public TimerPresenter(@NonNull final TimerContract.View timerView,
                          @NonNull final TimerModel timerModel) {
        this.timerModel = timerModel;
        this.timerView = timerView;
        this.timerView.setPresenter(this);
        this.handler = new Handler(Looper.getMainLooper());
        this.updateRunnable = new Runnable() {
            @Override
            public void run() {
                updateView();
            }
        };
    }

    @Override
    public void start() {
        timerModel.setCalendar(System.currentTimeMillis());
        handler.postDelayed(updateRunnable, 1000);
        updateView();
    }


    @Override
    public void stop() {
        handler.removeCallbacks(updateRunnable);
    }

    @Override
    public void updateView() {
        timerView.update(getElapsedTime());
    }

    /**
     * Get the time that has elapsed, since the timer started.
     *
     * @return A string representing the elapsed time, in minute minute : second second format.
     */
    private String getElapsedTime() {
        Calendar calendar = Calendar.getInstance(); // Get a calendar with the current time
        Calendar refCalendar = timerModel.getRefCalendar();

        Integer[] values = getDisplayTimes(calendar, refCalendar);
        String minutes = timerModel.formatString(values[0].toString());
        String seconds = timerModel.formatString(values[1].toString());

        return minutes + ":" + seconds;
    }

    /**
     * Starting from two calendars, one of the current time and one of reference, return an array
     * representing the time difference between the two.
     * @param curCalendar A calendar of the current time.
     * @param refCalendar A calendar of some time of reference.
     * @return An integer array, with the minutes difference at index 0, and the seconds difference
     * at index 1.
     */
    private Integer[] getDisplayTimes(Calendar curCalendar, Calendar refCalendar) {
        Integer seconds = curCalendar.get(Calendar.SECOND) - refCalendar.get(Calendar.SECOND);
        Integer minutes = curCalendar.get(Calendar.MINUTE) - refCalendar.get(Calendar.MINUTE);
        if (seconds < 0) {
            Integer offsetSeconds = minutes * 60 + seconds;
            seconds = offsetSeconds % 60;
            minutes = offsetSeconds / 60;
        }
        Integer displayedSeconds = seconds % 60;
        return new Integer[]{minutes, displayedSeconds};
    }
//
//
//    /**
//     * Starting from a string curTime (Must be in format mm:ss), extract the number of seconds
//     * elapsed, and then convert to a long representing the elapsed milliseconds
//     *
//     * @param curTime The string representing the time elapsed in mm:ss format.
//     * @return A long representing the milliseconds elapsed since timer was started.
//     */
//    private long extractTime(String curTime) {
//        String time = curTime.replaceFirst("Time - ", "");
//        String[] values = time.split(":");
//        int minutes = Integer.parseInt(values[0]);
//        int seconds = Integer.parseInt(values[1]);
//        return Integer.toUnsignedLong(minutes * 60 * 1000 + seconds * 1000);
//    }

}
