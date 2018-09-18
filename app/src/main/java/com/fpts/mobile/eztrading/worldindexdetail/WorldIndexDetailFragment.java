package com.fpts.mobile.eztrading.worldindexdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorldIndexDetailFragment extends Fragment {

    private DrawViewWorldIndexDetail drawViewWorldIndexDetail;
    private LinearLayout linearLayout;

    public WorldIndexDetailFragment() {

    }

    public static WorldIndexDetailFragment newInstance() {
        WorldIndexDetailFragment fragment = new WorldIndexDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worldindexsdetail, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.worldindexfragment);
        drawViewWorldIndexDetail = new DrawViewWorldIndexDetail(getContext(), getActivity(), ValueApp.arrayList);

        ValueApp.contextWorldIndexesFragment = getContext();
        ValueApp.activityWorldIndexesFragment = getActivity();

        ((AppCompatActivity) ValueApp.activityWorldIndexesFragment).getSupportActionBar().setTitle(R.string.world_indexes);
        ((AppCompatActivity) ValueApp.activityWorldIndexesFragment).getSupportActionBar().setSubtitle("");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayout.addView(drawViewWorldIndexDetail);
    }

}