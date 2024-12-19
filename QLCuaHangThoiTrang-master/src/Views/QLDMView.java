/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import Models.DanhMuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class QLDMView extends javax.swing.JPanel {

    private ArrayList<DanhMuc> list = new ArrayList<>();
    public QLDMView() {
        initComponents();
        setView("NhaCungCap");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabNCC = new javax.swing.JPanel();
        titleNCC = new javax.swing.JLabel();
        tabMH = new javax.swing.JPanel();
        titleMH = new javax.swing.JLabel();
        viewContent = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(770, 444));

        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 30, 0));

        tabNCC.setBackground(new java.awt.Color(135, 143, 253));
        tabNCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabNCCMouseClicked(evt);
            }
        });
        tabNCC.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 14));

        titleNCC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        titleNCC.setForeground(new java.awt.Color(255, 255, 255));
        titleNCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/parcel.png"))); // NOI18N
        titleNCC.setText("Nhà cung cấp");
        titleNCC.setIconTextGap(10);
        titleNCC.setRequestFocusEnabled(false);
        tabNCC.add(titleNCC);

        jPanel1.add(tabNCC);

        tabMH.setBackground(new java.awt.Color(232, 208, 125));
        tabMH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMHMouseClicked(evt);
            }
        });
        tabMH.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 14));

        titleMH.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        titleMH.setForeground(new java.awt.Color(255, 255, 255));
        titleMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon/cubes (1).png"))); // NOI18N
        titleMH.setText("Mặt hàng");
        titleMH.setIconTextGap(10);
        tabMH.add(titleMH);

        jPanel1.add(tabMH);

        viewContent.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout viewContentLayout = new javax.swing.GroupLayout(viewContent);
        viewContent.setLayout(viewContentLayout);
        viewContentLayout.setHorizontalGroup(
            viewContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        viewContentLayout.setVerticalGroup(
            viewContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 394, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setView(String name) {
        viewContent.removeAll();
        viewContent.setLayout(new BorderLayout());
        if (name.equalsIgnoreCase("NhaCungCap")) {
            NhaCungCapView nccView = new NhaCungCapView();
            viewContent.add(nccView);
            nccView.showListData();
        }
        if (name.equalsIgnoreCase("MatHang")) {
            MatHangView mHView = new MatHangView();
            viewContent.add(mHView);
            mHView.showListData();
        }
        
        viewContent.validate();
        viewContent.repaint();
    }
    
    private void tabNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabNCCMouseClicked
        tabNCC.setBackground(new Color(135, 143, 253));
        tabMH.setBackground(new Color(232, 208, 125));
        setView("NhaCungCap");
    }//GEN-LAST:event_tabNCCMouseClicked

    private void tabMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMHMouseClicked
        tabMH.setBackground(new Color(240, 198, 56));
        tabNCC.setBackground(new Color(169, 175, 251));
        setView("MatHang");
    }//GEN-LAST:event_tabMHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel tabMH;
    private javax.swing.JPanel tabNCC;
    private javax.swing.JLabel titleMH;
    private javax.swing.JLabel titleNCC;
    private javax.swing.JPanel viewContent;
    // End of variables declaration//GEN-END:variables
}
