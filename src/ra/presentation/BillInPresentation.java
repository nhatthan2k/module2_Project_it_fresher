package ra.presentation;

import ra.bussiness.BillBussiness;
import ra.bussiness.BillDetailBussiness;
import ra.bussiness.IBussiness;
import ra.bussiness.ProductBussiness;
import ra.entity.*;

import java.util.List;
import java.util.Scanner;

import static ra.presentation.ProductPresentation.productBussiness;

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
                        inputUpdateBillInId(scanner);
                        break;
                    case 4:
                        findBillDetail(scanner);
                        break;
                    case 5:
                        inputAcceptBill(scanner);
                        break;
                    case 6:
                        searchBillIn(scanner);
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
        bill.inputData(scanner, true, null);
        boolean result = billBussiness.create(bill);
        if (result) {
            System.out.println("thêm mới phiếu nhập thành công!");

            boolean isExit = true;
            do {
                System.out.println("nhấn phim 1 để thêm chi tiết phiếu, nhấn phím 2 hoàn tất tạo phiếu:");
                System.out.println("lựa chọn của bạn");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            BillDetail billDetail = new BillDetail();
                            Bill billCreate = (Bill) billBussiness.findByName(bill.getBillCode());
                            billDetail.inputData(scanner, billCreate.getBillId());
                            boolean resultBillDetail = billDetailBussiness.create(billDetail);

                            if (resultBillDetail) {
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
            } while (isExit);
        } else {
            System.err.println("thêm mới phiếu nhập thất bại!");
        }
    }

    public static void inputUpdateBillInId(Scanner scanner) {
        System.out.println("Mã phiếu bạn muốn cập nhật:");
        try {
            int updateId = Integer.parseInt(scanner.nextLine());

            updateBillIn(scanner, updateId);

        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        }
    }

    public static void updateBillIn(Scanner scanner, int billId) {
        Bill bill = (Bill) billBussiness.findById(billId);

        if (bill != null && bill.isBillType() == true) {
            if (bill.getBillStatus() == 0 || bill.getBillStatus() == 1) {
                bill.updateData(scanner);
                boolean result = billBussiness.update(bill);
                if (result) {
                    boolean isExit = true;
                    do {
                        System.out.println("nhấn phim 1 tiếp tục cập nhật chi tiết phiếu, nhấn phím 2 hoàn tất cập nhật phiếu:");
                        System.out.println("lựa chọn của bạn:");

                        try {
                            int choice = Integer.parseInt(scanner.nextLine());

                            switch (choice) {
                                case 1:
                                    updateBillDetail(scanner, billId);
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
                    } while (isExit);

                    System.out.println("cập nhật thành công!");
                } else {
                    System.err.println("cập nhật thất bại!");
                }
            } else {
                System.err.println("phiếu này không được phép cập nhật!");
            }
        } else {
            System.err.println("mã phiếu nhập không tồn tại!");
        }
    }

    public static void updateBillDetail(Scanner scanner, int updateId) {
        System.out.println("nhập vào mã chi tiết phiếu cần cập nhật:");
        try {
            int updateBillDetailID = Integer.parseInt(scanner.nextLine());

            List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(updateId);

            boolean isExists = false;

            for (BillDetail billDetail : listBillDetail) {
                if (billDetail.getBillDetailId() == updateBillDetailID) {
                    isExists = true;
                    break;
                }
            }

            if (isExists) {
                BillDetail billDetailUpdate = billDetailBussiness.findById(updateBillDetailID);
                billDetailUpdate.updateData(scanner);
                boolean resultbillDetail = billDetailBussiness.update(billDetailUpdate);
                if (resultbillDetail) {
                    System.out.println("cập nhật chi tiết phiếu thành công!");
                } else {
                    System.err.println("cập nhật chi tiết phiếu thất bại!");
                }
            } else {
                System.err.println("phiếu nhập không tồn tại mã chi tiết phiếu này!");
            }
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void findBillDetail(Scanner scanner) {
        System.out.println("mã phiếu bạn cần hiển thị chi tiết phiếu:");
        try {
            int billId = Integer.parseInt(scanner.nextLine());

            Bill bill = (Bill) billBussiness.findById(billId);

            if (bill != null && bill.isBillType() == true) {
                List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(billId);
                listBillDetail.stream().forEach(System.out::println);
            } else {
                System.err.println("không tồn tại mã phiếu nhập!");
            }
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhâp số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void inputAcceptBill(Scanner scanner) {
        System.out.println("mã phiếu cần duyệt:");
        try {
            int billId = Integer.parseInt(scanner.nextLine());

            acceptBill(scanner, billId);
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhâp số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void acceptBill(Scanner scanner, int billId) {
        Bill bill = (Bill) billBussiness.findById(billId);

        if (bill != null && bill.isBillType() == true) {
            System.out.println("thông tin phiếu bạn muốn duyệt:");
            bill.displayData();
            System.out.println("thông tin chi tiết phiếu:");
            List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(billId);
            listBillDetail.stream().forEach(System.out::println);

            boolean isExit = true;
            do {
                System.out.println("nhấn phim 1 để xác nhận duyệt phiếu, nhấn phím 2 thoát:");
                System.out.println("lựa chọn của bạn:");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        boolean isAcept = BillBussiness.BrowseBill(billId);
                        if (isAcept) {
                            if(listBillDetail.size() > 0) {
                                for (BillDetail billDetail: listBillDetail) {
                                    Product product = (Product) productBussiness.findById(billDetail.getProductID());
                                    int newQuantityProduct = billDetail.getQuantity() + product.getQuantity();
                                    ProductBussiness.updateQuantityProduct(billDetail.getProductID(), newQuantityProduct);
                                }
                            }
                            System.out.println("duyệt thành công!");
                        }else {
                            System.err.println("duyệt thất bại!");
                        }
                        isExit = false;
                        break;
                    case 2:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn 1 trong 2!");
                }

            } while (isExit);

        } else {
            System.err.println("không tồn tại phiếu nhập!");
        }
    }

    public static void searchBillIn(Scanner scanner) {
        System.out.println("mã phiếu bạn tìm kiếm:");
        try {
            int billId = Integer.parseInt(scanner.nextLine());
            Bill bill = (Bill) billBussiness.findById(billId);

            if (bill != null && bill.isBillType() == true) {
                System.out.println("thông tin phiếu bạn muốn tìm kiếm:");
                bill.displayData();
                System.out.println("thông tin chi tiết phiếu:");
                List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(billId);
                listBillDetail.stream().forEach(System.out::println);

                boolean isExit = true;
                do {
                    System.out.println("******************MENU****************\n" +
                            "1. Cập nhật thông tin phiếu nhập\n" +
                            "2. Duyệt phiếu nhập\n" +
                            "3. Thoát");
                    System.out.println("lựa chọn của bạn:");

                    try {
                        int choice = Integer.parseInt(scanner.nextLine());

                        switch (choice) {
                            case 1:
                                updateBillIn(scanner, billId);
                                break;
                            case 2:
                                acceptBill(scanner, billId);
                                break;
                            case 3:
                                isExit = false;
                                break;
                            default:
                                System.out.println("vui lòng chọn từ 1 - 3!");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("vui lòng nhập số nguyên!");
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                } while (isExit);
            }else {
                System.err.println("không tìm thấy phiếu nhập!");
            }

        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
