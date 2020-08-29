package com.example.codeinandroid.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            setTheme(R.style.darkThemeNoActionBar);
        } else {
            setTheme(R.style.lightThemeNoActionBar);
        }
        setContentView(R.layout.activity_splash_screen);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        RelativeLayout container = findViewById(R.id.container);
        ImageView imageView = findViewById(R.id.imageView);
        tv = findViewById(R.id.tv1);

        tv.setAnimation(bottomAnim);
        imageView.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                revealAnimation(container);
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);
    }

    void revealAnimation(View container) {
        int cx = container.getWidth() / 2;
        int cy = container.getHeight() / 2;
        float radius = (float) Math.hypot(cx, cy);
        Animator anim = ViewAnimationUtils.createCircularReveal(container, cx, cy, 0, radius);
        anim.setDuration(800);
        Toast.makeText(this, "" + anim.getDuration(), Toast.LENGTH_SHORT).show();
        anim.start();
        container.setVisibility(View.VISIBLE);
        if (anim.isPaused()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
