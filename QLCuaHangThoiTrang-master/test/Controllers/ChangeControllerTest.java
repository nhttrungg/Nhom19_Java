/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controllers;

import Models.DanhMuc;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author my computer
 */
public class ChangeControllerTest {
    
    public ChangeControllerTest() {
    }

    /**
     * Test of setQLSP method, of class ChangeController.
     */
    @Test
    public void testSetQLSP() {
        System.out.println("setQLSP");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        JPanel rootPanel = new JPanel();
        ChangeController controller = new ChangeController(rootPanel);

        controller.setQLSP(panel, label);

        assertEquals(new Color(39, 43, 48), panel.getBackground());
        assertEquals(new Color(39, 43, 48), label.getBackground());
    }

    /**
     * Test of setEvent method, of class ChangeController.
     */
    @Test
    public void testSetEvent() {
        System.out.println("setEvent");
        ArrayList<DanhMuc> danhMucList = new ArrayList<>();
        DanhMuc danhMuc1 = new DanhMuc("Kind1", new JPanel(), new JLabel());
        DanhMuc danhMuc2 = new DanhMuc("Kind2", new JPanel(), new JLabel());
        danhMucList.add(danhMuc1);
        danhMucList.add(danhMuc2);
        JPanel jpnRoot = new JPanel();
        ChangeController changeController = new ChangeController(jpnRoot);
        changeController.setEvent(danhMucList);

        for (DanhMuc danhMuc : danhMucList) {
            assertNotNull(danhMuc.getJlb().getMouseListeners());
            assertEquals(1, danhMuc.getJlb().getMouseListeners().length);
        }
    }
}
