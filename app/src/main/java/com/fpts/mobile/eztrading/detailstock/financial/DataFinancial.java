package com.fpts.mobile.eztrading.detailstock.financial;

import android.content.Context;

import java.util.ArrayList;

public class DataFinancial {
    public static String getLinkJson(String code) {
        return getLinkJsonPrivate(code);
    }

    private static String getLinkJsonPrivate(String code) {
        return "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_report&symbol=" + code;
    }

    public static ArrayList<String> getCache(Context context, String code) {
        return getCachePrivate(context, code);
    }

    private static ArrayList<String> getCachePrivate(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();


        return arrayList;
    }
}
