package com.fpts.mobile.eztrading.detailstock.trading;

import android.content.Context;

import java.util.ArrayList;

public class DataTrading {

    private static ArrayList<String> arrayListPrice = new ArrayList<>();
    private static ArrayList<String> arrayListChart = new ArrayList<>();
    private static ArrayList<String> arrayListNews = new ArrayList<>();

    public static String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    private static String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_QUOTES_STOCK";
    }

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

    public static String getLinkJsonChart(String code) {
        return getLinkJsonChartPrivate(code);
    }

    private static String getLinkJsonChartPrivate(String code) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/realtime/?s=" + code;
        return link;
    }

    public static String getLinkJsonChartAll(String code) {
        return getLinkJsonChartAllPrivate(code);
    }

    private static String getLinkJsonChartAllPrivate(String code) {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=" + code;
        return link;
    }

    public static String getLinkJsonNewsCompany(String code) {
        return getLinkJsonNewsCompanyPrivate(code);
    }

    private static String getLinkJsonNewsCompanyPrivate(String code) {
        return "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=company_news&language=1&symbol=" + code + "&pageindex=1&pagesize=8";
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

    public static ArrayList<String> getCacheNewsCompany(String code) {
        return getCacheNewsCompanyPrivate(code);
    }

    private static ArrayList<String> getCacheNewsCompanyPrivate(String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        return arrayList;
    }

    public static ArrayList<String> getCacheChart(String code) {
        return getCacheChartPrivate(code);
    }

    private static ArrayList<String> getCacheChartPrivate(String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        return arrayList;
    }

    // TODO: TamHV 7/24/2018 10:11 PM reTime
    public static ArrayList<String> getColumnTitleKenh(String symbol, String marketName) {
        ArrayList<String> list = new ArrayList<>();

        if(marketName.equalsIgnoreCase("HNX") || marketName.equalsIgnoreCase("UPCOME")) {
//            list.add("REALTIME_SI_" + "CODE_" + symbol);
//            list.add("REALTIME_SI_" + "PRICE_REF_" + symbol);
//            list.add("REALTIME_SI_" + "PRICE_CEIL_" + symbol);
//            list.add("REALTIME_SI_" + "PRICE_FL_" + symbol);
//            list.add("REALTIME_SI_" + "TRADE_PRICE_" + symbol);
//            list.add("REALTIME_SI_" + "TRADE_QTY_" + symbol);
//            list.add("REALTIME_SI_" + "SUM_TRADE_QTY_" + symbol);
//            list.add("REALTIME_SI_" + "OPEN_PRICE_" + symbol);
//            list.add("REALTIME_SI_" + "HIGH_PRICE_" + symbol);
//            list.add("REALTIME_SI_" + "LOW_PRICE_" + symbol);
//            list.add("REALTIME_SI_" + "FR_BUY_" + symbol);
//            list.add("REALTIME_SI_" + "FR_SELL_" + symbol);

            list.add("REALTIME_SI_" + "TRADE_PRICE_" + symbol);
            list.add("REALTIME_SI_" + "TRADE_QTY_" + symbol);
            list.add("REALTIME_SI_" + "SUM_TRADE_QTY_" + symbol);
            list.add("REALTIME_SI_" + "SUM_TRADE_QTY_" + symbol);

            String[] s1 = {"BUY_PRICE3", "BUY_QTY3", "BUY_PRICE2", "BUY_QTY2", "BUY_PRICE1", "BUY_QTY1", "SELL_PRICE1", "SELL_QTY1","SELL_PRICE2", "SELL_QTY2", "SELL_PRICE3","SELL_QTY3"};

            for (int i = 0; i < s1.length; i++) {
                list.add("REALTIME_TP_" + s1[i] + "_" + symbol);
            }

            list.add("REALTIME_SI_" + "PRICE_REF_" + symbol);
            list.add("REALTIME_SI_" + "PRICE_CEIL_" + symbol);
            list.add("REALTIME_SI_" + "PRICE_FL_" + symbol);

            list.add("REALTIME_SI_" + "OPEN_PRICE_" + symbol);
            list.add("REALTIME_SI_" + "HIGH_PRICE_" + symbol);
            list.add("REALTIME_SI_" + "LOW_PRICE_" + symbol);

            list.add("REALTIME_SI_" + "FR_BUY_" + symbol);
            list.add("REALTIME_SI_" + "FR_SELL_" + symbol);

        } else if (marketName.equalsIgnoreCase("HOSE")) {
            list.add("REALTIME_" + symbol + "_MP");
            list.add("REALTIME_" + symbol + "_MQ");
            list.add("REALTIME_" + symbol + "_MC");
            list.add("REALTIME_" + symbol + "_TQ");

            list.add("REALTIME_" + symbol + "_BP3");
            list.add("REALTIME_" + symbol + "_BQ3");
            list.add("REALTIME_" + symbol + "_BP2");
            list.add("REALTIME_" + symbol + "_BQ2");
            list.add("REALTIME_" + symbol + "_BP1");
            list.add("REALTIME_" + symbol + "_BQ1");

            list.add("REALTIME_" + symbol + "_SP1");
            list.add("REALTIME_" + symbol + "_SQ1");
            list.add("REALTIME_" + symbol + "_SP2");
            list.add("REALTIME_" + symbol + "_SQ2");
            list.add("REALTIME_" + symbol + "_SP3");
            list.add("REALTIME_" + symbol + "_SQ3");

            list.add("REALTIME_" + symbol + "_RE");
            list.add("REALTIME_" + symbol + "_CE");
            list.add("REALTIME_" + symbol + "_FL");

            list.add("REALTIME_" + symbol + "_OP");
            list.add("REALTIME_" + symbol + "_HI");
            list.add("REALTIME_" + symbol + "_LO");

            list.add("REALTIME_" + symbol + "_FB");
            list.add("REALTIME_" + symbol + "_FS");
        }

        return list;
    }

}
