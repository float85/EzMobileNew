package com.fpts.mobile.eztrading.common;

import java.util.ArrayList;

public class ID {
    public static ArrayList<String> arrayList = new ArrayList<>();

    public static int setID(String id) {
        arrayList.add(id);
        return arrayList.size()-1;
    }

    public static int getID(String id) {
        int re = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (id.equalsIgnoreCase(arrayList.get(i))) {
                re = i;
            }
        }
        return re;
    }
}
