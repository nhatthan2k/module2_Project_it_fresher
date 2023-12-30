package ra.entity;

import ra.bussiness.IBussiness;

import java.io.Serializable;
import java.util.Scanner;

public class Account implements Serializable {
    private int accId;
    private String userName;
    private String password;
    private boolean permission;
    private String empId;
    private boolean accStatus;

    public Account() {
    }

    public Account(int accId, String userName, String password, boolean permission, String empId, boolean accStatus) {
        this.accId = accId;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.empId = empId;
        this.accStatus = accStatus;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isAccStatus() {
        return accStatus;
    }

    public void setAccStatus(boolean accStatus) {
        this.accStatus = accStatus;
    }

    public void inputData(Scanner scanner, IBussiness accountBussiness) {

    }
}
