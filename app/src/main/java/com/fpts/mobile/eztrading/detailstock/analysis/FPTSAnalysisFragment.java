package com.fpts.mobile.eztrading.detailstock.analysis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpts.mobile.eztrading.R;

public class FPTSAnalysisFragment extends Fragment {
    private String code = "";

    public static FPTSAnalysisFragment newInstance(String code) {
        FPTSAnalysisFragment fragment = new FPTSAnalysisFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Code", code);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getString("Code");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        return view;
    }
}
