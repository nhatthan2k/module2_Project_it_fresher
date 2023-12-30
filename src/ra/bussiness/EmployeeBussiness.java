package ra.bussiness;

import ra.entity.Employee;
import ra.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBussiness implements IBussiness<Employee, String, String, Integer> {
    @Override
    public List<Employee> getAll(Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Employee> listEmployee = null;

        try {
            callSt = conn.prepareCall("call get_all_employee(?)");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            listEmployee = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getString("Emp_Id"));
                employee.setEmployeeName(rs.getString("Emp_Name"));
                employee.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhone(rs.getString("Phone"));
                employee.setAddress(rs.getString("Address"));
                employee.setEmployeeStatus(rs.getInt("Emp_Status"));

                listEmployee.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listEmployee;
    }

    @Override
    public boolean create(Employee employee) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_employee(?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployeeId());
            callSt.setString(2, employee.getEmployeeName());
            callSt.setDate(3, new java.sql.Date(employee.getBirthOfDate().getTime()));
            callSt.setString(4, employee.getEmail());
            callSt.setString(5, employee.getPhone());
            callSt.setString(6, employee.getAddress());
            callSt.setInt(7, employee.getEmployeeStatus());
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
    public boolean update(Employee employee) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        boolean result = false;

        try {
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_employee(?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployeeId());
            callSt.setString(2, employee.getEmployeeName());
            callSt.setDate(3, new java.sql.Date(employee.getBirthOfDate().getTime()));
            callSt.setString(4, employee.getEmail());
            callSt.setString(5, employee.getPhone());
            callSt.setString(6, employee.getAddress());
            callSt.setInt(7, employee.getEmployeeStatus());
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
    public Employee findById(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            callSt = conn.prepareCall("{call get_employee_by_id(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getString("Emp_Id"));
                employee.setEmployeeName(rs.getString("Emp_Name"));
                employee.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhone(rs.getString("Phone"));
                employee.setAddress(rs.getString("Address"));
                employee.setEmployeeStatus(rs.getInt("Emp_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return employee;
    }

    @Override
    public Employee findByName(String s) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            callSt = conn.prepareCall("{call get_employee_by_name(?)}");
            callSt.setString(1, s);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getString("Emp_Id"));
                employee.setEmployeeName(rs.getString("Emp_Name"));
                employee.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhone(rs.getString("Phone"));
                employee.setAddress(rs.getString("Address"));
                employee.setEmployeeStatus(rs.getInt("Emp_Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return employee;
    }

    @Override
    public List<Employee> searchName(String s, Integer integer) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        List<Employee> listEmployee = null;
        try {
            callSt = conn.prepareCall("{call search_employee_name(?,?)}");
            callSt.setString(1, s);
            callSt.setInt(2, integer);
            ResultSet rs = callSt.executeQuery();
            listEmployee = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getString("Emp_Id"));
                employee.setEmployeeName(rs.getString("Emp_Name"));
                employee.setBirthOfDate(rs.getDate("Birth_Of_Date"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhone(rs.getString("Phone"));
                employee.setAddress(rs.getString("Address"));
                employee.setEmployeeStatus(rs.getInt("Emp_Status"));
                listEmployee.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return listEmployee;
    }
}
