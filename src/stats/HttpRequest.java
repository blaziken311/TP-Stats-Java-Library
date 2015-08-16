package stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arjun on 8/10/15.
 */

/**
 * This class represents the request to the user's profile URL which returns his or her stats as a JSON block.
 */
public class HttpRequest {
    private String url;
    private String profileId;

    /**
     * Constructs an HttpRequest object. This constructor also builds the URL based on the user's hash and a server (which is
     * arbitrarily set to Radius). #RADIUSMASTERRACE
     * @param myProfileId the unique hash of a Tagpro player. This is located at the end of the URL when a user views
     *                    his or her own profile.
     * @see java.net.URL
     */
    public HttpRequest( String myProfileId ) {
        profileId = myProfileId;
        url = "http://tagpro-" + Constants.ALTSERVER + ".koalabeast.com/profiles/" + profileId;
    }

    /**
     * Returns the user's stats (JSON block) as a string. The execute() method opens a connection to the URL and
     * requests the stats from the server. In the case that someday the REST endpoint shows prettyPrinted JSON, the
     * while loop keeps the prettyPrint.
     * @return The user's stats as a JSON block.
     * @throws IOException I got lazy. //write stuff here later
     * @see java.net.URL
     * @see java.io.BufferedReader
     * @see java.lang.StringBuffer
     */
    public String execute() throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        // Check status code (should be 200)
        if( connection.getResponseCode() != 200 ) {
            System.err.println("Error: Response Code: " + connection.getResponseCode() );
        }

        BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream() ));
        String inLine;
        //from StringBuffer to StringBuilder.
        StringBuffer response = new StringBuffer();

        while((inLine = br.readLine()) != null) {
            response.append(inLine + "\n");
        }
        br.close();

        return response.toString();
    }

    /**
     * Getter for the profileId field.
     * @return The profileId of this instance.
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * Getter for the url field.
     * @return The fully constructed url of this instance.
     */
    public String getUrl() {
        return url;
    }
}
