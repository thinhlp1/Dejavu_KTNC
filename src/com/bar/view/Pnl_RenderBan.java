package com.bar.view;

import com.bar.model.Ban;
import com.bar.util.XImage;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class Pnl_RenderBan extends javax.swing.JPanel {

    Ban ban;

    public Pnl_RenderBan() {
        initComponents();

    }

    public void setBanLenGiaoDien() {
       
        ImageIcon iconTam1 = XImage.read("HinhCaiBan.png");
        Image img1 = iconTam1.getImage();
        ImageIcon icon1 = new ImageIcon(img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        lbl_HinhCaiBan.setIcon(icon1);
        lbl_MaBan.setText(ban.getMaBan());
    }

    public boolean isTrangThai() {
        return ban.isTrangThai();
    }

    public String getMaBan() {
        return ban.getMaBan();
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    MouseListener ac;

    public void setAction(MouseListener ac) {
        this.ac = ac;
    }

    public void setVisible(boolean Vi) {
        lbl_HinhKhach.setVisible(Vi);
    }

    public void setViSiBleBanGop(boolean t) {
        lbl_MaBanGop.setVisible(t);
    }

    public void setMaBanGopOn(Ban ban, String maBanGop) {
        lbl_MaBanGop.setText(ban.getMaBan() + " + " + maBanGop);
    }

    public void setRong() {
        lbl_MaBanGop.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_MaBan = new javax.swing.JLabel();
        lbl_HinhKhach = new javax.swing.JLabel();
        lbl_MaBanGop = new javax.swing.JLabel();
        lbl_HinhCaiBan = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(190, 190));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_MaBanMouseClicked(evt);
            }
        });
        setLayout(null);

        lbl_MaBan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_MaBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_MaBan.setText("MaBan");
        lbl_MaBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_MaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_MaBanMouseClicked(evt);
            }
        });
        add(lbl_MaBan);
        lbl_MaBan.setBounds(55, 70, 72, 32);

        lbl_HinhKhach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_HinhKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/customer.png"))); // NOI18N
        add(lbl_HinhKhach);
        lbl_HinhKhach.setBounds(142, 144, 36, 33);

        lbl_MaBanGop.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_MaBanGop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(lbl_MaBanGop);
        lbl_MaBanGop.setBounds(102, 8, 76, 31);
        add(lbl_HinhCaiBan);
        lbl_HinhCaiBan.setBounds(10, 0, 50, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_MaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_MaBanMouseClicked
        ac.mouseClicked(evt);
    }//GEN-LAST:event_lbl_MaBanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_HinhCaiBan;
    private javax.swing.JLabel lbl_HinhKhach;
    private javax.swing.JLabel lbl_MaBan;
    private javax.swing.JLabel lbl_MaBanGop;
    // End of variables declaration//GEN-END:variables
}
