package com.fpts.mobile.eztrading.detailstock.financial;

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

public class FinancialFragment extends Fragment {
    private String code = "";

    private LinearLayout linearLayout;
    private DrawViewFinancial drawViewFinancial;

    public static FinancialFragment newInstance(String code) {
        FinancialFragment fragment = new FinancialFragment();
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
        View view = inflater.inflate(R.layout.fragment_financial, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);
        new AsyntaskData().execute();

        return view;
    }

    private class AsyntaskData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            drawViewFinancial = new DrawViewFinancial(getContext(), GetJsonFinancialOverview.getData(getContext(), code));
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
        linearLayout.addView(drawViewFinancial);
    }
}
