package com.example.codeinandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

import java.util.Objects;

public class MainActivity extends MenuAppCompactActivity implements View.OnClickListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private CardView C;
    private CardView Cpp;
    private CardView Java;
    private CardView Python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            Toast.makeText(this, "" + "darkMode", Toast.LENGTH_SHORT).show();
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        setContentView(R.layout.activity_main);
        C = findViewById(R.id.c);
        Cpp = findViewById(R.id.cpp);
        Java = findViewById(R.id.java);
        Python = findViewById(R.id.python);
        //ActionBarToggle
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        C.setOnClickListener(this);
        Java.setOnClickListener(this);
        Python.setOnClickListener(this);
        Cpp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, CodeMenu.class);
        String language;
        switch (v.getId()) {
            case R.id.c:
                language = "C";
                break;
            case R.id.cpp:
                language = "Cpp";
                break;
            case R.id.java:
                language = "Java";
                break;
            case R.id.python:
                language = "Python";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        Toast.makeText(this, "" + language, Toast.LENGTH_SHORT).show();
        i.putExtra("language", language);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
