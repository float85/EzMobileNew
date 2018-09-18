package com.fpts.mobile.eztrading.detailstock.financialfigures;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;

import java.util.ArrayList;

public class DrawViewFinancialFigures extends ScrollView {

    private Context context;

    private ArrayList<String> arrayList;
    private int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int heightItem = height / 15;

    private Typeface typeface;

    public DrawViewFinancialFigures(Context context, ArrayList<String> arrayList) {
        super(context);
        this.context = context;
        this.arrayList = arrayList;
        typeface = ResourcesCompat.getFont(context, R.font.free_sans);

        init();
    }

    private void init() {
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);

        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size() / 6; i++) {
                linearLayout.addView(mLinearLayout(arrayList.get(i * 6 + 1), arrayList.get(i * 6 + 5)));
                linearLayout.addView(mLine());
            }
            linearLayout.addView(mLinearLayout(arrayList.get(arrayList.size() - 1), ""));
        }
        this.addView(linearLayout);
    }

    private LinearLayout mLinearLayout(String title, String value) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 12, 8, 12);
        linearLayout.setLayoutParams(params);

        TextView txtTitle = new TextView(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.weight = 1;
        txtTitle.setLayoutParams(params1);
        txtTitle.setText(title);
        txtTitle.setGravity(Gravity.CENTER_VERTICAL);
        txtTitle.setTextColor(ColorApp.colorText);
        txtTitle.setTextSize(pxToDp(heightItem * 3 / 10));
        txtTitle.setTypeface(typeface);

        TextView txtValue = new TextView(context);
        txtValue.setText(value == null || value.trim().equalsIgnoreCase("") ? ""
                : (double) Math.round(Double.parseDouble(value) * 100) / 100 + "");
        txtValue.setTextSize(pxToDp(heightItem * 3 / 10));
        txtValue.setTypeface(typeface);
        txtValue.setTextColor(ColorApp.colorText);
        txtValue.setGravity(Gravity.CENTER_VERTICAL);

        linearLayout.addView(txtTitle);
        linearLayout.addView(txtValue);
        return linearLayout;
    }

    private View mLine() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        view.setBackgroundColor(ColorApp.colorBackgroundDivider);

        return view;
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}
