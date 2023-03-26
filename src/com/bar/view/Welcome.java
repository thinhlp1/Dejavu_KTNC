package com.bar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author LAPTOP LENOVO
 */
public class Welcome extends javax.swing.JDialog implements Runnable {

    public Welcome(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Thread th;
        th = new Thread((Runnable) this);
        th.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMsg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        liquidProgress1 = new com.bar.customUI.LiquidProgress();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMsg.setBackground(new java.awt.Color(0, 173, 247));
        lblMsg.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(121, 65, 101));
        lblMsg.setText("Đang khởi động");
        jPanel1.add(lblMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 340, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/welcome.gif"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 580, 350));

        liquidProgress1.setForeground(new java.awt.Color(121, 65, 101));
        liquidProgress1.setAnimateColor(new java.awt.Color(210, 191, 203));
        liquidProgress1.setBorderColor(new java.awt.Color(121, 65, 101));
        jPanel1.add(liquidProgress1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, 240, 100));

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated


    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Welcome dialog = new Welcome(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMsg;
    private com.bar.customUI.LiquidProgress liquidProgress1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 101; i++) {
                int value = liquidProgress1.getValue();
                liquidProgress1.setValue(i);
                if (value < 100) {
                    if (value == 10) {
                        lblMsg.setText("Đang khởi động.");
                    }
                    if (value == 20) {
                        lblMsg.setText("Đang khởi động..");
                    }
                    if (value == 30) {
                        lblMsg.setText("Đang khởi động...");
                    }
                    if (value == 40) {
                        lblMsg.setText("Đang tải cơ sở dữ liệu.");
                        Thread.sleep(1500);

                    }
                    if (value == 55) {
                        lblMsg.setText("Đang tải cơ sở dữ liệu..");
                    }
                    if (value == 65) {
                        lblMsg.setText("Đang tải cơ sở dữ liệu...");
                    }

                    if (value == 80) {
                        lblMsg.setText("Hoàn thành tải tài nguyên người dùng...");
                    }
                    if (value == 95) {
                        lblMsg.setText("Đang mở ứng dụng");
                    Thread.sleep(1000);
                    }
                } else {
                    System.out.println("dfsdf");
                    this.dispose();
                    Login lg = new Login();
                    lg.setVisible(true);
                }
                Thread.sleep(20);
            }
        } catch (Exception e) {
        }
    }
}
