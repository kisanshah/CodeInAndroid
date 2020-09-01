package com.example.codeinandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeinandroid.MenuData;
import com.example.codeinandroid.R;
import com.example.codeinandroid.RecyclerViewInterface;
import com.example.codeinandroid.adapter.RecyclerAdapter;
import com.example.codeinandroid.model.Data;
import com.example.codeinandroid.prefrences.SharedPref;

import java.util.ArrayList;

public class CodeMenu extends BaseActivity implements RecyclerViewInterface {
    private RecyclerAdapter adapter;
    private ArrayList<Data> mData;
    private String language;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        setContentView(R.layout.activity_code_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle intent = getIntent().getExtras();
        assert intent != null;
        language = String.valueOf(intent.get("language"));
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MenuData menuData = new MenuData(language);
        mData = menuData.getData();
        adapter = new RecyclerAdapter(mData, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnClick(int position, View itemView, String title) {
        Intent intent = new Intent(getApplicationContext(), CodeViewActivity.class);
        intent.putExtra("topic", title);
        intent.putExtra("language", language);
        startActivity(intent);
    }

    @Override
    public void OnLongPress(int position, View itemView) {

    }
}
