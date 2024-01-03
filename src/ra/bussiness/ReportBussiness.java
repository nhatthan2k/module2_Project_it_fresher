package ra.bussiness;

import ra.util.ConnectionDB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReportBussiness {
    public static Date validateDate(Scanner  scanner){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            try {
                return sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.err.println("Định dạng ngày dd/MM/yyyy, vui lòng nhập lại");
            }
        } while (true);
    }

    public static void costReceiptByDay(Scanner scanner) {
        System.out.println("Nhập ngày tháng năm bạn muốn thống kê:");
        Date inputDate = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call get_sum_money_by_day(?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate.getTime()));
            callSt.setBoolean(2, true);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int sum = callSt.getInt(3);
            System.out.println("Chi phi = " + sum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void costReceiptFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call get_sum_money_from_day_to_day(?,?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, true);
            callSt.registerOutParameter(4, Types.INTEGER);
            callSt.execute();
            int sum = callSt.getInt(4);
            System.out.println("Chi phi = " + sum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void turnoverBillByDay(Scanner scanner) {
        System.out.println("Nhập ngày tháng năm muốn thống kê:");
        Date inputDate = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call get_sum_money_by_day(?,?,?)");
            java.sql.Timestamp createdTimestamp = new java.sql.Timestamp(inputDate.getTime());
            callSt.setTimestamp(1, createdTimestamp);
            callSt.setBoolean(2, false);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int sum = callSt.getInt(3);
            System.out.println("Doanh thu = " + sum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void turnoverBillFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call get_sum_money_from_day_to_day(?,?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, false);
            callSt.registerOutParameter(4, Types.INTEGER);
            callSt.execute();
            int sum = callSt.getInt(4);
            System.out.println("Doanh thu = " + sum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void quantityEmpByStatus(Scanner scanner) {
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call count_employee_by_status()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                int empStatus = rs.getInt(1);
                int empCount = rs.getInt(2);
                System.out.println((empStatus == 0 ? "Nhân viên đang hoạt động: " : empStatus == 1 ? "Nhân viên nghỉ chế độ: " : "Nhân viên nghỉ việc: ") + empCount + " nhân viên");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void statisticMaxReceiptFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call statistic_max_from_day_to_day(?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, true);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                String nameProduct = rs.getString(1);
                int sumQuantity = rs.getInt(2);
                System.out.println("Sản phẩm " + nameProduct + " : " + sumQuantity + " số lượng");
            } else {
                System.err.println("Không có dữ liệu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void statisticMinReceiptFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call statistic_min_from_day_to_day(?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, true);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                String nameProduct = rs.getString(1);
                int sumQuantity = rs.getInt(2);
                System.out.println("Sản phẩm " + nameProduct + " : " + sumQuantity + " số lượng");
            } else {
                System.err.println("Không có dữ liệu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void statisticMaxBillFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call statistic_max_from_day_to_day(?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, false);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                String nameProduct = rs.getString(1);
                int sumQuantity = rs.getInt(2);
                System.out.println("Sản phẩm " + nameProduct + " : " + sumQuantity + " số lượng");
            } else {
                System.err.println("Không có dữ liệu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static void statisticMinBillFromDayToDay(Scanner scanner) {
        System.out.println("Nhập khoảng thời gian thống kê bắt đầu từ ngày:");
        Date inputDate1 = validateDate(scanner);
        System.out.println("đến ngày:");
        Date inputDate2 = validateDate(scanner);
        Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("call statistic_min_from_day_to_day(?,?,?)");
            callSt.setTimestamp(1, new java.sql.Timestamp(inputDate1.getTime()));
            callSt.setTimestamp(2, new java.sql.Timestamp(inputDate2.getTime()));
            callSt.setBoolean(3, false);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                String nameProduct = rs.getString(1);
                int sumQuantity = rs.getInt(2);
                System.out.println("Sản phẩm " + nameProduct + " : " + sumQuantity + " số lượng");
            } else {
                System.err.println("Không có dữ liệu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

}
