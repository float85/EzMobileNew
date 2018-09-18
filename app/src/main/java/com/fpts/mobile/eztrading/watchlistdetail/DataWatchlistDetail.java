package com.fpts.mobile.eztrading.watchlistdetail;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;
import com.fpts.mobile.eztrading.common.ValueApp;

import java.util.ArrayList;
import java.util.Arrays;

public class DataWatchlistDetail {

    private static String CodeUser = "CodeUser";
    private static String SanUser = "SanUser";

    public static String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    private static String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_QUOTES_STOCK";
    }

    public static String getLinkJson(Context context) {
        return getLinkJsonPrivate(context);
    }

    private static String getLinkJsonPrivate(Context context) {
        ArrayList<String> arrayList1 = getCodePrivate(context);
        String k = "";
        for (String i : arrayList1) {
            k = k + i + ",";
        }
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=" + k;
        return link;
    }

    public static ArrayList<String> getChannel() {
        return getChanelPrivate();
    }

    private static ArrayList<String> getChanelPrivate() {
        ArrayList<String> arrayList = getCodePrivate(ValueApp.contextWatchlistDetailFragment);
        ArrayList<String> arrayListSan = getSanPrivate(ValueApp.contextWatchlistDetailFragment, arrayList.size());

        ArrayList<String> arrayListNew = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            String code = arrayList.get(i);
            if (arrayListSan.get(i).equals("HO")) {
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_RE");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_CE");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_FL");

                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_OP");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_HI");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_LO");

                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_FB");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_FS");

                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BP3");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BQ3");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BP2");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BQ2");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BP1");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_BQ1");

                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SP1");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SQ1");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SP2");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SQ2");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SP3");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_SQ3");

                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_TQ");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_MP");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_MQ");
//                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_MC");

            } else {
                arrayListNew.add("REALTIME_SI_PRICE_REF_" + code);
                arrayListNew.add("REALTIME_SI_PRICE_CEIL_" + code);
                arrayListNew.add("REALTIME_SI_TRADE_FL_" + code);

                arrayListNew.add("REALTIME_SI_OPEN_PRICE_" + code);
                arrayListNew.add("REALTIME_SI_HIGH_PRICE_" + code);
                arrayListNew.add("REALTIME_SI_LOW_PRICE_" + code);

                arrayListNew.add("REALTIME_SI_FR_BUY_" + code);
                arrayListNew.add("REALTIME_SI_FR_SELL_" + code);//8

                arrayListNew.add("REALTIME_TP_BUY_PRICE3_" + code);
                arrayListNew.add("REALTIME_TP_BUY_QTY3_" + code);
                arrayListNew.add("REALTIME_TP_BUY_PRICE2_" + code);
                arrayListNew.add("REALTIME_TP_BUY_QTY2_" + code);
                arrayListNew.add("REALTIME_TP_BUY_PRICE1_" + code);
                arrayListNew.add("REALTIME_TP_BUY_QTY1_" + code);//14

                arrayListNew.add("REALTIME_TP_SELL_PRICE1_" + code);
                arrayListNew.add("REALTIME_TP_SELL_QTY1_" + code);
                arrayListNew.add("REALTIME_TP_SELL_PRICE2_" + code);
                arrayListNew.add("REALTIME_TP_SELL_QTY2_" + code);
                arrayListNew.add("REALTIME_TP_SELL_PRICE3_" + code);
                arrayListNew.add("REALTIME_TP_SELL_QTY3_" + code);//20

                arrayListNew.add("REALTIME_SI_SUM_TRADE_QTY_" + code);
                arrayListNew.add("REALTIME_SI_TRADE_PRICE_" + code);
                arrayListNew.add("REALTIME_SI_TRADE_QTY_" + code);//23

                arrayListNew.add("REALTIME_EP_TRADE_PRICE_" + code);//24
                arrayListNew.add("REALTIME_EP_TRADE_QTY_" + code);//25
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayListSan.get(i).equals("HO")) {
            } else {
                arrayListNew.add("REALTIME_SI_TRADE_PRICE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_SI_TRADE_QTY_" + arrayList.get(i));
            }
        }
        return arrayListNew;
    }

    public static ArrayList<String> getCode(Context context) {
        return getCodePrivate(context);
    }

    private static ArrayList<String> getCodePrivate(Context context) {
        String s = FileInputAndOutputStream.readData(context, CodeUser);
        String s1 = s.replace("\n", "");
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i ++) {
            arrayList.add(arrayList1.get(i));
        }
        if (s.equals("")) {
            arrayList = new ArrayList<>();
            arrayList.add("FPT");
            arrayList.add("FTS");
            arrayList.add("GAS");
            arrayList.add("VNM");
        }
        return arrayList;
    }

    private static ArrayList<String> getSanPrivate(Context context, int size) {
        String s = FileInputAndOutputStream.readData(context, SanUser);
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s.replace("\n", "")
                .split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i ++) {
            arrayList.add(arrayList1.get(i));
        }

        if (s == null || s.equalsIgnoreCase("")) {
            arrayList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                arrayList.add("HO");
            }
        }
        return arrayList;
    }

}
