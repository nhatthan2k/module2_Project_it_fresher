package ra.presentation;

import java.util.Scanner;

public class BillOutPresentation {
    public static void BillOutMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************BILL MANAGEMENT****************\n" +
                    "1. Danh sách phiếu xuất\n" +
                    "2. Tạo phiếu xuất\n" +
                    "3. Cập nhật thông tin phiếu xuất\n" +
                    "4. Chi tiết phiếu xuất\n" +
                    "5. Duyệt phiếu xuất\n" +
                    "6. Tìm kiếm phiếu xuất\n" +
                    "7. Thoát");
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
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 7!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }
}
