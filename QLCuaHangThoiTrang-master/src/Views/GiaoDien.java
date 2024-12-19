package Views;
import Controllers.ChangeController;
import Models.DanhMuc;
import java.util.ArrayList;
public class GiaoDien extends javax.swing.JFrame {
    public GiaoDien() {
        initComponents();
        changeController();
    }
    @SuppressWarnings("unchecked")
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GiaoDien().setVisible(true);
//            }
//        });
//    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        jpnQLSP = new javax.swing.JPanel();
        jlbQLSP = new javax.swing.JLabel();
        jpnQLDM = new javax.swing.JPanel();
        jlbQLDM = new javax.swing.JLabel();
        jpnQLNH = new javax.swing.JPanel();
        jlbQLNH = new javax.swing.JLabel();
        jpnTKhoan = new javax.swing.JPanel();
        jlbTKhoan = new javax.swing.JLabel();
        jpnQLXH = new javax.swing.JPanel();
        jlbQLXH = new javax.swing.JLabel();
        jpnPhieuNhap = new javax.swing.JPanel();
        jlbPhieuNhap = new javax.swing.JLabel();
        jpnPhieuXuat = new javax.swing.JPanel();
        jlbPhieuXuat = new javax.swing.JLabel();
        jpnDisplay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÍ CỬA HÀNG THỜI TRANG");
        setBackground(new java.awt.Color(255, 255, 255));

        jpnMenu.setBackground(new java.awt.Color(26, 29, 31));

        jpnQLSP.setBackground(new java.awt.Color(26, 29, 31));
        jpnQLSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpnQLSP.setName(""); // NOI18N
        jpnQLSP.setPreferredSize(new java.awt.Dimension(180, 50));
        jpnQLSP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpnQLSPFocusGained(evt);
            }
        });

        jlbQLSP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbQLSP.setForeground(new java.awt.Color(255, 255, 255));
        jlbQLSP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbQLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/box.png"))); // NOI18N
        jlbQLSP.setText("Quản lí sản phẩm");
        jlbQLSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbQLSP.setIconTextGap(20);

        javax.swing.GroupLayout jpnQLSPLayout = new javax.swing.GroupLayout(jpnQLSP);
        jpnQLSP.setLayout(jpnQLSPLayout);
        jpnQLSPLayout.setHorizontalGroup(
            jpnQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLSPLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jpnQLSPLayout.setVerticalGroup(
            jpnQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLSPLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jpnQLDM.setBackground(new java.awt.Color(26, 29, 31));

        jlbQLDM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbQLDM.setForeground(new java.awt.Color(255, 255, 255));
        jlbQLDM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbQLDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit (1).png"))); // NOI18N
        jlbQLDM.setText("Quản lí danh mục");
        jlbQLDM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbQLDM.setIconTextGap(20);

        javax.swing.GroupLayout jpnQLDMLayout = new javax.swing.GroupLayout(jpnQLDM);
        jpnQLDM.setLayout(jpnQLDMLayout);
        jpnQLDMLayout.setHorizontalGroup(
            jpnQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLDMLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnQLDMLayout.setVerticalGroup(
            jpnQLDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLDMLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jpnQLNH.setBackground(new java.awt.Color(26, 29, 31));

        jlbQLNH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbQLNH.setForeground(new java.awt.Color(255, 255, 255));
        jlbQLNH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbQLNH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/management(1).png"))); // NOI18N
        jlbQLNH.setText("Quản lí nhập hàng");
        jlbQLNH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbQLNH.setIconTextGap(20);

        javax.swing.GroupLayout jpnQLNHLayout = new javax.swing.GroupLayout(jpnQLNH);
        jpnQLNH.setLayout(jpnQLNHLayout);
        jpnQLNHLayout.setHorizontalGroup(
            jpnQLNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLNHLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLNH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnQLNHLayout.setVerticalGroup(
            jpnQLNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLNHLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLNH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jpnTKhoan.setBackground(new java.awt.Color(26, 29, 31));

        jlbTKhoan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbTKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jlbTKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbTKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/user(1) (1).png"))); // NOI18N
        jlbTKhoan.setText("Tài khoản");
        jlbTKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbTKhoan.setIconTextGap(20);
        jlbTKhoan.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jpnTKhoanLayout = new javax.swing.GroupLayout(jpnTKhoan);
        jpnTKhoan.setLayout(jpnTKhoanLayout);
        jpnTKhoanLayout.setHorizontalGroup(
            jpnTKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTKhoanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbTKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnTKhoanLayout.setVerticalGroup(
            jpnTKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTKhoanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbTKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jpnQLXH.setBackground(new java.awt.Color(26, 29, 31));
        jpnQLXH.setPreferredSize(new java.awt.Dimension(180, 42));

        jlbQLXH.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbQLXH.setForeground(new java.awt.Color(255, 255, 255));
        jlbQLXH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlbQLXH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/medical (1).png"))); // NOI18N
        jlbQLXH.setText("Quản lí xuất hàng");
        jlbQLXH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbQLXH.setIconTextGap(20);

        javax.swing.GroupLayout jpnQLXHLayout = new javax.swing.GroupLayout(jpnQLXH);
        jpnQLXH.setLayout(jpnQLXHLayout);
        jpnQLXHLayout.setHorizontalGroup(
            jpnQLXHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLXHLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbQLXH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnQLXHLayout.setVerticalGroup(
            jpnQLXHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQLXHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbQLXH)
                .addGap(10, 10, 10))
        );

        jpnPhieuNhap.setBackground(new java.awt.Color(26, 29, 31));

        jlbPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        jlbPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/invoice (1).png"))); // NOI18N
        jlbPhieuNhap.setText("Phiếu nhập hàng");
        jlbPhieuNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbPhieuNhap.setIconTextGap(20);

        javax.swing.GroupLayout jpnPhieuNhapLayout = new javax.swing.GroupLayout(jpnPhieuNhap);
        jpnPhieuNhap.setLayout(jpnPhieuNhapLayout);
        jpnPhieuNhapLayout.setHorizontalGroup(
            jpnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuNhapLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnPhieuNhapLayout.setVerticalGroup(
            jpnPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPhieuNhapLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbPhieuNhap)
                .addGap(10, 10, 10))
        );

        jpnPhieuXuat.setBackground(new java.awt.Color(26, 29, 31));

        jlbPhieuXuat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbPhieuXuat.setForeground(new java.awt.Color(255, 255, 255));
        jlbPhieuXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/invoice (1).png"))); // NOI18N
        jlbPhieuXuat.setText("Phiếu xuất hàng");
        jlbPhieuXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbPhieuXuat.setIconTextGap(20);

        javax.swing.GroupLayout jpnPhieuXuatLayout = new javax.swing.GroupLayout(jpnPhieuXuat);
        jpnPhieuXuat.setLayout(jpnPhieuXuatLayout);
        jpnPhieuXuatLayout.setHorizontalGroup(
            jpnPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuXuatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbPhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jpnPhieuXuatLayout.setVerticalGroup(
            jpnPhieuXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPhieuXuatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbPhieuXuat)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpnQLDM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQLSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jpnTKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQLNH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnQLXH, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jpnPhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jpnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnQLDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnQLNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpnQLXH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jpnTKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnDisplay.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnDisplayLayout = new javax.swing.GroupLayout(jpnDisplay);
        jpnDisplay.setLayout(jpnDisplayLayout);
        jpnDisplayLayout.setHorizontalGroup(
            jpnDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        jpnDisplayLayout.setVerticalGroup(
            jpnDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jpnQLSPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpnQLSPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jpnQLSPFocusGained

    public void changeController() {
        ChangeController controller = new ChangeController(jpnDisplay);
        ArrayList<DanhMuc> danhMuc = new ArrayList<>();
        danhMuc.add(new DanhMuc("QLSP", jpnQLSP, jlbQLSP));
        danhMuc.add(new DanhMuc("QLDM", jpnQLDM, jlbQLDM));
        danhMuc.add(new DanhMuc("QLNH", jpnQLNH, jlbQLNH));
        danhMuc.add(new DanhMuc("QLXH", jpnQLXH, jlbQLXH));
        danhMuc.add(new DanhMuc("PhieuNhap", jpnPhieuNhap, jlbPhieuNhap));
        danhMuc.add(new DanhMuc("PhieuXuat", jpnPhieuXuat, jlbPhieuXuat));
        danhMuc.add(new DanhMuc("QLTKhoan", jpnTKhoan, jlbTKhoan));
        
        ChangeController menuController = new ChangeController(jpnDisplay);
        menuController.setQLSP(jpnQLSP, jlbQLSP);
        menuController.setEvent(danhMuc);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbPhieuNhap;
    private javax.swing.JLabel jlbPhieuXuat;
    private javax.swing.JLabel jlbQLDM;
    private javax.swing.JLabel jlbQLNH;
    private javax.swing.JLabel jlbQLSP;
    private javax.swing.JLabel jlbQLXH;
    private javax.swing.JLabel jlbTKhoan;
    private javax.swing.JPanel jpnDisplay;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnPhieuNhap;
    private javax.swing.JPanel jpnPhieuXuat;
    private javax.swing.JPanel jpnQLDM;
    private javax.swing.JPanel jpnQLNH;
    private javax.swing.JPanel jpnQLSP;
    private javax.swing.JPanel jpnQLXH;
    private javax.swing.JPanel jpnTKhoan;
    // End of variables declaration//GEN-END:variables
}
