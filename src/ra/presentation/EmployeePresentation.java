package ra.presentation;

import ra.bussiness.AccountBussiness;
import ra.bussiness.EmployeeBussiness;
import ra.bussiness.IBussiness;
import ra.entity.Account;
import ra.entity.Employee;

import java.util.List;
import java.util.Scanner;

import static ra.presentation.AccountPresentation.accountBussiness;

public class EmployeePresentation {

    public static IBussiness employeeBussiness = new EmployeeBussiness();

    public static void employeeMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("******************EMPLOYEE MANAGEMENT****************\n" +
                    "1. Danh sách nhân viên\n" +
                    "2. Thêm mới nhân viên\n" +
                    "3. Cập nhật thông tin nhân viên\n" +
                    "4. Cập nhật trạng thái nhân viên\n" +
                    "5. Tìm kiếm nhân viên\n" +
                    "6. Thoát");
            System.out.println("lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        displayEmployee(scanner);
                        break;
                    case 2:
                        createEmployee(scanner);
                        break;
                    case 3:
                        updateEmployee(scanner);
                        break;
                    case 4:
                        updateStatusEmployee(scanner);
                        break;
                    case 5:
                        searchEmployee(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("vui lòng chọn từ 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }

    public static void formatPrintEmp() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("| Mã nhân viên | Tên nhân viên |  Ngày sinh |       Email       | Số điện thoại |" +
                "   Địa chỉ   | Trạng thái nhân viên |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayEmployee(Scanner scanner) {
        int numPager = 1;
        boolean isExit = true;
        do {
            List<Employee> listEmployee = employeeBussiness.getAll(numPager);
            formatPrintEmp();
            listEmployee.stream().forEach(System.out::println);

            if (listEmployee.size() < 10) {
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

    public static void createEmployee(Scanner scanner) {
        Employee employee = new Employee();
        employee.inputData(scanner);
        boolean result = employeeBussiness.create(employee);
        if (result) {
            System.out.println("thêm mới thành công!");
        } else {
            System.err.println("thêm mới thất bại!");
        }
    }

    public static void updateEmployee(Scanner scanner) {
        System.out.println("Mã của nhân viên bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Employee employee = (Employee) employeeBussiness.findById(updateId);

            if (employee != null) {
                employee.updateData(scanner);
                boolean result = employeeBussiness.update(employee);
                if (result) {
                    System.out.println("cập nhật thành công!");
                } else {
                    System.err.println("cập nhật thất bại!");
                }
            } else {
                System.err.println("mã nhân viên không tồn tại!");
            }
        } else {
            System.err.println("mã nhân viên phải có 5 kí tự!");
        }
    }

    public static void searchEmployee(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Phương thức tìm kiếm nhân viên************");
            System.out.println("1. Tìm kiếm nhân viên theo tên");
            System.out.println("2. Tìm kiếm nhân viên theo mã");
            System.out.println("3. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        searchEmployeeByName(scanner);
                        break;
                    case 2:
                        System.out.println("Mã nhân viên(có 5 kí tự) bạn muốn tìm kiếm:");
                        String employeeId = scanner.nextLine();

                        if (employeeId.trim().length() == 5) {
                            if (employeeBussiness.findById(employeeId) == null) {
                                System.err.println("mã nhân viên không tồn tại!");
                            } else {
                                Employee employee = (Employee) employeeBussiness.findById(employeeId);
                                formatPrintEmp();
                                employee.displayData();
                            }
                        } else {
                            System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
                        }
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

    public static void searchEmployeeByName(Scanner scanner) {
        System.out.println("Tên nhân viên bạn muốn tìm kiếm:");
        String searchName = scanner.nextLine();
        int numPager = 1;
        boolean isExit = true;

        do {
            List<Employee> listEmployee = employeeBussiness.searchName(searchName, numPager);

            if (listEmployee.isEmpty() && numPager == 1) {
                System.err.println("không tìm thấy nhân viên!");
                isExit = false;
            } else {
                formatPrintEmp();
                listEmployee.stream().forEach(System.out::println);
                if (listEmployee.size() < 10) {
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

    public static void updateStatusEmployee(Scanner scanner) {
        System.out.println("Mã của nhân viên bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Employee employee = (Employee) employeeBussiness.findById(updateId);

            if (employee != null) {
                employee.updateDataStatus(scanner);
                boolean result = employeeBussiness.update(employee);
                if (result) {
                    System.out.println("cập nhật thành công!");
                    if (employee.getEmployeeStatus() == 1 || employee.getEmployeeStatus() == 2) {
                        Account account = AccountBussiness.findAccountByEmpId(employee.getEmployeeId());
                        account.setAccStatus(false);
                        boolean resultAcc = accountBussiness.update(account);
                        if (resultAcc) {
                            System.out.println("cập nhật trạng thái tài khoản thành công!");
                        } else {
                            System.err.println("cập nhật trạng thái tài khoản thất bại!");
                        }
                    }
                } else {
                    System.err.println("cập nhật thất bại!");
                }
            } else {
                System.err.println("mã nhân viên không tồn tại!");
            }
        } else {
            System.err.println("mã nhân viên phải có 5 kí tự!");
        }
    }
}
