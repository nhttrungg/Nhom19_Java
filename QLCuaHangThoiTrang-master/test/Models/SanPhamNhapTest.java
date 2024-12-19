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
public class SanPhamNhapTest {
    
    public SanPhamNhapTest() {
    }

    /**
     * Test of getMaSanPham method, of class SanPhamNhap.
     */
    @Test
    public void testGetMaSanPham() {
        System.out.println("getMaSanPham");
        SanPhamNhap instance = new SanPhamNhap();
        String expResult = "";
        instance.setMaSanPham("");
        String result = instance.getMaSanPham();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSoLuong method, of class SanPhamNhap.
     */
    @Test
    public void testGetSoLuong() {
        System.out.println("getSoLuong");
        SanPhamNhap instance = new SanPhamNhap();
        int expResult = 0;
        instance.setSoLuong(0);
        int result = instance.getSoLuong();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThanhTien method, of class SanPhamNhap.
     */
    @Test
    public void testGetThanhTien() {
        System.out.println("getThanhTien");
        SanPhamNhap instance = new SanPhamNhap();
        long expResult = 0L;
        instance.setThanhTien(0L);
        long result = instance.getThanhTien();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaPhieuNhap method, of class SanPhamNhap.
     */
    @Test
    public void testGetMaPhieuNhap() {
        System.out.println("getMaPhieuNhap");
        SanPhamNhap instance = new SanPhamNhap();
        String expResult = "";
        instance.setMaPhieuNhap("");
        String result = instance.getMaPhieuNhap();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaSanPham method, of class SanPhamNhap.
     */
    @Test
    public void testSetMaSanPham() {
        System.out.println("setMaSanPham");
        String maSanPham = "";
        SanPhamNhap instance = new SanPhamNhap();
        instance.setMaSanPham(maSanPham);
        String act = instance.getMaSanPham();
        String exp = "";
        assertEquals(exp, act);
    }

    /**
     * Test of setSoLuong method, of class SanPhamNhap.
     */
    @Test
    public void testSetSoLuong() {
        System.out.println("setSoLuong");
        int soLuong = 0;
        SanPhamNhap instance = new SanPhamNhap();
        instance.setSoLuong(soLuong);
        int act = instance.getSoLuong();
        int exp = 0;
        assertEquals(exp, act);
    }

    /**
     * Test of setThanhTien method, of class SanPhamNhap.
     */
    @Test
    public void testSetThanhTien() {
        System.out.println("setThanhTien");
        long thanhTien = 0L;
        SanPhamNhap instance = new SanPhamNhap();
        instance.setThanhTien(thanhTien);
        long act = instance.getThanhTien();
        long exp = 0L;
        assertEquals(exp, act);
    }

    /**
     * Test of setMaPhieuNhap method, of class SanPhamNhap.
     */
    @Test
    public void testSetMaPhieuNhap() {
        System.out.println("setMaPhieuNhap");
        String maPhieuNhap = "";
        SanPhamNhap instance = new SanPhamNhap();
        instance.setMaPhieuNhap(maPhieuNhap);
        String act = instance.getMaPhieuNhap();
        String exp = "";
        assertEquals(exp, act);
    }

    /**
     * Test of toString method, of class SanPhamNhap.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SanPhamNhap instance = new SanPhamNhap();
        String expResult = ";0;0;";
        instance.setMaSanPham("");
        instance.setSoLuong(0);
        instance.setThanhTien(0);
        instance.setMaPhieuNhap("");
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
