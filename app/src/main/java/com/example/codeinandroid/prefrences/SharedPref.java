package com.example.codeinandroid.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("AppSetting", Context.MODE_PRIVATE);
    }

    public void setNightMode(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }

    public Boolean loadNightMode() {
        return sharedPreferences.getBoolean("NightMode", false);
    }

    public void setWrapLine(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("WrapLine", state);
        editor.apply();
    }

    public Boolean loadWrapLine() {
        return sharedPreferences.getBoolean("WrapLine", false);
    }

    public void setZoom(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Zoom", state);
        editor.apply();
    }

    public Boolean loadZoom() {
        return sharedPreferences.getBoolean("Zoom", false);
    }
}
