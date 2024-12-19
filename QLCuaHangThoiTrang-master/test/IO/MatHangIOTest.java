/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package IO;

import Models.MatHang;
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
public class MatHangIOTest {

    public MatHangIOTest() {
    }

    /**
     * Test of readFromFile method, of class MatHangIO.
     */
    @Test
    public void testReadFromFile() {
        System.out.println("readFromFile");
        ArrayList<MatHang> expResult = new ArrayList<>();
        for (MatHang i : expResult) {
            MatHang mh = new MatHang(i.getMa(), i.getTen());
            expResult.add(mh);
        }
        ArrayList<MatHang> result = MatHangIO.readFromFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeToFile method, of class MatHangIO.
     */
    @Test
    public void testWriteToFile_ArrayList() {
        System.out.println("writeToFile");
        ArrayList<MatHang> list = new ArrayList<>();
        for (MatHang i : list) {
            MatHang mh = new MatHang(i.getMa(), i.getTen());
            list.add(mh);
        }
        MatHangIO.writeToFile(list);

        try {
            BufferedReader br = new BufferedReader(new FileReader("MatHang.txt"));
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
     * Test of writeToFile method, of class MatHangIO.
     */
    @Test
    public void testWriteToFile_ArrayList_boolean() {
        System.out.println("writeToFile");
        ArrayList<MatHang> list = new ArrayList<>();
        for (MatHang i : list) {
            MatHang mh = new MatHang(i.getMa(), i.getTen());
            list.add(mh);
        }
        boolean nextLine = false;
        MatHangIO.writeToFile(list, nextLine);

        try {
            BufferedReader br = new BufferedReader(new FileReader("MatHang.txt"));
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
