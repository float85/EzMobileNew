package com.fpts.mobile.eztrading.detailstock.statistics;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.detailstock.trading.DataTrading;
import com.fpts.mobile.eztrading.detailstock.trading.Quote;
import com.fpts.mobile.eztrading.detailstock.trading.StockInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonStatistics {
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

        return arrayList;
    }

    public static ArrayList<String> getArraylistChartAll(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();


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

    public static ArrayList<DataStatisticsPrice> getStatisticsPrice(Context context, String code, String pageIndex) {
        return getStatisticsPricePrivate(context, code, pageIndex);
    }

    private static ArrayList<DataStatisticsPrice> getStatisticsPricePrivate(Context context, String code, String pageIndex) {
        ArrayList<DataStatisticsPrice> list = new ArrayList<>();

//        StockInfo cache = DataTrading.getCachePrice(code);

        String get = new HttpHandler().makeServiceCall(DataStatistics.getLinkStatisticsPrice(code, pageIndex));
        JSONArray jsonArray = null;
        if (get == null)
            return null;
        try {
            jsonArray = new JSONArray(get);
            for (int i = 1; i < jsonArray.length(); i++) {
                list.add(new DataStatisticsPrice("" + jsonArray.getJSONObject(i).getString("ID"),
                        "" + jsonArray.getJSONObject(i).getString("TranDate"),
                        "" + jsonArray.getJSONObject(i).getString("TranDateUS"),
                        "" + jsonArray.getJSONObject(i).getString("StockSymbol"),
                        "" + jsonArray.getJSONObject(i).getString("Ceiling"),
                        "" + jsonArray.getJSONObject(i).getString("Floor"),
                        "" + jsonArray.getJSONObject(i).getString("Basic"),
                        "" + jsonArray.getJSONObject(i).getString("Open"),
                        "" + jsonArray.getJSONObject(i).getString("Close"),
                        "" + jsonArray.getJSONObject(i).getString("Highest"),
                        "" + jsonArray.getJSONObject(i).getString("Lowest"),
                        "" + jsonArray.getJSONObject(i).getString("ChangePrice"),
                        "" + jsonArray.getJSONObject(i).getString("PercentPrice"),
                        "" + jsonArray.getJSONObject(i).getString("AP"),
                        "" + jsonArray.getJSONObject(i).getString("NTQ"),
                        "" + jsonArray.getJSONObject(i).getString("NTV"),
                        "" + jsonArray.getJSONObject(i).getString("PTQ"),
                        "" + jsonArray.getJSONObject(i).getString("PTV"),
                        "" + jsonArray.getJSONObject(i).getString("TTQ"),
                        "" + jsonArray.getJSONObject(i).getString("TTV"),
                        "" + jsonArray.getJSONObject(i).getString("Status")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<DataPlacingOrder> getPlacingOrder(Context context, String code, String pageIndex) {
        return getPlacingOrderPrivate(context, code, pageIndex);
    }

    private static ArrayList<DataPlacingOrder> getPlacingOrderPrivate(Context context, String code, String pageIndex) {
        ArrayList<DataPlacingOrder> list = new ArrayList<>();

//        StockInfo cache = DataTrading.getCachePrice(code);

        String get = new HttpHandler().makeServiceCall(DataStatistics.getLinkPlacingOder(code, pageIndex));
        JSONArray jsonArray = null;
        if (get == null)
            return null;
        try {
            jsonArray = new JSONArray(get);
            for (int i = 1; i < jsonArray.length(); i++) {
                list.add(new DataPlacingOrder("" + jsonArray.getJSONObject(i).getString("ID"),
                        "" + jsonArray.getJSONObject(i).getString("TRADING_DATE"),
                        "" + jsonArray.getJSONObject(i).getString("TRADING_DATE_US"),
                        "" + jsonArray.getJSONObject(i).getString("CODE"),
                        "" + jsonArray.getJSONObject(i).getString("BID_COUNT"),
                        "" + jsonArray.getJSONObject(i).getString("TOTAL_BID_QTTY"),
                        "" + jsonArray.getJSONObject(i).getString("OFFER_COUNT"),
                        "" + jsonArray.getJSONObject(i).getString("TOTAL_OFFER_QTTY"),
                        "" + jsonArray.getJSONObject(i).getString("BID_OFFER"),
                        "" + jsonArray.getJSONObject(i).getString("CEILING_PRICE"),
                        "" + jsonArray.getJSONObject(i).getString("FLOOR_PRICE"),
                        "" + jsonArray.getJSONObject(i).getString("BASIC_PRICE"),
                        "" + jsonArray.getJSONObject(i).getString("BEST_BID_PRICE"),
                        "" + jsonArray.getJSONObject(i).getString("BEST_BID_QTTY"),
                        "" + jsonArray.getJSONObject(i).getString("BEST_OFFER_PRICE"),
                        "" + jsonArray.getJSONObject(i).getString("BEST_OFFER_QTTY"),
                        "" + jsonArray.getJSONObject(i).getString("Status")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Data4> get4(Context context, String code, String pageIndex) {
        return get4Private(context, code, pageIndex);
    }

    private static ArrayList<Data4> get4Private(Context context, String code, String pageIndex) {
        ArrayList<Data4> list = new ArrayList<>();

//        StockInfo cache = DataTrading.getCachePrice(code);

        String get = new HttpHandler().makeServiceCall(DataStatistics.getLink4(code, pageIndex));
        JSONArray jsonArray = null;
        if (get == null)
            return null;
        try {
            jsonArray = new JSONArray(get);
            for (int i = 1; i < jsonArray.length(); i++) {
                list.add(new Data4("" + jsonArray.getJSONObject(i).getString("IDX"),
                        "" + jsonArray.getJSONObject(i).getString("Date"),
                        "" + jsonArray.getJSONObject(i).getString("Code"),
                        "" + jsonArray.getJSONObject(i).getString("CURRENT_VOL"),
                        "" + jsonArray.getJSONObject(i).getString("CURRENT_PER"),
                        "" + jsonArray.getJSONObject(i).getString("CURRENT_REM"),
                        "" + jsonArray.getJSONObject(i).getString("BUY_VOL"),
                        "" + jsonArray.getJSONObject(i).getString("BUY_PER"),
                        "" + jsonArray.getJSONObject(i).getString("BUY_VAL"),
                        "" + jsonArray.getJSONObject(i).getString("BUY_VALPER"),
                        "" + jsonArray.getJSONObject(i).getString("SELL_VOL"),
                        "" + jsonArray.getJSONObject(i).getString("SELL_PER"),
                        "" + jsonArray.getJSONObject(i).getString("SELL_VAL"),
                        "" + jsonArray.getJSONObject(i).getString("SELL_VALPER")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

