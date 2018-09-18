package com.fpts.mobile.eztrading.detailstock.foreignownership;

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

public class ForeignOwnershipFragment extends Fragment {
    private String code = "";

    private LinearLayout linearLayout;
    private DrawViewForeignOwnership drawViewForeignOwnership;

    public static ForeignOwnershipFragment newInstance(String code) {
        ForeignOwnershipFragment fragment = new ForeignOwnershipFragment();

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
        ValueApp.activityStockDetailForeignship = getActivity();
        ValueApp.contextStockDetailForeignship = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foreign_ownership, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);
        new AsynTaskData().execute();
        return view;
    }

    private class AsynTaskData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            drawViewForeignOwnership = new DrawViewForeignOwnership(getContext(),
                    GetJsonForeignOwnership.getData(getContext(), code));
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
        linearLayout.addView(drawViewForeignOwnership);
    }
}
