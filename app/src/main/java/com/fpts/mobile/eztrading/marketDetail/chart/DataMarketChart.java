package com.fpts.mobile.eztrading.marketDetail.chart;

import com.fpts.mobile.eztrading.R;

public class DataMarketChart {
    public static String getLinkJsonOneDay(String s) {
        return getLinkJsonOneDayPrivete(s);
    }

    private static String getLinkJsonOneDayPrivete(String s) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/realtime/?s=" + getSFromMarketNameForAll(s);
        return link;
    }

    public static String getLinkJsonAll(String s) {
        return getLinkJsonAllPrivete(s);
    }

    private static String getLinkJsonAllPrivete(String s) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=" + getSFromMarketNameForAll(s);
        return link;
    }

    private static String getSFromMarketNameForAll(String marketName) {

        switch (marketName.trim().toUpperCase()) {
            case "HOSE":
            case "VNINDEX":
            case "VNI":
                return "vnindex";
            case "HNX":
            case "HNXINDEX":
                return "hnxindex";
            case "UPCOM":
                return "upcomindex";
            case "VN30":
                return "vn30index";
            case "HNX30":
                return "hnx30index";
            default:
                return "";
        }
    }
}
