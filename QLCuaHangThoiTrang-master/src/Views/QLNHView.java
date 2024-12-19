  
package Views;

import Models.MatHang;
import Models.NhaCungCap;
import Models.PhieuNhap;
import Models.Product;
import Models.SanPhamNhap;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class QLNHView extends javax.swing.JPanel {
    
    private String[] columnName = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Số lượng", "Giá"};
    
    private DefaultTableModel defaultTableSelectedModel = new DefaultTableModel(this.columnName, 0);
    private DefaultTableModel defaultTableProductModel = new DefaultTableModel(this.columnName, 0);
    
    private ArrayList<Product> listProduct = new ArrayList<Product>();
    private ArrayList<NhaCungCap> listNhaCungCap = new ArrayList<NhaCungCap>();
    private ArrayList<Product> listSelectedProduct = new ArrayList<Product>();
    
    private PhieuNhap currentValue = new PhieuNhap();
    private PhieuNhapView mainView;
    private String type = "Create";
    private JFrame frameView;
    
    public QLNHView() {
        initComponents();
        this.listProduct = getListProducts();
        this.listNhaCungCap = IO.NhaCungCapIO.readFromFile();
        inputMaPhieu.setText("PN" + System.currentTimeMillis());
        this.showTableProduct("Get");
        this.setSelectedNhaCungCap();
        this.showListSelected("Create");
    }
    
    public QLNHView(int inputIndex, PhieuNhapView inputMainView, JFrame inputFrameView) {
        initComponents();
        this.mainView = inputMainView;
        this.frameView = inputFrameView;
    }
    
    public void setValue(String maPhieuNhap) {
        this.currentValue = IO.PhieuNhapIO.getInfoById(maPhieuNhap);
        this.inputMaPhieu.setText(this.currentValue.getMa());
        ArrayList<SanPhamNhap> listSPNhap = IO.SanPhamNhapIO.getListById(maPhieuNhap);
        for (int i = 0; i < listSPNhap.size(); i++) {
            Product value = IO.ProductIO.getInfoProductById(listSPNhap.get(i).getMaSanPham());
            String category = "";
            if (value.getProductID() == null) {
                value.setProductName("Không tồn tại");
                category = "Không tồn tại";
                long price = listSPNhap.get(i).getThanhTien() / listSPNhap.get(i).getSoLuong();
                value.setProductPrice(price);
            } else {
                category = IO.MatHangIO.getNameById(value.getProductCategory());
            }
            this.listSelectedProduct.add(new Product(listSPNhap.get(i).getMaSanPham(), value.getProductName(), category, listSPNhap.get(i).getSoLuong(), value.getProductPrice()));
        }
        this.listProduct = getListProducts();
        NhaCungCap currentNCC = IO.NhaCungCapIO.getInfoById(this.currentValue.getMaNhaCungCap());
        if (currentNCC.getMa() == null) {
            this.listNhaCungCap.add(new NhaCungCap(this.currentValue.getMaNhaCungCap(), "Không tồn tại", "Không tồn tại", "Không tồn tại"));
        } else {
            this.listNhaCungCap.add(currentNCC);
        }
        this.setSelectedNhaCungCap(this.currentValue.getMaNhaCungCap());
        this.showTableProduct("Edit");
        this.showListSelected("Edit");
        btnDone.setBackground(new Color(75,174,79));
        btnDone.setText("Lưu chỉnh sửa");
        this.type = "Edit";
    }
    
    
    private void showMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private ArrayList<Product> getListProducts() {
        ArrayList<Product> list = IO.ProductIO.readFromFile();
        for (int i = 0; i < list.size(); i++) {
            String loaiSP = IO.MatHangIO.getNameById(list.get(i).getProductCategory());
            list.get(i).setProductCategory(loaiSP);
        }
        return list;
    }
    
     private void searchValue() {
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableProductModel);
        tableViewProduct.setRowSorter(rowSorter);
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
    
    private void chooseCreateOrEdit() {
        if (type.equalsIgnoreCase("Create")) {
            this.handleCreatePhieuNhap();
        }
        if (type.equalsIgnoreCase("Edit")) {
            this.handleEditPhieuNhap();
        }
    }
    
    private void handleEditPhieuNhap() {
        if (!this.listSelectedProduct.isEmpty()) {
            String maPhieuNhap = this.inputMaPhieu.getText();            
            ArrayList<SanPhamNhap> dsSanPhamNhap = new ArrayList<SanPhamNhap>();
            for (int i = 0; i < this.listSelectedProduct.size(); i++) {
                long gia = this.listSelectedProduct.get(i).getProductPrice() * this.listSelectedProduct.get(i).getProductQuantity();
                dsSanPhamNhap.add(new SanPhamNhap(this.listSelectedProduct.get(i).getProductID(), this.listSelectedProduct.get(i).getProductQuantity(), gia, maPhieuNhap));
            }
            this.mainView.editValue(this.getValue(), dsSanPhamNhap);
            JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.frameView.dispose();
            this.mainView.showListData();
        } else {
            this.showMessage("Không thể cập nhật phiếu, danh sách sản phẩm nhập đang trống");
        }
    }
    
    private void handleCreatePhieuNhap() {
        if (!this.listSelectedProduct.isEmpty()) {
            String maPhieuNhap = this.inputMaPhieu.getText();
            IO.PhieuNhapIO.writeToFile(this.getValue());
            ArrayList<SanPhamNhap> dsSanPhamNhap = new ArrayList<SanPhamNhap>();
            for (int i = 0; i < this.listSelectedProduct.size(); i++) {
                long gia = this.listSelectedProduct.get(i).getProductPrice() * this.listSelectedProduct.get(i).getProductQuantity();
                dsSanPhamNhap.add(new SanPhamNhap(this.listSelectedProduct.get(i).getProductID(), this.listSelectedProduct.get(i).getProductQuantity(), gia, maPhieuNhap));
            }
            IO.SanPhamNhapIO.writeToFile(dsSanPhamNhap, true);
            JOptionPane.showMessageDialog(null, "Tạo phiếu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            this.handleRefresh();
        } else {
            this.showMessage("Không thể nhập hàng, danh sách sản phẩm nhập đang trống");
        }
    }
    
    private PhieuNhap getValue() {
        String maNCC = this.listNhaCungCap.get(inputNhaCungCap.getSelectedIndex()).getMa();
        NumberFormat numberFormat = NumberFormat.getInstance();
        long gia = 0;
        try {
            gia = numberFormat.parse(this.cost.getText()).longValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PhieuNhap value = new PhieuNhap(this.inputMaPhieu.getText(), maNCC, gia);
        return value;
    }
    
    private void showTableProduct(String type) {
        if (!this.listProduct.isEmpty()) {
            if (type.equalsIgnoreCase("Get")) {
                this.defaultTableProductModel.setRowCount(0);
                for (Product i : this.listProduct) {
                    Object[] rowData = {i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), String.format("%,d", i.getProductPrice())}; 
                    this.defaultTableProductModel.addRow(rowData);
                }
            }
            if (type.equalsIgnoreCase("Edit")) {
                this.defaultTableProductModel.setRowCount(0);
                for (int i = 0; i < this.listProduct.size(); i++) {
                    boolean hasValue = false;
                    for (int j = 0; j < this.listSelectedProduct.size(); j++) {
                        if (this.listProduct.get(i).getProductID().equalsIgnoreCase(this.listSelectedProduct.get(j).getProductID())) {
                            hasValue = true;
                            break;
                        }
                    }
                    if (!hasValue) {
                        Product currentValue = this.listProduct.get(i);
                        Object[] rowData = {currentValue.getProductID(), currentValue.getProductName(), currentValue.getProductCategory(), currentValue.getProductQuantity(), String.format("%,d", currentValue.getProductPrice())}; 
                        this.defaultTableProductModel.addRow(rowData);
                    }
                }
            }
            if (type.equalsIgnoreCase("Remove")) {
                int index = this.tableViewProduct.getSelectedRow();
                this.defaultTableProductModel.removeRow(index);
            }
        }
        tableViewProduct.setModel(this.defaultTableProductModel);
        tableViewProduct.repaint();
    }
    
    private Product getValueTable(int index, JTable table) {
        Product value = new Product();
        value.setProductID(table.getValueAt(index, 0).toString());
        value.setProductName(table.getValueAt(index, 1).toString());
        value.setProductCategory(table.getValueAt(index, 2).toString());
        value.setProductQuantity(Integer.parseInt(table.getValueAt(index, 3).toString()));
        NumberFormat numberFormat = NumberFormat.getInstance();
        long gia = 0;
        try {
            gia = numberFormat.parse(table.getValueAt(index, 4).toString()).longValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        value.setProductPrice(gia);
        
        return value;
    }
    
    public void showListSelected(String type) {
        if (!this.listSelectedProduct.isEmpty()) {
            if (type.equalsIgnoreCase("Create")) { 
                Product value = this.listSelectedProduct.getLast();
                Object[] rowData = {value.getProductID(), value.getProductName(), value.getProductCategory(), value.getProductQuantity(), String.format("%,d", value.getProductPrice())};
                this.defaultTableSelectedModel.addRow(rowData);
            }
            if (type.equalsIgnoreCase("Edit")) {
                this.defaultTableSelectedModel.setRowCount(0);
                for (Product i : this.listSelectedProduct) {
                    Object[] rowData = {i.getProductID(), i.getProductName(), i.getProductCategory(), i.getProductQuantity(), String.format("%,d", i.getProductPrice())};
                    defaultTableSelectedModel.addRow(rowData);
                }
            }
            if (type.equalsIgnoreCase("Remove")) {
                int index = this.tableViewSelected.getSelectedRow();
                this.listSelectedProduct.remove(index);
                Product value = this.getValueTable(index, this.tableViewSelected);
                for (int i = 0; i < this.listProduct.size(); i++) {
                    if (value.getProductID().equalsIgnoreCase(this.listProduct.get(i).getProductID())) {
                        int quantity = this.listProduct.get(i).getProductQuantity();
                        value.setProductQuantity(quantity);
                        break;
                    }
                }
                Object[] data = {value.getProductID(), value.getProductName(), value.getProductCategory(), value.getProductQuantity(), String.format("%,d", value.getProductPrice())};
                this.defaultTableSelectedModel.removeRow(index);
                this.defaultTableProductModel.addRow(data);
                this.tableViewProduct.setModel(this.defaultTableProductModel);
                this.tableViewProduct.repaint();
            }
        } else {
            this.defaultTableSelectedModel.setRowCount(0);
        }
        tableViewSelected.setModel(defaultTableSelectedModel);
        tableViewSelected.repaint();
        this.showPrice();
    }
    
    private void showPrice() {
        long result = 0;
        if (!this.listSelectedProduct.isEmpty()) {
            for (Product i : this.listSelectedProduct) {
                result = result + (i.getProductPrice() * i.getProductQuantity());
            }
        } else {
            result = 0;
        }
        cost.setText(String.format("%,d", result));
    }
    
    private void setSelectedNhaCungCap() {
        inputNhaCungCap.removeAllItems();
        for (NhaCungCap i : listNhaCungCap) {
            inputNhaCungCap.addItem(i.getTen());
        }
    }
    
    private void setSelectedNhaCungCap(String inputMa) {
        inputNhaCungCap.removeAllItems();
        inputNhaCungCap.addItem(listNhaCungCap.get(0).getTen());
        inputNhaCungCap.setSelectedIndex(0);
    }
    
    private void resetInputQuantity() {
        inputQuantity.setText("1");
    }
    
    private void addProduct() {
        int index = -1;
        index = tableViewProduct.getSelectedRow();
        if (index != -1) {
            Product valueSelected = this.getValueTable(index, this.tableViewProduct);
            valueSelected.setProductQuantity(Integer.parseInt(inputQuantity.getText()));
            this.listSelectedProduct.add(valueSelected);
            this.showTableProduct("Remove");
            this.showListSelected("Create");
        } else {
            showMessage("Bạn chưa chọn sản phẩm để nhập");
        }
    }
    
    private void handleRefresh() {
        if (this.type.equalsIgnoreCase("Create")) {
            inputMaPhieu.setText("PN" + System.currentTimeMillis());
            this.showTableProduct("Get");
            this.listSelectedProduct.clear();
            this.showListSelected("Create");
        } else {
            this.listSelectedProduct.clear();
            String maPhieuNhap = inputMaPhieu.getText();
            ArrayList<SanPhamNhap> listSPNhap = IO.SanPhamNhapIO.getListById(maPhieuNhap);
            for (int i = 0; i < listSPNhap.size(); i++) {
                Product value = IO.ProductIO.getInfoProductById(listSPNhap.get(i).getMaSanPham());
                String category = "";
                if (value.getProductID() == null) {
                    value.setProductName("Không tồn tại");
                    category = "Không tồn tại";
                    long price = listSPNhap.get(i).getThanhTien() / listSPNhap.get(i).getSoLuong();
                    value.setProductPrice(price);
                } else {
                    category = IO.MatHangIO.getNameById(value.getProductCategory());
                }
                this.listSelectedProduct.add(new Product(listSPNhap.get(i).getMaSanPham(), value.getProductName(), category, listSPNhap.get(i).getSoLuong(), value.getProductPrice()));
            }
            this.showTableProduct("Edit");
            this.showListSelected("Edit");
        }
    }
    
    private void handleDeleteValue() {
        int index = -1;
        index = tableViewSelected.getSelectedRow();
        if (index != -1) {
            int rely = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (rely == JOptionPane.YES_NO_OPTION){
                this.showListSelected("Remove");
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            showMessage("Chưa chọn sản phẩm để xóa");
        }
    }
    
    private void handleEditValue() {
        int index = -1;
        index = tableViewSelected.getSelectedRow();
        if (index != -1) {
            ChinhSuaSoLuongSP editView = new ChinhSuaSoLuongSP(this, index);
            editView.setInputQuantity(this.listSelectedProduct.get(index).getProductQuantity());
            editView.display();
        } else {
            showMessage("Chưa chọn mặt hàng để sửa");
        }
    }
    
    public void editValue(int index, int quantity) {
        this.listSelectedProduct.get(index).setProductQuantity(quantity);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        inputSearch = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewProduct = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputQuantity = new javax.swing.JTextField();
        btnAddProduct = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputMaPhieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputNhaCungCap = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableViewSelected = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cost = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(1, 2, 6, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        inputSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inputSearchMousePressed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(15, 149, 224));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/reload-arrow.png"))); // NOI18N
        btnRefresh.setText("Làm mới");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(inputSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(inputSearch)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableViewProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableViewProduct.setModel(new javax.swing.table.DefaultTableModel(
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
        tableViewProduct.setDragEnabled(true);
        tableViewProduct.setRowHeight(25);
        tableViewProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewProduct.setShowGrid(true);
        tableViewProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableViewProduct);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Số lượng:");

        inputQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputQuantity.setText("1");
        inputQuantity.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAddProduct.setBackground(new java.awt.Color(75, 174, 79));
        btnAddProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/add (1).png"))); // NOI18N
        btnAddProduct.setText("Thêm");
        btnAddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(inputQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Mã phiếu nhập:");

        inputMaPhieu.setEditable(false);
        inputMaPhieu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputMaPhieu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputMaPhieu.setFocusable(false);

        jLabel3.setText("Nhà cung cấp:");

        inputNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputNhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inputNhaCungCap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inputNhaCungCapItemStateChanged(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tableViewSelected.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableViewSelected.setModel(new javax.swing.table.DefaultTableModel(
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
        tableViewSelected.setDragEnabled(true);
        tableViewSelected.setRowHeight(25);
        tableViewSelected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewSelected.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableViewSelected.setShowGrid(true);
        jScrollPane2.setViewportView(tableViewSelected);

        btnEdit.setBackground(new java.awt.Color(255, 185, 46));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        btnEdit.setText("Sửa số lượng");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(225, 47, 64));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        cost.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cost.setForeground(new java.awt.Color(225, 47, 64));
        cost.setText("0");

        btnDone.setBackground(new java.awt.Color(15, 149, 224));
        btnDone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDone.setForeground(new java.awt.Color(255, 255, 255));
        btnDone.setText("Nhập hàng");
        btnDone.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputMaPhieu)
                            .addComponent(inputNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cost)
                    .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void inputNhaCungCapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inputNhaCungCapItemStateChanged
//        System.out.println(inputNhaCungCap.getSelectedIndex());
    }//GEN-LAST:event_inputNhaCungCapItemStateChanged

    private void tableViewProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewProductMouseClicked
        this.resetInputQuantity();
    }//GEN-LAST:event_tableViewProductMouseClicked

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        this.addProduct();
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        this.handleRefresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.handleEditValue();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.handleDeleteValue();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        this.chooseCreateOrEdit();
    }//GEN-LAST:event_btnDoneActionPerformed

    private void inputSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputSearchMousePressed
        this.searchValue();
    }//GEN-LAST:event_inputSearchMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel cost;
    private javax.swing.JTextField inputMaPhieu;
    private javax.swing.JComboBox<String> inputNhaCungCap;
    private javax.swing.JTextField inputQuantity;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableViewProduct;
    private javax.swing.JTable tableViewSelected;
    // End of variables declaration//GEN-END:variables
}
