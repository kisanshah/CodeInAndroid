package com.example.codeinandroid.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("key", "value");
        editor.putBoolean("key", false);
        editor.putInt("key", 0);
        editor.commit();
    }

    public boolean loadNightMode() {
        return sharedPreferences.getBoolean("NightMode", false);
    }


    public void setNightMode(boolean b) {
        editor.putBoolean("NightMode", true);
        editor.commit();
    }

    public boolean loadWrapLine() {
        return sharedPreferences.getBoolean("WrapLine", false);
    }

    public boolean loadZoom() {
        return sharedPreferences.getBoolean("Zoom", false);
    }
}