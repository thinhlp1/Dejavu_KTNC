/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.dao.NhanVienDAO;
import com.bar.dao.TaiKhoanDAO;
import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author PHUOCTAI
 */
public class QL_TaiKhoanNV extends javax.swing.JPanel {

    List<NhanVien> listNV = new ArrayList<NhanVien>();
    List<TaiKhoan> listTK = new ArrayList<TaiKhoan>();
    TaiKhoanDAO tkDao = new TaiKhoanDAO();
    NhanVienDAO nvDao = new NhanVienDAO();
    DefaultTableModel tblModel;
    JTableHeader tableHeader; //Khai báo để định dạng font sz Tilte
    int index = -1;

    public QL_TaiKhoanNV() {
        initComponents();
        initCombobox();
        initTitleTable();
        fillTable();
    }

    void initTitleTable() {
        String[] tittleTaiKhoanNV = {"TÊN TÀI KHOẢN", "HỌ TÊN", "CHỨC VỤ"};
        tblModel = (DefaultTableModel) tblTaiKhoanNV.getModel();
        tblModel.setColumnIdentifiers(tittleTaiKhoanNV);
        tableHeader = tblTaiKhoanNV.getTableHeader();
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 24)); //Set font tiltle table
        tableHeader.setForeground(new Color(36, 41, 71));
        tableHeader.setOpaque(false);
        tableHeader.setBackground(Color.red);

        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblTaiKhoanNV.setFont(new Font("Tahoma", Font.PLAIN, 18));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        tblTaiKhoanNV.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        tblTaiKhoanNV.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tblTaiKhoanNV.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

        tblTaiKhoanNV.setRowHeight(50);  //Set height rows
        tblTaiKhoanNV.getColumnModel().getColumn(0).setPreferredWidth(20); //Set width coulumn
        tblTaiKhoanNV.getColumnModel().getColumn(1).setPreferredWidth(70); //Set width coulumn 
    }

    public void fillTable() {
        tblModel = (DefaultTableModel) tblTaiKhoanNV.getModel();
        tblModel.setRowCount(0);
        listTK = tkDao.select();
        listNV = nvDao.select();
        for (TaiKhoan taiKhoan : listTK) {
            tblModel.addRow(new Object[]{taiKhoan.getTenTaiKhoan(), getFullNameByID(taiKhoan.getMaNV()), getRoleByID(taiKhoan.getMaNV())});
        }
    }

    String getFullNameByID(String id) {
        for (NhanVien nhanVien : listNV) {
            if (id.equals(nhanVien.getMaNV())) {
                return nhanVien.getHoTenLot() + " " + nhanVien.getTenNV();
            }
        }
        return null;
    }

    String getRoleByID(String id) {
        for (NhanVien nhanVien : listNV) {
            if (id.equals(nhanVien.getMaNV())) {
                return nhanVien.getChucVu();
            }
        }
        return null;
    }

    String getUserNameByID(String id) {
        for (TaiKhoan taiKhoan : listTK) {
            if (taiKhoan.getMaNV().equals(id)) {
                return taiKhoan.getTenTaiKhoan();
            }
        }
        return null;
    }

    void showDetail_NhanVien(int index) {
        if (index != -1) {
            cboHoTen.setSelectedItem(tblTaiKhoanNV.getValueAt(index, 1));
            txtTenTaiKhoan.setText(tblTaiKhoanNV.getValueAt(index, 0).toString());
            lblChucVu.setText(tblTaiKhoanNV.getValueAt(index, 2).toString());
            tblTaiKhoanNV.setRowSelectionAllowed(true);
            tblTaiKhoanNV.setRowSelectionInterval(index, index);
        }
    }

    void resetFormNV() {
        cboHoTen.setSelectedIndex(0);
        txtTenTaiKhoan.setText("");
        lblChucVu.setText("Chưa có chức vụ");
        index = -1;
        tblTaiKhoanNV.setRowSelectionAllowed(false);
    }

    void deleteAccount() {
        String userName = txtTenTaiKhoan.getText();
        if (userName.equals(Auth.account.getTenTaiKhoan())) {
            MsgBox.alert(this, "Bạn không được xóa chính bạn!");
        } else if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
            try {
                tkDao.delete(userName);
                this.fillTable();
                this.resetFormNV();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    TaiKhoan getForm() {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(txtTenTaiKhoan.getText());
        taiKhoan.setMaNV(getIDNhanVien());
        return taiKhoan;
    }

    boolean checkNull() {
        if (txtTenTaiKhoan.getText().isEmpty()) {
            MsgBox.alert(this, "Tên tài khoản trống!");
            return false;
        }
        return true;
    }

    boolean checkSelected() {
        if (index == -1) {
            MsgBox.alert(this, "Vui lòng chọn nhân viên cần thao tác!");
            return false;
        }
        return true;
    }

    void updateAccount() {
        TaiKhoan model = getForm();
        if (MsgBox.confirm(this, "Bạn có chắc muốn cập nhật tên tài khoản này không?")) {
            try {
                tkDao.update(model);
                this.fillTable();
                resetFormNV();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    void initCombobox() {
        DefaultComboBoxModel cbomodel = (DefaultComboBoxModel) cboHoTen.getModel();
        cbomodel.removeAllElements();
        try {
            listNV = nvDao.select();
            cbomodel.addElement("Chọn nhân viên");
            for (NhanVien nv : listNV) {
                cbomodel.addElement(nv.getHoTenLot() + " " + nv.getTenNV());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    String getIDNhanVien() {
        int index = cboHoTen.getSelectedIndex() - 1;
        return listNV.get(index).getMaNV();
    }

    String getRoleNhanVien() {
        int index = cboHoTen.getSelectedIndex() - 1;
        return listNV.get(index).getChucVu();
    }

    int getIndex() {
        if (cboHoTen.getSelectedIndex() != 0) {
            for (int i = 0; i < tblTaiKhoanNV.getRowCount(); i++) {
                if (cboHoTen.getSelectedItem().toString().equalsIgnoreCase(tblTaiKhoanNV.getValueAt(i, 1).toString())) {
                    return i;
                }
            }
        }
        return -1;
    }

    void initUsername_by_Combobox() {
        if (cboHoTen.getSelectedIndex() != 0) {
            TaiKhoan tk = tkDao.selectID(getIDNhanVien());
            lblChucVu.setText(getRoleNhanVien());
            index = getIndex();

            if (tk == null) {
                txtTenTaiKhoan.setText("Chưa có tài khoản");
                tblTaiKhoanNV.setRowSelectionAllowed(false);
            } else {
                txtTenTaiKhoan.setText(tk.getTenTaiKhoan());
            }
            showDetail_NhanVien(index);
        } else if (cboHoTen.getSelectedIndex() == 0) {
            resetFormNV();
        }
    }

    void getList_NV_by_Combobox_ChucVu() {
        tblModel = (DefaultTableModel) tblTaiKhoanNV.getModel();
        tblModel.setRowCount(0);
        for (NhanVien nhanVien : listNV) {
            if (cboChucVu.getSelectedItem().toString().equalsIgnoreCase(nhanVien.getChucVu()) && getUserNameByID(nhanVien.getMaNV()) != null) {
                tblModel.addRow(new Object[]{getUserNameByID(nhanVien.getMaNV()), nhanVien.getHoTenLot() + " " + nhanVien.getTenNV(), nhanVien.getChucVu()});
            } else if (cboChucVu.getSelectedIndex() == 0) {
                fillTable();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgrole = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTaiKhoanNV = new javax.swing.JTable();
        BoLoc = new RoundedPanel(50);
        jLabel7 = new javax.swing.JLabel();
        pnlToll = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboHoTen = new com.bar.customUI.ComboBoxSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txtTenTaiKhoan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        cboChucVu = new com.bar.customUI.ComboBoxSuggestion();
        lblChucVu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlTao = new RoundedPanel(20);
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlSua = new RoundedPanel(20);
        pnlSua.setOpaque(false);
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlXoa = new RoundedPanel(20);
        Xóa = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlLamMoi = new RoundedPanel(20);
        pnlLamMoi.setOpaque(false);
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("cardQLNV"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 600));
        setLayout(new java.awt.BorderLayout(2, 0));

        tblTaiKhoanNV.setAutoCreateRowSorter(true);
        tblTaiKhoanNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblTaiKhoanNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
    );
    tblTaiKhoanNV.setAlignmentX(10.0F);
    tblTaiKhoanNV.setAlignmentY(10.0F);
    tblTaiKhoanNV.setFocusable(false);
    tblTaiKhoanNV.setIntercellSpacing(new java.awt.Dimension(0, 0));
    tblTaiKhoanNV.setRowHeight(30);
    tblTaiKhoanNV.setSelectionBackground(new java.awt.Color(37, 109, 133));
    tblTaiKhoanNV.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tblTaiKhoanNV.setShowVerticalLines(false);
    tblTaiKhoanNV.getTableHeader().setResizingAllowed(false);
    tblTaiKhoanNV.getTableHeader().setReorderingAllowed(false);
    tblTaiKhoanNV.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblTaiKhoanNVMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblTaiKhoanNV);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);

    BoLoc.setOpaque(false);
    BoLoc.setBackground(new java.awt.Color(37, 109, 133));
    BoLoc.setRequestFocusEnabled(false);

    jLabel7.setBackground(new java.awt.Color(0, 0, 0));
    jLabel7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel7.setText("THÔNG TIN TÀI KHOẢN");

    pnlToll.setBackground(new java.awt.Color(40, 40, 40));
    pnlToll.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setBackground(new java.awt.Color(0, 0, 0));
    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Họ tên");

    cboHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    cboHoTen.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboHoTenActionPerformed(evt);
        }
    });

    jLabel2.setBackground(new java.awt.Color(0, 0, 0));
    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Tên tài khoản");

    txtTenTaiKhoan.setBackground(new java.awt.Color(246, 246, 254));
    txtTenTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
    txtTenTaiKhoan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtTenTaiKhoan.setToolTipText("");
    txtTenTaiKhoan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

    jLabel6.setBackground(new java.awt.Color(0, 0, 0));
    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("Lọc chức vụ");

    jPanel6.setBackground(new java.awt.Color(40, 40, 40));
    jPanel6.setOpaque(false);
    jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

    btnFirst.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
    btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/first_white.png"))); // NOI18N
    btnFirst.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    btnFirst.setContentAreaFilled(false);
    btnFirst.setMargin(new java.awt.Insets(2, 10, 2, 10));
    btnFirst.setMaximumSize(new java.awt.Dimension(70, 50));
    btnFirst.setMinimumSize(new java.awt.Dimension(70, 50));
    btnFirst.setPreferredSize(new java.awt.Dimension(90, 70));
    btnFirst.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFirstActionPerformed(evt);
        }
    });
    jPanel6.add(btnFirst);

    btnPrev.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
    btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/prev_white.png"))); // NOI18N
    btnPrev.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    btnPrev.setContentAreaFilled(false);
    btnPrev.setMargin(new java.awt.Insets(2, 10, 2, 10));
    btnPrev.setMaximumSize(new java.awt.Dimension(70, 50));
    btnPrev.setMinimumSize(new java.awt.Dimension(70, 50));
    btnPrev.setPreferredSize(new java.awt.Dimension(90, 70));
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnPrevActionPerformed(evt);
        }
    });
    jPanel6.add(btnPrev);

    btnNext.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
    btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/next_white.png"))); // NOI18N
    btnNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    btnNext.setContentAreaFilled(false);
    btnNext.setMargin(new java.awt.Insets(2, 10, 2, 10));
    btnNext.setMaximumSize(new java.awt.Dimension(70, 50));
    btnNext.setMinimumSize(new java.awt.Dimension(70, 50));
    btnNext.setPreferredSize(new java.awt.Dimension(90, 70));
    btnNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnNextActionPerformed(evt);
        }
    });
    jPanel6.add(btnNext);

    btnLast.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
    btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/last_white.png"))); // NOI18N
    btnLast.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    btnLast.setContentAreaFilled(false);
    btnLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnLast.setMargin(new java.awt.Insets(2, 10, 2, 10));
    btnLast.setMaximumSize(new java.awt.Dimension(70, 50));
    btnLast.setMinimumSize(new java.awt.Dimension(70, 50));
    btnLast.setPreferredSize(new java.awt.Dimension(90, 70));
    btnLast.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLastActionPerformed(evt);
        }
    });
    jPanel6.add(btnLast);

    cboChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chọn chức vụ", "Nhân Viên", "Quản Lý" }));
    cboChucVu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    cboChucVu.setPreferredSize(new java.awt.Dimension(175, 34));
    cboChucVu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboChucVuActionPerformed(evt);
        }
    });

    lblChucVu.setBackground(new java.awt.Color(246, 246, 254));
    lblChucVu.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
    lblChucVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblChucVu.setText("Chưa có chức vụ");
    lblChucVu.setOpaque(true);

    jLabel4.setBackground(new java.awt.Color(0, 0, 0));
    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText("Chức vụ");

    jPanel1.setBackground(new java.awt.Color(40, 40, 40));
    jPanel1.setOpaque(false);
    jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

    pnlTao.setOpaque(false);
    pnlTao.setMinimumSize(new java.awt.Dimension(100, 50));
    pnlTao.setPreferredSize(new java.awt.Dimension(110, 80));
    pnlTao.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            pnlTaoMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            pnlTaoMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            pnlTaoMouseExited(evt);
        }
    });
    pnlTao.setLayout(new java.awt.BorderLayout());

    jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(6, 40, 61));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("Tạo");
    pnlTao.add(jLabel3, java.awt.BorderLayout.CENTER);

    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/add_account.png"))); // NOI18N
    jLabel5.setPreferredSize(new java.awt.Dimension(110, 40));
    pnlTao.add(jLabel5, java.awt.BorderLayout.PAGE_START);

    jPanel1.add(pnlTao);

    pnlSua.setMinimumSize(new java.awt.Dimension(100, 50));
    pnlSua.setPreferredSize(new java.awt.Dimension(110, 80));
    pnlSua.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            pnlSuaMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            pnlSuaMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            pnlSuaMouseExited(evt);
        }
    });
    pnlSua.setLayout(new java.awt.BorderLayout());

    jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(6, 40, 61));
    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Sửa");
    pnlSua.add(jLabel8, java.awt.BorderLayout.CENTER);

    jLabel9.setForeground(new java.awt.Color(255, 255, 255));
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/edit_account.png"))); // NOI18N
    jLabel9.setPreferredSize(new java.awt.Dimension(110, 40));
    pnlSua.add(jLabel9, java.awt.BorderLayout.PAGE_START);

    jPanel1.add(pnlSua);

    pnlXoa.setOpaque(false);
    pnlXoa.setMinimumSize(new java.awt.Dimension(100, 50));
    pnlXoa.setPreferredSize(new java.awt.Dimension(110, 80));
    pnlXoa.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            pnlXoaMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            pnlXoaMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            pnlXoaMouseExited(evt);
        }
    });
    pnlXoa.setLayout(new java.awt.BorderLayout());

    Xóa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    Xóa.setForeground(new java.awt.Color(6, 40, 61));
    Xóa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Xóa.setText("Xóa");
    pnlXoa.add(Xóa, java.awt.BorderLayout.CENTER);

    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/delete_account.png"))); // NOI18N
    jLabel11.setPreferredSize(new java.awt.Dimension(110, 40));
    pnlXoa.add(jLabel11, java.awt.BorderLayout.PAGE_START);

    jPanel1.add(pnlXoa);

    pnlLamMoi.setMinimumSize(new java.awt.Dimension(100, 50));
    pnlLamMoi.setPreferredSize(new java.awt.Dimension(110, 80));
    pnlLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            pnlLamMoiMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            pnlLamMoiMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            pnlLamMoiMouseExited(evt);
        }
    });
    pnlLamMoi.setLayout(new java.awt.BorderLayout());

    jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel12.setForeground(new java.awt.Color(6, 40, 61));
    jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel12.setText("Làm mới");
    pnlLamMoi.add(jLabel12, java.awt.BorderLayout.CENTER);

    jLabel13.setForeground(new java.awt.Color(255, 255, 255));
    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/refresh_account.png"))); // NOI18N
    jLabel13.setPreferredSize(new java.awt.Dimension(110, 40));
    pnlLamMoi.add(jLabel13, java.awt.BorderLayout.PAGE_START);

    jPanel1.add(pnlLamMoi);

    javax.swing.GroupLayout BoLocLayout = new javax.swing.GroupLayout(BoLoc);
    BoLoc.setLayout(BoLocLayout);
    BoLocLayout.setHorizontalGroup(
        BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(BoLocLayout.createSequentialGroup()
            .addGap(53, 53, 53)
            .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTenTaiKhoan)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BoLocLayout.createSequentialGroup()
                    .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(BoLocLayout.createSequentialGroup()
                    .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGap(51, 51, 51))
    );
    BoLocLayout.setVerticalGroup(
        BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BoLocLayout.createSequentialGroup()
            .addGap(38, 38, 38)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(39, 39, 39)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(54, 54, 54)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    add(BoLoc, java.awt.BorderLayout.LINE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void cboHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHoTenActionPerformed
        initUsername_by_Combobox();
        System.out.println(getIndex());
    }//GEN-LAST:event_cboHoTenActionPerformed

    private void tblTaiKhoanNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanNVMouseClicked
        index = tblTaiKhoanNV.getSelectedRow();
        showDetail_NhanVien(index);
//        System.out.println(index);
    }//GEN-LAST:event_tblTaiKhoanNVMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        index = 0;
        showDetail_NhanVien(index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        index--;
        if (index < 0) {
            index = tblTaiKhoanNV.getRowCount() - 1;
        }
        showDetail_NhanVien(index);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        index++;
        if (index > tblTaiKhoanNV.getRowCount() - 1) {
            index = 0;
        }
        showDetail_NhanVien(index);
    }//GEN-LAST:event_btnNextActionPerformed


    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        index = tblTaiKhoanNV.getRowCount() - 1;
        showDetail_NhanVien(index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        resetFormNV();
        getList_NV_by_Combobox_ChucVu();
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void pnlTaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoMouseClicked
        new Register().setVisible(true);
    }//GEN-LAST:event_pnlTaoMouseClicked

    private void pnlSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSuaMouseClicked
        if (checkSelected()) {
            if (checkNull()) {
                updateAccount();
            }
        }
    }//GEN-LAST:event_pnlSuaMouseClicked

    private void pnlXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXoaMouseClicked
        if (checkSelected()) {
            deleteAccount();
        }
    }//GEN-LAST:event_pnlXoaMouseClicked

    private void pnlLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLamMoiMouseClicked
        resetFormNV();
        fillTable();
    }//GEN-LAST:event_pnlLamMoiMouseClicked

    private void pnlTaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoMouseEntered
        mouseEntered(pnlTao);
    }//GEN-LAST:event_pnlTaoMouseEntered

    private void pnlTaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaoMouseExited
        mouseEixted(pnlTao);
    }//GEN-LAST:event_pnlTaoMouseExited

    private void pnlSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSuaMouseEntered
        mouseEntered(pnlSua);
    }//GEN-LAST:event_pnlSuaMouseEntered

    private void pnlSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSuaMouseExited
        mouseEixted(pnlSua);
    }//GEN-LAST:event_pnlSuaMouseExited

    private void pnlXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXoaMouseEntered
       mouseEntered(pnlXoa);
    }//GEN-LAST:event_pnlXoaMouseEntered

    private void pnlXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlXoaMouseExited
        mouseEixted(pnlXoa);
    }//GEN-LAST:event_pnlXoaMouseExited

    private void pnlLamMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLamMoiMouseEntered
        mouseEntered(pnlLamMoi);
    }//GEN-LAST:event_pnlLamMoiMouseEntered

    private void pnlLamMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLamMoiMouseExited
        mouseEixted(pnlLamMoi);
    }//GEN-LAST:event_pnlLamMoiMouseExited

    void mouseEntered(JPanel pnl) {
        pnl.setBackground(new Color(223, 246, 255));
    }

    void mouseEixted(JPanel pnl) {
        pnl.setBackground(new Color(240,240,240));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoLoc;
    private javax.swing.JLabel Xóa;
    private javax.swing.ButtonGroup btgrole;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private com.bar.customUI.ComboBoxSuggestion cboChucVu;
    private com.bar.customUI.ComboBoxSuggestion cboHoTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JPanel pnlLamMoi;
    private javax.swing.JPanel pnlSua;
    private javax.swing.JPanel pnlTao;
    private javax.swing.JPanel pnlToll;
    private javax.swing.JPanel pnlXoa;
    private javax.swing.JTable tblTaiKhoanNV;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
