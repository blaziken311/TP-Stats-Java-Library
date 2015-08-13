package stats;

/**
 * Created by arjun on 8/10/15.
 */
public class StatsFormatter {
    /**
     * A static method that simply converts seconds to HH:MM:SS
     * @param seconds The number of seconds to convert to HH:MM:SS
     * @return The HH:MM:SS representation of the number of seconds passed in.
     */
    public static String secondsToHours( int seconds ) {
        int hours = (int) (seconds / 3600.0);
        seconds -= hours * 3600;
        int minutes = (int) (seconds / 60.0 );
        seconds -= minutes * 60.0;
        return hours + ":" + minutes + ":" + seconds;
    }
}
