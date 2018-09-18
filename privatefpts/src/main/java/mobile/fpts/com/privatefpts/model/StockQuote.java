package mobile.fpts.com.privatefpts.model;

import java.io.Serializable;

public class StockQuote implements Serializable {
    private String stockType;
    private String exchange;
    private String volMax;
    private String rateMar;
    private String nameCity;
    private String QtyBD;
    private String symbol;
    private String pricecolor;
    private String changePct;
    private String volume;
    private String ceiling;
    private String reference;
    private String floor;
    private String highest;
    private String lowest;
    private String buy3;
    private String buy3Volume;
    private String buy2;
    private String buy2Volume;
    private String buy1;
    private String buy1Volume;
    private String sell1;
    private String sell1Volume;
    private String sell2;
    private String sell2Volume;
    private String sell3;
    private String sell3Volume;
    private String foreignBuy;
    private String foreignSell;
    private String centerNo;
    private String company;
    private String match;
    private String totalQtty;
    private String average;
    private String matchprice;
    private String changPrice;
    private String openPrice;
    private String highestPrice;
    private String newPrice;
    private String cashBuy;

    public StockQuote() {
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getCashBuy() {
        return cashBuy;
    }

    public void setCashBuy(String cashBuy) {
        this.cashBuy = cashBuy;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMatchprice() {
        return matchprice;
    }

    public void setMatchprice(String matchprice) {
        this.matchprice = matchprice;
    }

    public String getChangPrice() {
        return changPrice;
    }

    public void setChangPrice(String changPrice) {
        this.changPrice = changPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getVolMax() {
        return volMax;
    }

    public void setVolMax(String volMax) {
        this.volMax = volMax;
    }

    public String getRateMar() {
        return rateMar;
    }

    public void setRateMar(String rateMar) {
        this.rateMar = rateMar;
    }

    public String getQtyBD() {
        return QtyBD;
    }

    public void setQtyBD(String qtyBD) {
        QtyBD = qtyBD;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPricecolor() {
        return pricecolor;
    }

    public void setPricecolor(String pricecolor) {
        this.pricecolor = pricecolor;
    }

    public String getChangePct() {
        return changePct;
    }

    public void setChangePct(String changePct) {
        this.changePct = changePct;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getCeiling() {
        return ceiling;
    }

    public void setCeiling(String ceiling) {
        this.ceiling = ceiling;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public String getBuy3() {
        return buy3;
    }

    public void setBuy3(String buy3) {
        this.buy3 = buy3;
    }

    public String getBuy3Volume() {
        return buy3Volume;
    }

    public void setBuy3Volume(String buy3Volume) {
        this.buy3Volume = buy3Volume;
    }

    public String getBuy2() {
        return buy2;
    }

    public void setBuy2(String buy2) {
        this.buy2 = buy2;
    }

    public String getBuy2Volume() {
        return buy2Volume;
    }

    public void setBuy2Volume(String buy2Volume) {
        this.buy2Volume = buy2Volume;
    }

    public String getBuy1() {
        return buy1;
    }

    public void setBuy1(String buy1) {
        this.buy1 = buy1;
    }

    public String getBuy1Volume() {
        return buy1Volume;
    }

    public void setBuy1Volume(String buy1Volume) {
        this.buy1Volume = buy1Volume;
    }

    public String getSell1() {
        return sell1;
    }

    public void setSell1(String sell1) {
        this.sell1 = sell1;
    }

    public String getSell1Volume() {
        return sell1Volume;
    }

    public void setSell1Volume(String sell1Volume) {
        this.sell1Volume = sell1Volume;
    }

    public String getSell2() {
        return sell2;
    }

    public void setSell2(String sell2) {
        this.sell2 = sell2;
    }

    public String getSell2Volume() {
        return sell2Volume;
    }

    public void setSell2Volume(String sell2Volume) {
        this.sell2Volume = sell2Volume;
    }

    public String getSell3() {
        return sell3;
    }

    public void setSell3(String sell3) {
        this.sell3 = sell3;
    }

    public String getSell3Volume() {
        return sell3Volume;
    }

    public void setSell3Volume(String sell3Volume) {
        this.sell3Volume = sell3Volume;
    }

    public String getForeignBuy() {
        return foreignBuy;
    }

    public void setForeignBuy(String foreignBuy) {
        this.foreignBuy = foreignBuy;
    }

    public String getForeignSell() {
        return foreignSell;
    }

    public void setForeignSell(String foreignSell) {
        this.foreignSell = foreignSell;
    }

    public String getCenterNo() {
        return centerNo;
    }

    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getTotalQtty() {
        return totalQtty;
    }

    public void setTotalQtty(String totalQtty) {
        this.totalQtty = totalQtty;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
