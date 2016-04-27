package com.example.lababiba.weatherservice.Controller;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Администратор on 25.04.2016.
 */
public class SharedPrefencesController {
    static private SharedPrefencesController instance;

    public static SharedPrefencesController getInstance() {

        if (instance == null) {
            synchronized (SharedPrefencesController.class) {
                instance = new SharedPrefencesController();
            }
        }
        return instance;

    }

    private Context context ;
    private String username = "Qwer";
    private SharedPreferences preferences;

    public void init(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences(username, Context.MODE_PRIVATE);
    }

    public String getString(String prefKey) {
        SharedPreferences.Editor editor = preferences.edit();
        String result = preferences.getString(prefKey, "");
        return result;
    }

    public boolean getState(String prefkey) {
        SharedPreferences.Editor editor = preferences.edit();
        boolean result = preferences.getBoolean(prefkey, false);
        return result;
    }

    public int getInt(String prefKey) {
        SharedPreferences.Editor editor = preferences.edit();
        int result = preferences.getInt(prefKey, 0);
        return result;
    }

    public void putString(String prefKey, String prefValue) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(prefKey, prefValue);
        editor.apply();
    }

    public void putState(String prefKey, boolean prefValue) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(prefKey, prefValue);
        editor.apply();
    }

    public void putInt(String prefKey, int prefValue) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(prefKey, prefValue);
        editor.apply();
    }


    public void removeSpecific(String prefKey){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(prefKey);
        editor.apply();
    }

}
