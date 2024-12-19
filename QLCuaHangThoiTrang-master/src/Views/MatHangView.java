
package Views;

import Models.MatHang;
import Models.Product;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MatHangView extends javax.swing.JPanel {
    private String[] columnName = {"Mã mặt hàng", "Tên mặt hàng"};
    private ArrayList<MatHang> list = new ArrayList<MatHang>();
    private ArrayList<Product> listProduct = new ArrayList<Product>();

    public MatHangView() {
        initComponents();
    }
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    
    public void showListData() {
        this.list.clear();
        this.list = IO.MatHangIO.readFromFile();
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);   
        for (MatHang i : list) {
            Object[] rowData = {i.getMa(), i.getTen()}; 
            defaultTableModel.addRow(rowData);
        }
        defaultTableModel.fireTableDataChanged();
        tableView.setModel(defaultTableModel);
        tableView.repaint();
    }    
    
    private void searchValue() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);   
        for (MatHang i : list) {
            Object[] rowData = {i.getMa(), i.getTen()}; 
            defaultTableModel.addRow(rowData);
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        tableView.setRowSorter(rowSorter);
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
    
    private void deleteValue(String id) {
        IO.ProductIO.deleteByIdMatHang(id);
        IO.MatHangIO.deteleById(id);
    }
    
    private void handleDeleteValue() {
        int index = -1;
        index = tableView.getSelectedRow();
        if (index != -1) {
            int rely = JOptionPane.showConfirmDialog(null, "Thao tác này sẽ xóa tất cả các sản phẩm thuộc mặt hàng này và không thể hoàn tác. Bạn có muốn tiếp tục không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION){
                String id = this.getIdSelected(index, tableView);
                this.deleteValue(id);
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                showListData();
            }
        } else {
            showMessage("Chưa chọn nhà cung cấp để xóa");
        }
    }
    
    private void handleEditValue() {
        int index = -1;
        index = tableView.getSelectedRow();
        if (index != -1) {
            ChinhSuaMatHang editView = new ChinhSuaMatHang(this);
            String id = this.getIdSelected(index, tableView);
            editView.setInputValue(id);
            editView.display();
        } else {
            showMessage("Chưa chọn mặt hàng để sửa");
        }
    }
    
    public void editValue(String index, MatHang value) {
        IO.MatHangIO.updateInfoById(index, value);
    }
    
    private void handleAddValues() {
        ThemMatHang addView = new ThemMatHang(this);
        addView.display();
    }
    
    public void addValues(ArrayList<MatHang> list) throws FileNotFoundException, IOException{
        FileWriter fw = new FileWriter("MatHang.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (MatHang i : list) {
            bw.write(i.toString());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        inputSearch = new javax.swing.JTextField();
        refreshBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(335, 53));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(295, 18));
        jPanel4.setLayout(new java.awt.GridLayout(1, 3, 16, 0));

        addBtn.setBackground(new java.awt.Color(75, 174, 79));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/add (1).png"))); // NOI18N
        addBtn.setText("Thêm mới");
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel4.add(addBtn);

        editBtn.setBackground(new java.awt.Color(255, 185, 46));
        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        editBtn.setText("Chỉnh sửa");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel4.add(editBtn);

        deleteBtn.setBackground(new java.awt.Color(225, 47, 64));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        deleteBtn.setText("Xóa");
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel4.add(deleteBtn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        inputSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inputSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputSearchMousePressed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(15, 149, 224));
        refreshBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/reload-arrow.png"))); // NOI18N
        refreshBtn.setText("Làm mới");
        refreshBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshBtn)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputSearch)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addContainerGap())
        );

        tableView.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableView.setModel(new javax.swing.table.DefaultTableModel(
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
        tableView.setRowHeight(25);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setShowGrid(true);
        jScrollPane1.setViewportView(tableView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        this.handleDeleteValue();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        this.handleAddValues();
    }//GEN-LAST:event_addBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        this.showListData();
        inputSearch.setText("");
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        this.handleEditValue();
    }//GEN-LAST:event_editBtnActionPerformed

    private void inputSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputSearchMousePressed
        // TODO add your handling code here:
        this.searchValue();
    }//GEN-LAST:event_inputSearchMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTable tableView;
    // End of variables declaration//GEN-END:variables
}
