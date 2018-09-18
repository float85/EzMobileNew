package com.fpts.mobile.eztrading.marketOverview;

import android.content.Context;
import android.widget.Toast;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;
import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.common.ValueApp;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class DataMaket {

    private Context context;

    public DataMaket() {
        this.context = ValueApp.contextHome;
    }

    private static String CodeMarket = "CodeMarket";

    private ArrayList<String> getCodePrivate() {
        String s = FileInputAndOutputStream.readData(context, CodeMarket).replace("\n", "");
        String s1 = s.replace("\n", "");
        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < arrayList1.size(); i++) {
            if (arrayList1.get(i) != null && !arrayList1.get(i).equalsIgnoreCase(""))
                arrayList.add(arrayList1.get(i));
        }

        if (arrayList == null || arrayList.size() == 0) {
            arrayList = new ArrayList<>();
            arrayList.add("VNI");
            arrayList.add("HNXIndex");
            arrayList.add("UpCom");
            arrayList.add("VN30");
            arrayList.add("HNX30");

            addCodePrivate(context, "VNI");
            addCodePrivate(context, "HNXIndex");
            addCodePrivate(context, "UpCom");
            addCodePrivate(context, "VN30");
            addCodePrivate(context, "HNX30");
        }
        return arrayList;
    }

    private void deleteCodePrivate(String code) {
        String m = FileInputAndOutputStream.readData(context, code);
        String k = m.replace("\n", "");
        ArrayList<String> arrayListOld = new ArrayList<>();
        if (!k.equals("")) {
            arrayListOld = new ArrayList<>(Arrays.asList(k.split(",")));
        }
        String c = "";
        for (int i = 0; i < arrayListOld.size(); i++) {
            if (!code.equals(arrayListOld.get(i))) {
                c = c + arrayListOld.get(i) + ",";
            }
        }
        FileInputAndOutputStream.saveData(context, c, code);
    }

    private ArrayList<String> getChannelPrivete() {
        ArrayList<String> arrayList = getCodePrivate();
        ArrayList<String> arrayListNew = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (String.valueOf(arrayList.get(i).charAt(0)).equalsIgnoreCase("v")) {
                arrayListNew.add("REALTIME_" + "MO");
                arrayListNew.add("REALTIME_" + arrayList.get(i).toUpperCase() + "_INDEXVALUE");
                arrayListNew.add("REALTIME_" + arrayList.get(i).toUpperCase() + "_CHANGE");
                arrayListNew.add("REALTIME_" + arrayList.get(i).toUpperCase() + "_CHANGEPERCENT");
                arrayListNew.add("REALTIME_" + arrayList.get(i).toUpperCase() + "_TOTALVALUE");
            } else {
                if (arrayList.get(i).equalsIgnoreCase("upcom")) {
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_11_" + "HNXUPCOMINDEX");
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_VALUE_" + "HNXUPCOMINDEX");
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_CHANGE_" + "HNXUPCOMINDEX");
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_RATIO_CHG_" + "HNXUPCOMINDEX");
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_VAL_" + "HNXUPCOMINDEX");
                } else {
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_11_" + arrayList.get(i).toUpperCase());
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_VALUE_" + arrayList.get(i).toUpperCase());
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_CHANGE_" + arrayList.get(i).toUpperCase());
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_RATIO_CHG_" + arrayList.get(i).toUpperCase());
                    arrayListNew.add("REALTIME_INDEX_HA_I" + "_TOTAL_VAL_" + arrayList.get(i).toUpperCase());
                }
            }
        }
        return arrayListNew;
    }

    private String getLinkSocketPrivate() {
        return "http://livedata.fpts.com.vn/REALTIME_INDEX";
    }

    private ArrayList<String> getJsonPrivate() {
        ArrayList<String> arrayList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(getCode());

        String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=0&language=1");
        JSONArray jsonArray = null;

        if (get == null || get.equals("")) {
            int count = 0;
            for (int i = 0; i < list.size() * 5; i++) {
                if (i % 5 == 0) {
                    arrayList.add(list.get(count));
                    count = count + 1;
                } else {
                    arrayList.add("0");
                }
            }
            return arrayList;
        } else {
            try {
                jsonArray = new JSONArray(get.replace(" \"", "\""));
                for (int j = 0; j < list.size(); j++) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (list.get(j).equalsIgnoreCase("" + jsonArray.getJSONObject(i).getString("INDEX"))) {
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("INDEX"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("IndexValue"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("Change"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("ChangePercent"));
                            arrayList.add("" + jsonArray.getJSONObject(i).getString("TotalValue"));
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }

    public ArrayList<String> getCode() {
        return getCodePrivate();
    }

    public void deleteCode(String code) {
        deleteCodePrivate(code);
    }

    public ArrayList<String> getChannel() {
        return getChannelPrivete();
    }

    public String getLinkSocket() {
        return getLinkSocketPrivate();
    }

    public ArrayList<String> getJson() {
        return getJsonPrivate();
    }

    private static void addCodePrivate(Context context, String symbol) {
        String codeMarket = FileInputAndOutputStream.readData(context, CodeMarket);
        if (codeMarket.contains(symbol)) {
        } else {
            String codeMarket1 = codeMarket.replace("\n", "");
            FileInputAndOutputStream.saveData(context, codeMarket1 + "," + symbol, CodeMarket);
        }
    }

}