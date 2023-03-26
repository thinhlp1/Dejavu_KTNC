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

public class pnl_GopBan extends javax.swing.JPanel {

    public pnl_GopBan() {
        initComponents();
        fillCbBanCoKhach();
        fillCbBanKhongKhach();
        fillTable();
    }
    BanDAO banDao = new BanDAO();
    SanPhamOderDAO spOderDao = new SanPhamOderDAO();
    List<SanPham_GioHang> listSpGh = new ArrayList<SanPham_GioHang>();
    BanOderDAO banOderDao = new BanOderDAO();
    //  List<Ban> listBanCoKhach = new ArrayList<Ban>();
    //   List<Ban> listBanKoKhach = new ArrayList<Ban>();

    public void checkBanDaGop() {

    }

    public void fillCbBanCoKhach() {

        List<Ban> listBanCoKhach = new ArrayList<Ban>();
//        cb_BanCoKhach.removeAllItems();
        listBanCoKhach = banDao.selectBan(true);

        List<BanOder> listCheckBanOder = banOderDao.selectMBGopNotNull();

        // listbancokhach = 4; // 2 // 2
        // 
        if (listCheckBanOder.size() == 0) {
            for (int x = 0; x < listBanCoKhach.size(); x++) {
                cb_BanGop.addItem(listBanCoKhach.get(x));

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
                    cb_BanGop.addItem(listBanCoKhach.get(x));

                }
            }
        }
    }

    public void fillCbBanKhongKhach() {
        List<Ban> listBanKoKhach = new ArrayList<Ban>();
//        cb_BanCoKhach.removeAllItems();
        listBanKoKhach = banDao.selectBan(false);
        for (int i = 0; i < listBanKoKhach.size(); i++) {
            cb_BanCanGop.addItem(listBanKoKhach.get(i));
        }
    }

    // List<SanPham_GioHang> listSanPhamGop = new ArrayList<SanPham_GioHang>();
    public void fillTable() {

        Ban ban = (Ban) cb_BanGop.getSelectedItem();
        if (ban == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbl_BanGop.getModel();
        model.setRowCount(0);
        listSpGh = spOderDao.selectSpMaBan(ban.getMaBan());

        for (SanPham_GioHang sanPham_GioHang : listSpGh) {
            model.addRow(new Object[]{sanPham_GioHang.getMasp(), sanPham_GioHang.getTenSP(), sanPham_GioHang.getGia(), sanPham_GioHang.getSoLuong()});
        }
    }

    public void GopBan() {
        Ban banGop = (Ban) cb_BanGop.getSelectedItem();
        Ban banCanGop = (Ban) cb_BanCanGop.getSelectedItem();
        Ban ban = new Ban();

        ban.setTrangThai(true);
        ban.setMaBan(banCanGop.getMaBan());
        banDao.updateTrangThai(ban);

        BanOder banOderUpdate = new BanOder();
        banOderUpdate.setMaBan(banGop.getMaBan());
        banOderUpdate.setMaBanGop(banCanGop.getMaBan());
        banOderDao.updateMaBanOder(banOderUpdate);

        // ban oder them cai ban moi
        BanOder banOderNew = new BanOder();
        banOderNew.setMaBan(banCanGop.getMaBan());
        banOderNew.setMaBanGop(banGop.getMaBan());
        banOderDao.insert(banOderNew);
        // them list san pham vao gio hang
        for (int i = 0; i < listSpGh.size(); i++) {
            SanPham_GioHang sp = new SanPham_GioHang(banCanGop.getMaBan(), listSpGh.get(i).getHinh(), listSpGh.get(i).getMasp(), listSpGh.get(i).getTenSP(), listSpGh.get(i).getGia(), listSpGh.get(i).getSoLuong());
            spOderDao.insert(sp);
        }

        MsgBox.alert(this, "Gọp bàn thành công !");
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb_BanCanGop = new com.bar.customUI.ComboBoxSuggestion();
        cb_BanGop = new com.bar.customUI.ComboBoxSuggestion();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_BanGop = new com.bar.customUI.TableWhite();
        jPanel6 = new RoundedPanel(50);
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        cb_BanCanGop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanCanGop.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanCanGop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanCanGopActionPerformed(evt);
            }
        });

        cb_BanGop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cb_BanGop.setMinimumSize(new java.awt.Dimension(130, 30));
        cb_BanGop.setPreferredSize(new java.awt.Dimension(130, 30));
        cb_BanGop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_BanGopActionPerformed(evt);
            }
        });

        tbl_BanGop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Món", "Tên Món", "Giá", "Số Lượng"
            }
        ));
        tbl_BanGop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(tbl_BanGop);

        jPanel6.setOpaque(false);
        jPanel6.setBackground(new java.awt.Color(3, 155, 216));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Gọp Bàn");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel2, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gọp Bàn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_BanGop, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(121, 121, 121)
                                .addComponent(cb_BanCanGop, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_BanCanGop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_BanGop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_BanCanGopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanCanGopActionPerformed

    }//GEN-LAST:event_cb_BanCanGopActionPerformed

    private void cb_BanGopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_BanGopActionPerformed
        this.fillTable();
    }//GEN-LAST:event_cb_BanGopActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.GopBan();
        cb_BanCanGop.removeAllItems();
        cb_BanGop.removeAllItems();
        this.fillCbBanCoKhach();
        this.fillCbBanKhongKhach();
       fillTable() ;
    }//GEN-LAST:event_jLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bar.customUI.ComboBoxSuggestion cb_BanCanGop;
    private com.bar.customUI.ComboBoxSuggestion cb_BanGop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private com.bar.customUI.TableWhite tbl_BanGop;
    // End of variables declaration//GEN-END:variables
}
