package com.fpts.mobile.eztrading.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.derivative.DataDerivative;
import com.fpts.mobile.eztrading.derivative.DrawViewDerivative;
import com.fpts.mobile.eztrading.events.DataEvents;
import com.fpts.mobile.eztrading.events.DrawViewEventsHome;
import com.fpts.mobile.eztrading.events.GetJsonEvents;
import com.fpts.mobile.eztrading.marketOverview.DataMaket;
import com.fpts.mobile.eztrading.marketOverview.DrawViewMarket;
import com.fpts.mobile.eztrading.news.DataNews;
import com.fpts.mobile.eztrading.news.DrawViewNewsHome;
import com.fpts.mobile.eztrading.news.GetJsonNews;
import com.fpts.mobile.eztrading.watchlist.DrawViewWatchList;
import com.fpts.mobile.eztrading.watchlist.DataWatchList;
import com.fpts.mobile.eztrading.watchlist.GetJsonWatchList;
import com.fpts.mobile.eztrading.worldindexes.DrawViewWorldIndexs;
import com.fpts.mobile.eztrading.worldindexes.DataWorldIndexs;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private DrawViewMarket drawViewMarket;
    private DrawViewWatchList drawViewWatchList;
    private DrawViewDerivative drawViewDerivative;
    private DrawViewWorldIndexs drawViewWorldIndexs;
    private DrawViewEventsHome drawViewEventsHome;
    private DrawViewNewsHome drawViewNewsHome;

    private DataWatchList dataWatchList;
    private DataMaket dataMaket;
    private DataWorldIndexs dataWorldIndexs;
    private DataDerivative dataDerivative;

    private LinearLayout viewMarket;
    private LinearLayout viewWatchList;
    private LinearLayout viewDerivative;
    private LinearLayout viewWorldIndexs;
    private LinearLayout viewEvents;
    private LinearLayout viewNews;

    private Socket socketMarket;
    private Socket socketWatchList;


    private Socket socketDerivative;

    ArrayList<Emitter.Listener> listenersMarket = new ArrayList<>();
    ArrayList<Emitter.Listener> listenersWatchList = new ArrayList<>();
    ArrayList<Emitter.Listener> listenersDerivative = new ArrayList<>();

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ValueApp.contextHome = getContext();
        ValueApp.activityHome = getActivity();
        ValueApp.fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        viewMarket = view.findViewById(R.id.viewMarket);
        viewWatchList = view.findViewById(R.id.viewWatchList);
        viewDerivative = view.findViewById(R.id.viewDerivative);
        viewWorldIndexs = view.findViewById(R.id.viewWorldIndexs);
        viewEvents = view.findViewById(R.id.viewEvents);
        viewNews = view.findViewById(R.id.viewNews);

        ((AppCompatActivity) ValueApp.activityHome).getSupportActionBar().setTitle("TRANG CHỦ");
        ((AppCompatActivity) ValueApp.activityHome).getSupportActionBar().setSubtitle("");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        dataMaket = new DataMaket();
        try {
            socketMarket = IO.socket(dataMaket.getLinkSocket());
        } catch (URISyntaxException e) {
        }
        dataWatchList = new DataWatchList();
        try {
            socketWatchList = IO.socket(dataWatchList.getLinkSocket());
        } catch (URISyntaxException e) {
        }
        dataDerivative = new DataDerivative();
        try {
            socketDerivative = IO.socket(dataDerivative.getLinkSocket());
        } catch (URISyntaxException e) {
        }

        dataWorldIndexs = new DataWorldIndexs();

        new AsynMarket().execute();
    }

    public class AsynMarket extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.w("AsynMarket", "onProgressUpdate: " + values[0]);
            switch (values[0]) {
                case 1: {
                    market();
                    break;
                }
                case 2: {
                    watchist();
                    break;
                }
                case 3: {
                    derivative();
                    break;
                }
                case 4: {
                    news();
                    break;
                }
                case 5: {
                    events();
                    break;
                }

                case 6: {
                    worldindexs();
                    break;
                }
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            drawViewMarket = new DrawViewMarket(ValueApp.contextHome, dataMaket.getJson());
            publishProgress(1);

            drawViewWatchList = new DrawViewWatchList(ValueApp.contextHome, dataWatchList.getCode(ValueApp.contextHome), GetJsonWatchList.getData(ValueApp.contextHome));
            publishProgress(2);

            drawViewDerivative = new DrawViewDerivative(ValueApp.contextHome, dataDerivative.getJson());
            publishProgress(3);

            ArrayList newsAppList = GetJsonNews.getData(ValueApp.contextHome);
            DataNews.saveCache(ValueApp.contextHome, newsAppList);

            drawViewNewsHome = new DrawViewNewsHome(ValueApp.contextHome, newsAppList);
            publishProgress(4);

            ArrayList eventsAppList = GetJsonEvents.loadData(ValueApp.contextHome);
            DataEvents.saveCache(eventsAppList, ValueApp.contextHome);
            drawViewEventsHome = new DrawViewEventsHome(ValueApp.contextHome, eventsAppList);
            publishProgress(5);

            drawViewWorldIndexs = new DrawViewWorldIndexs(ValueApp.contextHome, dataWorldIndexs.getJson());
            publishProgress(6);


            Log.d("static : ", "" + ID.arrayList.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private void derivative() {
        viewDerivative.removeAllViews();
        viewDerivative.addView(drawViewDerivative);
        for (int i = 0; i < dataDerivative.getChannel().size(); i++) {
            final int finalI = i;
            listenersDerivative.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = String.valueOf(args[0]);
                    drawViewDerivative.changeData(finalI, data);
                }
            });
            socketDerivative.on(dataDerivative.getChannel().get(finalI), listenersDerivative.get(finalI));
        }
        socketDerivative.connect();
    }

    private void market() {

        viewMarket.removeAllViews();
        viewMarket.addView(drawViewMarket);

        for (int i = 0; i < dataMaket.getChannel().size(); i++) {
            final int finalI = i;
            listenersMarket.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = String.valueOf(args[0]);
                    Log.w("HomeFragment", "call: " + finalI + "  " + data);
                    drawViewMarket.changeData(finalI, data);
                }
            });
            socketMarket.on(dataMaket.getChannel().get(finalI), listenersMarket.get(finalI));
        }
        socketMarket.connect();
    }

    private void watchist() {

        viewWatchList.removeAllViews();
        viewWatchList.addView(drawViewWatchList);

        for (int i = 0; i < dataWatchList.getChannel(ValueApp.contextHome).size(); i++) {
            final int finalI = i;
            listenersWatchList.add(new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String data = String.valueOf(args[0]);
                    Log.w("HomeFragment", "call: watchist  " + finalI + " " + data);
                    if (finalI < dataWatchList.getCode(ValueApp.contextHome).size() * 3) {
                        drawViewWatchList.changeData(finalI, data);
                        Log.w("HomeFragment", "call: watchist  " + finalI + " " + data);
                    } else {

                    }
                }
            });
            Log.w("HomeFragment", "watchist: " + dataWatchList.getChannel(ValueApp.contextHome).get(finalI));
            socketWatchList.on(dataWatchList.getChannel(ValueApp.contextHome).get(finalI), listenersWatchList.get(finalI));
        }
        socketWatchList.connect();
    }

    private void events() {
        viewEvents.removeAllViews();
        viewEvents.addView(drawViewEventsHome);
    }

    private void news() {
        viewNews.removeAllViews();
        viewNews.addView(drawViewNewsHome);
    }

    private void worldindexs() {
        viewWorldIndexs.removeAllViews();
        viewWorldIndexs.addView(drawViewWorldIndexs);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        socketDerivative.off();
        socketWatchList.off();
        socketMarket.off();
        ID.arrayList = new ArrayList<>();
    }
}