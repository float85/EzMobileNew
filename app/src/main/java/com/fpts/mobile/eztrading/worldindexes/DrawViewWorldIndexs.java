package com.fpts.mobile.eztrading.worldindexes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.marketOverviewDetail.MarketOverviewDetailFragment;
import com.fpts.mobile.eztrading.worldindexdetail.WorldIndexDetailFragment;

import java.util.ArrayList;

public class DrawViewWorldIndexs extends LinearLayout implements View.OnClickListener {

    Context context;
    Activity activity;

    private Typeface typeface;
    private Typeface typefaceBold;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int heightItem = height / 15;
    private int heightTitle1 = height * 7 / 90;

    private ArrayList<String> arrayList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;

    private String TAG = getClass().getName();

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public DrawViewWorldIndexs(Context context, ArrayList<String> arrayList) {
        super(context);
        this.context = ValueApp.contextHome;
        this.activity = ValueApp.activityHome;
        this.arrayList = arrayList;
        this.fragmentTransaction = ValueApp.fragmentTransaction;
        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        this.setBackgroundColor(ColorApp.colorBackgroundTable);
        this.setOrientation(VERTICAL);
        this.addView(linearLayoutA());
        this.addView(linearLayoutB());

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setId(ID.setID("c" + TAG));

        for (int i = 0; i < 5; i++) {
            linearLayout.addView(linearLayoutC(i * 4));
        }

        this.addView(linearLayout);
        initColor();
    }

    private LinearLayout linearLayoutA() {
        LayoutParams params = new LayoutParams(width, heightTitle1);
        params.topMargin = 8;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setPadding(8, 0, 0, 0);

        TextView textView = new TextView(context);
        textView.setWidth(width - (heightItem / 2) - 16);
        textView.setTextSize(pxToDp(heightTitle1 * 2 / 5));
        textView.setMaxLines(1);
        textView.setTypeface(typefaceBold);
        textView.setText("Bảng Giá Thế Giới");
        textView.setTextColor(ColorApp.colorTextHeader);
        textView.setId(ID.setID("a" + TAG));
        textView.setOnClickListener(this);

        LayoutParams params1 = new LayoutParams(heightItem / 2, heightItem / 2);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(params1);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(ImageApp.iconArrowRight);
        imageView.setId(ID.setID("intent" + TAG));
        imageView.setOnClickListener(this);

        linearLayout.addView(textView);
        linearLayout.addView(imageView);

        return linearLayout;
    }

    private LinearLayout linearLayoutB() {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(width, heightItem);
        layoutParams.bottomMargin = 1;
        layoutParams.gravity = Gravity.CENTER;
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setPadding(8, 0, 8, 0);
        linearLayout.setId(ID.setID("b" + TAG));
        linearLayout.setOnClickListener(this);

        TextView textView1 = new TextView(context);
        textView1.setWidth(width / 4);
        textView1.setHeight(heightItem);
        textView1.setGravity(Gravity.CENTER_VERTICAL);
        textView1.setText(getContext().getString(R.string.home_symbol));
        textView1.setTextSize(pxToDp(heightItem / 3));
        textView1.setMaxLines(1);
        textView1.setTypeface(typeface);
        textView1.setTextColor(ColorApp.colorText);

        TextView textView2 = new TextView(context);
        textView2.setWidth(width / 4);
        textView2.setHeight(heightItem);
        textView2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView2.setText(getContext().getString(R.string.home_last));
        textView2.setTextSize(pxToDp(heightItem / 3));
        textView2.setMaxLines(1);
        textView2.setTypeface(typeface);
        textView2.setTextColor(ColorApp.colorText);

        TextView textView3 = new TextView(context);
        textView3.setWidth(width / 4);
        textView3.setHeight(heightItem);
        textView3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView3.setText(getContext().getString(R.string.home_change));
        textView3.setTextSize(pxToDp(heightItem / 3));
        textView3.setMaxLines(1);
        textView3.setTypeface(typeface);
        textView3.setTextColor(ColorApp.colorText);

        TextView textView4 = new TextView(context);
        textView4.setWidth(width / 4);
        textView4.setHeight(heightItem);
        textView4.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView4.setText(getContext().getString(R.string.home_qty));
        textView4.setTextSize(pxToDp(heightItem / 3));
        textView4.setMaxLines(1);
        textView4.setTypeface(typeface);
        textView4.setTextColor(ColorApp.colorText);

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
        return linearLayout;
    }

    private LinearLayout linearLayoutC(int id) {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.bottomMargin = 1;
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setPadding(8, 0, 8, 0);
        linearLayout.setOnClickListener(this);

        TextView textView1 = new TextView(context);
        textView1.setWidth(width / 4);
        textView1.setHeight(heightItem);
        textView1.setText(arrayList.get(id));
        textView1.setTextSize(pxToDp(heightItem / 3));
        textView1.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        textView1.setMaxLines(1);
        textView1.setEllipsize(TextUtils.TruncateAt.END);
        textView1.setTypeface(typeface);
        textView1.setTextColor(ColorApp.colorText);
        textView1.setId(ID.setID("" + id + TAG));

        TextView textView2 = new TextView(context);
        textView2.setWidth(width / 4);
        textView2.setHeight(heightItem);
        textView2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView2.setText(arrayList.get(id + 1));
        textView2.setTextSize(pxToDp(heightItem / 3));
        textView2.setMaxLines(1);
        textView2.setTypeface(typeface);
        textView2.setTextColor(ColorApp.colorText);
        textView2.setId(ID.setID("" + (id + 1) + TAG));

        TextView textView3 = new TextView(context);
        textView3.setWidth(width / 4);
        textView3.setHeight(heightItem);
        textView3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView3.setText(arrayList.get(id + 2));
        textView3.setTextSize(pxToDp(heightItem / 3));
        textView3.setMaxLines(1);
        textView3.setTypeface(typeface);
        textView3.setTextColor(ColorApp.colorText);
        textView3.setId(ID.setID("" + (id + 2) + TAG));

        TextView textView4 = new TextView(context);
        textView4.setWidth(width / 4);
        textView4.setHeight(heightItem);
        textView4.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView4.setText(arrayList.get(id + 3));
        textView4.setTextSize(pxToDp(heightItem / 3));
        textView4.setMaxLines(1);
        textView4.setTypeface(typeface);
        textView4.setTextColor(ColorApp.colorText);
        textView4.setId(ID.setID("" + (id + 3) + TAG));

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
        return linearLayout;
    }

    private void initColor() {
        int i = 0;
        while (i < 20) {
            TextView textView1 = (TextView) findViewById(ID.getID("" + i + TAG));
            TextView textView2 = (TextView) findViewById(ID.getID("" + (i + 1) + TAG));
            TextView textView3 = (TextView) findViewById(ID.getID("" + (i + 2) + TAG));
            TextView textView4 = (TextView) findViewById(ID.getID("" + (i + 3) + TAG));
            if (String.valueOf(arrayList.get(i + 3).charAt(0)).equalsIgnoreCase("-")) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setTextColor(ColorApp.colorBlue);
                        textView2.setTextColor(ColorApp.colorTextDown);
                        textView3.setTextColor(ColorApp.colorTextDown);
                        textView4.setTextColor(ColorApp.colorTextDown);
                    }
                });
            } else {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setTextColor(ColorApp.colorBlue);
                        textView2.setTextColor(ColorApp.colorTextUp);
                        textView3.setTextColor(ColorApp.colorTextUp);
                        textView4.setTextColor(ColorApp.colorTextUp);
                    }
                });
            }
            i = i + 4;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == ID.getID("intent" + TAG)) {
            fragmentTransaction.replace(R.id.linear_layout, WorldIndexDetailFragment.newInstance());
            fragmentTransaction.commit();
            ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
            ValueApp.arrayList.addAll(arrayList);
        }
        if (view.getId() == ID.getID("a" + TAG)) {
            if (findViewById(ID.getID("b" + TAG)).getVisibility() == VISIBLE) {
                findViewById(ID.getID("c" + TAG)).setVisibility(GONE);
                findViewById(ID.getID("b" + TAG)).setVisibility(GONE);
            } else {
                findViewById(ID.getID("c" + TAG)).setVisibility(VISIBLE);
                findViewById(ID.getID("b" + TAG)).setVisibility(VISIBLE);
            }
        }
    }
}
