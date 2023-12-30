package ra.presentation;

import ra.bussiness.EmployeeBussiness;
import ra.bussiness.IBussiness;
import ra.entity.Employee;
import ra.entity.Product;

import java.util.List;
import java.util.Scanner;

public class EmployeePresentation {

    private static IBussiness employeeBussiness = new EmployeeBussiness();
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
        }while (isExit);
    }

    public static void displayEmployee(Scanner scanner) {
        int numPager = 1;
        boolean isExit = true;
        do{
            List<Employee> listEmployee = employeeBussiness.getAll(numPager);
            listEmployee.stream().forEach(System.out::println);

            System.out.println("nhấn phím 1 để xem thêm, phím 2 để thoát");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        numPager ++;
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
        }while (isExit);
    }
    public static void createEmployee(Scanner scanner) {
        Employee employee = new Employee();
        employee.inputData(scanner, employeeBussiness);
        boolean result = employeeBussiness.create(employee);
        if (result) {
            System.out.println("thêm mới thành công!");
        }else {
            System.err.println("thêm mới thất bại!");
        }
    }
    public static void updateEmployee(Scanner scanner) {
        System.out.println("Mã của nhân viên bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Employee employee = (Employee) employeeBussiness.findById(updateId);

            if(employee != null) {
                employee.updateData(scanner, employeeBussiness);
                boolean result = employeeBussiness.update(employee);
                if (result) {
                    System.out.println("cập nhật thành công!");
                }else {
                    System.err.println("cập nhật thất bại!");
                }
            }else {
                System.err.println("mã nhân viên không tồn tại!");
            }
        }else {
            System.err.println("mã nhân viên phải có 5 kí tự!");
        }
    }

    public static void searchEmployee(Scanner scanner) {
        System.out.println("Tên nhân viên bạn muốn tìm kiếm:");
        String searchName = scanner.nextLine();
        int numPager = 1;
        boolean isExit = true;

        do {
            List<Employee> listEmployee = employeeBussiness.searchName(searchName,numPager);

            if(listEmployee.isEmpty() && numPager == 1) {
                System.err.println("không tìm thấy nhân viên!");
                isExit = false;
            }else {
                listEmployee.stream().forEach(System.out::println);
                System.out.println("nhấn phím 1 để xem thêm, phím 2 để thoát");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            numPager ++;
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
        }while (isExit);
    }

    public static void updateStatusEmployee(Scanner scanner) {
        System.out.println("Mã của nhân viên bạn muốn thay đổi:");
        String updateId = scanner.nextLine();

        if (updateId.trim().length() == 5) {
            Employee employee = (Employee) employeeBussiness.findById(updateId);

            if(employee != null) {
                employee.updateDataStatus(scanner);
                boolean result = employeeBussiness.update(employee);
                if (result) {
                    System.out.println("cập nhật thành công!");
                }else {
                    System.err.println("cập nhật thất bại!");
                }
            }else {
                System.err.println("mã nhân viên không tồn tại!");
            }
        }else {
            System.err.println("mã nhân viên phải có 5 kí tự!");
        }
    }
}
