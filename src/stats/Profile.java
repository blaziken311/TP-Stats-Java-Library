package stats;

import org.json.JSONObject;

/**
 * Created by arjun on 8/10/15.
 */

/**
 * This class represents a user's profile. Its fields are normal arrays of stats and an HttpRequest object corresponding
 * to his or her profile.
 */
public class Profile {
    // User's HTTP stats.Profile
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

    /**
     * Constructs a profile object and initializes all the stats to new arrays. It calls the refresh() method to refresh
     * its fields with fresh data from the REST endpoint.
     * @param myUserHttp The HttpRequest object corresponding to the user's profile.
     * @throws Exception I got lazy. //write stuff here
     * @see stats.HttpRequest
     */
    public Profile( HttpRequest myUserHttp ) throws Exception {
        userHttp = myUserHttp;
        threeHundred = new double[3];
        all = new int[13];
        month = new int[13];
        week = new int[13];
        today = new int[13];
        refresh();
    }

    /**
     * Refreshes all fields except userHttp with fresh data from the REST endpoint.
     * @throws Exception I got lazy. //write stuff here
     * @see stats.HttpRequest
     */
    // Refreshes instance variables
    public void refresh() throws Exception {
        String jsonString = userHttp.execute();

        //Locating square bracket's and deleting them using regex's to make a valid JSON object.
            //I did this because Profile is supposed to represent ONE profile. The multiple Profiles feature is not
            //necessary to implement in this class.
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

    /**
     * Overrides toString() method from java.lang.Object. This will print the JSON Keys/values in a more easily readable
     * format.
     * @return A more easily human readable version of the original JSON Block.
     */
    @Override
    public String toString() {
        String outString = reservedName + ":\n";

        outString += "\t300 stats:\n";
        for( int i = 0; i < Constants.threeHundredKeys.length; i++ ) {
            outString += ( "\t\t" + Constants.threeHundredKeys[i] + ": " + threeHundred[i] + "\n");
        }

        outString += "\tToday stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + today[i] + "\n";
        }

        outString += "\tMonth stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + month[i] + "\n";
        }

        outString += "\tWeek stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + week[i] + "\n";
        }

        outString += "\tAll stats:\n";
        for( int i = 0; i < Constants.timeKeys.length; i++ ) {
            outString += "\t\t" + Constants.timeKeys[i] + ": " + all[i] + "\n";
        }

        return outString;
    }

    // GETTERS

    /**
     * Returns the userHttp field
     * @return The userHttp field of this instance.
     */
    public HttpRequest getUserHttp() {
        return userHttp;
    }

    /**
     * Returns the user's reservedName.
     * @return The reservedName field of this instance.
     */
    public String getReservedName() {
        return reservedName;
    }

    /**
     * Returns a regular array of the user's Rolling 300 stats. (Best Win Rate, Games Played, Current Win Rate)
     * @return The user's Rolling 300 stats as an array.
     */
    public double[] getThreeHundred() {
        return threeHundred;
    }

    /**
     * Returns a regular array of the user's all-time stats. See stats.Constants.timeKeys[] for more information about
     * what each element of the array corresponds to.
     * @return The user's all-time stats as an array.
     * @see stats.Constants
     */
    public int[] getAll() {
        return all;
    }

    /**
     * Returns a regular array of the user's month stats. See stats.Constants.timeKeys[] for more information about what
     * each element of the array corresponds to.
     * @return The user's month stats as an array.
     * @see stats.Constants
     */
    public int[] getMonth() {
        return month;
    }

    /**
     * Returns a regular array of the user's week stats. See stats.Constants.timeKeys[] for more information about what
     * each element of the array corresponds to.
     * @return The user's week stats as an array.
     * @see stats.Constants
     */
    public int[] getWeek() {
        return week;
    }

    /**
     * Returns a regular array of the user's stats today. See stats.Constants.timeKeys[] for more information about what
     * each element of the array corresponds to.
     * @return The user's today stats as an array.
     * @see stats.Constants
     */
    public int[] getToday() {
        return today;
    }
}
