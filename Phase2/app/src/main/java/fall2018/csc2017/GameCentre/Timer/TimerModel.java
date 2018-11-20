package fall2018.csc2017.GameCentre.Timer;

import android.arch.lifecycle.ViewModel;

import java.util.Calendar;

/**
 * A model to store, and manipulate data used in presenter.
 */
public class TimerModel extends ViewModel {

    /**
     * A calendar used as a reference point in Presenter.
     */
    private Calendar refCalendar;

    /**
     * The time that has elapsed in a minute minute : second second format.
     */
    private String elapsedTime;


    TimerModel() {
        refCalendar.setTimeInMillis(System.currentTimeMillis());
    }

    /**
     * Set the calendar to curTime.
     *
     * @param curTime A long value specifying the current time in milliseconds.
     */
    void setCalendar(Long curTime) {
        refCalendar.setTimeInMillis(curTime);
    }

    String getElapsedTime(){
        return this.elapsedTime;
    }

    void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime; // Must be in mm:ss format
    }

    /**
     * Return the calendar of reference.
     *
     * @return A Calendar object representing the exact time of when TimerModel was last
     * changed.
     */
    Calendar getRefCalendar() {
        return this.refCalendar;
    }

    /**
     * Format time and return it in a tt format where tt represents a string representation of time.
     *
     * @param time Current time
     * @return An updated time string better suited for our purpose.
     */
    public String formatString(String time) {
        if (time.length() < 2) {
            return "0" + time;
        }
        return time;
    }
}
