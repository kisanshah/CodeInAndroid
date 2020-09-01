package com.example.codeinandroid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codeinandroid.R;
import com.example.codeinandroid.activity.CodeMenu;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardView c = view.findViewById(R.id.c);
        CardView cpp = view.findViewById(R.id.cpp);
        CardView java = view.findViewById(R.id.java);
        CardView python = view.findViewById(R.id.python);

        c.setOnClickListener(this);
        java.setOnClickListener(this);
        python.setOnClickListener(this);
        cpp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), CodeMenu.class);
        String language;
        switch (v.getId()) {
            case R.id.c:
                language = "C";
                break;
            case R.id.cpp:
                language = "Cpp";
                break;
            case R.id.java:
                language = "Java";
                break;
            case R.id.python:
                language = "Python";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        i.putExtra("language", language);
        startActivity(i);
    }
}
