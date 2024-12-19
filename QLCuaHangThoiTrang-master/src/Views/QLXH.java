package Views;

import IO.ListProduct;
import IO.PhieuXuatIO;
import IO.ProductIO;
import IO.ReadWriteProduct;
import IO.SanPhamXuatIO;
import Models.MatHang;
import Models.PhieuXuat;
import Models.Product;
import Models.SanPhamXuat;
import java.awt.Color;
import java.io.BufferedReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static Views.PhieuXuatView.checkFeature;
import java.io.FileReader;
import java.util.Random;

public class QLXH extends javax.swing.JPanel {
    
    ArrayList<Product> listSP = new ArrayList<>();
    static ArrayList<PhieuXuat> danhsachphieuxuat = new ArrayList<>();
    private IO.PhieuXuatIO phieuxuatIO = new PhieuXuatIO();
    private IO.ListProduct listProduct = new ListProduct();
    private IO.SanPhamXuatIO sanPhamXuatIO = new SanPhamXuatIO();
    private IO.ProductIO productIO = new ProductIO();
    private PhieuXuatView phieuXuatView;
    private SanPhamXuat sanPhamXuat;
    private QLSP qlsp = new QLSP();
    String fileQLSP = "QuanLySanPham.txt";
    String fileMatHang = "MatHang.txt";
    String fileQLPX = "QuanLyPhieuXuat.txt";
    private Models.PhieuXuat phieuxuatsanpham;
    DefaultTableModel model;
    DefaultTableModel model2;
    ReadWriteProduct rwp = new ReadWriteProduct();
    NumberFormat f = NumberFormat.getInstance(Locale.US);
    
    public QLXH() {
        initComponents();
        getData();
    }
    
    public QLXH(String maPhieu, String tenKH, String SDT, String diaChi, long tongTien) {
        initComponents();
        getData();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        txtMaphieuxuat.setText(maPhieu);
        txtTenKH.setText(tenKH);
        txtSDT.setText(SDT);
        txtDiaChi.setText(diaChi);
        txtTotalPrice.setText(String.valueOf(tongTien));
        model2 = (DefaultTableModel) tableXuat.getModel();
        ArrayList<SanPhamXuat> listsanphamxuat = sanPhamXuatIO.getListByID(maPhieu);
        for (SanPhamXuat spx : listsanphamxuat) {
            Product product = sanPhamXuatIO.getInfoProductById(spx.getMaSanPham(), spx.getSoLuong(), spx.getThanhTien());
            String category = IO.MatHangIO.getNameById(product.getProductCategory());
            String formattedPrice = format.format(product.getProductPrice());
            String price = formattedPrice;
            Object[] rowdatas = {product.getProductID(), product.getProductName(), category, product.getProductQuantity(), price};
            model2.addRow(rowdatas);
        }
        tableXuat.setModel(model2);
    }
    
    public boolean checkID(String id) {
        ArrayList<PhieuXuat> listPhieuXuat = new ArrayList<>();
        listPhieuXuat = phieuxuatIO.readFilePX(fileQLPX);
        for (int i = 0; i < listPhieuXuat.size(); i++) {
            if (listPhieuXuat.get(i).getMaPhieu().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
    
    private void getData() {
        try {
            rwp.readFile(fileQLSP, listSP);
            String valueIDPhieuXuat = generateMaPhieu();
            if (checkID(valueIDPhieuXuat)) {
                txtMaphieuxuat.setText(generateMaPhieu());
            } else {
                txtMaphieuxuat.setText(generateMaPhieu());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Danh sách rỗng");
        }
        model = (DefaultTableModel) tableThongTin.getModel();
        
        for (Product i : listSP) {
            String listMatHang = i.getProductCategory();
            String tenMatHang = loaiSP(listMatHang);
            String giaBan = f.format(i.getProductPrice());
            Object rows[] = {i.getProductID(), i.getProductName(), tenMatHang, i.getProductQuantity(), giaBan};
            model.addRow(rows);
        }
    }
    
    private ArrayList<MatHang> readFromFile(String url) {
        ArrayList<MatHang> list = new ArrayList<MatHang>();
        try {
            FileReader fr = new FileReader(fileMatHang);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                list.add(new MatHang(txt[0], txt[1]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            list = new ArrayList<MatHang>();
        }
        return list;
    }
    
    private String loaiSP(String category) {
        ArrayList<Models.MatHang> listMatHang = new ArrayList<>();
        listMatHang = readFromFile(fileMatHang);
        String categoryItem = "";
        for (MatHang matHang : listMatHang) {
            if (category.equalsIgnoreCase(matHang.getMa())) {
                categoryItem = matHang.getTen();
            }
        }
        return categoryItem;
    }
    
    private void totalPrice() {
        model = (DefaultTableModel) tableXuat.getModel();
        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                Number num = f.parse((String) model.getValueAt(i, 4));
                total = total + (Integer.parseInt(num.toString()) * Integer.parseInt(txtSoLuong.getText()));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
        txtTotalPrice.setText(f.format(total));
    }
    
    private void totalPriceNew() {
        int total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                Number num = f.parse((String) model.getValueAt(i, 4));
                total = total + (Integer.parseInt(model.getValueAt(i, 3).toString()) * Integer.parseInt(num.toString()));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
        txtTotalPrice.setText(f.format(total));
    }
    
    private Product getValueTable(int index, JTable table) {
        Product value = new Product();
        try {
            Number num = f.parse((String) table.getValueAt(index, 4));
            value.setProductID(table.getValueAt(index, 0).toString());
            value.setProductName(table.getValueAt(index, 1).toString());
            value.setProductCategory(table.getValueAt(index, 2).toString());
            value.setProductQuantity(Integer.parseInt(table.getValueAt(index, 3).toString()));
            value.setProductPrice(Integer.parseInt(num.toString()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        return value;
    }
    
    public static String generateMaPhieu() {
        Random random = new Random();
        char letter = (char) (random.nextInt(26) + 'A');
        int number = random.nextInt(9000) + 1000;
        return letter + String.valueOf(number);
    }
    
    private void showMessageWarning(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    private boolean checkValue(String tenKH, String sdt, String diaChi, TableModel TableXuat) {
        boolean check = true;
        if (tenKH.trim().length() == 0) {
            showMessageWarning("Tên khách hàng không được bỏ trống", "Thông báo");
            check = false;
        } else if (sdt.trim().length() == 0) {
            showMessageWarning("Số điện thoại không được bỏ trống", "Thông báo");
            check = false;
        } else if (sdt.trim().length() > 10) {
            showMessageWarning("Độ dài số điện thoại không đúng", "Thông báo");
            check = false;
        }
        if (diaChi.trim().length() == 0) {
            showMessageWarning("Địa chỉ không được bỏ trống", "Thông báo");
            check = false;
        }
        
        if (TableXuat.getRowCount() == 0) {
            showMessageWarning("Chưa chọn sản phẩm xuất", "Thông báo");
            check = false;
        }
        return check;
    }
    
    private void getDataFromTable() {
        String maPhieu = txtMaphieuxuat.getText();
        String tenKH = txtTenKH.getText();
        String sdtKH = txtSDT.getText();
        long gia = 0;
        long tongTien = 0;
        String diaChi = txtDiaChi.getText();
        try {
            tongTien = (long) (f.parse((String) txtTotalPrice.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(QLXH.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Product> listSanPham = new ArrayList<>();
        ArrayList<SanPhamXuat> listSPXuat = new ArrayList<>();
        for (int i = 0; i < tableXuat.getRowCount(); i++) {
            String maSP = (String) tableXuat.getValueAt(i, 0);
            String tenSP = (String) tableXuat.getValueAt(i, 1);
            String loaiSP = (String) tableXuat.getValueAt(i, 2);          
            Object valueAt3 = tableXuat.getValueAt(i, 3);
            int soLuong;
            if (valueAt3 instanceof String) {
                soLuong = Integer.parseInt((String) valueAt3);
            } else if (valueAt3 instanceof Integer) {
                soLuong = (Integer) valueAt3;
            } else {
                throw new ClassCastException("Lỗi " + valueAt3.getClass().getName());
            }
            try {
                gia = (long) f.parse((String) tableXuat.getValueAt(i, 4));
            } catch (ParseException ex) {
                Logger.getLogger(QLXH.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Product product = new Product(maSP, tenSP, loaiSP, soLuong, gia);
            listSanPham.add(product);
            sanPhamXuat = new SanPhamXuat(maSP, soLuong, gia, maPhieu);
            listSPXuat.add(sanPhamXuat);
        }
        model2 = (DefaultTableModel) tableXuat.getModel();
        if (checkValue(tenKH, sdtKH, diaChi, model2)) {
            phieuxuatsanpham = new PhieuXuat(maPhieu, tenKH, sdtKH, diaChi, tongTien);
            phieuXuatView = new PhieuXuatView();
            phieuXuatView.addPhieu(phieuxuatsanpham);
            phieuxuatIO.writePhieuXuat(phieuxuatsanpham, danhsachphieuxuat);
            sanPhamXuatIO.writeFIleSPX(listSPXuat);
            JOptionPane.showMessageDialog(null, "Tạo phiếu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            deleteFile();
        }
    }
    
    private void modifyPhieuXuat() {
        ArrayList<PhieuXuat> listPX = new ArrayList<>();
        ArrayList<SanPhamXuat> spx = new ArrayList<>();
        String maPhieu = txtMaphieuxuat.getText();
        String tenKH = txtTenKH.getText();
        String sdtKH = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        long gia = 0;
        long tongTien = 0;
        try {
            tongTien = (long) (f.parse((String) txtTotalPrice.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(QLXH.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < tableXuat.getRowCount(); i++) {
            String maSP = (String) tableXuat.getValueAt(i, 0);
            Object valueAt3 = tableXuat.getValueAt(i, 3);
            int soLuong;
            if (valueAt3 instanceof String) {
                soLuong = Integer.parseInt((String) valueAt3);
            } else if (valueAt3 instanceof Integer) {
                soLuong = (Integer) valueAt3;
            } else {
                throw new ClassCastException("Lỗi " + valueAt3.getClass().getName());
            }
            
            try {
                gia = (long) f.parse((String) tableXuat.getValueAt(i, 4));
            } catch (ParseException ex) {
                Logger.getLogger(QLXH.class.getName()).log(Level.SEVERE, null, ex);
            }
            sanPhamXuat = new SanPhamXuat(maSP, soLuong, gia, maPhieu);
            spx.add(sanPhamXuat);
        }
        model2 = (DefaultTableModel) tableXuat.getModel();
        if (checkValue(tenKH, sdtKH, diaChi, model2)) {
            phieuxuatsanpham = new PhieuXuat(maPhieu, tenKH, sdtKH, diaChi, tongTien);
            listPX.add(phieuxuatsanpham);
            phieuxuatIO.updateInfoById(phieuxuatsanpham);
            sanPhamXuatIO.updateDataByIdMaPhieu(maPhieu, spx);
            phieuXuatView = new PhieuXuatView();
            phieuXuatView.updateTable();
            deleteFile();
            model2.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.WARNING_MESSAGE);
            checkFeature = 0;
        }
    }
    
    private void addProduct() {
        TableModel model1 = tableThongTin.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tableThongTin.getModel();
        int selectedRowIndex = -1;
        selectedRowIndex = tableThongTin.getSelectedRow();
        if (selectedRowIndex == -1) {
            showMessageWarning("Chưa chọn sản phẩm", "Thông báo");
        } else {
            
            int[] indexs = tableThongTin.getSelectedRows();
            Object[] row = new Object[6];
            String soLuong = txtSoLuong.getText();
            model = (DefaultTableModel) tableXuat.getModel();
            for (int i = 0; i < indexs.length; i++) {
                row[0] = model1.getValueAt(indexs[i], 0);
                row[1] = model1.getValueAt(indexs[i], 1);
                row[2] = model1.getValueAt(indexs[i], 2);
                if (Integer.parseInt(soLuong) > 50 || Integer.parseInt(soLuong) > Integer.parseInt(tableThongTin.getValueAt(selectedRowIndex, 3).toString())) {
                    showMessageWarning("Số lượng vượt mức cho phép", "Thông báo");
                    break;
                } else {
                    row[3] = soLuong;
                }
                row[4] = model1.getValueAt(indexs[i], 4);
                model.addRow(row);
                model2.removeRow(selectedRowIndex);
            }
            totalPrice();
            totalPriceNew();
            txtSoLuong.setText("1");
        }
    }
    
    private void deleteProduct() {
        model = (DefaultTableModel) tableXuat.getModel();
        model2 = (DefaultTableModel) tableThongTin.getModel();
        int selectedRowIndex = tableXuat.getSelectedRow();
        try {
            Product value = this.getValueTable(selectedRowIndex, this.tableXuat);
            int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Sản phẩm chưa xóa", " Thông báo", JOptionPane.OK_OPTION);
            }
            
            if (check == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRowIndex);
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
                totalPriceNew();
                
                for (int i = 0; i < this.listSP.size(); i++) {
                    if (value.getProductID().equalsIgnoreCase(this.listSP.get(i).getProductID())) {
                        int quantity = this.listSP.get(i).getProductQuantity();
                        value.setProductQuantity(quantity);
                        break;
                    }
                }
                Object[] data = {value.getProductID(), value.getProductName(), value.getProductCategory(), value.getProductQuantity(), f.format(value.getProductPrice())};
                model2.addRow(data);
                tableThongTin.setModel(model2);
                tableThongTin.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần xóa", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Modify() {
        try {
            model = (DefaultTableModel) tableXuat.getModel();
            
            int selectedRowIndex = tableXuat.getSelectedRow();
            
            String soLuong = model.getValueAt(selectedRowIndex, 3).toString();
            
            String newSoLuong = JOptionPane.showInputDialog(null, "Nhập lại số lượng", soLuong);
            
            StringBuilder sb = new StringBuilder();
            if (newSoLuong.trim().equals("")) {
                sb.append("Số lượng không được để trống\n");
            }
            if (newSoLuong.equals("0")) {
                sb.append("Số lượng phải lớn hơn 0\n");
            }
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this,
                        sb.toString(),
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (Integer.parseInt(newSoLuong) > 50) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt mức cho phép", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                model.setValueAt(newSoLuong, selectedRowIndex, 3);
                JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            showMessageWarning("Chưa chọn sản phẩm sửa", "Thông báo");
        }
        totalPriceNew();
    }
    
    private void deleteFile() {
        String IDPhieuXuat = generateMaPhieu();
        if (checkID(IDPhieuXuat)) {
            txtMaphieuxuat.setText(IDPhieuXuat);
        } else {
            txtMaphieuxuat.setText(generateMaPhieu());
            
        }
        txtTenKH.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtTotalPrice.setText("");
        model.setRowCount(0);
    }
    
    private void searchProductTableThongTin() {
        model = (DefaultTableModel) tableThongTin.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableThongTin.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiem.getText(), 1));
    }
    
    public void updateTableModifyPX() {
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng", "Giá bán"};
        ArrayList<Product> listProductTableThongTin = new ArrayList<>();
        listProductTableThongTin = ProductIO.readFromFile();
        ArrayList<Product> listProductTableXuat = new ArrayList<>();
        model2 = (DefaultTableModel) tableXuat.getModel();
        int size = model2.getRowCount();
        for (int i = 0; i < size; i++) {
            String Ma = tableXuat.getValueAt(i, 0).toString();
            String Ten = tableXuat.getValueAt(i, 1).toString();
            String Loai = tableXuat.getValueAt(i, 2).toString();
            String soLuong = tableXuat.getValueAt(i, 3).toString();
            String giaBan = tableXuat.getValueAt(i, 4).toString();
            long priceProduct = Long.parseLong(giaBan.replace(",", ""));
            Product pd = new Product(Ma, Ten, Loai, Integer.parseInt(soLuong), priceProduct);
            listProductTableXuat.add(pd);
            for (int j = 0; j < listProductTableThongTin.size(); j++) {
                if (listProductTableThongTin.get(j).getProductID().equalsIgnoreCase(pd.getProductID())) {
                    listProductTableThongTin.remove(j);
                }
                
            }
        }
        
        DefaultTableModel dtmTableThongTin = new DefaultTableModel(columnNames, 0);
        for (Product i : listProductTableThongTin) {
            String listMatHang = i.getProductCategory();
            String tenMatHang = loaiSP(listMatHang);
            String giaBan = f.format(i.getProductPrice());
            Object rows[] = {i.getProductID(), i.getProductName(), tenMatHang, i.getProductQuantity(), giaBan};
            dtmTableThongTin.addRow(rows);
        }
        tableThongTin.setModel(dtmTableThongTin);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongTin = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableXuat = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        btnXuat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtMaphieuxuat = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtTenKH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtSDT = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtDiaChi = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 153, 153));
        setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("XUẤT HÀNG");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Thông Tin Sản Phẩm");

        tableThongTin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng", "Giá bán"
            }
        ));
        tableThongTin.setRowHeight(25);
        tableThongTin.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableThongTin.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableThongTin);

        btnThem.setBackground(new java.awt.Color(0, 153, 51));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/add (1).png"))); // NOI18N
        btnThem.setLabel("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Xuất Hàng");

        tableXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lương", "Giá bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableXuat.setRowHeight(25);
        tableXuat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableXuat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tableXuat);

        btnXoa.setBackground(new java.awt.Color(255, 0, 51));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/rubbish-bin (1).png"))); // NOI18N
        btnXoa.setText("Xóa sản phẩm");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 0));
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/edit-v2 (2).png"))); // NOI18N
        btnSua.setText("Sửa số lượng");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tổng tiền:");

        txtTotalPrice.setBackground(new java.awt.Color(242, 242, 242));
        txtTotalPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTotalPrice.setForeground(new java.awt.Color(204, 0, 0));
        txtTotalPrice.setText("0");
        txtTotalPrice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        btnXuat.setBackground(new java.awt.Color(0, 153, 51));
        btnXuat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("Xuất hàng");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng");

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setText("1");

        jButton1.setBackground(new java.awt.Color(15, 149, 224));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/reload-arrow.png"))); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã phiếu xuất\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtMaphieuxuat.setEditable(false);
        txtMaphieuxuat.setBackground(new java.awt.Color(255, 255, 255));
        txtMaphieuxuat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMaphieuxuat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaphieuxuat.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaphieuxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMaphieuxuat, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtTenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTenKH.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số điện thoại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Địa chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDiaChi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtTimKiem.setBackground(new java.awt.Color(153, 153, 153));
        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setText("Nhập tên sản phẩm cần tìm kiếm");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addProduct();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteProduct();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Modify();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        if (checkFeature == 1) {
            modifyPhieuXuat();
        } else {
            getDataFromTable();
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        if (txtTimKiem.getText().equals("Nhập tên sản phẩm cần tìm kiếm")) {
            txtTimKiem.setText("");
            txtTimKiem.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_txtTimKiemFocusGained

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        if (txtTimKiem.getText().equals("")) {
            txtTimKiem.setText("Nhập tên sản phẩm cần tìm kiếm");
            txtTimKiem.setForeground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_txtTimKiemFocusLost

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        searchProductTableThongTin();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        model = (DefaultTableModel) tableThongTin.getModel();
        model.setRowCount(0);
        getData();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    public javax.swing.JButton btnXuat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable tableThongTin;
    public javax.swing.JTable tableXuat;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaphieuxuat;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTotalPrice;
    // End of variables declaration//GEN-END:variables
}
