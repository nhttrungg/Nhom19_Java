package Views;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.JOptionPane;
import IO.ListProduct;
import IO.ReadWriteProduct;
import Models.Product;
import static Views.DienThongTInSanPham.danhsachsanpham;
import java.io.BufferedReader;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class QLSP extends javax.swing.JPanel {

    static int index = -1;
    static int checkFuntions;
    static String[] columnName = {"Mã sản phẩm", "Tên Sản phẩm", "Loại", "Số lượng", "Giá bán"};
    static DefaultTableModel dtmProduct = new DefaultTableModel(columnName, 0);
    static ListProduct listProduct = new ListProduct();
    ReadWriteProduct rwp = new ReadWriteProduct();
    String fileName = "QuanLySanPham.txt";

    public QLSP() {
        initComponents();
        Init();
    }

    private void Init() {
        danhsachsanpham = IO.ProductIO.readFromFile();
        try {
            rwp.readFile(fileName, danhsachsanpham);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Danh sach hien tai rong");
        }
        updateTable();
    }

    public void addProduct(Product product) {
        danhsachsanpham.add(product);
        updateTable();
    }

    private void showMessageWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private ArrayList<Models.MatHang> readFromFile(String url) {
        ArrayList<Models.MatHang> list = new ArrayList<Models.MatHang>();
        String FILE_NAME = "MatHang.txt";
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                list.add(new Models.MatHang(txt[0], txt[1]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            list = new ArrayList<Models.MatHang>();
        }
        return list;
    }

    public String getCategory(String category) {
        ArrayList<Models.MatHang> danhsachmathang = new ArrayList<>();
        danhsachmathang = readFromFile("MatHang.txt");
        String categoryItem = "";
        for (int i = 0; i < danhsachmathang.size(); i++) {
            if (category.equalsIgnoreCase(danhsachmathang.get(i).getMa())) {
                categoryItem = danhsachmathang.get(i).getTen();
            }
        }
        return categoryItem;
    }

    public void updateTable() {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        dtmProduct.setRowCount(0);
        for (Product product : danhsachsanpham) {
            String formattedNumber = format.format(product.getProductPrice());
            String price = formattedNumber;
            String categoryItem = product.getProductCategory();
            String category = getCategory(categoryItem);
            Object[] rowData = {product.getProductID(), product.getProductName(), category, product.getProductQuantity(), price};
            dtmProduct.addRow(rowData);
        }
        TableProduct.setModel(dtmProduct);
    }

    private void searchProduct() {
        String name = SearchText.getText();
        try {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(dtmProduct);
            TableProduct.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + name, 1));
        } catch (Exception e) {
            showMessageWarning("Lỗi");
        }
    }


    public String getIDSelected(int index, JTable table) {
        String id = "";
        id = (String) table.getValueAt(index, 0);
        return id;
    }
     private void deleteValue(int inputIndex) {
        String id = this.getIDSelected(inputIndex, TableProduct);
        IO.ProductIO.deleteByID(id);
    }

    private void deleteProduct() {
        danhsachsanpham.clear();
        ArrayList<Product> danhsachsauxoa = new ArrayList<>();
        int vitri = -1;
        vitri = TableProduct.getSelectedRow();
        if (vitri == -1) {
            showMessageWarning("Bạn chưa chọn sản phẩm để xóa");
        } else {
            String valueID = getIDSelected(vitri, TableProduct);
            int select = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (select == 0) {
                danhsachsauxoa = IO.ProductIO.deleteByID(valueID    );
                danhsachsanpham = danhsachsauxoa;
                PrintWriter writer;
                try {
                    writer = new PrintWriter(fileName);
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(QLSP.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Product product : danhsachsauxoa) {
                    rwp.writeFile(product, fileName, danhsachsauxoa);
                }
                vitri--;
                updateTable();
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void displayFunctions(String name, DienThongTInSanPham dienthongtinsanpham) {
        JFrame newFrame = new JFrame(name);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(720, 550);
        newFrame.add(dienthongtinsanpham);
        newFrame.setVisible(true);
        newFrame.setLocationRelativeTo(null);
    }

    private void ModifyProduct() {
        index = TableProduct.getSelectedRow();
        if (index == -1) {
            showMessageWarning("Bạn chưa chọn sản phẩm để sửa");
        } else {
            try {
                checkFuntions = 2;
                String valueID = getIDSelected(index, TableProduct);
                for (int i = 0; i < danhsachsanpham.size(); i++) {
                    if (danhsachsanpham.get(i).getProductID().equalsIgnoreCase(valueID)) {
                        String id = danhsachsanpham.get(i).getProductID();
                        String name = danhsachsanpham.get(i).getProductName();
                        String categoryItem = danhsachsanpham.get(i).getProductCategory();
                        String category = getCategory(categoryItem);
                        int quantity = danhsachsanpham.get(i).getProductQuantity();
                        long price = danhsachsanpham.get(i).getProductPrice();
                        DienThongTInSanPham dienthongtinsanpham = new DienThongTInSanPham(i, id, name, category, quantity, price);
                        displayFunctions("Sửa thông tin sản phẩm", dienthongtinsanpham);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HOME = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        JFeature = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        JSearch = new javax.swing.JPanel();
        SearchText = new javax.swing.JTextField();
        ButtonSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        ButtonAdd = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        ButtonDelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        ButtonModify = new javax.swing.JButton();
        Products = new javax.swing.JScrollPane();
        TableProduct = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Header.setBackground(new java.awt.Color(204, 204, 204));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Title.setText("Quản Lí Sản Phẩm");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JFeature.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(301, 60));

        JSearch.setBackground(new java.awt.Color(255, 255, 255));

        SearchText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchTextKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JSearchLayout = new javax.swing.GroupLayout(JSearch);
        JSearch.setLayout(JSearchLayout);
        JSearchLayout.setHorizontalGroup(
            JSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SearchText, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        JSearchLayout.setVerticalGroup(
            JSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSearchLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        ButtonSearch.setBackground(new java.awt.Color(15, 149, 244));
        ButtonSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonSearch.setForeground(new java.awt.Color(255, 255, 255));
        ButtonSearch.setText("Tìm kiếm");
        ButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonSearch))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        ButtonAdd.setBackground(new java.awt.Color(4, 190, 79));
        ButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/add (1).png"))); // NOI18N
        ButtonAdd.setText("Thêm mới");
        ButtonAdd.setMargin(new java.awt.Insets(10, 10, 10, 10));
        ButtonAdd.setPreferredSize(new java.awt.Dimension(120, 40));
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        ButtonDelete.setBackground(new java.awt.Color(255, 102, 102));
        ButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        ButtonDelete.setText("Xóa");
        ButtonDelete.setMargin(new java.awt.Insets(2, 10, 2, 10));
        ButtonDelete.setPreferredSize(new java.awt.Dimension(70, 32));
        ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        ButtonModify.setBackground(new java.awt.Color(255, 185, 46));
        ButtonModify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonModify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        ButtonModify.setText("Sửa");
        ButtonModify.setMaximumSize(new java.awt.Dimension(70, 32));
        ButtonModify.setMinimumSize(new java.awt.Dimension(0, 0));
        ButtonModify.setPreferredSize(new java.awt.Dimension(50, 32));
        ButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonModifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(ButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout JFeatureLayout = new javax.swing.GroupLayout(JFeature);
        JFeature.setLayout(JFeatureLayout);
        JFeatureLayout.setHorizontalGroup(
            JFeatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFeatureLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
        );
        JFeatureLayout.setVerticalGroup(
            JFeatureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Products.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TableProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sản phẩm", "Đã bán", "Loại", "Tình trạng", "Giá bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableProduct.setRowHeight(25);
        TableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Products.setViewportView(TableProduct);

        javax.swing.GroupLayout HOMELayout = new javax.swing.GroupLayout(HOME);
        HOME.setLayout(HOMELayout);
        HOMELayout.setHorizontalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOMELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HOMELayout.createSequentialGroup()
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JFeature, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Products, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        HOMELayout.setVerticalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOMELayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JFeature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Products, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HOME, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HOME, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSearchActionPerformed
        searchProduct();
    }//GEN-LAST:event_ButtonSearchActionPerformed

    private void ButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonModifyActionPerformed
        ModifyProduct();
    }//GEN-LAST:event_ButtonModifyActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed

        try {
            checkFuntions = 1;
            DienThongTInSanPham dienThongTInSanPham = new DienThongTInSanPham();
            displayFunctions("Điền thông tin sản phẩm", dienThongTInSanPham);
            dienThongTInSanPham.ProductID.setText((String.valueOf(dienThongTInSanPham.generateID() + 1)));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void ButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteActionPerformed
        deleteProduct();
    }//GEN-LAST:event_ButtonDeleteActionPerformed

    private void SearchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchTextKeyReleased
        searchProduct();
    }//GEN-LAST:event_SearchTextKeyReleased
    public static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonDelete;
    private javax.swing.JButton ButtonModify;
    private javax.swing.JButton ButtonSearch;
    private javax.swing.JPanel HOME;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel JFeature;
    private javax.swing.JPanel JSearch;
    private javax.swing.JScrollPane Products;
    private javax.swing.JTextField SearchText;
    public javax.swing.JTable TableProduct;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
