/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.util.Auth;
import com.bar.util.MsgBox;
import java.awt.Component;
import javax.swing.SwingUtilities;

/**
 *
 * @author PHUOCTAI
 */
public class Setting extends javax.swing.JPanel {

    /**
     * Creates new form Setting
     */
    public Setting() {
        initComponents();
        checkRole();
    }

    void checkRole() {
        if (Auth.user != null) {
            if (Auth.user.getChucVu().equalsIgnoreCase("Nhân viên")) {
                pnlDangKyTaiKhoan.setVisible(false);
            }
        }else{
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new com.bar.customUI.MaterialTabbed();
        pnlCaiDatChung = new javax.swing.JPanel();
        pnlDoiMatKhau = new RoundedPanel(20);
        jPanel6 = new RoundedPanel(100);
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlDangXuat = new RoundedPanel(20);
        jPanel15 = new RoundedPanel(100);
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlThoatChuongTrinh = new RoundedPanel(20);
        jPanel17 = new RoundedPanel(100);
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlDangKyTaiKhoan = new RoundedPanel(20);
        jPanel18 = new RoundedPanel(100);
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlGioiThieu = new javax.swing.JPanel();
        pnlHuongDan = new javax.swing.JPanel();

        setBackground(new java.awt.Color(243, 246, 253));
        setPreferredSize(new java.awt.Dimension(1835, 910));

        materialTabbed1.setBackground(new java.awt.Color(255, 255, 255));
        materialTabbed1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        pnlCaiDatChung.setBackground(new java.awt.Color(255, 255, 255));

        pnlDoiMatKhau.setOpaque(false);
        pnlDoiMatKhau.setBackground(new java.awt.Color(249, 243, 211));
        pnlDoiMatKhau.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDoiMatKhauMouseClicked(evt);
            }
        });

        //jPanel6.setOpaque(false);
        jPanel6.setBackground(new java.awt.Color(241, 235, 203));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(225, 230, 233));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/changepass_setting.png"))); // NOI18N
        jPanel6.add(jLabel3, java.awt.BorderLayout.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Đổi Mật Khẩu");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlDoiMatKhauLayout = new javax.swing.GroupLayout(pnlDoiMatKhau);
        pnlDoiMatKhau.setLayout(pnlDoiMatKhauLayout);
        pnlDoiMatKhauLayout.setHorizontalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDoiMatKhauLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        pnlDoiMatKhauLayout.setVerticalGroup(
            pnlDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiMatKhauLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pnlDangXuat.setOpaque(false);
        pnlDangXuat.setBackground(new java.awt.Color(182, 187, 235));
        pnlDangXuat.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDangXuatMouseClicked(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(190, 195, 248));
        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/logoutsetting.png"))); // NOI18N
        jPanel15.add(jLabel4, java.awt.BorderLayout.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Đăng Xuất");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlDangXuatLayout = new javax.swing.GroupLayout(pnlDangXuat);
        pnlDangXuat.setLayout(pnlDangXuatLayout);
        pnlDangXuatLayout.setHorizontalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangXuatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDangXuatLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        pnlDangXuatLayout.setVerticalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangXuatLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlThoatChuongTrinh.setOpaque(false);
        pnlThoatChuongTrinh.setBackground(new java.awt.Color(249, 216, 227));
        pnlThoatChuongTrinh.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlThoatChuongTrinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThoatChuongTrinhMouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(243, 212, 222));
        jPanel17.setOpaque(false);
        jPanel17.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/exitprogram.png"))); // NOI18N
        jPanel17.add(jLabel5, java.awt.BorderLayout.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Thoát Chương Trình");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlThoatChuongTrinhLayout = new javax.swing.GroupLayout(pnlThoatChuongTrinh);
        pnlThoatChuongTrinh.setLayout(pnlThoatChuongTrinhLayout);
        pnlThoatChuongTrinhLayout.setHorizontalGroup(
            pnlThoatChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThoatChuongTrinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlThoatChuongTrinhLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        pnlThoatChuongTrinhLayout.setVerticalGroup(
            pnlThoatChuongTrinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThoatChuongTrinhLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pnlDangKyTaiKhoan.setOpaque(false);
        pnlDangKyTaiKhoan.setBackground(new java.awt.Color(223, 246, 255));
        pnlDangKyTaiKhoan.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlDangKyTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDangKyTaiKhoanMouseClicked(evt);
            }
        });

        jPanel18.setOpaque(false);
        jPanel18.setBackground(new java.awt.Color(233, 249, 255));
        jPanel18.setOpaque(false);
        jPanel18.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel6.setBackground(new java.awt.Color(244, 252, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/registersetting.png"))); // NOI18N
        jPanel18.add(jLabel6, java.awt.BorderLayout.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setText("Đăng Kí Tài Khoản");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlDangKyTaiKhoanLayout = new javax.swing.GroupLayout(pnlDangKyTaiKhoan);
        pnlDangKyTaiKhoan.setLayout(pnlDangKyTaiKhoanLayout);
        pnlDangKyTaiKhoanLayout.setHorizontalGroup(
            pnlDangKyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangKyTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDangKyTaiKhoanLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        pnlDangKyTaiKhoanLayout.setVerticalGroup(
            pnlDangKyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangKyTaiKhoanLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCaiDatChungLayout = new javax.swing.GroupLayout(pnlCaiDatChung);
        pnlCaiDatChung.setLayout(pnlCaiDatChungLayout);
        pnlCaiDatChungLayout.setHorizontalGroup(
            pnlCaiDatChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaiDatChungLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(pnlDoiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(63, 63, 63)
                .addComponent(pnlDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(68, 68, 68)
                .addComponent(pnlThoatChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(59, 59, 59)
                .addComponent(pnlDangKyTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(250, 250, 250))
        );
        pnlCaiDatChungLayout.setVerticalGroup(
            pnlCaiDatChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCaiDatChungLayout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addGroup(pnlCaiDatChungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlThoatChuongTrinh, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(pnlDangXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(pnlDoiMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(pnlDangKyTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addGap(308, 308, 308))
        );

        materialTabbed1.addTab("   Cài Đặt Chung   ", pnlCaiDatChung);

        pnlGioiThieu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlGioiThieuLayout = new javax.swing.GroupLayout(pnlGioiThieu);
        pnlGioiThieu.setLayout(pnlGioiThieuLayout);
        pnlGioiThieuLayout.setHorizontalGroup(
            pnlGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1830, Short.MAX_VALUE)
        );
        pnlGioiThieuLayout.setVerticalGroup(
            pnlGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("   Giới Thiệu   ", pnlGioiThieu);

        pnlHuongDan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlHuongDanLayout = new javax.swing.GroupLayout(pnlHuongDan);
        pnlHuongDan.setLayout(pnlHuongDanLayout);
        pnlHuongDanLayout.setHorizontalGroup(
            pnlHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1830, Short.MAX_VALUE)
        );
        pnlHuongDanLayout.setVerticalGroup(
            pnlHuongDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("   Hướng Dẫn Sử Dụng   ", pnlHuongDan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDoiMatKhauMouseClicked
        new ChangePass().setVisible(true);
        SwingUtilities.getWindowAncestor(pnlCaiDatChung).dispose();
    }//GEN-LAST:event_pnlDoiMatKhauMouseClicked

    private void pnlDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDangXuatMouseClicked
        if (MsgBox.confirm(this, "Bạn có muốn đăng xuất?")) {
            new Login().setVisible(true);
            SwingUtilities.getWindowAncestor(pnlCaiDatChung).dispose();
        } else {

        }

    }//GEN-LAST:event_pnlDangXuatMouseClicked

    private void pnlThoatChuongTrinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThoatChuongTrinhMouseClicked
        if (MsgBox.confirm(this, "Bạn có muốn thoát chương trình?")) {
            System.exit(0);
        } else {

        }

    }//GEN-LAST:event_pnlThoatChuongTrinhMouseClicked

    private void pnlDangKyTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDangKyTaiKhoanMouseClicked
        new Register().setVisible(true);
        SwingUtilities.getWindowAncestor(pnlCaiDatChung).dispose();
    }//GEN-LAST:event_pnlDangKyTaiKhoanMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel6;
    private com.bar.customUI.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel pnlCaiDatChung;
    private javax.swing.JPanel pnlDangKyTaiKhoan;
    private javax.swing.JPanel pnlDangXuat;
    private javax.swing.JPanel pnlDoiMatKhau;
    private javax.swing.JPanel pnlGioiThieu;
    private javax.swing.JPanel pnlHuongDan;
    private javax.swing.JPanel pnlThoatChuongTrinh;
    // End of variables declaration//GEN-END:variables
}
