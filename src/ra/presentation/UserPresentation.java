package ra.presentation;

import ra.bussiness.BillBussiness;
import ra.bussiness.BillDetailBussiness;
import ra.bussiness.userBussiness;
import ra.entity.Account;
import ra.entity.Bill;
import ra.entity.BillDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.BillInPresentation.billBussiness;
import static ra.presentation.BillInPresentation.billDetailBussiness;

public class UserPresentation {
    public static void userMenu(Scanner scanner, Account account) {
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
                    "9. Đăng xuất\n" +
                    "10. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayBillInByStatus(account);
                        break;
                    case 2:
                        createBillIn(scanner, account);
                        break;
                    case 3:
                        inputUpdateBillInId(scanner, account);
                        break;
                    case 4:
                        searchBillIn(scanner, account);
                        break;
                    case 5:
                        displayBillOutByStatus(account);
                        break;
                    case 6:
                        createBillOut(scanner, account);
                        break;
                    case 7:
                        inputUpdateBillOutId(scanner, account);
                        break;
                    case 8:
                        searchBillOut(scanner, account);
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

        } while (isExit);
    }

    public static void formatPrintBill() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Mã phiếu |  Mã code |  Loại phiếu  | Mã nhân viên nhập |  Ngày tạo  |" +
                " Mã nhân viên duyệt | Ngày duyệt | Trạng thái phiếu |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }

    public static void formatPrintBillDetail() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Mã phiếu chi tiết | Mã phiếu xuất | Mã sản phẩm | Số lượng xuất | Giá xuất |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayBillInByStatus(Account account) {
        List<Bill> listBillInCreate = BillBussiness.getAllBillInByStatus(0, account.getEmpId());
        List<Bill> listBillInCancel = BillBussiness.getAllBillInByStatus(1, account.getEmpId());
        List<Bill> listBillInAccept = BillBussiness.getAllBillInByStatus(2, account.getEmpId());
        System.out.println("danh sách phiếu nhập ở trạng thái tạo:");
        formatPrintBill();
        listBillInCreate.stream().forEach(System.out::println);
        System.out.println("danh sách phiếu nhập ở trạng thái hủy:");
        formatPrintBill();
        listBillInCancel.stream().forEach(System.out::println);
        System.out.println("danh sách phiếu nhập ở trạng thái duyệt:");
        formatPrintBill();
        listBillInAccept.stream().forEach(System.out::println);
    }

    public static void createBillIn(Scanner scanner, Account account) {
        Bill bill = new Bill();
        bill.inputData(scanner, true, account.getEmpId());
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

    public static void inputUpdateBillInId(Scanner scanner, Account account) {
        System.out.println("Mã phiếu bạn muốn cập nhật:");
        try {
            int updateId = Integer.parseInt(scanner.nextLine());

            updateBillIn(scanner, updateId, account);

        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        }
    }

    public static void updateBillIn(Scanner scanner, int billId, Account account) {
        Bill bill = (Bill) billBussiness.findById(billId);

        if (bill != null && bill.isBillType() == true) {
            if (bill.getEmployeeIdCreate() == account.getEmpId()) {
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
                System.err.println("bạn chỉ có thể sửa phiếu nhập của chính bạn!");
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

    public static void searchBillIn(Scanner scanner, Account account) {
        System.out.println("mã phiếu bạn tìm kiếm:");
        try {
            int billId = Integer.parseInt(scanner.nextLine());
            Bill bill = (Bill) billBussiness.findById(billId);

            if (bill != null && bill.isBillType() == true) {
                if (bill.getEmployeeIdCreate() == account.getEmpId()) {
                    System.out.println("thông tin phiếu bạn muốn tìm kiếm:");
                    formatPrintBill();
                    bill.displayData();
                    System.out.println("thông tin chi tiết phiếu:");
                    List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(billId);
                    formatPrintBillDetail();
                    listBillDetail.stream().forEach(System.out::println);
                }else {
                    System.err.println("bạn chỉ có thể tìm phiếu của chính mình!");
                }
            }else {
                System.err.println("không tìm thấy phiếu nhập!");
            }
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void displayBillOutByStatus(Account account) {
        List<Bill> listBillOutCreate = BillBussiness.getAllBillOutByStatus(0, account.getEmpId());
        List<Bill> listBillOutCancel = BillBussiness.getAllBillOutByStatus(1, account.getEmpId());
        List<Bill> listBillOutAccept = BillBussiness.getAllBillOutByStatus(2, account.getEmpId());
        System.out.println("danh sách phiếu xuất ở trạng thái tạo:");
        formatPrintBill();
        listBillOutCreate.stream().forEach(System.out::println);
        System.out.println("danh sách phiếu xuất ở trạng thái hủy:");
        formatPrintBill();
        listBillOutCancel.stream().forEach(System.out::println);
        System.out.println("danh sách phiếu xuất ở trạng thái duyệt:");
        formatPrintBill();
        listBillOutAccept.stream().forEach(System.out::println);
    }

    public static void createBillOut(Scanner scanner, Account account) {
        Bill bill = new Bill();
        bill.inputData(scanner, false, account.getEmpId());
        boolean result = billBussiness.create(bill);
        if (result) {
            System.out.println("thêm mới phiếu xuất thành công!");

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

    public static void inputUpdateBillOutId(Scanner scanner, Account account) {
        System.out.println("Mã phiếu bạn muốn cập nhật:");
        try {
            int updateId = Integer.parseInt(scanner.nextLine());

            updateBillOut(scanner, updateId, account);

        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        }
    }

    public static void updateBillOut(Scanner scanner, int billId, Account account) {
        Bill bill = (Bill) billBussiness.findById(billId);

        if (bill != null && bill.isBillType() == false) {
            if (bill.getEmployeeIdCreate() == account.getEmpId()) {
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
                                        updateBillOutDetail(scanner, billId);
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
                System.err.println("bạn chỉ có thể sửa phiếu nhập của chính bạn!");
            }

        } else {
            System.err.println("mã phiếu nhập không tồn tại!");
        }
    }

    public static void updateBillOutDetail(Scanner scanner, int updateId) {
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

    public static void searchBillOut(Scanner scanner, Account account) {
        System.out.println("mã phiếu bạn tìm kiếm:");
        try {
            int billId = Integer.parseInt(scanner.nextLine());
            Bill bill = (Bill) billBussiness.findById(billId);

            if (bill != null && bill.isBillType() == false) {
                if (bill.getEmployeeIdCreate() == account.getEmpId()) {
                    System.out.println("thông tin phiếu bạn muốn tìm kiếm:");
                    formatPrintBill();
                    bill.displayData();
                    System.out.println("thông tin chi tiết phiếu:");
                    List<BillDetail> listBillDetail = BillDetailBussiness.findByBillId(billId);
                    formatPrintBillDetail();
                    listBillDetail.stream().forEach(System.out::println);
                }else {
                    System.err.println("bạn chỉ có thể tìm phiếu của chính mình!");
                }
            }else {
                System.err.println("không tìm thấy phiếu xuất!");
            }
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
