package com.fpts.mobile.eztrading.events;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;

public class DataEvents {
    private static String Cache = "CacheEvents";
    public static String getLinkJson() {
        return getLinkJsonPrivate();
    }

    private static String getLinkJsonPrivate() {
//        https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=eventbydate&language=1
        return "https://eztrade3.fpts.com.vn/gatewaydev/fpts/?s=eventbytype&language=1&type=1";
    }

    public static ArrayList<Event> getCache(Context context) {
        return getCachePrivate(context);
    }

    private static ArrayList<Event> getCachePrivate(Context context) {
        ArrayList<Event> list = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        String s1 = FileInputAndOutputStream.readData(context, Cache);
        String s2 = s1.replace("\n", "");

        if (s2 == null) {

        } else {
            String[] strings = s2.split("!@");
            for (int i = 0; i < strings.length; i++) {
                String[] sEnvent = strings[i].split("!#");

                for (int j = 0; j < sEnvent.length; j++) {
                    arrayList.add(sEnvent[j]);
                }
            }

            for (int i = 0; i < arrayList.size(); i = i + 7) {
                try {
                    list.add(new Event(arrayList.get(i), arrayList.get(i + 1), arrayList.get(i + 2), arrayList.get(i + 3),
                            arrayList.get(i + 4), arrayList.get(i + 5), arrayList.get(i + 6)));
                } catch (Exception e) {}
            }
        }

        return list;
    }

    public static void saveCache(ArrayList<Event> list, Context context) {
        saveCachePrivate(list, context);
    }

    private static void saveCachePrivate(ArrayList<Event> list, Context context) {
        String s = "";

        for (int i = 0; i < list.size(); i ++) {
            s = s + list.get(i).getGroupNm() + "!#" + list.get(i).getiD() + "!#" + list.get(i).getStockCode()
                    + "!#" + list.get(i).getMarketname() + "!#" + list.get(i).getContent() + "!#"
                    + list.get(i).getUrl() + "!#" + list.get(i).getDate1() + "!@" + "\n";
        }

        FileInputAndOutputStream.saveData(context, s, Cache);
    }
}
