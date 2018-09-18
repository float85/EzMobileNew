package com.fpts.mobile.eztrading.detailstock.foreignownership;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonForeignOwnership {
    public static ArrayList<String> getData(Context context, String code) {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> cache = DataForeignOwnership.getCacheData(context, code);

        String get = new HttpHandler().makeServiceCall(DataForeignOwnership.getLinkJson(code));
        if (get == null) return cache;

        try {
            JSONArray jsonArray = new JSONArray(get);
            if (jsonArray != null && jsonArray.length() > 0) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                arrayList.add("");
                arrayList.add(jsonObject.getString("qty"));
                arrayList.add("");
                arrayList.add(jsonObject.getString("TLSHNN"));
                arrayList.add(jsonObject.getString("NNMUA_YTD"));
                arrayList.add(jsonObject.getString("NNMUA_YTD30"));
                arrayList.add(jsonObject.getString("DATE"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
