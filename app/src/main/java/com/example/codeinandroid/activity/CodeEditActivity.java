package com.example.codeinandroid.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.codeinandroid.CodeExec;
import com.example.codeinandroid.R;
import com.example.codeinandroid.databinding.ActivityCodeEditBinding;
import com.example.codeinandroid.model.CodeModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CodeEditActivity extends AppCompatActivity {
    BottomSheetDialog bottomSheetDialog;
    LinearLayout outPut;
    TextView output;
    ProgressDialog progressDialog;
    CodeModel codeModel;
    ActivityCodeEditBinding activityCodeEditBinding;

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
//                getOutput2(activityCodeEditBinding.editor.getText(), "java", "", output);
                CodeExec codeExec = new CodeExec(getApplicationContext(), output, "c", "#include <stdio.h>\n" +
                        "\n" +
                        "int main()\n" +
                        "{\n" +
                        "    printf(\"Hello, World!\\n\");\n" +
                        "    return 0;\n" +
                        "}", "", "gcc -o main *.c", "main", "main.c");
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
        progressDialog = new ProgressDialog(this);
        createBottomSheet();
        Bundle intent = getIntent().getExtras();
        assert intent != null;
        String code = intent.getString("code");
        codeModel = new CodeModel(code, "swift");
        activityCodeEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_code_edit);
        activityCodeEditBinding.setViewModel(codeModel);

        activityCodeEditBinding.setLifecycleOwner(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getOutput(String code, String lang, String input, TextView view) {
        //parameter to local variable
        final String Code = code;
        final String Input = input;

        progressDialog.show();
        progressDialog.setMessage("Please wait, the code is executing");
        //Clearing cache using deleteCache() function
        File dir = getApplicationContext().getCacheDir();
        //Making a request through StringRequest using Volley Library
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://tpcg.tutorialspoint.com/tpcg.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            progressDialog.dismiss();
            output.setText(Html.fromHtml(response, Html.FROM_HTML_MODE_LEGACY));
            bottomSheetDialog.show();
            Toast.makeText(CodeEditActivity.this, "" + response, Toast.LENGTH_SHORT).show();
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Check internet connection", Toast.LENGTH_LONG).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("exc", "java");
                params.put("lang", "java8");
                params.put("code", Code);
                params.put("stdinput", Input);
                params.put("compile", "javac");
                params.put("execute", "java -Xmx128M -Xms16M");
                params.put("mainfile", "HelloWorld.java");
                return params;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.start();
    }

    private void getOutput2(String code, String lang, String input, final TextView view) {

        //parameter to local variable
        final String Lang = lang;
        final String Code = code;
        final String Input = input;

        progressDialog.show();
        progressDialog.setMessage("Please wait, the code is executing");

        //Clearing cache using deleteCache() function
        File dir = getApplicationContext().getCacheDir();
        deleteCache(dir);

        //Making a request through StringRequest using Volley Library
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://ide.geeksforgeeks.org/main.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            try {
                progressDialog.dismiss();
                JSONObject jsonObject = new JSONObject(response);
                String op = jsonObject.getString("rntError");
                if (op.isEmpty()) {
                    op = jsonObject.getString("output");
                    view.setText("Output:\n" + op);
                    Toast.makeText(CodeEditActivity.this, "" + op, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CodeEditActivity.this, "" + op, Toast.LENGTH_SHORT).show();
                    view.setText("Output:\n" + op);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

        }, error -> {
            Toast.makeText(CodeEditActivity.this, "Error : " + error, Toast.LENGTH_LONG).show();
            view.setText(error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lang", Lang);
                params.put("code", Code);
                params.put("input", Input);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public boolean deleteCache(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
                boolean success = deleteCache(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
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
