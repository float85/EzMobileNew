package com.fpts.mobile.eztrading.events;

public class Event {
    private String iDX;
    private String groupNm;
    private String iD;
    private String stockCode;
    private String marketname;
    private String content;
    private String url;
    private String date1;

    public Event(String groupNm, String iD, String stockCode, String marketname,  String content, String url, String date1) {
        this.groupNm = groupNm;
        this.iD = iD;
        this.stockCode = stockCode;
        this.marketname = marketname;
        this.content = content;
        this.url = url;
        this.date1 = date1;
    }

    public String getMarketname() {
        return marketname;
    }

    public void setMarketname(String marketname) {
        this.marketname = marketname;
    }

    public String getiDX() {
        return iDX;
    }

    public void setiDX(String iDX) {
        this.iDX = iDX;
    }

    public String getGroupNm() {
        return groupNm;
    }

    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }
}
