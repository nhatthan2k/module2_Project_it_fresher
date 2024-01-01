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

    public void inputData(Scanner scanner) {
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
            int productQuantity = Integer.parseInt(scanner.nextLine());

            if(productQuantity>0) {
                return productQuantity;
            }else{
                System.err.println("số lượng sản phẩm có giá trị lớn hơn 0");
            }
        }while (true);
    }

    public float inputPrice(Scanner scanner) {
        do {
            System.out.println("giá sản phẩm:");
            float productPrice = Float.parseFloat(scanner.nextLine());

            if(productPrice>0) {
                return productPrice;
            }else{
                System.err.println("giá sản phẩm có giá trị lớn hơn 0");
            }
        }while (true);
    }
}
