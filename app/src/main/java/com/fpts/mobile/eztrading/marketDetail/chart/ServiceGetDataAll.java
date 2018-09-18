package com.fpts.mobile.eztrading.marketDetail.chart;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.common.ValueApp;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceGetDataAll extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS

    public ServiceGetDataAll() {
        super("ServiceStatistic");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver receiver = (ResultReceiver) intent.getParcelableExtra("NGUOI_NHAN");
        Bundle bundle = new Bundle();
        String txt = "";
        String get = new HttpHandler().makeServiceCall("https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=" + getSFromMarketNameForAll(ValueApp.intent));
        txt = get != null ? get : "";
        bundle.putString("CHUOI", txt);
        receiver.send(0, bundle);
    }

    private static String getSFromMarketNameForAll(String marketName) {

        switch (marketName.trim().toUpperCase()) {
            case "HOSE":
            case "VNINDEX":
            case "VNI":
                return "vnindex";
            case "HNX":
            case "HNXINDEX":
                return "hnxindex";
            case "UPCOM":
                return "upcomindex";
            case "VN30":
                return "vn30index";
            case "HNX30":
                return "hnx30index";
            default:
                return "";
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
