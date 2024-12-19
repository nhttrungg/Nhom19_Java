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
public class NhaCungCapTest {
    
    public NhaCungCapTest() {
    }

    /**
     * Test of getMa method, of class NhaCungCap.
     */
    @Test
    public void testGetMa() {
        System.out.println("getMa");
        NhaCungCap instance = new NhaCungCap();
        String expResult = "";
        instance.setMa("");
        String result = instance.getMa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTen method, of class NhaCungCap.
     */
    @Test
    public void testGetTen() {
        System.out.println("getTen");
        NhaCungCap instance = new NhaCungCap();
        String expResult = "";
        instance.setTen("");
        String result = instance.getTen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSoDienThoai method, of class NhaCungCap.
     */
    @Test
    public void testGetSoDienThoai() {
        System.out.println("getSoDienThoai");
        NhaCungCap instance = new NhaCungCap();
        String expResult = "";
        instance.setSoDienThoai("");
        String result = instance.getSoDienThoai();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiaChi method, of class NhaCungCap.
     */
    @Test
    public void testGetDiaChi() {
        System.out.println("getDiaChi");
        NhaCungCap instance = new NhaCungCap();
        String expResult = "";
        instance.setDiaChi("");
        String result = instance.getDiaChi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMa method, of class NhaCungCap.
     */
    @Test
    public void testSetMa() {
        System.out.println("setMa");
        String ma = "";
        NhaCungCap instance = new NhaCungCap();
        instance.setMa(ma);
        String result = instance.getMa();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of setTen method, of class NhaCungCap.
     */
    @Test
    public void testSetTen() {
        System.out.println("setTen");
        String ten = "";
        NhaCungCap instance = new NhaCungCap();
        instance.setTen(ten);
        String result = instance.getTen();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of setSoDienThoai method, of class NhaCungCap.
     */
    @Test
    public void testSetSoDienThoai() {
        System.out.println("setSoDienThoai");
        String soDienThoai = "";
        NhaCungCap instance = new NhaCungCap();
        instance.setSoDienThoai(soDienThoai);
        String result = instance.getSoDienThoai();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiaChi method, of class NhaCungCap.
     */
    @Test
    public void testSetDiaChi() {
        System.out.println("setDiaChi");
        String diaChi = "";
        NhaCungCap instance = new NhaCungCap();
        instance.setDiaChi(diaChi);
        String result = instance.getDiaChi();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class NhaCungCap.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        NhaCungCap instance = new NhaCungCap();
        String expResult = ";;;";
        instance.setMa("");
        instance.setTen("");
        instance.setSoDienThoai("");
        instance.setDiaChi("");
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
