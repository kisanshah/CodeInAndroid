package com.example.codeinandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Java extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<Data> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AddingData();
        adapter = new RecyclerAdapter(mData, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
    }

    private void AddingData() {
        mData = new ArrayList<>();

//        mData.add(new Data("Basic", "true"));
//        mData.add(new Data("syntax", ""));
//        mData.add(new Data("data types", ""));
//        mData.add(new Data("variables", ""));
//        mData.add(new Data("", ""));
//        mData.add(new Data("", ""));
//        mData.add(new Data("", ""));
        mData.add(new Data("Control Statements", "true"));
        mData.add(new Data("if-else", ""));
        mData.add(new Data("switch", ""));
        mData.add(new Data("for Loop", ""));
        mData.add(new Data("while loop", ""));
        mData.add(new Data("break", ""));
        mData.add(new Data("continue", ""));
        mData.add(new Data("Oop Concepts - 1", "true"));
        mData.add(new Data("Classes and Object", ""));
        mData.add(new Data("Methods", ""));
        mData.add(new Data("Method Overloading", ""));
        mData.add(new Data("Constructor", ""));
        mData.add(new Data("Constructor Overloading", ""));
//        mData.add(new Data("Access Modifiers", ""));
        mData.add(new Data("this Keyword", ""));
        mData.add(new Data("", ""));
        mData.add(new Data("", ""));
        mData.add(new Data("", ""));
        mData.add(new Data("", ""));
    }

    @Override
    public void OnClick(int position, View itemView, String title) {

        ArrayList<String> exp = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();
        ArrayList<String> titleData = new ArrayList<>();

        try {
            InputStream is = getAssets().open("java_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            JSONObject jsonObject = new JSONObject(json);
            String[] codename = {"code1", "code2", "code3", "code4", "code5"};
            String[] expname = {"exp1", "exp2", "exp3", "exp4", "exp5"};
            String[] titlename = {"title1", "title2", "title3", "title4", "title5"};
            JSONArray jsonArray = jsonObject.getJSONArray(title);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employee = jsonArray.getJSONObject(i);
                for (String s : codename) {
                    String a = employee.get(s).toString();
                    if (!a.isEmpty()) {
                        code.add(a);
                    }

                }
                for (String s : expname) {
                    String b = employee.get(s).toString();
                    if (!b.isEmpty()) {
                        exp.add(b);
                    }
                }
                for (String s : titlename) {
                    String c = employee.get(s).toString();
                    if (!c.isEmpty()) {
                        titleData.add(c);
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "" + exp.size(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), CodeViewActivity.class);
        intent.putStringArrayListExtra("CodeData", code);
        intent.putStringArrayListExtra("ExpData", exp);
        intent.putStringArrayListExtra("TitleData", titleData);
        Toast.makeText(this, "" + titleData, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void OnLongPress(int position, View itemView) {

    }
}
