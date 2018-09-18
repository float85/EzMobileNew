package com.fpts.mobile.eztrading.watchlist;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
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
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.detailstock.DetailStockFragment;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.watchlistSearch.WatchlistSearchFragment;
import com.fpts.mobile.eztrading.watchlistdetail.WatchlistDetailFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DrawViewWatchList extends LinearLayout implements View.OnClickListener {
    private final int ID = 2000000;
    private Context context;
    private Activity activity;
    private Typeface typeface;
    private Typeface typefaceBold;
    private FragmentTransaction fragmentTransaction;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int heightItem = height / 15;

    private ArrayList<String> arrayListCode;
    private ArrayList<String> arrayListPrice;//matchPrice,refPrice,totalQuantity
    private ArrayList<String> arrayListTitle;
    private boolean isChange = true;

    // TODO: HoaDT 7/24/2018 9:58 AM isChange = true: show change, = false: show Change percent
    public DrawViewWatchList(Context context, ArrayList<String> arrayListCode,
                             ArrayList<String> arrayListPrice) {
        super(context);
        this.context = ValueApp.contextHome;
        this.activity = ValueApp.activityHome;

        this.fragmentTransaction = ValueApp.fragmentTransaction;
        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        this.arrayListCode = arrayListCode;
        this.arrayListPrice = arrayListPrice;
        init();
    }

    private void init() {
        arrayListTitle = new ArrayList<>();
        arrayListTitle.add(getContext().getString(R.string.home_symbol));
        arrayListTitle.add(getContext().getString(R.string.home_last));
        arrayListTitle.add(getContext().getString(R.string.home_change) + "◥");
        arrayListTitle.add(getContext().getString(R.string.home_change_per) + "◥");
        arrayListTitle.add(getContext().getString(R.string.home_qty));

        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(ColorApp.colorBackgroundTable);
        this.addView(linearLayoutHeader());
        this.addView(linearLayoutTitle());
        this.addView(linearLayoutContent());

    }

    private LinearLayout linearLayoutHeader() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 8;
        params.gravity = Gravity.CENTER;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setPadding(8, 0, 8, 0);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundHeader);

        LayoutParams params1 = new LayoutParams(width - 16 - (width / 16 + 16) * 3, heightItem);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(pxToDp(heightItem * 4 / 10));
        textView.setAllCaps(true);
        textView.setMaxLines(1);
        textView.setTypeface(typefaceBold);
        textView.setText(R.string.watchlist);
        textView.setTextColor(ColorApp.colorTextHeader);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((LinearLayout) getChildAt(1)).getVisibility() == VISIBLE) {
                    ((LinearLayout) getChildAt(1)).setVisibility(GONE);
                    ((LinearLayout) getChildAt(2)).setVisibility(GONE);
                } else {
                    ((LinearLayout) getChildAt(1)).setVisibility(VISIBLE);
                    ((LinearLayout) getChildAt(2)).setVisibility(VISIBLE);
                }
            }
        });

        ImageView imgAdd = imageviewHeader(ImageApp.iconHomeAdd);
        imgAdd.setId(ID + 2000 + 1);
        imgAdd.setOnClickListener(this);
        ImageView imgEdit = imageviewHeader(ImageApp.iconHomeEdit);
        imgEdit.setId(ID + 2000 + 2);
        imgEdit.setOnClickListener(this);

        ImageView imgDetail = imageviewHeader(ImageApp.iconArrowRight);
        imgDetail.setId(ID + 2000 + 3);
        imgDetail.setOnClickListener(this);

        linearLayout.addView(textView);
        linearLayout.addView(imgAdd);
        linearLayout.addView(imgEdit);
        linearLayout.addView(imgDetail);
        return linearLayout;
    }

    // TODO: HoaDT 7/25/2018 9:17 AM Tiêu đề: Symbol, Last, Change,  Value
    private LinearLayout linearLayoutTitle() {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem * 4 / 5);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.topMargin = 1;
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setPadding(8, 0, 8, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);

        TextView txtSymbol = textViewSymbol(arrayListTitle.get(0));
        txtSymbol.setTextColor(ColorApp.colorText);

        TextView txtLast = textviewLast(arrayListTitle.get(1));
        txtLast.setTextColor(ColorApp.colorText);

        TextView txtChange = textviewChange(isChange ? arrayListTitle.get(2) : arrayListTitle.get(3));
        txtChange.setTextColor(ColorApp.colorText);
        // TODO: HoaDT 7/25/2018 3:29 PM click để thay đổi change <=> change %
        txtChange.setOnClickListener(view -> {
            isChange = !isChange;
            txtChange.setText(isChange ? arrayListTitle.get(2) : arrayListTitle.get(3));
            LinearLayout line = (LinearLayout) this.getChildAt(2);
            for (int i = 0; i < line.getChildCount(); i++) {
                updateView((LinearLayout) line.getChildAt(i), arrayListPrice.get(i * 3), arrayListPrice.get(i * 3 + 1));

            }
        });
        TextView txtValue = textviewValue(arrayListTitle.get(4));

        linearLayout.addView(txtSymbol);
        linearLayout.addView(txtLast);
        linearLayout.addView(txtChange);
        linearLayout.addView(txtValue);
        return linearLayout;
    }

    private LinearLayout linearLayoutContent() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(VERTICAL);
        if (arrayListPrice != null && arrayListPrice.size() > 0) {
            for (int i = 0; i < arrayListCode.size(); i++) {
                final int finalI = i;
                LinearLayout line = new LinearLayout(context);
                LayoutParams paramsLine = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                paramsLine.gravity = Gravity.CENTER;
                paramsLine.setMargins(0, 1, 0, 0);
                line.setLayoutParams(paramsLine);
                line.setOrientation(HORIZONTAL);
                line.setBackgroundColor(ColorApp.colorBackground);

                Double matchPrice = Double.parseDouble(arrayListPrice.get(i * 3).replace(",", ""));
                Double refPrice = Double.parseDouble(arrayListPrice.get(i * 3 + 1));

                Double change;
                if (matchPrice == 0) change = 0d;
                else
                    change = (double) Math.round((matchPrice - refPrice) * 100) / 100;
                Double changePer = (double) Math.round(change / refPrice * 100) / 100;
                TextView txtSymbol = textViewSymbol(arrayListCode.get(i));
                TextView txtLast = textviewLast(matchPrice + "");
                TextView txtValue = textviewValue(arrayListPrice.get(i * 3 + 2));

                TextView txtChange = textviewChange(isChange ? change + "" : changePer + "");

                txtSymbol.setId(ID + i);
                txtLast.setId(ID + 1000 + i * 3);
                txtChange.setId(ID + 1000 + i * 3 + 1);
                txtValue.setId(ID + 1000 + i * 3 + 2);

                if (change > 0) {
                    txtSymbol.setTextColor(ColorApp.colorTextUp);
                    txtLast.setTextColor(ColorApp.colorTextUp);
                    txtChange.setTextColor(ColorApp.colorTextUp);
                } else if (change == 0) {
                    txtSymbol.setTextColor(ColorApp.colorTextRef);
                    txtLast.setTextColor(ColorApp.colorTextRef);
                    txtChange.setTextColor(ColorApp.colorTextRef);
                } else {
                    txtSymbol.setTextColor(ColorApp.colorTextDown);
                    txtLast.setTextColor(ColorApp.colorTextDown);
                    txtChange.setTextColor(ColorApp.colorTextDown);
                }
                line.addView(txtSymbol);
                line.addView(txtLast);
                line.addView(txtChange);
                line.addView(txtValue);
                line.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setFragment(DetailStockFragment.newInstance(arrayListCode.get(finalI)));
                    }
                });
                linearLayout.addView(line);
            }
        }
        return linearLayout;
    }

    private TextView textViewSymbol(String symbol) {
        TextView textView = new TextView(context);
        textView.setHeight(heightItem);
        textView.setWidth(width * 2 / 11);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(symbol);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setTypeface(typeface);
        textView.setPadding(8, 0, 0, 0);

        return textView;
    }

    private TextView textviewLast(String last) {
        TextView textView = new TextView(context);
        textView.setHeight(heightItem);
        textView.setWidth(width * 2 / 11);
        textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView.setText(last);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setTypeface(typeface);

        return textView;
    }

    private TextView textviewChange(String change) {
        TextView textView = new TextView(context);
        textView.setHeight(heightItem);
        textView.setWidth(width * 3 / 11);
        textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView.setText(change);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setTypeface(typeface);
        return textView;
    }

    private TextView textviewValue(String value) {
        TextView textView = new TextView(context);
        textView.setHeight(heightItem);
        textView.setWidth(width * 4 / 11);
        textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView.setText(value);

        textView.setTextColor(ColorApp.colorText);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setTypeface(typeface);
        textView.setPadding(0, 0, 8, 0);

        return textView;
    }

    private ImageView imageviewHeader(Drawable drawable) {
        LayoutParams params = new LayoutParams(width / 16, width / 16);
        params.setMargins(8, 0, 8, 0);
        params.gravity = Gravity.CENTER;
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(params);
        imageView.setPadding(4, 4, 4, 4);
//        imageView.measure(width / 6, width / 6);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(drawable);
        return imageView;
    }

    private void updateView(LinearLayout view, String matchPrice, String refPrice) {

        TextView txtSymbol = (TextView) view.getChildAt(0);
        TextView txtLast = (TextView) view.getChildAt(1);
        TextView txtChange = (TextView) view.getChildAt(2);

        Double match = Double.parseDouble(matchPrice);
        Double ref = Double.parseDouble(refPrice);
        Double change = 0d;
        if (match != 0)
            change = (double) Math.round((match - ref) * 100) / 100;
        final double change1 = change;
        Double changePer = (double) Math.round(((match - ref) / ref * 100) * 100) / 100;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtChange.setText(isChange ? change1 + "" : changePer + "%");
                if (change1 > 0) {
                    txtSymbol.setTextColor(ColorApp.colorTextUp);
                    txtLast.setTextColor(ColorApp.colorTextUp);
                    txtChange.setTextColor(ColorApp.colorTextUp);
                } else if (change1 == 0) {
                    txtSymbol.setTextColor(ColorApp.colorTextRef);
                    txtLast.setTextColor(ColorApp.colorTextRef);
                    txtChange.setTextColor(ColorApp.colorTextRef);
                } else {
                    txtSymbol.setTextColor(ColorApp.colorTextDown);
                    txtLast.setTextColor(ColorApp.colorTextDown);
                    txtChange.setTextColor(ColorApp.colorTextDown);
                }
            }
        });
    }

    ////id là số thứ tự trong arrayListPrice
    public void changeData(int id, String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (arrayListPrice != null && arrayListPrice.size() > 0 && arrayListPrice.get(id).equals(s)) {

                } else {

                    int position = (id) % 3;
                    TextView textView = findViewById(ID + 1000 + id);
                    switch (position) {
                        case 0: {//last

                            Double matchPrice = Double.parseDouble(s.replace(",", ""));
                            Double refPrice = Double.parseDouble(arrayListPrice.get(id + 1).replace(",", ""));
                            Double change = 0d;
                            if (matchPrice != 0)
                                change = (double) Math.round((matchPrice / 100 - refPrice) * 100) / 100;

                            final double change1 = change;
                            Double changePer = (double) Math.round(((matchPrice / 100 - refPrice) / refPrice * 100) * 100) / 100;
                            TextView txtChange = findViewById(ID + 1000 + id + 1);
                            TextView txtSymbol = findViewById(ID + id / 3);
                            arrayListPrice.set(id, (double) Math.round(matchPrice / 100) + "");
                            if (change > 0) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText((double) (matchPrice / 100) + "");
                                        textView.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        textView.setTextColor(ColorApp.colorTextUp);
                                        txtChange.setTextColor(ColorApp.colorTextUp);
                                        txtSymbol.setTextColor(ColorApp.colorTextUp);
                                        txtChange.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        txtChange.setText(isChange ? change1 + "" : changePer + "%");
                                    }
                                });
                            } else if (change == 0) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText((double) (matchPrice / 100) + "");
                                        textView.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        textView.setTextColor(ColorApp.colorTextRef);
                                        txtChange.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        txtChange.setTextColor(ColorApp.colorTextRef);
                                        txtChange.setText(isChange ? change1 + "" : changePer + "%");
                                        txtSymbol.setTextColor(ColorApp.colorTextRef);
                                    }
                                });
                            } else {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText((double) (matchPrice / 100) + "");
                                        textView.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        textView.setTextColor(ColorApp.colorTextDown);
                                        txtChange.setBackgroundColor(ColorApp.colorTextBackgroundChange);
                                        txtChange.setTextColor(ColorApp.colorTextDown);
                                        txtChange.setText(isChange ? change1 + "" : changePer + "%");
                                        txtSymbol.setTextColor(ColorApp.colorTextDown);
                                    }
                                });
                            }
                            Thread.currentThread();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtChange.setBackgroundColor(ColorApp.TRANSPARENT);
                                    textView.setBackgroundColor(ColorApp.TRANSPARENT);
                                }
                            });
                            break;
                        }
                        case 2://value
                            DecimalFormat format = new DecimalFormat("###,###.##");
                            Double value = Double.parseDouble(s) * 10;
                            arrayListPrice.set(id, format.format(value));
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(format.format(value).replace(".", ","));
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
                            break;
                    }
                }
            }
        }).start();

    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case ID + 2000 + 1:
                setFragment(WatchlistSearchFragment.newInstance());
                break;
            case ID + 2000 + 2:

                break;
            case ID + 2000 + 3:
                setFragment(WatchlistDetailFragment.newInstance());
                break;
        }
    }

    private void setFragment(Fragment fragment) {
        fragmentTransaction.replace(R.id.linear_layout, fragment);
        fragmentTransaction.commit();
        ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
    }

}