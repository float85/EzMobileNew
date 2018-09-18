package com.fpts.mobile.eztrading.detailstock;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;

public class DataDetailStock {
    private static String CodeStock = "CodeStock";

    public static String getNameCompany(Context context, String symbol) {

        return getNameCompanyPrivate(context, symbol);
    }

    private static String getNameCompanyPrivate(Context context, String symbol) {

        String s1 = FileInputAndOutputStream.readData(context, CodeStock);
        String s2 = s1.replace("\n", "");
        String name = "";
        ArrayList arrayList = new ArrayList<>();
        String[] strings1 = s2.split("@#");
        for (int i = 0; i < strings1.length; i++) {
            String[] strings2 = strings1[i].split("!=");
            if (strings2[0].toUpperCase().equalsIgnoreCase(symbol.toUpperCase()))
                return strings2[1];
        }
        return "";
    }
}
