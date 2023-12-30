package ra.bussiness;

import ra.entity.Product;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBussiness implements IBussiness<Product, String, String, Integer>{

    @Override
    public List<Product> getAll(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Product> listProduct = null;

        try {
            callSt = conn.prepareCall("call get_all_product(?)");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listProduct = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("Product_Id"));
                product.setProductName(rs.getString("Product_Name"));
                product.setManufacturer(rs.getString("Manufacturer"));
                product.setCreatedDate(rs.getDate("Created"));
                product.setBatch(rs.getInt("Batch"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getBoolean("Product_Status"));

                listProduct.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listProduct;
    }

    @Override
    public boolean create(Product product) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_product(?,?,?,?,?)}");
            callSt.setString(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setString(3, product.getManufacturer());
            callSt.setInt(4, product.getBatch());
            callSt.setBoolean(5, product.isStatus());
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
    public boolean update(Product product) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?)}");
            callSt.setString(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setString(3, product.getManufacturer());
            callSt.setInt(4, product.getBatch());
            callSt.setBoolean(5, product.isStatus());
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
    public Product findById(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Product product = null;
        try {
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getString("Product_Id"));
                product.setProductName(rs.getString("Product_Name"));
                product.setManufacturer(rs.getString("Manufacturer"));
                product.setCreatedDate(rs.getDate("Created"));
                product.setBatch(rs.getInt("Batch"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getBoolean("Product_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public Product findByName(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Product product = null;
        try {
            callSt = conn.prepareCall("{call get_product_by_name(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getString("Product_Id"));
                product.setProductName(rs.getString("Product_Name"));
                product.setManufacturer(rs.getString("Manufacturer"));
                product.setCreatedDate(rs.getDate("Created"));
                product.setBatch(rs.getInt("Batch"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getBoolean("Product_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public List<Product> searchName(String s, Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Product> listproduct = null;
        try {
            callSt = conn.prepareCall("{call search_product_name(?,?)}");
            callSt.setString(1, s);
            callSt.setInt(2, integer);
            ResultSet rs = callSt.executeQuery();
            listproduct = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("Product_Id"));
                product.setProductName(rs.getString("Product_Name"));
                product.setManufacturer(rs.getString("Manufacturer"));
                product.setCreatedDate(rs.getDate("Created"));
                product.setBatch(rs.getInt("Batch"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setStatus(rs.getBoolean("Product_Status"));
                listproduct.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listproduct;
    }
}
