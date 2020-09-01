package com.example.codeinandroid.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.codeinandroid.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends BaseActivity {
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.navigationView);

        navController = Navigation.findNavController(this, R.id.fragment);

        navController.navigate(R.id.homeFragment);
        navigationView.setCheckedItem(R.id.home);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    navController.navigate(R.id.homeFragment);
                    onBackPressed();
                    return true;
                case R.id.yourProgram:
                    navController.navigate(R.id.yourProgramFragment);
                    onBackPressed();
                    return true;
                case R.id.setting:
                    navController.navigate(R.id.settingFragment);
                    onBackPressed();
                    return true;
                case R.id.codeEditor

                        :
                    navController.navigate(R.id.editorFragment);
                    onBackPressed();
                    return true;
                default:
                    return false;
            }
        });

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.about, R.string.app_name);
        drawer.setDrawerListener(toggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isOpen()) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
