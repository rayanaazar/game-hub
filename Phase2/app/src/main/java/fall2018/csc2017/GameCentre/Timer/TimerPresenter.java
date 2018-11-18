package fall2018.csc2017.GameCentre.Timer;


public class TimerPresenter implements TimerModel {

    /**
     * Describes the time at which the timer was created in milliseconds.
     */

    private long startTime;

    /**
     * Describes the time that has elapsed since the timer was created.
     */

    private long elapsedTime;


    /**
     * Initialize a new TimerPresenter, setting the initial time to curTime.
     */
    public TimerPresenter(String curTime) {
        elapsedTime = Long.parseLong(curTime);
        startTime = elapsedTime;
    }

    public TimerPresenter() {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
    }

    @Override
    public void startTimer() {
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public void pause() {

    }

    @Override
    public void reset() {
        elapsedTime = 0;
    }

    @Override
    public void update(boolean lastMove) {
        if (!lastMove) {// If the last move is not favorable
            startTimer();
            elapsedTime += 10000;
        } else {
            startTimer();
        }
    }

    @Override
    public String toString() {
        long elapsedSeconds = elapsedTime / 1000;
        Long secondsDisplay = elapsedSeconds % 60;
        Long elapsedMinutes = elapsedSeconds / 60;

        String seconds = formatString(secondsDisplay.toString());
        String minutes = formatString(elapsedMinutes.toString());
        return minutes + ":" + seconds;
    }

    /**
     * Format time and return it in a tt format where tt represents a string representation of time.
     *
     * @param time Current time
     * @return An updated time string better suited for our purpose.
     */
    private String formatString(String time) {
        if (time.length() < 2) {
            return "0" + time;
        }
        return time;
    }
}
