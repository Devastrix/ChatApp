package devas.com.whatchaap;

/**
 * Created by user on 1/29/2016.
 */
import org.jivesoftware.smack.chat.Chat;
import org.jxmpp.stringprep.XmppStringprepException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MyService extends Service {
    private static final String DOMAIN = "sanyam";
    private static  String USERNAME = "devastrix";
    private static  String PASSWORD = "putu1";
    public static ConnectivityManager cm;
    public static MyXMPP xmpp;
    public static boolean ServerchatCreated = false;
    String text = "";

    @Override
    public IBinder onBind(final Intent intent) {
        return new LocalBinder<MyService>(this);
    }

    public Chat chat;

    @Override
    public void onCreate() {
        super.onCreate();

        UserDetails ud = new UserDetails(this);
        USERNAME = ud.getUsername();
        PASSWORD = ud.getPasswd();
        Log.d(USERNAME, PASSWORD);
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            xmpp = MyXMPP.getInstance(MyService.this, DOMAIN, USERNAME, PASSWORD);
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }

        xmpp.connect("onCreate");
        xmpp.login();


    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,
                              final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xmpp.connection.disconnect();
    }

    public static boolean isNetworkConnected() {
        return cm.getActiveNetworkInfo() != null;
    }
}