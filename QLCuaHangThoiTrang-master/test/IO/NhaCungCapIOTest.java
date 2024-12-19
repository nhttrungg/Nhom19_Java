/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package IO;

import Models.NhaCungCap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author my computer
 */
public class NhaCungCapIOTest {
    
    public NhaCungCapIOTest() {
    }

    /**
     * Test of readFromFile method, of class NhaCungCapIO.
     */
    @Test
    public void testReadFromFile() {
        System.out.println("readFromFile");
        ArrayList<NhaCungCap> expResult = new ArrayList<>();
        for (NhaCungCap i : expResult) {
            NhaCungCap ncc = new NhaCungCap(i.getMa(),i.getTen(), i.getSoDienThoai(), i.getDiaChi());
            expResult.add(ncc);
        }
        ArrayList<NhaCungCap> result = NhaCungCapIO.readFromFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeToFile method, of class NhaCungCapIO.
     */
    @Test
    public void testWriteToFile_ArrayList() {
        System.out.println("writeToFile");
        ArrayList<NhaCungCap> list = new ArrayList<>();
        for (NhaCungCap i : list) {
            NhaCungCap ncc = new NhaCungCap(i.getMa(),i.getTen(), i.getSoDienThoai(), i.getDiaChi());
            list.add(ncc);
        }
        NhaCungCapIO.writeToFile(list);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("NhaCungCap.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            assertEquals(list.size(), lines.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of writeToFile method, of class NhaCungCapIO.
     */
    @Test
    public void testWriteToFile_ArrayList_boolean() {
        System.out.println("writeToFile");
        ArrayList<NhaCungCap> list = new ArrayList<>();
        for (NhaCungCap i : list) {
            NhaCungCap ncc = new NhaCungCap(i.getMa(),i.getTen(), i.getSoDienThoai(), i.getDiaChi());
            list.add(ncc);
        }
        boolean nextLine = false;
        NhaCungCapIO.writeToFile(list, nextLine);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("NhaCungCap.txt"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            assertEquals(list.size(), lines.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
