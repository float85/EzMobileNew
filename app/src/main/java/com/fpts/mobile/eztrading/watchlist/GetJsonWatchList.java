package com.fpts.mobile.eztrading.watchlist;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.marketOverview.DataMaket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonWatchList {
    public static ArrayList<String> getData(Context context) {
        return getDataPrivate(context);
    }

    private static ArrayList<String> getDataPrivate(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataWatchList.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataWatchList.getLinkJson(context));
        JSONArray jsonArray = null;

        if (get == null || get.equals("")) {
            for (int i = 0; i < list.size() * 3; i++) {
                arrayList.add("0");
            }
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length() -1; i++) {
                    if (jsonArray.getJSONObject(i) != null) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        arrayList.add("" + object.getString("MatchPrice"));
                        arrayList.add("" + object.getString("RefPrice"));
                        arrayList.add("" + object.getString("TotalQtty"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
