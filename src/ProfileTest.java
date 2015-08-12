import stats.Profile;
import stats.HttpRequest;

import java.util.Scanner;

/**
 * Created by arjun on 8/10/15.
 */
public class ProfileTest {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter unique hash: ");
        String hash = in.nextLine();
        Profile me = new Profile( new HttpRequest(hash) );

        System.out.println( me.toString() );
    }
}