package ra.entity;

import java.util.Scanner;

import static ra.presentation.ProductPresentation.productBussiness;

public class BillDetail {
    private int billDetailId;
    private int billId;
    private String productID;
    private int quantity;
    private float price;

    public BillDetail() {
    }

    public BillDetail(int billDetailId, int billId, String productID, int quantity, float price) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void inputData(Scanner scanner, int billId) {
        this.billId = billId;
        this.productID = inputProductId(scanner);
        this.quantity = inputQuantity(scanner);
        this.price = inputPrice(scanner);
    }

    public String inputProductId(Scanner scanner) {
        do {
            System.out.println("Mã sản phẩm(có 5 kí tự):");
            String productId = scanner.nextLine();

            if (productId.trim().length() == 5) {
                if (productBussiness.findById(productId) == null) {
                    System.err.println("mã sản phẩm không tồn tại! vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Mã sản phẩm có 5 kí tự! vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputQuantity(Scanner scanner) {
        do {
            System.out.println("số luợng sản phẩm:");

            try{
                int productQuantity = Integer.parseInt(scanner.nextLine());

                if(productQuantity>0) {
                    return productQuantity;
                }else{
                    System.err.println("số lượng sản phẩm có giá trị lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }while (true);
    }

    public float inputPrice(Scanner scanner) {
        do {
            System.out.println("giá sản phẩm:");
            try {
                float productPrice = Float.parseFloat(scanner.nextLine());

                if(productPrice>0) {
                    return productPrice;
                }else{
                    System.err.println("giá sản phẩm có giá trị lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số thực");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }while (true);
    }

    public void updateData(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************Câp nhât thông tin************");
            System.out.println("1. cập nhât mã sản phẩm");
            System.out.println("2. cập nhât số lượng sản phẩm");
            System.out.println("3. cập nhât giá sản phẩm");
            System.out.println("4. thoát");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.productID = inputProductId(scanner);
                        break;
                    case 2:
                        this.quantity = inputQuantity(scanner);
                        break;
                    case 3:
                        this.price = inputPrice(scanner);
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

    @Override
    public String toString() {
        return "BillDetail{" +
                "billDetailId=" + billDetailId +
                ", billId=" + billId +
                ", productID='" + productID + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
