package com.fpts.mobile.eztrading.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.newsDetail.NewsDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrawViewNewsHome extends LinearLayout implements View.OnClickListener {
    private static String TAG = "DrawViewNewsHome";
    Context context;
    ArrayList<NewsArticle> newsList;

    private Typeface typeface;
    private Typeface typefaceBold;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    protected int height = width;
    protected int heightItem = height * 4 / 5;

    public DrawViewNewsHome(Context context, ArrayList<NewsArticle> arrayList) {
        super(context);
        this.context = context;
        this.newsList = arrayList;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);

        init();
    }

    private void init() {
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout6 = new LinearLayout(getContext());
        linearLayout6.setOrientation(LinearLayout.VERTICAL);
        linearLayout6.setLayoutParams(layoutParams6);
        linearLayout6.addView(linearLayoutHeader());
        linearLayout6.setPadding(0, 8, 0, 0);
        linearLayout6.setBackgroundColor(ColorApp.colorBackgroundTable);

        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout5 = new LinearLayout(getContext());
        linearLayout5.setOrientation(LinearLayout.VERTICAL);
        linearLayout5.setLayoutParams(layoutParams5);
        linearLayout5.setId(ID.setID(TAG + "llItem"));
        linearLayout5.setBackgroundColor(ColorApp.colorBackground);

        for (int i = 0; i < 5; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setWeightSum(10f);
//            linearLayout.setId(ID.setID(TAG + "llItem" + i));
            linearLayout.setBackgroundColor(ColorApp.colorBackground);

//            linearLayout.addView(linearLayout(newsList.get(i).getNewsImg()));
            linearLayout.addView(linearLayout());
            linearLayout.addView(relativeLayout(newsList.get(i).getNewsDate(), newsList.get(i).getNewsTitle()));

            View view2 = new View(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            view2.setLayoutParams(layoutParams2);
            view2.setBackgroundColor(ColorApp.colorTextHeader);

            linearLayout5.addView(view2);

            linearLayout5.addView(linearLayout);

            int finalI = i;
            linearLayout5.setOnClickListener(view1 -> {
                ValueApp.fragmentTransaction.replace(R.id.linear_layout, NewsDetailFragment.newInstance(newsList.get(finalI).getNewsId(),
                        newsList.get(finalI).getNewsImg(), "Market News"));
                ValueApp.fragmentTransaction.commit();
                ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;

                Log.d(TAG, finalI + "");
            });
        }

        linearLayout6.addView(linearLayout5);
        this.addView(linearLayout6);
    }

    private RelativeLayout linearLayoutHeader() {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundColor(ColorApp.colorBackgroundHeader);
        relativeLayout.setId(ID.setID(TAG + "llItemAll"));
        relativeLayout.setPadding(8, 0, 8, 0);
        relativeLayout.setOnClickListener(this::onClick);

        LayoutParams paramsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heightItem * 2 / 4);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(pxToDp(heightItem / 5));
        textView.setAllCaps(true);
        textView.setMaxLines(1);
        textView.setTypeface(typefaceBold);
        textView.setText(R.string.news);
        textView.setTextColor(ColorApp.colorTextHeader);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(heightItem / 6, heightItem / 6);
        params.setMargins(8, 4, 8, 4);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        ImageView imageView = new ImageView(context);
        imageView.setPadding(2, 2, 2, 2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(ImageApp.iconArrowRight);

        relativeLayout.addView(textView, paramsText);
        relativeLayout.addView(imageView, params);
        return relativeLayout;
    }

//    private LinearLayout linearLayoutHeader() {
//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.topMargin = 8;
//        params.gravity = Gravity.CENTER;
//        LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setLayoutParams(params);
//        linearLayout.setPadding(8, 0, 8, 0);
//        linearLayout.setOrientation(HORIZONTAL);
//        linearLayout.setBackgroundColor(ColorApp.colorBackgroundHeader);
//        linearLayout.addView(textViewHeader());
//        linearLayout.setId(ID.setID(TAG + "llItemAll"));
//        linearLayout.setOnClickListener(this::onClick);
//
//        ImageView imgDetail = imageviewHeader(ImageApp.iconArrowRight);
////        imgDetail.setId(ID + 2000 + 3);
//        imgDetail.setOnClickListener(this);
//
//        linearLayout.addView(imgDetail);
//        return linearLayout;
//    }

    // TODO: TamHV 8/10/2018 3:19 AM MY
    private LinearLayout linearLayout() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);

        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParamsText1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParamsText1);

//        if (s.equalsIgnoreCase("null")) {
        imageView.setImageResource(R.drawable.icon_app);
//        } else {
//            Picasso.with(getContext()).load("http://www.fpts.com.vn"  + s).into(imageView);
//        }

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
        layoutParamsText.setMargins(10, 10, 0, 0);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView1.setLayoutParams(layoutParamsText);
        textView1.setText(s1);
        textView1.setLines(2);
        textView1.setTypeface(type);
        textView1.setTextColor(ColorApp.colorTextHeader);
        textView1.setTextSize(pxToDp(heightItem * 5 / 28));

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
        if (view.getId() == ID.getID(TAG + "llItemAll")) {
            if (findViewById(ID.getID(TAG + "llItem")).getVisibility() == VISIBLE) {
                findViewById(ID.getID(TAG + "llItem")).setVisibility(GONE);
            } else {
                findViewById(ID.getID(TAG + "llItem")).setVisibility(VISIBLE);
            }
        }
    }
}
