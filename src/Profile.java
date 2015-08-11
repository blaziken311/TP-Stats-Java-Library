import org.json.JSONObject;

/**
 * Created by arjun on 8/10/15.
 */
public class Profile {
    // User's HTTP Profile
    private HttpRequest userHttp;
    private String reservedName;
    // TH stands for three hundred
    private double[] threeHundred;
    // Arrays for today, week, etc. are in the format:
    // captures, drops, grabs, hold (seconds), pops, potentialpups, pups, prevent (seconds), returns, saveAttempts,
    // saved, support, tags
    private int[] all;
    private int[] month;
    private int[] week;
    private int[] today;

    public Profile( HttpRequest myUserHttp ) throws Exception {
        userHttp = myUserHttp;
        threeHundred = new double[3];
        all = new int[13];
        month = new int[13];
        week = new int[13];
        today = new int[13];
        refresh();
    }

    // Refreshes instance variables
    public void refresh() throws Exception {
        String jsonString = userHttp.execute();

        jsonString=jsonString.replaceFirst("\\[","");
        jsonString=jsonString.replaceFirst("\\]$","");

        JSONObject jsonProfile = new JSONObject( jsonString );
        JSONObject statsJson = jsonProfile.getJSONObject("stats");

        reservedName = jsonProfile.getString("reservedName");

        JSONObject threeHundredJson = statsJson.getJSONObject(Constants.ROLLING);
        for( int i = 0; i < Constants.threeHundredKeys.length; i++ ) {
            threeHundred[i] = threeHundredJson.getDouble(Constants.threeHundredKeys[i]);
        }

        JSONObject allJson = statsJson.getJSONObject(Constants.ALL);
        JSONObject monthJson = statsJson.getJSONObject(Constants.MONTH);
        JSONObject weekJson = statsJson.getJSONObject(Constants.WEEK);
        JSONObject todayJson = statsJson.getJSONObject(Constants.TODAY);
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            all[i] = allJson.getInt( Constants.timeKeys[i] );
            month[i] = monthJson.getInt( Constants.timeKeys[i] );
            week[i] = weekJson.getInt( Constants.timeKeys[i] );
            today[i] = todayJson.getInt( Constants.timeKeys[i] );
        }
    }

    @Override
    public String toString() {
        String outString = reservedName + ":\n";

        outString += "\t300 Stats:\n";
        for( int i = 0; i < Constants.threeHundredKeys.length; i++ ) {
            outString += ( "\t\t" + Constants.threeHundredKeys[i] + ": " + threeHundred[i] + "\n");
        }

        outString += "\tToday Stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + today[i] + "\n";
        }

        outString += "\tMonth Stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + month[i] + "\n";
        }

        outString += "\tWeek Stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + week[i] + "\n";
        }

        outString += "\tAll Stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + all[i] + "\n";
        }

        return outString;
    }

    // GETTERS
    public HttpRequest getUserHttp() {
        return userHttp;
    }

    public String getReservedName() {
        return reservedName;
    }

    public double[] getThreeHundred() {
        return threeHundred;
    }

    public int[] getAll() {
        return all;
    }

    public int[] getMonth() {
        return month;
    }

    public int[] getWeek() {
        return week;
    }

    public int[] getToday() {
        return today;
    }
}
