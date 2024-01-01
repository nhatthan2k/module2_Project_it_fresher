package ra.entity;

import ra.bussiness.AccountBussiness;

import java.util.Date;
import java.util.Scanner;

import static ra.presentation.AccountPresentation.accountBussiness;
import static ra.presentation.BillInPresentation.billBussiness;
import static ra.presentation.EmployeePresentation.employeeBussiness;

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

    public void inputData(Scanner scanner, Boolean billType) {
        this.billCode = inputBillCode(scanner);
        this.billType = billType;
        this.employeeIdCreate = inputEmpCreate(scanner);
        this.employeeIdAuth = inputEmpAuth(scanner);

    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", billType=" + billType +
                ", employeeIdCreate='" + employeeIdCreate + '\'' +
                ", createDate=" + createDate +
                ", employeeIdAuth='" + employeeIdAuth + '\'' +
                ", authDate=" + authDate +
                ", billStatus=" + billStatus +
                '}';
    }

    public String inputBillCode(Scanner scanner) {
        do {
            System.out.println("mã code:");
            String billCode = scanner.nextLine();

            if (billCode.trim().isEmpty()) {
                System.err.println("mã code không được để trống!");
            } else {
                if (billBussiness.findByName(billCode) == null) {
                    return billCode;
                } else {
                    System.err.println("mã code đã tồn tại! vui lòng nhập lại");
                }
            }
        } while (true);
    }

    public String inputEmpCreate(Scanner scanner) {
        do {
            System.out.println("mã nhân viên tạo phiếu:");
            String empIdCreate = scanner.nextLine();

            if (empIdCreate.trim().length() == 5) {
                if (employeeBussiness.findById(empIdCreate) == null) {
                    System.err.println("nhân viên không tồn tại! vui lòng nhập lại");
                } else {
                    return empIdCreate;
                }
            } else {
                System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputEmpAuth(Scanner scanner) {
        do {
            System.out.println("mã nhân viên duyệt phiếu:");
            String empIdAuth = scanner.nextLine();

            if (empIdAuth.trim().length() == 5) {
                if (employeeBussiness.findById(empIdAuth) == null) {
                    System.err.println("nhân viên không tồn tại! vui lòng nhập lại");
                } else {
                    return empIdAuth;
                }
            } else {
                System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }
}
