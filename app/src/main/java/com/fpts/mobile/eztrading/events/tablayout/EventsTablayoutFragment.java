package com.fpts.mobile.eztrading.events.tablayout;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.databinding.FragEventsTablayoutBinding;
import com.fpts.mobile.eztrading.events.DataEvents;
import com.fpts.mobile.eztrading.events.Event;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class EventsTablayoutFragment extends Fragment {
    FragEventsTablayoutBinding binding;
    EventTabAdapter adapter;
    boolean bDate;
    ArrayList<Event> eventsAppList = new ArrayList<>();
    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    protected int height = width;
    protected int heightItem = height * 4 / 5;

    public static EventsTablayoutFragment newInstance(Boolean bDate) {
        EventsTablayoutFragment fragment = new EventsTablayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("bDate", bDate);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bDate = getArguments().getBoolean("bDate");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_events_tablayout, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventsAppList = DataEvents.getCache(getContext());

        for (int i = 0; i < eventsAppList.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem * 3 / 4);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setWeightSum(13f);
            linearLayout.setBackgroundColor(ColorApp.colorBackground);

            linearLayout.addView(linearLayout(eventsAppList.get(i).getStockCode(), eventsAppList.get(i).getGroupNm()));

            View view1 = new View(getContext());
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams1.setMargins(0, 15, 0, 15);
            view1.setLayoutParams(layoutParams1);
            view1.setBackgroundColor(ColorApp.colorTextHeader);

            linearLayout.addView(view1);

            linearLayout.addView(relativeLayout(eventsAppList.get(i).getDate1(), eventsAppList.get(i).getContent(),
                    eventsAppList.get(i).getMarketname()));

            binding.linear.addView(linearLayout);

            View view2 = new View(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1, 1f);
            view2.setLayoutParams(layoutParams2);
            view2.setBackgroundColor(ColorApp.colorBackgroundDivider);

            binding.linear.addView(view2);
        }
    }

    private LinearLayout linearLayout(String s, String s1) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setVerticalGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);

        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText(s);
        textView.setLayoutParams(layoutParamsText1);
        Typeface type = ResourcesCompat.getFont(getContext(), R.font.free_sans);
        textView.setTypeface(type, Typeface.BOLD);
        textView.setTextColor(ColorApp.colorText);
        textView.setTextSize(heightItem * 3 / 42);

        TextView textView1 = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView1.setText(s1);
        textView1.setLayoutParams(layoutParamsText2);
        textView1.setTypeface(type);
        textView1.setTextColor(ColorApp.colorText);
        textView1.setTextSize(heightItem * 1 / 21);

        linearLayout.addView(textView);
        linearLayout.addView(textView1);
        return linearLayout;
    }

    @SuppressLint("ResourceType")
    private LinearLayout relativeLayout(String s, String s1, String s2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 10f);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);

        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(layoutParams1);
        relativeLayout.setBackgroundColor(ColorApp.colorBackground);

        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText1.setMargins(10, 0, 10, 5);
        textView.setText(s);
        textView.setId(100);
        layoutParamsText1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParamsText1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textView.setLayoutParams(layoutParamsText1);
        Typeface type = ResourcesCompat.getFont(getContext(), R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorTextNewsDate);
        textView.setTextSize(pxToDp(heightItem * 5 / 42));

        if (bDate) {
//            textView.setVisibility(View.VISIBLE);
            textView.setText(s);
        } else {
//            textView.setVisibility(View.GONE);
            textView.setText("");
        }

        TextView textView1 = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText.addRule(RelativeLayout.ABOVE, 100);
        layoutParamsText.setMargins(10, 5, 0, 0);
        textView1.setLayoutParams(layoutParamsText);
        textView1.setText(s1);
        textView1.setId(101);
        textView1.setTypeface(type);
        textView1.setTextColor(ColorApp.colorTextHeader);
        textView1.setTextSize(pxToDp(heightItem * 15 / 112));

        TextView textView2 = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText2.addRule(RelativeLayout.ABOVE, 101);
        layoutParamsText2.setMargins(10, 5, 0, 0);
        textView2.setLayoutParams(layoutParamsText2);
        textView2.setText(s2);
        textView2.setTypeface(type, Typeface.BOLD);
        textView2.setTextColor(ColorApp.colorTextHeader);
        textView2.setTextSize(pxToDp(heightItem * 4 / 28));

        relativeLayout.addView(textView);
        relativeLayout.addView(textView1);
        relativeLayout.addView(textView2);

        linearLayout.addView(relativeLayout);

        return linearLayout;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
