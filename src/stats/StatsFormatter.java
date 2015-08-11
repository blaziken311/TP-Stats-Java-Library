package stats;

/**
 * Created by arjun on 8/10/15.
 */
public class StatsFormatter {
    public static String secondsToHours( int seconds ) {
        int hours = (int) (seconds / 3600.0);
        seconds -= hours * 3600;
        int minutes = (int) (seconds / 60.0 );
        seconds -= minutes * 60.0;
        return hours + ":" + minutes + ":" + seconds;
    }
}
