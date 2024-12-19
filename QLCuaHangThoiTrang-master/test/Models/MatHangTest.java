/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class MatHangTest {
    
    public MatHangTest() {
    }

    /**
     * Test of getMa method, of class MatHang.
     */
    @Test
    public void testGetMa() {
        System.out.println("getMa");
        MatHang instance = new MatHang();
        String ma = "";
        instance.setMa(ma);
        String expResult = "";
        String result = instance.getMa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTen method, of class MatHang.
     */
    @Test
    public void testGetTen() {
        System.out.println("getTen");
        MatHang instance = new MatHang();
        String expResult = "";
        instance.setTen("");
        String result = instance.getTen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMa method, of class MatHang.
     */
    @Test
    public void testSetMa() {
        System.out.println("setMa");
        String ma = "";
        MatHang instance = new MatHang();
        instance.setMa(ma);
        String result = instance.getMa();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of setTen method, of class MatHang.
     */
    @Test
    public void testSetTen() {
        System.out.println("setTen");
        String ten = "";
        MatHang instance = new MatHang();
        instance.setTen(ten);
        String result = instance.getTen();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class MatHang.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MatHang instance = new MatHang();
        String expResult = ";";
        instance.setMa("");
        instance.setTen("");
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
