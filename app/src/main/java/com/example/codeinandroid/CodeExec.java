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
    public CodeExec(Context context, String language, TextView view, String code, String input,String ext, String compile, String execute, String mainfile) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "https://tpcg.tutorialspoint.com/tpcg.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            System.out.println("Success" + response);
            view.setText(Html.fromHtml(response,Html.FROM_HTML_MODE_LEGACY));
        }, error -> {
            System.out.println("Error" + error);
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lang", language);
                params.put("code", code);
                params.put("stdinput", input);
                params.put("ext",ext);
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
