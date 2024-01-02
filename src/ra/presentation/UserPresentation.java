package ra.presentation;

import ra.bussiness.userBussiness;

import java.util.Scanner;

public class UserPresentation {
    public static void userMenu(Scanner scanner) {
        boolean isExit = true;

        do {
            System.out.println("******************WAREHOUSE MANAGEMENT****************\n" +
                    "1. Danh sách phiếu nhập theo trạng thái\n" +
                    "2. Tạo phiếu nhập\n" +
                    "3. Cập nhật phiếu nhập\n" +
                    "4. Tìm kiếm phiếu nhập\n" +
                    "5. Danh sách phiếu xuất theo trạng thái\n" +
                    "6. Tạo phiếu xuất\n" +
                    "7. Cập nhật phiếu xuất\n" +
                    "8. Tìm kiếm phiếu xuất\n" +
                    "9. logOut\n" +
                    "10. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        userBussiness.writeAccountToFile(null);
                        isExit = false;
                        break;
                    case 10:
                        System.exit(0);
                    default:
                        System.out.println("vui lòng chọn từ 1 - 10!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }while (isExit);
    }


}
