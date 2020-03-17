package com.example.codeinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Data> mData;
    private Context context;
    private RecyclerViewInterface recyclerViewInterface;
    private int header = 0;

    public RecyclerAdapter(ArrayList<Data> mData, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.mData = mData;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).isHeadFlag().isEmpty()) {
            return 1;
        } else {
            return header;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == header) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new header(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new item(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data data = mData.get(position);
        if (getItemViewType(position) == header) {
            ((header) holder).title.setText(data.getTitle());
        } else {
            ((item) holder).title.setText(data.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class item extends RecyclerView.ViewHolder {
        TextView title;

        public item(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewInterface.OnClick(getAdapterPosition(), itemView, mData.get(getAdapterPosition()).getTitle());
                }
            });
        }
    }

    public class header extends RecyclerView.ViewHolder {
        TextView title;

        public header(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);

        }
    }
}
