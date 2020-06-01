package com.example.codeinandroid.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeinandroid.R;
import com.example.codeinandroid.adapter.deleteAdapter;
import com.example.codeinandroid.data.Cdata;
import com.example.codeinandroid.model.DataModel;
import com.example.codeinandroid.prefrences.SharedPref;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;

public class CodeViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private deleteAdapter adapter;
    private ArrayList<DataModel> mData;
    private Multimap<String, Object> data;
    private Cdata c;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete);

        Bundle intent = getIntent().getExtras();
        assert intent != null;
        String topic = String.valueOf(intent.get("topic"));
        String language = String.valueOf(intent.get("language"));

        data = ArrayListMultimap.create();


        Toast.makeText(this, "" + language, Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recycler_view);
        mData = new ArrayList<>();
        c = new Cdata(this);
        switch (language) {
            case "C":
            case "Cpp":
            case "Java":
            case "Python":
                mData = c.create(topic, language);
                break;
        }
        adapter = new deleteAdapter(mData, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}