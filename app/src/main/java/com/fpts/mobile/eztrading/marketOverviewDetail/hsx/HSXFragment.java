package com.fpts.mobile.eztrading.marketOverviewDetail.hsx;

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
import com.fpts.mobile.eztrading.marketOverviewDetail.hnx.DataMarketHNX;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class HSXFragment extends Fragment {
    private ArrayList<String> arrayListCode = new ArrayList<>();
    private ArrayList<String> arrayListPrice = new ArrayList<>();
    private LinearLayout linearLayout;

    DrawViewHSX drawViewHSX;
    private ArrayList<Emitter.Listener> listeners = new ArrayList<>();

    private Socket socketMarket;

    {
        try {
            socketMarket = IO.socket(DataMarketHNX.getLinkSocket());
        } catch (URISyntaxException e) {
        }
    }

    public static HSXFragment newInstance() {
        HSXFragment fragment = new HSXFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hsx, container, false);
        linearLayout = view.findViewById(R.id.linear_layout);

        new AsynMarket().execute();

        return view;
    }

    public class AsynMarket extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            drawViewHSX = new DrawViewHSX(getActivity(), getContext(),
                    DataMarketHSX.getCode(),
                    GetJsonHSX.getData(getContext()));
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
        linearLayout.addView(drawViewHSX);
        for (int i = 0; i < DataMarketHSX.getChannel().size(); i++) {
            final int finalI = i;
            listeners.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    drawViewHSX.changeData(finalI, String.valueOf(args[0]));
                }
            });
            socketMarket.on(DataMarketHSX.getChannel().get(finalI), listeners.get(finalI));
        }
        socketMarket.connect();
    }
}
