package com.example.codeinandroid.fragment;

import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codeinandroid.CodeModel;
import com.example.codeinandroid.R;
import com.example.codeinandroid.databinding.FragmentEditorBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditorFragment extends Fragment {


    public EditorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        CodeModel codeModel;
        codeModel = new CodeModel("", "swift");
        FragmentEditorBinding fragmentEditorBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_editor);
        fragmentEditorBinding.setViewModel(codeModel);

        fragmentEditorBinding.setLifecycleOwner(getViewLifecycleOwner()) ;
        return inflater.inflate(R.layout.fragment_editor, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
