package com.example.codeinandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.codeinandroid.R;

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
            case R.id.JavaScript:
                language = "JavaScript";
                break;
            case R.id.r:
                language = "R";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        Toast.makeText(this, "" + language, Toast.LENGTH_SHORT).show();
        i.putExtra("language", language);
        startActivity(i);
    }
}
