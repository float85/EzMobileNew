package com.fpts.mobile.eztrading.detailstock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.detailstock.trading.DataTrading;

public class DetailStockFragment extends Fragment {
    private String symbol = "";
    private LinearLayout linearLayout;

    public static DetailStockFragment newInstance(String code) {
        DetailStockFragment fragment = new DetailStockFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Code", code);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            symbol = getArguments().getString("Code");
        }

        ValueApp.contextStockDetail = getContext();
        ValueApp.activityStockDetail = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_stock, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);
        linearLayout.addView(new DrawViewStockDetail(getContext(), symbol, getChildFragmentManager()));

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(symbol);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(DataDetailStock.getNameCompany(getContext(), symbol));

        new AsynData().execute();
        return view;
    }

    private class AsynData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
