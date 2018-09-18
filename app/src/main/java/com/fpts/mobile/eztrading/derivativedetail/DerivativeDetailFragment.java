package com.fpts.mobile.eztrading.derivativedetail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class DerivativeDetailFragment extends Fragment {

    private DrawViewDerivativeDetail drawViewDerivativeDetail;
    private DataDerivativeDetail dataDerivativeDetail;
    private LinearLayout linearLayout;

    private Socket socket;

    private ArrayList<Emitter.Listener> emitterArrayList = new ArrayList<>();
    private ArrayList<Emitter.Listener> emitterArrayList1 = new ArrayList<>();

    public DerivativeDetailFragment() {

    }

    public static DerivativeDetailFragment newInstance() {
        DerivativeDetailFragment fragment = new DerivativeDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_derivative_detail, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.viewDerivativeDetail);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ValueApp.contextPS = getContext();
        ValueApp.activityPS = getActivity();
        ValueApp.fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        dataDerivativeDetail = new DataDerivativeDetail();
        try {
            socket = IO.socket(dataDerivativeDetail.getLinkSocket());
        } catch (URISyntaxException e) {
        }

        new AsynDerivative().execute();
    }

    public class AsynDerivative extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            drawViewDerivativeDetail = new DrawViewDerivativeDetail(ValueApp.contextPS, dataDerivativeDetail.getJson(), dataDerivativeDetail.code);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            linearLayout.removeAllViews();
            linearLayout.addView(drawViewDerivativeDetail);

            for (int i = 0; i < dataDerivativeDetail.getChannel().size(); i++) {
                final int finalI = i;
                emitterArrayList.add(new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        String k = String.valueOf(args[0]);
                        if (finalI == 2) {
                            drawViewDerivativeDetail.changeData(0, "" + k);
                        } else {
                            drawViewDerivativeDetail.changeData(finalI, "" + k);
                        }
                    }
                });
                socket.on(dataDerivativeDetail.getChannel().get(i), emitterArrayList.get(i));
            }

            for (int i = 0; i < dataDerivativeDetail.code.size(); i++) {
                final int vt = i * 26 + 1;
                emitterArrayList1.add(new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        String k = String.valueOf(args[0]);
                        drawViewDerivativeDetail.changeData(vt, "" + k);
                    }
                });
                socket.on("REALTIME_EP_TRADE_QTY_" +dataDerivativeDetail.code.get(i), emitterArrayList1.get(i));
            }

            socket.connect();
        }
    }
}