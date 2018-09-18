package com.fpts.mobile.eztrading.marketOverviewDetail;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketOverviewDetailFragment extends Fragment {

    LinearLayout linearLayoutContent;

    public static MarketOverviewDetailFragment newInstance() {
        MarketOverviewDetailFragment fragment = new MarketOverviewDetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market_overview_detail, container, false);

        linearLayoutContent = view.findViewById(R.id.linear_layout);
        linearLayoutContent.addView(new DrawViewMarketDetail(getContext(), getChildFragmentManager()));

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