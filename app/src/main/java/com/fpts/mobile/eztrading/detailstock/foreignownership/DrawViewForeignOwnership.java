package com.fpts.mobile.eztrading.detailstock.foreignownership;

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
import com.fpts.mobile.eztrading.common.ValueApp;

import java.util.ArrayList;

public class DrawViewForeignOwnership extends ScrollView {

    private int mHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mHeightItem = mHeight / 15;

    private Context context;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Typeface typeface;

    public DrawViewForeignOwnership(Context context, ArrayList<String> arrayList) {
        super(context);
        this.context = ValueApp.contextStockDetailForeignship;
        this.arrayList = arrayList;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.topMargin = 12;
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);

        if (arrayList != null && arrayList.size() > 0) {
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_khoi_luong_duoc_mua), arrayList.get(0)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_khoi_luong_hien_tai), arrayList.get(1)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_khoi_luong_con_lai), arrayList.get(2)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_ty_le_so_huu), arrayList.get(3)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_nn_mua_YTD), arrayList.get(4)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_nn_mua_YTD_30ngay), arrayList.get(5)));
            linearLayout.addView(line());
            linearLayout.addView(mLinearLayout(getContext().getString(R.string.watchlist_detail_cap_nhat_den), arrayList.get(6)));
        }
        this.addView(linearLayout);
    }

    private LinearLayout mLinearLayout(String title, String value) {
        LinearLayout linearLayout = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 12, 8, 12);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.weight = 1;
        TextView textView = new TextView(context);
        textView.setText(title);
        textView.setTextColor(ColorApp.colorText);
        textView.setTextSize(pxToDp(mHeightItem * 3 / 10));
        textView.setLayoutParams(params1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTypeface(typeface);

        TextView textView1 = new TextView(context);
        textView1.setText(value);
        textView1.setTextColor(ColorApp.colorText);
        textView1.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        textView1.setTypeface(typeface);
        textView1.setTextSize(pxToDp(mHeightItem * 3 / 10));
        linearLayout.addView(textView);
        linearLayout.addView(textView1);
        return linearLayout;
    }

    private View line() {
        View view = new View(context);
        view.setBackgroundColor(ColorApp.colorBackgroundDivider);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(8, 8, 8, 8);
        view.setLayoutParams(params);
        return view;
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
