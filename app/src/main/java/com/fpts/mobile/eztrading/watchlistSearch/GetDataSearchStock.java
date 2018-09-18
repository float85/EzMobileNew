package com.fpts.mobile.eztrading.watchlistSearch;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetDataSearchStock {
    public static ArrayList<String> getDataStock(Context context) {
        return getDataStockPrivate(context);
    }

    private static ArrayList<String> getDataStockPrivate(Context context) {
        ArrayList<String> list = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=codename&c=0");
        JSONArray jsonArray = null;

        ArrayList<String> listCache = DataStock.getCodeStock(context);

        if (get == null) {
            return listCache;
        } else {
            try {
                jsonArray = new JSONArray(get.replace("\n", ""));
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add("" + jsonArray.getJSONObject(i).getString("stock_code"));
                    list.add("" + jsonArray.getJSONObject(i).getString("name_vn"));
                    list.add("" + jsonArray.getJSONObject(i).getString("name_en"));
                    list.add("" + jsonArray.getJSONObject(i).getString("post_to"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
