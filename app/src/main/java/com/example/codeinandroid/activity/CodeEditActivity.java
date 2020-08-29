package com.example.codeinandroid.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.codeinandroid.CodeModel;
import com.example.codeinandroid.R;
import com.example.codeinandroid.databinding.ActivityCodeEditBinding;
import com.example.codeinandroid.prefrences.SharedPref;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CodeEditActivity extends AppCompatActivity {
    String code;
    private BottomSheetDialog bottomSheetDialog;
    private TextView output;
    CodeModel codeModel;
    ActivityCodeEditBinding activityCodeEditBinding;
    private ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.code_edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.run:
//                new CodeExec(getApplicationContext(), output, "c", code, "", "gcc -o main *.c", "main", "main.c");
                return true;
            case R.id.Item1:
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Item3:
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Item2:
                Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMode()) {
            Toast.makeText(this, "" + "darkMode", Toast.LENGTH_SHORT).show();
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.lightTheme);
        }
        setContentView(R.layout.activity_code_edit);
        progressDialog = new ProgressDialog(this);
        createBottomSheet();

        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            code = String.valueOf(intent.get("code"));
        }
        System.out.println("OUTPUT" + code);
        codeModel = new CodeModel(code, "swift");
        activityCodeEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_code_edit);
        activityCodeEditBinding.setViewModel(codeModel);

        activityCodeEditBinding.setLifecycleOwner(this);
    }

    private void createBottomSheet() {
        if (bottomSheetDialog == null) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.output_sheet, null);
            output = view.findViewById(R.id.Output);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
        }
    }
}
