package com.example.codeinandroid;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class CodeViewActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ArrayList<String> CodeData, ExpData, TitleData;
    ProgressDialog progressDialog;

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

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_view);
        progressDialog = new ProgressDialog(this);
        Bundle intent = getIntent().getExtras();
        if (intent != null) {
            CodeData = intent.getStringArrayList("CodeData");
            ExpData = intent.getStringArrayList("ExpData");
            TitleData = intent.getStringArrayList("TitleData");
        }

        linearLayout = findViewById(R.id.linearLayout);

        String[] Code = CodeData.toArray(new String[CodeData.size()]);
        String[] Data = ExpData.toArray(new String[ExpData.size()]);
        String[] Title = TitleData.toArray(new String[TitleData.size()]);

        createView(Code.length, Data, Code, Title);
    }

    public void createView(int size, String[] explaination, final String[] code, String[] title) {
        Typeface regular = Typeface.createFromAsset(getAssets(), "regular.ttf");
        Typeface bold = Typeface.createFromAsset(getAssets(), "bold.ttf");
        Typeface mediumitalic = Typeface.createFromAsset(getAssets(), "mediumitalic.ttf");
        final LinearLayout[] ll = new LinearLayout[10];
        final TextView[] textViews = new TextView[10];
        final TextView[] textViews1 = new TextView[10];
        final TextView[] outPuts = new TextView[10];

        final ImageButton[] run = new ImageButton[10];
        final ImageButton[] copy = new ImageButton[10];
        final ImageButton[] edit = new ImageButton[10];
        final CodeView[] codeViews = new CodeView[10];

        for (int i = 0; i < size; i++) {

            ll[i] = new LinearLayout(this);
            codeViews[i] = new CodeView(this);
            run[i] = new ImageButton(this);
            copy[i] = new ImageButton(this);
            edit[i] = new ImageButton(this);
            textViews[i] = new TextView(this);
            textViews1[i] = new TextView(this);
            outPuts[i] = new TextView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params2.height = 100;
            params2.width = 100;
            params3.bottomMargin = 20;
            params2.setMargins(10, 10, 10, 10);

            codeViews[i]
                    .setTheme(Theme.ATELIER_CAVE_LIGHT)
                    .setCode(code[i])
                    .setLanguage(Language.JAVA)
                    .setWrapLine(true)
                    .setFontSize(12)
                    .setShowLineNumber(true)
                    .apply();
            run[i].setLayoutParams(params2);
            copy[i].setLayoutParams(params2);
            edit[i].setLayoutParams(params2);

            run[i].setBackground(getDrawable(R.drawable.button_bg));
            run[i].setImageDrawable(getDrawable(R.drawable.run));
            copy[i].setBackground(getDrawable(R.drawable.button_bg));
            copy[i].setImageDrawable(getDrawable(R.drawable.copy));
            edit[i].setBackground(getDrawable(R.drawable.button_bg));
            edit[i].setImageDrawable(getDrawable(R.drawable.edit));
            outPuts[i].setLayoutParams(params3);
            outPuts[i].setTextSize(18);
            outPuts[i].setTypeface(regular);
            outPuts[i].setText("Output:");
            final int finalI = i;
            run[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOutput(code[finalI], "Java", "", outPuts[finalI]);
                }
            });

            edit[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CodeEditActivity.class);
                    intent.putExtra("code", code[finalI]);
                    startActivity(intent);
                }
            });
            copy[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", code[finalI]);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(CodeViewActivity.this, "Code Copied!", Toast.LENGTH_SHORT).show();
                }
            });

            textViews[i].setText(title[i]);
            textViews[i].setLayoutParams(params);
            textViews[i].setTextSize(25);
            textViews[i].setTypeface(bold);

            textViews1[i].setText(explaination[i]);
            textViews1[i].setLayoutParams(params);
            textViews1[i].setTextSize(20);
            textViews1[i].setTypeface(regular);


            ll[i].setLayoutParams(params3);

            linearLayout.addView(textViews[i]);
            linearLayout.addView(textViews1[i]);
            linearLayout.addView(codeViews[i]);
            linearLayout.addView(ll[i]);
            linearLayout.addView(outPuts[i]);
            ll[i].addView(run[i]);
            ll[i].addView(copy[i]);
            ll[i].addView(edit[i]);
        }
    }

    private void getOutput(String code, String lang, String input, final TextView textView) {
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
        String URL = "https://tpcg.tutorialspoint.com/tpcg.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                textView.setText(Html.fromHtml("Output:<br></br>" + response, Html.FROM_HTML_MODE_COMPACT));
                Toast.makeText(CodeViewActivity.this, "" + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Check internet connection", Toast.LENGTH_LONG).show();
            }
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String op = jsonObject.getString("rntError");
                    if (op.isEmpty()) {
                        op = jsonObject.getString("output");
                        view.setText("Output:\n" + op);
                        Toast.makeText(CodeViewActivity.this, "" + op, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CodeViewActivity.this, "" + op, Toast.LENGTH_SHORT).show();
                        view.setText("Output:\n" + op);
                    }
//                    Toast.makeText(CodeViewActivity.this, "OUTPUT" + op, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CodeViewActivity.this, "Error : " + error, Toast.LENGTH_LONG).show();
                view.setText(error.toString());
            }
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

}
