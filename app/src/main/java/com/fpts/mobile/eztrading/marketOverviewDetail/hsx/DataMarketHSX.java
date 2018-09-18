package com.fpts.mobile.eztrading.marketOverviewDetail.hsx;

import android.content.Context;
import android.widget.Toast;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class DataMarketHSX {
    private static String CodeMarket = "CodeMarket";
    private static String CacheMarketHSX = "CacheMarketHSX";

    public static String getLinkJson() {
        return getLinkJsonPrivete();
    }

    private static String getLinkJsonPrivete() {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=1&language=1";
        return link;
    }

    public static void addCode(Context context, String symbol) {
        addCodePrivate(context, symbol);
    }

    private static void addCodePrivate(Context context, String symbol) {
        String codeMarket = FileInputAndOutputStream.readData(context, CodeMarket);
        if (codeMarket.contains(symbol)) {
        } else {
            String codeMarket1 = codeMarket.replace("\n", "");
            FileInputAndOutputStream.saveData(context, codeMarket1 + "," + symbol, CodeMarket);
        }
    }

    public static ArrayList<String> getCode() {
        return getCodePrivate();
    }

    private static ArrayList<String> getCodePrivate() {
        ArrayList<String> arrayList;

        arrayList = new ArrayList<>();
        arrayList.add("VNI");
        arrayList.add("VN100");
        arrayList.add("VN30");
        arrayList.add("VNALL");
        arrayList.add("VNMID");
        arrayList.add("VNSML");
        arrayList.add("VNXAL");
        return arrayList;
    }

    public static void saveCache(Context context, ArrayList<String> s) {
        saveCachePrivate(context, s);
    }

    private static void saveCachePrivate(Context context, ArrayList<String> s) {
        String s1 = "";
        for (int i = 0; i < s.size(); i++) {
            s1 = s1 + s.get(i) + ",";
        }
        FileInputAndOutputStream.saveData(context, s1, CacheMarketHSX);
    }

    public static ArrayList<String> getCache(Context context) {
        return getCachePrivate(context);
    }

    private static ArrayList<String> getCachePrivate(Context context) {
        String s1 = FileInputAndOutputStream.readData(context, CacheMarketHSX);
        String s2 = s1.replace("\n", "");
        ArrayList<String> arrayList;
        if (s2.equals("")) {
            arrayList = new ArrayList<>();
            String[] s = {"VNI", "VN100", "VN30 ", "VNALL", "VNMID", "VNSML", "VNXAL"};
            for (int i = 0; i < s.length * 5; i++) {
                arrayList.add("0");
            }
        } else {
            arrayList = new ArrayList<String>(Arrays.asList(s1.split(",")));
        }
        return arrayList;
    }

    public static void deleteCode(Context context, String code) {
        deleteCodePrivate(context, code);
    }

    private static void deleteCodePrivate(Context context, String code) {
        String m = FileInputAndOutputStream.readData(context, CodeMarket);
        String k = m.replace("\n", "");
        ArrayList<String> arrayListOld = new ArrayList<>();
        if (!k.equals("")) {
            arrayListOld = new ArrayList<>(Arrays.asList(k.split(",")));
        }
        String c = "";
        for (int i = 0; i < arrayListOld.size(); i++) {
            if (!code.equals(arrayListOld.get(i))) {
                c = c + arrayListOld.get(i) + ",";
            }
        }
        FileInputAndOutputStream.saveData(context, c, CodeMarket);
    }

    public static ArrayList<String> getChannel() {
        return getChannelPrivete();
    }

    private static ArrayList<String> getChannelPrivete() {
        ArrayList<String> arrayList = getCodePrivate();
        ArrayList<String> arrayListNew = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
//            arrayListNew.add("REALTIME_" + "MO");
            arrayListNew.add("REALTIME_" + arrayList.get(i) + "_INDEXVALUE");
            arrayListNew.add("REALTIME_" + arrayList.get(i) + "_CHANGE");
            arrayListNew.add("REALTIME_" + arrayList.get(i) + "_CHANGEPERCENT");
            arrayListNew.add("REALTIME_" + arrayList.get(i) + "_TOTALVALUE");
            arrayListNew.add("REALTIME_" + arrayList.get(i) + "_TOTALQUANTITY");
        }
        return arrayListNew;
    }

    public static boolean isCodeSaved(Context context, String code) {
        return isCodeSavedPrivate(context, code);
    }

    private static boolean isCodeSavedPrivate(Context context, String code) {
        String codeMarket = FileInputAndOutputStream.readData(context, CodeMarket);
        if (codeMarket.contains(code))
            return true;

        return false;
    }

    public static String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    private static String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_INDEX";
    }
}
