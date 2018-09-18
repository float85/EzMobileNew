package com.fpts.mobile.eztrading.news.tablayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.databinding.FragmentTablayoutNewsBinding;
import com.fpts.mobile.eztrading.news.DataNews;
import com.fpts.mobile.eztrading.news.HomeNewsFragment;
import com.fpts.mobile.eztrading.news.NewsArticle;
import com.fpts.mobile.eztrading.newsDetail.NewsDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class TablayoutFragment extends Fragment implements View.OnClickListener{
    private static String TAG = "TablayoutFragment";
    private List<NewsArticle> newsList;
    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    protected int height = width;
    protected int heightItem = height * 4 / 5;

    FragmentTablayoutNewsBinding binding;

    public static TablayoutFragment newInstance() {
        TablayoutFragment fragment = new TablayoutFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tablayout_news, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsList = DataNews.getCache(getContext());

        for (int i = 0; i < newsList.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setWeightSum(10f);
            linearLayout.setId(ID.setID(TAG + "llItem" + i));
            linearLayout.setBackgroundColor(ColorApp.colorBackground);

            linearLayout.addView(linearLayout(newsList.get(i).getNewsImg()));
            linearLayout.addView(relativeLayout(newsList.get(i).getNewsDate(), newsList.get(i).getNewsTitle()));

            View view2 = new View(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            view2.setLayoutParams(layoutParams2);
            view2.setBackgroundColor(ColorApp.colorTextHeader);

            binding.line1.addView(view2);

            binding.line1.addView(linearLayout);

            int finalI = i;
            linearLayout.setOnClickListener(view1 -> {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linear_layout, NewsDetailFragment.newInstance(newsList.get(finalI).getNewsId(),
                        newsList.get(finalI).getNewsImg(), "Market News"));
                transaction.commit();

                ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;

                Log.d(TAG, finalI + "");
            });
        }
    }

    private LinearLayout linearLayout(String s) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);

        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParamsText1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParamsText1);

        if (s.equalsIgnoreCase("null")) {
            imageView.setImageResource(R.drawable.icon_app);
        } else {
            Picasso.with(getContext()).load("http://www.fpts.com.vn"  + s).into(imageView);
        }

        linearLayout.addView(imageView);
        return linearLayout;
    }

    @SuppressLint("ResourceType")
    private LinearLayout relativeLayout(String s, String s1) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 7f);
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

        TextView textView1 = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParamsText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsText.addRule(RelativeLayout.ABOVE, 100);
        layoutParamsText.setMargins(10, 5, 0, 0);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView1.setLayoutParams(layoutParamsText);
        textView1.setText(s1);
        textView1.setTypeface(type);
        textView1.setTextColor(ColorApp.colorTextHeader);
        textView1.setTextSize(pxToDp(heightItem * 1 / 7));

        relativeLayout.addView(textView);
        relativeLayout.addView(textView1);

        linearLayout.addView(relativeLayout);

        return linearLayout;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View view) {

    }

//    public void moveFragmentView(String id, String img, String folder) {
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.contentview, NewsDetailFragment.newInstance(id, img, Define.NEWS_TABLAYOUT_Analysis_Comment));
//        transaction.addToBackStack("");
//        transaction.commit();
//    }
}
