package mobile.fpts.com.privatefpts.model;


import mobile.fpts.com.privatefpts.common.CommonData;

public class ProductInfo {

    private String product;
    private String status;

    public ProductInfo() {
        reset();
    }

    public void reset() {
        product = "";
        status = "";
    }

    public String product() {
        return this.product;
    }

    public void product(String product) {
        this.product = product;
    }

    public String status() {
        return this.status;
    }

    public void status(String status) {
        this.status = status;
    }

    public ProductInfo(String scripData) {
        // TODO Auto-generated constructor stub
        reset();
        this.product = CommonData.getValue(scripData, "product");
        this.status = CommonData.getValue(scripData, "status");
    }
}
