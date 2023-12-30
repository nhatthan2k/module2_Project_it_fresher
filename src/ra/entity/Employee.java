package ra.entity;

import ra.bussiness.IBussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Date birthOfDate;
    private String email;
    private String phone;
    private String address;
    private int employeeStatus;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Date birthOfDate, String email, String phone, String address, int employeeStatus) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.birthOfDate = birthOfDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(int employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public void inputData(Scanner scanner, IBussiness employeeBussiness) {
        this.employeeId = inputEmployeeId(scanner, employeeBussiness);
        this.employeeName = inputEmployeeName(scanner, employeeBussiness);
        this.birthOfDate = inputBirthDay(scanner);
        this.email = inputEmail(scanner);
        this.phone = inputPhone(scanner);
        this.address = inputAddress(scanner);
        this.employeeStatus = inputStatus(scanner);
    }

    public String inputEmployeeId(Scanner scanner, IBussiness employeeBussiness) {
        do {
            System.out.println("Mã nhân viên(có 5 kí tự):");
            String employeeId = scanner.nextLine();

            if (employeeId.trim().length() == 5) {
                if (employeeBussiness.findById(employeeId) == null) {
                    return employeeId;
                } else {
                    System.err.println("mã nhân viên đã tồn tại! vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã nhân viên có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputEmployeeName(Scanner scanner, IBussiness employeeBussiness) {
        do {
            System.out.println("Tên nhân viên:");
            String employeeName = scanner.nextLine();

            if (employeeName.trim().isEmpty()) {
                System.err.println("Tên nhân viên không được để trống!");
            } else {
                if (employeeBussiness.findByName(employeeName) == null) {
                    return employeeName;
                } else {
                    System.err.println("Tên nhân viên đã tồn tại! vui lòng nhập lại");
                }
            }
        } while (true);
    }

    public Date inputBirthDay(Scanner scanner) {
        System.out.println("Ngày sinh của nhân viên: ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        do {
            try {
                Date birthOfDate = sdf.parse(scanner.nextLine());
                return birthOfDate;
            } catch (Exception e) {
                System.err.println("Ngày tháng nhập vào không đúng định dạng, vui lòng nhập lại!");
            }
        } while (true);
    }

    public String inputEmail(Scanner scanner) {
        do {
            System.out.println("email nhân viên:");
            String email = scanner.nextLine();

            if (email.trim().isEmpty()) {
                System.err.println("email không được để trống!");
            } else {
                return email;
            }
        } while (true);
    }

    public String inputPhone(Scanner scanner) {
        do {
            System.out.println("số điện thoại nhân viên:");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.trim().isEmpty()) {
                System.err.println("số điên thoại không được để trống!");
            } else {
                return phoneNumber;
            }
        } while (true);
    }

    public String inputAddress(Scanner scanner) {
        do {
            System.out.println("địa chỉ nhân viên:");
            String phoneNumber = scanner.nextLine();

            if (phoneNumber.trim().isEmpty()) {
                System.err.println("địa chỉ nhân viên không được để trống!");
            } else {
                return phoneNumber;
            }
        } while (true);
    }

    public int inputStatus(Scanner scanner) {
        do {
            System.out.println("trạng thái nhân viên:");
            try {
                int employeeStatus = Integer.parseInt(scanner.nextLine());

                if (employeeStatus >= 0 && employeeStatus <= 2) {
                    return employeeStatus;
                } else {
                    System.err.println("trạng thái nhân viên chỉ nhận giá trị từ 0 - 2! vui lòng nhập lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("employeeId: %s, employeeName: %s, birthOfDate: %s, email: %s, phone: %s, address: %s, " +
                "employeeStatus: %s\n", this.employeeId, this.employeeName, this.birthOfDate, this.email, this.phone, this.address, this.employeeStatus);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", birthOfDate=" + birthOfDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", employeeStatus=" + employeeStatus +
                '}';
    }

    public void updateData(Scanner scanner, IBussiness employeeBussiness) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât tên nhân viên");
            System.out.println("2. cập nhât ngày sinh");
            System.out.println("3. cập nhât email");
            System.out.println("4. cập nhật số điện thoại");
            System.out.println("5. cập nhật địa chỉ");
            System.out.println("6. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.employeeName = inputEmployeeName(scanner, employeeBussiness);
                        break;
                    case 2:
                        this.birthOfDate = inputBirthDay(scanner);
                        break;
                    case 3:
                        this.email = inputEmail(scanner);
                        break;
                    case 4:
                        this.phone = inputPhone(scanner);
                        break;
                    case 5:
                        this.address = inputAddress(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1-6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }

    public void updateDataStatus(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât trang thái************");
            System.out.println("1. hoat đông");
            System.out.println("2. nghỉ chế độ");
            System.out.println("3. nghỉ việc");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.employeeStatus = 0;
                        isExit = false;
                        break;
                    case 2:
                        this.employeeStatus = 1;
                        isExit = false;
                        break;
                    case 3:
                        this.employeeStatus = 2;
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
}
