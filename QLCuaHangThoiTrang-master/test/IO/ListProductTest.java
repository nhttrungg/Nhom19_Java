/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package IO;

import Models.Product;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author my computer
 */
public class ListProductTest {
    
    public ListProductTest() {
    }

    /**
     * Test of displayData method, of class ListProduct.
     */
    @Test
    public void testDisplayData() {
        System.out.println("displayData");
        DefaultTableModel dtm = null;
        ArrayList<Product> danhsachsanpham = null;
        ListProduct instance = new ListProduct();
        instance.displayData(dtm, danhsachsanpham);
        assertEquals(dtm, danhsachsanpham);
    }

    /**
     * Test of findAll method, of class ListProduct.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        ArrayList<Product> danhsachsanpham = new ArrayList<>();
        for (Product i : danhsachsanpham) {
            i.setProductID("");
            i.setProductName("");
            i.setProductCategory("");
            i.setProductQuantity(0);
            i.setProductPrice(0);
            Product p = new Product(i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), i.getProductPrice());
            danhsachsanpham.add(p);
        }
        String name = "";
        ListProduct instance = new ListProduct();
        ArrayList<Product> expResult = new ArrayList<>();
        ArrayList result = instance.findAll(danhsachsanpham, name);
        assertEquals(expResult, result);
    }

    /**
     * Test of correctProduct method, of class ListProduct.
     */
    @Test
    public void testCorrectProduct() {
        System.out.println("correctProduct");
        String id = "";
        ArrayList<Product> danhsachsanpham = null;
        ListProduct instance = new ListProduct();
        boolean expResult = false;
        boolean result = instance.correctProduct(id, danhsachsanpham);
        assertEquals(expResult, result);
    }

    /**
     * Test of addProduct method, of class ListProduct.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product p = null;
        ArrayList<Product> danhsachsanpham = null;
        ListProduct instance = new ListProduct();
        boolean expResult = false;
        boolean result = instance.addProduct(p, danhsachsanpham);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class ListProduct.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int index = 0;
        ArrayList<Product> danhsachsanpham = new ArrayList<>();
        danhsachsanpham.add(null);
        ListProduct instance = new ListProduct();
        instance.delete(index, danhsachsanpham);
        ArrayList<Product> expResult = new ArrayList<>();
        assertEquals(expResult, danhsachsanpham);
        Exception excep = assertThrows(IndexOutOfBoundsException.class, () -> {
            instance.delete(index, danhsachsanpham);
        });
        assertEquals("Index 0 out of bounds for length 0", excep.getMessage());
    }

    /**
     * Test of checkIDSame method, of class ListProduct.
     */
    @Test
    public void testCheckIDSame() {
        System.out.println("checkIDSame");
        String id = "";
        ArrayList<Product> danhsachsanpham = new ArrayList<>();
        for (Product i : danhsachsanpham) {
            i.setProductID("");
            i.setProductName("");
            i.setProductCategory("");
            i.setProductQuantity(0);
            i.setProductPrice(0);
            Product p = new Product(i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), i.getProductPrice());
            danhsachsanpham.add(p);
        }
        ListProduct instance = new ListProduct();
        boolean expResult = false;
        boolean result = instance.checkIDSame(id, danhsachsanpham);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchProduct method, of class ListProduct.
     */
    @Test
    public void testSearchProduct() {
        System.out.println("searchProduct");
        ArrayList<Product> danhsachsanpham = new ArrayList<>();
        for (Product i : danhsachsanpham) {
            i.setProductID("");
            i.setProductName("");
            i.setProductCategory("");
            i.setProductQuantity(0);
            i.setProductPrice(0);
            Product p = new Product(i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), i.getProductPrice());
            danhsachsanpham.add(p);
        }
        String name = "";
        ListProduct instance = new ListProduct();
        ArrayList<Product> expResult = new ArrayList<>();
        ArrayList result = instance.searchProduct(danhsachsanpham, name);
        assertEquals(expResult, result);
    }
}
