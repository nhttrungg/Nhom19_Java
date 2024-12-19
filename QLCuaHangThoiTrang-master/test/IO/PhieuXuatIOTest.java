/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package IO;

import Models.PhieuXuat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class PhieuXuatIOTest {
    
    public PhieuXuatIOTest() {
    }

    /**
     * Test of writePhieuXuat method, of class PhieuXuatIO.
     */
    @Test
    public void testWritePhieuXuat() {
        System.out.println("writePhieuXuat");
        PhieuXuat phieuxuat = new PhieuXuat("", "", "", "", 0);
        ArrayList<PhieuXuat> danhsachphieuxuat = new ArrayList<>();

 
        PhieuXuatIO phieuXuatIO = new PhieuXuatIO();
        phieuXuatIO.writePhieuXuat(phieuxuat, danhsachphieuxuat);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            String line = reader.readLine();
            reader.close();
            assertTrue(line.contains((CharSequence) danhsachphieuxuat));
            
        } catch (Exception e) {
        }

    }

    /**
     * Test of readFilePX method, of class PhieuXuatIO.
     */
    @Test
    public void testReadFilePX() {
        System.out.println("readFilePX");
        String fileName = "";
        PhieuXuatIO instance = new PhieuXuatIO();
        ArrayList expResult = new ArrayList();
        ArrayList result = instance.readFilePX(fileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteByIdMaPhieuNhap method, of class PhieuXuatIO.
     */
    @Test
    public void testDeleteByIdMaPhieuNhap() {
        System.out.println("deleteByIdMaPhieuNhap");
        String id = "";
        PhieuXuatIO instance = new PhieuXuatIO();
        instance.deleteByIdMaPhieuNhap(id);
        PhieuXuat p  = new PhieuXuat();
        p.setMaPhieu("");
        assertEquals(id, p.getMaPhieu());
                
    }

    /**
     * Test of updateInfoById method, of class PhieuXuatIO.
     */
    @Test
    public void testUpdateInfoById() {
        System.out.println("updateInfoById");
        PhieuXuat data = null;
        PhieuXuatIO instance = new PhieuXuatIO();
        instance.updateInfoById(data);
        PhieuXuat p = null;
        assertEquals(data, p);
    }
    
}
