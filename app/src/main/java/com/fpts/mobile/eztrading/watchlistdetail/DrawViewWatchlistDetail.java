package com.fpts.mobile.eztrading.watchlistdetail;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.detailstock.DetailStockFragment;

import java.util.ArrayList;

public class DrawViewWatchlistDetail extends LinearLayout {
    private Context context;
    private Activity activity;
    private int width = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int height = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int heightItem = height / 10;
    private int sizeText = pxToDp(heightItem * 2 / 5);

    private int widthSymbol = width / 10;
    private int widthSmall = width / 9;
    private int widthBig = width / 7;
    private int widthBiggest = width / 5;
    private ArrayList<String> arrayListHeader = new ArrayList<>();
    private ArrayList<String> arrayListCode = new ArrayList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    Typeface typeface = ResourcesCompat.getFont(ValueApp.contextHome, R.font.free_sans);
    Typeface typefaceBold = ResourcesCompat.getFont(ValueApp.contextHome, R.font.free_sans_bold);

    ScrollView scrollViewTable;
    HorizontalScrollView horizontalScrollViewTable;

    ScrollView scrollViewSideBar;
    HorizontalScrollView horizontalScrollViewHeader;

    private String TAG = getClass().getName();

    public DrawViewWatchlistDetail(Context context, ArrayList<String> arrayListCode, ArrayList<String> arrayList) {
        super(context);
        this.context = ValueApp.contextWatchlistDetailFragment;
        this.activity = ValueApp.activityWatchlistDetailFragment;
        this.arrayListCode = arrayListCode;
        this.arrayList = arrayList;
        arrayListHeader.add("Giá Khớp");
        arrayListHeader.add("KL Khớp");
        arrayListHeader.add("+/-");
        arrayListHeader.add("Khối lượng");

        arrayListHeader.add("G.M3");
        arrayListHeader.add("KL.M3");
        arrayListHeader.add("G.M2");
        arrayListHeader.add("KL.M2");
        arrayListHeader.add("G.M1");
        arrayListHeader.add("KL.M1");

        arrayListHeader.add("G.B1");
        arrayListHeader.add("KL.B1");
        arrayListHeader.add("G.B2");
        arrayListHeader.add("KL.B2");
        arrayListHeader.add("G.B3");
        arrayListHeader.add("KL.B3");

        arrayListHeader.add("TC");
        arrayListHeader.add("Trần");
        arrayListHeader.add("Sàn");

        arrayListHeader.add("Mở");
        arrayListHeader.add("Cao");
        arrayListHeader.add("Thấp");

        arrayListHeader.add("NN Mua");
        arrayListHeader.add("NN Bán");

        this.setBackgroundColor(ColorApp.colorBackgroundTable);
        this.setOrientation(VERTICAL);
        this.addView(linearLayoutUp());
        this.addView(linearLayoutDown());
        initColor();
    }

    private LinearLayout linearLayoutUp() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);

        LinearLayout.LayoutParams layoutParamsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText.setMargins(0, 0, 1, 1);

        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParamsText);
        textView.setWidth(widthSymbol);
        textView.setText("Mã");
        textView.setTypeface(typeface);
        textView.setHeight(heightItem * 3 / 5);
        textView.setBackgroundColor(ColorApp.colorBackground);
        textView.setTextSize(pxToDp(heightItem * 2 / 5));
        textView.setPadding(5, 0, 0, 0);
        textView.setTextColor(ColorApp.colorText);
        linearLayout.addView(textView);

        horizontalScrollViewHeader = new Ngang(context);
        horizontalScrollViewHeader.setTag("horizontalScrollViewHeader");
        horizontalScrollViewHeader.setHorizontalScrollBarEnabled(false);

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(HORIZONTAL);

        for (int i = 0; i < arrayListHeader.size(); i++) {
            textView = new TextView(context);
            textView.setLayoutParams(layoutParamsText);
            textView.setHeight(heightItem * 3 / 5);
            textView.setBackgroundColor(ColorApp.colorBackground);
            textView.setTextSize(pxToDp(heightItem * 2 / 5));
            textView.setWidth(this.getWidth(i));
//            textView.setPadding(0, 0, 8, 0);
            textView.setText(arrayListHeader.get(i));
            textView.setTypeface(typeface);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ColorApp.colorText);
            linearLayout1.addView(textView);
        }

        horizontalScrollViewHeader.addView(linearLayout1);
        linearLayout.addView(horizontalScrollViewHeader);

        return linearLayout;
    }

    private LinearLayout linearLayoutDown() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);

        LayoutParams layoutParamsText = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsText.setMargins(0, 0, 1, 1);

        scrollViewSideBar = new Doc(context);
        scrollViewSideBar.setTag("scrollViewSideBar");
        scrollViewSideBar.setVerticalScrollBarEnabled(false);

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setLayoutParams(layoutParams);
        linearLayout1.setOrientation(VERTICAL);
        for (int i = 0; i < arrayListCode.size(); i++) {
            TextView textView = new TextView(context);
            textView.setWidth(widthSymbol);
            textView.setHeight(heightItem);
            textView.setBackgroundColor(ColorApp.colorBackground);
            textView.setText(arrayListCode.get(i));
            textView.setTextSize(sizeText);
            textView.setTypeface(typefaceBold);
            textView.setLayoutParams(layoutParamsText);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(5, 0, 0, 0);
            textView.setTextColor(ColorApp.colorText);
            textView.setId(ID.setID("trai" + i + TAG));
            linearLayout1.addView(textView);
        }
        scrollViewSideBar.addView(linearLayout1);
        linearLayout.addView(scrollViewSideBar);

        horizontalScrollViewTable = new Ngang(context);
        scrollViewTable = new Doc(context);

        horizontalScrollViewTable.setTag("horizontalScrollViewTable");
        scrollViewTable.setTag("scrollViewTable");

        horizontalScrollViewTable.setHorizontalScrollBarEnabled(false);
        scrollViewTable.setVerticalScrollBarEnabled(false);

        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setOrientation(VERTICAL);

        for (int i = 0; i < arrayListCode.size(); i++) {
            final int finalI = i;
            LinearLayout linearLayoutLine = new LinearLayout(context);
            linearLayoutLine.setLayoutParams(layoutParams);
            linearLayoutLine.setOrientation(HORIZONTAL);
            linearLayoutLine.setBackgroundColor(ColorApp.colorBackgroundTable);
            linearLayoutLine.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setFragment(DetailStockFragment.newInstance(arrayListCode.get(finalI)));
                    ValueApp.vt = ValueApp.ENUM_VT.FROM_WATCHLIST_DETAIL;
                }
            });
            for (int j = 0; j < arrayListHeader.size(); j++) {
                int vt = (arrayListHeader.size() * i) + j;
                TextView textView = new TextView(context);
                textView.setHeight(heightItem);
                textView.setWidth(getWidth(j));
                textView.setPadding(0, 0, 8, 0);
                textView.setTextSize(sizeText);
                textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                textView.setBackgroundColor(ColorApp.colorBackground);
                textView.setLayoutParams(layoutParamsText);
                textView.setTypeface(typeface);
                textView.setTextColor(ColorApp.colorText);
                textView.setText(arrayList.get(vt));
                textView.setId(ID.setID("phai" + vt + TAG));
                linearLayoutLine.addView(textView);
            }
            linearLayout2.addView(linearLayoutLine);
        }

        scrollViewTable.addView(linearLayout2);
        horizontalScrollViewTable.addView(scrollViewTable);
        linearLayout.addView(horizontalScrollViewTable);

        return linearLayout;
    }

    private void setFragment(Fragment fragment) {
        ValueApp.fragmentTransactionWatchlistDetailFragment.replace(R.id.linear_layout, fragment);
        ValueApp.fragmentTransactionWatchlistDetailFragment.commit();
        ValueApp.vt = ValueApp.ENUM_VT.FROM_WATCHLIST_DETAIL;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private int getWidth(int i) {
        int k = i % arrayListHeader.size();
        int so;
        if (k == 0 || k == 2 || k == 4 || k == 6 || k == 8 || k == 10 || k == 12 || k == 14) {
            so = widthSmall;
        } else if (k == 1 || k == 5 || k == 7 || k == 9 || k == 11 || k == 13 || k == 15) {
            so = widthBig;
        } else if (k == 3) {
            so = widthBiggest;
        } else if (k >= 16 && k <= 21) {
            so = widthSmall;
        } else {
            so = widthBig;
        }
        return so;
    }

    class Ngang extends HorizontalScrollView {

        public Ngang(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            String tag = (String) this.getTag();

            if (tag.equalsIgnoreCase("horizontalScrollViewTable")) {
                horizontalScrollViewHeader.scrollTo(l, 0);
            } else {
                horizontalScrollViewTable.scrollTo(l, 0);
            }
        }
    }

    class Doc extends ScrollView {

        public Doc(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {

            String tag = (String) this.getTag();

            if (tag.equalsIgnoreCase("scrollViewTable")) {
                scrollViewSideBar.scrollTo(0, t);
            } else {
                scrollViewTable.scrollTo(0, t);
            }
        }
    }

    public void initColor() {
        int i = 0;
        Log.d("mang day", "" + String.valueOf(ID.arrayList));
        while (i < arrayList.size()) {
            changeColor(i);
            i++;
        }
    }

    private void changeColor(int i) {
        int vt = i % arrayListHeader.size();
        int dong = i / arrayListHeader.size();
        double so;
        int cl = 0;
        double tc = Double.parseDouble(arrayList.get(dong * arrayListHeader.size() + 16).replace(",", ""));
        double tran = Double.parseDouble(arrayList.get(dong * arrayListHeader.size() + 17).replace(",", ""));
        double san = Double.parseDouble(arrayList.get(dong * arrayListHeader.size() + 18).replace(",", ""));
        if (vt != arrayListHeader.size() - 1) {
            so = Double.parseDouble(arrayList.get(i) == null || arrayList.get(i).equalsIgnoreCase("")
                    || arrayList.get(i).equalsIgnoreCase("ATC") ?
                    "0" : arrayList.get(i).replace(",", ""));
            cl = Mau(tc, tran, san, so);
        }
        if (vt == 0) {
            TextView textView = findViewById(ID.getID("trai" + dong + TAG));
            TextView textView1 = findViewById(ID.getID("phai" + i + TAG));
            TextView textView2 = findViewById(ID.getID("phai" + (i + 1) + TAG));
            TextView textView3 = findViewById(ID.getID("phai" + (i + 2) + TAG));
            int finalCl = cl;
            ValueApp.activityWatchlistDetailFragment.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setTextColor(finalCl);
                    textView1.setTextColor(finalCl);
                    textView2.setTextColor(finalCl);
                    textView3.setTextColor(finalCl);
                }
            });
        } else if (vt == 4 || vt == 6 || vt == 8 || vt == 10 || vt == 12 || vt == 14) {
            TextView textView1 = findViewById(ID.getID("phai" + i + TAG));
            TextView textView2 = findViewById(ID.getID("phai" + (i + 1) + TAG));
            int finalCl1 = cl;
            ValueApp.activityWatchlistDetailFragment.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(finalCl1);
                    textView2.setTextColor(finalCl1);
                }
            });
        } else if (vt == 16) {
            TextView textView1 = findViewById(ID.getID("phai" + i + TAG));
            TextView textView2 = findViewById(ID.getID("phai" + (i + 1) + TAG));
            TextView textView3 = findViewById(ID.getID("phai" + (i + 2) + TAG));
            ValueApp.activityWatchlistDetailFragment.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextRef);
                    textView2.setTextColor(ColorApp.colorTextCeiling);
                    textView3.setTextColor(ColorApp.colorTextFloor);
                }
            });
        } else if (vt == 19 || vt == 20 || vt == 21) {
            TextView textView = findViewById(ID.getID("phai" + i + TAG));
            int finalCl1 = cl;
            ValueApp.activityWatchlistDetailFragment.runOnUiThread(() -> textView.setTextColor(finalCl1));
        }
    }

    private int Mau(double tc, double tran, double san, double so) {
        if (so == tc)
            return ColorApp.colorTextRef;
        if (so == tran)
            return ColorApp.colorTextCeiling;
        if (so == san)
            return ColorApp.colorTextFloor;
        if (so > tc)
            return ColorApp.colorTextUp;
        if (so < tc)
            return ColorApp.colorTextDown;
        return 0;
    }

    public void changeData(final int id, final String s) {
        new Thread(() -> {
            if (s.equals(arrayList.get(id))) {

            } else {
                arrayList.set(id, s);
                TextView textView = findViewById(ID.getID("phai" + id + TAG));
                changeColor(id);

                activity.runOnUiThread(() -> {
                    textView.setText(s);
                    textView.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                });

                Thread.currentThread();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(() -> textView.setBackgroundColor(ColorApp.colorBackground));
            }
        }).start();
    }
}
