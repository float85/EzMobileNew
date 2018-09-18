package com.fpts.mobile.eztrading.detailstock.statistics;

import com.fpts.mobile.eztrading.detailstock.trading.Quote;
import com.fpts.mobile.eztrading.detailstock.trading.StockInfo;

import java.util.ArrayList;

public class DataStatistics {
    private static ArrayList<String> arrayListPrice = new ArrayList<>();
    private static ArrayList<String> arrayListChart = new ArrayList<>();
    private static ArrayList<String> arrayListNews = new ArrayList<>();

    public static String getLinkJsonQuote(String code) {
        return getLinkJsonQuotePrivate(code);
    }

    private static String getLinkJsonQuotePrivate(String code) {
        return "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=" + code;
    }

    public static String getLinkJsonStockInfo(String code) {
        return getLinkJsonStockInfoPrivate(code);
    }

    private static String getLinkJsonStockInfoPrivate(String code) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_basic&symbol=" + code;
        //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_basic&symbol=" + code;
        return link;
    }

    public static Quote getCacheQuote(String code) {
        return getCacheQuotePrivate(code);
    }

    private static Quote getCacheQuotePrivate(String code) {
        Quote quote = new Quote();

        return quote;
    }

    public static StockInfo getCachePrice(String code) {
        return getCachePricePrice(code);
    }

    private static StockInfo getCachePricePrice(String code) {
        StockInfo stockInfo = new StockInfo();

        return stockInfo;
    }

    public static String getLinkStatisticsPrice(String code, String pageIndex) {
        return getLinkStatisticsPricePrivate(code, pageIndex);
    }

    private static String getLinkStatisticsPricePrivate(String code, String pageIndex) {
        String s = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=hist_stock&a=2&code=" + code + "&pageindex=" + pageIndex;

        return s;
    }

    public static String getLinkPlacingOder(String code, String pageIndex) {
        return getLinkPlacingOderPrivate(code, pageIndex);
    }

    private static String getLinkPlacingOderPrivate(String code, String pageIndex) {
        String s = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=hist_order&a=2&c=1&type=0&code=" + code + "&pageindex=" + pageIndex;

        return s;
    }

    public static String getLink4(String code, String pageIndex) {
        return getLink4Private(code, pageIndex);
    }

    private static String getLink4Private(String code, String pageIndex) {
        String s = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=hist_fom&a=2&type=5&code="+code+"&pageindex=" + pageIndex;

        return s;
    }

}
