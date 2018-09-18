package com.fpts.mobile.eztrading.detailstock.financial;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonFinancialOverview {

    public static ArrayList<String> getData(Context context, String code) {
        return getDataPrivate(context, code);
    }

    private static ArrayList<String> getDataPrivate(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> cache = DataFinancial.getCache(context, code);

        String get = new HttpHandler().makeServiceCall(DataFinancial.getLinkJson(code));
        if (get == null)
            return cache;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(get);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length() - 1; i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    if (object.getString("TITLE") != null &&
                            !object.getString("TITLE").trim().equalsIgnoreCase("")) {
                        arrayList.add(object.getString("ORDERBY"));
                        arrayList.add(object.getString("TITLE"));
                        arrayList.add(object.getString("TITLE_SHORT"));
                        arrayList.add(object.getString("CpnyID"));
                        arrayList.add(object.getString("stock_code"));
                        arrayList.add(object.getString("BALANCEY1"));
//                    arrayList.add(object.getString("CpnyType"));
                    }
                }
                arrayList.add(((JSONObject) jsonArray.get(jsonArray.length() - 1)).getString("CpnyType"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}
