package Views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DangNhap extends javax.swing.JFrame {

    String filePassword = "password.dat";
    File f = new File(filePassword);

    public DangNhap() {
        initComponents();
    }

    private void displayUI() {
        GiaoDien gd = new GiaoDien();
        gd.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gd.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BF = new javax.swing.JPanel();
        BC1 = new javax.swing.JPanel();
        Img1 = new javax.swing.JLabel();
        BC2 = new javax.swing.JPanel();
        Img2 = new javax.swing.JLabel();
        Exit = new javax.swing.JLabel();
        titleDN = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        titleTK = new javax.swing.JLabel();
        titleMK = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        BF.setBackground(new java.awt.Color(255, 255, 255));
        BF.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        BC1.setBackground(new java.awt.Color(255, 255, 153));

        Img1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/brand.png"))); // NOI18N
        Img1.setAutoscrolls(true);

        javax.swing.GroupLayout BC1Layout = new javax.swing.GroupLayout(BC1);
        BC1.setLayout(BC1Layout);
        BC1Layout.setHorizontalGroup(
            BC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BC1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Img1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );
        BC1Layout.setVerticalGroup(
            BC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BC1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Img1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        BC2.setBackground(new java.awt.Color(204, 255, 255));

        Img2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/login.png"))); // NOI18N

        Exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit.setText("X");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });

        titleDN.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        titleDN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleDN.setText("ĐĂNG NHẬP HỆ THỐNG");

        btnDangNhap.setBackground(new java.awt.Color(102, 255, 255));
        btnDangNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        titleTK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        titleTK.setText("TÊN TÀI KHOẢN");

        titleMK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        titleMK.setText("MẬT KHẨU");

        txtTaiKhoan.setBackground(new java.awt.Color(204, 255, 255));
        txtTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTaiKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtMatKhau.setBackground(new java.awt.Color(204, 255, 255));
        txtMatKhau.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout BC2Layout = new javax.swing.GroupLayout(BC2);
        BC2.setLayout(BC2Layout);
        BC2Layout.setHorizontalGroup(
            BC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BC2Layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addComponent(Img2)
                .addGap(97, 97, 97)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BC2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(BC2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(BC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleMK, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleTK, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BC2Layout.setVerticalGroup(
            BC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BC2Layout.createSequentialGroup()
                .addGroup(BC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Exit)
                    .addGroup(BC2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Img2)))
                .addGap(18, 18, 18)
                .addComponent(titleDN)
                .addGap(28, 28, 28)
                .addComponent(titleTK, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(titleMK, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BFLayout = new javax.swing.GroupLayout(BF);
        BF.setLayout(BFLayout);
        BFLayout.setHorizontalGroup(
            BFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BFLayout.createSequentialGroup()
                .addComponent(BC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BFLayout.setVerticalGroup(
            BFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BC1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        String taiKhoan = txtTaiKhoan.getText();
        String matKhau = new String(txtMatKhau.getPassword());

        StringBuilder sb = new StringBuilder();

        if (taiKhoan.trim().equals("")) {
            sb.append("Tên tài khoản đang bị trống\n");
        }
        if (matKhau.equals("")) {
            sb.append("Mật khẩu đang bị trống\n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this,
                    sb.toString(),
                    "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            byte[] savedHash;
            try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
                savedHash = (byte[]) ois.readObject();
            }

            // Mã hóa mật khẩu nhập liệu
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] enteredHash = digest.digest(matKhau.getBytes());

            if (evt.getSource() == btnDangNhap) {
                while (savedHash != null) {
                    if (taiKhoan.equals("admin") && MessageDigest.isEqual(savedHash, enteredHash)) {
                        JOptionPane.showMessageDialog(this,
                                "Đăng nhập thành công",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        displayUI();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Tài khoản hoặc mật khẩu không đúng, vui lòng nhập lại",
                                "Thông báo",
                                JOptionPane.OK_OPTION);
                        txtTaiKhoan.setText("");
                        txtMatKhau.setText("");
                    }
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BC1;
    private javax.swing.JPanel BC2;
    private javax.swing.JPanel BF;
    private javax.swing.JLabel Exit;
    private javax.swing.JLabel Img1;
    private javax.swing.JLabel Img2;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel titleDN;
    private javax.swing.JLabel titleMK;
    private javax.swing.JLabel titleTK;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
