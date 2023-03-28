package com.bar.view;

import com.bar.dao.BanDAO;
import com.bar.dao.NhanVienDAO;
import com.bar.model.Ban;
import com.bar.model.NhanVien;
import com.bar.util.MsgBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FormThemBan extends javax.swing.JFrame {

    BanDAO banDao = new BanDAO();
    NhanVienDAO nv = new NhanVienDAO();
    List<Ban> listBan = new ArrayList<Ban>();
    List<NhanVien> listNV = new ArrayList<NhanVien>();

    public FormThemBan() {
        this.setUndecorated(true);
        initComponents();

        lbl_ThongBao.setVisible(false);
        this.setLocation(650, 520);

        //  this.setLocationRelativeTo(null);
        listNV = nv.select();
        listBan = banDao.select();
        this.addItemCb();
    }

    public void addItemCb() {
        List<NhanVien> listCb = listNV;
        for (int i = 0; i < listCb.size(); i++) {
            cb_MaNhanVien.addItem(listCb.get(i).getMaNV());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_MaBanMoi = new com.bar.customUI.TextField();
        lbl_ThemBan = new javax.swing.JLabel();
        lbl_ThongBao = new javax.swing.JLabel();
        cb_MaNhanVien = new com.bar.customUI.ComboBoxSuggestion();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 270));
        setPreferredSize(new java.awt.Dimension(400, 270));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(60, 185, 230)));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 270));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Chọn mã nhân viên :");

        lbl_MaBanMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_MaBanMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_MaBanMoiActionPerformed(evt);
            }
        });
        lbl_MaBanMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbl_MaBanMoiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_MaBanMoiKeyReleased(evt);
            }
        });

        lbl_ThemBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThemBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/icon_TangSoLuong30px.png"))); // NOI18N
        lbl_ThemBan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbl_ThemBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ThemBanMouseClicked(evt);
            }
        });

        lbl_ThongBao.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_ThongBao.setForeground(new java.awt.Color(255, 51, 51));

        cb_MaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_MaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_MaNhanVienActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nhập mã bàn mới :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_ThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_MaBanMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_ThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cb_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_MaBanMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_ThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     public boolean checkMaBan() {
        boolean check = false;
        if (lbl_MaBanMoi.getText().equals("")) {
            lbl_ThongBao.setText("Vui lòng không bỏ trống !");
            lbl_ThongBao.setVisible(true);
            check = true;
        } else {
            lbl_ThongBao.setVisible(false);
        }
        int index = -1;
        for (int i = 0; i < listBan.size(); i++) {
            if (listBan.get(i).getMaBan().equalsIgnoreCase(lbl_MaBanMoi.getText())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            lbl_ThongBao.setText("Mã bàn bạn nhập đã tồn tại !");
            lbl_ThongBao.setVisible(true);
            check = true;
        } else {
            lbl_ThongBao.setVisible(false);
        }
        return check;

    }

    public String insertTable(Ban ban) {
        boolean check = checkMaBan();
        if (check == true) {
            return "Them ban khong thanh cong";
        }
            banDao.insert(ban);
            listBan.clear();
            listBan = banDao.select();
          //  MsgBox.alert(this, "Thêm bàn thành công!");
System.out.println("Them thanh cong");
            return "Thêm bàn thành công!";
        }

    

    private void lbl_MaBanMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_MaBanMoiActionPerformed

    }//GEN-LAST:event_lbl_MaBanMoiActionPerformed

    private void lbl_ThemBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThemBanMouseClicked

    }//GEN-LAST:event_lbl_ThemBanMouseClicked

    private void lbl_MaBanMoiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_MaBanMoiKeyPressed

    }//GEN-LAST:event_lbl_MaBanMoiKeyPressed

    private void lbl_MaBanMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_MaBanMoiKeyReleased

    }//GEN-LAST:event_lbl_MaBanMoiKeyReleased

    private void cb_MaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_MaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_MaNhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormThemBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormThemBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormThemBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormThemBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormThemBan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bar.customUI.ComboBoxSuggestion cb_MaNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.bar.customUI.TextField lbl_MaBanMoi;
    private javax.swing.JLabel lbl_ThemBan;
    private javax.swing.JLabel lbl_ThongBao;
    // End of variables declaration//GEN-END:variables
}
