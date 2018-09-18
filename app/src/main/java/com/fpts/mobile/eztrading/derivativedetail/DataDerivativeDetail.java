package com.fpts.mobile.eztrading.derivativedetail;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;
import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.common.ValueApp;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DataDerivativeDetail {

    private Context context;

    public DataDerivativeDetail() {
        this.context = ValueApp.contextHome;
    }

    private String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn:3000/REALTIME_PS";
    }

    private ArrayList<String> getJsonPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();
        code = new ArrayList<>();

        for (int i = 0; i < 26*4; i++) {
            arrayList.add("0");
        }

        code.add("VN30F1809");
        code.add("VN30F1810");
        code.add("VN30F1812");
        code.add("VN30F1903");

            /*String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quote_ds&a=HNXDSAll");
        if (get != null || !get.equals("[]\n")) {
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(get);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    code.add("" + jsonArray.getJSONObject(i).getString("CODE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("TRADE_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("TRADE_QTY"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("CHANGE_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("SUM_TRADE_QTY"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("OPEN_QTY"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_BUY3"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_BUY3"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_BUY2"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_BUY2"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_BUY1"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_BUY1"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_SELL1"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_SELL1"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_SELL2"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_SELL2"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_SELL3"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("QTY_SELL3"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_REF"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_CEIL"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("PRICE_FLOOR"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("OPEN_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("HI_PRICE"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("LO_PRICE"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("FR_BUY"));
                    arrayList.add("" + jsonArray.getJSONObject(i).getString("FR_SELL"));

                    arrayList.add("" + jsonArray.getJSONObject(i).getString("DATE_CLOSE"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } else {
            for (int i = 0; i < 4 * 26; i++) {
                arrayList.add("0");
            }
        }*/
        return arrayList;
    }

    private ArrayList<String> getChannelPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < code.size(); i++) {
            arrayList.add("REALTIME_DI_TRADE_PRICE_" + code.get(i));
            arrayList.add("REALTIME_DI_TRADE_QTY_" + code.get(i));
            arrayList.add("REALTIME_EP_TRADE_PRICE_" + code.get(i));
            arrayList.add("REALTIME_DI_SUM_TRADE_QTY_" + code.get(i));
            arrayList.add("REALTIME_DI_IO_QTY_" + code.get(i));

            arrayList.add("REALTIME_TP_BUY_PRICE3_" + code.get(i));
            arrayList.add("REALTIME_TP_BUY_QTY3_" + code.get(i));
            arrayList.add("REALTIME_TP_BUY_PRICE2_" + code.get(i));
            arrayList.add("REALTIME_TP_BUY_QTY2_" + code.get(i));
            arrayList.add("REALTIME_TP_BUY_PRICE1_" + code.get(i));
            arrayList.add("REALTIME_TP_BUY_QTY1_" + code.get(i));

            arrayList.add("REALTIME_TP_SELL_PRICE1_" + code.get(i));
            arrayList.add("REALTIME_TP_SELL_QTY1_" + code.get(i));
            arrayList.add("REALTIME_TP_SELL_PRICE2_" + code.get(i));
            arrayList.add("REALTIME_TP_SELL_QTY2_" + code.get(i));
            arrayList.add("REALTIME_TP_SELL_PRICE3_" + code.get(i));
            arrayList.add("REALTIME_TP_SELL_QTY3_" + code.get(i));

            arrayList.add("REALTIME_DI_PRICE_REF_" + code.get(i));
            arrayList.add("REALTIME_DI_PRICE_CEIL_" + code.get(i));
            arrayList.add("REALTIME_DI_PRICE_FL_" + code.get(i));

            arrayList.add("REALTIME_DI_OPEN_PRICE_" + code.get(i));
            arrayList.add("REALTIME_DI_HIGH_PRICE_" + code.get(i));
            arrayList.add("REALTIME_DI_LOW_PRICE_" + code.get(i));

            arrayList.add("REALTIME_DI_FR_BUY_" + code.get(i));
            arrayList.add("REALTIME_DI_FR_SELL_" + code.get(i));

            arrayList.add("REALTIME_DI_DATE_CLOSE_" + code.get(i));
        }
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
