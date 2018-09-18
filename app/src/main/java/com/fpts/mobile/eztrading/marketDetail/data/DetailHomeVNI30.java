package com.fpts.mobile.eztrading.marketDetail.data;

public class DetailHomeVNI30 {
    private String date;
    private String strStatus;
    private String index;
    private String strArrow;
    private String indexChange;
    private String indexChangePer;
    private String tongKL;
    private String tongGT;
    private String somaTangtran;
    private String somaTang;
    private String somaKhongdoi;
    private String somaGiam;

    public DetailHomeVNI30(String date, String strStatus, String index, String strArrow, String indexChange, String indexChangePer, String tongKL, String tongGT, String somaTangtran, String somaTang, String somaKhongdoi, String somaGiam) {
        this.date = date;
        this.strStatus = strStatus;
        this.index = index;
        this.strArrow = strArrow;
        this.indexChange = indexChange;
        this.indexChangePer = indexChangePer;
        this.tongKL = tongKL;
        this.tongGT = tongGT;
        this.somaTangtran = somaTangtran;
        this.somaTang = somaTang;
        this.somaKhongdoi = somaKhongdoi;
        this.somaGiam = somaGiam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStrArrow() {
        return strArrow;
    }

    public void setStrArrow(String strArrow) {
        this.strArrow = strArrow;
    }

    public String getIndexChange() {
        return indexChange;
    }

    public void setIndexChange(String indexChange) {
        this.indexChange = indexChange;
    }

    public String getIndexChangePer() {
        return indexChangePer;
    }

    public void setIndexChangePer(String indexChangePer) {
        this.indexChangePer = indexChangePer;
    }

    public String getTongKL() {
        return tongKL;
    }

    public void setTongKL(String tongKL) {
        this.tongKL = tongKL;
    }

    public String getTongGT() {
        return tongGT;
    }

    public void setTongGT(String tongGT) {
        this.tongGT = tongGT;
    }

    public String getSomaTangtran() {
        return somaTangtran;
    }

    public void setSomaTangtran(String somaTangtran) {
        this.somaTangtran = somaTangtran;
    }

    public String getSomaTang() {
        return somaTang;
    }

    public void setSomaTang(String somaTang) {
        this.somaTang = somaTang;
    }

    public String getSomaKhongdoi() {
        return somaKhongdoi;
    }

    public void setSomaKhongdoi(String somaKhongdoi) {
        this.somaKhongdoi = somaKhongdoi;
    }

    public String getSomaGiam() {
        return somaGiam;
    }

    public void setSomaGiam(String somaGiam) {
        this.somaGiam = somaGiam;
    }
}
