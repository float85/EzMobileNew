package com.fpts.mobile.eztrading.marketOverview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.marketDetail.DetailsMarketFragment;
import com.fpts.mobile.eztrading.marketOverviewDetail.MarketOverviewDetailFragment;

import java.util.ArrayList;

public class DrawViewMarket extends LinearLayout implements View.OnClickListener{

    private Context context;
    private Activity activity;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    private int height = width;
    private int heightItem = height / 4;
    private ArrayList<String> arrayList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private String TAG = getClass().getName();

    public DrawViewMarket(Context context, ArrayList<String> arrayList) {
        super(context);
        this.fragmentTransaction = ValueApp.fragmentTransaction;
        this.context = ValueApp.contextHome;
        this.activity = ValueApp.activityHome;
        this.arrayList = arrayList;

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);

        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);

        for (int i = 0; i < arrayList.size() / 5; i++) {
            int id = i * 5;
            linearLayout.addView(linearLayout(id));
        }

        LayoutParams layoutParams1 = new LayoutParams(width / 3, height * 2 / 5);
        layoutParams1.topMargin = height * 3 / 10;
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams1);
        imageView.setBackgroundColor(ColorApp.colorBackground);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageDrawable(ImageApp.iconArrowRight);
        imageView.setId(ID.setID("intent" + TAG));
        imageView.setOnClickListener(this);
        linearLayout.addView(imageView);

        horizontalScrollView.addView(linearLayout);
        this.setBackgroundColor(ColorApp.colorBackground);
        this.addView(horizontalScrollView);
    }

    private LinearLayout linearLayout(int id) {
        LayoutParams layoutParams = new LayoutParams(width, height);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.addView(textView1(id));
        linearLayout.addView(textView2(id));
        linearLayout.addView(textView34(id));
        linearLayout.addView(textView5(id));
        linearLayout.setId(ID.setID("cuc"+id+TAG));
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String sIndex = "";

                if (arrayList.get(id).equalsIgnoreCase("hnxindex")) {
                    sIndex = "HNX";
                } else if (arrayList.get(id).equalsIgnoreCase("vni")) {
                    sIndex = "VNI";
                } else if (arrayList.get(id).equalsIgnoreCase("upcom")) {
                    sIndex = "UPCOM";
                } else if (arrayList.get(id).equalsIgnoreCase("vn30")) {
                    sIndex = "VN30";
                } else if (arrayList.get(id).equalsIgnoreCase("hnx30")) {
                    sIndex = "HNX30";
                } else {
                    sIndex = "VNI";
                }

                fragmentTransaction.replace(R.id.linear_layout, DetailsMarketFragment.newInstance(sIndex));
                fragmentTransaction.commit();
                ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
                ValueApp.intent = sIndex;
            }
        });

        return linearLayout;
    }

    private TextView textView1(int id) {
        TextView textView = new TextView(context);
        textView.setText(arrayList.get(id));
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        Typeface type = ResourcesCompat.getFont(context, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorText);
        textView.setTextSize(pxToDp(heightItem * 3 / 5));
        textView.setMaxLines(1);
        textView.setId(ID.setID(""+id+TAG));
        return textView;
    }

    private TextView textView2(int id) {
        TextView textView = new TextView(context);
        textView.setText(arrayList.get(id + 1));
        textView.setGravity(Gravity.CENTER);
        Typeface type = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        textView.setTypeface(type);
        textView.setMaxLines(1);
        if (Double.parseDouble(arrayList.get(id + 2).replace(",", "")) > 0) {
            textView.setTextColor(ColorApp.colorTextUp);
        } else {
            textView.setTextColor(ColorApp.colorTextDown);
        }
        textView.setTextSize(pxToDp(heightItem * 4 / 5));
        textView.setId(ID.setID(""+(id+1)+TAG));
        return textView;
    }

    private LinearLayout textView34(int id) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView3 = new TextView(context);
        textView3.setText(arrayList.get(id + 2) + " ");
        textView3.setGravity(Gravity.RIGHT);
        Typeface type = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        textView3.setTypeface(type);
        textView3.setMaxLines(1);
        textView3.setTextSize(pxToDp(heightItem * 3 / 5));
        textView3.setId(ID.setID(""+(id+2)+TAG));

        TextView textView4 = new TextView(context);
        textView4.setText(" " + arrayList.get(id + 3) + "%");
        textView4.setGravity(Gravity.LEFT);
        textView4.setTypeface(type);
        textView4.setMaxLines(1);
        textView4.setTextSize(pxToDp(heightItem * 3 / 5));
        textView4.setId(ID.setID(""+(id+3)+TAG));

        if (Double.parseDouble(arrayList.get(id + 2).replace(",", "")) > 0) {
            textView3.setTextColor(ColorApp.colorTextUp);
            textView4.setTextColor(ColorApp.colorTextUp);
        } else {
            textView4.setTextColor(ColorApp.colorTextDown);
            textView3.setTextColor(ColorApp.colorTextDown);
        }

        linearLayout.addView(textView3);
        linearLayout.addView(textView4);

        return linearLayout;
    }

    private TextView textView5(int id) {
        TextView textView = new TextView(context);
        textView.setText(arrayList.get(id + 4));
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        Typeface type = ResourcesCompat.getFont(context, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorText);
        textView.setMaxLines(1);
        textView.setTextSize(pxToDp((heightItem * 1 / 2)));
        textView.setId(ID.setID(""+(id+4)+TAG));
        return textView;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void changeData(int vt, String s) {
        changeDataPrivate(vt, s.replace(",",""));
    }

    private void changeDataPrivate(final int id, final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (s.equals(arrayList.get(id))) {

                } else {
                    arrayList.set(id, s);
                    if (String.valueOf(arrayList.get(id / 5).charAt(0)).equalsIgnoreCase("v")) {
                        int vt = (id + 1) % 5;
                        TextView textView = (TextView) findViewById(ID.getID(""+id+TAG));
                        if (vt != 3) {
                            if (vt == 4) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(s + "%");
                                    }
                                });
                            } else {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(s);
                                    }
                                });
                            }
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(s + "  ");
                                }
                            });
                            changeColor(id, s);
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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
                    } else {
                        int vt = (id + 1) % 5;
                        TextView textView = (TextView) findViewById(ID.getID(""+id+TAG));
                        if (vt == 4) {
                            Double k = (double) Math.round(Double.parseDouble(s) * 100) / 100;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(k + "%");
                                }
                            });
                        } else if(vt==2){
                            Double k = (double) Math.round(Double.parseDouble(s) * 100) / 100;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(k + "");
                                }
                            });
                        } else if (vt == 3) {
                            Double k = (double) Math.round(Double.parseDouble(s) * 100) / 100;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(k + "  ");
                                }
                            });
                            changeColor(id, s);
                        } else{
                            Double k = (double) Math.round(Double.parseDouble(s)/10000000) / 100;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(k + " tỷ");
                                }
                            });
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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

    private void changeColor(int id, String s) {
        TextView textView0 = (TextView) findViewById(ID.getID(""+(id-1)+TAG));
        TextView textView = (TextView) findViewById(ID.getID(""+id+TAG));
        TextView textView1 = (TextView) findViewById(ID.getID(""+(id+1)+TAG));
        if (Double.parseDouble(s.replace(",", "")) > 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView0.setTextColor(ColorApp.colorTextUp);
                    textView.setTextColor(ColorApp.colorTextUp);
                    textView1.setTextColor(ColorApp.colorTextUp);
                }
            });
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView0.setTextColor(ColorApp.colorTextDown);
                    textView.setTextColor(ColorApp.colorTextDown);
                    textView1.setTextColor(ColorApp.colorTextDown);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == ID.getID("intent" + TAG)){
            fragmentTransaction.replace(R.id.linear_layout, MarketOverviewDetailFragment.newInstance());
            fragmentTransaction.commit();
            ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
        }
    }
}

