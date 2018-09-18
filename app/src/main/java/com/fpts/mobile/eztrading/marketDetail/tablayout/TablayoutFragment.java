package com.fpts.mobile.eztrading.marketDetail.tablayout;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.databinding.FragDetailsMostactiveBinding;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;

public class TablayoutFragment extends Fragment {
    FragDetailsMostactiveBinding binding;
    int intIndex;
    String tablayout;
    boolean isLight = true;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    protected int height = width;
    protected int heightItem = height / 4;

    public static TablayoutFragment newInstance(String idURL, String tabLayout) {
        TablayoutFragment fragment = new TablayoutFragment();

        Bundle bundle = new Bundle();
        bundle.putString("idURL", idURL);
        bundle.putString("tablayout", tabLayout);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_details_mostactive, container, false);
        if (getArguments() != null) {
            String strIndex = getArguments().getString("idURL");
            tablayout = getArguments().getString("tablayout");

            if (strIndex.equalsIgnoreCase("HNX")) {
                intIndex = 2;
            } else if (strIndex.equalsIgnoreCase("VNI")) {
                intIndex = 1;
            } else if (strIndex.equalsIgnoreCase("UPCOM")) {
                intIndex = 3;
            } else if (strIndex.equalsIgnoreCase("VN30")) {
                intIndex = 4;
            } else if (strIndex.equalsIgnoreCase("HNX30")) {
                intIndex = 5;
            }
        }

        ValueApp.activityMarketDetailTablayout = getActivity();
        ValueApp.contextMarketDetailTablayout = getContext();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        //mode theme
//        binding.llTablayoutColor.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
//                getResources().getColor(R.color.colorBackgroundDark));
//        binding.txtSymbol.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtOpen.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtClose.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtHigh.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtLow.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtVolume.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        binding.txtChange.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));

        new Thread(() -> {
            ArrayList<TopRealtime> list = GetJsonMarketTablayout.loadData(ValueApp.contextMarketDetailTablayout, String.valueOf(intIndex), tablayout);

            ValueApp.activityMarketDetailTablayout.runOnUiThread(() -> {
                for (int i = 0; i < list.size(); i++) {
                    loadData(list.get(i));
                }
            });
        }).start();
    }

    public void loadData(TopRealtime dataTopGainers) {
//        LayoutIndexdetailactivityTopstockheaderBinding bindingLayout;
//        View convertView = LayoutInflater.from(App.getInstance().getApplicationContext())
//                .inflate(R.layout.layout_indexdetailactivity_topstockheader, null);
//        bindingLayout = DataBindingUtil.bind(convertView);
//
//        bindingLayout.txtSymbol.setText(dataTopGainers.getSCode());
//        bindingLayout.txtOpen.setText(dataTopGainers.getSOpen());
//        bindingLayout.txtClose.setText(dataTopGainers.getSClose());
//        bindingLayout.txtHigh.setText(dataTopGainers.getSHighest());
//        bindingLayout.txtLow.setText(dataTopGainers.getSLowest());
//        bindingLayout.txtVolume.setText(dataTopGainers.getSTotalShares());
//        bindingLayout.txtChange.setText(dataTopGainers.getSChangePercent());
//        binding.linear.addView(bindingLayout.getRoot());
//
//        bindingLayout.txtOpen.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSOpen())));
//
//        bindingLayout.txtClose.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSClose())));
//
//        bindingLayout.txtHigh.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSHighest())));
//
//        bindingLayout.txtLow.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSLowest())));
//
//        bindingLayout.txtSymbol.setTextColor(getResources().getColor(R.color.blue));
////        bindingLayout.txtSymbol.setTypeface(null, Typeface.BOLD);
//
//        bindingLayout.txtChange.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));
//        bindingLayout.txtVolume.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
//                getResources().getColor(R.color.colorFontDark));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(ValueApp.contextMarketDetailTablayout);
        layoutParams.setMargins(7, 7, 7, 7);
        linearLayout.setPadding(5, 0, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        for (int i = 0; i < 7; i++) {
            LinearLayout.LayoutParams layoutParams1 = null;
            TextView textView = new TextView(ValueApp.contextMarketDetailTablayout);
            String s = "";
            if (i == 0) {
                s = dataTopGainers.getSCode();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                textView.setTextColor(ValueApp.contextMarketDetailTablayout.getResources().getColor(R.color.blue));
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            }

            if (i == 1) {
                s = dataTopGainers.getSOpen();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                textView.setTextColor(ValueApp.contextMarketDetailTablayout.getResources().getColor(setColorTextView(dataTopGainers, dataTopGainers.getSOpen())));
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            if (i == 2) {
                s = dataTopGainers.getSClose();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                textView.setTextColor(ValueApp.contextMarketDetailTablayout.getResources().getColor(setColorTextView(dataTopGainers, dataTopGainers.getSClose())));
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            if (i == 3) {
                s = dataTopGainers.getSHighest();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                textView.setTextColor(ValueApp.contextMarketDetailTablayout.getResources().getColor(setColorTextView(dataTopGainers, dataTopGainers.getSHighest())));
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            if (i == 4) {
                s = dataTopGainers.getSLowest();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                textView.setTextColor(ValueApp.contextMarketDetailTablayout.getResources().getColor(setColorTextView(dataTopGainers, dataTopGainers.getSLowest())));
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            if (i == 5) {
                s = dataTopGainers.getSTotalShares();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2.5f);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            if (i == 6) {
                s = dataTopGainers.getSChangePercent();
                layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            }

            textView.setText(s);
            textView.setLayoutParams(layoutParams1);
            Typeface type = ResourcesCompat.getFont(ValueApp.contextMarketDetailTablayout, R.font.free_sans);
            textView.setTypeface(type);
            textView.setMaxLines(1);
            textView.setTextSize((int) (heightItem * 1 / 2 / Resources.getSystem().getDisplayMetrics().density));

            linearLayout.addView(textView);
        }

        View view = new View(ValueApp.contextMarketDetailTablayout);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2, 1.5f);
        view.setLayoutParams(layoutParams2);
        view.setBackgroundColor(ColorApp.colorGray);

        binding.linear.addView(linearLayout);
        binding.linear.addView(view);
    }

    public int setColorTextView(TopRealtime dataTopGainers, String values) {
        int color_no_change;
        color_no_change = ColorApp.colorText;

        double val = 0;
        try {
            val = Double.parseDouble(values);
        } catch (Exception e) {
            val = 0;
        }
        try {
            double ref, ceiling, floor;
            try {
                ref = Double.parseDouble(dataTopGainers.getSRefercence());
            } catch (Exception e) {
                ref = 0;
            }
            try {
                ceiling = Double.parseDouble(dataTopGainers.getSCeiling());
            } catch (Exception e) {
                ceiling = 0;
            }
            try {
                floor = Double.parseDouble(dataTopGainers.getSFloor());
            } catch (Exception e) {
                floor = 0;
            }
            if (val >= ceiling) {
                return R.color.purple;
            } else if (val == ref) {
                return R.color.orange;
            } else if (val <= floor) {
                return R.color.blue;
            } else if (val < ref && val > floor) {
                return R.color.red;
            } else if (val > ref && val < ceiling) {
                return R.color.green;
            }
        } catch (Exception e) {
            return color_no_change;
        }

        return color_no_change;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
