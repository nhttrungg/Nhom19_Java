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
public class PhieuXuatTest {
    
    public PhieuXuatTest() {
    }

    /**
     * Test of getMaPhieu method, of class PhieuXuat.
     */
    @Test
    public void testGetMaPhieu() {
        System.out.println("getMaPhieu");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "";
        instance.setMaPhieu("");
        String result = instance.getMaPhieu();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaPhieu method, of class PhieuXuat.
     */
    @Test
    public void testSetMaPhieu() {
        System.out.println("setMaPhieu");
        String maPhieu = "";
        PhieuXuat instance = new PhieuXuat();
        instance.setMaPhieu(maPhieu);
        String result = instance.getMaPhieu();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getTenKH method, of class PhieuXuat.
     */
    @Test
    public void testGetTenKH() {
        System.out.println("getTenKH");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "";
        instance.setTenKH("");
        String result = instance.getTenKH();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTenKH method, of class PhieuXuat.
     */
    @Test
    public void testSetTenKH() {
        System.out.println("setTenKH");
        String tenKH = "";
        PhieuXuat instance = new PhieuXuat();
        instance.setTenKH(tenKH);
        String result = instance.getTenKH();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getSdtKH method, of class PhieuXuat.
     */
    @Test
    public void testGetSdtKH() {
        System.out.println("getSdtKH");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "";
        instance.setSdtKH("");
        String result = instance.getSdtKH();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSdtKH method, of class PhieuXuat.
     */
    @Test
    public void testSetSdtKH() {
        System.out.println("setSdtKH");
        String sdtKH = "";
        PhieuXuat instance = new PhieuXuat();
        instance.setSdtKH(sdtKH);
        String result = instance.getSdtKH();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getNgayTao method, of class PhieuXuat.
     */
    @Test
    public void testGetNgayTao() {
        System.out.println("getNgayTao");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "";
        instance.setNgayTao("");
        String result = instance.getNgayTao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNgayTao method, of class PhieuXuat.
     */
    @Test
    public void testSetNgayTao() {
        System.out.println("setNgayTao");
        String ngayTao = "";
        PhieuXuat instance = new PhieuXuat();
        instance.setNgayTao(ngayTao);
        String result = instance.getNgayTao();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getDiaChi method, of class PhieuXuat.
     */
    @Test
    public void testGetDiaChi() {
        System.out.println("getDiaChi");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "";
        instance.setDiaChi("");
        String result = instance.getDiaChi();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDiaChi method, of class PhieuXuat.
     */
    @Test
    public void testSetDiaChi() {
        System.out.println("setDiaChi");
        String diaChi = "";
        PhieuXuat instance = new PhieuXuat();
        instance.setDiaChi(diaChi);
        String result = instance.getDiaChi();
        String expResult = "";
        assertEquals(expResult, result);
    }

    /**
     * Test of getTien method, of class PhieuXuat.
     */
    @Test
    public void testGetTien() {
        System.out.println("getTien");
        PhieuXuat instance = new PhieuXuat();
        long expResult = 0L;
        instance.setTien(0L);
        long result = instance.getTien();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTien method, of class PhieuXuat.
     */
    @Test
    public void testSetTien() {
        System.out.println("setTien");
        long tien = 0L;
        PhieuXuat instance = new PhieuXuat();
        instance.setTien(tien);
        long result = instance.getTien();
        long expResult = 0L;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class PhieuXuat.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PhieuXuat instance = new PhieuXuat();
        String expResult = "a-b-c-d-e-0";
        instance.setMaPhieu("a");
        instance.setTenKH("b");
        instance.setSdtKH("c");
        instance.setNgayTao("d");
        instance.setDiaChi("e");
        instance.setTien(0);
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
