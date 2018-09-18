package com.fpts.mobile.eztrading.marketDetail;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

public class DataMarketDetail {
    private static String Code = "CodeMarketDetail";

    public static ArrayList<String> getCode(Context context) {
        return getCodePrivate(context);
    }

    private static ArrayList<String> getCodePrivate(Context context) {
        String s = FileInputAndOutputStream.readData(context, Code);
        String s1 = s.replace("\n", "");
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i++) {
            if (arrayList1.get(i) != null && !arrayList1.get(i).equalsIgnoreCase(""))
                arrayList.add(arrayList1.get(i));
        }

        if (s.equals("")) {
            arrayList = new ArrayList<>();
            arrayList.add("VNI");
            arrayList.add("HNXINDEX");
            arrayList.add("UPCOM");
            arrayList.add("VN30");
            arrayList.add("HNX30");
        }

        return arrayList;
    }

    public static String getLinkJson(String s) {
        return getLinkJsonPrivete(s);
    }

    private static String getLinkJsonPrivete(String s) {
        String link = "";

        if (s.equalsIgnoreCase("realtime_index_ho")) {
            link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_ho";
        } else if (s.equalsIgnoreCase("realtime_index_ha")){
            link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_ha";
        } else if (s.equalsIgnoreCase("realtime_index_up")){
            link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_up";
        } else if (s.equalsIgnoreCase("realtime_index_vni30")){
            link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_vni30";
        } else if (s.equalsIgnoreCase("realtime_index_hnx30")){
            link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_hnx30";
        }

        return link;
    }

    public static ArrayList<String> getChannel(Context context) {
        return getChannelPrivete(context);
    }

    private static ArrayList<String> getChannelPrivete(Context context) {
        ArrayList<String> arrayList = getCodePrivate(context);
        ArrayList<String> arrayListNew = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (String.valueOf(arrayList.get(i).charAt(0)).equalsIgnoreCase("v")) {
                arrayListNew.add("REALTIME_" + "MO");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_INDEXVALUE");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_CHANGE");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_CHANGEPERCENT");
                arrayListNew.add("REALTIME_" + arrayList.get(i) + "_TOTALVALUE");
            } else {
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_NAME_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_VALUE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_CHANGE_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_RATIO_CHG_" + arrayList.get(i));
                arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_VAL_" + arrayList.get(i));
            }
        }
        return arrayListNew;
    }

    public static String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    private static String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_INDEX";
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static ArrayList<String> getKenhSocket(String s) {
        ArrayList<String> arrayListNew = new ArrayList<>();
        if (String.valueOf(s.charAt(0)).equalsIgnoreCase("v")) {
//            String[] strings = {"CHANGEPERCENT", "CHANGE", "INDEXVALUE", "TOTALTRADE", "TOTALTRADE", "TOTALSHARESAOM",
//                    "TOTALVALUESAOM", "UPVOLUME", "NOCHANGEVOLUME", "DOWNVOLUME", "UP",
//                    "DOWN", "NOCHANGE", "TIME", "CEILING", "FLOOR"};

            String[] strings = {"CHANGEPERCENT", "CHANGE", "INDEXVALUE", "TOTALSHARESAOM",
                    "TOTALVALUESAOM", "UP", "DOWN", "NOCHANGE", "CEILING", "FLOOR"};

            for (int i = 0; i < strings.length; i++) {
                arrayListNew.add("REALTIME_" +  s + "_" + strings[i]);
            }
        } else {
//            String[] strings = {"CODE", "NAME", "VALUE", "CHANGE", "RATIO_CHG", "TIME",
//                    "TOTAL_QTY", "TOTAL_VAL", "TRD_DTE", "TOTAL_STK", "PRV_INDEX_VAL",
//                    "HI_VAL", "LO_VAL", "NUM_INC", "NUM_NOCHG", "NUM_DEC", "NUM_CEIL", "NUM_FL"};

            String[] strings = {"RATIO_CHG", "CHANGE", "VALUE",
                    "TOTAL_QTY", "TOTAL_VAL", "NUM_INC", "NUM_DEC",
                    "NUM_NOCHG", "TRD_DTE", "TOTAL_STK"};

            for (int i = 0; i < strings.length; i++) {
                arrayListNew.add("REALTIME_INDEX_HA_I_" + strings[i] + "_" + "HNXINDEX");
            }
        }


//        ArrayList<String> list = new ArrayList<>();
//        for (int i = 0; i < strings.length; i++) {
//            list.add("REALTIME_INDEX_HA_I_" + "_" + strings[i] + "_" + s);
//        }

        return arrayListNew;
    }
}
