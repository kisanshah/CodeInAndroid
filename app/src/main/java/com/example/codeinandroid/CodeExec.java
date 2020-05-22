package com.example.codeinandroid;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CodeExec {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CodeExec(Context context, TextView output, String lang, String code, String input, String compile, String execute, String mainfile) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "https://tpcg.tutorialspoint.com/tpcg.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> output.setText(Html.fromHtml(response, Html.FROM_HTML_MODE_LEGACY)), error -> Toast.makeText(context, "Check internet connection", Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lang", lang);
                params.put("code", code);
                params.put("stdinput", input);
                params.put("compile", compile);
                params.put("execute", execute);
                params.put("mainfile", mainfile);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
