package com.fpts.mobile.eztrading.watchlistSearch;

import android.content.Context;

import com.fpts.mobile.eztrading.common.FileInputAndOutputStream;

import java.util.ArrayList;

public class DataStock {
    private static String CodeStock = "CodeStock";
    private static String CodeRecent = "CodeStockRecent";
    private static String SanUser = "SanUser";

    public static void saveStock(Context context, ArrayList<CustomerAutoComleteTextView> s) {
        saveStockPrivate(context, s);
    }

    private static void saveStockPrivate(Context context, ArrayList<CustomerAutoComleteTextView> s) {
        String s1 = "";
        for (int i = 0; i < s.size(); i++) {
            s1 = s1 + s.get(i).getStock_code() + "!=" + s.get(i).getName_vn() + "!=" + s.get(i).getName_en() + "!=" + s.get(i).getPost_to() + "@#";
        }
        FileInputAndOutputStream.saveData(context, s1, CodeStock);
    }

    public static ArrayList<String> getCodeStock(Context context) {
        return getCodeStockPrivate(context);
    }

    private static ArrayList<String> getCodeStockPrivate(Context context) {
        String s1 = FileInputAndOutputStream.readData(context, CodeStock);
        String s2 = s1.replace("\n", "");
        ArrayList<String> arrayList = null;
        if (s2.equals("")) {
            arrayList = new ArrayList<>();
            arrayList.add("AAA");
            arrayList.add("Công ty Cổ phần Nhựa và Môi trường xanh An Phát");
            arrayList.add("AN PHAT PLASTIC AND GREEN EVIRONMENT JOINT STOCK COMPANY");
            arrayList.add("HOSE");
        } else {
            arrayList = new ArrayList<>();
            String[] strings1 = s2.split("@#");
            for (int i = 0; i < strings1.length; i++) {
                String[] strings2 = strings1[i].split("!=");
                for (int j = 0; j < strings2.length; j++) {
                    arrayList.add(strings2[j]);
                }
            }
        }
        return arrayList;
    }

    public static void addCodeReCent(Context context, String code) {
        addCodeReCentPrivate(context, code);
    }

    private static void addCodeReCentPrivate(Context context, String s) {
        int iMa = 5;
        String data = FileInputAndOutputStream.readData(context, CodeRecent).replace("\n", "");

        String[] word = data.split("@#");
        for (int i = 0; i < word.length; i++) {
            if (s.equalsIgnoreCase(word[i])) {
                iMa = i;
            }
        }

        String s1 = "";
        if (word.length > 5) {
            for (int i = 0; i < 5; i++) {
                if (!(iMa == i)) {
                    if (i == 0) {
                        s1 = word[0];
                    } else {
                        s1 = s1 + "@#" + word[i];
                    }
                }
            }
        } else {
            for (int i = 0; i < word.length; i++) {
                if (!(iMa == i)) {
                    if (i == 0) {
                        s1 = word[0];
                    } else {
                        s1 = s1 + "@#" + word[i];
                    }
                }
            }
        }

        FileInputAndOutputStream.saveData(context, s + "@#" + s1, CodeRecent);
    }

    public static ArrayList<String> getCodeReCent(Context context) {
        return getCodeReCentPrivate(context);
    }

    private static ArrayList<String> getCodeReCentPrivate(Context context) {
        ArrayList<String> list = new ArrayList<>();

        String m = FileInputAndOutputStream.readData(context, CodeRecent);
        String k = m.replace("\n", "");
        if (!k.equals("")) {
            String[] s = k.split("@#");
            for (int i = 0; i < s.length; i++) {
                list.add(s[i]);
            }
        }

        return list;
    }

    public static String getLinkJson() {
        return getLinkJsonPrivete();
    }

    private static String getLinkJsonPrivete() {
        String link = "https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=0&language=1";
        return link;
    }
}
