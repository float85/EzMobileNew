package mobile.fpts.com.privatefpts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import mobile.fpts.com.privatefpts.common.CommonConnection;
import mobile.fpts.com.privatefpts.common.CommonData;
import mobile.fpts.com.privatefpts.model.SoapAction;
import mobile.fpts.com.privatefpts.util.MethodUtils;

public class UpdatePresenter {

    IPrivate.ICheckUpdate icheckUpdate;
    String versionName,linkgateway;
    Context context;
    String WS_SOAP_NAMESPACE = "http://fpts.com.vn/EzGateway/";
    public final static String PARAM_PRODUCT_TYPE = "MA_A";
    public final static String LINK_UPDATE = "http://play.google.com/store/apps/details?id=vndungnd.com.fpts.mobile.activity";


    public UpdatePresenter(IPrivate.ICheckUpdate checkUpdate,Context context,String linkgateway) {
        this.icheckUpdate = checkUpdate;
        this.context=context;
        this.linkgateway=linkgateway;
        setCheckUpdate();
    }

    public void setCheckUpdate(){
        try {
            versionName = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName;
            new AsyCheckUpdae().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class AsyCheckUpdae extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            int checkversion = 0;
            try {
                checkversion = checkVesion(versionName, "MA_A");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return checkversion;
        }

        @Override
        protected void onPostExecute(Integer check) {
            super.onPostExecute(check);
            if (check==0) {
                MethodUtils.initConfirmDialog(context, "Xin mời cập nhật phiên bản mới.",
                        (dialog, which) -> context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(LINK_UPDATE)))).show();
            } else icheckUpdate.onNoUpdate();

        }
    }

    public int checkVesion(String VersionName, String ProductType) throws Exception {
        SoapAction soapVesion = new SoapAction();
        soapVesion.setWebServiceUrl(linkgateway);
        soapVesion.setSoapAction(WS_SOAP_NAMESPACE, "Check_Version");
        soapVesion.setProperty("verName", VersionName);
        soapVesion.setProperty("productType", ProductType);
        CommonConnection.sendWebServiceRequest(soapVesion);

        return parseCheckVersionResponse(soapVesion.getResponseData());
    }

    protected static int parseCheckVersionResponse(String response) {
        System.out.println("parseCheckVersionResponse#Start:" + response);

        int result = -1;
        try {
            result = Integer.parseInt(CommonData.getValue(response, "Check_VersionResult"));
        } catch (Throwable e) {
            System.out.println("Error in parsing checkVersionResponse:" + e);
        }
        System.out.println("parseCheckVersionResponse#End");
        return result;
    }
}
