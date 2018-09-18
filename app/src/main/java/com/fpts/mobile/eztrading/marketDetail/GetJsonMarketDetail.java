package com.fpts.mobile.eztrading.marketDetail;

import android.content.Context;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeHNX;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeHNX30;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeItemVNI;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeUpcom;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeVNI;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeVNI30;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GetJsonMarketDetail {
    public static ArrayList<DetailHomeVNI> getHoData(Context context, String s) {
        return getDataHoPrivate(context, s);
    }

    public static ArrayList<DetailHomeItemVNI> getItemHoData(Context context, String s) {
        return getDataItemHoPrivate(context, s);
    }

    private static ArrayList<DetailHomeVNI> getDataHoPrivate(Context context, String s) {
        ArrayList<DetailHomeVNI> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeVNI("0", "0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0",
                    "0", "0", "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 1; i < jsonArray.length(); i++) {
                    arrayList.add(new DetailHomeVNI("" + jsonArray.getJSONObject(0).getString("date"),
                            "" + jsonArray.getJSONObject(0).getString("strStatus"),
                            "" + jsonArray.getJSONObject(0).getString("HO_MARKET_INDEX"),
                            "" + jsonArray.getJSONObject(0).getString("strArrow"),
                            "" + jsonArray.getJSONObject(0).getString("HO_CHG_INDEX"),
                            "" + jsonArray.getJSONObject(0).getString("HO_PCT_INDEX"),
                            "" + jsonArray.getJSONObject(0).getString("HO_TOTAL_QTTY"),
                            "" + jsonArray.getJSONObject(0).getString("HO_TOTAL_VALUE"),
                            "" + jsonArray.getJSONObject(0).getString("HO_TOTAL_TRADE"),
                            "" + jsonArray.getJSONObject(0).getString("HO_ADVANCES"),
                            "" + jsonArray.getJSONObject(0).getString("HO_NOCHANGE"),
                            "" + jsonArray.getJSONObject(0).getString("HO_DECLINES"),
                            "" + jsonArray.getJSONObject(0).getString("HO_PT_TOTAL_QTTY"),
                            "" + jsonArray.getJSONObject(0).getString("HO_PT_TOTAL_VALUE"),
                            "" + jsonArray.getJSONObject(0).getString("HO_PT_TOTAL_TRADE")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }

    private static ArrayList<DetailHomeItemVNI> getDataItemHoPrivate(Context context, String s) {
        ArrayList<DetailHomeItemVNI> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeItemVNI("0", "0", "0", "0", "0", "0",
                    "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                for (int i = 1; i < jsonArray.length(); i++) {
                    arrayList.add(new DetailHomeItemVNI("" + jsonArray.getJSONObject(i).getString("MARKET_INDEX"),
                            "" + jsonArray.getJSONObject(i).getString("strArrow0"),
                            "" + jsonArray.getJSONObject(i).getString("CHG_INDEX"),
                            "" + jsonArray.getJSONObject(i).getString("PCT_INDEX"),
                            "" + jsonArray.getJSONObject(i).getString("TOTAL_QTTY"),
                            "" + jsonArray.getJSONObject(i).getString("TOTAL_VALUE"),
                            "" + jsonArray.getJSONObject(i).getString("TOTAL_TRADE")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

    }

    public static ArrayList<DetailHomeHNX> getHaData(Context context, String s) {
        return getDataHaPrivate(context, s);
    }

    private static ArrayList<DetailHomeHNX> getDataHaPrivate(Context context, String s) {
        ArrayList<DetailHomeHNX> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeHNX("0", "0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0",
                    "0", "0", "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                arrayList.add(new DetailHomeHNX("" + jsonArray.getJSONObject(0).getString("date"),
                        "" + jsonArray.getJSONObject(0).getString("statusSub"),
                        "" + jsonArray.getJSONObject(0).getString("Index"),
                        "" + jsonArray.getJSONObject(0).getString("strArrow"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change_per"),
                        "" + jsonArray.getJSONObject(0).getString("total_qty"),
                        "" + jsonArray.getJSONObject(0).getString("total_val"),
                        "" + jsonArray.getJSONObject(0).getString("total_trade"),
                        "" + jsonArray.getJSONObject(0).getString("count_inc"),
                        "" + jsonArray.getJSONObject(0).getString("count_equal"),
                        "" + jsonArray.getJSONObject(0).getString("count_down"),
                        "" + jsonArray.getJSONObject(0).getString("KL_Khop"),
                        "" + jsonArray.getJSONObject(0).getString("GT_Khop"),
                        "" + jsonArray.getJSONObject(0).getString("SoGD_GDTTCP")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }

    public static ArrayList<DetailHomeUpcom> getUpData(Context context, String s) {
        return getDataUpPrivate(context, s);
    }

    private static ArrayList<DetailHomeUpcom> getDataUpPrivate(Context context, String s) {
        ArrayList<DetailHomeUpcom> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeUpcom("0", "0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0",
                    "0", "0", "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                arrayList.add(new DetailHomeUpcom("" + jsonArray.getJSONObject(0).getString("date"),
                        "" + jsonArray.getJSONObject(0).getString("statusSub"),
                        "" + jsonArray.getJSONObject(0).getString("Index"),
                        "" + jsonArray.getJSONObject(0).getString("strArrow"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change_per"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_KL"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_GT"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_GiaoDich"),
                        "" + jsonArray.getJSONObject(0).getString("SoMa_Tang"),
                        "" + jsonArray.getJSONObject(0).getString("SoMa_Khongdoi"),
                        "" + jsonArray.getJSONObject(0).getString("SoMa_Giam"),
                        "" + jsonArray.getJSONObject(0).getString("KLKhop_GDTTCP"),
                        "" + jsonArray.getJSONObject(0).getString("GTKhop_GDTTCP"),
                        "" + jsonArray.getJSONObject(0).getString("SoGD_GDTTCP")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }

    public static ArrayList<DetailHomeVNI30> getvni30Data(Context context, String s) {
        return getDatavni30Private(context, s);
    }

    private static ArrayList<DetailHomeVNI30> getDatavni30Private(Context context, String s) {
        ArrayList<DetailHomeVNI30> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeVNI30("0", "0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                arrayList.add(new DetailHomeVNI30("" + jsonArray.getJSONObject(0).getString("date"),
                        "" + jsonArray.getJSONObject(0).getString("strStatus"),
                        "" + jsonArray.getJSONObject(0).getString("Index"),
                        "" + jsonArray.getJSONObject(0).getString("strArrow"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change_per"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_KL"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_GT"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_tangtran"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_tang"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_khongdoi"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_giam")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }

    public static ArrayList<DetailHomeHNX30> getHnx30Data(Context context, String s) {
        return getDataHnx30Private(context, s);
    }

    private static ArrayList<DetailHomeHNX30> getDataHnx30Private(Context context, String s) {
        ArrayList<DetailHomeHNX30> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(DataMarketDetail.getCode(context));

        String get = new HttpHandler().makeServiceCall(DataMarketDetail.getLinkJson(s));
        JSONArray jsonArray = null;

        if (get == null) {
            arrayList.add(new DetailHomeHNX30("0", "0", "0", "0", "0", "0",
                    "0", "0", "0", "0", "0", "0"));

            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get);
                arrayList.add(new DetailHomeHNX30("" + jsonArray.getJSONObject(0).getString("date"),
                        "" + jsonArray.getJSONObject(0).getString("statusSub"),
                        "" + jsonArray.getJSONObject(0).getString("Index"),
                        "" + jsonArray.getJSONObject(0).getString("strArrow"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change"),
                        "" + jsonArray.getJSONObject(0).getString("Index_change_per"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_KL"),
                        "" + jsonArray.getJSONObject(0).getString("Tong_GT"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_tangtran"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_tang"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_khongdoi"),
                        "" + jsonArray.getJSONObject(0).getString("Soma_giam")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
