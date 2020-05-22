package com.example.codeinandroid.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

public class MenuAppCompactActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            Toast.makeText(this, "" + "darkMode", Toast.LENGTH_SHORT).show();
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
            case R.id.Setting:
                startActivity(new Intent(getApplicationContext(), Setting.class));
            case R.id.darkMode:
                Toast.makeText(this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
