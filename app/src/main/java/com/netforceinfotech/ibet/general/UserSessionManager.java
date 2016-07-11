package com.netforceinfotech.ibet.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSessionManager {

    private static final String R_TOKEN = "rtoken";
    // Sharedpref file name
    private static final String PREFER_NAME = "AndroidExamplePref";

    // All Shared Preferences Keys
    private static final String REG_ID = "regId";
    private static final String TOKEN = "token";
    private static final String FBID = "fbid";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    // Shared Preferences reference
    SharedPreferences pref;
    // Editor reference for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Constructor
    public UserSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getRegId() {
        return pref.getString(REG_ID, "");
    }

    public void setRegId(String regid) {
        editor.putString(REG_ID, regid);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(TOKEN, "");
    }

    public void setToken(String regid) {
        editor.putString(TOKEN, regid);
        editor.commit();
    }
    public String getFBID() {
        return pref.getString(FBID, "");
    }

    public void setFBID(String regid) {
        editor.putString(FBID, regid);
        editor.commit();
    }
    public String getEmail() {
        return pref.getString(EMAIL, "");
    }

    public void setEmail(String regid) {
        editor.putString(EMAIL, regid);
        editor.commit();
    }
    public String getName() {
        return pref.getString(NAME, "");
    }

    public void setName(String regid) {
        editor.putString(NAME, regid);
        editor.commit();
    }



}