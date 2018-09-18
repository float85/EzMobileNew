package mobile.fpts.com.privatefpts.model;


public class ClientBalanceDetails {
    private String LedgerBalance;
    private String CashAdvance;
    private String aCashTransfer;
    private String stockVal;
    private String adhoc;
    private String CashTrading;
    private String PTG;
    private String TMCTC;
    private String SDCTC;
    private String SDCTGD;
    private String SMTH;
    private String remainingQuota;
    private String tienMarpro;
    private String tienMarginDangGiaoDich;
    private String tienDaSuDung;

    public ClientBalanceDetails(String ledgerBalance, String cashAdvance, String aCashTransfer, String stockVal, String adhoc, String cashTrading, String PTG, String TMCTC, String SDCTC, String SDCTGD, String remainingQuota, String tienMarpro, String tienMarginDangGiaoDich, String tienDaSuDung) {
        LedgerBalance = ledgerBalance;
        CashAdvance = cashAdvance;
        this.aCashTransfer = aCashTransfer;
        this.stockVal = stockVal;
        this.adhoc = adhoc;
        CashTrading = cashTrading;
        this.PTG = PTG;
        this.TMCTC = TMCTC;
        this.SDCTC = SDCTC;
        this.SDCTGD = SDCTGD;
        this.remainingQuota = remainingQuota;
        this.tienMarpro = tienMarpro;
        this.tienMarginDangGiaoDich = tienMarginDangGiaoDich;
        this.tienDaSuDung = tienDaSuDung;
    }

    public ClientBalanceDetails() {
    }

    public String getSMTH() {
        return SMTH;
    }

    public void setSMTH(String SMTH) {
        this.SMTH = SMTH;
    }

    public String getTienMarpro() {
        return tienMarpro;
    }

    public void setTienMarpro(String tienMarpro) {
        this.tienMarpro = tienMarpro;
    }

    public String getTienMarginDangGiaoDich() {
        return tienMarginDangGiaoDich;
    }

    public void setTienMarginDangGiaoDich(String tienMarginDangGiaoDich) {
        this.tienMarginDangGiaoDich = tienMarginDangGiaoDich;
    }

    public String getTienDaSuDung() {
        return tienDaSuDung;
    }

    public void setTienDaSuDung(String tienDaSuDung) {
        this.tienDaSuDung = tienDaSuDung;
    }

    public String getRemainingQuota() {
        return remainingQuota;
    }

    public void setRemainingQuota(String remainingQuota) {
        this.remainingQuota = remainingQuota;
    }

    public String getStockVal() {
        return stockVal;
    }

    public void setStockVal(String stockVal) {
        this.stockVal = stockVal;
    }

    public String getaCashTransfer() {
        return aCashTransfer;
    }

    public void setaCashTransfer(String aCashTransfer) {
        this.aCashTransfer = aCashTransfer;
    }

    public String getLedgerBalance() {
        return LedgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        LedgerBalance = ledgerBalance;
    }

    public String getCashAdvance() {
        return CashAdvance;
    }

    public void setCashAdvance(String cashAdvance) {
        CashAdvance = cashAdvance;
    }

    public String getAdhoc() {
        return adhoc;
    }

    public void setAdhoc(String adhoc) {
        this.adhoc = adhoc;
    }

    public String getCashTrading() {
        return CashTrading;
    }

    public void setCashTrading(String cashTrading) {
        CashTrading = cashTrading;
    }

    public String getPTG() {
        return PTG;
    }

    public void setPTG(String PTG) {
        this.PTG = PTG;
    }

    public String getTMCTC() {
        return TMCTC;
    }

    public void setTMCTC(String TMCTC) {
        this.TMCTC = TMCTC;
    }

    public String getSDCTC() {
        return SDCTC;
    }

    public void setSDCTC(String SDCTC) {
        this.SDCTC = SDCTC;
    }

    public String getSDCTGD() {
        return SDCTGD;
    }

    public void setSDCTGD(String SDCTGD) {
        this.SDCTGD = SDCTGD;
    }
}
