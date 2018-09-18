package com.fpts.mobile.eztrading.events;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetJsonEvents {
    public static ArrayList<Event> loadData(Context context) {
        return loadDataPrivate(context);
    }

    private static ArrayList<Event> loadDataPrivate(Context context) {
        ArrayList<Event> list = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataEvents.getLinkJson());
        JSONArray jsonArray = null;

        if (get == null) {
            list = DataEvents.getCache(context);

            if (list == null || list.size() == 0) {
                list.add(new Event("THQ", "19146", "DRI", "Đại hội đồng cổ đông",
                        "CTCP Xây dựng Coteccons", "http://www.fpts.com.vn/VN/Lich-su-kien/Thuc-hien-quyen/?q=1", "8/6/2018 12:00:00 AM"));
            }

            return list;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(new Event("" + jsonArray.getJSONObject(i).getString("GroupNm"),
                            "" + jsonArray.getJSONObject(i).getString("ID"),
                            "" + jsonArray.getJSONObject(i).getString("stock_code"),
                            "" + jsonArray.getJSONObject(i).getString("stock_name"),
                            "" + jsonArray.getJSONObject(i).getString("Content"),
                            "" + jsonArray.getJSONObject(i).getString("url"),
                            "" + jsonArray.getJSONObject(i).getString("Date1")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
