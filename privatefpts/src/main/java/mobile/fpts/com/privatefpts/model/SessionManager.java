package mobile.fpts.com.privatefpts.model;

public class SessionManager {
    private String userName;
    private String passWord;
    private String fullName;
    private String sessionNo;
    private String clientCode;
    private String message;
    private String message2;
    private int logInResult;
    private int checkEzTransfer;
    private int checkEzOddlot;
    private int checkMar;
    private int checkMor;
    private int checkEzAdvance;
    private int checkEzTrading;
    private int checkMarPro;
    private static SessionManager _instanceOfSession;

    static {
        _instanceOfSession = new SessionManager();
    }

    public static SessionManager getInstance() {
        return _instanceOfSession;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSessionNo() {
        return sessionNo;
    }

    public void setSessionNo(String sessionNo) {
        this.sessionNo = sessionNo;
    }

    public int getLogInResult() {
        return logInResult;
    }

    public void setLogInResult(int logInResult) {
        this.logInResult = logInResult;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public int getCheckEzTransfer() {
        return checkEzTransfer;
    }

    public void setCheckEzTransfer(int checkEzTransfer) {
        this.checkEzTransfer = checkEzTransfer;
    }

    public int getCheckEzOddlot() {
        return checkEzOddlot;
    }

    public void setCheckEzOddlot(int checkEzOddlot) {
        this.checkEzOddlot = checkEzOddlot;
    }

    public int getCheckMar() {
        return checkMar;
    }

    public void setCheckMar(int checkMar) {
        this.checkMar = checkMar;
    }

    public int getCheckMor() {
        return checkMor;
    }

    public void setCheckMor(int checkMor) {
        this.checkMor = checkMor;
    }

    public int getCheckEzAdvance() {
        return checkEzAdvance;
    }

    public void setCheckEzAdvance(int checkEzAdvance) {
        this.checkEzAdvance = checkEzAdvance;
    }

    public int getCheckEzTrading() {
        return checkEzTrading;
    }

    public void setCheckEzTrading(int checkEzTrading) {
        this.checkEzTrading = checkEzTrading;
    }

    public int getCheckMarPro() {
        return checkMarPro;
    }

    public void setCheckMarPro(int checkMarPro) {
        this.checkMarPro = checkMarPro;
    }
}
