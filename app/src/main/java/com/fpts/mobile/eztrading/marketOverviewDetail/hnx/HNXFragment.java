package com.fpts.mobile.eztrading.marketOverviewDetail.hnx;

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
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class HNXFragment extends Fragment {
    private ArrayList<String> arrayListCode = new ArrayList<>();
    private ArrayList<String> arrayListPrice = new ArrayList<>();
    private DrawViewHNX drawViewHNX;
    private LinearLayout linearLayout;
    ArrayList<Emitter.Listener> listeners = new ArrayList<>();
    private Socket socketMarket;

    {
        try {
            socketMarket = IO.socket(DataMarketHNX.getLinkSocket());
        } catch (URISyntaxException e) {
        }
    }

    public static HNXFragment newInstance() {
        HNXFragment fragment = new HNXFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hnx, container, false);
        linearLayout = view.findViewById(R.id.linear_layout);
        new AsynMarket().execute();

        return view;
    }

    public class AsynMarket extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            update();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            drawViewHNX = new DrawViewHNX(getActivity(), getContext(), DataMarketHNX.getCode(getContext()),
                    GetJsonHNX.getData(getContext()));
            publishProgress(1);
            return null;
        }
    }

    private void update() {
        linearLayout.addView(drawViewHNX);
        for (int i = 0; i < DataMarketHNX.getChannel(getContext()).size(); i++) {
            final int finalI = i;
            listeners.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = String.valueOf(args[0]);
                    drawViewHNX.changeData(finalI, data);
                }
            });
            socketMarket.on(DataMarketHNX.getChannel(getContext()).get(finalI), listeners.get(finalI));
        }
        socketMarket.connect();
    }
}
