package com.fpts.mobile.eztrading.watchlistdetail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class WatchlistDetailFragment extends Fragment {
    private LinearLayout linearLayout;
    private DrawViewWatchlistDetail drawViewWatchlistDetail;
    private Socket socket;
    ArrayList<Emitter.Listener> listenersWatchList = new ArrayList<>();

    {
        try {
            socket = IO.socket(DataWatchlistDetail.getLinkSocket());
        } catch (URISyntaxException e) {
        }
    }

    public static WatchlistDetailFragment newInstance() {
        WatchlistDetailFragment fragment = new WatchlistDetailFragment();


        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ValueApp.activityWatchlistDetailFragment = getActivity();
        ValueApp.contextWatchlistDetailFragment = getContext();
        ValueApp.fragmentTransactionWatchlistDetailFragment = getFragmentManager().beginTransaction();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist_detail, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bảng giá");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
        new AsyncTaskData().execute();
        return view;
    }

    private class AsyncTaskData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            drawViewWatchlistDetail = new DrawViewWatchlistDetail(ValueApp.contextWatchlistDetailFragment,
                    DataWatchlistDetail.getCode(ValueApp.contextWatchlistDetailFragment),
                    GetJsonWatchListDetail.getData(ValueApp.contextWatchlistDetailFragment));

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
        linearLayout.addView(drawViewWatchlistDetail);
        Log.w("WatchlistDetailFragment", "update: ");
        for (int i = 0; i < DataWatchlistDetail.getChannel().size(); i++) {
            final int finalI = i;
            if (finalI == 23 || finalI == 24) {
                listenersWatchList.add(new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        String data = String.valueOf(args[0]);
                        Log.w("WatchlistDetailFragment", "call: " + finalI + data);
                        drawViewWatchlistDetail.changeData(finalI - 1, data);
                    }
                });
            } else {
                listenersWatchList.add(new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        String data = String.valueOf(args[0]);
                        Log.w("WatchlistDetailFragment", "call: " + finalI + data);

                        if (finalI < DataWatchlistDetail.getCode(ValueApp.contextHome).size() * 22) {
                            drawViewWatchlistDetail.changeData(finalI, data);
                        } else {

                        }
                    }
                });
            }
            socket.on(DataWatchlistDetail.getChannel().get(finalI), listenersWatchList.get(finalI));
        }
    }
}
