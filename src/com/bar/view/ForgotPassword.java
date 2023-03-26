/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.dao.TaiKhoanDAO;
import com.bar.model.TaiKhoan;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import java.awt.HeadlessException;
import java.math.BigInteger;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author PHUOCTAI
 */
public class ForgotPassword extends javax.swing.JFrame {

    List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
    TaiKhoanDAO tkDao = new TaiKhoanDAO();
    String randomCode;

    public ForgotPassword() {
        initComponents();
        pnlLayLaiMatKhau.setEnabled(false);
        changeIcon();
    }
    ImageIcon icon;

    public void changeIcon() {
        icon = new ImageIcon(this.getClass().getResource("images/DEjavu_LoGo_App.png"));
        setIconImage(icon.getImage());
    }

    boolean checkNull() {
        StringBuilder sbd = new StringBuilder();
        if (txtTenDangNhap.getText().isEmpty()) {
            sbd.append("Tên đăng nhập trống!\n");
            txtTenDangNhap.requestFocus();
        }
        if (txtEmail1.getText().isEmpty()) {
            sbd.append("Email trống!\n");
            txtEmail1.requestFocus();
        }
        String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        if (!txtEmail1.getText().matches(EMAIL_PATTERN)) {
            sbd.append("Email không đúng định dạng!\nVD: example.com\n");
            txtEmail1.requestFocus();
        }
        if (sbd.length() > 0) {
            sbd.append("Vui lòng kiểm tra lại !");
            MsgBox.alert(this, sbd.toString());
            return false;
        }
        return true;
    }

    boolean checkAccount_Exist(String name) {
        Auth.account = tkDao.selectNAME(name);
        if (Auth.account == null) {
            MsgBox.alert(this, "Tài khoản không tồn tại!");
            return false;
        }
        return true;
    }

    private void returnHome() throws HeadlessException {
        String[] languages = {"Có, Trở về", "Không, Thoát"};
        int options = JOptionPane.showOptionDialog(this, "Bạn có muốn quay về trang đăng nhập ?", "Quán Bar Dejavu", 0, JOptionPane.QUESTION_MESSAGE, null, languages, "Có, Trở về");

        System.out.println(options);
        if (options == 0) {
            new Login().setVisible(true);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.dispose();
        } else if (options == -1) {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        } else {
            this.dispose();
        }
    }

    private void sendCode() {
        try {
            String taiKhoan = "taichet2003@gmail.com";
            String matKhau = "lejunmhmhfnlmkvo";
            //Các thông số kết nối tới mail Server
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            //Tạo đối tượng Session đưa vào các thông tin xác thực tài khoản email
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(taiKhoan, matKhau);
                }
            });
            //Lấy mã code random
            SecureRandom random = new SecureRandom();
            randomCode = new BigInteger(30, random).toString(32).toUpperCase();
//            System.out.println(randomCode);

            //lấy các thông tin người dùng nhập vào trên Form
            String from = taiKhoan;
            String to = txtEmail1.getText();
            String subject = "MÃ XÁC THỰC";
            String body = "Mã xác minh của bạn là: " + randomCode;
            //Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message); //gọi phương thức send để gửi message đi
            JOptionPane.showMessageDialog(this, "Đã gởi mã xác thực đến email của bạn!\nVui lòng kiểm tra lại!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

    }

    boolean checkCode() {
        if (!randomCode.equalsIgnoreCase(txtMaXacThuc.getText())) {
            MsgBox.alert(this, "Mã xác thực không đúng!");
            txtMaXacThuc.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        btnLayMa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaXacThuc = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pnlLayLaiMatKhau = new RoundedPanel(50);
        lblLayLaiMatKhau = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jLabel1.setText("LẤY LẠI MẬT KHẨU");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(77, 20, 340, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 180, 30));

        txtTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtTenDangNhap.setSelectionColor(new java.awt.Color(153, 0, 51));
        jPanel1.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 360, 50));

        btnLayMa.setBackground(new java.awt.Color(12, 151, 96));
        btnLayMa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLayMa.setForeground(new java.awt.Color(255, 255, 255));
        btnLayMa.setText("LẤY MÃ");
        btnLayMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayMaActionPerformed(evt);
            }
        });
        jPanel1.add(btnLayMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 150, 60));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mã xác thực");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 180, 30));

        txtMaXacThuc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaXacThuc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtMaXacThuc.setSelectionColor(new java.awt.Color(153, 0, 51));
        txtMaXacThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaXacThucKeyReleased(evt);
            }
        });
        jPanel1.add(txtMaXacThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 190, 60));

        txtEmail1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        txtEmail1.setSelectionColor(new java.awt.Color(153, 0, 51));
        jPanel1.add(txtEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 360, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 180, 30));

        pnlLayLaiMatKhau.setOpaque(false);
        pnlLayLaiMatKhau.setBackground(new java.awt.Color(191, 151, 66));
        pnlLayLaiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        pnlLayLaiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLayLaiMatKhauMouseClicked(evt);
            }
        });
        pnlLayLaiMatKhau.setLayout(new java.awt.BorderLayout());

        lblLayLaiMatKhau.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLayLaiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblLayLaiMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLayLaiMatKhau.setText("LẤY LẠI MẬT KHẨU");
        lblLayLaiMatKhau.setToolTipText("");
        lblLayLaiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLayLaiMatKhauMouseClicked(evt);
            }
        });
        pnlLayLaiMatKhau.add(lblLayLaiMatKhau, java.awt.BorderLayout.CENTER);

        jPanel1.add(pnlLayLaiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 230, 70));

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

    private void btnLayMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayMaActionPerformed
        if (checkNull()) {
            System.out.println("134");
            if (checkAccount_Exist(txtTenDangNhap.getText())) {
                sendCode();
            }
        }
    }//GEN-LAST:event_btnLayMaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        returnHome();
    }//GEN-LAST:event_formWindowClosing

    private void txtMaXacThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaXacThucKeyReleased
        if (txtMaXacThuc.getText().length() >= 5) {
            pnlLayLaiMatKhau.setEnabled(true);
        } else {
            pnlLayLaiMatKhau.setEnabled(false);
        }
    }//GEN-LAST:event_txtMaXacThucKeyReleased

    private void pnlLayLaiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLayLaiMatKhauMouseClicked
        if (checkCode()) {
            new GetPasswordNew().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_pnlLayLaiMatKhauMouseClicked

    private void lblLayLaiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLayLaiMatKhauMouseClicked
        if (checkCode()) {
            new GetPasswordNew().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_lblLayLaiMatKhauMouseClicked

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
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLayMa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLayLaiMatKhau;
    private javax.swing.JPanel pnlLayLaiMatKhau;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtMaXacThuc;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables

}
