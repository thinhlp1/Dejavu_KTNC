package com.bar.view;

import com.bar.dao.BanDAO;
import com.bar.dao.BanOderDAO;
import com.bar.dao.SanPhamOderDAO;
import com.bar.model.Ban;
import com.bar.model.BanOder;
import com.bar.model.SanPham_GioHang;
import com.bar.util.MsgBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class pnl_ChuyenBan extends javax.swing.JPanel {

    BanDAO banDao = new BanDAO();
    SanPhamOderDAO spOderDao = new SanPhamOderDAO();
    List<SanPham_GioHang> listSpGh = new ArrayList<SanPham_GioHang>();
    BanOderDAO banOderDao = new BanOderDAO();
  //  List<Ban> listBanCoKhach = new ArrayList<Ban>();
 //   List<Ban> listBanKoKhach = new ArrayList<Ban>();

    public pnl_ChuyenBan() {
        initComponents();
        this.fillCbBanCoKhach();
        this.fillCbBanKoKhach();
    }

    public void fillCbBanCoKhach() {
     
        List<Ban> listBanCoKhach = new ArrayList<Ban>();
//        cb_BanCoKhach.removeAllItems();
        listBanCoKhach = banDao.selectBan(true);
       
         List<BanOder> listCheckBanOder = banOderDao.selectMBGopNotNull();

        if (listCheckBanOder.size() == 0) {
            for (int x = 0; x < listBanCoKhach.size(); x++) {
                cb_BanCoKhach.addItem(listBanCoKhach.get(x));

            }
        } else {

            int doDai = listBanCoKhach.size();
            for (int i = doDai - 1; i < doDai; i--) {
                if (i < 0) {
                    break;
                }
                for (int j = 0; j < listCheckBanOder.size(); j++) {
                    if (listBanCoKhach.get(i).getMaBan().equals(listCheckBanOder.get(j).getMaBanGop())) {
                        listBanCoKhach.remove(i);
                        //listCheckBanOder.remove(j);
                        break;
                    }

                }

            }

            if (listBanCoKhach.size() == 0) {
                return;
            } else {
                for (int x = 0; x < listBanCoKhach.size(); x++) {
                    cb_BanCoKhach.addItem(listBanCoKhach.get(x));

                }
            }
        }
    }

    public void fillCbBanKoKhach() {
//        listBanKoKhach.clear();
        List<Ban> listBanKoKhach = new ArrayList<Ban>();
//        cb_BanKhongCoKhach.removeAllItems();
        listBanKoKhach = banDao.selectBan(false);
        for (int i = 0; i < listBanKoKhach.size(); i++) {
            cb_BanKhongCoKhach.addItem(listBanKoKhach.get(i));
        }
    }

    public void fillTable() {
        Ban ban = (Ban) cb_BanCoKhach.getSelectedItem();
        
        if (ban == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbl_BanMonAn.getModel();
        model.setRowCount(0);
        listSpGh = spOderDao.selectSpMaBan(ban.getMaBan());

        for (SanPham_GioHang sanPham_GioHang : listSpGh) {
            model.addRow(new Object[]{sanPham_GioHang.getMasp(), sanPham_GioHang.getTenSP(), sanPham_GioHang.getGia(), sanPham_GioHang.getSoLuong()});
        }

    }

    public void ChuyenBan() {
        Ban banKhongKhach = (Ban) cb_BanKhongCoKhach.getSelectedItem();
        Ban banCoKhach = (Ban) cb_BanCoKhach.getSelectedItem();
        BanOder banOder = new BanOder(banKhongKhach.getMaBan(), null, null, null);

        //them ban oder moi
        banOderDao.insert(banOder);
        List<SanPham_GioHang> listNew = listSpGh;
        for (int i = 0; i < listNew.size(); i++) {
            listNew.get(i).setMaBan(banKhongKhach.getMaBan());
            spOderDao.insert(listNew.get(i));
        }

        // xoa ban oder cu
        Ban banChuyen = new Ban();
        banChuyen.setMaBan(banKhongKhach.getMaBan());
        banChuyen.setTrangThai(true);
        banDao.updateTrangThai(banChuyen);
        MsgBox.alert(this, "Chuyển bàn thành công !");
        spOderDao.delete(banCoKhach.getMaBan());
        banOderDao.delete(banCoKhach.getMaBan());
        Ban ban = new Ban();
        ban.setTrangThai(false);
        ban.setMaBan(banCoKhach.getMaBan());
        banDao.updateTrangThai(ban);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb_BanCoKhach = new com.bar.customUI.ComboBoxSuggestion();
        jPanel6 = new RoundedPanel(50);
        lbl_ChuyenBan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_BanMonAn = new com.bar.customUI.TableWhite();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_BanKhongCoKhach = new com.bar.customUI.ComboBoxSuggestion();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setPreferredSize(new java.awt.Dimension(1100, 650));

        cb_BanCoKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanCoKhach.setMinimumSize(new java.awt.Dimension(130, 30));
        cb_BanCoKhach.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanCoKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_BanCoKhachMouseClicked(evt);
            }
        });
        cb_BanCoKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanCoKhachActionPerformed(evt);
            }
        });

        jPanel6.setOpaque(false);
        jPanel6.setBackground(new java.awt.Color(3, 155, 216));
        jPanel6.setLayout(new java.awt.BorderLayout());

        lbl_ChuyenBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ChuyenBan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ChuyenBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ChuyenBan.setText("Chuyển Bàn");
        lbl_ChuyenBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ChuyenBanMouseClicked(evt);
            }
        });
        jPanel6.add(lbl_ChuyenBan, java.awt.BorderLayout.CENTER);

        tbl_BanMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Món", "Tên Món", "Giá", "Số Lượng"
            }
        ));
        tbl_BanMonAn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(tbl_BanMonAn);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Chọn bàn cần chuyển :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Chọn bàn chuyển tới :");

        cb_BanKhongCoKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanKhongCoKhach.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanKhongCoKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanKhongCoKhachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cb_BanKhongCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_BanCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_BanCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_BanKhongCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_BanCoKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_BanCoKhachMouseClicked


    }//GEN-LAST:event_cb_BanCoKhachMouseClicked

    private void cb_BanCoKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanCoKhachActionPerformed
        if (cb_BanCoKhach.getSelectedItem() == null) {
           return;
}
        this.fillTable();

    }//GEN-LAST:event_cb_BanCoKhachActionPerformed

    private void cb_BanKhongCoKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanKhongCoKhachActionPerformed
       // fillCbBanKoKhach();
    }//GEN-LAST:event_cb_BanKhongCoKhachActionPerformed

    private void lbl_ChuyenBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ChuyenBanMouseClicked
        this.ChuyenBan();
        
        cb_BanCoKhach.removeAllItems();
        cb_BanKhongCoKhach.removeAllItems();
        fillCbBanCoKhach();
        fillCbBanKoKhach();
        
    }//GEN-LAST:event_lbl_ChuyenBanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bar.customUI.ComboBoxSuggestion cb_BanCoKhach;
    private com.bar.customUI.ComboBoxSuggestion cb_BanKhongCoKhach;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_ChuyenBan;
    private com.bar.customUI.TableWhite tbl_BanMonAn;
    // End of variables declaration//GEN-END:variables
}
