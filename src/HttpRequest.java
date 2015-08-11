import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by arjun on 8/10/15.
 */
public class HttpRequest {
    private String url;
    private String profileId;

    public HttpRequest( String myProfileId ) {
        profileId = myProfileId;
        url = "http://tagpro-" + Constants.ALTSERVER + ".koalabeast.com/profiles/" + profileId;
    }

    public String execute() throws MalformedURLException, IOException, ProtocolException, JSONException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");

        // Check status code (should be 200)
        if( connection.getResponseCode() != 200 ) {
            System.err.println("Error: Response Code: " + connection.getResponseCode() );
        }

        BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream() ));
        String inLine;
        StringBuffer response = new StringBuffer();

        while((inLine = br.readLine()) != null) {
            response.append(inLine + "\n");
        }
        br.close();

        return response.toString();
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getUrl() {
        return url;
    }
}
