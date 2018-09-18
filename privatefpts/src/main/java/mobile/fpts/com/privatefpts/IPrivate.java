package mobile.fpts.com.privatefpts;

import java.util.List;

import mobile.fpts.com.privatefpts.model.ClientBalanceDetails;
import mobile.fpts.com.privatefpts.model.StockQuote;

public interface IPrivate {
    interface ILogin {
        void onLoginSucscess(String mes);

        void onLoginFail(String mes);

        void updateUI();
    }

    interface INetworkView {
        void isConnected(boolean connected);
    }

    interface ICheckUpdate {

        void onNoUpdate();
    }

    interface IOrder {
        void OrderSucscess();

        void OrderError(String mes);

        void updateUI(StockQuote stockQuote, ClientBalanceDetails clientBalanceDetails, List<String> list);

        void ClearUI();
    }
}
