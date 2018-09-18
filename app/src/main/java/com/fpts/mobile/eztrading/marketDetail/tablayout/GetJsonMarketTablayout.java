package com.fpts.mobile.eztrading.marketDetail.tablayout;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetJsonMarketTablayout {
    public static ArrayList<TopRealtime> loadData(Context context, String c, String type) {
        return loadDataPrivate(context, c, type);
    }

    private static ArrayList<TopRealtime> loadDataPrivate(Context context, String c, String type) {
        ArrayList<TopRealtime> list = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataMarketTablayout.getLinkJson(c, type));
        JSONArray jsonArray = null;

        if (get == null) {
            list.add(new TopRealtime("0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0", "0", "0"));

            return list;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String s = "0";
                    try {
                        s = jsonArray.getJSONObject(i).getString("sAverage");
                    } catch (Exception e) {
                        s = "0";
                    }

                    list.add(new TopRealtime("" + jsonArray.getJSONObject(i).getString("sCode"),
                            "" + jsonArray.getJSONObject(i).getString("sCeiling"),
                            "" + jsonArray.getJSONObject(i).getString("sFloor"),
                            "" + jsonArray.getJSONObject(i).getString("sRefercence"),
                            "" + jsonArray.getJSONObject(i).getString("sOpen"),
                            "" + jsonArray.getJSONObject(i).getString("sClose"),
                            "" + s,
                            "" + jsonArray.getJSONObject(i).getString("sHighest"),
                            "" + jsonArray.getJSONObject(i).getString("sLowest"),
                            "" + jsonArray.getJSONObject(i).getString("sChange"),
                            "" + jsonArray.getJSONObject(i).getString("sChangePercent"),
                            "" + jsonArray.getJSONObject(i).getString("sTotalShares"),
                            "" + jsonArray.getJSONObject(i).getString("sTotalValue")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
