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
public class SanPhamXuatTest {
    
    public SanPhamXuatTest() {
    }

    /**
     * Test of getMaSanPham method, of class SanPhamXuat.
     */
    @Test
    public void testGetMaSanPham() {
        System.out.println("getMaSanPham");
        SanPhamXuat instance = new SanPhamXuat();
        String expResult = "";
        instance.setMaSanPham("");
        String result = instance.getMaSanPham();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaSanPham method, of class SanPhamXuat.
     */
    @Test
    public void testSetMaSanPham() {
        System.out.println("setMaSanPham");
        String maSanPham = "";
        SanPhamXuat instance = new SanPhamXuat();
        instance.setMaSanPham(maSanPham);
        String exp = "";
        String act = instance.getMaSanPham();
        assertEquals(exp, act);
    }

    /**
     * Test of getSoLuong method, of class SanPhamXuat.
     */
    @Test
    public void testGetSoLuong() {
        System.out.println("getSoLuong");
        SanPhamXuat instance = new SanPhamXuat();
        int expResult = 0;
        instance.setSoLuong(0);
        int result = instance.getSoLuong();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSoLuong method, of class SanPhamXuat.
     */
    @Test
    public void testSetSoLuong() {
        System.out.println("setSoLuong");
        int soLuong = 0;
        SanPhamXuat instance = new SanPhamXuat();
        instance.setSoLuong(soLuong);
        int exp = 0;
        int act = instance.getSoLuong();
        assertEquals(exp, act);
    }

    /**
     * Test of getThanhTien method, of class SanPhamXuat.
     */
    @Test
    public void testGetThanhTien() {
        System.out.println("getThanhTien");
        SanPhamXuat instance = new SanPhamXuat();
        long expResult = 0L;
        instance.setThanhTien(0L);
        long result = instance.getThanhTien();
        assertEquals(expResult, result);
    }

    /**
     * Test of setThanhTien method, of class SanPhamXuat.
     */
    @Test
    public void testSetThanhTien() {
        System.out.println("setThanhTien");
        long thanhTien = 0L;
        SanPhamXuat instance = new SanPhamXuat();
        instance.setThanhTien(thanhTien);
        long exp = 0L;
        long act = instance.getThanhTien();
        assertEquals(exp, act);
    }

    /**
     * Test of getMaPhieuXuat method, of class SanPhamXuat.
     */
    @Test
    public void testGetMaPhieuXuat() {
        System.out.println("getMaPhieuXuat");
        SanPhamXuat instance = new SanPhamXuat();
        String expResult = "";
        instance.setMaPhieuXuat("");
        String result = instance.getMaPhieuXuat();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaPhieuXuat method, of class SanPhamXuat.
     */
    @Test
    public void testSetMaPhieuXuat() {
        System.out.println("setMaPhieuXuat");
        String maPhieuXuat = "";
        SanPhamXuat instance = new SanPhamXuat();
        instance.setMaPhieuXuat(maPhieuXuat);
        String exp = "";
        String act = instance.getMaPhieuXuat();
        assertEquals(exp, act);
    }

    /**
     * Test of toString method, of class SanPhamXuat.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SanPhamXuat instance = new SanPhamXuat();
        String expResult = ";0;0;";
        instance.setMaPhieuXuat("");
        instance.setMaSanPham("");
        instance.setSoLuong(0);
        instance.setThanhTien(0);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
