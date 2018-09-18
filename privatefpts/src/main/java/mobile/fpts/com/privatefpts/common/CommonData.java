package mobile.fpts.com.privatefpts.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.privatefpts.model.ClientBalanceDetails;
import mobile.fpts.com.privatefpts.model.ProductInfo;

public class CommonData {

    /**
     * Get object form XML document
     */
    public static List getObject(String xmlDocument, String tagName) {
        // System.out.println("getObject#Start");
        List lstArray = new ArrayList();

        if (xmlDocument == null)
            return null;

        int startPos;
        int endPos;

        String tmp = xmlDocument;
        do {
            // Find match start tag
            startPos = tmp.indexOf(startTag(tagName));
            // Find match end tag
            endPos = tmp.indexOf(endTag(tagName));

            if (startPos >= 0 && endPos >= 0) {
                if (startPos < endPos) {
                    // get element
                    String XMLScripData = getValue(tmp, tagName);

                    if (tagName == "ClientBalanceDetails") {
                        ClientBalanceDetails clientBalanceDetails = new ClientBalanceDetails();
                        clientBalanceDetails.setLedgerBalance(getValue(XMLScripData, "LedgerBalance"));
                        clientBalanceDetails.setCashAdvance(getValue(XMLScripData, "CashAdvance"));
                        clientBalanceDetails.setAdhoc(getValue(XMLScripData, "adhoc"));
                        clientBalanceDetails.setCashTrading(getValue(XMLScripData, "CashTrading"));
                        clientBalanceDetails.setPTG(getValue(XMLScripData, "PTG"));
                        clientBalanceDetails.setTMCTC(getValue(XMLScripData, "TMCTC"));
                        clientBalanceDetails.setSDCTC(getValue(XMLScripData, "SDCTC"));
                        clientBalanceDetails.setSDCTGD(getValue(XMLScripData, "SDCTGD"));
                        clientBalanceDetails.setaCashTransfer(getValue(XMLScripData, "acashtransfer"));
                        if (getValue(XMLScripData, "StockVal") == null || getValue(XMLScripData, "StockVal") == "") {
                            clientBalanceDetails.setStockVal("0");
                        } else clientBalanceDetails.setStockVal(getValue(XMLScripData, "StockVal"));
                        if (getValue(XMLScripData, "RemainingQuota") == null || getValue(XMLScripData, "RemainingQuota") == "") {
                            clientBalanceDetails.setRemainingQuota("0");
                        } else
                            clientBalanceDetails.setRemainingQuota(getValue(XMLScripData, "RemainingQuota"));
                        if (getValue(XMLScripData, "TienMarpro") == null || getValue(XMLScripData, "TienMarpro") == "") {
                            clientBalanceDetails.setRemainingQuota("0");
                        } else
                            clientBalanceDetails.setTienMarpro(getValue(XMLScripData, "TienMarpro"));
                        clientBalanceDetails.setTienMarginDangGiaoDich(getValue(XMLScripData, "TienMarginDangGiaoDich"));
                        clientBalanceDetails.setTienDaSuDung(getValue(XMLScripData, "TienDaSuDung"));

                        lstArray.add(clientBalanceDetails);
                    }
                }
                tmp = tmp.substring(endPos + endTag(tagName).length());
            }
        } while (startPos >= 0 && endPos >= 0);
        // System.out.println("getObject#End");
        return lstArray;
    }

    // Must be a TEXT_NODE
    public static String getValue(String xmlDocument, String tagName) {

        if (xmlDocument == null)
            return "";

        // Find first match start tag
        int startPos = xmlDocument.indexOf(startTag(tagName));

        // Find first match end tag
        int endPos = xmlDocument.indexOf(endTag(tagName));
        // Can not found any match tag
        if (+startPos < 0 || endPos < 0)
            return "";
        else {
            // Find empty tag
            int emptyPos = xmlDocument.indexOf(emptyTag(tagName));

            if (emptyPos > 0 && emptyPos > startPos) {
                return "";
            }
        }
        String value = "";
        value = xmlDocument.substring(startPos + startTag(tagName).length(), endPos);
        // System.out.println("getValue:" + value);
        return value;
    }

    /**
     * Create empty of XML Tag
     */
    public static String emptyTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("<").append(tagName).append("/>");

        return tag.toString();
    }

    /**
     * Get Name of stock exchange
     */
    public static String getExactname(String Name) {
        String Exactname = "";
        if (Name.toUpperCase().equals("HNX.UPCOM"))
            Exactname = "HNX.UPCOM";
        else if (Name.toUpperCase().equals("HNX.NY"))
            Exactname = "HNX.NY";
        else
            Exactname = "HSX";
        //Exactname = Name;
        return Exactname;
    }

    /**
     * Get Name of stock exchange
     */
    public static String _getExactname(String Name) {
        String Exactname = "";
        if (Name.toUpperCase().equals("HOSE"))
            Exactname = "HOSTC";
        else
            Exactname = "HASTC";
        return Exactname;
    }

    /**
     * Get Name of stock exchange centerNo.
     */
    public static String _getExchangebyCenterNo(String centerNo) {
        String Exactname = "";
        if (centerNo.equals("1"))
            Exactname = "HOSE";
        else if (centerNo.toUpperCase().equals("2"))
            Exactname = "HNX";
        else
            Exactname = "UPCOM";
        return Exactname;
    }

    /**
     * Create start of XML Tag
     */
    public static String startTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("<").append(tagName).append(">");

        return tag.toString();
    }

    /**
     * Create end of XML Tag
     */
    public static String endTag(String tagName) {
        StringBuffer tag = new StringBuffer();
        tag.append("</").append(tagName).append(">");

        return tag.toString();
    }

    /**
     * Convert InputStream to String
     */
    public static String InputStreamToString(InputStream is) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[4096];

            for (int b = is.read(buffer); b >= 0; b = is.read(buffer)) {
                out.write(buffer, 0, b);
            }

            return new String(out.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean FindSymbol(String SymBolList, String Symbol) {
        int startPos = SymBolList.indexOf(Symbol);
        if (+startPos < 0)
            return false;
        return true;
    }

}
