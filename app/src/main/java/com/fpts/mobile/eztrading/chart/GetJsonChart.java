package com.fpts.mobile.eztrading.chart;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.marketDetail.chart.DataMarketChart;
import com.fpts.mobile.eztrading.marketDetail.chart.HistoryChartOtherIndex;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetJsonChart {
    public static List<HistoryChartOtherIndex> loadDataAll(Context context, String marketName) {
        return loadDataAllPrivate(context, marketName);
    }

    private static List<HistoryChartOtherIndex> loadDataAllPrivate(Context context, String marketName) {
        List<HistoryChartOtherIndex> arrayList = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=" + marketName);
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new HistoryChartOtherIndex("0", "0", "0", "0", "0", "0"));
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(new HistoryChartOtherIndex("" + jsonArray.getJSONObject(i).getString("Open"),
                            "" + jsonArray.getJSONObject(i).getString("High"),
                            "" + jsonArray.getJSONObject(i).getString("Low"),
                            "" + jsonArray.getJSONObject(i).getString("Close"),
                            "" + jsonArray.getJSONObject(i).getString("Vol"),
                            "" + jsonArray.getJSONObject(i).getString("Date")));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
