package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testGetProductID() {
        Product instance = new Product();
        String expResult = "";
        instance.setProductID("");
        String result = instance.getProductID();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductID() {
        Product instance = new Product();
        String exResult = "";
        instance.setProductID("");
        String productID = instance.getProductID();
        assertEquals(exResult, productID);
    }

    @Test
    public void testGetProductName() {
        Product instance = new Product();
        String expResult = "";
        instance.setProductName("");
        String result = instance.getProductName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductName() {
        String productName = "";
        Product instance = new Product();
        instance.setProductName(productName);
        String result = instance.getProductName();
        String exResult = "";
        assertEquals(exResult, result);
    }

    @Test
    public void testGetProductCategory() {
        Product instance = new Product();
        String expResult = "";
        instance.setProductCategory("");
        String result = instance.getProductCategory();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductCategory() {
        String productCategory = "";
        Product instance = new Product();
        instance.setProductCategory(productCategory);
        String result = instance.getProductCategory();
        String expResult = "";
        assertEquals(expResult, result);
    }

    @Test
    public void testGetProductQuantity() {
        Product instance = new Product();
        int expResult = 0;
        instance.setProductQuantity(0);
        int result = instance.getProductQuantity();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductQuantity() {
        int productQuantity = 0;
        Product instance = new Product();
        instance.setProductQuantity(productQuantity);
        int result = instance.getProductQuantity();
        int expResult = 0;
        assertEquals(expResult, result);
    }

    @Test
    public void testGetProductPrice() {
        Product instance = new Product();
        long expResult = 0L;
        instance.setProductPrice(0L);
        long result = instance.getProductPrice();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductPrice() {
        long productPrice = 0L;
        Product instance = new Product();
        instance.setProductPrice(productPrice);
        long result = instance.getProductPrice();
        long expResult = 0L;
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        Product instance = new Product();
        String expResult = ";;;0;0";
        instance.setProductID("");
        instance.setProductName("");
        instance.setProductCategory("");
        instance.setProductQuantity(0);
        instance.setProductPrice(0);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
