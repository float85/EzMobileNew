package mobile.fpts.com.privatefpts.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import mobile.fpts.com.privatefpts.R;
import mobile.fpts.com.privatefpts.model.ClientBalanceDetails;
import mobile.fpts.com.privatefpts.model.StockQuote;


public class MethodUtils {

    final static DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.ENGLISH);
    final static DecimalFormat numberformatter = new DecimalFormat("###,###,###,###", symbol);
    public static final int MARPRO = 3;
    public static final int MAR = 2;
    public static final int NORMAL = 1;
    public static final String HOSE_EXCHANGE = "HSX";
    public static final String HNX_NY_EXCHANGE = "HNX.LISTED";
    public static final String HNX_UPCOM_EXCHANGE = "HNX.UPCOM";
    public final static String URL_GATEWAY_2018 = "http://gateway.fpts.com.vn/g5g/fpts/";
    public final static String WS_SOAP_NAMESPACE = "http://fpts.com.vn/EzGateway/";

    public static String formatNumber(String _number) {
        String num = "0";
        try {
            double number = Double.parseDouble(_number);
            num = numberformatter.format(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public static String convertPass(String pass) {
        String str = pass.replace("&", "&amp;");
        return str.replace("<", "&lt;");
    }

    public static void toast(Context context, String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static AlertDialog initConfirmDialog(Context context, String message, DialogInterface.OnClickListener confirmClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Thông Báo")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Đồng ý", confirmClick);
        return builder.show();
    }

    public static String setVolMax(StockQuote quote, ClientBalanceDetails details, String price, int toBuy) {
        /**
         * toBuy =      1 : tk thường
         *              2 : tk Mar thường
         *              3: Marpro
         *
         * 	Lệnh mua thường: <max> = Số dư tiền / [Giá x (1 + 0.15%)]
         *  Lệnh mua ký quỹ (Mar thường): <max> = Min {Số dư tiền / [Giá x (1- TLV + 0.15%)], Hạn mức / Giá}
         * Lệnh mua Marpro:<max> = Min {SMCS / [Giá x (1 - TLV + 0.15%)], Hạn mức / [Giá x (1 + 0.15%)]}        *
         *
         * clientBalanceDetails.getSDCTGD(); // sức mua cơ sở
         *  clientBalanceDetails.getRemainingQuota();// hạn mức
         *   stockQuote.getCeiling(); // giá = giá trần
         *     stockQuote.getRateMar(); // TLV = tỷ lệ vay
         */
        int volMax02, volMax01 = 0;

        switch (toBuy) {
            case 1:
                volMax01 = (int) (Integer.valueOf(details.getSDCTGD()) /
                        ((Double.parseDouble(price) * 1000) * 1.0015));
                break;
            case 2:
                volMax01 = (int) (Integer.parseInt(details.getSDCTGD()) /
                        ((Double.parseDouble(price) * 1000) * (1 - (Float.parseFloat(quote.getRateMar()) / 100) + 0.0015)));

                volMax02 = (Integer.valueOf(details.getRemainingQuota()) /
                        (Integer.parseInt(price) * 1000));

                if (volMax01 > volMax02) volMax01 = volMax02;
                break;
            case 3:
                volMax02 = (int) (Integer.valueOf(details.getRemainingQuota()) /
                        ((Double.parseDouble(price) * 1000) * 1.0015));
                volMax01 = (int) (Integer.parseInt(details.getSDCTGD()) /
                        ((Double.parseDouble(price) * 1000) * (1 - (Float.parseFloat(quote.getRateMar()) / 100) + 0.0015)));
                if (volMax01 > volMax02) volMax01 = volMax02;
                break;
        }

        if (quote.getExchange().equals(HOSE_EXCHANGE)) {
            if (volMax01 > 10)
                volMax01 = Integer.parseInt(String.valueOf(volMax01).substring(0, String.valueOf(volMax01).length() - 1)) * 10;
            else volMax01 = 0;
        } else if (quote.getExchange().equals(HNX_NY_EXCHANGE) || quote.getExchange().equals(HNX_UPCOM_EXCHANGE)) {
            if (volMax01 >= 100)
                volMax01 = Integer.parseInt(String.valueOf(volMax01).substring(0, String.valueOf(volMax01).length() - 2)) * 100;
        }
        return String.valueOf(volMax01);
    }

    public static String setNextQuantity(String quantity, String price, String maxQuantity, String stock) {
        String newQuantity;
        int volQuantity = Integer.valueOf(quantity.replace(",", ""));
        int volmaxQuantity = Integer.valueOf(maxQuantity.replace(",", ""));

        // bắt chưa nhập giá
        if (price.equals("") || price.equals("0")) {
            if (quantity.equals("") || quantity.equals("0")) newQuantity = "10";
            else {
                if (stock.substring(0, stock.indexOf(" -")).equals(MethodUtils.HOSE_EXCHANGE)) {
                    if (volQuantity >= 10) {
                        volQuantity = Integer.parseInt(String.valueOf(volQuantity).substring(0, String.valueOf(volQuantity).length() - 1)) * 10;
                        newQuantity = String.valueOf(volQuantity + 10);
                    } else newQuantity = "10";
                } else {
                    if (volQuantity < 100)
                        newQuantity = String.valueOf(Integer.valueOf(quantity) + 1);
                    else {
                        volQuantity = Integer.parseInt(String.valueOf(volQuantity).substring(0, String.valueOf(volQuantity).length() - 2)) * 100;
                        newQuantity = String.valueOf(volQuantity + 100);
                    }
                }
            }
        } else {
            if (volQuantity >= volmaxQuantity) newQuantity = String.valueOf(volmaxQuantity);
            else {
                if (stock.substring(0, stock.indexOf(" -")).equals(MethodUtils.HOSE_EXCHANGE)) {
                    if (volQuantity >= 10) {
                        volQuantity = Integer.parseInt(String.valueOf(volQuantity).substring(0, String.valueOf(volQuantity).length() - 1)) * 10;
                        newQuantity = String.valueOf(volQuantity + 10);
                    } else newQuantity = "10";
                } else {
                    if (volQuantity < 100)
                        newQuantity = String.valueOf(Integer.valueOf(quantity) + 1);
                    else {
                        volQuantity = Integer.parseInt(String.valueOf(volQuantity).substring(0, String.valueOf(volQuantity).length() - 2)) * 100;
                        newQuantity = String.valueOf(volQuantity + 100);
                    }
                }
            }
        }
        return newQuantity;
    }

    public static String setBackQuantity(String quantity, String stock) {
        String newQuantity;
        int volMax01 = Integer.valueOf(quantity.replace(",", ""));

        if (quantity.equals("") || quantity.equals("0")) newQuantity = "0";
        else {
            if (stock.substring(0, stock.indexOf(" -")).equals(MethodUtils.HOSE_EXCHANGE)) {
                if (volMax01 >= 10) {
                    volMax01 = Integer.parseInt(String.valueOf(volMax01).substring(0, String.valueOf(volMax01).length() - 1)) * 10;
                    newQuantity = String.valueOf(volMax01 - 10);
                } else newQuantity = "0";
            } else {
                if (volMax01 < 100)
                    newQuantity = String.valueOf(Integer.valueOf(quantity) - 1);
                else {
                    volMax01 = Integer.parseInt(String.valueOf(volMax01).substring(0, String.valueOf(volMax01).length() - 2)) * 100;
                    newQuantity = String.valueOf(volMax01 - 100);
                }
            }
        }
        return newQuantity;
    }

    public static StockQuote setNextPrice(Context context, StockQuote stockQuote, String price, int toBuy, String volume) {
        double newPrice;
        DecimalFormat df2 = new DecimalFormat("#.00");
        DecimalFormat df1 = new DecimalFormat("#.0");
        DecimalFormat df3 = new DecimalFormat("#.000");
        if (price.equals("") || price.equals("0"))
            newPrice = Double.valueOf(stockQuote.getReference());
        else newPrice = Double.valueOf(price);

        if (newPrice >= Double.valueOf(stockQuote.getCeiling())) {
            initConfirmDialog(context, context.getResources().getString(R.string.Price_must_be_between) + " " + stockQuote.getFloor() + " - " + stockQuote.getCeiling()
                    , (dialogInterface, i) -> {
                    });
            newPrice = Double.valueOf(price);
        } else {
            if (stockQuote.getExchange().equals(HOSE_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2") || stockQuote.getStockType().equals("3")) {
                    if (newPrice < 10) newPrice = Double.valueOf(df2.format(newPrice + 0.01));
                    else if (newPrice >= 10 && newPrice <= 49.95)
                        newPrice = Double.valueOf(df2.format(newPrice + 0.05));
                    else if (newPrice >= 50)
                        newPrice = Double.valueOf(df1.format(newPrice + 0.1));
                } else if (stockQuote.getStockType().equals("6"))
                    newPrice = Double.valueOf(df2.format(newPrice + 0.01));
            } else if (stockQuote.getExchange().equals(HNX_NY_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2") || stockQuote.getStockType().equals("3"))
                    newPrice = Double.valueOf(df1.format(newPrice + 0.1));
                else if (stockQuote.getStockType().equals("6"))
                    newPrice = Double.valueOf(df3.format(newPrice + 0.001));
            } else if (stockQuote.getExchange().equals(HNX_UPCOM_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2"))
                    newPrice = Double.valueOf(df1.format(newPrice + 0.1));
                else newPrice = Double.valueOf(df3.format(newPrice + 0.001));
            }
        }
        stockQuote.setNewPrice(String.valueOf(newPrice));
        stockQuote.setCashBuy(getCashBuy(volume, stockQuote.getRateMar(), String.valueOf(newPrice), toBuy));
        return stockQuote;
    }

    public static StockQuote setBackPrice(Context context, StockQuote stockQuote, String price, int toBuy, String volume) {
        double newPrice;
        DecimalFormat df2 = new DecimalFormat("#.00");
        DecimalFormat df1 = new DecimalFormat("#.0");
        DecimalFormat df3 = new DecimalFormat("#.000");
        if (price.equals("") || price.equals("0"))
            newPrice = Double.valueOf(stockQuote.getReference());
        else newPrice = Double.valueOf(price);

        if (newPrice <= Double.valueOf(stockQuote.getFloor())) {
            initConfirmDialog(context, context.getResources().getString(R.string.Price_must_be_between) + " " + stockQuote.getFloor() + " - " + stockQuote.getCeiling()
                    , (dialogInterface, i) -> {
                    });
            newPrice = Double.valueOf(price);
        } else {
            if (stockQuote.getExchange().equals(HOSE_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2") || stockQuote.getStockType().equals("3")) {
                    if (newPrice < 10) newPrice = Double.valueOf(df2.format(newPrice - 0.01));
                    else if (newPrice >= 10 && newPrice <= 49.95)
                        newPrice = Double.valueOf(df2.format(newPrice - 0.05));
                    else if (newPrice >= 50) newPrice = Double.valueOf(df1.format(newPrice - 0.1));
                } else if (stockQuote.getStockType().equals("6"))
                    newPrice = Double.valueOf(df2.format(newPrice - 0.01));
            } else if (stockQuote.getExchange().equals(HNX_NY_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2") || stockQuote.getStockType().equals("3"))
                    newPrice = Double.valueOf(df1.format(newPrice - 0.1));
                else if (stockQuote.getStockType().equals("6"))
                    newPrice = Double.valueOf(df3.format(newPrice - 0.001));
            } else if (stockQuote.getExchange().equals(HNX_UPCOM_EXCHANGE)) {
                if (stockQuote.getStockType().equals("2"))
                    newPrice = Double.valueOf(df1.format(newPrice - 0.1));
                else newPrice = Double.valueOf(df3.format(newPrice - 0.001));
            }
        }
        stockQuote.setNewPrice(String.valueOf(newPrice));
        stockQuote.setCashBuy(getCashBuy(volume, stockQuote.getRateMar(), String.valueOf(newPrice), toBuy));
        return stockQuote;
    }

    public static String getCashBuy(String volume, String TLV, String price, int toBuy) {
        /**
         * Đối với tài khoản không sử dụng Marpro
         	Tiền treo gốc = Khối lượng x Giá x (1 – TLV + 0.15%)
         	Tiền KQ = Khối lượng x Giá x TLV
         *	Đối với tài khoản sử dụng Marpro
         	Giá trị lệnh mua = Khối lượng x Giá x (1 + 0.15%)
         **/
        String cashBuy = "0";
        double cash, monneyMar;
        switch (toBuy) {
            case MARPRO:
                cashBuy = String.valueOf((int) (Double.valueOf(volume) * Double.valueOf(price) * 1000 * (1 + 0.0015)));
                break;
            case MAR:
                cash = Double.valueOf(volume) * Double.valueOf(price) * 1000 * (1 - (Double.valueOf(TLV) / 100) + 0.0015);
                monneyMar = Double.valueOf(volume) * Double.valueOf(price) * 1000 * (Double.valueOf(TLV) / 100);
                cashBuy = String.valueOf(cash) + "," + String.valueOf(monneyMar);
                break;
            case NORMAL:
                cash = Double.valueOf(volume) * Double.valueOf(price) * 1000 * (1 - (Double.valueOf(TLV) / 100) + 0.0015);
                monneyMar = Double.valueOf(volume) * Double.valueOf(price) * 1000 * (Double.valueOf(TLV) / 100);
                cashBuy = String.valueOf(cash) + "," + String.valueOf(monneyMar);
                break;
        }
        return formatNumber(cashBuy);
    }


    public static String setSMTH(String SDCTGD, String rate) {
        return String.valueOf((int) (Integer.valueOf(SDCTGD) / (1 - (Float.parseFloat(rate) * 0.01))));
    }
}
