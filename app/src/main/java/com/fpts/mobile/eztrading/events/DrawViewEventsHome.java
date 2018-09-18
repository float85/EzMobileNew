package com.fpts.mobile.eztrading.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
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

import java.util.ArrayList;

public class DrawViewEventsHome extends LinearLayout implements View.OnClickListener {
    Context context;
    private static String TAG = "DrawViewEventsHome";
    ArrayList<Event> eventsAppList;
    private Typeface typeface;
    private Typeface typefaceBold;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 6;
    protected int height = width;
    //    protected int heightItem = height * 4 / 7;
    protected int heightItem1 = height * 4 / 5;
    private int heightItem = height / 15;

    public DrawViewEventsHome(Context context, ArrayList<Event> arrayList) {
        super(context);

        this.context = context;
        this.eventsAppList = arrayList;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);

        init();
    }

    private void init() {
        LinearLayout.LayoutParams layoutParamsAll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setLayoutParams(layoutParamsAll);
        linearLayout2.setPadding(0, 8, 0, 0);
        linearLayout2.setBackgroundColor(ColorApp.colorBackgroundTable);

        LinearLayout.LayoutParams layoutParamsAll1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setLayoutParams(layoutParamsAll1);
        linearLayout1.setId(ID.setID(TAG + "llItem"));
        linearLayout1.setBackgroundColor(ColorApp.colorBackground);

        linearLayout2.addView(linearLayoutHeader());

        for (int i = 0; i < eventsAppList.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem1 * 4 / 3);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundColor(ColorApp.colorBackground);

            linearLayout.addView(relativeLayout(eventsAppList.get(i).getDate1(), eventsAppList.get(i).getContent()));

            View view2 = new View(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            view2.setLayoutParams(layoutParams2);
            view2.setBackgroundColor(ColorApp.colorTextHeader);

            linearLayout1.addView(view2);
            linearLayout1.addView(linearLayout);
        }

        linearLayout2.addView(linearLayout1);
        this.addView(linearLayout2);
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

        LayoutParams paramsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heightItem * 9);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(pxToDp(heightItem * 7 / 2));
        textView.setAllCaps(true);
        textView.setMaxLines(1);
        textView.setTypeface(typefaceBold);
        textView.setText(R.string.events);
        textView.setTextColor(ColorApp.colorTextHeader);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(heightItem * 3, heightItem * 3);
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

    @SuppressLint("ResourceType")
    private LinearLayout relativeLayout(String s, String s1) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(8, 8, 8, 8);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);

        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(layoutParams1);
        relativeLayout.setBackgroundColor(ColorApp.colorBackground);

        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParamsText1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText1.setMargins(10, 0, 10, 5);
        textView.setText(s);
        textView.setId(100);
        layoutParamsText1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParamsText1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        textView.setLayoutParams(layoutParamsText1);
        Typeface type = ResourcesCompat.getFont(context, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorTextNewsDate);
        textView.setTextSize(pxToDp(heightItem * 17 / 8));

        TextView textView1 = new TextView(context);
        RelativeLayout.LayoutParams layoutParamsText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsText.addRule(RelativeLayout.ABOVE, 100);
        layoutParamsText.setMargins(10, 5, 0, 0);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView1.setLayoutParams(layoutParamsText);
        textView1.setText(s1);
        textView1.setTypeface(type);
        textView1.setTextColor(ColorApp.colorTextHeader);
        textView1.setTextSize(pxToDp(heightItem * 10 / 3));

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
