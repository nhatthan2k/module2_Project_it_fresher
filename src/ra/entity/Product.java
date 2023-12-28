package ra.entity;

import ra.bussiness.IBussiness;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private String manufacturer;
    private Date createdDate;
    private int batch;
    private int quantity;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, String manufacturer, Date createdDate, int batch, int quantity, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.createdDate = createdDate;
        this.batch = batch;
        this.quantity = quantity;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, IBussiness productBussiness) {
        this.productId = inputProductId(scanner, productBussiness);
        this.productName = inputProductName(scanner, productBussiness);
        this.manufacturer = inputManufacturer(scanner);
        this.batch = inputBatch(scanner);
        this.status = inputProductStatus(scanner);
    }

    public static String inputProductId(Scanner scanner, IBussiness productBussiness) {
        do {
            System.out.println("Mã sản phẩm(có 5 kí tự):");
            String productId = scanner.nextLine();

            if (productId.length() == 5) {
                if (productBussiness.findById(productId) == null) {
                    return productId;
                }else {
                    System.err.println("mã sản phẩm đã tồn tại! vui lòng nhập lại");
                }
            }else {
                System.err.println("Mã sản phẩm có 5 kí tự! vui lòng nhập lại");
            }
        }while (true);
    }

    public static String inputProductName(Scanner scanner, IBussiness productBussiness) {
        do{
            System.out.println("Tên Sản phẩm:");
            String productName = scanner.nextLine();

             if (productName.trim().isEmpty()) {
                 System.err.println("Tên sản phẩm không được để trống!");
             } else {
                 if(productBussiness.findByName(productName) == null) {
                     return productName;
                 }else {
                     System.err.println("Tên sản phẩm đã tồn tại! vui lòng nhập lại");
                 }
             }
        }while (true);
    }

    public static String inputManufacturer(Scanner scanner) {
        do {
            System.out.println("Tên nhà sản xuất:");
            String manufacturer = scanner.nextLine();

            if (manufacturer.trim().isEmpty()) {
                System.err.println("Tên nhà sản suất không được để trống!");
            }else {
                return manufacturer;
            }
        }while (true);
    }

    public static int inputBatch(Scanner scanner) {
        do {
            System.out.println("Lô chứa sản phẩm:");
            try {
                int batch = Integer.parseInt( scanner.nextLine());

                if (batch > 0) {
                    return batch;
                }else {
                    System.err.println("Lô chứa sản phẩm phải lớn hơn không!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            }
        }while (true);
    }

    public static boolean inputProductStatus(Scanner scanner) {
        do {
            System.out.println("Trạng thái của sản phẩm:");
            String productStatus = scanner.nextLine();

            if(productStatus.trim().equalsIgnoreCase("true") || productStatus.trim().equalsIgnoreCase("false")){
                return Boolean.parseBoolean(productStatus);
            }else {
                System.err.println("Trạng thái của sản phẩm chỉ nhận giá là true hoặc false!");
            }
        }while (true);
    }

    public void displayData() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", createdDate=" + createdDate +
                ", batch=" + batch +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }
}
