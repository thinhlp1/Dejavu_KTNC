
package com.bar.view;

import com.bar.model.SanPham;

import com.bar.util.XImage;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;


public class PanelRanderSP extends javax.swing.JPanel {

    SanPham sp;

    public PanelRanderSP() {
        initComponents();
    }
   

     public static String toMoneyString(long moneyy) {
        String money = moneyy + "";
        int x = 0; // cứ 3 số đơn vị sẽ là một dấu chấm
        for (int i = money.length() - 1; i >= 0; i--) {
            x++;
            if (x == 3) {
                money = money.substring(0, i) + '.' + money.substring(i, money.length());
                x = 0;
            }
        }
        if (money.charAt(0) == '.') {
            money = money.replaceFirst(".", "");
        }

        return money;
    }

    public static long toMoneyLong(String moneyy) {
        return Long.parseLong(moneyy.replaceAll("\\.", ""));
    }

    public void setSpLenGiaoDien() {
       
        if (sp.getHinh()!= null) {
            ImageIcon iconTam1 = XImage.read(sp.getHinh());
            Image img1 = iconTam1.getImage();
            ImageIcon icon1 = new ImageIcon(img1.getScaledInstance(180,180, Image.SCALE_SMOOTH));
           lbl_HinhSP.setIcon(icon1);

        }
        String giaSp = toMoneyString(sp.getGia());
        lbl_TenSP.setText(sp.getTenMon());
        lbl_GiaSP.setText(giaSp);
        
    }

    // lay bo vo gio hang
      public String getMaMon () {
       return this.sp.getMaMon();
}
    public String getTenSp() {
        return lbl_TenSP.getText();
    }

    public String getGiaSp() {
        return lbl_GiaSP.getText();
    }
    public String getHinh () {
       return sp.getHinh();
}

    public void setSp(SanPham sp) {
        this.sp = sp;
    }
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_TenSP = new javax.swing.JLabel();
        lbl_HinhSP = new javax.swing.JLabel();
        lbl_GiaSP = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.MatteBorder(null));
        setPreferredSize(new java.awt.Dimension(200, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_TenSP.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_TenSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TenSP.setText("Cocktail");
        lbl_TenSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_TenSP.setPreferredSize(new java.awt.Dimension(180, 30));
        lbl_TenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhSPMouseClicked(evt);
            }
        });
        add(lbl_TenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 180, 41));

        lbl_HinhSP.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_HinhSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_HinhSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/DEJAVU SHOP.png"))); // NOI18N
        lbl_HinhSP.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        lbl_HinhSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_HinhSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_HinhSP.setPreferredSize(new java.awt.Dimension(180, 180));
        lbl_HinhSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhSPMouseClicked(evt);
            }
        });
        add(lbl_HinhSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 180));

        lbl_GiaSP.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_GiaSP.setForeground(new java.awt.Color(255, 102, 102));
        lbl_GiaSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_GiaSP.setText("100000");
        lbl_GiaSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_GiaSP.setPreferredSize(new java.awt.Dimension(180, 30));
        lbl_GiaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhSPMouseClicked(evt);
            }
        });
        add(lbl_GiaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 180, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_HinhSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HinhSPMouseClicked
       ac.mouseClicked(evt);
    }//GEN-LAST:event_lbl_HinhSPMouseClicked
    MouseListener ac;

    public void setAction(MouseListener ac) {
        this.ac = ac;
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_GiaSP;
    private javax.swing.JLabel lbl_HinhSP;
    private javax.swing.JLabel lbl_TenSP;
    // End of variables declaration//GEN-END:variables
}
