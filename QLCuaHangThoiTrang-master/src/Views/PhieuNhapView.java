
package Views;

import IO.PdfIO;
import Models.NhaCungCap;
import Models.PhieuNhap;
import Models.SanPhamNhap;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class PhieuNhapView extends javax.swing.JPanel {
    private final String[] columnName = {"Mã phiếu", "Tên nhà cung cấp", "Ngày tạo", "Lần cập nhật gần nhất", "Tổng tiền"};
    
    private ArrayList<PhieuNhap> listPhieuNhap = new ArrayList<PhieuNhap>();
    
    public PhieuNhapView() {
        initComponents();
        this.showListData();
    }
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    
    public void showListData() {
        this.listPhieuNhap.clear();
        this.listPhieuNhap = IO.PhieuNhapIO.readFromFile();
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);
        for (int i = 0; i < this.listPhieuNhap.size(); i++) {
            PhieuNhap value = this.listPhieuNhap.get(i);
            NhaCungCap nhaCungCap = IO.NhaCungCapIO.getInfoById(value.getMaNhaCungCap());
            String tenNCC = "";
            if (nhaCungCap.getMa() == null) {
                tenNCC = "Không tồn tại";
            } else {
                tenNCC = nhaCungCap.getTen();
            }
            Object[] rowData = {value.getMa(), tenNCC, value.getNgayTao(), value.getNgayCapNhat(), String.format("%,d", value.getTien())}; 
            defaultTableModel.addRow(rowData);
        }
        defaultTableModel.fireTableDataChanged();
        tableViewData.setModel(defaultTableModel);
        tableViewData.repaint();
    }
    
     private void searchValue() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);   
        for (PhieuNhap i : this.listPhieuNhap) {
            NhaCungCap nhaCungCap = IO.NhaCungCapIO.getInfoById(i.getMaNhaCungCap());
            String tenNCC = nhaCungCap.getTen();
            Object[] rowData = {i.getMa(), tenNCC, i.getNgayTao(), i.getNgayCapNhat(), String.format("%,d", i.getTien())}; 
            defaultTableModel.addRow(rowData);
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        tableViewData.setRowSorter(rowSorter);
        inputSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter(rowSorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter(rowSorter);                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applyFilter(rowSorter);                
            }
        });
    }
    
    private void applyFilter(TableRowSorter rowSorter) {
        String text = inputSearch.getText();
        if (text.trim().isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
    
    private String getIdSelected(int index, JTable table) {
        String id = "";
        id = table.getValueAt(index, 0).toString();
        return id;
    }
    
    private void handleDetailValue() {
        int index = -1;
        index = tableViewData.getSelectedRow();
        if (index != -1) {
            PhieuNhap currentValue = IO.PhieuNhapIO.getInfoById(this.getIdSelected(index, tableViewData));
            String maPhieu = currentValue.getMa();
            String maNCC = currentValue.getMaNhaCungCap();
            NhaCungCap nhaCungCap = IO.NhaCungCapIO.getInfoById(maNCC);
            if (nhaCungCap.getMa() == null) {
                nhaCungCap.setMa("Không tồn tại");
                nhaCungCap.setTen("Không tồn tại");
                nhaCungCap.setDiaChi("Không tồn tại");
                nhaCungCap.setSoDienThoai("Không tồn tại");
            }
            ChiTietPhieuNhap detailView = new ChiTietPhieuNhap(maPhieu, nhaCungCap);
            detailView.display();
        } else {
            this.showMessage("Chưa chọn phiếu để xem chi tiết");
        }
    }
    
    private void handleDeleteValue() {
        int index = -1;
        index = tableViewData.getSelectedRow();
        if (index != -1) {
            int rely = JOptionPane.showConfirmDialog(null, "Sau khi xóa sẽ không thể hoàn tác. Tiếp tục?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION){
                String maPhieu = this.getIdSelected(index, tableViewData);
                IO.SanPhamNhapIO.deleteByIdMaPhieuNhap(maPhieu);
                IO.PhieuNhapIO.deleteById(maPhieu);
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                this.showListData();
            }
        } else {
            showMessage("Chưa chọn phiếu nhập để xóa");
        }
    }
    
    private void handleEditValue() {
        int index = -1;
        index = tableViewData.getSelectedRow();
        if (index != -1) {
            JFrame frameView = new JFrame();
            frameView.setLayout(new BorderLayout());
            QLNHView editView = new QLNHView(index, this, frameView);
            editView.setValue(this.getIdSelected(index, tableViewData));
            frameView.add(editView);
            frameView.setVisible(true);
            frameView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameView.setSize(1050, 700);
            frameView.setResizable(false);
            frameView.setTitle("Chỉnh sửa phiếu nhập");
            frameView.setLocationRelativeTo(null);
        } else {
            showMessage("Chưa chọn phiếu nhập để sửa");
        }
    }
    
    public void editValue(PhieuNhap value, ArrayList<SanPhamNhap> data) {
        IO.PhieuNhapIO.updateInfo(value);
        IO.SanPhamNhapIO.updateDataByIdMaPhieu(value.getMa(), data);
    }
    
    private void handleExportPdfFile() {
        int index = -1;
        index = tableViewData.getSelectedRow();
        if (index != -1) {
            String maPhieu = this.listPhieuNhap.get(index).getMa();
            PdfIO.handleExportPdfFile(this, maPhieu);
        } else {
            showMessage("Chưa chọn phiếu nhập để xuất PDF");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnXuatPhieu = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        btnChinhSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        inputSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewData = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(1, 3, 16, 0));

        btnXuatPhieu.setBackground(new java.awt.Color(75, 174, 79));
        btnXuatPhieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/pdf (1).png"))); // NOI18N
        btnXuatPhieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuatPhieu.setLabel("Xuất phiếu");
        btnXuatPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPhieuActionPerformed(evt);
            }
        });
        jPanel5.add(btnXuatPhieu);

        btnChiTiet.setBackground(new java.awt.Color(15, 149, 224));
        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/eye (1).png"))); // NOI18N
        btnChiTiet.setText("Chi tiết");
        btnChiTiet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });
        jPanel5.add(btnChiTiet);

        btnChinhSua.setBackground(new java.awt.Color(255, 185, 46));
        btnChinhSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChinhSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        btnChinhSua.setText("Chỉnh sửa");
        btnChinhSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaActionPerformed(evt);
            }
        });
        jPanel5.add(btnChinhSua);

        btnXoa.setBackground(new java.awt.Color(225, 47, 64));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        inputSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputSearchMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputSearch)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputSearch)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableViewData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableViewData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableViewData.setRowHeight(25);
        tableViewData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewData.setShowGrid(true);
        jScrollPane1.setViewportView(tableViewData);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaActionPerformed
        this.handleEditValue();
    }//GEN-LAST:event_btnChinhSuaActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        this.handleDetailValue();
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.handleDeleteValue();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void inputSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputSearchMousePressed
        this.searchValue();
    }//GEN-LAST:event_inputSearchMousePressed

    private void btnXuatPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPhieuActionPerformed
        // TODO add your handling code here:
        this.handleExportPdfFile();
    }//GEN-LAST:event_btnXuatPhieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatPhieu;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableViewData;
    // End of variables declaration//GEN-END:variables
}
