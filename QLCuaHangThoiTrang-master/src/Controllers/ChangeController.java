
package Controllers;

import Models.DanhMuc;
import Views.PhieuNhapView;
import Views.PhieuXuatView;
import Views.QLDMView;
import Views.QLNHView;
import Views.QLSP;
import Views.QLTKhoan;
import Views.QLXH;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChangeController {
    private ArrayList<DanhMuc> danhMuc = null;
    private JPanel jpnRoot;
    private String kindSelected = "";

    public ChangeController() {
    }

    public ChangeController(JPanel jpnRoot) {
        this.jpnRoot = jpnRoot;
    }
    
    public void setQLSP(JPanel jpn, JLabel jlb) {
        kindSelected = "QLSP";
        jpn.setBackground(new Color(39, 43, 48));
        jlb.setBackground(new Color(39, 43, 48));
        jpnRoot.removeAll();
        jpnRoot.setLayout(new BorderLayout());
        jpnRoot.add(new QLSP());
        jpnRoot.validate();
        jpnRoot.repaint();
    }
    
    public void setEvent(ArrayList<DanhMuc> danhMuc) {
        this.danhMuc = danhMuc;
        for (DanhMuc item : danhMuc) {
            item.getJlb().addMouseListener(new LableEvent(item.getKind(), item.getJpl(), item.getJlb()));
        }
    }
    
    class LableEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jplItem;
        private JLabel jlbItem;

        public LableEvent(String kind, JPanel jplItem, JLabel jlbItem) {
            this.kind = kind;
            this.jplItem = jplItem;
            this.jlbItem = jlbItem;
        }

        public JPanel getNode() {
            return node;
        }

        public void setNode(JPanel node) {
            this.node = node;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public JPanel getJplItem() {
            return jplItem;
        }

        public void setJplItem(JPanel jplItem) {
            this.jplItem = jplItem;
        }

        public JLabel getJlbItem() {
            return jlbItem;
        }

        public void setJlbItem(JLabel jlbItem) {
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "QLSP": {
                    node = new QLSP();
                    break;
                }
                case "QLDM": {
                    node = new QLDMView();
                    break;
                }
                case "QLNH": {
                    node = new QLNHView();
                    break;
                }
                case "PhieuNhap": {
                    node = new PhieuNhapView();
                    break;
                }
                case "PhieuXuat": {
                    node = new PhieuXuatView();
                    break;
                }
                case "QLXH": {
                    node = new QLXH();
                    break;
                }
                case "QLTKhoan": {
                    node = new QLTKhoan();
                    break;
                }
                default:
                    node = new QLSP();
                    break;
            }
            jpnRoot.removeAll();
            jpnRoot.setLayout(new BorderLayout());
            jpnRoot.add(node);
            jpnRoot.validate();
            jpnRoot.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jplItem.setBackground(new Color(39, 43, 48));
            jlbItem.setBackground(new Color(39, 43, 48));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jplItem.setBackground(new Color(39, 43, 48));
            jlbItem.setBackground(new Color(39, 43, 48));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jplItem.setBackground(new Color(26, 29, 31));
                jlbItem.setBackground(new Color(26, 29, 31));
            }
        }
        
        private void setChangeBackground(String kind) {
            for (DanhMuc i : danhMuc) {
                if (i.getKind().equalsIgnoreCase(kind)) {
                    i.getJpl().setBackground(new Color(39, 43, 48));
                    i.getJlb().setBackground(new Color(39, 43, 48));
                } else {
                    i.getJpl().setBackground(new Color(26, 29, 31));
                    i.getJlb().setBackground(new Color(26, 29, 31));
                }
            }
        }
    }
}
