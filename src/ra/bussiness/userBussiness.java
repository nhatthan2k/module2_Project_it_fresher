package ra.bussiness;

import ra.entity.Account;
import ra.util.ConnectionDB;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class userBussiness {
    public static Account login(String userName, String password) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Account account = null;

        try {
            callSt = conn.prepareCall("{call login(?, ?)}");
            callSt.setString(1, userName);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccId(rs.getInt("Acc_Id"));
                account.setUserName(rs.getString("User_Name"));
                account.setPassword(rs.getString("Password"));
                account.setPermission(rs.getBoolean("Permission"));
                account.setEmpId(rs.getString("Emp_Id"));
                account.setAccStatus(rs.getBoolean("Acc_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return account;
    }

    public static Account readAccountFromFile() {
        Account account = null;
        File file = new File("account.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            account = (Account) ois.readObject();
        } catch (Exception ex) {
            account = new Account();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return account;
    }

    public static void writeAccountToFile(Account account) {
        File file = new File("account.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(account);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
