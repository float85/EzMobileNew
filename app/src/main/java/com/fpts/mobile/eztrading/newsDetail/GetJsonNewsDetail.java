package com.fpts.mobile.eztrading.newsDetail;

import android.util.Log;

import com.fpts.mobile.eztrading.common.HttpHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetJsonNewsDetail {
    public static String[] loadData(String id) {
        return loadDataPrivate(id);
    }

    private static String[] loadDataPrivate(String id) {
        String get = new HttpHandler().makeServiceCall(DataNewsDetail.getLinkJson(id));

        if (get == null) {
            String[] item = new String[0];
            return item;
        } else {
            String body = get.replace("face=arial", "")
                    .replace("size=2", "")
                    .replace("size=3", "")
                    .replace("color=#000000", "")
                    .replace("color=#015396", "");

            Log.w("NewDetailPresenter", "splitChiTietTin: " + body);
            Document document = Jsoup.parse(body);
            document.body();

            String link = "";
            if (document.body().getElementsByTag("a").size() > 0) {
                link = document.body().getElementsByAttribute("href").attr("href")
                        .replace("</b></font>", "");
            }
            //document.body().getElementsByTag("a").remove().toString()
            String item[] = body.split("</b></font></P>");
            return item;
        }
    }
}
