package com.example.codeinandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private CardView C;
    private CardView Cpp;
    private CardView Java;
    private CardView Python;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C = findViewById(R.id.c);
        Cpp = findViewById(R.id.cpp);
        Java = findViewById(R.id.java);
        Python = findViewById(R.id.python);


        C.setOnClickListener(this);
        Java.setOnClickListener(this);
        Python.setOnClickListener(this);
        Cpp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
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
        i.putExtra("language", language);
        startActivity(i);
    }
}
