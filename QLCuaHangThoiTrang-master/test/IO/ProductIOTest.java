/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package IO;

import Models.Product;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class ProductIOTest {
    
    public ProductIOTest() {
    }

    /**
     * Test of readFromFile method, of class ProductIO.
     */
    @Test
    public void testReadFromFile() {
        System.out.println("readFromFile");
        
        ArrayList<Product> expResult = new ArrayList<>();
        for (Product i : expResult) {
            Product p = new Product(i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), i.getProductPrice());
            expResult.add(p);
        }
        ArrayList<Product> result = ProductIO.readFromFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeToFile method, of class ProductIO.
     */
    @Test
    public void testWriteToFile() {
        System.out.println("writeToFile");
        ArrayList<Product> list = ProductIO.readFromFile();
        ProductIO.writeToFile(list);
        ArrayList<Product> result = new ArrayList<>();
        assertEquals(list, result);
    }
    
}
