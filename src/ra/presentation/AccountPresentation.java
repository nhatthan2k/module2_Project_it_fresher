package ra.presentation;

import ra.bussiness.AccountBussiness;
import ra.bussiness.IBussiness;
import ra.entity.Account;
import ra.entity.Employee;

import java.util.List;
import java.util.Scanner;

import static ra.presentation.EmployeePresentation.employeeBussiness;

public class AccountPresentation {
    public static IBussiness accountBussiness = new AccountBussiness();

    public static void accountMenu(Scanner scanner) {
        boolean isExit = true;

        do {
            System.out.println("******************ACCOUNT MANAGEMENT****************\n" +
                    "1. Danh sách tài khoản\n" +
                    "2. Tạo tài khoản mới\n" +
                    "3. Cập nhật trạng thái tài khoản\n" +
                    "4. Tìm kiếm tài khoản\n" +
                    "5. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayAccount(scanner);
                        break;
                    case 2:
                        createAccount(scanner);
                        break;
                    case 3:
                        updateStatusAccount(scanner);
                        break;
                    case 4:
                        searchAccount(scanner);
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 5!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }

    public static void formatPrintAcc() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("| Mã tài khoản | Tên tài khoản |  Mật khẩu  | Quyền tài khoản | Mã nhân viên | Trạng thái tài khoản |");
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public static void displayAccount(Scanner scanner) {
        int numPager = 1;
        boolean isExit = true;
        do {
            List<Account> listAccount = accountBussiness.getAll(numPager);
            formatPrintAcc();
            listAccount.stream().forEach(System.out::println);

            if (listAccount.size() < 10) {
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

       public static void createAccount(Scanner scanner) {
        Account account = new Account();
        account.inputData(scanner);
        boolean result = accountBussiness.create(account);
        if (result) {
            System.out.println("thêm mới thành công!");
        } else {
            System.err.println("thêm mới thất bại!");
        }
    }

    public static void updateStatusAccount(Scanner scanner) {
        System.out.println("Mã tài khoản bạn muốn thay đổi:");
        try {
            int updateId = Integer.parseInt(scanner.nextLine());

            Account account = (Account) accountBussiness.findById(updateId);

            if (account != null) {
                account.updateDataStatus(scanner);
                boolean result = accountBussiness.update(account);
                if (result) {
                    System.out.println("cập nhật thành công!");
                } else {
                    System.err.println("cập nhật thất bại!");
                }
            } else {
                System.err.println("mã nhân viên không tồn tại!");
            }
        } catch (NumberFormatException e) {
            System.err.println("vui lòng nhập số nguyên!");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void searchAccount(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Phương thức tìm kiếm nhân viên************");
            System.out.println("1. Tìm kiếm nhân viên theo userName");
            System.out.println("2. Tìm kiếm nhân viên theo tên nhân viên");
            System.out.println("3. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        searchAccountByUserName(scanner);
                        break;
                    case 2:
                        searchAccountByEmployeeName(scanner);
                        break;
                    case 3:
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1 - 3!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }

    public static void searchAccountByUserName(Scanner scanner) {
        System.out.println("tên người dùng bạn muốn tìm kiếm:");
        String searchName = scanner.nextLine();
        int numPager = 1;
        boolean isExit = true;

        do {
            List<Account> listAccount = accountBussiness.searchName(searchName, numPager);

            if (listAccount.isEmpty() && numPager == 1) {
                System.err.println("không tìm thấy tài khoản!");
                isExit = false;
            } else {
                formatPrintAcc();
                listAccount.stream().forEach(System.out::println);
                if (listAccount.size() < 10) {
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
            }
        } while (isExit);
    }

    public static void searchAccountByEmployeeName(Scanner scanner) {
        System.out.println("tên nhân viên bạn muốn tìm kiếm tài khoản:");
        String employeeName = scanner.nextLine();

        int numPager = 1;
        boolean isExit = true;

        do {
            List<Account> listAccount = AccountBussiness.searchAccountbyEmpName(employeeName, numPager);

            if (listAccount.isEmpty() && numPager == 1) {
                System.err.println("không tìm thấy tài khoản!");
                isExit = false;
            } else {
                formatPrintAcc();
                listAccount.stream().forEach(System.out::println);
                if (listAccount.size() < 10) {
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
            }
        } while (isExit);
    }
}
