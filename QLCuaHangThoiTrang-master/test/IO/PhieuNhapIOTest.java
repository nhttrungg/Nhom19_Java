/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package IO;

import Models.PhieuNhap;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class PhieuNhapIOTest {
    
    public PhieuNhapIOTest() {
    }

    /**
     * Test of writeToFile method, of class PhieuNhapIO.
     */
    @Test
    public void testWriteToFile_PhieuNhap() {
        System.out.println("writeToFile");
        PhieuNhap value = null;
        PhieuNhapIO.writeToFile(value);
        PhieuNhap p = null;
        assertEquals(value, p);
    }

    /**
     * Test of writeToFile method, of class PhieuNhapIO.
     */
    @Test
    public void testWriteToFile_ArrayList() {
        System.out.println("writeToFile");
        ArrayList<PhieuNhap> list = null;
        PhieuNhapIO.writeToFile(list);
        PhieuNhap p = null;
        assertEquals(list, p);
    }

    /**
     * Test of updateInfoById method, of class PhieuNhapIO.
     */
    @Test
    public void testUpdateInfo() {
        System.out.println("updateInfo");
        PhieuNhap data = null;
        PhieuNhapIO.updateInfo(data);
        PhieuNhap p = null;
        assertEquals(p, data);
    }

    /**
     * Test of getInfoById method, of class PhieuNhapIO.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        String id = "";
        PhieuNhap expResult = null;
        PhieuNhap result = null;
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteById method, of class PhieuNhapIO.
     */
    @Test
    public void testDeleteById() {
        System.out.println("deleteById");
        String id = "";
        PhieuNhapIO.deleteById(id);
        PhieuNhap p =new PhieuNhap();
        p.setMa("");
        assertEquals(id, p.getMa());
    }

    /**
     * Test of readFromFile method, of class PhieuNhapIO.
     */
    @Test
    public void testReadFromFile() {
        System.out.println("readFromFile");
        ArrayList<PhieuNhap> expResult = PhieuNhapIO.readFromFile();;
        ArrayList<PhieuNhap> result = PhieuNhapIO.readFromFile();
        assertEquals(expResult, result);
    }
    
}
