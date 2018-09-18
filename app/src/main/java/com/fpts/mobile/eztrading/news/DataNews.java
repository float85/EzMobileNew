package com.fpts.mobile.eztrading.news;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;

public class DataNews {
    static String Cache = "CacheNews86";
    public static String getLinkJson() {
        return getLinkJsonPrivete();
    }

    private static String getLinkJsonPrivete() {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news2&folder=86";
        return link;
    }

    public static void saveCache(Context context, ArrayList<NewsArticle> list) {
        saveCachePrivate(context, list);
    }

    private static void saveCachePrivate(Context context, ArrayList<NewsArticle> list) {
        String s = "";

         for (int i = 0; i < list.size(); i++) {
             String s1 = "";
             if (list.get(i).getNewsImg().equalsIgnoreCase("")) {
                s1 = "null";
             } else {
                 s1 = list.get(i).getNewsImg();
             }

            s = s + list.get(i).getNewsId() + "!@" + list.get(i).getNewsTitle() + "!@" + list.get(i).getNewsDate() + "!@" +
                    list.get(i).getNewsSizeInByte() + "!@" + list.get(i).getNewsSizeInKB() + "!@" + list.get(i).getNewsDate2() + "!@" +
                    list.get(i).getNewsFTitle() + "!@" + list.get(i).getNewsContent() + "!@" + s1 + "#&";
        }

        FileInputAndOutputStream.saveData(context, s, Cache);
    }

    public static ArrayList<NewsArticle> getCache(Context context) {
        return getCachePrivate(context);
    }

    private static ArrayList<NewsArticle> getCachePrivate(Context context) {
        ArrayList<NewsArticle> list = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        String s1 = FileInputAndOutputStream.readData(context, Cache);
        String s2 = s1.replace("\n", "");

        if (s2 == null) {

        } else {
            String[] strings = s2.split("#&");
            for (int i = 0; i < strings.length; i++) {
                String[] sEnvent = strings[i].split("!@");

                for (int j = 0; j < sEnvent.length; j++) {
                    arrayList.add(sEnvent[j]);
                }
            }

            for (int i = 0; i < arrayList.size(); i = i + 9) {
                try {
                    list.add(new NewsArticle(arrayList.get(i), arrayList.get(i + 1), arrayList.get(i + 2), arrayList.get(i + 3),
                            arrayList.get(i + 4), arrayList.get(i + 5), arrayList.get(i + 6), arrayList.get(i + 7), arrayList.get(i + 8)));
                } catch (Exception e) {}
            }
        }

        return list;
    }
}
