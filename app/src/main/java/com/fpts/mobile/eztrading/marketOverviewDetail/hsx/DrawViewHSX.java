package com.fpts.mobile.eztrading.marketOverviewDetail.hsx;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
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
import com.fpts.mobile.eztrading.common.ValueApp;

import java.util.ArrayList;

public class DrawViewHSX extends LinearLayout {
    private final int ID = 4000000;
    private Context context;
    private Activity activity;
    private Typeface typeface;
    private Typeface typefaceBold;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int heightItem = height / 15;
    private int widthItem = width / 4;

    private ArrayList<String> arrayListCode;
    private ArrayList<String> arrayListPrice;//matchPrice,change,totalQuantity
    private ArrayList<String> arrayListTitle;
    private boolean isChange = true;
    private boolean isValue = true;

    // TODO: HoaDT 7/24/2018 9:58 AM isChange = true: show change, = false: show Change percent
    public DrawViewHSX(Activity activity, Context context, ArrayList<String> arrayListCode,
                       ArrayList<String> arrayListPrice) {
        super(context);
        this.context = context;
        this.activity = activity;
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
        arrayListTitle.add(getContext().getString(R.string.home_value_bill) + "◥");
        arrayListTitle.add(getContext().getString(R.string.home_qty) + "◥");

        this.setOrientation(VERTICAL);
        this.addView(linearLayoutTitle());
        this.addView(linearLayoutContent());
    }

    // TODO: HoaDT 7/25/2018 9:17 AM Tiêu đề: Symbol, Last, Change,  Value
    private LinearLayout linearLayoutTitle() {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem * 4 / 5);
        layoutParams.gravity = Gravity.CENTER;
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
            updateChangeView();
        });

        TextView txtValue = textviewValue(isValue ? arrayListTitle.get(4) : arrayListTitle.get(5));
        txtValue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isValue = !isValue;
                txtValue.setText(isValue ? arrayListTitle.get(4) : arrayListTitle.get(5));
                updateValueView();
            }
        });
        linearLayout.addView(txtSymbol);
        linearLayout.addView(txtLast);
        linearLayout.addView(txtChange);
        linearLayout.addView(txtValue);
        linearLayout.addView(new ImageView(getContext()));
        return linearLayout;
    }

    private LinearLayout linearLayoutContent() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(VERTICAL);

        for (int i = 0; i < arrayListCode.size(); i++) {
            int finalI = i;
            LinearLayout line = new LinearLayout(context);
            LayoutParams paramsLine = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsLine.gravity = Gravity.CENTER;
            paramsLine.setMargins(0, 1, 0, 0);
            line.setLayoutParams(paramsLine);
            line.setBackgroundColor(ColorApp.colorBackground);
            line.setOrientation(HORIZONTAL);
            //symbol
            TextView txtSymbol = textViewSymbol(arrayListCode.get(i));
            TextView txtLast = textviewLast(arrayListPrice.get(i * 5));
            TextView txtChange = textviewChange(isChange ? arrayListPrice.get(i * 5 + 1) : arrayListPrice.get(i * 5 + 2));
            TextView txtValue = textviewValue(isValue ? arrayListPrice.get(i * 5 + 3) : arrayListPrice.get(i * 5 + 4));
            ImageView imageView = imageviewStar(arrayListCode.get(i));
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataMarketHSX.isCodeSaved(context, arrayListCode.get(finalI))) {
                        DataMarketHSX.deleteCode(context, arrayListCode.get(finalI));
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageDrawable(ImageApp.iconMarketStar);
                            }
                        });
                    } else {
                        DataMarketHSX.addCode(context, arrayListCode.get(finalI));
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageDrawable(ImageApp.iconMarketStarMarked);
                            }
                        });
                    }
                }
            });
            txtSymbol.setId(ID + i);
            txtLast.setId(ID + 1000 + i * 5);
            txtChange.setId(ID + 1000 + i * 5 + 1);
            txtValue.setId(ID + 1000 + i * 5 + 2);

            Double change = Double.parseDouble(arrayListPrice.get(i * 5 + 1)
                    .replace(",", ""));
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
            line.addView(imageView);

            linearLayout.addView(line);
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                }
            });
        }

        return linearLayout;
    }

    private TextView textViewSymbol(String symbol) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 0, 0);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setHeight(heightItem);
        textView.setWidth(width * 3 / 12);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText(symbol);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setSingleLine();
        textView.setTypeface(typeface);

        return textView;
    }

    private TextView textviewLast(String last) {
        TextView textView = new TextView(context);
        textView.setHeight(heightItem);
        textView.setWidth(width * 2 / 12);
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
        textView.setWidth(width * 3 / 12);
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
        textView.setWidth(width * 3 / 12);
        textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        textView.setText(value);
        textView.setTextColor(ColorApp.colorText);
        textView.setTextSize(pxToDp(heightItem / 3));
        textView.setMaxLines(1);
        textView.setTypeface(typeface);

        return textView;
    }

    private ImageView imageviewStar(String symbol) {
        ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(width * 1 / 12, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(0, 0, 8, 0);
        imageView.setLayoutParams(params);

        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (DataMarketHSX.isCodeSaved(getContext(), symbol)) {
            imageView.setImageDrawable(ImageApp.iconMarketStarMarked);
        } else {
            imageView.setImageDrawable(ImageApp.iconMarketStar);
        }
        return imageView;
    }

    private void updateChangeView() {
        //0 - title
        //1 - content
        LinearLayout groupLine = (LinearLayout) this.getChildAt(1);
        for (int i = 0; i < groupLine.getChildCount(); i++) {
            TextView textView = (TextView) ((LinearLayout) groupLine.getChildAt(i)).getChildAt(2);
            textView.setText(isChange ? arrayListPrice.get(i * 5 + 1) :
                    arrayListPrice.get(i * 5 + 2));
        }
    }

    private void updateValueView() {
        LinearLayout groupLine = (LinearLayout) this.getChildAt(1);
        for (int i = 0; i < groupLine.getChildCount(); i++) {
            TextView textView = (TextView) ((LinearLayout) groupLine.getChildAt(i)).getChildAt(3);
            textView.setText(isValue ? arrayListPrice.get(i * 5 + 3) :
                    arrayListPrice.get(i * 5 + 4));
        }
    }

    ////id là số thứ tự trong arrayListPrice
    public void changeData(int id, String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (arrayListPrice.get(id).equals(s)) {

                } else {
                    arrayListPrice.set(id, s);
                    int position = id % 5;
                    switch (position) {
                        case 0: {//last
                            updateViewLast(id, s);
                            break;
                        }
                        case 1: {//change
                            updateViewChange(id, s);
                            break;
                        }
                        case 2: {//change percent
                            updateViewChangePer(id, s);
                            break;
                        }
                        case 3: {//value
                            updateViewValue(id, s);
                            break;
                        }
                        case 4: {//qty
                            updateViewQty(id, s);
                            break;
                        }
                    }

                }
            }
        }).start();
    }

    private void updateViewLast(int id, String s) {
        TextView textView = findViewById(ID + 1000 + id);
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
        activity.runOnUiThread(() -> textView.setBackgroundColor(ColorApp.TRANSPARENT));
    }

    private void updateViewChange(int id, String s) {
        TextView textView = findViewById(ID + 1000 + id);
        Double change = Double.parseDouble(s.replace(",", ""));
        activity.runOnUiThread(() -> {
            if (change > 0) {
                textView.setTextColor(ColorApp.colorTextUp);
                ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextUp);
            } else if (change == 0) {
                textView.setTextColor(ColorApp.colorTextRef);
                ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextUp);
            } else {
                textView.setTextColor(ColorApp.colorTextDown);
                ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextDown);
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

    private void updateViewChangePer(int id, String s) {
        TextView textView = findViewById(ID + 1000 + id - 1);
        Double change = Double.parseDouble(arrayListPrice.get(id - 1));
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(arrayListPrice.get(id - 1));
                if (change > 0) {
                    textView.setTextColor(ColorApp.colorTextUp);
                    ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextUp);
                } else if (change == 0) {
                    textView.setTextColor(ColorApp.colorTextRef);
                    ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextUp);
                } else {
                    textView.setTextColor(ColorApp.colorTextDown);
                    ((TextView) findViewById(ID + 1000 + id - 1)).setTextColor(ColorApp.colorTextDown);
                }
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

    private void updateViewValue(int id, String s) {
        Double val = Double.parseDouble(s);
        arrayListPrice.set(id, (double) Math.round(val / 1000000000 * 100) / 100 + "tỷ");
        if (isValue) {
            TextView textView = findViewById(ID + 1000 + id);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText((double) Math.round(val / 1000000000 * 100) / 100 + "tỷ");
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

    private void updateViewQty(int id, String s) {
        if (!isValue) {
            TextView textView = findViewById(ID + 1000 + id - 1);

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
            activity.runOnUiThread(() -> textView.setBackgroundColor(ColorApp.TRANSPARENT));
        }
    }

    public ArrayList<String> getArrayListPrice() {
        return arrayListPrice;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
