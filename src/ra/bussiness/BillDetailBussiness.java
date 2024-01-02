package ra.bussiness;

import ra.entity.Bill;
import ra.entity.BillDetail;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailBussiness implements IBussiness<BillDetail, Integer, String, Integer> {
    @Override
    public List<BillDetail> getAll(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<BillDetail> listBillDetail = null;

        try {
            callSt = conn.prepareCall("{call get_all_bill_detail(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listBillDetail = new ArrayList<>();

            while (rs.next()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                billDetail.setBillId(rs.getInt("Bill_Id"));
                billDetail.setProductID(rs.getString("Product_Id"));
                billDetail.setQuantity(rs.getInt("Quantity"));
                billDetail.setPrice(rs.getFloat("Price"));
                listBillDetail.add(billDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBillDetail;
    }

    @Override
    public boolean create(BillDetail billDetail) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_bill_detail(?,?,?,?)}");
            callSt.setInt(1, billDetail.getBillId());
            callSt.setString(2, billDetail.getProductID());
            callSt.setInt(3, billDetail.getQuantity());
            callSt.setFloat(4, billDetail.getPrice());
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
    public boolean update(BillDetail billDetail) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_bill_detail(?,?,?,?,?)}");
            callSt.setInt(1, billDetail.getBillDetailId());
            callSt.setInt(2, billDetail.getBillId());
            callSt.setString(3, billDetail.getProductID());
            callSt.setInt(4, billDetail.getQuantity());
            callSt.setFloat(5, billDetail.getPrice());
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
    public BillDetail findById(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        BillDetail billDetail = null;

        try {
            callSt = conn.prepareCall("{call get_Bill_detail_by_id(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                billDetail = new BillDetail();
                billDetail.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                billDetail.setBillId(rs.getInt("Bill_Id"));
                billDetail.setProductID(rs.getString("Product_Id"));
                billDetail.setQuantity(rs.getInt("Quantity"));
                billDetail.setPrice(rs.getFloat("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return billDetail;
    }

    @Override
    public BillDetail findByName(String s) {
        return null;
    }

    @Override
    public List<BillDetail> searchName(String s, Integer integer) {
        return null;
    }

    public static List<BillDetail> findByBillId(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<BillDetail> listBillDetail = null;

        try {
            callSt = conn.prepareCall("{call get_Bill_detail_by_bill_id(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listBillDetail = new ArrayList<>();

            while (rs.next()) {
                BillDetail billDetail = new BillDetail();
                billDetail.setBillDetailId(rs.getInt("Bill_Detail_Id"));
                billDetail.setBillId(rs.getInt("Bill_Id"));
                billDetail.setProductID(rs.getString("Product_Id"));
                billDetail.setQuantity(rs.getInt("Quantity"));
                billDetail.setPrice(rs.getFloat("Price"));
                listBillDetail.add(billDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listBillDetail;
    }
}
