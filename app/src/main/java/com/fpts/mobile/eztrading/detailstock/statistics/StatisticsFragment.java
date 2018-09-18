package com.fpts.mobile.eztrading.detailstock.statistics;

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
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.detailstock.trading.GetJsonTrading;
import com.fpts.mobile.eztrading.detailstock.trading.TradingFragment;

public class StatisticsFragment extends Fragment {
    private String code = "";
    private DrawViewStatisticsFragment drawViewStatisticsFragment;

    private LinearLayout linearLayout;

    public static StatisticsFragment newInstance(String code) {
        StatisticsFragment fragment = new StatisticsFragment();
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
        ValueApp.activityStockDetailStatistics = getActivity();
        ValueApp.contextStockDetailStatistics = getContext();
        ValueApp.fragmentTransaction = getFragmentManager().beginTransaction();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        new AsyntaskData().execute();

        linearLayout = view.findViewById(R.id.linear_layout);
        return view;
    }

    class AsyntaskData extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            drawViewStatisticsFragment = new DrawViewStatisticsFragment(getContext(), code,
                    GetJsonStatistics.getStockInfo(getContext(), code),
                    GetJsonStatistics.getQuote(getContext(), code),
                    GetJsonStatistics.getStatisticsPrice(getContext(), code, "1"),
                    GetJsonStatistics.getPlacingOrder(getContext(), code, "1"),
                    GetJsonStatistics.get4(getContext(), code, "1"));
            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {
                case 1:
                    update();
                    break;
            }
        }
    }

    private void update() {
        linearLayout.removeAllViews();
        linearLayout.addView(drawViewStatisticsFragment);
    }
}
