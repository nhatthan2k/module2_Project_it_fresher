import ra.bussiness.userBussiness;
import ra.entity.Account;
import ra.presentation.AdminPresentation;
import ra.presentation.UserPresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account account = userBussiness.readAccountFromFile();
        Scanner scanner = new Scanner(System.in);
        if (account != null) {
            if (!account.isPermission()) {
                AdminPresentation.adminMenu(scanner);
            } else {
                UserPresentation.userMenu(scanner, account);
            }
            inputLogin(scanner, account);
        } else {
            inputLogin(scanner, account);
        }
    }

    public static void inputLogin(Scanner scanner, Account account) {
        do {
            System.out.println("***************Đăng nhập**************");
            System.out.println("Tên người dùng:");
            String inputName = scanner.nextLine();
            System.out.println("Mật khẩu:");
            String inputPassWord = scanner.nextLine();

            account = userBussiness.login(inputName, inputPassWord);

            if (account == null) {
                System.err.println("Tên người dùng hoặc mật khẩu sai");
            } else if (!account.isAccStatus()) {
                System.err.println("tài khoản đang bị khóa!");
            } else {
                userBussiness.writeAccountToFile(account);
                if (!account.isPermission()) {
                    AdminPresentation.adminMenu(scanner);
                } else {
                    UserPresentation.userMenu(scanner, account);
                }
            }
        } while (true);
    }
}