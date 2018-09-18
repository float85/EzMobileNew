package com.fpts.mobile.eztrading.marketDetail.chart;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetJsonMarketChart {
    // TODO: TamHV 8/2/2018 9:37 PM https://eztrade3.fpts.com.vn/GateWAYDEV/realtime/?s=vnindex
    public static List<HistoryChartOtherIndex> loadDataOneDay(Context context, String marketName) {
        return loadDataOneDayPrivate(context, marketName);
    }

    private static List<HistoryChartOtherIndex> loadDataOneDayPrivate(Context context, String marketName) {
        List<HistoryChartOtherIndex> list = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataMarketChart.getLinkJsonOneDay(marketName));
        JSONArray jsonArray = null;

        if (get == null) {
            list.add(new HistoryChartOtherIndex("0", "0", "0", "0", "0", "0"));

            return list;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(new HistoryChartOtherIndex("" + jsonArray.getJSONObject(i).getString("Open"),
                            "" + jsonArray.getJSONObject(i).getString("High"),
                            "" + jsonArray.getJSONObject(i).getString("Low"),
                            "" + jsonArray.getJSONObject(i).getString("Close"),
                            "" + jsonArray.getJSONObject(i).getString("Vol"),
                            "" + jsonArray.getJSONObject(i).getString("Date")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public static List<List<HistoryChartOtherIndex>> loadDataAll(Context context, String marketName) {
        return loadDataAllPrivate(context, marketName);
    }

    private static List<List<HistoryChartOtherIndex>> loadDataAllPrivate(Context context, String marketName) {
        List<List<HistoryChartOtherIndex>> list = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataMarketChart.getLinkJsonAll(marketName));
        JSONArray jsonArray = null;

        if (get == null) {
            List<HistoryChartOtherIndex> arrayList = new ArrayList<>();
            arrayList.add(new HistoryChartOtherIndex("0", "0", "0", "0", "0", "0"));
            list = convert(arrayList);
            return list;
        } else {
            try {
                List<HistoryChartOtherIndex> arrayList = new ArrayList<>();
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(new HistoryChartOtherIndex("" + jsonArray.getJSONObject(i).getString("Open"),
                            "" + jsonArray.getJSONObject(i).getString("High"),
                            "" + jsonArray.getJSONObject(i).getString("Low"),
                            "" + jsonArray.getJSONObject(i).getString("Close"),
                            "" + jsonArray.getJSONObject(i).getString("Vol"),
                            "" + jsonArray.getJSONObject(i).getString("Date")));

                    list = convert(arrayList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public static List<List<HistoryChartOtherIndex>> getDataAll(String get) {
        return getDataAllPrivate(get);
    }

    private static List<List<HistoryChartOtherIndex>> getDataAllPrivate(String get) {
        List<List<HistoryChartOtherIndex>> list = new ArrayList<>();
        JSONArray jsonArray = null;

        if (get == null) {
            List<HistoryChartOtherIndex> arrayList = new ArrayList<>();
            arrayList.add(new HistoryChartOtherIndex("0", "0", "0", "0", "0", "0"));
            list = convert(arrayList);
            return list;
        } else {
            try {
                List<HistoryChartOtherIndex> arrayList = new ArrayList<>();
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(new HistoryChartOtherIndex("" + jsonArray.getJSONObject(i).getString("Open"),
                            "" + jsonArray.getJSONObject(i).getString("High"),
                            "" + jsonArray.getJSONObject(i).getString("Low"),
                            "" + jsonArray.getJSONObject(i).getString("Close"),
                            "" + jsonArray.getJSONObject(i).getString("Vol"),
                            "" + jsonArray.getJSONObject(i).getString("Date")));

                    list = convert(arrayList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    private static List<List<HistoryChartOtherIndex>> convert(List<HistoryChartOtherIndex> chartIndices) {
        List<List<HistoryChartOtherIndex>> superList = new ArrayList<>();
        List<HistoryChartOtherIndex>[] tem = new List[7];

        for (int i = chartIndices.size() - 1; i >= 0; i--) {
//        for (int i = 1; i < chartIndices.size(); i++) {
            if (i == chartIndices.size() - 1)
                for (int j = 0; j < tem.length; j++)
                    tem[j] = new ArrayList<>();

            if (i >= chartIndices.size() - 5) tem[0].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 5 * 4) tem[1].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 3) tem[2].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6) tem[3].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6 * 2) tem[4].add(chartIndices.get(i));
            if (i >= chartIndices.size() - 21 * 6 * 2 * 2) tem[5].add(chartIndices.get(i));
            if (i >= 0) tem[6].add(chartIndices.get(i));
        }
        for (int i = 0; i < tem.length; i++) {
            superList.add(tem[i]);
        }
        return superList;
    }

}
