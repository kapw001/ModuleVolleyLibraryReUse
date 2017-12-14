package volleywebrequest.com.volleylibrarymoduleforreuse;

import android.app.Application;

import com.mysessionmanagementlibrary.SessionManager;
import com.volleywebrequest.MakeRestRequest;
import com.volleywebrequest.VolleyWebRequest;

/**
 * Created by yasar on 5/10/17.
 */

public class App extends Application {

    private static App app;
    private MakeRestRequest volleyWebRequest;
    private SessionManager sessionManager;


    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        volleyWebRequest = VolleyWebRequest.getInstance(this);
        sessionManager = SessionManager.getInstance(this);
    }

    public MakeRestRequest getVolleyWebRequest() {
        return volleyWebRequest;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

}
