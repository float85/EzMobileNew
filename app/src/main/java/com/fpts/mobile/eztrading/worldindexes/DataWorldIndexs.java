package com.fpts.mobile.eztrading.worldindexes;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.common.ValueApp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DataWorldIndexs {

    private Context context;

    public DataWorldIndexs() {
        this.context = ValueApp.contextHome;
    }

    public ArrayList<String> getJson() {
        return getJsonPrivate();
    }

    private ArrayList<String> getJsonPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=world_indices");
        JSONArray jsonArray = null;

        if (get == null || get.equals("")) {
            for (int i = 0; i < 25; i++) {
                arrayList.add("0");
            }
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("title"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("price"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("change"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("changePct"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
