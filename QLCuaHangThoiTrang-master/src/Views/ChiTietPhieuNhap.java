
package Views;

import Models.NhaCungCap;
import Models.PhieuNhap;
import Models.Product;
import Models.SanPhamNhap;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ChiTietPhieuNhap extends javax.swing.JFrame {
    private NhaCungCap nhaCungCap;
    private String maPhieuNhap;
    
    private String[] columnName = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Số lượng", "Giá", "Thành tiền"};

    public ChiTietPhieuNhap(String inputPhieuNhap, NhaCungCap inputNhaCungCap) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.nhaCungCap = inputNhaCungCap;
        this.maPhieuNhap = inputPhieuNhap;
    }
    
    public void display() {
        this.setValue();
        this.setVisible(true);
    }
    
    private void setValue() {
        PhieuNhap phieuNhap = IO.PhieuNhapIO.getInfoById(maPhieuNhap);
        maPhieu.setText(phieuNhap.getMa());
        ngayTao.setText(phieuNhap.getNgayTao());
        capNhat.setText(phieuNhap.getNgayCapNhat());
        tenNhaCungCap.setText(nhaCungCap.getTen());
        diaChiNhaCungCap.setText(nhaCungCap.getDiaChi());
        sdtNhaCungCap.setText(nhaCungCap.getSoDienThoai());
        tongTien.setText(String.format("%,d", phieuNhap.getTien()));
        ArrayList<SanPhamNhap> dsSanPhamNhap = IO.SanPhamNhapIO.getListById(maPhieu.getText());
        DefaultTableModel defaultTableModel = new DefaultTableModel(columnName, 0);   
        for (int i = 0; i < dsSanPhamNhap.size(); i++) {
            Product value = IO.ProductIO.getInfoProductById(dsSanPhamNhap.get(i).getMaSanPham());
            String category = "";
            if (value.getProductID() == null) {
                value.setProductName("Không tồn tại");
                category = "Không tồn tại";
                long price = dsSanPhamNhap.get(i).getThanhTien() / dsSanPhamNhap.get(i).getSoLuong();
                value.setProductPrice(price);
            } else {
                category = IO.MatHangIO.getNameById(value.getProductCategory());
            }
            Object[] rowData = {dsSanPhamNhap.get(i).getMaSanPham(), value.getProductName(), category, dsSanPhamNhap.get(i).getSoLuong(), String.format("%,d", value.getProductPrice()), String.format("%,d", dsSanPhamNhap.get(i).getThanhTien())};
            defaultTableModel.addRow(rowData);
        }
        defaultTableModel.fireTableDataChanged();
        tableViewProduct.setModel(defaultTableModel);
        tableViewProduct.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        maPhieu = new javax.swing.JLabel();
        ngayTao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tenNhaCungCap = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        diaChiNhaCungCap = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sdtNhaCungCap = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewProduct = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        tongTien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        capNhat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chi tiết phiếu nhập");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Mã phiếu:");

        jLabel2.setText("Ngày tạo:");

        maPhieu.setText("abc");

        ngayTao.setText("abc");

        jLabel5.setText("Nhà cung cấp:");

        tenNhaCungCap.setText("abc");

        jLabel7.setText("Địa chỉ:");

        diaChiNhaCungCap.setText("abc");

        jLabel9.setText("Số điện thoại:");

        sdtNhaCungCap.setText("abc");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

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
        tableViewProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableViewProduct.setDragEnabled(true);
        tableViewProduct.setEnabled(false);
        tableViewProduct.setRowSelectionAllowed(false);
        tableViewProduct.setShowGrid(true);
        jScrollPane1.setViewportView(tableViewProduct);

        jLabel11.setText("Tổng tiền:");

        tongTien.setForeground(new java.awt.Color(255, 51, 51));
        tongTien.setText("12344545");

        jLabel3.setText("Cập nhật:");

        capNhat.setText("abc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(diaChiNhaCungCap))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maPhieu))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tenNhaCungCap)))
                                .addGap(214, 214, 214)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(capNhat)
                                    .addComponent(ngayTao)
                                    .addComponent(tongTien))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sdtNhaCungCap)))
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(maPhieu)
                    .addComponent(jLabel2)
                    .addComponent(ngayTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tenNhaCungCap)
                    .addComponent(jLabel3)
                    .addComponent(capNhat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(diaChiNhaCungCap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(sdtNhaCungCap)
                    .addComponent(jLabel11)
                    .addComponent(tongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel capNhat;
    private javax.swing.JLabel diaChiNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maPhieu;
    private javax.swing.JLabel ngayTao;
    private javax.swing.JLabel sdtNhaCungCap;
    private javax.swing.JTable tableViewProduct;
    private javax.swing.JLabel tenNhaCungCap;
    private javax.swing.JLabel tongTien;
    // End of variables declaration//GEN-END:variables
}
