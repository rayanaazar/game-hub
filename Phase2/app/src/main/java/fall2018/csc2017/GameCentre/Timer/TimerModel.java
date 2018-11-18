package fall2018.csc2017.GameCentre.Timer;

/**
 * Outlines the basic capabilities a timer should have.
 */
public interface TimerModel {

    /**
     * Start the timer.
     */
    void startTimer();

    /**
     * Pause the timer.
     */
    void pause();

    /**
     * Reset the timer back to 0s.
     */
    void reset();

    /**
     * Return a string representation of the form mm:ss:msms
     */
    String toString();

    /**
     * Update timer based on boolean input.
     */
    void update(boolean lastMove);
}
