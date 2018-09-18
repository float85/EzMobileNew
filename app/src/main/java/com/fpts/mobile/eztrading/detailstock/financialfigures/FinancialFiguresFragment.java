package com.fpts.mobile.eztrading.detailstock.financialfigures;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;

public class FinancialFiguresFragment extends Fragment {
    private String code = "";

    private LinearLayout linearLayout;
    private DrawViewFinancialFigures drawViewFinancialFigures;

    public static FinancialFiguresFragment newInstance(String code) {
        FinancialFiguresFragment fragment = new FinancialFiguresFragment();
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
        View view = inflater.inflate(R.layout.fragment_financial_figures, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);
        new AsyntaskData().execute();
        return view;
    }

    private class AsyntaskData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            drawViewFinancialFigures = new DrawViewFinancialFigures(getContext(),
                    GetJsonFinancialFigures.getData(getContext(), code));
            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            update();
        }
    }

    private void update() {
        linearLayout.removeAllViews();
        linearLayout.addView(drawViewFinancialFigures);
    }
}
