package ra.bussiness;

import ra.entity.Bill;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillBussiness implements IBussiness<Bill, Integer, String, Integer> {
    @Override
    public List<Bill> getAll(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Bill> listBill = null;

        try {
            callSt = conn.prepareCall("{call get_all_bill_in(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listBill = new ArrayList<>();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setBillCode(rs.getString("Bill_Code"));
                bill.setBillType(rs.getBoolean("Bill_Type"));
                bill.setEmployeeIdCreate(rs.getString("Emp_id_created"));
                bill.setCreateDate(rs.getDate("Created"));
                bill.setEmployeeIdAuth(rs.getString("Emp_id_auth"));
                bill.setAuthDate(rs.getDate("Auth_date"));
                bill.setBillStatus(rs.getInt("Bill_Status"));
                listBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBill;
    }

    @Override
    public boolean create(Bill bill) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_bill(?,?,?,?)}");
            callSt.setString(1, bill.getBillCode());
            callSt.setBoolean(2, bill.isBillType());
            callSt.setString(3, bill.getEmployeeIdCreate());
            callSt.setString(4, bill.getEmployeeIdAuth());
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
    public boolean update(Bill bill) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_bill(?,?,?,?,?)}");
            callSt.setInt(1, bill.getBillId());
            callSt.setString(2, bill.getBillCode());
            callSt.setString(3, bill.getEmployeeIdCreate());
            callSt.setString(4, bill.getEmployeeIdAuth());
            callSt.setInt(5, bill.getBillStatus());
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
    public Bill findById(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Bill bill = null;

        try {
            callSt = conn.prepareCall("{call get_Bill_by_id(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setBillCode(rs.getString("Bill_Code"));
                bill.setBillType(rs.getBoolean("Bill_Type"));
                bill.setEmployeeIdCreate(rs.getString("Emp_id_created"));
                bill.setCreateDate(rs.getDate("Created"));
                bill.setEmployeeIdAuth(rs.getString("Emp_id_auth"));
                bill.setAuthDate(rs.getDate("Auth_date"));
                bill.setBillStatus(rs.getInt("Bill_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return bill;
    }

    @Override
    public Bill findByName(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Bill bill = null;

        try {
            callSt = conn.prepareCall("{call get_Bill_by_bill_code(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setBillCode(rs.getString("Bill_Code"));
                bill.setBillType(rs.getBoolean("Bill_Type"));
                bill.setEmployeeIdCreate(rs.getString("Emp_id_created"));
                bill.setCreateDate(rs.getDate("Created"));
                bill.setEmployeeIdAuth(rs.getString("Emp_id_auth"));
                bill.setAuthDate(rs.getDate("Auth_date"));
                bill.setBillStatus(rs.getInt("Bill_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return bill;
    }

    @Override
    public List<Bill> searchName(String s, Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Bill> listBill = null;

        try {
            callSt = conn.prepareCall("{call search_bill_by_code(?,?)}");
            callSt.setString(1, s);
            callSt.setInt(2, integer);
            ResultSet rs = callSt.executeQuery();
            listBill = new ArrayList<>();

            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setBillCode(rs.getString("Bill_Code"));
                bill.setBillType(rs.getBoolean("Bill_Type"));
                bill.setEmployeeIdCreate(rs.getString("Emp_id_created"));
                bill.setCreateDate(rs.getDate("Created"));
                bill.setEmployeeIdAuth(rs.getString("Emp_id_auth"));
                bill.setAuthDate(rs.getDate("Auth_date"));
                bill.setBillStatus(rs.getInt("Bill_Status"));
                listBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBill;
    }

    public static List<Bill> getAllBillOut(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Bill> listBill = null;

        try {
            callSt = conn.prepareCall("{call get_all_bill_out(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listBill = new ArrayList<>();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("Bill_Id"));
                bill.setBillCode(rs.getString("Bill_Code"));
                bill.setBillType(rs.getBoolean("Bill_Type"));
                bill.setEmployeeIdCreate(rs.getString("Emp_id_created"));
                bill.setCreateDate(rs.getDate("Created"));
                bill.setEmployeeIdAuth(rs.getString("Emp_id_auth"));
                bill.setAuthDate(rs.getDate("Auth_date"));
                bill.setBillStatus(rs.getInt("Bill_Status"));
                listBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBill;
    }

    public static boolean BrowseBill(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call Browse_bill(?)}");
            callSt.setInt(1, integer);
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
}
