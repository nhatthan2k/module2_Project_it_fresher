package ra.presentation;

import java.util.Scanner;

public class ReportPresentation {
    public static void ReportMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("1. Thống kê chi phí theo ngày, tháng, năm\n" +
                    "2. Thống kê chi phí theo khoảng thời gian\n" +
                    "3. Thống kê doanh thu theo ngày, tháng, năm\n" +
                    "4. Thống kê doanh thu theo khoảng thời gian\n" +
                    "5. Thống kê số nhân viên theo từng trạng thái\n" +
                    "6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian\n" +
                    "7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian\n" +
                    "8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian\n" +
                    "9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian\n" +
                    "10.Thoát");
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
                        break;
                    case 10:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 10!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }
}
