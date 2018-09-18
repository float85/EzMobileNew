package mobile.fpts.com.privatefpts;


import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class NetworkPresenter {
    IPrivate.INetworkView view;
    Context context;
    private static final int REQUEST_SETTINGS = 99;
    CompositeSubscription subscription = new CompositeSubscription();

    public NetworkPresenter(IPrivate.INetworkView networkReport, Context context) {
        this.view = networkReport;
        this.context = context;

        subscription.add(ReactiveNetwork.observeNetworkConnectivity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    if (connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        view.isConnected(true);
                    } else {
                        view.isConnected(false);
                    }
                }, throwable -> {
                    Log.w("NetworkPresenter", "NetworkPresenter: throwable");
                    view.isConnected(false);
                    throwable.printStackTrace();
                }, () -> {

                }));
    }

    public void onDestroy() {
        subscription.unsubscribe();
    }
}
