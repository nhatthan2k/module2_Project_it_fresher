package ra.presentation;

import ra.bussiness.BillBussiness;
import ra.bussiness.BillDetailBussiness;
import ra.bussiness.IBussiness;
import ra.entity.Account;
import ra.entity.Bill;
import ra.entity.BillDetail;

import java.util.List;
import java.util.Scanner;

public class BillInPresentation {

    public static IBussiness billBussiness = new BillBussiness();

    public static BillDetailBussiness billDetailBussiness = new BillDetailBussiness();
    public static void BillInMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************RECEIPT MANAGEMENT****************\n" +
                    "1. Danh sách phiếu nhập\n" +
                    "2. Tạo phiếu nhập\n" +
                    "3. Cập nhật thông tin phiếu nhập\n" +
                    "4. Chi tiết phiếu nhập\n" +
                    "5. Duyệt phiếu nhập\n" +
                    "6. Tìm kiếm phiếu nhập\n" +
                    "7. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayBillIn(scanner);
                        break;
                    case 2:
                        createBillIn(scanner);
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

    public static void displayBillIn(Scanner scanner) {
        int numPager = 1;
        boolean isExit = true;
        do {
            List<Bill> listBillIn = billBussiness.getAll(numPager);
            listBillIn.stream().forEach(System.out::println);

            if (listBillIn.size() < 10) {
                isExit = false;
            } else {
                System.out.println("nhấn phím 1 để xem thêm, phím 2 để thoát");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            numPager++;
                            break;
                        case 2:
                            isExit = false;
                            break;
                        default:
                            System.out.println("vui lòng chọn 1 trong 2 lưa chọn trên!");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("vui lòng nhập số nguyên!");
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } while (isExit);
    }

    public static void createBillIn(Scanner scanner) {
        Bill bill = new Bill();
        bill.inputData(scanner, true);
        boolean result = billBussiness.create(bill);
        if (result) {
            System.out.println("thêm mới phiếu nhập thành công!");

            boolean isExit = true;
            do{
                System.out.println("nhấn phim 1 để thêm chi tiết phiếu, nhấn phím 2 hoàn tất tạo phiếu:");
                System.out.println("lựa chọn của bạn");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            BillDetail billDetail = new BillDetail();
                            billDetail.inputData(scanner);
                            boolean resultBillDetail = billDetailBussiness.create(billDetail);

                            if(resultBillDetail) {
                                System.out.println("thêm mới chi tiết phiếu thành công!");
                            } else {
                                System.err.println("thêm mới chi tiết phiếu thất bại!");
                            }
                            break;
                        case 2:
                            isExit = false;
                            break;
                        default:
                            System.out.println("vui lòng chọn 1 trong 2!");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("vui lòng nhập số nguyên!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }while (isExit);
        } else {
            System.err.println("thêm mới phiếu nhập thất bại!");
        }
    }
}
