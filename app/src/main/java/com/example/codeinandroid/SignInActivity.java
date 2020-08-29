package com.example.codeinandroid;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.lightThemeNoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FloatingActionButton signIn = findViewById(R.id.signIn);
        RelativeLayout parent = findViewById(R.id.parent);
        RelativeLayout main = findViewById(R.id.main);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int x = parent.getMeasuredWidth() / 2;
                int y = parent.getMeasuredHeight() / 2;

                int startRadius = 0;
                int endRadius = (int) Math.hypot(main.getWidth(), main.getHeight());
                Toast.makeText(SignInActivity.this, "" + signIn.getWidth(), Toast.LENGTH_SHORT).show();
                Animator anim = ViewAnimationUtils.createCircularReveal(main, x, y, startRadius, endRadius);
                anim.setDuration(2000);
                main.setVisibility(View.VISIBLE);
                signIn.setX(x - 30);
                signIn.setY(y);
                signIn.setVisibility(View.INVISIBLE);
                anim.start();


            }
        });
    }
}
