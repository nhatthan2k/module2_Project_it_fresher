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

    public void inputData(Scanner scanner, Boolean billType, String empIdCreate) {
        this.billCode = inputBillCode(scanner);
        this.billType = billType;
        if(empIdCreate == null) {
            this.employeeIdCreate = inputEmpCreate(scanner);
        } else {
            this.employeeIdCreate = empIdCreate;
        }
        this.employeeIdAuth = inputEmpAuth(scanner);

    }

    public void displayData() {
        System.out.printf("billId: %d, billCode: %s, billType: %s, employeeIdCreate: %s, createDate: %s, employeeIdAuth: %s," +
                        "authDate: %s, billStatus: %s\n",
                this.billId, this.billCode, this.billType, this.employeeIdCreate, this.createDate, this.employeeIdAuth, this.authDate, this.billStatus);
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

    public void updateData(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât mã code");
            System.out.println("2. cập nhât mã nhân viên tạo");
            System.out.println("3. cập nhât mã nhân viên duyệt");
            System.out.println("4. cập nhật trạng thái phiếu");
            System.out.println("5. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.billCode = inputBillCode(scanner);
                        break;
                    case 2:
                        this.employeeIdCreate = inputEmpCreate(scanner);
                        break;
                    case 3:
                        this.employeeIdAuth = inputEmpAuth(scanner);
                        break;
                    case 4:
                        updateDataStatus(scanner);
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1-5!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }

    public void updateDataStatus(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât trang thái************");
            System.out.println("1. tạo");
            System.out.println("2. hủy phiếu");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.billStatus = 0;
                        isExit = false;
                        break;
                    case 2:
                        this.billStatus = 1;
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1 trong 2!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (isExit);
    }
}
