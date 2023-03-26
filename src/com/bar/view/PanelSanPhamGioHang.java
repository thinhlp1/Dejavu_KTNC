/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bar.view;

import com.bar.model.SanPham;
import com.bar.model.SanPham_GioHang;
import com.bar.util.XImage;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author thaih
 */
public class PanelSanPhamGioHang extends javax.swing.JPanel {

    SanPham_GioHang spGioHang;    
    public PanelSanPhamGioHang() {
        initComponents();
    }
    


    public void setSP (SanPham_GioHang spGioHang) {
         this.spGioHang = spGioHang;
}
    public void setSpOnFrom(SanPham_GioHang spGioHang) {
        
      if (spGioHang.getHinh()!= null) {
            ImageIcon iconTam1 = XImage.read(spGioHang.getHinh());
            Image img1 = iconTam1.getImage();
            ImageIcon icon1 = new ImageIcon(img1.getScaledInstance(72,66, Image.SCALE_SMOOTH));
           lbl_HinhGioHang.setIcon(icon1);

        }
        String giaSp = toMoneyString(spGioHang.getGia());
        lbl_Gia.setText(giaSp);
        lbl_TenSP.setText(spGioHang.getTenSP());
        lbl_SoLuong.setText(spGioHang.getSoLuong()+"");
}

  public void tangSoLuong() {
    
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
    
    public String getTenSP () {
       return lbl_TenSP.getText();
}
    public long getGiaSp() {
       return toMoneyLong(lbl_Gia.getText());
}
    public String getHinhSp () {
       return lbl_HinhGioHang.getText();
}

   
    public int getSoLuongSp () {
       return Integer.parseInt(lbl_SoLuong.getText());
} 

   public void setSoLuong (int soLuong) {
           String soLu = String.valueOf(soLuong);
           lbl_SoLuong.setText(soLu);
}
    MouseListener acGiam;
    MouseListener acTang;
    MouseListener acXoa;
    public void setActionGiam(MouseListener acGiam) {
        this.acGiam = acGiam;
    }

    public void setActionTang(MouseListener acTang) {
        this.acTang = acTang;
    }
    public void setActionXoa(MouseListener acXoa) {
        this.acXoa = acXoa;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_HinhGioHang = new javax.swing.JLabel();
        lbl_TenSP = new javax.swing.JLabel();
        lbl_Gia = new javax.swing.JLabel();
        lbl_SoLuong = new javax.swing.JLabel();
        lbl_Tang = new javax.swing.JLabel();
        lbl_Giam = new javax.swing.JLabel();
        lbl_HuyOther = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        setPreferredSize(new java.awt.Dimension(660, 80));

        lbl_HinhGioHang.setPreferredSize(new java.awt.Dimension(72, 66));

        lbl_TenSP.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_TenSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TenSP.setText("Ten");

        lbl_Gia.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_Gia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Gia.setText("Gia");

        lbl_SoLuong.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_SoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_SoLuong.setText("1");

        lbl_Tang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Tang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/icon_TangSoLuong30px.png"))); // NOI18N
        lbl_Tang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Tang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TangMouseClicked(evt);
            }
        });

        lbl_Giam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Giam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/icon_GiamSoLuong30px.png"))); // NOI18N
        lbl_Giam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Giam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_GiamMouseClicked(evt);
            }
        });

        lbl_HuyOther.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_HuyOther.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/icon_huyOtherGioHang.png"))); // NOI18N
        lbl_HuyOther.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_HuyOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HuyOtherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbl_HinhGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Gia, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Giam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Tang)
                .addGap(74, 74, 74)
                .addComponent(lbl_HuyOther)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_HinhGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Giam)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Tang)
                            .addComponent(lbl_HuyOther))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_GiamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_GiamMouseClicked
       acGiam.mouseClicked(evt);
    }//GEN-LAST:event_lbl_GiamMouseClicked

    private void lbl_TangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_TangMouseClicked
        acTang.mouseClicked(evt);
    }//GEN-LAST:event_lbl_TangMouseClicked

    private void lbl_HuyOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HuyOtherMouseClicked
       acXoa.mouseClicked(evt);
    }//GEN-LAST:event_lbl_HuyOtherMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_Gia;
    private javax.swing.JLabel lbl_Giam;
    private javax.swing.JLabel lbl_HinhGioHang;
    private javax.swing.JLabel lbl_HuyOther;
    private javax.swing.JLabel lbl_SoLuong;
    private javax.swing.JLabel lbl_Tang;
    private javax.swing.JLabel lbl_TenSP;
    // End of variables declaration//GEN-END:variables
}
