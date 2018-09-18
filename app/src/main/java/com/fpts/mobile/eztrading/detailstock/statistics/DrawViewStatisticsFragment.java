package com.fpts.mobile.eztrading.detailstock.statistics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.detailstock.trading.Quote;
import com.fpts.mobile.eztrading.detailstock.trading.StockInfo;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class DrawViewStatisticsFragment extends ScrollView implements View.OnClickListener {
    private final String TAG = getClass().getName();
    private Context context;
    private FragmentTransaction fragmentTransaction;
    private String code;

    private Typeface typeface;
    private Typeface typefaceBold;
    private int mHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int mHeightItem = mHeight / 15;
    private int mTextSizeMax = mHeightItem / 2;
    private int mTextSize = mTextSizeMax * 3 / 5;

    String[] sHead = {"Ceiling", "Floor", "Basic", "Open",
            getContext().getString(R.string.watchlist_detail_priceClose), getContext().getString(R.string.watchlist_detail_priceHighest), getContext().getString(R.string.watchlist_detail_priceLowest), getContext().getString(R.string.watchlist_detail_change), getContext().getString(R.string.watchlist_detail_changePer), getContext().getString(R.string.watchlist_detail_AP),
            getContext().getString(R.string.watchlist_detail_NTQ), getContext().getString(R.string.watchlist_detail_NTV),
            getContext().getString(R.string.watchlist_detail_PTQ),
            getContext().getString(R.string.watchlist_detail_PTV),
            getContext().getString(R.string.watchlist_detail_TTQ),
            getContext().getString(R.string.watchlist_detail_TTV)};

    String[] sHead3 = {getContext().getString(R.string.watchlist_detail_bid_count),
            getContext().getString(R.string.watchlist_detail_total_bid_qtty), getContext().getString(R.string.watchlist_detail_offer_count),
            getContext().getString(R.string.watchlist_detail_total_offer_qtty),
            getContext().getString(R.string.watchlist_detail_bid_offer),
            getContext().getString(R.string.watchlist_detail_ceiling_price),
            getContext().getString(R.string.watchlist_detail_floor_price),
            getContext().getString(R.string.watchlist_detail_basic_price),
            getContext().getString(R.string.watchlist_detail_best_bid_price),
            getContext().getString(R.string.watchlist_detail_best_bid_qtty),
            getContext().getString(R.string.watchlist_detail_best_offer_price),
            getContext().getString(R.string.watchlist_detail_best_offer_qtty)};

    String[] sHead4 = {getContext().getString(R.string.watchlist_detail_current_vol), getContext().getString(R.string.watchlist_detail_current_per),
            getContext().getString(R.string.watchlist_detail_current_rem), getContext().getString(R.string.watchlist_detail_buy_vol),
            getContext().getString(R.string.watchlist_detail_buy_per), getContext().getString(R.string.watchlist_detail_buy_val),
            getContext().getString(R.string.watchlist_detail_buy_val_per), getContext().getString(R.string.watchlist_detail_sell_vol),
            getContext().getString(R.string.watchlist_detail_sell_per), getContext().getString(R.string.watchlist_detail_sell_val),
            getContext().getString(R.string.watchlist_detail_sell_val_per)};

    private int countLinearAll = 0;
    private int pageIndex2 = 1;
    private int pageIndex3 = 1;
    private int pageIndex4 = 1;

    private int countLinear2 = -1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout2All;

    private int countLinear3 = -1;
    LinearLayout linearLayout3;
    LinearLayout linearLayout3All;

    private int countLinear4 = -1;
    LinearLayout linearLayout4;
    LinearLayout linearLayout4All;

    private Quote quotes;
    private StockInfo stockInfo = new StockInfo();
    ArrayList<DataStatisticsPrice> listStatisticsPrice = new ArrayList<>();
    ArrayList<DataPlacingOrder> listPlacingOrder = new ArrayList<>();
    ArrayList<Data4> list4 = new ArrayList<>();

    public DrawViewStatisticsFragment(Context context, String code, StockInfo stockInfo, Quote quotes,
                                      ArrayList<DataStatisticsPrice> listStatisticsPrice,
                                      ArrayList<DataPlacingOrder> listPlacingOrder, ArrayList<Data4> list4) {
        super(context);
        this.context = context;
        this.fragmentTransaction = fragmentTransaction;
        this.code = code;
        this.quotes = quotes;
        this.stockInfo = stockInfo;
        this.listStatisticsPrice = listStatisticsPrice;
        this.listPlacingOrder = listPlacingOrder;
        this.list4 = list4;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);
        init();
    }

    private void init() {
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.addView(linearLayout1());
        linearLayout.addView(linearLayout2(-1));
        linearLayout.addView(linearLayout3(-1));
        linearLayout.addView(linearLayout4(-1));

        this.addView(linearLayout);
    }

    private LinearLayout linearLayout1() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);

        ImageView imageView = new ImageView(context);
        imageView.setId(ID.setID("imageView" + TAG));
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(mWidth / 9, mWidth / 9);
        paramsImg.setMargins(8, 8, 8, 8);
//        paramsImg.gravity = Gravity.CENTER;
        imageView.setLayoutParams(paramsImg);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(12, 12, 12, 12);

        LinearLayout linearLayoutRight = new LinearLayout(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.setMargins(mWidth / 8, 0, 0, 0);
        linearLayoutRight.setLayoutParams(params1);
        linearLayoutRight.setLayoutParams(params);
        linearLayoutRight.setOrientation(VERTICAL);
        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setLayoutParams(params);
        linearLayout1.setOrientation(HORIZONTAL);
        TextView txtMatchPrice = mTextView(quotes.getMatchPrice());
        txtMatchPrice.setId(ID.setID("txtMatchPrice" + TAG));
        txtMatchPrice.setTextSize(pxToDp(mTextSizeMax));
        txtMatchPrice.setTypeface(typefaceBold);
        TextView txtChange = mTextView(quotes.getChangePrice());
        txtChange.setId(ID.setID("txtChange" + TAG));
        txtChange.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        txtChange.setPadding(12, 0, 0, 0);
        //change per
        Double change = Double.parseDouble(quotes.getChangePrice() == null || quotes.getChangePrice().equalsIgnoreCase("") ?
                "0" : quotes.getChangePrice());
        Double matchPrice = Double.parseDouble(quotes.getMatchPrice() == null || quotes.getMatchPrice().equalsIgnoreCase("") ?
                "0" : quotes.getMatchPrice());
        Double refPrice = Double.parseDouble(quotes.getRefPrice() == null || quotes.getRefPrice().equalsIgnoreCase("") ?
                "0" : quotes.getRefPrice());
        Double changePer = 0d;
        if (change == 0 || refPrice == 0) {
            changePer = 0d;
        } else {
            changePer = (double) Math.round((matchPrice - refPrice) / refPrice * 100) / 100;
        }
        TextView txtChangePer = mTextView(changePer + "");
        txtChangePer.setId(ID.setID("txtChangePer" + TAG));
        txtChangePer.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        txtChangePer.setPadding(12, 0, 0, 0);

        linearLayout1.addView(txtMatchPrice);
        linearLayout1.addView(txtChange);
        linearLayout1.addView(txtChangePer);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(params);
        linearLayout2.setOrientation(HORIZONTAL);
        TextView textViewKL = mTextView(getContext().getString(R.string.watchlist_detail_GDKL));
        textViewKL.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        TextView textViewKL1 = mTextView(quotes.getMatchQtty());
        textViewKL1.setTypeface(typefaceBold);
        textViewKL1.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        textViewKL1.setId(ID.setID("textViewKL1" + TAG));
        textViewKL1.setPadding(12, 0, 12, 0);
        TextView textViewTT = mTextView(getContext().getString(R.string.watchlist_detail_GDTT));
        textViewTT.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        TextView textViewTT1 = mTextView(quotes.getTotalQtty());
        textViewTT1.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        textViewTT1.setId(ID.setID("textViewTT1" + TAG));
        textViewTT1.setTypeface(typefaceBold);
        textViewTT1.setPadding(12, 0, 0, 0);
        linearLayout2.addView(textViewKL);
        linearLayout2.addView(textViewKL1);
        linearLayout2.addView(textViewTT);
        linearLayout2.addView(textViewTT1);

        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(params);
        linearLayout3.setOrientation(HORIZONTAL);
        LinearLayout linearLayout31 = new LinearLayout(context);
        linearLayout31.setLayoutParams(new LayoutParams(mWidth * 2 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout31.setOrientation(VERTICAL);
        linearLayout31.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout31.addView(mTextView(getContext().getString(R.string.watchlist_detail_ceiling)));
        linearLayout31.addView(mTextView(getContext().getString(R.string.watchlist_detail_tc)));
        linearLayout31.addView(mTextView(getContext().getString(R.string.watchlist_detail_floor)));
        LinearLayout linearLayout32 = new LinearLayout(context);
        linearLayout32.setLayoutParams(new LayoutParams(mWidth * 2 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout32.setOrientation(VERTICAL);
        linearLayout32.setGravity(Gravity.CENTER_VERTICAL);
        TextView txtCeiling = mTextView(quotes.getCeiling());
        txtCeiling.setId(ID.setID("txtCeiling" + TAG));
        txtCeiling.setTextColor(ColorApp.colorTextCeiling);
        TextView txtTC = mTextView(quotes.getRefPrice());
        txtTC.setId(ID.setID("txtTC" + TAG));
        txtTC.setTextColor(ColorApp.colorTextRef);
        TextView txtFloor = mTextView(quotes.getFloor());
        txtFloor.setId(ID.setID("txtFloor" + TAG));
        txtFloor.setTextColor(ColorApp.colorTextFloor);
        linearLayout32.addView(txtCeiling);
        linearLayout32.addView(txtTC);
        linearLayout32.addView(txtFloor);

        LinearLayout linearLayout33 = new LinearLayout(context);
        linearLayout33.setLayoutParams(new LayoutParams(mWidth * 2 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout33.setOrientation(VERTICAL);
        linearLayout33.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout33.addView(mTextView(getContext().getString(R.string.watchlist_detail_open)));
        linearLayout33.addView(mTextView(getContext().getString(R.string.watchlist_detail_heighest)));
        linearLayout33.addView(mTextView(getContext().getString(R.string.watchlist_detail_lowest)));
        LinearLayout linearLayout34 = new LinearLayout(context);
        linearLayout34.setLayoutParams(new LayoutParams(mWidth * 2 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout34.setOrientation(VERTICAL);
        linearLayout34.setGravity(Gravity.CENTER_VERTICAL);
        TextView txtOpen = mTextView(quotes.getOpenPrice());
        txtOpen.setId(ID.setID("txtOpen" + TAG));
        TextView txtHighest = mTextView(quotes.getHighestPrice());
        txtHighest.setId(ID.setID("txtHighest" + TAG));
        TextView txtLowest = mTextView(quotes.getLowestPrice());
        txtLowest.setId(ID.setID("txtLowest" + TAG));
        linearLayout34.addView(txtOpen);
        linearLayout34.addView(txtHighest);
        linearLayout34.addView(txtLowest);

        linearLayout3.addView(linearLayout31);
        linearLayout3.addView(linearLayout32);
        linearLayout3.addView(linearLayout33);
        linearLayout3.addView(linearLayout34);

        LinearLayout linearLayout4 = new LinearLayout(context);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params4.topMargin = 4;
        linearLayout4.setLayoutParams(params4);
        linearLayout4.setOrientation(HORIZONTAL);
        linearLayout4.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout linearLayout41 = new LinearLayout(context);
        linearLayout41.setOrientation(HORIZONTAL);
        linearLayout41.setLayoutParams(new LinearLayout.LayoutParams(mWidth * 4 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout linearLayout42 = new LinearLayout(context);
        linearLayout42.setOrientation(HORIZONTAL);
        linearLayout42.setLayoutParams(new LinearLayout.LayoutParams(mWidth * 4 / 9, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView txtForeignBuy = mTextView(quotes.getForeignBuyQtty());
        txtForeignBuy.setPadding(12, 0, 0, 0);
        txtForeignBuy.setId(ID.setID("txtForeignBuy" + TAG));
        TextView txtForeignSell = mTextView(quotes.getForeignSellQtty());
        txtForeignSell.setId(ID.setID("txtForeignSell" + TAG));
        txtForeignSell.setPadding(12, 0, 0, 0);

        linearLayout41.addView(mTextView(getContext().getString(R.string.watchlist_detail_nnmua)));
        linearLayout41.addView(txtForeignBuy);
        linearLayout42.addView(mTextView(getContext().getString(R.string.watchlist_detail_nnban)));
        linearLayout42.addView(txtForeignSell);
        linearLayout4.addView(linearLayout41);
        linearLayout4.addView(linearLayout42);

        setPriceColor(imageView, txtMatchPrice, txtChange, txtChangePer, txtOpen, txtHighest, txtLowest);

        linearLayoutRight.addView(linearLayout1);
        linearLayoutRight.addView(linearLayout2);
        linearLayoutRight.addView(linearLayout3);
        linearLayoutRight.addView(linearLayout4);

        linearLayout.addView(imageView);
        linearLayout.addView(linearLayoutRight);
        return linearLayout;
    }

    private LinearLayout linearLayout2(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2All = new LinearLayout(context);
        linearLayout2All.setLayoutParams(params);
        linearLayout2All.setOrientation(VERTICAL);

        linearLayout2All.addView(linearLayout2Detail(count));
        return linearLayout2All;
    }

    private LinearLayout linearLayout2Detail(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(params);
        linearLayout2.setOrientation(VERTICAL);

        linearLayout2.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_thong_ke_gia), count));

        LinearLayout linearLayoutAll = new LinearLayout(context);
        linearLayoutAll.setLayoutParams(params);
        linearLayoutAll.setOrientation(HORIZONTAL);
        linearLayoutAll.setId(ID.setID("linearLayout2" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG));

        LinearLayout.LayoutParams paramsA = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutA = new LinearLayout(context);
        linearLayoutA.setLayoutParams(paramsA);
        linearLayoutA.setOrientation(VERTICAL);

        for (int i = 0; i < 6; i++) {
            LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
            TextView textViewA = new TextView(context);
            textViewA.setLayoutParams(paramsText);
            textViewA.setTypeface(typeface);
            textViewA.setTextSize(pxToDp(mTextSize * 7 / 8));

            if (i == 0) {
                textViewA.setText("TranDateUS");
                textViewA.setTypeface(typefaceBold);
            } else {
                textViewA.setText(listStatisticsPrice.get(count + i).getTranDateUS());
            }

            textViewA.setPadding(10, 10, 10, 10);
            textViewA.setTextColor(ColorApp.colorText);
            textViewA.setGravity(Gravity.CENTER_VERTICAL);

            linearLayoutA.addView(mLine());
            linearLayoutA.addView(textViewA);
        }

        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        ScrollView.LayoutParams paramsScroll = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(paramsScroll);

        LinearLayout.LayoutParams paramsB = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayoutB = new LinearLayout(context);
        linearLayoutB.setOrientation(HORIZONTAL);
        linearLayoutB.setBackgroundColor(ColorApp.colorGray);
        linearLayoutB.setLayoutParams(paramsB);

        for (int j = 0; j < sHead.length; j++) {
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 0, 1, 0);
            LinearLayout linearLayout1 = new LinearLayout(context);
            linearLayout1.setOrientation(VERTICAL);
            linearLayout1.setBackgroundColor(ColorApp.colorBackground);
            linearLayout1.setLayoutParams(params1);

            for (int i = 0; i < 6; i++) {
                LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
                TextView textViewB = new TextView(context);
                textViewB.setGravity(Gravity.CENTER);
                textViewB.setLayoutParams(paramsText);
                textViewB.setTextSize(pxToDp(mTextSize * 7 / 8));
                textViewB.setTypeface(typeface);

                if (i == 0) {
                    textViewB.setText(sHead[j]);
                    textViewB.setTypeface(typefaceBold);
                } else {
                    switch (j + 3) {
                        case 3:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getCeiling()));
                            break;
                        case 4:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getFloor()));
                            break;
                        case 5:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getBasic()));
                            break;
                        case 6:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getOpen()));
                            break;
                        case 7:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getClose()));
                            break;
                        case 8:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getClose()));
                            break;
                        case 9:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getLowest()));
                            break;
                        case 10:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getChangePrice()));
                            break;
                        case 11:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getPercentPrice()));
                            break;
                        case 12:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getAP()));
                            break;
                        case 13:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getNTQ()));
                            break;
                        case 14:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getNTV()));
                            break;
                        case 15:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getPTQ()));
                            break;
                        case 16:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getPTV()));
                            break;
                        case 17:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getTTQ()));
                            break;
                        case 18:
                            textViewB.setText(lamtron(listStatisticsPrice.get(count + i).getTTV()));
                            break;
                    }
                }

                textViewB.setPadding(10, 10, 10, 10);
                textViewB.setTextColor(ColorApp.colorText);
                textViewB.setGravity(Gravity.CENTER_VERTICAL);

                linearLayout1.addView(mLine());
                linearLayout1.addView(textViewB);
            }

            linearLayoutB.addView(linearLayout1);
        }

        scrollView.addView(linearLayoutB);

        linearLayoutAll.addView(linearLayoutA);
        linearLayoutAll.addView(mLine1());
        linearLayoutAll.addView(scrollView);

        linearLayout2.addView(linearLayoutAll);
        return linearLayout2;
    }

    private String lamtron(String s) {
        String f = "";

        try {
            int b = Math.round(Float.parseFloat(s) * 100);
            Float a = (float) b / 100;
            f = String.valueOf(a);
            return f;
        } catch (Exception e) {
            return s;
        }
    }

    private LinearLayout linearLayout3(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout3All = new LinearLayout(context);
        linearLayout3All.setLayoutParams(params);
        linearLayout3All.setOrientation(VERTICAL);

        linearLayout3All.addView(linearLayout3Detail(count));
        return linearLayout3All;
    }

    private LinearLayout linearLayout3Detail(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(params);
        linearLayout3.setOrientation(VERTICAL);

        linearLayout3.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh), count));

        LinearLayout linearLayoutAll = new LinearLayout(context);
        linearLayoutAll.setLayoutParams(params);
        linearLayoutAll.setOrientation(HORIZONTAL);
        linearLayoutAll.setId(ID.setID("linearLayout3" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG));

        LinearLayout.LayoutParams paramsA = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutA = new LinearLayout(context);
        linearLayoutA.setLayoutParams(paramsA);
        linearLayoutA.setOrientation(VERTICAL);

        for (int i = 0; i < 6; i++) {
            LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
            TextView textViewA = new TextView(context);
            textViewA.setLayoutParams(paramsText);
            textViewA.setTypeface(typeface);
            textViewA.setTextSize(pxToDp(mTextSize * 7 / 8));

            if (i == 0) {
                textViewA.setText(R.string.watchlist_detail_TranDateUS);
                textViewA.setTypeface(typefaceBold);
            } else {
                textViewA.setText(listPlacingOrder.get(count + i).getTRADINGDATEUS());
            }

            textViewA.setPadding(10, 10, 10, 10);
            textViewA.setTextColor(ColorApp.colorText);
            textViewA.setGravity(Gravity.CENTER_VERTICAL);

            linearLayoutA.addView(mLine());
            linearLayoutA.addView(textViewA);
        }

        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        ScrollView.LayoutParams paramsScroll = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(paramsScroll);

        LinearLayout.LayoutParams paramsB = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayoutB = new LinearLayout(context);
        linearLayoutB.setOrientation(HORIZONTAL);
        linearLayoutB.setBackgroundColor(ColorApp.colorGray);
        linearLayoutB.setLayoutParams(paramsB);

        for (int j = 0; j < sHead3.length; j++) {
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 0, 1, 0);
            LinearLayout linearLayout1 = new LinearLayout(context);
            linearLayout1.setOrientation(VERTICAL);
            linearLayout1.setBackgroundColor(ColorApp.colorBackground);
            linearLayout1.setLayoutParams(params1);

            for (int i = 0; i < 6; i++) {
                LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
                TextView textViewB = new TextView(context);
                textViewB.setGravity(Gravity.CENTER);
                textViewB.setLayoutParams(paramsText);
                textViewB.setTextSize(pxToDp(mTextSize * 7 / 8));
                textViewB.setTypeface(typeface);

                if (i == 0) {
                    textViewB.setText(sHead3[j]);
                    textViewB.setTypeface(typefaceBold);
                } else {
                    switch (j + 3) {
                        case 3:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBIDCOUNT()));
                            break;
                        case 4:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getTOTALBIDQTTY()));
                            break;
                        case 5:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getOFFERCOUNT()));
                            break;
                        case 6:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getTOTALOFFERQTTY()));
                            break;
                        case 7:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBIDOFFER()));
                            break;
                        case 8:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getCEILINGPRICE()));
                            break;
                        case 9:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getFLOORPRICE()));
                            break;
                        case 10:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBASICPRICE()));
                            break;
                        case 11:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBESTBIDPRICE()));
                            break;
                        case 12:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBESTBIDQTTY()));
                            break;
                        case 13:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBESTOFFERPRICE()));
                            break;
                        case 14:
                            textViewB.setText(lamtron(listPlacingOrder.get(count + i).getBESTOFFERQTTY()));
                            break;
                    }
                }

                textViewB.setPadding(10, 10, 10, 10);
                textViewB.setTextColor(ColorApp.colorText);
                textViewB.setGravity(Gravity.CENTER_VERTICAL);

                linearLayout1.addView(mLine());
                linearLayout1.addView(textViewB);
            }

            linearLayoutB.addView(linearLayout1);
        }

        scrollView.addView(linearLayoutB);

        linearLayoutAll.addView(linearLayoutA);
        linearLayoutAll.addView(mLine1());
        linearLayoutAll.addView(scrollView);

        linearLayout3.addView(linearLayoutAll);

        return linearLayout3;
    }

    private LinearLayout linearLayout4(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout4All = new LinearLayout(context);
        linearLayout4All.setLayoutParams(params);
        linearLayout4All.setOrientation(VERTICAL);

        linearLayout4All.addView(linearLayout4Detail(count));
        return linearLayout4All;
    }

    private LinearLayout linearLayout4Detail(int count) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout4 = new LinearLayout(context);
        linearLayout4.setLayoutParams(params);
        linearLayout4.setOrientation(VERTICAL);

        linearLayout4.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai), count));

        LinearLayout linearLayoutAll = new LinearLayout(context);
        linearLayoutAll.setLayoutParams(params);
        linearLayoutAll.setOrientation(HORIZONTAL);
        linearLayoutAll.setId(ID.setID("linearLayout4" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG));

        LinearLayout.LayoutParams paramsA = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutA = new LinearLayout(context);
        linearLayoutA.setLayoutParams(paramsA);
        linearLayoutA.setOrientation(VERTICAL);

        for (int i = 0; i < 6; i++) {
            LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
            TextView textViewA = new TextView(context);
            textViewA.setLayoutParams(paramsText);
            textViewA.setTypeface(typeface);
            textViewA.setTextSize(pxToDp(mTextSize * 7 / 8));

            if (i == 0) {
                textViewA.setText("TranDateUS");
                textViewA.setTypeface(typefaceBold);
            } else {
                textViewA.setText(list4.get(count + i).getDate());
            }

            textViewA.setPadding(10, 10, 10, 10);
            textViewA.setTextColor(ColorApp.colorText);
            textViewA.setGravity(Gravity.CENTER_VERTICAL);

            linearLayoutA.addView(mLine());
            linearLayoutA.addView(textViewA);
        }

        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        ScrollView.LayoutParams paramsScroll = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(paramsScroll);

        LinearLayout.LayoutParams paramsB = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayoutB = new LinearLayout(context);
        linearLayoutB.setOrientation(HORIZONTAL);
        linearLayoutB.setBackgroundColor(ColorApp.colorGray);
        linearLayoutB.setLayoutParams(paramsB);

        for (int j = 0; j < sHead4.length; j++) {
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 0, 1, 0);
            LinearLayout linearLayout1 = new LinearLayout(context);
            linearLayout1.setOrientation(VERTICAL);
            linearLayout1.setBackgroundColor(ColorApp.colorBackground);
            linearLayout1.setLayoutParams(params1);

            for (int i = 0; i < 6; i++) {
                LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mHeightItem * 5 / 8);
                TextView textViewB = new TextView(context);
                textViewB.setGravity(Gravity.CENTER);
                textViewB.setLayoutParams(paramsText);
                textViewB.setTextSize(pxToDp(mTextSize * 7 / 8));
                textViewB.setTypeface(typeface);

                if (i == 0) {
                    textViewB.setText(sHead4[j]);
                    textViewB.setTypeface(typefaceBold);
                } else {
                    switch (j + 3) {
                        case 3:
                            textViewB.setText(lamtron(list4.get(count + i).getCURRENTVOL()));
                            break;
                        case 4:
                            textViewB.setText(lamtron(list4.get(count + i).getCURRENTPER()));
                            break;
                        case 5:
                            textViewB.setText(lamtron(list4.get(count + i).getCURRENTREM()));
                            break;
                        case 6:
                            textViewB.setText(lamtron(list4.get(count + i).getBUYVOL()));
                            break;
                        case 7:
                            textViewB.setText(lamtron(list4.get(count + i).getBUYPER()));
                            break;
                        case 8:
                            textViewB.setText(lamtron(list4.get(count + i).getBUYVAL()));
                            break;
                        case 9:
                            textViewB.setText(lamtron(list4.get(count + i).getBUYVALPER()));
                            break;
                        case 10:
                            textViewB.setText(lamtron(list4.get(count + i).getSELLVOL()));
                            break;
                        case 11:
                            textViewB.setText(lamtron(list4.get(count + i).getSELLPER()));
                            break;
                        case 12:
                            textViewB.setText(lamtron(list4.get(count + i).getSELLVAL()));
                            break;
                        case 13:
                            textViewB.setText(lamtron(list4.get(count + i).getSELLVALPER()));
                            break;
                    }
                }

                textViewB.setPadding(10, 10, 10, 10);
                textViewB.setTextColor(ColorApp.colorText);
                textViewB.setGravity(Gravity.CENTER_VERTICAL);

                linearLayout1.addView(mLine());
                linearLayout1.addView(textViewB);
            }

            linearLayoutB.addView(linearLayout1);
        }

        scrollView.addView(linearLayoutB);

        linearLayoutAll.addView(linearLayoutA);
        linearLayoutAll.addView(mLine1());
        linearLayoutAll.addView(scrollView);

        linearLayout4.addView(linearLayoutAll);
        return linearLayout4;
    }

    private TextView mTextView(String s) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setText(s);
        textView.setTypeface(typeface);
        textView.setTextColor(ColorApp.colorText);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setSingleLine();
        textView.setMaxLines(1);
        return textView;
    }

    private View mLine() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params);
        view.setBackgroundColor(ColorApp.colorGray);
        return view;
    }

    private View mLine1() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setBackgroundColor(ColorApp.colorGray);
        return view;
    }

    private LinearLayout mLinearLayoutHeader(String str, int count) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundHeaderGray);

        TextView textView = new TextView(context);
        textView.setId(ID.setID(str + TAG));
        textView.setOnClickListener(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.weight = 1;
        textView.setLayoutParams(params1);
        textView.setPadding(8, 0, 0, 0);
        textView.setText(str);
        textView.setAllCaps(true);
        textView.setTypeface(typefaceBold);
        textView.setTextColor(ColorApp.colorTextHeader);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        ImageView imageView1 = new ImageView(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(mWidth / 16, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView1.setId(ID.setID("imgDetailLeft" + str + TAG));
        imageView1.setOnClickListener(this);
        imageView1.setImageDrawable(ImageApp.iconArrowLeft);
        imageView1.setLayoutParams(params2);

        if (count == -1) {
            imageView1.setVisibility(GONE);
        }

        imageView1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView1.setPadding(12, 12, 4, 12);

        ImageView imageView = new ImageView(context);
        imageView.setId(ID.setID("imgDetail" + str + TAG));
        imageView.setOnClickListener(this);
        imageView.setImageDrawable(ImageApp.iconArrowRight);
        imageView.setLayoutParams(params2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(12, 12, 4, 12);

        linearLayout.addView(textView);
        linearLayout.addView(imageView1);
        linearLayout.addView(imageView);
        return linearLayout;
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private void setPriceColor(ImageView imageView, TextView txtMatchPrice, TextView txtChange, TextView txtChangePer,
                               TextView txtOpen, TextView txtHighest, TextView txtLowest) {
        Double ceil = Double.parseDouble(quotes.getCeiling() != null && !quotes.getCeiling().trim().equalsIgnoreCase("")
                ? quotes.getCeiling() : "0");
        Double ref = Double.parseDouble(quotes.getRefPrice() != null && !quotes.getRefPrice().trim().equalsIgnoreCase("")
                ? quotes.getRefPrice() : "0");
        Double floor = Double.parseDouble(quotes.getFloor() != null && !quotes.getFloor().trim().equalsIgnoreCase("")
                ? quotes.getFloor() : "0");
        Double open = Double.parseDouble(quotes.getOpenPrice() != null && !quotes.getOpenPrice().trim().equalsIgnoreCase("")
                ? quotes.getOpenPrice() : "0");
        Double highest = Double.parseDouble(quotes.getHighestPrice() != null && !quotes.getHighestPrice().trim().equalsIgnoreCase("")
                ? quotes.getHighestPrice() : "0");
        Double lowest = Double.parseDouble(quotes.getLowestPrice() != null && !quotes.getLowestPrice().trim().equalsIgnoreCase("")
                ? quotes.getLowestPrice() : "0");
        Double change = Double.parseDouble(quotes.getChangePrice() != null && !quotes.getChangePrice().trim().equalsIgnoreCase("")
                ? quotes.getChangePrice() : "0");
        if (change > 0) {
            imageView.setImageDrawable(ImageApp.iconMarketDetailUp);
            txtChange.setTextColor(ColorApp.colorTextUp);
            txtChangePer.setTextColor(ColorApp.colorTextUp);
            txtMatchPrice.setTextColor(ColorApp.colorTextUp);
        } else if (change == 0) {
            imageView.setImageDrawable(ImageApp.iconMarketDetailNoChange);
            txtChange.setTextColor(ColorApp.colorTextRef);
            txtChangePer.setTextColor(ColorApp.colorTextRef);
            txtMatchPrice.setTextColor(ColorApp.colorTextRef);
        } else {
            imageView.setImageDrawable(ImageApp.iconMarketDetailDown);
            txtChange.setTextColor(ColorApp.colorTextDown);
            txtChangePer.setTextColor(ColorApp.colorTextDown);
            txtMatchPrice.setTextColor(ColorApp.colorTextDown);
        }
        if (open == ceil) txtOpen.setTextColor(ColorApp.colorTextCeiling);
        else if (open == ref) txtOpen.setTextColor(ColorApp.colorTextRef);
        else if (open == floor) txtOpen.setTextColor(ColorApp.colorTextFloor);
        else if (open > ref) txtOpen.setTextColor(ColorApp.colorTextUp);
        else txtOpen.setTextColor(ColorApp.colorTextDown);

        if (highest == ceil) txtHighest.setTextColor(ColorApp.colorTextCeiling);
        else if (highest == ref) txtHighest.setTextColor(ColorApp.colorTextRef);
        else if (highest == floor) txtHighest.setTextColor(ColorApp.colorTextFloor);
        else if (highest > ref) txtHighest.setTextColor(ColorApp.colorTextUp);
        else txtHighest.setTextColor(ColorApp.colorTextDown);

        if (lowest == ceil) txtLowest.setTextColor(ColorApp.colorTextCeiling);
        else if (lowest == ref) txtLowest.setTextColor(ColorApp.colorTextRef);
        else if (lowest == floor) txtLowest.setTextColor(ColorApp.colorTextFloor);
        else if (lowest > ref) txtLowest.setTextColor(ColorApp.colorTextUp);
        else txtLowest.setTextColor(ColorApp.colorTextDown);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == ID.getID("imgDetail" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)) {
            countLinear2 = countLinear2 + 5;
            if (listStatisticsPrice.size() < (countLinear2 + 5)) {
                countLinearAll = 2;
                new AsyntaskData().execute();
            } else {
                update2();
            }
        }

        if (view.getId() == ID.getID("imgDetailLeft" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)) {
            countLinear2 = countLinear2 - 5;

            update2();
        }

        if (view.getId() == ID.getID(getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)) {
            if (findViewById(ID.getID("linearLayout2" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)).getVisibility() == VISIBLE) {
                findViewById(ID.getID("linearLayout2" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)).setVisibility(GONE);
            } else {
                findViewById(ID.getID("linearLayout2" + getContext().getString(R.string.watchlist_detail_thong_ke_gia) + TAG)).setVisibility(VISIBLE);
            }
        }

        if (view.getId() == ID.getID("imgDetail" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)) {
            countLinear3 = countLinear3 + 5;
            if (listPlacingOrder.size() < (countLinear3 + 5)) {
                countLinearAll = 3;
                new AsyntaskData().execute();
            } else {
                update3();
            }
        }

        if (view.getId() == ID.getID("imgDetailLeft" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)) {
            countLinear3 = countLinear3 - 5;
            update3();
        }

        if (view.getId() == ID.getID(getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)) {
            if (findViewById(ID.getID("linearLayout3" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)).getVisibility() == VISIBLE) {
                findViewById(ID.getID("linearLayout3" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)).setVisibility(GONE);
            } else {
                findViewById(ID.getID("linearLayout3" + getContext().getString(R.string.watchlist_detail_thong_ke_dat_lenh) + TAG)).setVisibility(VISIBLE);
            }
        }

        if (view.getId() == ID.getID("imgDetail" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)) {
            countLinear4 = countLinear4 + 5;
            if (list4.size() < (countLinear4 + 5)) {
                countLinearAll = 4;
                new AsyntaskData().execute();
            } else {
                update4();
            }
        }

        if (view.getId() == ID.getID("imgDetailLeft" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)) {
            countLinear4 = countLinear4 - 5;
            update4();
        }

        if (view.getId() == ID.getID(getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)) {
            if (findViewById(ID.getID("linearLayout4" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)).getVisibility() == VISIBLE) {
                findViewById(ID.getID("linearLayout4" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)).setVisibility(GONE);
            } else {
                findViewById(ID.getID("linearLayout4" + getContext().getString(R.string.watchlist_detail_thong_ke_NDT_nuoc_ngoai) + TAG)).setVisibility(VISIBLE);
            }
        }
    }

    class AsyntaskData extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (countLinearAll == 2) {
                ArrayList<DataStatisticsPrice> list = GetJsonStatistics.getStatisticsPrice(context, code, String.valueOf(pageIndex2++));
                if (!(list.size() == 0)) {
                    listStatisticsPrice.addAll(list);
                }
            }

            if (countLinearAll == 3) {
                ArrayList<DataPlacingOrder> list = GetJsonStatistics.getPlacingOrder(context, code, String.valueOf(159));
                if (!(list.size() == 0)) {
                    listPlacingOrder.addAll(list);
                }
            }

            if (countLinearAll == 4) {
                ArrayList<Data4> list = GetJsonStatistics.get4(context, code, String.valueOf(pageIndex4++));
                if (!(list.size() == 0)) {
                    list4.addAll(list);
                }
            }

            publishProgress(countLinearAll);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {
                case 2:
                    update2();
                    break;
                case 3:
                    update3();
                    break;
                case 4:
                    update4();
                    break;
            }
        }
    }

    private void update2() {
        linearLayout2All.removeView(linearLayout2);
        linearLayout2All.addView(linearLayout2Detail(countLinear2));
    }

    private void update3() {
        linearLayout3All.removeView(linearLayout3);
        linearLayout3All.addView(linearLayout3Detail(countLinear3));
    }

    private void update4() {
        linearLayout4All.removeView(linearLayout4);
        linearLayout4All.addView(linearLayout4Detail(countLinear4));
    }
}
