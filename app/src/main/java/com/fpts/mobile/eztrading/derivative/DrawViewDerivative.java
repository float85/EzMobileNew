package com.fpts.mobile.eztrading.derivative;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
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
import com.fpts.mobile.eztrading.derivativedetail.DerivativeDetailFragment;
import com.fpts.mobile.eztrading.marketOverviewDetail.MarketOverviewDetailFragment;

import java.util.ArrayList;

public class DrawViewDerivative extends LinearLayout implements View.OnClickListener {

    Context context;
    Activity activity;

    private Typeface typeface;
    private Typeface typefaceBold;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int heightItem = height / 15;
    private int heightTitle1 = height * 7 / 90;

    private ArrayList<String> arrayList = new ArrayList<>();

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private String TAG = getClass().getName();

    FragmentTransaction fragmentTransaction;

    public DrawViewDerivative(Context context, ArrayList<String> arrayList) {
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

        for (int i = 0; i < arrayList.size() / 8; i++) {
            linearLayout.addView(linearLayoutC(i * 8));
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
        textView.setText(R.string.watchlist_derivate);
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
        textView2.setWidth(width * 2 / 10);
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
        textView3.setText(getContext().getString(R.string.home_change) + "◥");
        textView3.setTextSize(pxToDp(heightItem / 3));
        textView3.setMaxLines(1);
        textView3.setTypeface(typeface);
        textView3.setTextColor(ColorApp.colorText);
        textView3.setId(ID.setID("chuyen" + TAG));
        textView3.setOnClickListener(this);

        TextView textView4 = new TextView(context);
        textView4.setWidth(width * 3 / 10);
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
        textView1.setTypeface(typeface);
        textView1.setTextColor(ColorApp.colorText);
        textView1.setId(ID.setID("" + id + TAG));

        TextView textView2 = new TextView(context);
        textView2.setWidth(width * 2 / 10);
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
        textView3.setText(arrayList.get(id + 2) + "%");
        textView3.setTextSize(pxToDp(heightItem / 3));
        textView3.setMaxLines(1);
        textView3.setTypeface(typeface);
        textView3.setTextColor(ColorApp.colorText);
        textView3.setVisibility(GONE);
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

        TextView textView5 = new TextView(context);
        textView5.setWidth(width * 3 / 10);
        textView5.setHeight(heightItem);
        textView5.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView5.setText(arrayList.get(id + 4));
        textView5.setTextSize(pxToDp(heightItem / 3));
        textView5.setMaxLines(1);
        textView5.setTypeface(typeface);
        textView5.setTextColor(ColorApp.colorText);
        textView5.setId(ID.setID("" + (id + 4) + TAG));

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);
        linearLayout.addView(textView4);
        linearLayout.addView(textView5);
        return linearLayout;
    }

    private void initColor() {
        int i = 0;
        while (i < arrayList.size()) {
            changeColor(i);
            i = i + 8;
        }
    }

    private void changeColor(int i) {
        TextView textView1 = (TextView) findViewById(ID.getID("" + i + TAG));
        TextView textView2 = (TextView) findViewById(ID.getID("" + (i + 1) + TAG));
        TextView textView3 = (TextView) findViewById(ID.getID("" + (i + 2) + TAG));
        TextView textView4 = (TextView) findViewById(ID.getID("" + (i + 3) + TAG));
        if (arrayList.get(i + 1).equals(arrayList.get(i + 6))) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextCeiling);
                    textView2.setTextColor(ColorApp.colorTextCeiling);
                    textView3.setTextColor(ColorApp.colorTextCeiling);
                    textView4.setTextColor(ColorApp.colorTextCeiling);
                }
            });
        } else if (arrayList.get(i + 1).equals(arrayList.get(i + 7))) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextFloor);
                    textView2.setTextColor(ColorApp.colorTextFloor);
                    textView3.setTextColor(ColorApp.colorTextFloor);
                    textView4.setTextColor(ColorApp.colorTextFloor);
                }
            });
        } else if (arrayList.get(i + 1).equals(arrayList.get(i + 5))) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextRef);
                    textView2.setTextColor(ColorApp.colorTextRef);
                    textView3.setTextColor(ColorApp.colorTextRef);
                    textView4.setTextColor(ColorApp.colorTextRef);
                }
            });
        } else if (String.valueOf(arrayList.get(i + 3).charAt(0)).equalsIgnoreCase("-")) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextDown);
                    textView2.setTextColor(ColorApp.colorTextDown);
                    textView3.setTextColor(ColorApp.colorTextDown);
                    textView4.setTextColor(ColorApp.colorTextDown);
                }
            });
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView1.setTextColor(ColorApp.colorTextUp);
                    textView2.setTextColor(ColorApp.colorTextUp);
                    textView3.setTextColor(ColorApp.colorTextUp);
                    textView4.setTextColor(ColorApp.colorTextUp);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == ID.getID("chuyen" + TAG)) {
            TextView textView = (TextView) findViewById(ID.getID("chuyen" + TAG));
            if (textView.getText().equals(context.getString(R.string.home_change))) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(context.getString(R.string.home_change_per) + "◥");
                    }
                });
            } else {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(context.getString(R.string.home_change) + "◥");
                    }
                });
            }
            int i = 0;
            while (i < arrayList.size()) {
                TextView textView3 = (TextView) findViewById(ID.getID("" + (i + 2) + TAG));
                TextView textView4 = (TextView) findViewById(ID.getID("" + (i + 3) + TAG));
                if (textView.getText().equals(context.getString(R.string.home_change))) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView3.setVisibility(GONE);
                            textView4.setVisibility(VISIBLE);
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView3.setVisibility(VISIBLE);
                            textView4.setVisibility(GONE);
                        }
                    });
                }
                i = i + 8;
            }
        }
        if (view.getId() == ID.getID("a" + TAG)) {
            if (findViewById(ID.getID("b" + TAG)).getVisibility() == VISIBLE) {
                findViewById(ID.getID("b" + TAG)).setVisibility(GONE);
                findViewById(ID.getID("c" + TAG)).setVisibility(GONE);
            } else {
                findViewById(ID.getID("b" + TAG)).setVisibility(VISIBLE);
                findViewById(ID.getID("c" + TAG)).setVisibility(VISIBLE);
            }
        }
        if (view.getId() == ID.getID("intent" + TAG)) {
            fragmentTransaction.replace(R.id.linear_layout, DerivativeDetailFragment.newInstance());
            fragmentTransaction.commit();
            ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
        }
    }

    public void changeData(final int id, final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (s.equals(arrayList.get(id))) {

                } else {
                    arrayList.set(id, s);
                    int row = id % 8;
                    int line = id / 8;

                    if (row < 4) {
                        TextView textView = (TextView) findViewById(ID.getID("" + id + TAG));

                        if (row == 1 || row == 2) {
                            changeColor(line * 8);
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(s);
                                textView.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                            }
                        });

                        Thread.currentThread();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setBackgroundColor(ColorApp.TRANSPARENT);
                            }
                        });
                    }
                }
            }
        }).start();
    }
}
