package mobile.fpts.com.privatefpts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.fpts.com.privatefpts.common.CommonConnection;
import mobile.fpts.com.privatefpts.common.CommonData;
import mobile.fpts.com.privatefpts.model.ProductInfo;
import mobile.fpts.com.privatefpts.model.SessionManager;
import mobile.fpts.com.privatefpts.model.SoapAction;
import mobile.fpts.com.privatefpts.util.MethodUtils;


public class LoginPresenter {
    IPrivate.ILogin iLogin;
    String userName;
    String passWord;
    String linkGateway;
    boolean ischeckUser;
    SessionManager sessionManager;
    Context context;

    String WS_SOAP_NAMESPACE = "http://fpts.com.vn/EzGateway/";

    public LoginPresenter(IPrivate.ILogin iLogin, Context context,String linkGateway) {
        this.context = context;
        this.iLogin = iLogin;
        this.linkGateway = linkGateway;
        sessionManager = SessionManager.getInstance();
    }

    public void setLogin( String userName, String passWord, boolean ischeckUser){
        this.userName = userName;
        this.ischeckUser = ischeckUser;
        this.passWord = passWord;
        if (ischeckUser) saveStatusRemberAcc();
        new AsyLogin().execute();
    }

    public void setAutoLogin() {
        loadStatusRemberAcc();
        if (ischeckUser) new AsyLogin().execute();
        else iLogin.updateUI();
    }

    private class AsyLogin extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            int logInResult;
            String errorLogin = "";
            try {
                logInResult = Login(userName, MethodUtils.convertPass(passWord));

                if (logInResult != 0) {
                    errorLogin = sessionManager.getMessage();
                } else {
                    errorLogin = String.valueOf(logInResult);
                    GetClientInformation(sessionManager.getClientCode());
                    setSessionManager(checkRegister(sessionManager.getSessionNo(), sessionManager.getClientCode()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return errorLogin;
        }

        @Override
        protected void onPostExecute(String mess) {
            super.onPostExecute(mess);
            if (mess.equals("0")) iLogin.onLoginSucscess(sessionManager.getMessage2());
            else iLogin.onLoginFail(mess);
        }
    }

    private void saveStatusRemberAcc() {
        SharedPreferences sharedPref = context.getSharedPreferences("statusRememberAcc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user", userName);
        editor.putString("pass", passWord);
        editor.putBoolean("checkStatus", ischeckUser);
        editor.commit();
    }

    private void loadStatusRemberAcc() {
        SharedPreferences sharedPref = context.getSharedPreferences("statusRememberAcc", Context.MODE_PRIVATE);
        userName = sharedPref.getString("user", "");
        passWord = sharedPref.getString("pass", "");
        ischeckUser = sharedPref.getBoolean("checkStatus", false);
    }

    private int Login(String UserName, String Password) throws Exception {
        SoapAction soapLogin = new SoapAction();
        soapLogin.setWebServiceUrl(linkGateway);
        soapLogin.setSoapAction("http://fpts.com.vn/EzGateway/",
                "logIn");
        soapLogin.setProperty("username", UserName);
        soapLogin.setProperty("password", Password);

        CommonConnection.sendWebServiceRequest(soapLogin);
        return parseLoginResponse(
                soapLogin.getResponseData(), UserName);
    }

    private void GetClientInformation(String _clientCode) throws Exception {
        SoapAction LoginSoap = new SoapAction();
        LoginSoap.setWebServiceUrl(linkGateway);
        LoginSoap.setSoapAction(WS_SOAP_NAMESPACE, "GetClientInformation");
        LoginSoap.setProperty(_clientCode, _clientCode);

        CommonConnection.sendWebServiceRequest(LoginSoap);
        parseGetClientInformation(LoginSoap.getResponseData());
    }

    protected static void parseGetClientInformation(String response) {
        System.out.println("parseLoginResponse#Start:" + response);
        SessionManager logInRes = SessionManager.getInstance();

        try {
            logInRes.setFullName(CommonData.getValue(response, "clientName"));
        } catch (Throwable e) {
            System.out.println("Error in parsing logInResponse:" + e);
        }
    }

    protected static int parseLoginResponse(String response, String UserName) {
        System.out.println("parseLoginResponse#Start:" + response);
        SessionManager logInRes = SessionManager.getInstance();
        try {
            logInRes.setLogInResult(Integer.parseInt(CommonData.getValue(
                    response, "logInResult")));
            logInRes.setUserName(UserName);
            logInRes.setClientCode(CommonData.getValue(response,
                    "clientCode"));
            logInRes.setSessionNo(CommonData.getValue(response,
                    "sessionNo"));
            logInRes.setMessage(CommonData.getValue(response,
                    "message"));
            logInRes.setMessage2(CommonData.getValue(response,
                    "message2"));
        } catch (Throwable e) {
            System.out.println("Error in parsing logInResponse:" + e);
        }
        System.out.println("parseLoginResponse#End");
        return logInRes.getLogInResult();
    }

    protected static List<ProductInfo> parseCkRegisterMessageResponse(String response) {
        System.out.println("parsePortfolioOddlotResponse#Start:" + response);
        List lstArray = new ArrayList();
        try {
            lstArray = CommonData.getObject(response,
                    "ProductInfo");
        } catch (Throwable e) {
            System.out.println("[ERR] in parseMarMorPerResponse:" + e);
        }
        System.out.println("parseStockHoldResponse#End");

        return Arrays.asList((ProductInfo[]) lstArray.toArray(new ProductInfo[0]));
    }

    private void setSessionManager(List<ProductInfo> infos) {
        if (infos != null) {
            for (int i = 0; i <= infos.size(); i++) {
                if (infos.get(i).product().compareToIgnoreCase("EzAdvance") == 0) {
                    sessionManager.setCheckEzAdvance(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzMargin") == 0) {
                    sessionManager.setCheckMar(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzMarginPro") == 0) {
                    sessionManager.setCheckMarPro(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzMortgage") == 0) {
                    sessionManager.setCheckMor(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzOddLot") == 0) {
                    sessionManager.setCheckEzOddlot(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzTrade") == 0) {
                    sessionManager.setCheckEzTrading(Integer.parseInt(infos.get(i).status()));
                } else if (infos.get(i).product().compareToIgnoreCase("EzTransfer") == 0) {
                    sessionManager.setCheckEzTransfer(Integer.parseInt(infos.get(i).status()));
                }
            }
        }
    }

    public List<ProductInfo> checkRegister(String sessionNo, String clientCode) throws Exception {
        SoapAction ChkRegister = new SoapAction();
        ChkRegister.setWebServiceUrl(linkGateway);
        ChkRegister.setSoapAction(WS_SOAP_NAMESPACE, "Check_ProductRegister");
        ChkRegister.setProperty("strSessionNo", sessionNo);
        ChkRegister.setProperty("strAccount", clientCode);
        CommonConnection.sendWebServiceRequest(ChkRegister);

        return parseCkRegisterMessageResponse(ChkRegister.getResponseData());
    }
}
