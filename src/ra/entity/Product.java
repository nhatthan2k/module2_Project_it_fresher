package ra.entity;

import javax.xml.crypto.Data;
import java.util.Date;

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
}
