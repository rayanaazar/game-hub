package fall2018.csc2017.GameCentre.Timer;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerPresenterTask extends TimerTask implements TimerModel {

    /**
     * A simple calendar to use to find the time.
     */
    private Calendar refCalendar = Calendar.getInstance();

    private Calendar calendar = Calendar.getInstance();

    /**
     * The time that has elapsed in the mm:ss format.
     */
    private String elapsedTime;

    public TimerPresenterTask() {
        refCalendar.setTimeInMillis(calendar.getTimeInMillis());
        elapsedTime = "00:00";
    }

    /**
     * Initialize a new TimerPresenterTask, setting the initial time to curTime.
     */
    public TimerPresenterTask(String curTime) { //Todo: fix
        calendar.setTimeInMillis(extractTime(curTime));
        elapsedTime = curTime;
    }

    @Override
    public void run() {
        calendar = Calendar.getInstance();

        Integer seconds = calendar.get(Calendar.SECOND) - refCalendar.get(Calendar.SECOND);
        Integer minutes = calendar.get(Calendar.MINUTE) - refCalendar.get(Calendar.MINUTE);
        if (seconds < 0) {
            Integer offsetSeconds = minutes * 60 + seconds;
            seconds = offsetSeconds % 60;
            minutes = offsetSeconds / 60;
        }
        Integer displayedSeconds = seconds % 60;
        setElapsedTime(minutes, displayedSeconds);
    }

    private void setElapsedTime(Integer minutes, Integer seconds) {
        String formattedMinutes = formatString(minutes.toString());
        String formattedSeconds = formatString(seconds.toString());
        elapsedTime = formattedMinutes + ":" + formattedSeconds;
    }


    public void reset() {
        calendar.clear();
    }

    /**
     * Starting from a string curTime (Must be in format mm:ss), extract the number of seconds
     * elapsed, and then convert to a long representing the elapsed milliseconds
     *
     * @param curTime The string representing the time elapsed in mm:ss format.
     * @return A long representing the milliseconds elapsed since timer was started.
     */
    private long extractTime(String curTime) {
        String time = curTime.replaceFirst("Time - ", "");
        String[] values = time.split(":");
        int minutes = Integer.parseInt(values[0]);
        int seconds = Integer.parseInt(values[1]);
        return Integer.toUnsignedLong(minutes * 60 * 1000 + seconds * 1000);
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

    public String getElapsedTime() {
        return this.elapsedTime;
    }


}
