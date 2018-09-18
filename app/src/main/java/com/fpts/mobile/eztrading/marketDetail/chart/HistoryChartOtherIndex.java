package com.fpts.mobile.eztrading.marketDetail.chart;

public class HistoryChartOtherIndex {

    private String chartO;//open
    private String chartH;//high
    private String chartL;//low
    private String chartC;//close
    private String charV;//volumn
    private String charTime;

    public HistoryChartOtherIndex() {
    }

    public HistoryChartOtherIndex(String chartO, String chartH, String chartL, String chartC, String charV, String charTime) {
        this.chartO = chartO;
        this.chartH = chartH;
        this.chartL = chartL;
        this.chartC = chartC;
        this.charV = charV;
        this.charTime = charTime;
    }

    public String getChartO() {
        return chartO;
    }

    public void setChartO(String chartO) {
        this.chartO = chartO;
    }

    public String getChartH() {
        return chartH;
    }

    public void setChartH(String chartH) {
        this.chartH = chartH;
    }

    public String getChartL() {
        return chartL;
    }

    public void setChartL(String chartL) {
        this.chartL = chartL;
    }

    public String getChartC() {
        return chartC;
    }

    public void setChartC(String chartC) {
        this.chartC = chartC;
    }

    public String getCharV() {
        return charV;
    }

    public void setCharV(String charV) {
        this.charV = charV;
    }

    public String getCharTime() {
        return charTime;
    }

    public void setCharTime(String charTime) {
        this.charTime = charTime;
    }
}

