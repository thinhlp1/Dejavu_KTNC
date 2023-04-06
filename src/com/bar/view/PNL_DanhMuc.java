/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.dao.DanhMucDAO;
import com.bar.model.DanhMuc;
import com.bar.model.SanPham;
import com.bar.util.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Lenovo
 */
public class PNL_DanhMuc extends javax.swing.JPanel {

    /**
     * Creates new form PNL_DanhMuc
     */
    public PNL_DanhMuc() {
        initComponents();
        initLoadData();
        fillTableDanhMuc();
        // initTable(tblDanhMuc);
    }

    DanhMucDAO dmdao = new DanhMucDAO();
    int row_DanhMuc = -1;

    public void initLoadData() {
        setModelTable_DanhMuc();
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
    }

    public void setModelTable_DanhMuc() {
        DefaultTableModel modelDanhMuc = new DefaultTableModel(new Object[][]{}, new Object[]{"Ma Danh Muc", "Ten Danh Muc"});
        tblDanhMuc.setModel(modelDanhMuc);
    }

    void updateStatus() {
        boolean edit = (this.row_DanhMuc >= 0);
        boolean first = (this.row_DanhMuc == 0);
        boolean last = (this.row_DanhMuc == tblDanhMuc.getRowCount() - 1);
        txtMaDanhMuc.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnCapNhat.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPre.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);

    }

    void fillTableDanhMuc() {
        DefaultTableModel model = (DefaultTableModel) tblDanhMuc.getModel();
        tblDanhMuc.setDefaultEditor(Object.class, null);
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem2.getText();
            if (keyword != null) {

                List<com.bar.model.DanhMuc> list = dmdao.selectByKeyword(keyword);
                for (com.bar.model.DanhMuc dm : list) {
                    Object[] row = {
                        dm.getMaDanhMuc(),
                        dm.getTenDanhMuc()};
                    model.addRow(row);
                }
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
    }

    public int fillTableDanhMuc(List<com.bar.model.DanhMuc> list) {
        DefaultTableModel model = (DefaultTableModel) tblDanhMuc.getModel();
        tblDanhMuc.setDefaultEditor(Object.class, null);
        model.setRowCount(0);

        for (com.bar.model.DanhMuc dm : list) {
            Object[] row = {
                dm.getMaDanhMuc(),
                dm.getTenDanhMuc()};
            model.addRow(row);
        }

        return model.getRowCount();

    }

    public void setFormDanhMuc(com.bar.model.DanhMuc dm) {
        txtMaDanhMuc.setText(dm.getMaDanhMuc());
        txtTenDanhMuc.setText(dm.getTenDanhMuc());
    }

    public com.bar.model.DanhMuc getFormDanhMuc() {
        com.bar.model.DanhMuc dm = new com.bar.model.DanhMuc();
        dm.setMaDanhMuc(txtMaDanhMuc.getText());
        dm.setTenDanhMuc(txtTenDanhMuc.getText());
        return dm;
    }
    //Update Status comingsoon...

    void clearFormDanhMuc() {
        txtMaDanhMuc.setText("");
        txtTenDanhMuc.setText("");

        this.row_DanhMuc = -1;

        //update status
        this.updateStatus();
    }

    void insertDanhMuc() {
        com.bar.model.DanhMuc dm = getFormDanhMuc();
        try {
            dmdao.insert(dm);
            this.fillTableDanhMuc();
            this.clearFormDanhMuc();
            MsgBox.alert(this, "Thêm mới danh muc thanh cong!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm moi danh muc that bai!");
            e.printStackTrace();
        }
    }

    public String insertDanhMuc(DanhMuc dm) {
        try {
            dmdao.insert(dm);
            this.fillTableDanhMuc();
            this.clearFormDanhMuc();
            return "Thêm mới danh muc thanh cong!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm moi danh muc that bai!";
        }
    }

    void updateDanhMuc() {
        com.bar.model.DanhMuc dm = getFormDanhMuc();
        try {
            dmdao.update(dm);
            this.fillTableDanhMuc();
            MsgBox.alert(this, "Cap Nhat danh muc thanh cong!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cap Nhat danh muc that bai!");
            e.printStackTrace();
        }
    }

    public String updateDanhMuc(DanhMuc dm) {

        try {
            dmdao.update(dm);
            this.fillTableDanhMuc();
//            MsgBox.alert(this, "Cap Nhat danh muc thanh cong!");
            return "Cap Nhat danh muc thanh cong!";
        } catch (Exception e) {
//             MsgBox.alert(this, "Cap Nhat danh muc that bai!");
            return "Cap Nhat danh muc that bai!";
        }
    }

    void deleteDanhMuc() {
        String maDM = txtMaDanhMuc.getText();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa danh muc này?")) {
            try {
                dmdao.delete(maDM);
                this.fillTableDanhMuc();
                this.clearFormDanhMuc();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }

    public String deleteDanhMuc(String maDM) {

        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa danh muc này?")) {
            try {
                dmdao.delete(maDM);
                this.fillTableDanhMuc();
                this.clearFormDanhMuc();
//                MsgBox.alert(this, "Xóa thành công!");
                return "Xóa thành công!";
            } catch (Exception e) {
//                MsgBox.alert(this, "Xóa thất bại!");
                return "Xóa thất bại!";
            }
        }
        return "Xóa thành công!";
    }

    void timDanhMuc() {
        DefaultTableModel model = (DefaultTableModel) tblDanhMuc.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem2.getText();
            if (keyword != null) {
                List<DanhMuc> list = dmdao.selectByKeyword(keyword);
                for (DanhMuc dm : list) {
                    Object[] row = {
                        dm.getMaDanhMuc(),
                        dm.getTenDanhMuc()
                    };
                    model.addRow(row);
                }
                txtTimKiem2.setText("");
                this.updateStatus();
            } else {
                MsgBox.alert(this, "Không tìm thấy Danh Mục");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public String timDanhMuc(String keyword) {
        DefaultTableModel model = (DefaultTableModel) tblDanhMuc.getModel();
        model.setRowCount(0);
        try {

            if (keyword != null) {
                List<DanhMuc> list = dmdao.selectByKeyword(keyword);
                for (DanhMuc dm : list) {
                    Object[] row = {
                        dm.getMaDanhMuc(),
                        dm.getTenDanhMuc()
                    };
                    model.addRow(row);
                }
                txtTimKiem2.setText("");
                this.updateStatus();
                return model.getRowCount() + "";
            } else {
//                MsgBox.alert(this, "Không tìm thấy Danh Mục");
                return "Không tìm thấy Danh Mục";
            }
        } catch (Exception e) {
//            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            return "Lỗi truy vấn dữ liệu!";
        }
    }

    void editDanhMuc() {
        String maDM = (String) tblDanhMuc.getValueAt(this.row_DanhMuc, 0);
        com.bar.model.DanhMuc dm = dmdao.selectID(maDM);
        this.setFormDanhMuc(dm);
        this.updateStatus();
        //tbpNguoiHoc.setSelectedIndex(1); 
    }

    boolean checkForm() {
        String mauMaDM = "[A-Za-z0-9]{1,20}";
        if (txtMaDanhMuc.getText().equals("") || txtTenDanhMuc.getText().equals("")) {
            MsgBox.alert(this, "Hãy nhập đủ dữ liệu sau đó ấn Thêm");
            return false;
        } else if (!txtMaDanhMuc.getText().matches(mauMaDM)) {
            MsgBox.alert(this, "Mã dan mục không chứ kí tự đặt biệt!");
            return false;
        }
        return true;
    }

    public String checkForm(String maDM, String tenDM) {
        String mauMaDM = "[A-Za-z0-9]{1,20}";
        if (maDM.equals("") || tenDM.equals("")) {
//              MsgBox.alert(this, "Hãy nhập đủ dữ liệu sau đó ấn Thêm");
            return "Hãy nhập đủ dữ liệu sau đó ấn Thêm";
        } else if (!txtMaDanhMuc.getText().matches(mauMaDM)) {
//             MsgBox.alert(this, "Mã dan mục không chứ kí tự đặt biệt!");
            return "Mã danh mục không chứ kí tự đặt biệt!";
        }
        return "Hãy nhập đủ dữ liệu sau đó ấn Thêm";
    }

    boolean isUpdate = false;

    public boolean checkDupKey(String maDM) {
        DanhMuc sp = dmdao.selectID(maDM);
        if (sp != null) {
            return true;
        }
        return false;
    }

    void firstDanhMuc() {
        row_DanhMuc = 0;
        tblDanhMuc.setRowSelectionInterval(row_DanhMuc, row_DanhMuc);
        this.editDanhMuc();
    }

    void preDanhMuc() {
        if (row_DanhMuc > 0) {
            row_DanhMuc--;
            tblDanhMuc.setRowSelectionInterval(row_DanhMuc, row_DanhMuc);
            this.editDanhMuc();
        }
    }

    void nextDanhMuc() {
        if (row_DanhMuc < tblDanhMuc.getRowCount() - 1) {
            row_DanhMuc++;
            tblDanhMuc.setRowSelectionInterval(row_DanhMuc, row_DanhMuc);
            this.editDanhMuc();
        }
    }

    void lastDanhMuc() {
        row_DanhMuc = tblDanhMuc.getRowCount() - 1;
        tblDanhMuc.setRowSelectionInterval(row_DanhMuc, row_DanhMuc);
        this.editDanhMuc();
    }

    private void initTable(JTable table) {
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        table.getTableHeader().setForeground(new Color(26, 72, 86));
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        int colunm = table.getColumnCount() - 1;
        for (int i = 0; i <= colunm; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);
        table.setForeground(new Color(71, 102, 102));
        table.setFont(new Font("Segoe UI", Font.BOLD, 16));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTimKiem2 = new com.bar.customUI.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhMuc = new com.bar.customUI.TableWhite_DanhMuc();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaDanhMuc = new com.bar.customUI.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenDanhMuc = new com.bar.customUI.TextField();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1830, 910));
        setPreferredSize(new java.awt.Dimension(1830, 910));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/left.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 750, 140, 70));

        btnPre.setBackground(new java.awt.Color(255, 255, 255));
        btnPre.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/pre.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 750, 140, 70));

        btnNext.setBackground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 750, 140, 70));

        btnLast.setBackground(new java.awt.Color(255, 255, 255));
        btnLast.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/right.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 750, 130, 70));

        btnTim.setBackground(new java.awt.Color(255, 255, 255));
        btnTim.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/search.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 230, 120, 50));

        txtTimKiem2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimKiem2.setLabelText("");
        add(txtTimKiem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 230, 310, 50));

        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Danh Mục", "Tên Danh Mục"
            }
        ));
        tblDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhMuc);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1840, 400));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/wine-tasting.png"))); // NOI18N
        jLabel5.setText(" ");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 70, 130, 130));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/wine.png"))); // NOI18N
        jLabel7.setText(" ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 60, 150, 150));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/shelf.png"))); // NOI18N
        jLabel8.setText(" ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 260, 260));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/shelf.png"))); // NOI18N
        jLabel9.setText(" ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 260, 260));

        jPanel1.setBackground(new java.awt.Color(37, 109, 133));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã Danh Mục:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        txtMaDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMaDanhMuc.setLabelText("");
        jPanel1.add(txtMaDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 320, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên Danh Mục:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        txtTenDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTenDanhMuc.setLabelText("");
        txtTenDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDanhMucActionPerformed(evt);
            }
        });
        jPanel1.add(txtTenDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 320, -1));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 130, 70));

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/update.png"))); // NOI18N
        btnCapNhat.setText("Sửa");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel1.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 120, 70));

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/refresh.png"))); // NOI18N
        btnLamMoi.setText("Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 120, 70));

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/trash.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 130, 70));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 720, 240));
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDanhMucActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        isUpdate = false;
        if (checkDupKey(txtMaDanhMuc.getText()) == false) {
            if (checkForm()) {
                insertDanhMuc();
            }
        } else {
            MsgBox.alert(this, "Trùng mã Danh Mục!");

        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firstDanhMuc();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        preDanhMuc();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        isUpdate = true;
        if (checkForm()) {
            updateDanhMuc();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteDanhMuc();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        nextDanhMuc();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        lastDanhMuc();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFormDanhMuc();
        this.fillTableDanhMuc();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        timDanhMuc();
        this.clearFormDanhMuc();
        this.row_DanhMuc = -1;

    }//GEN-LAST:event_btnTimActionPerformed

    private void tblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseClicked
        if (evt.getClickCount() == 2) {
            this.row_DanhMuc = tblDanhMuc.getSelectedRow();
            this.editDanhMuc();
        }
    }//GEN-LAST:event_tblDanhMucMouseClicked

    private void txtTenDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDanhMucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bar.customUI.TableWhite_DanhMuc tblDanhMuc;
    private com.bar.customUI.TextField txtMaDanhMuc;
    private com.bar.customUI.TextField txtTenDanhMuc;
    private com.bar.customUI.TextField txtTimKiem2;
    // End of variables declaration//GEN-END:variables
}
