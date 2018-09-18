package com.fpts.mobile.eztrading.worldindexdetail;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;

import java.util.ArrayList;

public class DrawViewWorldIndexDetail extends LinearLayout implements View.OnClickListener {

    Context context;
    Activity activity;

    private Typeface typeface;
    private Typeface typefaceBold;
    private String TAG = getClass().getName();

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int heightItem = height / 15;
    private int heightTitle1 = height * 7 / 90;

    private ArrayList<String> arrayList = new ArrayList<>();

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public DrawViewWorldIndexDetail(Context context, Activity activity, ArrayList<String> arrayList) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList;
        init();
        initColor();
    }

    private void init() {
        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        this.setBackgroundColor(ColorApp.colorBackgroundTable);
        this.setOrientation(VERTICAL);
        this.addView(linearLayoutB());

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        for (int i = 0; i < arrayList.size()/4; i++) {
            linearLayout.addView(linearLayoutC(i * 4));
        }

        this.addView(linearLayout);
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
        textView1.setId(ID.setID(""+id+TAG));

        TextView textView2 = new TextView(context);
        textView2.setWidth(width / 4);
        textView2.setHeight(heightItem);
        textView2.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView2.setText(arrayList.get(id + 1));
        textView2.setTextSize(pxToDp(heightItem / 3));
        textView2.setMaxLines(1);
        textView2.setTypeface(typeface);
        textView2.setTextColor(ColorApp.colorText);
        textView2.setId(ID.setID(""+(id+1)+TAG));

        TextView textView3 = new TextView(context);
        textView3.setWidth(width / 4);
        textView3.setHeight(heightItem);
        textView3.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView3.setText(arrayList.get(id + 2));
        textView3.setTextSize(pxToDp(heightItem / 3));
        textView3.setMaxLines(1);
        textView3.setTypeface(typeface);
        textView3.setTextColor(ColorApp.colorText);
        textView3.setId(ID.setID(""+(id+2)+TAG));

        TextView textView4 = new TextView(context);
        textView4.setWidth(width / 4);
        textView4.setHeight(heightItem);
        textView4.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView4.setText(arrayList.get(id + 3));
        textView4.setTextSize(pxToDp(heightItem / 3));
        textView4.setMaxLines(1);
        textView4.setTypeface(typeface);
        textView4.setTextColor(ColorApp.colorText);
        textView4.setId(ID.setID(""+(id+3)+TAG));

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
        return linearLayout;
    }

    private void initColor() {
        int i = 0;
        while (i < arrayList.size()) {
            TextView textView1 = (TextView) findViewById(ID.getID(""+i+TAG));
            TextView textView2 = (TextView) findViewById(ID.getID(""+(i+1)+TAG));
            TextView textView3 = (TextView) findViewById(ID.getID(""+(i+2)+TAG));
            TextView textView4 = (TextView) findViewById(ID.getID(""+(i+3)+TAG));
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
        switch (view.getId()) {
        }
    }
}
