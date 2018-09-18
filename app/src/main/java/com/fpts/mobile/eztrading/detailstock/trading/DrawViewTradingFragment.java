package com.fpts.mobile.eztrading.detailstock.trading;

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
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.newsDetail.NewsDetailFragment;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class DrawViewTradingFragment extends ScrollView implements View.OnClickListener {
    private final String TAG = getClass().getName();
    private Context context;
    private Activity activity;
    private String code;

    private Typeface typeface;
    private Typeface typefaceBold;
    private int mHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int mHeightItem = mHeight / 15;
    private int mTextSizeMax = mHeightItem / 2;
    private int mTextSize = mTextSizeMax * 3 / 5;

    private Quote quotes;
    private StockInfo stockInfo = new StockInfo();
    private ArrayList<String> arrayListChart = new ArrayList<>();
    private ArrayList<String> arrayListChartAll = new ArrayList<>();
    private ArrayList<String> arrayListNews = new ArrayList<>();
    private ArrayList<String> arrayListSK;

    public DrawViewTradingFragment(Context context, Activity activity, String code, StockInfo stockInfo, Quote quotes,
                                   ArrayList<String> arrayListChart, ArrayList<String> arrayListChartAll,
                                   ArrayList<String> arrayListNews) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.code = code;
        this.quotes = quotes;
        this.stockInfo = stockInfo;
        this.arrayListChart = arrayListChart;
        this.arrayListChartAll = arrayListChartAll;
        this.arrayListNews = arrayListNews;

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
        linearLayout.addView(linearLayoutPrice());
        linearLayout.addView(mLine());
        linearLayout.addView(linearLayoutBuySell());
        linearLayout.addView(linearLayoutInfo());
        linearLayout.addView(linearLayoutChart());
        linearLayout.addView(linearLayoutNews());
        linearLayout.addView(linearLayoutAnalys());
        linearLayout.addView(linearLayoutComment());

        this.addView(linearLayout);

        arrayListSK = new ArrayList<>();

        arrayListSK.add("txtMatchPrice" + TAG);
        arrayListSK.add("textViewKL1" + TAG);
        arrayListSK.add("txtChange" + TAG);
//        s.add("txtChangePer");
        arrayListSK.add("textViewTT1" + TAG);

        arrayListSK.add("txtBuyQty3" + TAG);
        arrayListSK.add("txtBuyPrice3" + TAG);

        arrayListSK.add("txtBuyQty2" + TAG);
        arrayListSK.add("txtBuyPrice2" + TAG);

        arrayListSK.add("txtBuyQty1" + TAG);
        arrayListSK.add("txtBuyPrice1" + TAG);

        arrayListSK.add("txtSellPrice1" + TAG);
        arrayListSK.add("txtSellQty1" + TAG);

        arrayListSK.add("txtSellPrice2" + TAG);
        arrayListSK.add("txtSellQty2" + TAG);

        arrayListSK.add("txtSellPrice3" + TAG);
        arrayListSK.add("txtSellQty3" + TAG);

        arrayListSK.add("txtTC" + TAG);
        arrayListSK.add("txtCeiling" + TAG);
        arrayListSK.add("txtFloor" + TAG);

        arrayListSK.add("txtOpen" + TAG);
        arrayListSK.add("txtHighest" + TAG);
        arrayListSK.add("txtLowest" + TAG);

        arrayListSK.add("txtForeignBuy" + TAG);
        arrayListSK.add("txtForeignSell" + TAG);
    }

    private LinearLayout linearLayoutPrice() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);

        ImageView imageView = new ImageView(context);
        imageView.setId(ID.setID("imageView" + TAG));
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(mWidth / 9, mWidth / 9);
        paramsImg.setMargins(8, 0, 8, 0);
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
            changePer = (double) (Math.round(((matchPrice - refPrice) / refPrice * 100) * 100)) / 100;
        }
        TextView txtChangePer = mTextView(changePer + "%");
        txtChangePer.setId(ID.setID("txtChangePer" + TAG));
        txtChangePer.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        txtChangePer.setPadding(12, 0, 0, 0);

        linearLayout1.addView(txtMatchPrice);
        linearLayout1.addView(txtChange);
        linearLayout1.addView(txtChangePer);

        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(params);
        linearLayout2.setOrientation(HORIZONTAL);
        TextView textViewKL = mTextView("GDKL:");
        textViewKL.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        TextView textViewKL1 = mTextView(quotes.getMatchQtty());
        textViewKL1.setTypeface(typefaceBold);
        textViewKL1.setTextSize(pxToDp(mTextSizeMax * 3 / 4));
        textViewKL1.setId(ID.setID("textViewKL1" + TAG));
        textViewKL1.setPadding(12, 0, 12, 0);
        TextView textViewTT = mTextView("GDTT:");
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
        linearLayout31.addView(mTextView("Trần:"));
        linearLayout31.addView(mTextView("TC:"));
        linearLayout31.addView(mTextView("Sàn:"));
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
        linearLayout33.addView(mTextView("Mở:"));
        linearLayout33.addView(mTextView("Cao nhất:"));
        linearLayout33.addView(mTextView("Thấp nhất:"));
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

        linearLayout41.addView(mTextView("NN Mua:"));
        linearLayout41.addView(txtForeignBuy);
        linearLayout42.addView(mTextView("NN Bán:"));
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

    private LinearLayout linearLayoutBuySell() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 20;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(params);


        LinearLayout linearLayout0 = new LinearLayout(context);
        LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout0.setLayoutParams(params0);
        TextView textView = mTextView("");
        textView.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(R.string.watchlist_detail_best_price_buy);
        textView.setPadding(0, 0, 12, 0);
        textView.setGravity(Gravity.RIGHT);

        TextView textView1 = mTextView("");
        textView1.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView1.setText(R.string.watchlist_detail_best_price_sell);
        textView1.setPadding(12, 0, 0, 0);
        textView1.setGravity(Gravity.LEFT);
        linearLayout0.addView(textView);
        linearLayout0.addView(textView1);

        LinearLayout linearLayout1 = new LinearLayout(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.topMargin = 8;
        linearLayout1.setLayoutParams(params1);
        View view = mLine();
        view.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 2, 1));
        View view1 = mLine();
        view1.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 2, 1));
        linearLayout1.addView(view);
        linearLayout1.addView(view1);

        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setPadding(8, 0, 8, 0);
        linearLayout2.setLayoutParams(params);
        linearLayout2.setOrientation(HORIZONTAL);
        LinearLayout linearLayout21 = new LinearLayout(context);
        linearLayout21.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params21 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout21.setLayoutParams(params21);
        TextView txtBuy1 = mLinearLayoutBuy();
        txtBuy1.setId(ID.setID("txtBuy1" + TAG));
        TextView txtBuy2 = mLinearLayoutBuy();
        txtBuy2.setId(ID.setID("txtBuy2" + TAG));
        TextView txtBuy3 = mLinearLayoutBuy();
        txtBuy3.setId(ID.setID("txtBuy3" + TAG));
        linearLayout21.addView(txtBuy1);
        linearLayout21.addView(txtBuy2);
        linearLayout21.addView(txtBuy3);

        LinearLayout.LayoutParams paramsBuySell = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        paramsBuySell.bottomMargin = 12;

        LinearLayout linearLayout22 = new LinearLayout(context);
        linearLayout22.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params22 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout22.setLayoutParams(params22);
        TextView txtBuyQty1 = mTextView(quotes.getBuyQtty1());
        txtBuyQty1.setLayoutParams(paramsBuySell);
        txtBuyQty1.setId(ID.setID("txtBuyQty1" + TAG));
        txtBuyQty1.setGravity(Gravity.RIGHT);
        TextView txtBuyQty2 = mTextView(quotes.getBuyQtty2());
        txtBuyQty2.setId(ID.setID("txtBuyQty2" + TAG));
        txtBuyQty2.setLayoutParams(paramsBuySell);
        txtBuyQty2.setGravity(Gravity.RIGHT);
        TextView txtBuyQty3 = mTextView(quotes.getBuyQtty3());
        txtBuyQty3.setId(ID.setID("txtBuyQty3" + TAG));
        txtBuyQty3.setLayoutParams(paramsBuySell);
        txtBuyQty3.setGravity(Gravity.RIGHT);
        linearLayout22.addView(txtBuyQty1);
        linearLayout22.addView(txtBuyQty2);
        linearLayout22.addView(txtBuyQty3);

        LinearLayout linearLayout23 = new LinearLayout(context);
        linearLayout23.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params23 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout23.setLayoutParams(params23);
        TextView txtBuyPrice1 = mTextView(quotes.getBuyPrice1());
        txtBuyPrice1.setId(ID.setID("txtBuyPrice1" + TAG));
        txtBuyPrice1.setLayoutParams(paramsBuySell);
        txtBuyPrice1.setGravity(Gravity.RIGHT);
        TextView txtBuyPrice2 = mTextView(quotes.getBuyPrice2());
        txtBuyPrice2.setId(ID.setID("txtBuyPrice2" + TAG));
        txtBuyPrice2.setLayoutParams(paramsBuySell);
        txtBuyPrice2.setGravity(Gravity.RIGHT);
        TextView txtBuyPrice3 = mTextView(quotes.getBuyPrice3());
        txtBuyPrice3.setId(ID.setID("txtBuyPrice3" + TAG));
        txtBuyPrice3.setLayoutParams(paramsBuySell);
        txtBuyPrice3.setGravity(Gravity.RIGHT);
        linearLayout23.addView(txtBuyPrice1);
        linearLayout23.addView(txtBuyPrice2);
        linearLayout23.addView(txtBuyPrice3);

        LinearLayout linearLayout24 = new LinearLayout(context);
        linearLayout24.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params24 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout24.setLayoutParams(params24);
        TextView txtSellPrice1 = mTextView(quotes.getSellPrice1());
        txtSellPrice1.setId(ID.setID("txtSellPrice1" + TAG));
        txtSellPrice1.setLayoutParams(paramsBuySell);
        txtSellPrice1.setGravity(Gravity.RIGHT);
        TextView txtSellPrice2 = mTextView(quotes.getSellPrice2());
        txtSellPrice2.setId(ID.setID("txtSellPrice2" + TAG));
        txtSellPrice2.setLayoutParams(paramsBuySell);
        txtSellPrice2.setGravity(Gravity.RIGHT);
        TextView txtSellPrice3 = mTextView(quotes.getSellPrice3());
        txtSellPrice3.setId(ID.setID("txtSellPrice3" + TAG));
        txtSellPrice3.setLayoutParams(paramsBuySell);
        txtSellPrice3.setGravity(Gravity.RIGHT);
        linearLayout24.addView(txtSellPrice1);
        linearLayout24.addView(txtSellPrice2);
        linearLayout24.addView(txtSellPrice3);

        LinearLayout linearLayout25 = new LinearLayout(context);
        linearLayout25.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params25 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout25.setLayoutParams(params25);
        TextView txtSellQty1 = mTextView(quotes.getSellQtty1());
        txtSellQty1.setId(ID.setID("txtSellQty1" + TAG));
        txtSellQty1.setLayoutParams(paramsBuySell);
        txtSellQty1.setGravity(Gravity.RIGHT);
        TextView txtSellQty2 = mTextView(quotes.getSellQtty2());
        txtSellQty2.setId(ID.setID("txtSellQty2" + TAG));
        txtSellQty2.setLayoutParams(paramsBuySell);
        txtSellQty2.setGravity(Gravity.RIGHT);
        TextView txtSellQty3 = mTextView(quotes.getSellQtty3());
        txtSellQty3.setId(ID.setID("txtSellQty3" + TAG));
        txtSellQty3.setLayoutParams(paramsBuySell);
        txtSellQty3.setGravity(Gravity.RIGHT);
        linearLayout25.addView(txtSellQty1);
        linearLayout25.addView(txtSellQty2);
        linearLayout25.addView(txtSellQty3);

        LinearLayout linearLayout26 = new LinearLayout(context);
        linearLayout26.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params26 = new LinearLayout.LayoutParams((mWidth - 16) / 6, ViewGroup.LayoutParams.WRAP_CONTENT);
        params26.gravity = Gravity.RIGHT;
        linearLayout26.setLayoutParams(params26);
        TextView llSell1 = mTextViewSell();
        llSell1.setId(ID.setID("txtSell1" + TAG));
        TextView llSell2 = mTextViewSell();
        llSell2.setId(ID.setID("txtSell2" + TAG));
        TextView llSell3 = mTextViewSell();
        llSell3.setId(ID.setID("txtSell3" + TAG));
        linearLayout26.addView(llSell1);
        linearLayout26.addView(llSell2);
        linearLayout26.addView(llSell3);

        linearLayout2.addView(linearLayout21);
        linearLayout2.addView(linearLayout22);
        linearLayout2.addView(linearLayout23);
        linearLayout2.addView(linearLayout24);
        linearLayout2.addView(linearLayout25);
        linearLayout2.addView(linearLayout26);
        //progress
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.weight = 1;
        params3.setMargins(8, 20, 8, 0);
        linearLayout3.setLayoutParams(params3);
        Double sellPer = getPercent();
        TextView txtSellPer = mTextView("" + (double) Math.round(sellPer * 100) / 100 + "%");
        txtSellPer.setId(ID.setID("txtSellPer" + TAG));
        txtSellPer.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));
        txtSellPer.setPadding(0, 0, 12, 0);
        TextView txtBuyPer = mTextView("" + (double) Math.round((100 - sellPer) * 100) / 100 + "%");
        txtBuyPer.setId(ID.setID("txtBuyPer" + TAG));
        txtBuyPer.setPadding(12, 0, 0, 0);
        txtBuyPer.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));
        ProgressBar progressBar = new ProgressBar(context, null, R.style.HorizontalProgressBar);
        progressBar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_horizontal));
//        progressBar.setIndeterminate();
        LinearLayout.LayoutParams paramsProgress = new LinearLayout.LayoutParams(mWidth - 100 * 2,
                mHeightItem / 3);
        paramsProgress.weight = 1;
        progressBar.setProgress((int) getPercent());
        progressBar.setLayoutParams(paramsProgress);

        linearLayout3.addView(txtSellPer);
        linearLayout3.addView(progressBar);
        linearLayout3.addView(txtBuyPer);

        linearLayout.addView(linearLayout0);
        linearLayout.addView(linearLayout1);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout3);
        return linearLayout;
    }

    private LinearLayout linearLayoutInfo() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 20;
        params.bottomMargin = 20;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(params);

        LinearLayout linearLayout1 = new LinearLayout(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((mWidth - 32) / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.rightMargin = 8;
        params1.leftMargin = 8;
        linearLayout1.setLayoutParams(params1);
        linearLayout1.setOrientation(VERTICAL);
        linearLayout1.addView(linearLayoutItemInfo(context.getString(R.string.watchlist_detail_klbq_30_ngay), stockInfo.getKLGD30Days()));
        linearLayout1.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_gia_thap_nhat_52_tuan), stockInfo.getWk52Low()));
        linearLayout1.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_gia_cao_nhat_52_tuan), stockInfo.getWk52High()));
        linearLayout1.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_nn_mua_YDT), stockInfo.getNNMUA_YTD()));
        linearLayout1.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_nuoc_ngoai), stockInfo.getTLSHNN()));
        linearLayout1.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_von_hoa), stockInfo.getMktCap()));

        LinearLayout linearLayout2 = new LinearLayout(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((mWidth - 32) / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.leftMargin = 8;
        params2.rightMargin = 8;
        linearLayout2.setLayoutParams(params2);
        linearLayout2.setOrientation(VERTICAL);
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_ESP), stockInfo.getResEPS()));
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_ESP_dieu_chinh), stockInfo.getEPSAdjustedSTC()));
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_PE), stockInfo.getPE()));
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_PB), stockInfo.getPB()));
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_gia_tri_so_sach), ""));
        linearLayout2.addView(linearLayoutItemInfo(getContext().getString(R.string.watchlist_detail_KLDNY), stockInfo.getQty()));


        linearLayout.addView(linearLayout1);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    private LinearLayout linearLayoutItemInfo(String title, String value) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.topMargin = 8;
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);

        TextView txtTitle = mTextView(title);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.weight = 1;
        txtTitle.setLayoutParams(params1);
        txtTitle.setGravity(Gravity.LEFT);
        txtTitle.setPadding(8, 0, 8, 0);
        TextView txtValue = mTextView(value);
        txtValue.setGravity(Gravity.RIGHT);
        txtValue.setPadding(0, 0, 8, 0);

        linearLayout.addView(txtTitle);
        linearLayout.addView(txtValue);
        return linearLayout;
    }

    private LinearLayout linearLayoutChart() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        ScrollView scrollView = new DrawViewChart(context, arrayListChart, arrayListChartAll);
        LinearLayout header = mLinearLayoutHeader("Chart");

        linearLayout.addView(header);
        linearLayout.addView(scrollView);

        header.setOnClickListener(view -> scrollView.setVisibility(scrollView.getVisibility() == VISIBLE ? GONE : VISIBLE));
        return linearLayout;
    }

    private LinearLayout linearLayoutNews() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        LinearLayout linearLayout1 = new LinearLayout(context);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout1.setLayoutParams(params1);
        linearLayout1.setOrientation(VERTICAL);
        if (arrayListNews != null && arrayListNews.size() > 0) {
            int count = arrayListNews.size() >= 5 * 10 ? 5 * 10 : arrayListNews.size();
            for (int i = 0; i < count / 10; i++) {
                linearLayout1.addView(linearLayoutItemNews(arrayListNews.get(i * 10 + 0), arrayListNews.get(i * 10 + 1),
                        arrayListNews.get(i * 10 + 2)));
                linearLayout1.addView(mLine());
            }
        }

        linearLayout.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_news)));
        linearLayout.addView(linearLayout1);
        return linearLayout;
    }

    private LinearLayout linearLayoutItemNews(String ID, String title, String date) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams((mWidth - 16) / 5, (mWidth - 16) / 5);
        imageView.setLayoutParams(paramsImg);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(ImageApp.iconNews);

        LinearLayout linearLayout1 = new LinearLayout(context);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout1.setLayoutParams(params1);
        linearLayout1.setOrientation(VERTICAL);
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params2.weight = 1;
        params2.setMargins(8, 8, 8, 0);
        textView.setLayoutParams(params2);
        textView.setText(title);
        textView.setMaxLines(3);
        textView.setTypeface(typeface);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setTextColor(ColorApp.colorText);
        TextView txtTime = new TextView(context);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.setMargins(0, 0, 8, 8);
        txtTime.setLayoutParams(params3);
        txtTime.setTypeface(typeface);
        txtTime.setGravity(Gravity.RIGHT);
        txtTime.setText(date);
        txtTime.setTextSize(pxToDp(mTextSize - 1));
        txtTime.setTextColor(ColorApp.colorTextSub);
        linearLayout1.addView(textView);
        linearLayout1.addView(txtTime);

        linearLayout.addView(imageView);
        linearLayout.addView(linearLayout1);

        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueApp.fragmentTransactionStockDetailTradingFragment.replace(R.id.linear_layout, NewsDetailFragment.newInstance(ID, code, "Market News"));
                ValueApp.fragmentTransactionStockDetailTradingFragment.commit();
                ValueApp.vt = ValueApp.ENUM_VT.FROM_WATCHLIST_DETAIL_TRADING;
            }
        });
        return linearLayout;
    }

    private LinearLayout linearLayoutAnalys() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        linearLayout.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_analysis)));
        return linearLayout;
    }

    private LinearLayout linearLayoutComment() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        linearLayout.addView(mLinearLayoutHeader(getContext().getString(R.string.watchlist_detail_comment)));
        return linearLayout;
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

    private TextView mLinearLayoutBuy() {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.rightMargin = 8;
        params.bottomMargin = 12;
        textView.setLayoutParams(params);
        textView.setBackgroundResource(R.drawable.bg_button_buy);
        textView.setGravity(Gravity.CENTER);

        textView.setTextSize(pxToDp(mTextSize));
        textView.setText(R.string.watchlist_detail_buy);
        textView.setTypeface(typeface);
        textView.setAllCaps(true);
        textView.setTextColor(ColorApp.colorWhite);
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine();
        textView.setMaxLines(1);

        return textView;
    }

    private TextView mTextViewSell() {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = 8;
        params.bottomMargin = 12;
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setText(R.string.watchlist_detail_sell);
        textView.setTypeface(typeface);
        textView.setBackgroundResource(R.drawable.bg_button_sell);
        textView.setTextColor(ColorApp.colorWhite);
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine();
        textView.setMaxLines(1);

        return textView;
    }

    private View mLine() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        view.setLayoutParams(params);
        view.setBackgroundColor(ColorApp.colorGray);
        return view;
    }

    private LinearLayout mLinearLayoutHeader(String str) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundHeaderGray);

        TextView textView = new TextView(context);
        textView.setId(ID.setID(str + TAG));
        textView.setOnClickListener(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(mWidth - mWidth / 12, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.weight = 1;
        textView.setLayoutParams(params1);
        textView.setPadding(8, 0, 0, 0);
        textView.setText(str);
        textView.setAllCaps(true);
        textView.setTypeface(typefaceBold);
        textView.setTextColor(ColorApp.colorTextHeader);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(mWidth / 12, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setId(ID.setID("imgDetail" + str + TAG));
        imageView.setOnClickListener(this);
        imageView.setImageDrawable(ImageApp.iconArrowRight);
        imageView.setLayoutParams(params2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(12, 12, 4, 12);

        linearLayout.addView(textView);
        linearLayout.addView(imageView);
        return linearLayout;
    }

    private double getPercent() {
        double BuyQtty3 = Double.parseDouble(quotes.getBuyQtty3() != null && quotes.getBuyQtty3() != "" ?
                quotes.getBuyQtty3().replace(",", "") : "0");
        double BuyQtty2 = Double.parseDouble(quotes.getBuyQtty2() != null && quotes.getBuyQtty2() != "" ?
                quotes.getBuyQtty2().replace(",", "") : "0");
        double BuyQtty1 = Double.parseDouble(quotes.getBuyQtty1() != null && quotes.getBuyQtty1() != "" ?
                quotes.getBuyQtty1().replace(",", "") : "0");
        double SellQtty1 = Double.parseDouble(quotes.getSellQtty1() != null && quotes.getSellQtty1() != "" ?
                quotes.getSellQtty1().replace(",", "") : "0");
        double SellQtty2 = Double.parseDouble(quotes.getSellQtty2() != null && quotes.getSellQtty2() != "" ?
                quotes.getSellQtty2().replace(",", "") : "0");
        double SellQtty3 = Double.parseDouble(quotes.getSellQtty3() != null && quotes.getSellQtty3() != "" ?
                quotes.getSellQtty3().replace(",", "") : "0");
        double sumBuy = BuyQtty1 + BuyQtty2 + BuyQtty3;//BuyPrice1 + BuyPrice2 + BuyPrice3
        double sumSell = SellQtty1 + SellQtty2 + SellQtty3;// SellPrice1 + SellPrice2 + SellPrice3 +
        if (sumSell + sumBuy > 0)
            return (double) Math.round((sumBuy * 100 / (sumSell + sumBuy)) * 100) / 100;

        return 50;
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

    }

    public void change(int id, String s) {
        TextView textView = findViewById(ID.getID(arrayListSK.get(id)));
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
