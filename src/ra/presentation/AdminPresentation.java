package ra.presentation;

import ra.bussiness.userBussiness;
import ra.entity.Account;

import java.util.Scanner;

public class AdminPresentation {
    public static void adminMenu(Scanner scanner, Account account) {
        boolean isExit = true;

        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************\n" +
                    "1. Quản lý sản phẩm\n" +
                    "2. Quản lý nhân viên\n" +
                    "3. Quản lý tài khoản\n" +
                    "4. Quản lý phiếu nhập\n" +
                    "5. Quản lý phiếu xuất\n" +
                    "6. Quản lý báo cáo\n" +
                    "7. đăng xuất");
            System.out.println("8. thoát");
            System.out.println("lựa chọn của bạn:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ProductPresentation.productMenu(scanner);
                        break;
                    case 2:
                        EmployeePresentation.employeeMenu(scanner);
                        break;
                    case 3:
                        AccountPresentation.accountMenu(scanner);
                        break;
                    case 4:
                        BillInPresentation.BillInMenu(scanner, account);
                        break;
                    case 5:
                        BillOutPresentation.BillOutMenu(scanner, account);
                        break;
                    case 6:
                        ReportPresentation.ReportMenu(scanner);
                        break;
                    case 7:
                        userBussiness.writeAccountToFile(null);
                        isExit = false;
                        break;
                    case 8:
                        System.exit(0);
                    default:
                        System.out.println("vui lòng chọn từ 1 - 8!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExit);
    }
}
