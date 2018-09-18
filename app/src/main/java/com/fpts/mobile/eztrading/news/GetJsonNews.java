package com.fpts.mobile.eztrading.news;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.marketOverview.DataMaket;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetJsonNews {
    public static ArrayList<NewsArticle> getData(Context context) {
        return getDataPrivate(context);
    }

    private static ArrayList<NewsArticle> getDataPrivate(Context context) {
        ArrayList<NewsArticle> arrayList = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataNews.getLinkJson());
        JSONArray jsonArray = null;

        if (get == null || get.equals("")) {
            arrayList = DataNews.getCache(context);

            if (arrayList == null || arrayList.size() == 0) {
                arrayList.add(new NewsArticle("1128648",
                        "Báo cáo tài chính công ty mẹ quý II năm 2018",
                        "30/07/3018 10:00",
                        "600",
                        "1",
                        "30/07/3018 10:00",
                        "News",
                        "Download File",
                        ""));
            }
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get.replace(" \"", "\""));
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(new NewsArticle("" + jsonArray.getJSONObject(i).getString("ID"),
                            "" + jsonArray.getJSONObject(i).getString("Title"),
                            "" + jsonArray.getJSONObject(i).getString("Date"),
                            "" + jsonArray.getJSONObject(i).getString("SizeInByte"),
                            "" + jsonArray.getJSONObject(i).getString("SizeInKB"),
                            "" + jsonArray.getJSONObject(i).getString("Date2"),
                            "" + jsonArray.getJSONObject(i).getString("FTitle"),
                            "" + jsonArray.getJSONObject(i).getString("Content"),
                            "" + jsonArray.getJSONObject(i).getString("Img")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
