package ra.entity;

import ra.bussiness.IBussiness;

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

    public String inputProductId(Scanner scanner, IBussiness productBussiness) {
        do {
            System.out.println("Mã sản phẩm(có 5 kí tự):");
            String productId = scanner.nextLine();

            if (productId.trim().length() == 5) {
                if (productBussiness.findById(productId) == null) {
                    return productId;
                } else {
                    System.err.println("mã sản phẩm đã tồn tại! vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sản phẩm có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner, IBussiness productBussiness) {
        do {
            System.out.println("Tên Sản phẩm:");
            String productName = scanner.nextLine();

            if (productName.trim().isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống!");
            } else {
                if (productBussiness.findByName(productName) == null) {
                    return productName;
                } else {
                    System.err.println("Tên sản phẩm đã tồn tại! vui lòng nhập lại");
                }
            }
        } while (true);
    }

    public String inputManufacturer(Scanner scanner) {
        do {
            System.out.println("Tên nhà sản xuất:");
            String manufacturer = scanner.nextLine();

            if (manufacturer.trim().isEmpty()) {
                System.err.println("Tên nhà sản suất không được để trống!");
            } else {
                return manufacturer;
            }
        } while (true);
    }

    public int inputBatch(Scanner scanner) {
        do {
            System.out.println("Lô chứa sản phẩm:");
            try {
                int batch = Integer.parseInt(scanner.nextLine());

                if (batch > 0) {
                    return batch;
                } else {
                    System.err.println("Lô chứa sản phẩm phải lớn hơn không!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }

    public boolean inputProductStatus(Scanner scanner) {
        do {
            System.out.println("Trạng thái của sản phẩm:");
            String productStatus = scanner.nextLine();

            if (productStatus.trim().equalsIgnoreCase("true") || productStatus.trim().equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(productStatus);
            } else {
                System.err.println("Trạng thái của sản phẩm chỉ nhận giá là true hoặc false!");
            }
        } while (true);
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

    public void updateData(Scanner scanner, IBussiness productBussiness) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât tên sản phẩm");
            System.out.println("2. cập nhât tên nhà sản xuất");
            System.out.println("3. cập nhât lô sản phẩm");
            System.out.println("4. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.productName = inputProductName(scanner, productBussiness);
                        break;
                    case 2:
                        this.manufacturer = inputManufacturer(scanner);
                        break;
                    case 3:
                        this.batch = inputBatch(scanner);
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1-4!");
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
            System.out.println("2. Không hoạt động");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.status = true;
                        isExit = false;
                        break;
                    case 2:
                        this.status = false;
                        isExit = false;
                        break;
                    default:
                        System.out.println("nhập lựa chọn từ 1 trong 2!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExit);
    }
}
