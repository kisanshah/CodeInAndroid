package com.example.codeinandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

public class Setting extends AppCompatActivity {

    Switch themeToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            Toast.makeText(this, "" + "darkMode", Toast.LENGTH_SHORT).show();
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        themeToggle = findViewById(R.id.themeToggle);

        if (sharedPref.loadNightMode()) {
            themeToggle.setChecked(true);
            Toast.makeText(this, "" + themeToggle.isActivated(), Toast.LENGTH_SHORT).show();
        }
        themeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPref.setNightMode(true);
                restartApp();
            } else {
                sharedPref.setNightMode(false);
                restartApp();
            }
        });
    }

    private void restartApp() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(i);
    }
}
