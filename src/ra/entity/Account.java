package ra.entity;

import ra.bussiness.AccountBussiness;
import ra.bussiness.IBussiness;

import java.io.Serializable;
import java.util.Scanner;

import static ra.presentation.AccountPresentation.accountBussiness;
import static ra.presentation.EmployeePresentation.employeeBussiness;

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

    public void inputData(Scanner scanner) {
        this.userName = inputUserName(scanner);
        this.password = inputPassword(scanner);
        this.permission = inputPermission(scanner);
        this.empId = inputEmpId(scanner);
        this.accStatus = inputAccountStatus(scanner);
    }

    public void displayData() {
        System.out.printf("| %12d | %13s | %10s | %15s | %12s | %20s |\n",
                this.accId, this.userName, this.password, this.permission ? "Admin" : "User", this.empId, this.accStatus ? "Hoạt động" : "Bị khóa");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("| %12d | %13s | %10s | %15s | %12s | %20s |\n" +
                        "-----------------------------------------------------------------------------------------------------",
                this.accId, this.userName, this.password, this.permission ? "Admin" : "User", this.empId, this.accStatus ? "Hoạt động" : "Bị khóa");
    }

    public String inputUserName(Scanner scanner) {
        do {
            System.out.println("Tên tài khoản:");
            String accountName = scanner.nextLine();

            if (accountName.trim().isEmpty()) {
                System.err.println("Tên tài khoản không được để trống!");
            } else {
                if (accountBussiness.findByName(accountName) == null) {
                    return accountName;
                } else {
                    System.err.println("tên tài khoản đã tồn tại! vui lòng nhập lại");
                }
            }
        } while (true);
    }

    public String inputPassword(Scanner scanner) {
        do {
            System.out.println("mật khẩu:");
            String password = scanner.nextLine();

            if (password.trim().isEmpty()) {
                System.err.println("Tên nhân viên không được để trống!");
            } else {
                return password;
            }
        } while (true);
    }

    public boolean inputPermission(Scanner scanner) {
        do {
            System.out.println("Quyền tài khoản:");
            String Permission = scanner.nextLine();

            if (Permission.trim().equalsIgnoreCase("true") || Permission.trim().equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(Permission);
            } else {
                System.err.println("Quyền tài khoản tài khoản chỉ nhận giá là true hoặc false!");
            }
        } while (true);
    }

    public String inputEmpId(Scanner scanner) {
        do {
            System.out.println("mã nhân viên sở hữu tài khoản!");
            String empId = scanner.nextLine();

            if (empId.trim().length() == 5) {
                if (AccountBussiness.findAccountByEmpId(empId) == null) {
                    if (employeeBussiness.findById(empId) == null) {
                        System.err.println("nhân viên không tồn tại! vui lòng nhập lại");
                    } else {
                        return empId;
                    }
                } else {
                    System.err.println("mã nhân viên sở hữu tài khoản đã tồn tại!");
                }
            } else {
                System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public boolean inputAccountStatus(Scanner scanner) {
        do {
            System.out.println("Trạng thái của tài khoản:");
            String accountStatus = scanner.nextLine();

            if (accountStatus.trim().equalsIgnoreCase("true") || accountStatus.trim().equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(accountStatus);
            } else {
                System.err.println("Trạng thái của tài khoản chỉ nhận giá là true hoặc false!");
            }
        } while (true);
    }

    public void updateDataStatus(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât trang thái************");
            System.out.println("1. hoat đông");
            System.out.println("2. Khóa");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.accStatus = true;
                        isExit = false;
                        break;
                    case 2:
                        this.accStatus = false;
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng lựa chọn từ 1 trong 2!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }
}
