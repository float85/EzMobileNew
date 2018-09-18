package com.fpts.mobile.eztrading.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class ValueApp {
    public enum ENUM_VT {
        FROM_MAIN,
        FROM_WATCHLIST_DETAIL,
        FROM_WATCHLIST_DETAIL_TRADING,
        FROM_MARKETOVERVIEW_DETAIL,
        FROM_EVENTS,
        FROM_NEWS,
        FROM_WORLDINDEXES,
    }

    public static ENUM_VT vt = ENUM_VT.FROM_MAIN;
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static FragmentTransaction fragmentTransaction;
    public static String intent;

    public static Context contextHome;
    public static Activity activityHome;

    public static Context contextPS;
    public static Activity activityPS;

    public static Context contextMarketDetail;
    public static Activity activityMarketDetail;

    public static Context contextMarketDetailTablayout;
    public static Activity activityMarketDetailTablayout;

    public static Context contextChart;
    public static Activity activityChart;

    public static Context contextSearch;
    public static Activity activitySearch;

    public static Context contextStockDetail;
    public static Activity activityStockDetail;

    public static Context contextStockDetailForeignship;
    public static Activity activityStockDetailForeignship;

    public static Context contextStockDetailStatistics;
    public static Activity activityStockDetailStatistics;

    public static Context contextChartFragment;
    public static Activity activityChartFragment;

    public static Context contextWorldIndexesFragment;
    public static Activity activityWorldIndexesFragment;

    public static Activity activityWatchlistDetailFragment;
    public static Context contextWatchlistDetailFragment;
    public static FragmentTransaction fragmentTransactionWatchlistDetailFragment;

    public static FragmentTransaction fragmentTransactionStockDetailTradingFragment;
}
