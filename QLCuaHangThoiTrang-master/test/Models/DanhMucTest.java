/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Models;

import javax.swing.JLabel;
import javax.swing.JPanel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class DanhMucTest {
    
    public DanhMucTest() {
    }

    /**
     * Test of getKind method, of class DanhMuc.
     */
    @Test
    public void testGetKind() {
        System.out.println("getKind");
        DanhMuc instance = new DanhMuc();
        String expResult = "";
        instance.setKind("");
        String result = instance.getKind();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKind method, of class DanhMuc.
     */
    @Test
    public void testSetKind() {
        System.out.println("setKind");
        String kind = "";
        DanhMuc instance = new DanhMuc();
        instance.setKind(kind);
        String result = instance.getKind();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getJpl method, of class DanhMuc.
     */
    @Test
    public void testGetJpl() {
        System.out.println("getJpl");
        DanhMuc instance = new DanhMuc();
        JPanel expResult = null;
        instance.setJpl(null);
        JPanel result = instance.getJpl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJpl method, of class DanhMuc.
     */
    @Test
    public void testSetJpl() {
        System.out.println("setJpl");
        JPanel jpl = null;
        DanhMuc instance = new DanhMuc();
        instance.setJpl(jpl);
        JPanel result = instance.getJpl();
        JPanel expResult = null;
        assertEquals(expResult, result);
    }

    /**
     * Test of getJlb method, of class DanhMuc.
     */
    @Test
    public void testGetJlb() {
        System.out.println("getJlb");
        DanhMuc instance = new DanhMuc();
        JLabel expResult = null;
        instance.setJlb(null);
        JLabel result = instance.getJlb();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJlb method, of class DanhMuc.
     */
    @Test
    public void testSetJlb() {
        System.out.println("setJlb");
        JLabel jlb = null;
        DanhMuc instance = new DanhMuc();
        instance.setJlb(jlb);
        JLabel result = instance.getJlb();
        JLabel expResult = null;
        assertEquals(expResult, result);
    }
    
}
