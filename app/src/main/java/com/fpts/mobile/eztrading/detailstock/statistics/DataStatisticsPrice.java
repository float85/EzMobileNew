package com.fpts.mobile.eztrading.detailstock.statistics;

public class DataStatisticsPrice {
    private String iD;
    private String tranDate;
    private String tranDateUS;
    private String stockSymbol;
    private String ceiling;
    private String floor;
    private String basic;
    private String open;
    private String close;
    private String highest;
    private String lowest;
    private String changePrice;
    private String percentPrice;
    private String aP;
    private String nTQ;
    private String nTV;
    private String pTQ;
    private String pTV;
    private String tTQ;
    private String tTV;
    private String status;

    public DataStatisticsPrice(String iD, String tranDate, String tranDateUS, String stockSymbol, String ceiling, String floor, String basic, String open, String close, String highest, String lowest, String changePrice, String percentPrice, String aP, String nTQ, String nTV, String pTQ, String pTV, String tTQ, String tTV, String status) {
        this.iD = iD;
        this.tranDate = tranDate;
        this.tranDateUS = tranDateUS;
        this.stockSymbol = stockSymbol;
        this.ceiling = ceiling;
        this.floor = floor;
        this.basic = basic;
        this.open = open;
        this.close = close;
        this.highest = highest;
        this.lowest = lowest;
        this.changePrice = changePrice;
        this.percentPrice = percentPrice;
        this.aP = aP;
        this.nTQ = nTQ;
        this.nTV = nTV;
        this.pTQ = pTQ;
        this.pTV = pTV;
        this.tTQ = tTQ;
        this.tTV = tTV;
        this.status = status;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranDateUS() {
        return tranDateUS;
    }

    public void setTranDateUS(String tranDateUS) {
        this.tranDateUS = tranDateUS;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getCeiling() {
        return ceiling;
    }

    public void setCeiling(String ceiling) {
        this.ceiling = ceiling;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getPercentPrice() {
        return percentPrice;
    }

    public void setPercentPrice(String percentPrice) {
        this.percentPrice = percentPrice;
    }

    public String getAP() {
        return aP;
    }

    public void setAP(String aP) {
        this.aP = aP;
    }

    public String getNTQ() {
        return nTQ;
    }

    public void setNTQ(String nTQ) {
        this.nTQ = nTQ;
    }

    public String getNTV() {
        return nTV;
    }

    public void setNTV(String nTV) {
        this.nTV = nTV;
    }

    public String getPTQ() {
        return pTQ;
    }

    public void setPTQ(String pTQ) {
        this.pTQ = pTQ;
    }

    public String getPTV() {
        return pTV;
    }

    public void setPTV(String pTV) {
        this.pTV = pTV;
    }

    public String getTTQ() {
        return tTQ;
    }

    public void setTTQ(String tTQ) {
        this.tTQ = tTQ;
    }

    public String getTTV() {
        return tTV;
    }

    public void setTTV(String tTV) {
        this.tTV = tTV;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
