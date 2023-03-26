package com.bar.view;

import com.bar.dao.NhanVienDAO;
import com.bar.dao.TaiKhoanDAO;
import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;
import com.bar.util.JdbcHelper;
//import com.bar.view.QL_TaiKhoanNV;
import com.bar.util.MsgBox;
import java.awt.Button;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 *
 * @author PHUOCTAI
 */
public class Register extends javax.swing.JFrame {

    List<NhanVien> listNV = new ArrayList<NhanVien>();
    List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
    boolean hideAndShow1 = true;
    boolean hideAndShow2 = true;
    TaiKhoanDAO tkDao = new TaiKhoanDAO();
    String checkNV = "";

    public Register() {
        initComponents();
        initCombobox();
        txtMatKhau.setEchoChar('\u25cf');
        txtXacNhanMatKhau.setEchoChar('\u25cf');
        this.changeIcon();
    }

    ImageIcon icon;

    public void changeIcon() {
        icon = new ImageIcon(this.getClass().getResource("images/DEjavu_LoGo_App.png"));
        setIconImage(icon.getImage());
    }



    void initCombobox() {
        NhanVienDAO nvDao = new NhanVienDAO();
        DefaultComboBoxModel cbomodel = (DefaultComboBoxModel) cboHoTen.getModel();
        cbomodel.removeAllElements();
        try {
            listNV = nvDao.select();
            for (NhanVien nv : listNV) {
                cbomodel.addElement(nv.getHoTenLot() + " " + nv.getTenNV());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //FUNCTION UPDATE
    void initCombobox2() {
        NhanVienDAO nvDao = new NhanVienDAO();
        DefaultComboBoxModel cbomodel = (DefaultComboBoxModel) cboHoTen.getModel();
        cbomodel.removeAllElements();
        try {
            listNV = nvDao.selectCheckNV();
            for (NhanVien nv : listNV) {        
                    cbomodel.addElement(nv.getHoTenLot() + " " + nv.getTenNV());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    String getIDNhanVien() {
        int index = cboHoTen.getSelectedIndex();
        return listNV.get(index).getMaNV();
    }

    boolean check_NV_Null() {
        StringBuilder sbd = new StringBuilder();
        if (txtTenDangNhap.getText().isEmpty()) {
            sbd.append("Tên đăng nhập trống!\n");
        }
        if (txtMatKhau.getPassword().length <= 0) {
            sbd.append("Mật khẩu trống trống!\n");
        }
        if (txtXacNhanMatKhau.getPassword().length <= 0) {
            sbd.append("Vui lòng xác nhân mật khẩu!\n");
        }
        if (sbd.length() > 0) {
            sbd.append("Vui lòng kiểm tra lại!");
            MsgBox.alert(this, sbd.toString());
            return false;
        }
        return true;
    }

    boolean check_NV_Exist_Account() {
        String maNV = getIDNhanVien();
        TaiKhoan tk = tkDao.selectID(getIDNhanVien());
        if (tk != null) {
            MsgBox.alert(this, "Nhân viên này đã có tài khoản!");
            return false;
        }
        return true;
    }

    boolean check_NV_Duplicate_Account() {
        String userName = txtTenDangNhap.getText();
        listTK = tkDao.select();
        for (TaiKhoan taiKhoan : listTK) {
            if (taiKhoan.getTenTaiKhoan().equalsIgnoreCase(userName)) {
                MsgBox.alert(this, "Tên đăng nhập đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    boolean checkPass() {
        String pass = String.valueOf(txtMatKhau.getPassword());
        String passConfirm = String.valueOf(txtXacNhanMatKhau.getPassword());
        if (!pass.equals(passConfirm)) {
            MsgBox.alert(this, "Mật khẩu không khớp!\nVui lòng kiếm tra lại");
            return false;
        }
        return true;
    }

    TaiKhoan getDataOnForm() {
        TaiKhoan tkGet = new TaiKhoan();
        tkGet.setTenTaiKhoan(txtTenDangNhap.getText());
        tkGet.setMatKhau(new String(txtMatKhau.getPassword()));
        tkGet.setMaNV(getIDNhanVien());
        return tkGet;
    }

    void register() {
        TaiKhoan tk = getDataOnForm();
        try {
            tkDao.insert(tk);
            MsgBox.alert(this, "Đăng ký thành công với tên tài khoản là " + tk.getTenTaiKhoan().toUpperCase());
        } catch (Exception e) {
            MsgBox.alert(this, "Đăng ký thất bại\nVui lòng kiểm tra lại!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btg_Role = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnShow_Hide_2 = new javax.swing.JButton();
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        btnShow_Hide = new javax.swing.JButton();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        cboHoTen = new com.bar.customUI.ComboBoxSuggestion();
        pnlTaoTaiKhoan = new RoundedPanel(50);
        lblTaoTaiKhoan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 102, 102));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(6, 40, 61));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TẠO TÀI KHOẢN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 277, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 180, 30));

        txtTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtTenDangNhap.setSelectionColor(new java.awt.Color(204, 54, 54));
        jPanel1.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 360, 45));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 200, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Xác nhận mật khẩu");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 190, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        btnShow_Hide_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/show.png"))); // NOI18N
        btnShow_Hide_2.setBorder(null);
        btnShow_Hide_2.setBorderPainted(false);
        btnShow_Hide_2.setContentAreaFilled(false);
        btnShow_Hide_2.setDefaultCapable(false);
        btnShow_Hide_2.setFocusPainted(false);
        btnShow_Hide_2.setPreferredSize(new java.awt.Dimension(50, 18));
        btnShow_Hide_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShow_Hide_2ActionPerformed(evt);
            }
        });
        jPanel4.add(btnShow_Hide_2, java.awt.BorderLayout.LINE_END);

        txtXacNhanMatKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtXacNhanMatKhau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtXacNhanMatKhau.setFocusCycleRoot(true);
        txtXacNhanMatKhau.setMargin(new java.awt.Insets(2, 5, 2, 2));
        txtXacNhanMatKhau.setSelectionColor(new java.awt.Color(204, 54, 54));
        jPanel4.add(txtXacNhanMatKhau, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 360, 45));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        btnShow_Hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/show.png"))); // NOI18N
        btnShow_Hide.setBorder(null);
        btnShow_Hide.setBorderPainted(false);
        btnShow_Hide.setContentAreaFilled(false);
        btnShow_Hide.setDefaultCapable(false);
        btnShow_Hide.setFocusPainted(false);
        btnShow_Hide.setPreferredSize(new java.awt.Dimension(50, 18));
        btnShow_Hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShow_HideActionPerformed(evt);
            }
        });
        jPanel3.add(btnShow_Hide, java.awt.BorderLayout.LINE_END);

        txtMatKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMatKhau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtMatKhau.setFocusCycleRoot(true);
        txtMatKhau.setMargin(new java.awt.Insets(2, 5, 2, 2));
        txtMatKhau.setSelectionColor(new java.awt.Color(204, 54, 54));
        jPanel3.add(txtMatKhau, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 360, 45));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Họ tên");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 180, 30));

        cboHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cboHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHoTenActionPerformed(evt);
            }
        });
        jPanel1.add(cboHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 360, 45));

        pnlTaoTaiKhoan.setOpaque(false);
        pnlTaoTaiKhoan.setBackground(new java.awt.Color(191, 151, 66));
        pnlTaoTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        pnlTaoTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTaoTaiKhoanMouseClicked(evt);
            }
        });
        pnlTaoTaiKhoan.setLayout(new java.awt.BorderLayout());

        lblTaoTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTaoTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaoTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaoTaiKhoan.setText("TẠO TÀI KHOẢN");
        lblTaoTaiKhoan.setToolTipText("");
        lblTaoTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaoTaiKhoanMouseClicked(evt);
            }
        });
        pnlTaoTaiKhoan.add(lblTaoTaiKhoan, java.awt.BorderLayout.CENTER);

        jPanel1.add(pnlTaoTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 230, 60));

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(40, 40, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/DEjavu (2).png"))); // NOI18N
        jPanel2.add(jLabel4, java.awt.BorderLayout.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/DEJAVU SHOP_1.png"))); // NOI18N
        jPanel2.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("© 2022. All Rights Reserved. Dejavu     ");
        jLabel8.setPreferredSize(new java.awt.Dimension(229, 40));
        jPanel2.add(jLabel8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//    QL_TaiKhoanNV tk = new QL_TaiKhoanNV();
    private void cboHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHoTenActionPerformed
        System.out.println(getIDNhanVien());
    }//GEN-LAST:event_cboHoTenActionPerformed

    private void btnShow_HideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShow_HideActionPerformed
        showAndHidePass(txtMatKhau, btnShow_Hide);
//        hideAndShow1 = false;
    }//GEN-LAST:event_btnShow_HideActionPerformed

    private void btnShow_Hide_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShow_Hide_2ActionPerformed
        showAndHidePass2(txtXacNhanMatKhau, btnShow_Hide_2);
//        hideAndShow2 = false;
    }//GEN-LAST:event_btnShow_Hide_2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean checkBack = MsgBox.confirm(this, "Quay lại trang chủ?");
        if (checkBack) {
//            QL_TaiKhoanNV qlnv = new QL_TaiKhoanNV();
//            qlnv.resetFormNV();
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void pnlTaoTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoTaiKhoanMouseClicked
        if (check_NV_Null()) {
            if (checkPass()) {
                if (check_NV_Exist_Account()) {
                    if (check_NV_Duplicate_Account()) {
                        register();
//                        tk.fillTable();
                    }
                }
            }
        }
    }//GEN-LAST:event_pnlTaoTaiKhoanMouseClicked

    private void lblTaoTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaoTaiKhoanMouseClicked
        if (check_NV_Null()) {
            if (checkPass()) {
                if (check_NV_Exist_Account()) {
                    if (check_NV_Duplicate_Account()) {
                        register();
//                        tk.fillTable();
                    }
                }
            }
        }
    }//GEN-LAST:event_lblTaoTaiKhoanMouseClicked

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_Role;
    private javax.swing.JButton btnShow_Hide;
    private javax.swing.JButton btnShow_Hide_2;
    private com.bar.customUI.ComboBoxSuggestion cboHoTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblTaoTaiKhoan;
    private javax.swing.JPanel pnlTaoTaiKhoan;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables

    void showPass(JPasswordField pass, JButton btn) {
        btn.setIcon(new ImageIcon(this.getClass().getResource("images/dontshow.png")));
        pass.setEchoChar((char) 0);
    }

    void hidePass(JPasswordField pass, JButton btn) {
        btn.setIcon(new ImageIcon(this.getClass().getResource("images/show.png")));
        pass.setEchoChar('\u25cf');
    }

    void showAndHidePass(JPasswordField pass, JButton btn) {
        if (pass.getPassword().length <= 0) {
            return;
        } else {
            if (hideAndShow1) {
                showPass(pass, btn);
                hideAndShow1 = false;
            } else {
                hidePass(pass, btn);
                hideAndShow1 = true;
            }
        }
    }

    void showAndHidePass2(JPasswordField pass, JButton btn) {
        if (pass.getPassword().length <= 0) {
            return;
        } else {
            if (hideAndShow2) {
                showPass(pass, btn);
                hideAndShow2 = false;
            } else {
                hidePass(pass, btn);
                hideAndShow2 = true;
            }
        }
    }

}
