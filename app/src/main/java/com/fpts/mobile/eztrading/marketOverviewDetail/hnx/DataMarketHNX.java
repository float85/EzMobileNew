package com.fpts.mobile.eztrading.marketOverviewDetail.hnx;

import android.content.Context;
import android.widget.Toast;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class DataMarketHNX {
    private static String CodeMarket = "CodeMarket";
    private static String CacheMarketHSX = "CacheMarketHSX";

    public static String getLinkJson() {
        return getLinkJsonPrivete();
    }

    private static String getLinkJsonPrivete() {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=2&language=1";
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

    public static ArrayList<String> getCode(Context context) {
        return getCodePrivate(context);
    }

    private static ArrayList<String> getCodePrivate(Context context) {
        ArrayList<String> arrayList;

        arrayList = new ArrayList<>();
        arrayList.add("HNX30");
        arrayList.add("HNX30TRI");
        arrayList.add("HNXCon");
        arrayList.add("HNXFin");
        arrayList.add("HNXIndex");
        arrayList.add("HNXLCap");
        arrayList.add("HNXMan");
        arrayList.add("HNXMSCap");
        arrayList.add("UpCom");

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
            String[] s = {"HNX30", "HNX30TRI", "HNXCon", "HNXFin", "HNXIndex", "HNXLCap", "HNXMan", "HNXMSCap", "UpCom"};
            int count = 0;
            for (int i = 0; i < s.length * 5; i++) {
//                if (i % 5 == 0) {
//                    arrayList.add(s[count]);
//                    count = count + 1;
//                } else {
                arrayList.add("0");
//                }
            }
        } else {
            arrayList = new ArrayList<String>(Arrays.asList(s1.split(",")));
        }
        return arrayList;
    }

    public static ArrayList<String> getChannel(Context context) {
        return getChannelPrivete(context);
    }

    private static ArrayList<String> getChannelPrivete(Context context) {
        ArrayList<String> arrayList = getCodePrivate(context);
        ArrayList<String> arrayListNew = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).equalsIgnoreCase("upcom")){
//                arrayListNew.add("REALTIME_INDEX_HA_I" + "_11_" + "HNXUPCOMINDEX");
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_VALUE_" + "HNXUPCOMINDEX");
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_CHANGE_" + "HNXUPCOMINDEX");
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_RATIO_CHG_" + "HNXUPCOMINDEX");
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_VAL_" + "HNXUPCOMINDEX");
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_QTY_" + "HNXUPCOMINDEX");
            }
            else{
//                arrayListNew.add("REALTIME_INDEX_HA_I" + "_11_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_VALUE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_CHANGE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_RATIO_CHG_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_VAL_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_QTY_" + "HNXUPCOMINDEX");
            }
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
