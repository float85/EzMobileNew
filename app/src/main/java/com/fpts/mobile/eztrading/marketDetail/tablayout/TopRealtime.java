package com.fpts.mobile.eztrading.marketDetail.tablayout;

public class TopRealtime {
    private String sCode;
    private String sCeiling;
    private String sFloor;
    private String sRefercence;
    private String sOpen;
    private String sClose;
    private String sAverage;
    private String sHighest;
    private String sLowest;
    private String sChange;
    private String sChangePercent;
    private String sTotalShares;
    private String sTotalValue;

    public TopRealtime(String sCode, String sCeiling, String sFloor, String sRefercence, String sOpen, String sClose, String sAverage, String sHighest, String sLowest, String sChange, String sChangePercent, String sTotalShares, String sTotalValue) {
        this.sCode = sCode;
        this.sCeiling = sCeiling;
        this.sFloor = sFloor;
        this.sRefercence = sRefercence;
        this.sOpen = sOpen;
        this.sClose = sClose;
        this.sAverage = sAverage;
        this.sHighest = sHighest;
        this.sLowest = sLowest;
        this.sChange = sChange;
        this.sChangePercent = sChangePercent;
        this.sTotalShares = sTotalShares;
        this.sTotalValue = sTotalValue;
    }

    public String getSCode() {
        return sCode;
    }

    public void setSCode(String sCode) {
        this.sCode = sCode;
    }

    public String getSCeiling() {
        return sCeiling;
    }

    public void setSCeiling(String sCeiling) {
        this.sCeiling = sCeiling;
    }

    public String getSFloor() {
        return sFloor;
    }

    public void setSFloor(String sFloor) {
        this.sFloor = sFloor;
    }

    public String getSRefercence() {
        return sRefercence;
    }

    public void setSRefercence(String sRefercence) {
        this.sRefercence = sRefercence;
    }

    public String getSOpen() {
        return sOpen;
    }

    public void setSOpen(String sOpen) {
        this.sOpen = sOpen;
    }

    public String getSClose() {
        return sClose;
    }

    public void setSClose(String sClose) {
        this.sClose = sClose;
    }

    public String getSAverage() {
        return sAverage;
    }

    public void setSAverage(String sAverage) {
        this.sAverage = sAverage;
    }

    public String getSHighest() {
        return sHighest;
    }

    public void setSHighest(String sHighest) {
        this.sHighest = sHighest;
    }

    public String getSLowest() {
        return sLowest;
    }

    public void setSLowest(String sLowest) {
        this.sLowest = sLowest;
    }

    public String getSChange() {
        return sChange;
    }

    public void setSChange(String sChange) {
        this.sChange = sChange;
    }

    public String getSChangePercent() {
        return sChangePercent;
    }

    public void setSChangePercent(String sChangePercent) {
        this.sChangePercent = sChangePercent;
    }

    public String getSTotalShares() {
        return sTotalShares;
    }

    public void setSTotalShares(String sTotalShares) {
        this.sTotalShares = sTotalShares;
    }

    public String getSTotalValue() {
        return sTotalValue;
    }

    public void setSTotalValue(String sTotalValue) {
        this.sTotalValue = sTotalValue;
    }

}