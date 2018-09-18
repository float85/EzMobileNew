package com.fpts.mobile.eztrading.detailstock.trading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.marketOverviewDetail.hnx.DataMarketHNX;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class TradingFragment extends Fragment {

    private String code = "";
    private LinearLayout linearLayout;
    ArrayList<Emitter.Listener> arrayListListener = new ArrayList<>();

    private Socket socketMarket;

    {
        try {
            socketMarket = IO.socket(DataTrading.getLinkSocket());
        } catch (URISyntaxException e) {
        }
    }

    private DrawViewTradingFragment drawViewTradingFragment;

    public static TradingFragment newInstance(String code) {
        TradingFragment fragment = new TradingFragment();
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
        ValueApp.fragmentTransactionStockDetailTradingFragment = getFragmentManager().beginTransaction();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trading, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);

        new AsyntaskData().execute();
        return view;
    }

    private class AsyntaskData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            drawViewTradingFragment = new DrawViewTradingFragment(getContext(), getActivity(), code,
                    GetJsonTrading.getStockInfo(getContext(), code),
                    GetJsonTrading.getQuote(getContext(), code),
                    GetJsonTrading.getArraylistChart(getContext(), code),
                    GetJsonTrading.getArraylistChartAll(getContext(), code),
                    GetJsonTrading.getArrayListNews(getContext(), code));
            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            updatePrice();
        }
    }

    private void updatePrice() {
        linearLayout.removeAllViews();
        linearLayout.addView(drawViewTradingFragment);

        for (int i = 0; i < DataTrading.getColumnTitleKenh("VIC", "HOSE").size(); i++) {
            int finalI = i;
            arrayListListener.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = String.valueOf(args[0]);
                    Log.d("TradingFragment", "" + finalI + "    " + data);
                    drawViewTradingFragment.change(finalI, data);
                }
            });
            socketMarket.on(DataTrading.getColumnTitleKenh("VIC", "HOSE").get(finalI), arrayListListener.get(finalI));
        }
//        socketMarket.connect();
    }
}
