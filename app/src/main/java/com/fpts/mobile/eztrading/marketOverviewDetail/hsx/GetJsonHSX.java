package com.fpts.mobile.eztrading.marketOverviewDetail.hsx;

import android.content.Context;
import android.util.Log;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetJsonHSX {
    public static ArrayList<String> getData(Context context) {
        return getDataPrivate(context);
    }

    private static ArrayList<String> getDataPrivate(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketHSX.getCode());

        ArrayList<String> listCache = DataMarketHSX.getCache(context);

        String get = new HttpHandler().makeServiceCall(DataMarketHSX.getLinkJson());
        JSONArray jsonArray = null;

        if (get == null) {
            return listCache;
        } else {
            Log.w("GetJsonHSX", "getDataPrivate: " + get);
            try {
                jsonArray = new JSONArray(get.replace(" \"", "\""));
                for (int j = 0; j < list.size(); j++) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (list.get(j).equalsIgnoreCase("" + jsonArray.getJSONObject(i).getString("INDEX"))) {
//                            arrayList.add("" + jsonArray.getJSONObject(i).getString("INDEX"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("IndexValue"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("Change"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("ChangePercent"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("TotalValue"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("TotalQtty"));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}