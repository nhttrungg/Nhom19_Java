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
public class PhieuNhapTest {
    
    public PhieuNhapTest() {
    }

    /**
     * Test of getMa method, of class PhieuNhap.
     */
    @Test
    public void testGetMa() {
        System.out.println("getMa");
        PhieuNhap instance = new PhieuNhap();
        String expResult = "";
        instance.setMa("");
        String result = instance.getMa();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNgayCapNhat method, of class PhieuNhap.
     */
    @Test
    public void testSetNgayCapNhat() {
        System.out.println("setNgayCapNhat");
        String ngayCapNhat = "";
        PhieuNhap instance = new PhieuNhap();
        instance.setNgayCapNhat(ngayCapNhat);
        String exp = "";
        String act = instance.getNgayCapNhat();
        assertEquals(exp, act);
    }

    /**
     * Test of getNgayCapNhat method, of class PhieuNhap.
     */
    @Test
    public void testGetNgayCapNhat() {
        System.out.println("getNgayCapNhat");
        PhieuNhap instance = new PhieuNhap();
        String expResult = "";
        instance.setNgayCapNhat("");
        String result = instance.getNgayCapNhat();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaNhaCungCap method, of class PhieuNhap.
     */
    @Test
    public void testGetMaNhaCungCap() {
        System.out.println("getMaNhaCungCap");
        PhieuNhap instance = new PhieuNhap();
        String expResult = "";
        instance.setMaNhaCungCap("");
        String result = instance.getMaNhaCungCap();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTien method, of class PhieuNhap.
     */
    @Test
    public void testGetTien() {
        System.out.println("getTien");
        PhieuNhap instance = new PhieuNhap();
        Long expResult = null;
        instance.setTien(null);
        Long result = instance.getTien();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMa method, of class PhieuNhap.
     */
    @Test
    public void testSetMa() {
        System.out.println("setMa");
        String ma = "";
        PhieuNhap instance = new PhieuNhap();
        instance.setMa(ma);
        String act = instance.getMa();
        String exp = "";
        assertEquals(exp, act);
    }

    /**
     * Test of setMaNhaCungCap method, of class PhieuNhap.
     */
    @Test
    public void testSetMaNhaCungCap() {
        System.out.println("setMaNhaCungCap");
        String maNhaCungCap = "";
        PhieuNhap instance = new PhieuNhap();
        instance.setMaNhaCungCap(maNhaCungCap);
        String act = instance.getMaNhaCungCap();
        String exp = "";
        assertEquals(exp, act);
    }

    /**
     * Test of getNgayTao method, of class PhieuNhap.
     */
    @Test
    public void testGetNgayTao() {
        System.out.println("getNgayTao");
        PhieuNhap instance = new PhieuNhap();
        String expResult = "";
        instance.setNgayTao("");
        String result = instance.getNgayTao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNgayTao method, of class PhieuNhap.
     */
    @Test
    public void testSetNgayTao() {
        System.out.println("setNgayTao");
        String ngayTao = "";
        PhieuNhap instance = new PhieuNhap();
        instance.setNgayTao(ngayTao);
        String act = instance.getNgayTao();
        String exp = "";
        assertEquals(exp, act);
    }

    /**
     * Test of setTien method, of class PhieuNhap.
     */
    @Test
    public void testSetTien() {
        System.out.println("setTien");
        Long tien = null;
        PhieuNhap instance = new PhieuNhap();
        instance.setTien(tien);
        Long act = instance.getTien();
        Long exp = null;
        assertEquals(exp, act);
    }

    /**
     * Test of toString method, of class PhieuNhap.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PhieuNhap instance = new PhieuNhap();
        String expResult = "null-null-null-null-null";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
