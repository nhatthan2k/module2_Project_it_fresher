package ra.bussiness;

import ra.entity.Account;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountBussiness implements IBussiness<Account, Integer, String, Integer>{

    @Override
    public List<Account> getAll(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Account> listAccount = null;

        try {
            callSt = conn.prepareCall("{call get_all_account(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listAccount = new ArrayList<>();

            while (rs.next()) {
                Account account = new Account();
                account.setAccId(rs.getInt("Acc_Id"));
                account.setUserName(rs.getString("User_Name"));
                account.setPassword(rs.getString("Password"));
                account.setPermission(rs.getBoolean("Permission"));
                account.setEmpId(rs.getString("Emp_Id"));
                account.setAccStatus(rs.getBoolean("Acc_Status"));
                listAccount.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listAccount;
    }

    @Override
    public boolean create(Account account) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_account(?,?,?,?,?)}");
            callSt.setString(1, account.getUserName());
            callSt.setString(2, account.getPassword());
            callSt.setBoolean(3, account.isPermission());
            callSt.setString(4, account.getEmpId());
            callSt.setBoolean(5, account.isAccStatus());
            callSt.executeUpdate();
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean update(Account account) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_account(?,?,?,?,?,?)}");
            callSt.setInt(1, account.getAccId());
            callSt.setString(2, account.getUserName());
            callSt.setString(3, account.getPassword());
            callSt.setBoolean(4, account.isPermission());
            callSt.setString(5, account.getEmpId());
            callSt.setBoolean(6, account.isAccStatus());
            callSt.executeUpdate();
            conn.commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return result;
    }

    @Override
    public Account findById(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Account account = null;

        try {
            callSt = conn.prepareCall("{call get_account_by_id(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()) {
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

    @Override
    public Account findByName(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Account account = null;

        try {
            callSt = conn.prepareCall("{call get_account_by_userName(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()) {
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

    @Override
    public List<Account> searchName(String s, Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Account> listAccount = null;

        try {
            callSt = conn.prepareCall("{call search_username(?,?)}");
            callSt.setString(1, s);
            callSt.setInt(2,integer);
            ResultSet rs = callSt.executeQuery();
            listAccount = new ArrayList<>();

            if(rs.next()) {
                Account account = new Account();
                account.setAccId(rs.getInt("Acc_Id"));
                account.setUserName(rs.getString("User_Name"));
                account.setPassword(rs.getString("Password"));
                account.setPermission(rs.getBoolean("Permission"));
                account.setEmpId(rs.getString("Emp_Id"));
                account.setAccStatus(rs.getBoolean("Acc_Status"));
                listAccount.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listAccount;
    }

    public Account findAccountByEmpId(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Account account = null;

        try {
            callSt = conn.prepareCall("{call get_account_by_emp_id(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()) {
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
}
