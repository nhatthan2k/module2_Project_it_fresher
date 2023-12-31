package ra.presentation;

import ra.bussiness.userBussiness;

import java.util.Scanner;

public class AdminPresentation {
    public static void adminMenu(Scanner scanner) {
        boolean isExit = true;

        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************\n" +
                    "1. Quản lý sản phẩm\n" +
                    "2. Quản lý nhân viên\n" +
                    "3. Quản lý tài khoản\n" +
                    "4. Quản lý phiếu nhập\n" +
                    "5. Quản lý phiếu xuất\n" +
                    "6. Quản lý báo cáo\n" +
                    "7. Thoát");
            System.out.println("8. logout");
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
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        System.exit(0);
                    case 8:
                        userBussiness.writeAccountToFile(null);
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 8!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }while (isExit);
    }
}
