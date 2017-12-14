package com.mysessionmanagementlibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by yasar on 5/10/17.
 */

public class SessionManager {

    public static SessionManager sessionManager;
    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = BuildConfig.APPLICATION_ID;

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_URL = "url";

    public String getKeyUrl() {
        return pref.getString(KEY_URL, null);
    }

    // Constructor
    private SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static SessionManager getInstance(Context _context) {
        if (sessionManager == null) {
            sessionManager = new SessionManager(_context);
        }

        return sessionManager;
    }

    /**
     * Create login session
     */
    public void createSession(String email, String name) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_PASSWORD, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    public void setUrl(String url) {
        editor.putString(KEY_URL, url);
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void claerData() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
