package ra.entity;

import java.util.Date;

public class Bill {
    private int billId;
    private String billCode;
    private boolean billType;
    private String employeeIdCreate;
    private Date createDate;
    private String employeeIdAuth;
    private Date authDate;
    private int billStatus;

    public Bill() {
    }

    public Bill(int billId, String billCode, boolean billType, String employeeIdCreate, Date createDate, String employeeIdAuth, Date authDate, int billStatus) {
        this.billId = billId;
        this.billCode = billCode;
        this.billType = billType;
        this.employeeIdCreate = employeeIdCreate;
        this.createDate = createDate;
        this.employeeIdAuth = employeeIdAuth;
        this.authDate = authDate;
        this.billStatus = billStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public boolean isBillType() {
        return billType;
    }

    public void setBillType(boolean billType) {
        this.billType = billType;
    }

    public String getEmployeeIdCreate() {
        return employeeIdCreate;
    }

    public void setEmployeeIdCreate(String employeeIdCreate) {
        this.employeeIdCreate = employeeIdCreate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmployeeIdAuth() {
        return employeeIdAuth;
    }

    public void setEmployeeIdAuth(String employeeIdAuth) {
        this.employeeIdAuth = employeeIdAuth;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }
}
