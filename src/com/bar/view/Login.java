/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.dao.MatKhauTamDAO;
import com.bar.dao.NhanVienDAO;
import com.bar.dao.TaiKhoanDAO;
import com.bar.model.MatKhauTam;
import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Login extends javax.swing.JFrame {

    boolean hideAndShow = true;
    NhanVienDAO nvDao = new NhanVienDAO();
    NhanVien nv = null;
    MatKhauTamDAO mktDao = new MatKhauTamDAO();
    static String user;
    static String pass;

    public Login() {
        initComponents();
        txtPassword.setEchoChar('\u25cf');
        initPassTam();
        this.changeIcon();
    }

    public void showPass() {
        txtPassword.setEchoChar((char) 0);
        btnShow.setIcon(new ImageIcon(this.getClass().getResource("images/dontshow.png")));
    }

    public void hidePass() {
        txtPassword.setEchoChar('\u25cf');
        btnShow.setIcon(new ImageIcon(this.getClass().getResource("images/show.png")));
    }

    ImageIcon icon;

    public void changeIcon() {
        icon = new ImageIcon(this.getClass().getResource("images/DEjavu_LoGo_App.png"));

        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkNhoMatKhau = new javax.swing.JCheckBox();
        txtQuenMatKhau = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        btnShow = new javax.swing.JButton();
        pnlDangNhap = new RoundedPanel(50);
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));
        setPreferredSize(new java.awt.Dimension(1000, 550));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(java.awt.Color.white);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 511));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/DEjavu (2).png"))); // NOI18N
        jLabel5.setToolTipText("");
        jPanel1.add(jLabel5, java.awt.BorderLayout.CENTER);

        jLabel6.setText("      © 2022. All Rights Reserved. Dejavu");
        jLabel6.setPreferredSize(new java.awt.Dimension(209, 50));
        jPanel1.add(jLabel6, java.awt.BorderLayout.PAGE_END);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/DEJAVU SHOP_1.png"))); // NOI18N
        jPanel1.add(jLabel7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(6, 40, 61));
        jPanel2.setForeground(new java.awt.Color(40, 40, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đăng nhập");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 277, 82));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 160, -1));

        txtTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 53));
        txtTenDangNhap.setMargin(new java.awt.Insets(2, 5, 2, 2));
        jPanel2.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 330, 45));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        chkNhoMatKhau.setBackground(new java.awt.Color(37, 109, 133));
        chkNhoMatKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        chkNhoMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        chkNhoMatKhau.setText("Nhớ mật khẩu?");
        chkNhoMatKhau.setOpaque(false);
        chkNhoMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkNhoMatKhauMouseClicked(evt);
            }
        });
        jPanel2.add(chkNhoMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        txtQuenMatKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtQuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtQuenMatKhau.setText("Quên mật khẩu?");
        txtQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQuenMatKhauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtQuenMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtQuenMatKhauMouseExited(evt);
            }
        });
        jPanel2.add(txtQuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtPassword.setFocusCycleRoot(true);
        txtPassword.setMargin(new java.awt.Insets(2, 5, 2, 2));
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });
        jPanel3.add(txtPassword, java.awt.BorderLayout.CENTER);

        btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/show.png"))); // NOI18N
        btnShow.setBorder(null);
        btnShow.setBorderPainted(false);
        btnShow.setContentAreaFilled(false);
        btnShow.setDefaultCapable(false);
        btnShow.setFocusPainted(false);
        btnShow.setPreferredSize(new java.awt.Dimension(50, 18));
        btnShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowMouseClicked(evt);
            }
        });
        jPanel3.add(btnShow, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 330, 45));

        pnlDangNhap.setOpaque(false);
        pnlDangNhap.setBackground(new java.awt.Color(191, 151, 66));
        pnlDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDangNhapMouseClicked(evt);
            }
        });
        pnlDangNhap.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ĐĂNG NHẬP");
        pnlDangNhap.add(jLabel4, java.awt.BorderLayout.CENTER);

        jPanel2.add(pnlDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 210, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/qr-code-white.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, -1, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowMouseClicked
        if (txtPassword.getPassword().length <= 0) {
            return;
        } else {
            if (hideAndShow) {
                showPass();
                hideAndShow = false;
            } else {
                hidePass();
                hideAndShow = true;
            }
        }
    }//GEN-LAST:event_btnShowMouseClicked

    private void txtQuenMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuenMatKhauMouseEntered
        txtQuenMatKhau.setForeground(Color.red);
        txtQuenMatKhau.setText("<html><u>Quên mật khẩu?</u></html>");
    }//GEN-LAST:event_txtQuenMatKhauMouseEntered

    private void txtQuenMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuenMatKhauMouseExited
        txtQuenMatKhau.setForeground(Color.white);
        txtQuenMatKhau.setText("Quên mật khẩu?");
    }//GEN-LAST:event_txtQuenMatKhauMouseExited

    private void chkNhoMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNhoMatKhauMouseClicked
        if (chkNhoMatKhau.isSelected()) {
            chkNhoMatKhau.setForeground(Color.green);
        } else {
            chkNhoMatKhau.setForeground(Color.white);
        }
    }//GEN-LAST:event_chkNhoMatKhauMouseClicked

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (checkInput()) {
//                System.out.println("DÔ");
                login();
            }
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean isExit = MsgBox.confirm(this, "Bạn có chắc chắn muốn thoát khỏi chương trình ?");
        if (!isExit) {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuenMatKhauMouseClicked
        new ForgotPassword().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtQuenMatKhauMouseClicked

    private void pnlDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDangNhapMouseClicked
        if (checkInput()) {
            login();
        }
    }//GEN-LAST:event_pnlDangNhapMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new QRCODE().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShow;
    private javax.swing.JCheckBox chkNhoMatKhau;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlDangNhap;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel txtQuenMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables

// XỬ LÍ NHỚ MẬT KHẨU
    void initPassTam() {
        List<MatKhauTam> mk = mktDao.select();
        if (mk == null) {
            txtPassword.setText("");
            txtTenDangNhap.setText("");
        } else {
            for (MatKhauTam matKhauTam : mk) {
                txtTenDangNhap.setText(matKhauTam.getTenTaiKhoanTam());
                txtPassword.setText(matKhauTam.getPassTam());
            }
        }

    }

    MatKhauTam getPassOnForm() {
        MatKhauTam mkt = new MatKhauTam();
        if (chkNhoMatKhau.isSelected()) {
            mkt.setPassTam(String.valueOf(txtPassword.getPassword()));
            mkt.setTenTaiKhoanTam(txtTenDangNhap.getText());
        } else {
            mkt.setPassTam(null);
            mkt.setTenTaiKhoanTam(null);
        }
        return mkt;
    }

    void updateTaiKhoanTam() {
        try {
            MatKhauTam tam = getPassOnForm();
            mktDao.update(tam);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean checkInput() {
        StringBuilder sb = new StringBuilder();
        String maNV = txtTenDangNhap.getText();
        String matKhau = String.valueOf(txtPassword.getPassword());
        if (maNV.equals("") || maNV.equals("Tên đăng nhập")) {
            sb.append("Tên đăng nhập không được để trống\n");
        }
        if (matKhau.equals("") || matKhau.equals("Mật khẩu")) {
            sb.append("Mật khẩu không được để trống\n");
        }
        if (sb.length() > 0) {
            sb.append("Vui lòng kiểm tra lại !");
            MsgBox.alert(this, sb.toString());
            return false;
        }
        return true;
    }

    public void getAccountOnForm() {
        user = txtTenDangNhap.getText();
        pass = String.valueOf(txtPassword.getPassword());
    }

    public void login() {
        if (this.isVisible()) {
            getAccountOnForm();
        }
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        Auth.account = taiKhoanDAO.selectNAME(user);
        if (Auth.account == null) {
            MsgBox.alert(this, "Tên đăng nhập của bạn không đúng. Vui lòng kiểm tra lại");
            txtTenDangNhap.requestFocus();
        } else if (!pass.equals(Auth.account.getMatKhau())) {
            MsgBox.alert(this, "Mật khẩu của bạn không đúng. Vui lòng kiểm tra lại");
            txtPassword.requestFocus();
        } else {
            Auth.user = nvDao.selectID(Auth.account.getMaNV());
            updateTaiKhoanTam();
//            Auth.account = taiKhoanDAO.selectNAME(tenDangNhap);
            if (Auth.isManager()) {  // Vai trò Manager
                new MainDejavu().setVisible(true);
                this.dispose();
            } else {
                new MainDejavuNhanVien().setVisible(true);
                this.dispose();
            }
        }
    }
}
