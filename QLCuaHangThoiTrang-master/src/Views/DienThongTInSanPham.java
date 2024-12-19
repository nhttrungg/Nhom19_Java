package Views;

import IO.ListProduct;
import IO.ReadWriteProduct;
import  Models.Product;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import static Views.QLSP.checkFuntions;
import static Views.QLSP.index;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DienThongTInSanPham extends javax.swing.JPanel {

    static ArrayList<Product> danhsachsanpham = new ArrayList<>();
    static Product product;
    private QLSP qlsp;
    private ReadWriteProduct rwp = new ReadWriteProduct();
    private ListProduct listProduct = new ListProduct();
    private String fileName = "QuanLySanPham.txt";

    public DienThongTInSanPham() {
        initComponents();
        Init();
        getDataCategory();
    }

    public DienThongTInSanPham(int index, String id, String name, String category, int quantity, long price){
        initComponents();
        Init();
        getDataCategory();
        ProductID.setText(id);
        ProductName.setText(name);
        ProductCategory.setSelectedItem(category);
        ProductQuantity.setText(String.valueOf(quantity));
        ProductPrice.setText(String.valueOf(price));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTitle = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        ProductID = new javax.swing.JTextField();
        ProductName = new javax.swing.JTextField();
        ProductPrice = new javax.swing.JTextField();
        ButtonDelete = new javax.swing.JButton();
        ButtonConfirm = new javax.swing.JButton();
        ProductCategory = new javax.swing.JComboBox<>();
        Quantity = new javax.swing.JLabel();
        ProductQuantity = new javax.swing.JTextField();

        JTitle.setBackground(new java.awt.Color(51, 153, 255));

        Title.setBackground(new java.awt.Color(255, 255, 255));
        Title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Thêm thông tin sản phẩm");

        javax.swing.GroupLayout JTitleLayout = new javax.swing.GroupLayout(JTitle);
        JTitle.setLayout(JTitleLayout);
        JTitleLayout.setHorizontalGroup(
            JTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JTitleLayout.setVerticalGroup(
            JTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        ID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ID.setText("Mã sản phẩm");

        Name.setBackground(new java.awt.Color(153, 204, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name.setText("Tên sản phẩm");

        Category.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Category.setText("Loại");

        Price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Price.setText("Giá bán");

        ProductID.setEditable(false);
        ProductID.setBackground(new java.awt.Color(255, 255, 255));
        ProductID.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        ProductID.setFocusable(false);

        ProductName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ProductName.setPreferredSize(new java.awt.Dimension(64, 30));

        ProductPrice.setMinimumSize(new java.awt.Dimension(64, 30));

        ButtonDelete.setBackground(new java.awt.Color(255, 51, 51));
        ButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        ButtonDelete.setText("Đặt lại");
        ButtonDelete.setPreferredSize(new java.awt.Dimension(90, 27));
        ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteActionPerformed(evt);
            }
        });

        ButtonConfirm.setBackground(new java.awt.Color(102, 153, 255));
        ButtonConfirm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ButtonConfirm.setForeground(new java.awt.Color(255, 255, 255));
        ButtonConfirm.setText("Xác nhận");
        ButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConfirmActionPerformed(evt);
            }
        });

        ProductCategory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Quantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Quantity.setText("Số lượng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Name)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ID)
                            .addComponent(Quantity))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ProductPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ProductID)
                            .addComponent(ProductCategory, 0, 264, Short.MAX_VALUE)
                            .addComponent(ProductQuantity)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(ButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(ButtonConfirm)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID)
                    .addComponent(ProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Quantity)
                    .addComponent(ProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    public void Init() {
        if (checkFuntions == 1) {
            Title.setText("Thêm thông tin sản phẩm");
        } else if (checkFuntions == 2) {
            Title.setText("Sửa thông tin sản phẩm");
            ButtonConfirm.setText("Cập nhật");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    
    public int generateID(){
        String valueString = danhsachsanpham.getLast().getProductID();
        int value = Integer.parseInt(valueString);
        return value;
    }
    
    public void deleteFile() {
        ProductID.setText(String.valueOf(generateID() + 1));
        ProductName.setText("");
        ProductCategory.setSelectedIndex(0);
        ProductQuantity.setText("");
        ProductPrice.setText("");
        ProductName.requestFocus();
    }
    
    private void getDataCategory() {
        String fileData = "MatHang.txt";
        try {
            FileReader fw = new FileReader(fileData);
            BufferedReader br = new BufferedReader(fw);
            String line;
            while ((line = br.readLine()) != null){
                String txt [] = line.split(";");
                ProductCategory.addItem(txt[1]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public boolean checkValue(String id, String name, String category, int quantity, long price) {
        boolean check = true;
        try {
            if (id.trim().length() == 0) {
                showMessage("Mã sản phẩm không được để trống");
                check = false;
                ProductID.requestFocus();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (name.trim().length() == 0) {
                showMessage("Tên không được để trống");
                check = false;
                ProductName.requestFocus();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {

            if (quantity < 0) {
                check = false;
                showMessage("Số lượng phải lớn hơn 0");
                ProductPrice.requestFocus();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            if (price <= 0) {
                check = false;
                showMessage("Giá phải lớn hơn 0");
                ProductPrice.requestFocus();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return check;
    }

    public void AddProduct(ArrayList<Product> danhsachsanpham) {
        String id = ProductID.getText();
        String name = ProductName.getText();
        String category = ProductCategory.getSelectedItem().toString();
        int quantity = 0;
        long price = 0;
        boolean check = true;
        if (listProduct.checkIDSame(id, danhsachsanpham)) {
            showMessage("Mã sản phẩm đã tồn tại");
            check = false;
            }
        try {
            String priceString = ProductPrice.getText();
            price = Long.parseLong(ProductPrice.getText());

        } catch (NumberFormatException e) {
            showMessage("Giá tiền không đúng");
        }
        try {
            quantity = Integer.parseInt(ProductQuantity.getText());

        } catch (NumberFormatException e) {
            showMessage("Số lượng không đúng");
        }
        if (checkValue(id, name, category, quantity, price) && check) {
            qlsp = new QLSP();
            product = new Product(id, name, category, quantity, price);
            int select = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm sản phẩm: \n" + "ID: " + id + "\nTên sản phẩm: " + name
                    + "\nLoại: " + category + "\nSố lượng: " + quantity + "\nGiá: " + price, "Thông báo", JOptionPane.OK_CANCEL_OPTION);
            if (select == 0) {
                rwp.writeFile(product, fileName, danhsachsanpham);
                JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                qlsp.addProduct(product);
                deleteFile();
            }
        }

    }

    private void ModifyProduct() {
        String id = ProductID.getText();
        String name = ProductName.getText();
        String category = ProductCategory.getSelectedItem().toString();
        int quantity = 0;
        long price = 0;
        try {
            price = Long.parseLong(ProductPrice.getText());

        } catch (NumberFormatException e) {
            showMessage("Giá tiền không đúng");
        }
        try {
            quantity = Integer.parseInt(ProductQuantity.getText());

        } catch (NumberFormatException e) {
            showMessage("Số lượng không đúng"); 
        }
        if (checkValue(id, name, category, quantity, price)) {
            qlsp = new QLSP();
            Product products = new Product(id, name, category, quantity, price);
            for (int i = 0; i < danhsachsanpham.size(); i++) {
                if(danhsachsanpham.get(i).getProductID().equalsIgnoreCase(products.getProductID())){
                    danhsachsanpham.get(i).setProductID(id);
                    danhsachsanpham.get(i).setProductName(name);
                    danhsachsanpham.get(i).setProductCategory(category);
                    danhsachsanpham.get(i).setProductQuantity(quantity);
                    danhsachsanpham.get(i).setProductPrice(price);
                }
            }
            PrintWriter writer;
            try {
                writer = new PrintWriter(fileName);
                writer.print("");
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(QLSP.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Product product : danhsachsanpham) {

                rwp.writeFile(product, fileName, danhsachsanpham);
            }
            qlsp.updateTable();
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void ButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteActionPerformed
        deleteFile();
    }//GEN-LAST:event_ButtonDeleteActionPerformed

   
    private void ButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConfirmActionPerformed
        if (checkFuntions == 1) {
            AddProduct(danhsachsanpham);
        } else if (checkFuntions == 2) {
            ModifyProduct();
        }
    }//GEN-LAST:event_ButtonConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonConfirm;
    private javax.swing.JButton ButtonDelete;
    private javax.swing.JLabel Category;
    private javax.swing.JLabel ID;
    private javax.swing.JPanel JTitle;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JComboBox<String> ProductCategory;
    public javax.swing.JTextField ProductID;
    private javax.swing.JTextField ProductName;
    private javax.swing.JTextField ProductPrice;
    private javax.swing.JTextField ProductQuantity;
    private javax.swing.JLabel Quantity;
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables
}
