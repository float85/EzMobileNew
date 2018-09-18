package com.fpts.mobile.eztrading.derivative;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DataDerivative {

    private String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_PS";
    }

    private ArrayList<String> getChannelPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < code.size(); i++) {
            arrayList.add("REALTIME_DI_CODE_" + code.get(i));
            arrayList.add("REALTIME_DI_TRADE_PRICE_" + code.get(i));
            arrayList.add("REALTIME_EP_TRADE_PRICE_" + code.get(i));
            arrayList.add("0" + code.get(i));
            arrayList.add("REALTIME_DI_SUM_TRADE_QTY_" + code.get(i));
            arrayList.add("REALTIME_DI_PRICE_REF_" + code.get(i));
            arrayList.add("REALTIME_DI_PRICE_CEIL_" + code.get(i));
            arrayList.add("REALTIME_DI_PRICE_FL_" + code.get(i));
        }
        return arrayList;
    }

    private ArrayList<String> getJsonPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            arrayList.add("0");
        }

        arrayList.set(0,"VN30F1809");
        arrayList.set(8,"VN30F1810");
        arrayList.set(16,"VN30F1812");
        arrayList.set(24,"VN30F1903");

        code.add("VN30F1809");
        code.add("VN30F1810");
        code.add("VN30F1812");
        code.add("VN30F1903");

        /*String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quote_ds&a=HNXDSAll");
        JSONArray jsonArray = null;

        if (get == null || get.equals("")) {
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("CODE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("TRADE_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("CHANGE_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("CHANGE_PER"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("SUM_TRADE_QTY"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_REF"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_CEIL"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_FLOOR"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int i = 0;
            while (i < arrayList.size()) {
                code.add(arrayList.get(i));
                i = i + 8;
            }
            return arrayList;
        }*/
        return arrayList;
    }

    public ArrayList<String> code = new ArrayList<>();

    public String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    public ArrayList<String> getChannel() {
        return getChannelPrivate();
    }

    public ArrayList<String> getJson() {
        return getJsonPrivate();
    }
}
