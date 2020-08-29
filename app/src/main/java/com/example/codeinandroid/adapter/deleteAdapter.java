package com.example.codeinandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeinandroid.CodeExec;
import com.example.codeinandroid.R;
import com.example.codeinandroid.activity.CodeEditActivity;
import com.example.codeinandroid.model.DataModel;
import com.example.codeinandroid.prefrences.SharedPref;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class deleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TITLE = 1;
    private static final int EXP = 2;
    private static final int CODE_EXEC = 3;
    private static final int IMAGE = 4;
    private static final int CODE_NOT_EXEC = 5;
    private static final int TABLE = 6;
    private ArrayList<DataModel> mData;
    private Context context;
    private SharedPref sharedPref;
    private String language;

    public deleteAdapter(ArrayList<DataModel> mData, Context context, String language) {
        this.mData = mData;
        this.language = language;
        this.context = context;
        sharedPref = new SharedPref(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TITLE:
                view = LayoutInflater.from(context).inflate(R.layout.title, parent, false);
                return new title(view);
            case EXP:
                view = LayoutInflater.from(context).inflate(R.layout.exp, parent, false);
                return new exp(view);
            case CODE_EXEC:
                view = LayoutInflater.from(context).inflate(R.layout.code_exec, parent, false);
                return new codeExec(view);
            case TABLE:
                view = LayoutInflater.from(context).inflate(R.layout.table_column, parent, false);
                return new table(view);
            case CODE_NOT_EXEC:
                view = LayoutInflater.from(context).inflate(R.layout.code_not_exec, parent, false);
                return new codeNotExec(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.image, parent, false);
                return new image(view);

        }

    }

    @Override
    public int getItemViewType(int position) {
        DataModel model = mData.get(position);
        switch (model.getType()) {
            case "TITLE":
                return TITLE;
            case "TABLE":
                return TABLE;
            case "EXP":
                return EXP;
            case "CODE_EXEC":
                return CODE_EXEC;
            case "IMAGE":
                return IMAGE;
            default:
                return CODE_NOT_EXEC;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DataModel model = mData.get(position);
        switch (model.getType()) {
            case "TITLE":
                ((title) holder).title.setText(model.getData());
                if (sharedPref.loadNightMode()) {
                    ((title) holder).title.setTextColor(context.getResources().getColor(R.color.textColorDark));
                } else {
                    ((title) holder).title.setTextColor(context.getResources().getColor(R.color.textColor));
                }
                break;
            case "EXP":
                ((exp) holder).exp.setText(model.getData());
                if (sharedPref.loadNightMode()) {
                    ((exp) holder).exp.setTextColor(context.getResources().getColor(R.color.textColorDark));
                } else {
                    ((exp) holder).exp.setTextColor(context.getResources().getColor(R.color.textColor));
                }
                break;
            case "TABLE":
                ((table) holder).column1.setText(model.getColumn1());
                ((table) holder).column2.setText(model.getColumn2());
                ((table) holder).column3.setText(model.getColumn3());
                break;
            case "CODE_EXEC":
                ((codeExec) holder).code
                        .setTheme(Theme.ANDROIDSTUDIO)
                        .setCode(model.getData()).setLanguage(Language.JAVA)
                        .setWrapLine(true).setShowLineNumber(true).setZoomEnabled(true).apply();

                ((codeExec) holder).run.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        ExecuteCode(((codeExec) holder).output, model.getData(), "");
                    }
                });
                ((codeExec) holder).edit.setOnClickListener(v -> {
                    Intent intent = new Intent(context, CodeEditActivity.class);
                    String s = model.getData();
                    intent.putExtra("code", model.getData());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });

                break;
            case "CODE_NOT_EXEC":
                ((codeNotExec) holder).code
                        .setTheme(Theme.ANDROIDSTUDIO)
                        .setCode(model.getData()).setLanguage(Language.JAVA)
                        .setWrapLine(false)
                        .setShowLineNumber(true).apply();
                break;
            default:
                ((image) holder).image.setImageDrawable(context.getDrawable(model.getImage()));
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ExecuteCode(TextView output, String code, String input) {
        System.out.println(language);
        switch (language) {
            case "C":
                new CodeExec(context, language.toLowerCase(), output, code, input, "c", "gcc -o main *.c", "main", "main.c");
                break;
            case "Java":
                new CodeExec(context, language.toLowerCase(), output, code, input, "java", "javac", "java -Xmx128M -Xms16M", "HelloWorld.java");
                break;
            case "Python":
                new CodeExec(context, "python3", output, code, input, "py", "0", "python3 main.py", "main.py");
                break;
            case "Cpp":
                new CodeExec(context, "cpp", output, code, input, "cpp", "g++ -o main *.cpp", "main", "main.cpp");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class title extends RecyclerView.ViewHolder {
        TextView title;
        LinearLayout container;

        title(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            container = itemView.findViewById(R.id.container);
        }
    }

    static class exp extends RecyclerView.ViewHolder {
        TextView exp;

        exp(@NonNull View itemView) {
            super(itemView);
            exp = itemView.findViewById(R.id.exp);
        }
    }

    static class codeExec extends RecyclerView.ViewHolder {
        CodeView code;
        FloatingActionButton run, copy, edit;
        TextView output;

        codeExec(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code2);
            output = itemView.findViewById(R.id.output);
            run = itemView.findViewById(R.id.run);
            edit = itemView.findViewById(R.id.edit);
            copy = itemView.findViewById(R.id.copy);
        }
    }


    static class codeNotExec extends RecyclerView.ViewHolder {
        CodeView code;

        codeNotExec(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.code3);
        }
    }

    static class image extends RecyclerView.ViewHolder {
        ImageView image;

        image(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

    static class table extends RecyclerView.ViewHolder {
        TextView column1, column2, column3;

        table(@NonNull View itemView) {
            super(itemView);

            column1 = itemView.findViewById(R.id.column1);
            column2 = itemView.findViewById(R.id.column2);
            column3 = itemView.findViewById(R.id.column3);
        }
    }


}
