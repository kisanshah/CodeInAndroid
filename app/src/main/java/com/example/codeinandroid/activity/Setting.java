package com.example.codeinandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;

import java.util.ArrayList;
import java.util.Arrays;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class Setting extends AppCompatActivity implements AdapterView.OnItemClickListener {
    CodeView codeView;
    Switch themeToggle, wrapLineSwitch, zoomSwitch;
    Spinner spinner;
    SharedPref sharedPref;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        setTitle("CodeInAndroid - Setting");
        if (sharedPref.loadNightMode()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        recyclerView = findViewById(R.id.recyclerView);
        themeToggle = findViewById(R.id.themeToggle);
        codeView = findViewById(R.id.codeView);
        wrapLineSwitch = findViewById(R.id.wrapLineSwitch);
        zoomSwitch = findViewById(R.id.zoomSwitch);
        spinner = findViewById(R.id.spinner);
        CodeViewApply();
        if (sharedPref.loadWrapLine()) {
            wrapLineSwitch.setChecked(true);
        }

        if (sharedPref.loadZoom()) {
            zoomSwitch.setChecked(true);
        }

        ArrayList<String> themes = new ArrayList<>();
        themes.add("AGATE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, themes);
        spinner.setAdapter(adapter);
        zoomSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPref.setZoom(true);
                CodeViewApply();
            } else {
                sharedPref.setZoom(false);
                CodeViewApply();
            }
        });
        wrapLineSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPref.setWrapLine(true);
                CodeViewApply();
            } else {
                sharedPref.setWrapLine(false);
                CodeViewApply();
            }
        });


        if (sharedPref.loadNightMode()) {
            themeToggle.setChecked(true);
        }
        themeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPref.setNightMode(true);
                restartApp();
            } else {
                sharedPref.setNightMode(false);
                restartApp();
            }
        });
    }

    private void restartApp() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(i);
    }

    void CodeViewApply() {
        codeView.setTheme(Theme.ANDROIDSTUDIO)
                .setCode("public class Main {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"This will be printed\");\n" +
                        "    }\n" +
                        "}").setLanguage(Language.JAVA)
                .setWrapLine(sharedPref.loadWrapLine()).setShowLineNumber(true).setZoomEnabled(sharedPref.loadZoom()).apply();
        System.out.println(Arrays.toString(Language.values()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
