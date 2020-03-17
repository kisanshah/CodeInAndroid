package com.example.codeinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView C, Cpp, Java, Python, JavaScript, RLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C = findViewById(R.id.c);
        Cpp = findViewById(R.id.cpp);
        Java = findViewById(R.id.java);
        Python = findViewById(R.id.python);
        JavaScript = findViewById(R.id.JavaScript);
        RLang = findViewById(R.id.r);

        C.setOnClickListener(this);
        JavaScript.setOnClickListener(this);
        Java.setOnClickListener(this);
        Python.setOnClickListener(this);
        RLang.setOnClickListener(this);
        Cpp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.c:
                i = new Intent(getApplicationContext(), com.example.codeinandroid.C.class);
                break;
            case R.id.cpp:
                i = new Intent(getApplicationContext(), com.example.codeinandroid.Cpp.class);
                break;
            case R.id.java:
                i = new Intent(getApplicationContext(), com.example.codeinandroid.Java.class);
                break;
            case R.id.python:
                i = new Intent(getApplicationContext(), com.example.codeinandroid.Python.class);
                break;
            case R.id.JavaScript:
                i = new Intent(getApplicationContext(), com.example.codeinandroid.JavaScript.class);
                break;
            case R.id.r:
                i = new Intent(getApplicationContext(), Rlang.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(i);
    }
}
