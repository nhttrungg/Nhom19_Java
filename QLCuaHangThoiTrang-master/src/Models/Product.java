package Models;

import java.io.Serializable;

public class Product implements Serializable{
    private String productID;
    private String productName;
    private String productCategory;
    private int productQuantity;
    private long productPrice;

    public Product() {
    }

    public Product(String productID, String productName, String productCategory, int productQuantity, long productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return productID + ";" + productName + ";" + productCategory + ";" + productQuantity + ";" + productPrice;
    }
    
   
}
