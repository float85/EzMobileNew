package com.fpts.mobile.eztrading.detailstock.trading;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonTrading {

    public static Quote getQuote(Context context, String code) {
        Quote quote = new Quote();

        Quote cache = DataTrading.getCacheQuote(code);

        String get = new HttpHandler().makeServiceCall(DataTrading.getLinkJsonQuote(code));
        JSONArray jsonArray = null;
        if (get == null)
            return cache;
        try {
            jsonArray = new JSONArray(get);
            quote.setCode("" + jsonArray.getJSONObject(0).getString("Code"));
            quote.setUpDown("" + jsonArray.getJSONObject(0).getString("UpDown"));
            quote.setMatchPrice("" + jsonArray.getJSONObject(0).getString("MatchPrice"));
            quote.setChangePrice("" + jsonArray.getJSONObject(0).getString("ChangePrice"));
            quote.setTotalQtty("" + jsonArray.getJSONObject(0).getString("TotalQtty"));
            quote.setCenterNo("" + jsonArray.getJSONObject(0).getString("CenterNo"));
            quote.setCeiling("" + jsonArray.getJSONObject(0).getString("Ceiling"));
            quote.setFloor("" + jsonArray.getJSONObject(0).getString("Floor"));
            quote.setRefPrice("" + jsonArray.getJSONObject(0).getString("RefPrice"));
            quote.setBuyPrice3("" + jsonArray.getJSONObject(0).getString("BuyPrice3"));
            quote.setBuyQtty3("" + jsonArray.getJSONObject(0).getString("BuyQtty3"));
            quote.setBuyPrice2("" + jsonArray.getJSONObject(0).getString("BuyPrice2"));
            quote.setBuyQtty2("" + jsonArray.getJSONObject(0).getString("BuyQtty2"));
            quote.setBuyPrice1("" + jsonArray.getJSONObject(0).getString("BuyPrice1"));
            quote.setBuyQtty1("" + jsonArray.getJSONObject(0).getString("BuyQtty1"));
            quote.setMatchQtty("" + jsonArray.getJSONObject(0).getString("MatchQtty"));
            quote.setSellPrice1("" + jsonArray.getJSONObject(0).getString("SellPrice1"));
            quote.setSellQtty1("" + jsonArray.getJSONObject(0).getString("SellQtty1"));
            quote.setSellPrice2("" + jsonArray.getJSONObject(0).getString("SellPrice2"));
            quote.setSellQtty2("" + jsonArray.getJSONObject(0).getString("SellQtty2"));
            quote.setSellPrice3("" + jsonArray.getJSONObject(0).getString("SellPrice3"));
            quote.setSellQtty3("" + jsonArray.getJSONObject(0).getString("SellQtty3"));
            quote.setOpenPrice("" + jsonArray.getJSONObject(0).getString("OpenPrice"));
            quote.setHighestPrice("" + jsonArray.getJSONObject(0).getString("HighestPrice"));
            quote.setLowestPrice("" + jsonArray.getJSONObject(0).getString("LowestPrice"));
            quote.setForeignBuyQtty("" + jsonArray.getJSONObject(0).getString("ForeignBuyQtty"));
            quote.setForeignSellQtty("" + jsonArray.getJSONObject(0).getString("ForeignSellQtty"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quote;
    }

    public static StockInfo getStockInfo(Context context, String code) {
        return getStockInfoPrivate(context, code);
    }

    private static StockInfo getStockInfoPrivate(Context context, String code) {
        StockInfo stockInfo = new StockInfo();

        StockInfo cache = DataTrading.getCachePrice(code);

        String get = new HttpHandler().makeServiceCall(DataTrading.getLinkJsonStockInfo(code));
        JSONArray jsonArray = null;
        if (get == null)
            return cache;
        try {
            jsonArray = new JSONArray(get);
            stockInfo.setTime("" + jsonArray.getJSONObject(0).getString("TIME"));
            stockInfo.setQty("" + jsonArray.getJSONObject(0).getString("qty"));
            stockInfo.setKldlhht("" + jsonArray.getJSONObject(0).getString("KLDLHHT"));
            stockInfo.setDate("" + jsonArray.getJSONObject(0).getString("DATE"));
            stockInfo.setCurrentPrice("" + jsonArray.getJSONObject(0).getString("CURRENT_PRICE"));
            stockInfo.setChanges("" + jsonArray.getJSONObject(0).getString("CHANGES"));
            stockInfo.setPerChange("" + jsonArray.getJSONObject(0).getString("PerCHANGE"));
            stockInfo.setMktCap("" + jsonArray.getJSONObject(0).getString("MktCap"));
            stockInfo.setBasicPrice("" + jsonArray.getJSONObject(0).getString("BASIC_PRICE"));
            stockInfo.setOpenPrice("" + jsonArray.getJSONObject(0).getString("OPEN_PRICE"));
            stockInfo.setTotalTradingQtty("" + jsonArray.getJSONObject(0).getString("TOTAL_TRADING_QTTY"));
            stockInfo.setFloor("" + jsonArray.getJSONObject(0).getString("FLOOR"));
            stockInfo.setCeiling("" + jsonArray.getJSONObject(0).getString("CEILING"));
            stockInfo.setDividentRate("" + jsonArray.getJSONObject(0).getString("DIVIDENT_RATE"));
            stockInfo.setHighestPrice("" + jsonArray.getJSONObject(0).getString("HIGHEST_PRICE"));
            stockInfo.setLowestPrice("" + jsonArray.getJSONObject(0).getString("LOWEST_PRICE"));
            stockInfo.setPriorClosePrice("" + jsonArray.getJSONObject(0).getString("PRIOR_CLOSE_PRICE"));
            stockInfo.setResEPS("" + jsonArray.getJSONObject(0).getString("RepEPS"));
            stockInfo.setDividend("" + jsonArray.getJSONObject(0).getString("Dividend"));
            stockInfo.setPE("" + jsonArray.getJSONObject(0).getString("P_E"));
            stockInfo.setEPSAdjustedSTC("" + jsonArray.getJSONObject(0).getString("EPSAdjustedSTC"));
            stockInfo.setEPSbasicFPTS("" + jsonArray.getJSONObject(0).getString("EPSbasicFPTS"));
            stockInfo.setEPSadjusted4QFPTS("" + jsonArray.getJSONObject(0).getString("EPSadjusted4QFPTS"));
            stockInfo.setPE4QFPTS("" + jsonArray.getJSONObject(0).getString("PE4QFPTS"));
            stockInfo.setTotalTradingValue("" + jsonArray.getJSONObject(0).getString("TOTAL_TRADING_VALUE"));
            stockInfo.setWk52Low("" + jsonArray.getJSONObject(0).getString("wk52Low"));
            stockInfo.setWk52High("" + jsonArray.getJSONObject(0).getString("wk52High"));
            stockInfo.setDumua("" + jsonArray.getJSONObject(0).getString("Dumua"));
            stockInfo.setDuban("" + jsonArray.getJSONObject(0).getString("Duban"));
            stockInfo.setEpsFpts("" + jsonArray.getJSONObject(0).getString("EpsFpts"));
            stockInfo.setCTMG("" + jsonArray.getJSONObject(0).getString("CTMG"));
            stockInfo.setKLGD30Days("" + jsonArray.getJSONObject(0).getString("KLGD_30_Days"));
            stockInfo.setTLSHNN("" + jsonArray.getJSONObject(0).getString("TLSHNN"));
            stockInfo.setPreCt("" + jsonArray.getJSONObject(0).getString("PreCt"));
            stockInfo.setNNMUA_YTD("" + jsonArray.getJSONObject(0).getString("NNMUA_YTD"));
            stockInfo.setNNMUA_YTD30("" + jsonArray.getJSONObject(0).getString("NNMUA_YTD30"));
            stockInfo.setPB("" + jsonArray.getJSONObject(0).getString("PB"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockInfo;
    }

    public static ArrayList<String> getArraylistChart(Context context, String code) {
        return getArraylistChartPrivate(context, code);
    }

    private static ArrayList<String> getArraylistChartPrivate(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> cache = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataTrading.getLinkJsonChart(code));
        if (get == null) {
            return cache;
        } else {
            try {
                JSONArray jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    arrayList.add(object.getString("Open"));
                    arrayList.add(object.getString("Close"));
                    arrayList.add(object.getString("High"));
                    arrayList.add(object.getString("Low"));
                    arrayList.add(object.getString("Vol"));
                    arrayList.add(object.getString("Date"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return arrayList;
    }

    public static ArrayList<String> getArraylistChartAll(Context context, String code) {
        return getArraylistChartAllPrivate(context, code);
    }

    private static ArrayList<String> getArraylistChartAllPrivate(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> cache = DataTrading.getCacheChart(code);

        String get = new HttpHandler().makeServiceCall(DataTrading.getLinkJsonChartAll(code));
        if (get == null) {
            return cache;
        } else {
            try {
                JSONArray jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    arrayList.add(object.getString("Open"));
                    arrayList.add(object.getString("Close"));
                    arrayList.add(object.getString("High"));
                    arrayList.add(object.getString("Low"));
                    arrayList.add(object.getString("Vol"));
                    arrayList.add(object.getString("Date"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static ArrayList<String> getArrayListNews(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> cache = DataTrading.getCacheNewsCompany(code);

        String get = new HttpHandler().makeServiceCall(DataTrading.getLinkJsonNewsCompany(code));
        if (get == null)
            return cache;

        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(get);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                arrayList.add(object.getString("ID"));
                arrayList.add(object.getString("Title"));
                arrayList.add(object.getString("Date"));
                arrayList.add(object.getString("Stock_code"));
                arrayList.add(object.getString("SizeInByte"));
                arrayList.add(object.getString("SizeInKB"));
                arrayList.add(object.getString("Date2"));
                arrayList.add(object.getString("FTitle"));
                arrayList.add(object.getString("Content"));
                arrayList.add(object.getString("Img"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}

