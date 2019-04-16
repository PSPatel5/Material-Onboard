package com.pspdevelopers.materialonboard.helper;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private int layoutId;

    public static BaseFragment newInstance(int layoutId) {
        BaseFragment fragmentFirst = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.IMAGE, layoutId);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null)
            layoutId = getArguments().getInt(Constants.IMAGE);
        return inflater.inflate(layoutId, container, false);
    }
}
