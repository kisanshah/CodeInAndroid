package com.example.codeinandroid;

import android.view.View;

public interface RecyclerViewInterface {
    void OnClick(int position, View itemView, String title);

    void OnLongPress(int position, View itemView);
}