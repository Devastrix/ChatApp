package devas.com.whatchaap;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by user on 1/31/2016.
 */
public class UserDetails {
    SharedPreferences sharedpreferences;
    Context c;
    public static final String PREF_FILE_NAME = "whatchaap";
    private String username, passwd;
    private static  String USR = "user", PASS = "pass";
    public UserDetails(Context con) {
        this. c = con;
    }

    public void setDetails(String a, String b, Context con) {
        username = a;
        passwd = b;


        sharedpreferences = con.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USR, username);
        editor.putString(PASS, passwd);


        editor.commit();


    }
    public void setFriends(ArrayList<String> a) {
        String fri = "";
        for(String t : a) {
            fri += (t + "=");
        }
        sharedpreferences = c.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("friends", fri);
     //   editor.putString(PASS, passwd);


        editor.commit();
    }

    public String getUserfriends() {
        sharedpreferences = c.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("friends"))
        {

            return sharedpreferences.getString("friends", "");

        }
        return "";
    }


    public String getUsername() {
        sharedpreferences = c.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(USR))
        {

            return sharedpreferences.getString(USR, "");

        }
        return "";
    }

    public String getPasswd() {
        sharedpreferences = c.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(PASS))
        {

            return sharedpreferences.getString(PASS, "");

        }
        return "";
    }
    public  int isUserSet() {
        sharedpreferences = c.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if(sharedpreferences == null) {
            return 0;
        }
        if (sharedpreferences.contains(USR))
        {
            return 1;

        }
        return 0;
    }

}
