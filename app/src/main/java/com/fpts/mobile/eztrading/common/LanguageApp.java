package com.fpts.mobile.eztrading.common;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageApp {
    private static final String FileLanguage = "LanguageApp";
    public static final int LANGUAGE_EN = 0;
    public static final int LANGUAGE_VI = 1;

    public LanguageApp(Context context) {
    }

    public static int getLanguage(Context context) {
        return getLanguagePrivate(context);
    }

    private static int getLanguagePrivate(Context context) {
        String s = FileInputAndOutputStream.readData(context, FileLanguage);
        if (s == null || s.trim().equalsIgnoreCase("")) {
            return LANGUAGE_VI;
        }
        int lan = Integer.parseInt(s);
        if (lan == LANGUAGE_EN) {
            return LANGUAGE_EN;
        }

        return LANGUAGE_VI;
    }

    public static void setLanguage(Context context) {
        String lan = getLanguagePrivate(context) == LANGUAGE_VI ? "vi" : "en";
        if (lan == "vi") {
            FileInputAndOutputStream.saveData(context, LANGUAGE_EN + "", FileLanguage);
        } else {
            FileInputAndOutputStream.saveData(context, LANGUAGE_VI + "", FileLanguage);
        }
        try {
            Locale myLocale = new Locale(lan);
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
