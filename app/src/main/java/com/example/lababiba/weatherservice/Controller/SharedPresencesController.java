package com.example.lababiba.weatherservice.Controller;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Администратор on 25.04.2016.
 */
public class SharedPresencesController {

    private static volatile SharedPresencesController instance;

    public static SharedPresencesController getInstance() {
        SharedPresencesController localInstance = instance;
        if (localInstance == null) {
            synchronized (SharedPresencesController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SharedPresencesController();
                }
            }
        }
        return localInstance;
    }




    private SharedPreferences preferences;

    public void init(Context context) {
        preferences = context.getSharedPreferences("defaultUser", Context.MODE_PRIVATE);
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
