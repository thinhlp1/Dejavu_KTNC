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

public class pnl_tachBan extends javax.swing.JPanel {

    public pnl_tachBan() {
        initComponents();
        this.fillCbBanCoKhach();
        this.fillCbBanKoKhach();
    }
    BanDAO banDao = new BanDAO();
    SanPhamOderDAO spOderDao = new SanPhamOderDAO();
    List<SanPham_GioHang> listSpGh = new ArrayList<SanPham_GioHang>();
    BanOderDAO banOderDao = new BanOderDAO();
    //  List<Ban> listBanCoKhach = new ArrayList<Ban>();
    //   List<Ban> listBanKoKhach = new ArrayList<Ban>();

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
            cb_BanTach.addItem(listBanKoKhach.get(i));
        }
    }

    List<SanPham_GioHang> listSanPhamTach = new ArrayList<SanPham_GioHang>();

    public void fillTable() {

        Ban ban = (Ban) cb_BanCoKhach.getSelectedItem();

        DefaultTableModel model = (DefaultTableModel) tbl_ListBanKhach.getModel();
        model.setRowCount(0);
        listSpGh = spOderDao.selectSpMaBan(ban.getMaBan());

        for (SanPham_GioHang sanPham_GioHang : listSpGh) {
            model.addRow(new Object[]{sanPham_GioHang.getMasp(), sanPham_GioHang.getTenSP(), sanPham_GioHang.getGia(), sanPham_GioHang.getSoLuong()});
        }
    }

    public void fillTableBanCu() {

        //  Ban ban = (Ban) cb_BanCoKhach.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) tbl_ListBanKhach.getModel();
        model.setRowCount(0);
        // listSpGh = spOderDao.selectSpMaBan(ban.getMaBan());

        for (SanPham_GioHang sanPham_GioHang : listSpGh) {
            model.addRow(new Object[]{sanPham_GioHang.getMasp(), sanPham_GioHang.getTenSP(), sanPham_GioHang.getGia(), sanPham_GioHang.getSoLuong()});
        }
    }

    public void listCuToListMoi() {
        int index = tbl_ListBanKhach.getSelectedRow();
        if (listSpGh.size() == 0) {
            MsgBox.alert(this, "Bạn đã chuyển tất cả món !");
            return;
        }
        if (index < 0) {
            MsgBox.alert(this, "Vui lòng chọn món trên bảng !");
            return;
        }
        listSanPhamTach.add(new SanPham_GioHang(listSpGh.get(index).getMaBan(), listSpGh.get(index).getHinh(), listSpGh.get(index).getMasp(), listSpGh.get(index).getTenSP(), listSpGh.get(index).getGia(), 1));

        if (listSpGh.get(index).getSoLuong() >= 2) {
            listSpGh.get(index).setSoLuong(listSpGh.get(index).getSoLuong() - 1);
            if (listSpGh.get(index).getSoLuong() == 0) {
                listSpGh.remove(index);
            }
        } else {
            listSpGh.remove(index);
        }

        int check = -1;
        int viTriXoa = -1;
        for (int i = 0; i < listSanPhamTach.size(); i++) {
            for (int j = i + 1; j < listSanPhamTach.size(); j++) {
                if (listSanPhamTach.get(i).getMasp().equals(listSanPhamTach.get(j).getMasp())) {
                    check = i;
                    viTriXoa = j;
                    break;
                }
            }

        }

        if (check >= 0) {
            listSanPhamTach.get(check).setSoLuong(listSanPhamTach.get(check).getSoLuong() + 1);
            listSanPhamTach.remove(viTriXoa);
        }

        fillTableBanCu();
        fillTableBanTach();
    }

    public void listMoiToListCu() {
        int index = tbl_ListMonMoi.getSelectedRow();
        if (listSanPhamTach.size() == 0) {
            MsgBox.alert(this, "Bàn mới tách chưa có sản phẩm !");
            return;
        }
        if (index < 0) {
            MsgBox.alert(this, "Vui lòng chọn món trên bảng !");
            return;
        }
        listSpGh.add(new SanPham_GioHang(listSanPhamTach.get(index).getMaBan(), listSanPhamTach.get(index).getHinh(), listSanPhamTach.get(index).getMasp(), listSanPhamTach.get(index).getTenSP(), listSanPhamTach.get(index).getGia(), 1));

        if (listSanPhamTach.get(index).getSoLuong() >= 2) {
            listSanPhamTach.get(index).setSoLuong(listSanPhamTach.get(index).getSoLuong() - 1);
            if (listSanPhamTach.get(index).getSoLuong() == 0) {
                listSanPhamTach.remove(index);
            }
        } else {
            listSanPhamTach.remove(index);
        }

        int check = -1;
        int viTriXoa = -1;
        for (int i = 0; i < listSpGh.size(); i++) {
            for (int j = i + 1; j < listSpGh.size(); j++) {
                if (listSpGh.get(i).getMasp().equals(listSpGh.get(j).getMasp())) {
                    check = i;
                    viTriXoa = j;
                    break;
                }
            }

        }

        if (check >= 0) {
            listSpGh.get(check).setSoLuong(listSpGh.get(check).getSoLuong() + 1);
            listSpGh.remove(viTriXoa);
        }

        fillTableBanCu();
        fillTableBanTach();
    }

    public void fillTableBanTach() {

        DefaultTableModel model = (DefaultTableModel) tbl_ListMonMoi.getModel();
        model.setRowCount(0);
        //   listSpGh = spOderDao.selectSpMaBan(ban.getMaBan());

        for (SanPham_GioHang sanPham_GioHang : listSanPhamTach) {
            model.addRow(new Object[]{sanPham_GioHang.getMasp(), sanPham_GioHang.getTenSP(), sanPham_GioHang.getGia(), sanPham_GioHang.getSoLuong()});
        }

    }

    public void chuyenMonSangBanMoi() {

    }

    public void TachBan() {
        Ban banTach = (Ban) cb_BanTach.getSelectedItem();
        Ban banCoKhach = (Ban) cb_BanCoKhach.getSelectedItem();
        if (listSanPhamTach.size() == 0) {
            MsgBox.alert(this, "Bạn chưa chọn món tách !");
            return;
        }

        BanOder banOder = new BanOder(banTach.getMaBan(), null, null, null);

        //them ban oder moi
        banOderDao.insert(banOder);
        List<SanPham_GioHang> listNew = listSanPhamTach;
        for (int i = 0; i < listNew.size(); i++) {
            listNew.get(i).setMaBan(banTach.getMaBan());
            spOderDao.insert(listNew.get(i));
        }

        // xoa ban oder cu
        Ban banChuyen = new Ban();
        banChuyen.setMaBan(banTach.getMaBan());
        banChuyen.setTrangThai(true);
        banDao.updateTrangThai(banChuyen);
      

        if (listSpGh.size() == 0) {
            MsgBox.alert(this, "Bạn đã chuyển tất cả món đi bàn sẽ trống khách!");
            Ban banCu = new Ban();
            banCu.setMaBan(banCoKhach.getMaBan());
            banCu.setTrangThai(true);
            banDao.updateTrangThai(banChuyen);
            
            spOderDao.delete(banCoKhach.getMaBan());
            banOderDao.delete(banCoKhach.getMaBan());
            Ban ban = new Ban();
            ban.setTrangThai(false);
            ban.setMaBan(banCoKhach.getMaBan());
            banDao.updateTrangThai(ban);
              MsgBox.alert(this, "Tách bàn thành công !");
        } else {
            spOderDao.delete(banCoKhach.getMaBan());
            List<SanPham_GioHang> listBanCu = listSpGh;
            for (int i = 0; i < listBanCu.size(); i++) {
                listBanCu.get(i).setMaBan(banCoKhach.getMaBan());
                spOderDao.insert(listBanCu.get(i));
                  MsgBox.alert(this, "Tách bàn thành công !");
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new RoundedPanel(50);
        lbl_TachBan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ListBanKhach = new com.bar.customUI.TableWhite();
        cb_BanCoKhach = new com.bar.customUI.ComboBoxSuggestion();
        btn_ChuyenMonVe = new javax.swing.JButton();
        btn_ChuyenMonDi = new javax.swing.JButton();
        cb_BanTach = new com.bar.customUI.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ListMonMoi = new com.bar.customUI.TableWhite();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 650));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tách Bàn");

        jPanel7.setOpaque(false);
        jPanel7.setBackground(new java.awt.Color(3, 155, 216));
        jPanel7.setLayout(new java.awt.BorderLayout());

        lbl_TachBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_TachBan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TachBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TachBan.setText("Lưu Bàn Tách");
        lbl_TachBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TachBanMouseClicked(evt);
            }
        });
        jPanel7.add(lbl_TachBan, java.awt.BorderLayout.CENTER);

        tbl_ListBanKhach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Món", "Tên Món", "Giá", "Số lượng"
            }
        ));
        tbl_ListBanKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(tbl_ListBanKhach);

        cb_BanCoKhach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanCoKhach.setMinimumSize(new java.awt.Dimension(130, 30));
        cb_BanCoKhach.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanCoKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanCoKhachActionPerformed(evt);
            }
        });

        btn_ChuyenMonVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/prev_gradient.png"))); // NOI18N
        btn_ChuyenMonVe.setBorderPainted(false);
        btn_ChuyenMonVe.setContentAreaFilled(false);
        btn_ChuyenMonVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuyenMonVeActionPerformed(evt);
            }
        });

        btn_ChuyenMonDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/next_gradient.png"))); // NOI18N
        btn_ChuyenMonDi.setBorderPainted(false);
        btn_ChuyenMonDi.setContentAreaFilled(false);
        btn_ChuyenMonDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChuyenMonDiActionPerformed(evt);
            }
        });

        cb_BanTach.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanTach.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanTach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanTachActionPerformed(evt);
            }
        });

        tbl_ListMonMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Món", "Tên Món", "Giá Món", "Số lượng"
            }
        ));
        tbl_ListMonMoi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(tbl_ListMonMoi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_ChuyenMonVe)
                                    .addComponent(btn_ChuyenMonDi)))
                            .addComponent(cb_BanCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_BanTach, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_ChuyenMonVe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ChuyenMonDi)
                        .addGap(222, 222, 222))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_BanTach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_BanCoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ChuyenMonVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChuyenMonVeActionPerformed
        this.listMoiToListCu();
    }//GEN-LAST:event_btn_ChuyenMonVeActionPerformed

    private void cb_BanTachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanTachActionPerformed

    }//GEN-LAST:event_cb_BanTachActionPerformed

    private void cb_BanCoKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanCoKhachActionPerformed
        if (cb_BanCoKhach.getSelectedItem() == null) {
            return;
        }
        this.fillTable();
        listSanPhamTach.clear();
       this.fillTableBanTach();
    }//GEN-LAST:event_cb_BanCoKhachActionPerformed

    private void btn_ChuyenMonDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChuyenMonDiActionPerformed
        this.listCuToListMoi();


    }//GEN-LAST:event_btn_ChuyenMonDiActionPerformed

    private void lbl_TachBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_TachBanMouseClicked
        this.TachBan();

        cb_BanCoKhach.removeAllItems();
        cb_BanTach.removeAllItems();

        fillCbBanCoKhach();
        fillCbBanKoKhach();
        listSanPhamTach.clear();
        fillTableBanTach();
    }//GEN-LAST:event_lbl_TachBanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ChuyenMonDi;
    private javax.swing.JButton btn_ChuyenMonVe;
    private com.bar.customUI.ComboBoxSuggestion cb_BanCoKhach;
    private com.bar.customUI.ComboBoxSuggestion cb_BanTach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_TachBan;
    private com.bar.customUI.TableWhite tbl_ListBanKhach;
    private com.bar.customUI.TableWhite tbl_ListMonMoi;
    // End of variables declaration//GEN-END:variables
}
