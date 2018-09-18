package com.fpts.mobile.eztrading.watchlist;

import android.content.Context;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class DataWatchList {
    private static String CodeUser = "CodeUser";
    private static String San = "SanUser";

    public static void addCodeMaket(Context context, String code, String san) {
        addCodePrivate(context, code, san);
    }

    private static void addCodePrivate(Context context, String s, String san) {
        int dem = 0;
        String m = FileInputAndOutputStream.readData(context, CodeUser);
        String k = m.replace("\n", "");
        String[] h = new String[0];
        if (!k.equals("")) {
            h = k.split(",");
        }
        for (int i = 0; i < h.length; i++) {
            if (s.equalsIgnoreCase(h[i])) {
                dem = 1;
            }
        }

        if (dem == 0) {
            String codeMarket = FileInputAndOutputStream.readData(context, CodeUser);
            String codeMarket1 = codeMarket.replace("\n", "");
            FileInputAndOutputStream.saveData(context, s + "," + codeMarket1, CodeUser);

            String codeSan = FileInputAndOutputStream.readData(context, San);
            String codeSan1 = codeSan.replace("\n", "");
            FileInputAndOutputStream.saveData(context, san + "," + codeSan1, San);

            Toast.makeText(context, R.string.watchlist_add_code_success, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.watchlist_add_code_error, Toast.LENGTH_SHORT).show();
        }
    }

    public static ArrayList<String> getCode(Context context) {
        return getCodePrivate(context);
    }

    private static ArrayList<String> getCodePrivate(Context context) {
        String s = FileInputAndOutputStream.readData(context, CodeUser);
        String s1 = s.replace("\n", "");
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i = i + 1) {
            if (!arrayList1.get(i).equalsIgnoreCase(""))
                arrayList.add(arrayList1.get(i));
        }

        if (arrayList == null || arrayList.size() == 0) {
            arrayList = new ArrayList<>();
            arrayList.add("FPT");
            arrayList.add("HAG");
            arrayList.add("FTS");
            arrayList.add("VNM");
            FileInputAndOutputStream.saveData(context, "FPT,HAG,FTS,VNM", CodeUser);
        }

        return arrayList;
    }

    private static ArrayList<String> getSanPrivate(Context context) {
        String s = FileInputAndOutputStream.readData(context, San);
        String s1 = s.replace("\n", "");
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i = i + 1) {
            if (!arrayList1.get(i).equalsIgnoreCase(""))
                arrayList.add(arrayList1.get(i));
        }

        if (arrayList == null || arrayList.size() == 0) {
            arrayList = new ArrayList<>();
            String str = "";
            for (int i = 0; i < getCode(context).size(); i++) {
                arrayList.add("HO");
                str += "HO,";
            }
            FileInputAndOutputStream.saveData(context, str, San);
        }
        return arrayList;
    }

    public static void deleteCode(Context context, String code) {
        deleteCodePrivate(context, code);
    }

    private static void deleteCodePrivate(Context context, String code) {
        String m = FileInputAndOutputStream.readData(context, CodeUser);
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
        FileInputAndOutputStream.saveData(context, c, CodeUser);
    }

    public static String getLinkJson(Context context) {
        return getLinkJsonPrivete(context);
    }

    private static String getLinkJsonPrivete(Context context) {
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.addAll(getCode(context));

        String k = "";
        for (String i : arrayList1) {
            k = k + i + ",";
        }
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=" + k;
        return link;
    }

    public static ArrayList<String> getChannel(Context context) {
        return getChannelPrivate(context);
    }

    private static ArrayList<String> getChannelPrivate(Context context) {

        ArrayList<String> arrayList = getCodePrivate(context);
        ArrayList<String> arrayListSan = getSanPrivate(context);

        ArrayList<String> arrayListNew = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayListSan.get(i).equals("HO")) {
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_MP");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_RE");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_TQ");
            } else {
                arrayListNew.add("REALTIME_SI_TRADE_PRICE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_SI_PRICE_REF_" + arrayList.get(i));
                arrayListNew.add("REALTIME_SI_TRADE_QTY_" + arrayList.get(i));
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

    public static String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    private static String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_QUOTES_STOCK";
    }
}
