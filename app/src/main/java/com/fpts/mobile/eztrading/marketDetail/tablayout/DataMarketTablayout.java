package com.fpts.mobile.eztrading.marketDetail.tablayout;

public class DataMarketTablayout {
    public static String getLinkJson(String c, String type) {
        return getLinkJsonPrivete(c, type);
    }

    private static String getLinkJsonPrivete(String c, String type) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=top_realtime&c=" + c + "&type=" + type;
        return link;
    }
}
