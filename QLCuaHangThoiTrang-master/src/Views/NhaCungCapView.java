
package Views;

import Models.NhaCungCap;
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

public class NhaCungCapView extends javax.swing.JPanel {
    private String[] columnName = {"Mã NCC", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
    private static ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
    
    public NhaCungCapView() {
        initComponents();
    }
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    
    private void searchValue() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);   
        for (NhaCungCap i : list) {
            Object[] rowData = {i.getMa(), i.getTen(), i.getSoDienThoai(), i.getDiaChi()}; 
            defaultTableModel.addRow(rowData);
        }
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
        tableView.setRowSorter(rowSorter);
        searchInput.getDocument().addDocumentListener(new DocumentListener() {
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
        String text = searchInput.getText();
        if (text.trim().isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
    
    public void showListData() {
        this.list.clear();
        this.list = IO.NhaCungCapIO.readFromFile();
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);
        for (NhaCungCap i : list) {
            Object[] rowData = {i.getMa(), i.getTen(), i.getSoDienThoai(), i.getDiaChi()}; 
            defaultTableModel.addRow(rowData);
        }
        defaultTableModel.fireTableDataChanged();
        tableView.setModel(defaultTableModel);
        tableView.repaint();
    }    
    
    private String getIdSelected(int index, JTable table) {
        String id = "";
        id = table.getValueAt(index, 0).toString();
        return id;
    }
    
    private void deleteValue(int inputIndex) {
        String id = this.getIdSelected(inputIndex, tableView);
        IO.NhaCungCapIO.deleteById(id);
    }
    
    private void handleDeleteValue() {
        int index = -1;
        index = tableView.getSelectedRow();
        if (index != -1) {
            int rely = JOptionPane.showConfirmDialog(null, "Thao tác này sẽ ảnh hưởng đến thông tin các phiếu nhập và không thể hoàn tác. Bạn có muốn tiếp tục không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION){
                deleteValue(index);
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
            ChinhSuaNhaCungCap editView = new ChinhSuaNhaCungCap(this);
            String id = this.getIdSelected(index, tableView);
            editView.setValueInput(id);
            editView.display();
            
        } else {
            showMessage("Chưa chọn nhà cung cấp để sửa");
        }
    }
    
    public void editValue(String id, NhaCungCap value) {
        IO.NhaCungCapIO.updateInfoById(id, value);
    }
    
    private void handleAddValues() {
        ThemNhaCungCap addView = new ThemNhaCungCap(this);
        addView.display();
    }
    
    public void addValues(ArrayList<NhaCungCap> list) throws FileNotFoundException, IOException{
        FileWriter fw = new FileWriter("NhaCungCap.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (NhaCungCap i : list) {
            bw.write(i.toString());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        boxFunction = new javax.swing.JPanel();
        grid = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        boxSearch = new javax.swing.JPanel();
        searchInput = new javax.swing.JTextField();
        refreshBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        boxFunction.setBackground(new java.awt.Color(255, 255, 255));
        boxFunction.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        grid.setBackground(new java.awt.Color(255, 255, 255));
        grid.setLayout(new java.awt.GridLayout(1, 3, 16, 0));

        addBtn.setBackground(new java.awt.Color(75, 174, 79));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/add (1).png"))); // NOI18N
        addBtn.setText("Thêm mới");
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setFocusPainted(false);
        addBtn.setIconTextGap(6);
        addBtn.setPreferredSize(new java.awt.Dimension(84, 18));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        grid.add(addBtn);

        editBtn.setBackground(new java.awt.Color(255, 185, 46));
        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        editBtn.setText("Chỉnh sửa");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.setFocusPainted(false);
        editBtn.setIconTextGap(6);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        grid.add(editBtn);

        deleteBtn.setBackground(new java.awt.Color(225, 47, 64));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        deleteBtn.setText("Xóa");
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setIconTextGap(6);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        grid.add(deleteBtn);

        javax.swing.GroupLayout boxFunctionLayout = new javax.swing.GroupLayout(boxFunction);
        boxFunction.setLayout(boxFunctionLayout);
        boxFunctionLayout.setHorizontalGroup(
            boxFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );
        boxFunctionLayout.setVerticalGroup(
            boxFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxFunctionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grid, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        boxSearch.setBackground(new java.awt.Color(255, 255, 255));
        boxSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(39, 43, 48))); // NOI18N
        boxSearch.setOpaque(false);

        searchInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchInput.setDisabledTextColor(new java.awt.Color(39, 43, 48));
        searchInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchInputMousePressed(evt);
            }
        });
        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInputActionPerformed(evt);
            }
        });

        refreshBtn.setBackground(new java.awt.Color(15, 149, 224));
        refreshBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshBtn.setForeground(new java.awt.Color(255, 255, 255));
        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/reload-arrow.png"))); // NOI18N
        refreshBtn.setText("Làm mới");
        refreshBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setIconTextGap(6);
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout boxSearchLayout = new javax.swing.GroupLayout(boxSearch);
        boxSearch.setLayout(boxSearchLayout);
        boxSearchLayout.setHorizontalGroup(
            boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchInput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshBtn)
                .addContainerGap())
        );
        boxSearchLayout.setVerticalGroup(
            boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boxSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(boxSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchInput)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(boxFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boxSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boxFunction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setOpaque(false);

        tableView.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableView.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableView.setDragEnabled(true);
        tableView.setRowHeight(25);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setShowGrid(true);
        jScrollPane2.setViewportView(tableView);
        if (tableView.getColumnModel().getColumnCount() > 0) {
            tableView.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableView.getColumnModel().getColumn(0).setMaxWidth(40);
            tableView.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        this.showListData();
        searchInput.setText("");
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        this.handleEditValue();
    }//GEN-LAST:event_editBtnActionPerformed

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
//        
        
    }//GEN-LAST:event_addBtnMouseClicked

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        this.handleAddValues();
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        this.handleDeleteValue();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInputActionPerformed

    private void searchInputMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchInputMousePressed
        // TODO add your handling code here:
        this.searchValue();
    }//GEN-LAST:event_searchInputMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel boxFunction;
    private javax.swing.JPanel boxSearch;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel grid;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTextField searchInput;
    private javax.swing.JTable tableView;
    // End of variables declaration//GEN-END:variables
}
