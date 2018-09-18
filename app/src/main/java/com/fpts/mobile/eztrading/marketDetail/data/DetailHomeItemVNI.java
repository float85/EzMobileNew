package com.fpts.mobile.eztrading.marketDetail.data;

public class DetailHomeItemVNI {
    private String mARKETINDEX;
    private String strArrow0;
    private String cHGINDEX;
    private String pCTINDEX;
    private String tOTALQTTY;
    private String tOTALVALUE;
    private String tOTALTRADE;

    public DetailHomeItemVNI(String mARKETINDEX, String strArrow0, String cHGINDEX, String pCTINDEX, String tOTALQTTY, String tOTALVALUE, String tOTALTRADE) {
        this.mARKETINDEX = mARKETINDEX;
        this.strArrow0 = strArrow0;
        this.cHGINDEX = cHGINDEX;
        this.pCTINDEX = pCTINDEX;
        this.tOTALQTTY = tOTALQTTY;
        this.tOTALVALUE = tOTALVALUE;
        this.tOTALTRADE = tOTALTRADE;
    }

    public String getMARKETINDEX() {
        return mARKETINDEX;
    }

    public void setMARKETINDEX(String mARKETINDEX) {
        this.mARKETINDEX = mARKETINDEX;
    }

    public String getStrArrow0() {
        return strArrow0;
    }

    public void setStrArrow0(String strArrow0) {
        this.strArrow0 = strArrow0;
    }

    public String getCHGINDEX() {
        return cHGINDEX;
    }

    public void setCHGINDEX(String cHGINDEX) {
        this.cHGINDEX = cHGINDEX;
    }

    public String getPCTINDEX() {
        return pCTINDEX;
    }

    public void setPCTINDEX(String pCTINDEX) {
        this.pCTINDEX = pCTINDEX;
    }

    public String getTOTALQTTY() {
        return tOTALQTTY;
    }

    public void setTOTALQTTY(String tOTALQTTY) {
        this.tOTALQTTY = tOTALQTTY;
    }

    public String getTOTALVALUE() {
        return tOTALVALUE;
    }

    public void setTOTALVALUE(String tOTALVALUE) {
        this.tOTALVALUE = tOTALVALUE;
    }

    public String getTOTALTRADE() {
        return tOTALTRADE;
    }

    public void setTOTALTRADE(String tOTALTRADE) {
        this.tOTALTRADE = tOTALTRADE;
    }
}
