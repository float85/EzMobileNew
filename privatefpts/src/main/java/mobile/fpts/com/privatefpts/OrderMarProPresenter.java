package mobile.fpts.com.privatefpts;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.fpts.com.privatefpts.common.CommonConnection;
import mobile.fpts.com.privatefpts.common.CommonData;
import mobile.fpts.com.privatefpts.common.HttpClient;
import mobile.fpts.com.privatefpts.model.ClientBalanceDetails;
import mobile.fpts.com.privatefpts.model.SessionManager;
import mobile.fpts.com.privatefpts.model.SoapAction;
import mobile.fpts.com.privatefpts.model.StockQuote;
import mobile.fpts.com.privatefpts.util.MethodUtils;

public class OrderMarProPresenter {
    IPrivate.IOrder iOrder;
    StockQuote stockQuote;
    SessionManager sessionManager;
    ClientBalanceDetails clientBalanceDetails;
    Context context;
    int TO_BUY_MARPRO = 3;
    String rateS, symbols, getScrip, linkGateway;
    List<String> list;
    HttpClient httpClient;
    private String[] termHNX = {"LO", "MTL", "MAK", "MOK", "ATC"};
    private String[] termHSX = {"ATO", "LO", "MP", "ATC"};
    private String[] termUPCOM = {"LO"};


    public OrderMarProPresenter(IPrivate.IOrder iOrder, Context context, String linkGateway) {
        this.linkGateway = linkGateway;
        this.iOrder = iOrder;
        this.context = context;
        sessionManager = SessionManager.getInstance();
        stockQuote = new StockQuote();
        clientBalanceDetails = new ClientBalanceDetails();
        httpClient = new HttpClient();
    }


    public void getSymbolsDetail(String symbols) {
        this.symbols = symbols;
        new getSymbol().execute();

    }

    class getSymbol extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... strings) {
            String str = "";
            try {
                str = httpClient.getHttpClient(MethodUtils.URL_GATEWAY_2018 + "?s=quotes2&symbol=" + symbols);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            setSymbolsQC(parseStockQuote(s));
        }
    }

    private void setSymbolsQC(StockQuote qc) {
        if (qc == null) {
            MethodUtils.toast(context, context.getResources().getString(R.string.order_symbol_does_not_exist));
            iOrder.ClearUI();
        } else {
            this.stockQuote = qc;
            new DetailSymbols().execute();
        }
    }

    class DetailSymbols extends AsyncTask<Void, Void, ClientBalanceDetails> {
        @Override
        protected ClientBalanceDetails doInBackground(Void... voids) {
            try {
                rateS = getMarProRate(sessionManager.getSessionNo(), sessionManager.getClientCode(), symbols);
                clientBalanceDetails = ClientBalanceDetails(sessionManager.getSessionNo(), sessionManager.getClientCode());
                getScrip = getScrip(sessionManager.getSessionNo(), sessionManager.getClientCode(), symbols);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return clientBalanceDetails;
        }

        @Override
        protected void onPostExecute(ClientBalanceDetails s) {
            super.onPostExecute(s);

            list = new ArrayList<>();
            s.setSMTH(MethodUtils.setSMTH(s.getSDCTGD(), rateS));
            stockQuote.setExchange(getScrip.substring(0, getScrip.indexOf(",")));
            stockQuote.setStockType(getScrip.substring(getScrip.indexOf(",") + 1, getScrip.length()));
            stockQuote.setRateMar(rateS);
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, s, stockQuote.getCeiling(), TO_BUY_MARPRO));

            if (stockQuote.getExchange().equals(MethodUtils.HOSE_EXCHANGE))
                list = Arrays.asList(termHSX);
            else if (stockQuote.getExchange().equals(MethodUtils.HNX_NY_EXCHANGE))
                list = Arrays.asList(termHNX);
            else if (stockQuote.getExchange().equals(MethodUtils.HNX_UPCOM_EXCHANGE))
                list = Arrays.asList(termUPCOM);

            iOrder.updateUI(stockQuote, s, list);
        }
    }

    public void checkVol(String vol, String stock) {
        if (!vol.equals("") && stock.length() > 0) {
            double volcheck = Double.valueOf(vol.replace(",", ""));

            if (stock.substring(0, stock.indexOf(" -")).equals(MethodUtils.HOSE_EXCHANGE)) {
                if (volcheck > 500000 || volcheck % 10 != 0)
                    MethodUtils.toast(context, "Số lượng không được vượt quá 500,000 và là bội của 10");
            } else {
                if (volcheck >= 100 && volcheck % 100 == 0) {
                    MethodUtils.toast(context, "Số lượng phải là bội của 100");
                }
            }
        }
    }

    private String getMarProRate(String sessionNo, String clientCode, String symbol) throws Exception {
        SoapAction getOrderSoap = new SoapAction();
        getOrderSoap.setWebServiceUrl(linkGateway);
        getOrderSoap.setSoapAction(MethodUtils.WS_SOAP_NAMESPACE, "getMarProRate");
        getOrderSoap.setProperty("strSessionNo", sessionNo);
        getOrderSoap.setProperty("strAccount", clientCode);
        getOrderSoap.setProperty("strAsecabb", symbol);
        CommonConnection.sendWebServiceRequest(getOrderSoap);

        return CommonData.getValue(getOrderSoap.getResponseData(), "aprorate");
    }

    private ClientBalanceDetails ClientBalanceDetails(String sessionNo, String clientCode) throws Exception {
        SoapAction getOrderSoap = new SoapAction();

        getOrderSoap.setWebServiceUrl(linkGateway);
        getOrderSoap.setSoapAction(MethodUtils.WS_SOAP_NAMESPACE, "ClientBalance_Details");
        getOrderSoap.setProperty("sessionNo", sessionNo);
        getOrderSoap.setProperty("clientCode", clientCode);
        CommonConnection.sendWebServiceRequest(getOrderSoap);

        return parseClientBalance_Details(getOrderSoap.getResponseData());
    }

    private String getScrip(String sessionNo, String clientCode, String symbol) throws Exception {

        SoapAction LoginSoap = new SoapAction();
        LoginSoap.setWebServiceUrl(linkGateway);
        LoginSoap.setSoapAction(MethodUtils.WS_SOAP_NAMESPACE, "getScrip");
        LoginSoap.setProperty("sessionNo", sessionNo);
        LoginSoap.setProperty("clientCode", clientCode);
        LoginSoap.setProperty("symbol", symbol);

        CommonConnection.sendWebServiceRequest(LoginSoap);
        return parsegetScrip(LoginSoap.getResponseData());
    }

    private StockQuote parseStockQuote(String responseText) {
        String[] line1 = responseText.split("@");
        StockQuote stockQuote = new StockQuote();

        List lstArray = new ArrayList();
        for (int i = 0; i < line1.length; i++) {
            String line2 = line1[i].replace("{", "#").replace("|", "#").replace("}", "#");
            String[] line = line2.split("#", -1);

            if (line.length < 2) {
                return null;
            }

            try {
                stockQuote.setSymbol(line[0]);
            } catch (Exception e) {
                stockQuote.setSymbol("0");
            }

            try {
                stockQuote.setPricecolor(line[1]);
            } catch (Exception e) {
                stockQuote.setPricecolor("0");
            }

            try {
                stockQuote.setMatchprice(line[2]);
            } catch (Exception e) {
                stockQuote.setMatchprice("0");
            }

            try {
                stockQuote.setChangePct(line[3]);
            } catch (Exception e) {
                stockQuote.setChangePct("0");
            }

            try {
                stockQuote.setVolume(line[4]);
            } catch (Exception e) {
                stockQuote.setVolume("0");
            }

            try {
                stockQuote.setCeiling(line[6]);
            } catch (Exception e) {
                stockQuote.setCeiling("0");
            }

            try {
                stockQuote.setFloor(line[7]);
            } catch (Exception e) {
                stockQuote.setFloor("0");
            }

            try {
                stockQuote.setReference(line[8]);
            } catch (Exception e) {
                stockQuote.setReference("0");
            }

            try {
                stockQuote.setBuy3(line[11]);
            } catch (Exception e) {
                stockQuote.setBuy3("0");
            }
            try {
                stockQuote.setBuy3Volume(line[12]);
            } catch (Exception e) {
                stockQuote.setBuy3Volume("0");
            }

            try {
                stockQuote.setBuy2(line[13]);
            } catch (Exception e) {
                stockQuote.setBuy2("0");
            }

            try {
                stockQuote.setBuy2Volume(line[14]);
            } catch (Exception e) {
                stockQuote.setBuy2Volume("0");
            }

            try {
                stockQuote.setBuy1(line[15]);
            } catch (Exception e) {
                stockQuote.setBuy1("0");
            }

            try {
                stockQuote.setBuy1Volume(line[16]);
            } catch (Exception e) {
                stockQuote.setBuy1Volume("0");
            }

            try {

                stockQuote.setMatch(line[17]);
            } catch (Exception e) {
                stockQuote.setMatch("0");
            }

            try {
                stockQuote.setMatchprice(line[18]);
            } catch (Exception e) {
                stockQuote.setMatchprice("0");
            }
            try {
                stockQuote.setChangPrice(line[19]);
            } catch (Exception e) {
                stockQuote.setChangPrice("0");
            }
            try {
                stockQuote.setSell1(line[20]);
            } catch (Exception e) {
                stockQuote.setSell1("0");
            }
            try {
                stockQuote.setSell1Volume(line[21]);
            } catch (Exception e) {
                stockQuote.setSell1Volume("0");
            }

            try {
                stockQuote.setSell2(line[22]);
            } catch (Exception e) {
                stockQuote.setSell2("0");
            }

            try {
                stockQuote.setSell2Volume(line[23]);
            } catch (Exception e) {
                stockQuote.setSell2Volume("0");
            }

            try {
                stockQuote.setSell3(line[24]);
            } catch (Exception e) {
                stockQuote.setSell3("0");
            }

            try {
                stockQuote.setSell3Volume(line[25]);
            } catch (Exception e) {
                stockQuote.setSell3Volume("0");
            }

            try {
                stockQuote.setTotalQtty(line[26]);
            } catch (Exception e) {
                stockQuote.setTotalQtty("0");
            }

            try {
                stockQuote.setAverage(line[27]);
            } catch (Exception e) {
                stockQuote.setAverage("0");
            }


            if (line.length == 33) {
                try {
                    stockQuote.setLowest(line[29]);

                } catch (Exception e) {
                    stockQuote.setLowest("0");
                }

                try {
                    stockQuote.setHighestPrice(line[28]);
                } catch (Exception e) {
                    stockQuote.setHighestPrice("0");
                }

                try {
                    stockQuote.setForeignBuy(line[30]);
                } catch (Exception e) {
                    stockQuote.setForeignBuy("0");
                }

                try {
                    stockQuote.setForeignSell(line[31]);
                } catch (Exception e) {
                    stockQuote.setForeignSell("0");
                }
            } else {
                if (line.length == 34) {
                    System.out.print("hhhh");
                    try {
                        stockQuote.setLowest(line[30]);
                    } catch (Exception e) {
                        stockQuote.setOpenPrice("0");
                    }

                    try {
                        stockQuote.setHighestPrice(line[29]);
                    } catch (Exception e) {
                        stockQuote.setHighestPrice("0");
                    }

                    try {
                        stockQuote.setForeignBuy(line[31]);
                    } catch (Exception e) {
                        stockQuote.setForeignBuy("0");
                    }

                    try {
                        stockQuote.setForeignSell(line[32]);
                    } catch (Exception e) {
                        stockQuote.setForeignSell("0");
                    }
                }
            }
            lstArray.add(stockQuote);
        }
        Log.d("lstArrayQuotes1", String.valueOf(lstArray));
        return stockQuote;
    }

    private ClientBalanceDetails parseClientBalance_Details(String response) {
        System.out.println("parseGetOrderResponse#Start:" + response);
        List lstArray = new ArrayList();
        try {
            lstArray = CommonData.getObject(response, "ClientBalanceDetails");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("[ERR] parseGetOrderResponse:" + e);
        }
        System.out.println("parseGetOrderResponse#End");
        return (ClientBalanceDetails) lstArray.get(0);
    }

    private String parsegetScrip(String response) {

        System.out.println("parsegetScripResponse#Start:" + response);
        System.out.println("Exchange + StockType:" + CommonData.getValue(response, "Exchange") + "," + CommonData.getValue(response, "StockType"));

        return CommonData.getValue(response, "Exchange") + "," + CommonData.getValue(response, "StockType");
    }


}
