package com.example.codeinandroid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.codeinandroid.R;
import com.example.codeinandroid.prefrences.SharedPref;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;


public class SettingFragment extends Fragment {
    CodeView codeView;
    Switch themeToggle;
    Spinner spinner;
    SharedPref sharedPref;
    ChipGroup filter_group;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        themeToggle = view.findViewById(R.id.themeToggle);
        codeView = view.findViewById(R.id.codeView);
        spinner = view.findViewById(R.id.spinner);
        filter_group = view.findViewById(R.id.filter_group);
        sharedPref = new SharedPref(getContext());

        filter_group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Toast.makeText(getContext(), "" + checkedId, Toast.LENGTH_SHORT).show();
            }
        });

        CodeViewApply();

        ArrayList<String> themes = new ArrayList<>();
        themes.add("AGATE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, themes);
        spinner.setAdapter(adapter);


        if (sharedPref.loadNightMode()) {
            themeToggle.setChecked(true);
        }
        themeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sharedPref.setNightMode(true);
            } else {
                sharedPref.setNightMode(false);
            }
            restartApp();
        });
    }

    private void restartApp() {
        Intent i = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().finish();
        startActivity(i);
    }

    void CodeViewApply() {
        codeView.setTheme(Theme.ANDROIDSTUDIO)
                .setCode("public class Main {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"This will be printed\");\n" +
                        "    }\n" +
                        "}").setLanguage(Language.JAVA)
                .setWrapLine(sharedPref.loadWrapLine()).setShowLineNumber(true).setZoomEnabled(sharedPref.loadZoom()).setStartLineNumber(2).apply();
        System.out.println(Arrays.toString(Language.values()));
    }

}
