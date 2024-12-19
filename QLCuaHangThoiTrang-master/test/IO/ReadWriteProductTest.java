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
public class ReadWriteProductTest {
    
    public ReadWriteProductTest() {
    }

    /**
     * Test of writeFile method, of class ReadWriteProduct.
     */
    @Test
    public void testWriteFile() {
        System.out.println("writeFile");
        Product product = null;
        String fileName = "";
        ArrayList<Product> danhsachsanpham = null;
        ReadWriteProduct instance = new ReadWriteProduct();
        instance.writeFile(product, fileName, danhsachsanpham);
    }

    /**
     * Test of readFile method, of class ReadWriteProduct.
     */
    @Test
    public void testReadFile() {
        System.out.println("readFile");
        String fileName = "";
        ArrayList<Product> danhsachsanpham = null;
        ReadWriteProduct instance = new ReadWriteProduct();
        ArrayList expResult = null;
        ArrayList result = instance.readFile(fileName, danhsachsanpham);
        assertEquals(expResult, result);
    }
    
}
