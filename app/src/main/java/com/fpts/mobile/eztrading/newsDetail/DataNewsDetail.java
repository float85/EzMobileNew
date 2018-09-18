package com.fpts.mobile.eztrading.newsDetail;

public class DataNewsDetail {
    public static String getLinkJson(String id) {
        return getLinkJsonPrivete(id);
    }

    private static String getLinkJsonPrivete(String id) {
        // TODO: TamHV 8/7/2018 1:35 AM https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news_detail&id=1131799
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news_detail&id=" + id;
        return link;
    }
}
