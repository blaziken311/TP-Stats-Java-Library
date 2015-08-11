package stats;

/**
 * Created by arjun on 8/10/15.
 */
public class Constants {
    // Servers
    public static final String SERVER = "diameter";
    public static final String ALTSERVER = "diameter";

    // Object names
    public static final String ROLLING = "rollingCache";
    public static final String ALL = "all";
    public static final String MONTH = "month";
    public static final String WEEK = "week";
    public static final String TODAY = "today";

    // JSON Keys
    public static final String[] threeHundredKeys = { "bestWinRate", "games", "winRate" };
        // Arrays for today, week, etc. are in the format:
        // captures, drops, grabs, hold (seconds), pops, potentialpups, pups, prevent (seconds), returns, saveAttempts,
        // saved, support, tags
    public static final String[] timeKeys = { "captures", "drops", "grabs", "hold", "pops", "potentialPowerups",
                                              "powerups", "prevent", "returns", "saveAttempts", "saved", "support",
                                              "tags" };
}
