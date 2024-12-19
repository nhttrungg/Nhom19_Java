
package Views;

import Models.MatHang;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ThemMatHang extends javax.swing.JFrame {
    private ArrayList<MatHang> list = new ArrayList<MatHang>();
    private MatHangView mainView;

    public ThemMatHang(MatHangView inputView) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.mainView = inputView;
    }
    
    public void display() {
        this.setVisible(true);
    }
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Cảnh báo nhập dữ liệu", JOptionPane.WARNING_MESSAGE);
    }
    
    private boolean hasId(String id) {
        for (int i = 0; i < IO.MatHangIO.readFromFile().size(); i++) {
            if (IO.MatHangIO.readFromFile().get(i).getMa().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkInput() {
        boolean check = true;
        try {
            String ma = inputMa.getText();
            String ten = inputTen.getText();
            if (ma.length() == 0) {
                showMessage("Không được để trống mã nhà cung cấp");
                check = false;
            } else if (this.hasId(ma)) {
                showMessage("Mã mặt hàng đã tồn tại");
                check = false;
            }
            if (ten.length() == 0) {
                showMessage("Không được để trống tên nhà cung cấp");
                check = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
    
    
    private void resetInput() {
        inputMa.setText("");
        inputTen.setText("");
    }
    
    private boolean listHasData() {
        if (!this.list.isEmpty()) {
            return true;
        } 
        return false;
    }
    
    private void getInfor() {
        if (checkInput()) {
            MatHang newValue = new MatHang(inputMa.getText(), inputTen.getText());
            list.add(newValue);
            resetInput();
            JOptionPane.showMessageDialog(null, "Đã thêm vào danh sách chờ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void handleAddData() {
        if (!listHasData()) {
            int rely = JOptionPane.showConfirmDialog(null, "Bạn chưa có dữ liệu trong danh sách chờ. Bạn muốn kết thúc chứ?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION) {
                this.dispose();
            }
        } else {
            try {
                this.mainView.addValues(this.list);
                JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                this.mainView.showListData();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Thêm mới không thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void handleCancel() {
        if (listHasData()) {
            int rely = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm các mặt hàng trước đó không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION) {
                handleAddData();
            } else {
                this.dispose();
            }
        } else {
            this.dispose();
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputTen = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnHuy = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thêm mặt hàng");
        setMaximumSize(new java.awt.Dimension(574, 300));
        setMinimumSize(new java.awt.Dimension(574, 300));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new java.awt.CardLayout(20, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÊM MỚI MẶT HÀNG");
        jPanel2.add(jLabel1, "card2");

        jLabel2.setText("Mã mặt hàng:");

        inputMa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputMaFocusLost(evt);
            }
        });
        inputMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMaActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên mặt hàng:");

        inputTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputTenFocusLost(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 3, 30, 0));

        btnHuy.setBackground(new java.awt.Color(225, 47, 64));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel3.add(btnHuy);

        btnThemMoi.setBackground(new java.awt.Color(75, 174, 79));
        btnThemMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnThemMoi);

        btnHoanThanh.setBackground(new java.awt.Color(51, 153, 255));
        btnHoanThanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHoanThanh.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanh.setText("Hoàn thành");
        btnHoanThanh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });
        jPanel3.add(btnHoanThanh);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputMa)
                            .addComponent(inputTen, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                .addGap(121, 121, 121))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMaActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        this.getInfor();
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void inputMaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputMaFocusLost
        inputMa.setText(inputMa.getText().trim());
    }//GEN-LAST:event_inputMaFocusLost

    private void inputTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTenFocusLost
        inputTen.setText(inputTen.getText().trim());
    }//GEN-LAST:event_inputTenFocusLost

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
       this.handleCancel();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
        this.handleAddData();
    }//GEN-LAST:event_btnHoanThanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JTextField inputMa;
    private javax.swing.JTextField inputTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
