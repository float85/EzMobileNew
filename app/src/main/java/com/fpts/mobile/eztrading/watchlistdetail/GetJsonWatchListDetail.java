package com.fpts.mobile.eztrading.watchlistdetail;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;
import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.watchlist.DataWatchList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GetJsonWatchListDetail {

    public static ArrayList<String> getData(Context context) {
        return getDataPrivate(context);
    }

    private static ArrayList<String> getDataPrivate(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();

        String get = new HttpHandler().makeServiceCall(DataWatchlistDetail.getLinkJson(context));
        JSONArray jsonArray = null;
        if (get != null) {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length() -1 ; i++) {
                    if (jsonArray.get(i) == null)
                        break;
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (object != null) {
                        arrayList.add("" + object.getString("MatchPrice"));//0
                        arrayList.add("" + object.getString("MatchQtty"));
                        arrayList.add("" + object.getString("ChangePrice"));
                        arrayList.add("" + object.getString("TotalQtty"));

                        arrayList.add("" + object.getString("BuyPrice3"));
                        arrayList.add("" + object.getString("BuyQtty3"));//5
                        arrayList.add("" + object.getString("BuyPrice2"));
                        arrayList.add("" + object.getString("BuyQtty2"));///7
                        arrayList.add("" + object.getString("BuyPrice1"));
                        arrayList.add("" + object.getString("BuyQtty1"));//9

                        arrayList.add("" + object.getString("SellPrice1"));
                        arrayList.add("" + object.getString("SellQtty1"));//11
                        arrayList.add("" + object.getString("SellPrice2"));
                        arrayList.add("" + object.getString("SellQtty2"));//13
                        arrayList.add("" + object.getString("SellPrice3"));
                        arrayList.add("" + object.getString("SellQtty3"));//15

                        arrayList.add("" + object.getString("RefPrice"));//16
                        arrayList.add("" + object.getString("Ceiling"));//17
                        arrayList.add("" + object.getString("Floor"));//18

                        arrayList.add("" + object.getString("OpenPrice"));
                        arrayList.add("" + object.getString("HighestPrice"));
                        arrayList.add("" + object.getString("LowestPrice"));

                        arrayList.add("" + object.getString("ForeignBuyQtty"));
                        arrayList.add("" + object.getString("ForeignSellQtty"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < 24; j++) {
                    arrayList.add("0");
                }
            }
        }
        return arrayList;
    }
}
