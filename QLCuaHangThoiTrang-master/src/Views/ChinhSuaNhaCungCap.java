
package Views;

import Models.NhaCungCap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChinhSuaNhaCungCap extends javax.swing.JFrame {
    private NhaCungCapView mainView;

    public ChinhSuaNhaCungCap(NhaCungCapView inputView) {
        initComponents();
        this.mainView = inputView;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void display() {
        this.setVisible(true);
    }
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Cảnh báo nhập dữ liệu", JOptionPane.WARNING_MESSAGE);
    }
    
    public void setValueInput(String id) {
        NhaCungCap nhaCungCap = IO.NhaCungCapIO.getInfoById(id);
        inputMaNCC.setText(nhaCungCap.getMa());
        inputTenNCC.setText(nhaCungCap.getTen());
        inputSdtNCC.setText(nhaCungCap.getSoDienThoai());
        inputDiaChiNCC.setText(nhaCungCap.getDiaChi());
    }
    
    private boolean checkInput() {
        boolean check = true;
        try {
            String ma = inputMaNCC.getText();
            String ten = inputTenNCC.getText();
            String dienThoai = inputSdtNCC.getText();
            String diaChi = inputDiaChiNCC.getText();
            if (ma.length() == 0) {
                showMessage("Không được để trống mã nhà cung cấp");
                check = false;
            }
            if (ten.length() == 0) {
                showMessage("Không được để trống tên nhà cung cấp");
                check = false;
            }
            if (dienThoai.length() == 0) {
                showMessage("Không được để trống số điện thoại");
                check = false;
            } else if (dienThoai.length() < 7 || dienThoai.length() > 11) {
                showMessage("Độ dài số điện thoại không hợp lệ");
                check = false;
            } else if (dienThoai.length() != 0) {
                Long sdt = Long.parseLong(inputSdtNCC.getText());
            }
            if (diaChi.length() == 0) {
                showMessage("Không được để trống địa chỉ");
                check = false;
            }
        } catch (NumberFormatException e) {
            showMessage("Số điện thoại không thể chứa kí tự");
            check = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
    public void editValue() {
        if (checkInput()) {
            NhaCungCap value = new NhaCungCap();
            value.setMa(inputMaNCC.getText());
            value.setTen(inputTenNCC.getText());
            value.setSoDienThoai(inputSdtNCC.getText());
            value.setDiaChi(inputDiaChiNCC.getText());
            this.mainView.editValue(value.getMa(), value);
            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            this.mainView.showListData();
        } else {
            JOptionPane.showMessageDialog(null, "Chỉnh sửa không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputMaNCC = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputTenNCC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputSdtNCC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputDiaChiNCC = new javax.swing.JTextArea();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chỉnh sửa thông tin");
        setMaximumSize(new java.awt.Dimension(599, 327));
        setMinimumSize(new java.awt.Dimension(599, 327));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã nhà cung cấp:");

        inputMaNCC.setEditable(false);
        inputMaNCC.setFocusable(false);

        jLabel2.setText("Tên nhà cung cấp:");

        jLabel3.setText("Số điện thoại:");

        jLabel4.setText("Địa chỉ:");

        inputDiaChiNCC.setColumns(20);
        inputDiaChiNCC.setLineWrap(true);
        inputDiaChiNCC.setRows(5);
        jScrollPane1.setViewportView(inputDiaChiNCC);

        btnEdit.setBackground(new java.awt.Color(255, 185, 46));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setText("Lưu thay đổi");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(inputSdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inputMaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(inputTenNCC))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputSdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.editValue();
    }//GEN-LAST:event_btnEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JTextArea inputDiaChiNCC;
    private javax.swing.JTextField inputMaNCC;
    private javax.swing.JTextField inputSdtNCC;
    private javax.swing.JTextField inputTenNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
