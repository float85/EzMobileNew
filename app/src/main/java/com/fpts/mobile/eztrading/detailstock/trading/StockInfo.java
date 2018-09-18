package com.fpts.mobile.eztrading.detailstock.trading;

public class StockInfo {
    private String Time;
    //khối lượng niêm yết hiện tại
    private String Qty;
    //KHối lượng DLH hiện tại
    private String Kldlhht;
    private String Date;
    private String CurrentPrice;
    private String Changes;
    private String PerChange;
    //Giá trị vốn hóa thị trường
    private String MktCap;
    private String BasicPrice;
    //Tổng khối lượng giao dịch
    private String OpenPrice;
    private String TotalTradingQtty;

    private String Floor;
    private String Ceiling;
    private String DividentRate;
    private String HighestPrice;
    private String LowestPrice;
    private String PriorClosePrice;
    private String ResEPS;
    private String Dividend;
    private String PE;
    private String EPSAdjustedSTC;
    private String EPSbasicFPTS;
    private String EPSadjusted4QFPTS;
    private String PE4QFPTS;
    private String TotalTradingValue;
    private String Wk52Low;
    private String Wk52High;
    //NN Mua
    private String Dumua;
    //NN Bán
    private String Duban;
    private String EpsFpts;
    private String CTMG;

    private String KLGD30Days;
    //tỷ  lệ sở hữu nước ngoài
    private String TLSHNN;
    private String PreCt;
    private String NNMUA_YTD;
    private String NNMUA_YTD30;
    private String PB;

    public StockInfo() {
    }

    public StockInfo(String time, String qty, String kldlhht, String date, String currentPrice,
                     String changes, String perChange, String mktCap, String basicPrice,
                     String openPrice, String totalTradingQtty, String floor, String ceiling,
                     String dividentRate, String highestPrice, String lowestPrice, String priorClosePrice,
                     String resEPS, String dividend, String PE, String EPSAdjustedSTC, String EPSbasicFPTS,
                     String EPSadjusted4QFPTS, String PE4QFPTS, String totalTradingValue, String wk52Low,
                     String wk52High, String dumua, String duban, String epsFpts, String CTMG,
                     String KLGD30Days, String TLSHNN, String preCt, String NNMUA_YTD, String NNMUA_YTD30, String PB) {
        Time = time;
        Qty = qty;
        Kldlhht = kldlhht;
        Date = date;
        CurrentPrice = currentPrice;
        Changes = changes;
        PerChange = perChange;
        MktCap = mktCap;
        BasicPrice = basicPrice;
        OpenPrice = openPrice;
        TotalTradingQtty = totalTradingQtty;
        Floor = floor;
        Ceiling = ceiling;
        DividentRate = dividentRate;
        HighestPrice = highestPrice;
        LowestPrice = lowestPrice;
        PriorClosePrice = priorClosePrice;
        ResEPS = resEPS;
        Dividend = dividend;
        this.PE = PE;
        this.EPSAdjustedSTC = EPSAdjustedSTC;
        this.EPSbasicFPTS = EPSbasicFPTS;
        this.EPSadjusted4QFPTS = EPSadjusted4QFPTS;
        this.PE4QFPTS = PE4QFPTS;
        TotalTradingValue = totalTradingValue;
        Wk52Low = wk52Low;
        Wk52High = wk52High;
        Dumua = dumua;
        Duban = duban;
        EpsFpts = epsFpts;
        this.CTMG = CTMG;
        this.KLGD30Days = KLGD30Days;
        this.TLSHNN = TLSHNN;
        PreCt = preCt;
        this.NNMUA_YTD = NNMUA_YTD;
        this.NNMUA_YTD30 = NNMUA_YTD30;
        this.PB = PB;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getKldlhht() {
        return Kldlhht;
    }

    public void setKldlhht(String kldlhht) {
        Kldlhht = kldlhht;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        CurrentPrice = currentPrice;
    }

    public String getChanges() {
        return Changes;
    }

    public void setChanges(String changes) {
        Changes = changes;
    }

    public String getPerChange() {
        return PerChange;
    }

    public void setPerChange(String perChange) {
        PerChange = perChange;
    }

    public String getMktCap() {
        return MktCap;
    }

    public void setMktCap(String mktCap) {
        MktCap = mktCap;
    }

    public String getBasicPrice() {
        return BasicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        BasicPrice = basicPrice;
    }

    public String getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(String openPrice) {
        OpenPrice = openPrice;
    }

    public String getTotalTradingQtty() {
        return TotalTradingQtty;
    }

    public void setTotalTradingQtty(String totalTradingQtty) {
        TotalTradingQtty = totalTradingQtty;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getCeiling() {
        return Ceiling;
    }

    public void setCeiling(String ceiling) {
        Ceiling = ceiling;
    }

    public String getDividentRate() {
        return DividentRate;
    }

    public void setDividentRate(String dividentRate) {
        DividentRate = dividentRate;
    }

    public String getHighestPrice() {
        return HighestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        HighestPrice = highestPrice;
    }

    public String getLowestPrice() {
        return LowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        LowestPrice = lowestPrice;
    }

    public String getPriorClosePrice() {
        return PriorClosePrice;
    }

    public void setPriorClosePrice(String priorClosePrice) {
        PriorClosePrice = priorClosePrice;
    }

    public String getResEPS() {
        return ResEPS;
    }

    public void setResEPS(String resEPS) {
        ResEPS = resEPS;
    }

    public String getDividend() {
        return Dividend;
    }

    public void setDividend(String dividend) {
        Dividend = dividend;
    }

    public String getPE() {
        return PE;
    }

    public void setPE(String PE) {
        this.PE = PE;
    }

    public String getEPSAdjustedSTC() {
        return EPSAdjustedSTC;
    }

    public void setEPSAdjustedSTC(String EPSAdjustedSTC) {
        this.EPSAdjustedSTC = EPSAdjustedSTC;
    }

    public String getEPSbasicFPTS() {
        return EPSbasicFPTS;
    }

    public void setEPSbasicFPTS(String EPSbasicFPTS) {
        this.EPSbasicFPTS = EPSbasicFPTS;
    }

    public String getEPSadjusted4QFPTS() {
        return EPSadjusted4QFPTS;
    }

    public void setEPSadjusted4QFPTS(String EPSadjusted4QFPTS) {
        this.EPSadjusted4QFPTS = EPSadjusted4QFPTS;
    }

    public String getPE4QFPTS() {
        return PE4QFPTS;
    }

    public void setPE4QFPTS(String PE4QFPTS) {
        this.PE4QFPTS = PE4QFPTS;
    }

    public String getTotalTradingValue() {
        return TotalTradingValue;
    }

    public void setTotalTradingValue(String totalTradingValue) {
        TotalTradingValue = totalTradingValue;
    }

    public String getWk52Low() {
        return Wk52Low;
    }

    public void setWk52Low(String wk52Low) {
        Wk52Low = wk52Low;
    }

    public String getWk52High() {
        return Wk52High;
    }

    public void setWk52High(String wk52High) {
        Wk52High = wk52High;
    }

    public String getDumua() {
        return Dumua;
    }

    public void setDumua(String dumua) {
        Dumua = dumua;
    }

    public String getDuban() {
        return Duban;
    }

    public void setDuban(String duban) {
        Duban = duban;
    }

    public String getEpsFpts() {
        return EpsFpts;
    }

    public void setEpsFpts(String epsFpts) {
        EpsFpts = epsFpts;
    }

    public String getCTMG() {
        return CTMG;
    }

    public void setCTMG(String CTMG) {
        this.CTMG = CTMG;
    }

    public String getKLGD30Days() {
        return KLGD30Days;
    }

    public void setKLGD30Days(String KLGD30Days) {
        this.KLGD30Days = KLGD30Days;
    }

    public String getTLSHNN() {
        return TLSHNN;
    }

    public void setTLSHNN(String TLSHNN) {
        this.TLSHNN = TLSHNN;
    }

    public String getPreCt() {
        return PreCt;
    }

    public void setPreCt(String preCt) {
        PreCt = preCt;
    }

    public String getNNMUA_YTD() {
        return NNMUA_YTD;
    }

    public void setNNMUA_YTD(String NNMUA_YTD) {
        this.NNMUA_YTD = NNMUA_YTD;
    }

    public String getNNMUA_YTD30() {
        return NNMUA_YTD30;
    }

    public void setNNMUA_YTD30(String NNMUA_YTD30) {
        this.NNMUA_YTD30 = NNMUA_YTD30;
    }

    public String getPB() {
        return PB;
    }

    public void setPB(String PB) {
        this.PB = PB;
    }
}
