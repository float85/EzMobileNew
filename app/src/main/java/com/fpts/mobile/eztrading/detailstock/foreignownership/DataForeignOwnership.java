package com.fpts.mobile.eztrading.detailstock.foreignownership;

import android.content.Context;

import java.util.ArrayList;

public class DataForeignOwnership {
    public static ArrayList<String> getCacheData(Context context, String code) {
        return getCacheDataPrivate(context, code);
    }

    private static ArrayList<String> getCacheDataPrivate(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        return arrayList;
    }

    public static String getLinkJson(String code) {
        return getLinkJsonPrivate(code);
    }

    private static String getLinkJsonPrivate(String code) {
        return "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_basic&symbol=" + code;
    }

}
