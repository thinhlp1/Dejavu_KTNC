/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.view;

import com.bar.dao.DanhMucDAO;
import com.bar.dao.SanPhamDAO;
import com.bar.model.SanPham;
import com.bar.util.MsgBox;
import com.bar.util.XImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Lenovo
 */
public class PNL_SanPham extends javax.swing.JPanel {

    List<com.bar.model.DanhMuc> list;

    /**
     * Creates new form PNL_SanPham
     */
    public PNL_SanPham() {
        initComponents();
        initLoadData();

    }

    SanPhamDAO spdao = new SanPhamDAO();
    int row_SanPham = -1;

    public void initLoadData() {
        setModelTable_SanPham();
        fillComboBoxDanhMuc();
        fillComboBoxDanhMucLoc();
        fillTableSanPham();
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    String getMaDM() {
        return list.get(cbDanhMuc.getSelectedIndex()).getMaDanhMuc();

    }

    public void setModelTable_SanPham() {
        DefaultTableModel modelDanhMuc = new DefaultTableModel(new Object[][]{}, new Object[]{"Ma Mon", "Ten Mon", "Gia", "DanhMuc", "GhiChu"});
        tblSanPham.setModel(modelDanhMuc);
    }

    String getTenDM(String maDM) {
        for (int i = 0; i < list.size(); i++) {
            if (maDM.equalsIgnoreCase(list.get(i).getMaDanhMuc())) {
                return list.get(i).getTenDanhMuc();
            }
        }
        return null;
    }

    String getMaDM(String tenDM) {
        for (int i = 0; i < list.size(); i++) {
            if (tenDM.equalsIgnoreCase(list.get(i).getTenDanhMuc())) {
                return list.get(i).getMaDanhMuc();
            }
        }
        return null;
    }

    void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        tblSanPham.setDefaultEditor(Object.class, null);
        model.setRowCount(0);

        try {
            String keyword = txtTimKiem.getText();
            if (keyword != null) {

                List<com.bar.model.SanPham> list = spdao.selectByKeyword(keyword);
                for (com.bar.model.SanPham sp : list) {
                    //String gia = sp.getGia().toString().length()+"";
                    //int doDaiGia = Integer.parseInt(gia) - 2;
                    Object[] row = {
                        sp.getMaMon(),
                        sp.getTenMon(),
                        toMoneyString(sp.getGia()),
                        getTenDM(sp.getDanhMuc()),
                        //sp.getDanhMuc(),
                        sp.getGhiChu(),
                        sp.getHinh()
                    };
                    model.addRow(row);
                }
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        }
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

    String ChuyenMaDM(String tenDM) {
        dmdao.selectMaDanhMuc(tenDM);
        return tenDM;
    }

     public void updateStatus() {
        boolean edit = (this.row_SanPham >= 0);
        boolean first = (this.row_SanPham == 0);
        boolean last = (this.row_SanPham == tblSanPham.getRowCount() - 1);
        txtMaMon.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnCapNhat.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);

    }

   public void setFormSanPham(com.bar.model.SanPham sp) {
        // String gia = sp.getGia().toString().length()+"";
        // int doDaiGia = Integer.parseInt(gia) - 2;
        txtMaMon.setText(sp.getMaMon());
        txtTenMon.setText(sp.getTenMon());
        txtGia1.setText(toMoneyString(sp.getGia()));

        cbDanhMuc.setSelectedItem(getTenDM(sp.getDanhMuc()));
        // lblAnh.setIcon(ResizeImage(String.valueOf(duongDanAnh)));
        lblAnh.setToolTipText(sp.getHinh());
        if (sp.getHinh() != null) {
            lblAnh.setIcon(ResizeImage(String.valueOf(XImage.read(sp.getHinh()))));
        }
        areaGhiChu1.setText(sp.getGhiChu());

    }

    com.bar.model.SanPham getFormSanPham() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbDanhMuc.getModel();
        com.bar.model.SanPham sp = new com.bar.model.SanPham();
        sp.setMaMon(txtMaMon.getText());
        sp.setTenMon(txtTenMon.getText());
        sp.setGia(toMoneyLong(txtGia1.getText()));
        //sp.setDanhMuc(ChuyenMaDM(cbDanhMuc.getSelectedItem().toString()));
        sp.setDanhMuc(getMaDM());
//        List<com.bar.model.SanPham> list = spdao.select();
//            for (com.bar.model.SanPham cd : list) {
//                model.addElement(cd.getDanhMuc().toString()); 
//            }
        sp.setHinh(lblAnh.getToolTipText());
        sp.setGhiChu(areaGhiChu1.getText());

        return sp;
    }
    //Update Status comingsoon...

    public void clearFormSanPham() {

        txtMaMon.setText("");
        txtTenMon.setText("");
        txtGia1.setText("");
        // cbDanhMuc.setSelectedItem(sp.getDanhMuc());
        areaGhiChu1.setText("");
        //xoa anh
        //ImageIcon icon1 = new ImageIcon("D:\\Hoc_Ky_4\\Block2\\DuAn1\\FileDuAn_MoiNhat\\DuAn1");
        //String anh = "D:\\Hoc_Ky_4\\Block2\\DuAn1\\FileDuAn_MoiNhat\\DuAn1";
        lblAnh.setIcon(null);
        lblAnh.setToolTipText(null);

        this.row_SanPham = -1;
        lblAnh.setText("Chọn Ảnh");
        //update status
        this.updateStatus();
    }
    
    
    public void insertSanPham() {
        com.bar.model.SanPham sp = getFormSanPham();
        if (sp.getHinh() == null) {
            MsgBox.alert(this, "Hình Không Được Để Trống");
            return;
        }
        try {
            spdao.insert(sp);
            this.fillTableSanPham();
            this.clearFormSanPham();
            MsgBox.alert(this, "Thêm mới San Pham thanh cong!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm moi San Pham that bai!");
            e.printStackTrace();
        }
    }
    
    //inser for test
     public String insertSanPham(SanPham sp) {
       
        if (sp.getHinh() == null) {
            //MsgBox.alert(this, "Hình Không Được Để Trống");
            return  "Hình Không Được Để Trống";
        }
        try {
            spdao.insert(sp);
            this.fillTableSanPham();
            this.clearFormSanPham();
//            MsgBox.alert(this, "Thêm mới San Pham thanh cong!");
            return "Thêm mới San Pham thanh cong!";
        } catch (Exception e) {
//            MsgBox.alert(this, "Thêm moi San Pham that bai!");
          
            e.printStackTrace();
               return "Thêm moi San Pham that bai!";
        }
    }

    public void updateSanPham() {
        com.bar.model.SanPham sp = getFormSanPham();
        try {
            spdao.update(sp);
            this.fillTableSanPham();
            MsgBox.alert(this, "Cap Nhat San Pham thanh cong!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cap Nhat San Pham that bai!");
            e.printStackTrace();
        }
    }

    public void deleteSanPham() {
        String maDM = txtMaMon.getText();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa san pham này?")) {
            try {
                spdao.delete(maDM);
                this.fillTableSanPham();
                this.clearFormSanPham();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
                e.printStackTrace();
            }
        }
    }

    public void timSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            if (keyword != null) {
                List<SanPham> list = spdao.selectByKeyword2(keyword);
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getMaMon(),
                        sp.getTenMon(),
                        sp.getGia(),
                        sp.getDanhMuc(),
                        sp.getGhiChu()
                    };
                    model.addRow(row);
                }
                txtTimKiem.setText("");
                this.updateStatus();
            } else {
                MsgBox.alert(this, "Không tìm thấy sản phẩm");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void locSanPham() {
        //if (cbDanhMuc_Loc.getSelectedItem())
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            String keyword = String.valueOf(getMaDM(cbDanhMuc_Loc.getSelectedItem().toString()));
            String tatCa = "Tất Cả";
            if (keyword.equalsIgnoreCase(tatCa)) {
                this.fillTableSanPham();
            } else //if(keyword != null)
            {
                List<SanPham> list = spdao.selectByLoc(keyword);
                for (SanPham sp : list) {
                    Object[] row = {
                        sp.getMaMon(),
                        sp.getTenMon(),
                        toMoneyString(sp.getGia()),
                        getTenDM(sp.getDanhMuc()),
                        //sp.getDanhMuc(),
                        sp.getGhiChu()
                    };
                    model.addRow(row);
                }

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void editSanPham() {
        String maSP = (String) tblSanPham.getValueAt(this.row_SanPham, 0);
        com.bar.model.SanPham sp = spdao.selectID(maSP);
        this.setFormSanPham(sp);
        this.updateStatus();
        //tbpNguoiHoc.setSelectedIndex(1); 
    }

    public void timDanhMuc() {
        this.fillTableSanPham();
        this.clearFormSanPham();
        this.row_SanPham = -1;
        this.updateStatus();
    }

    public void firstDanhMuc() {
        row_SanPham = 0;
        tblSanPham.setRowSelectionInterval(row_SanPham, row_SanPham);
        this.editSanPham();
    }

   public void preDanhMuc() {
        if (row_SanPham > 0) {
            row_SanPham--;
            tblSanPham.setRowSelectionInterval(row_SanPham, row_SanPham);
            this.editSanPham();
        }
    }

    public void nextDanhMuc() {
        if (row_SanPham < tblSanPham.getRowCount() - 1) {
            row_SanPham++;
            tblSanPham.setRowSelectionInterval(row_SanPham, row_SanPham);
            this.editSanPham();
        }
    }

    public void lastDanhMuc() {
        row_SanPham = tblSanPham.getRowCount() - 1;
        tblSanPham.setRowSelectionInterval(row_SanPham, row_SanPham);
        this.editSanPham();
    }

    DanhMucDAO dmdao = new DanhMucDAO();

    public void fillComboBoxDanhMuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbDanhMuc.getModel();
        model.removeAllElements();
        try {
            list = dmdao.select();
            for (com.bar.model.DanhMuc cd : list) {
                model.addElement(cd.getTenDanhMuc());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void fillComboBoxDanhMucLoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbDanhMuc_Loc.getModel();
        //model.removeAllElements();
        try {
            List<com.bar.model.DanhMuc> list = dmdao.select();
            for (com.bar.model.DanhMuc cd : list) {
                model.addElement(cd.getTenDanhMuc());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
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

    JFileChooser filenChooser = new JFileChooser();

    public ImageIcon ResizeImage(String Path) {
        ImageIcon MyImage = new ImageIcon(Path);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void chonAnh() {

        if (filenChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filenChooser.getSelectedFile();
            XImage.save(file);

            ImageIcon iconTam = new ImageIcon(file.getAbsolutePath());
            Image img = iconTam.getImage();
            ImageIcon icon = new ImageIcon(img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH));
            lblAnh.setToolTipText(file.getName());
            lblAnh.setIcon(icon);
            lblAnh.setText("");
        }
    }

    public void docAnh(SanPham sp) {
        ImageIcon iconTam1 = XImage.read(sp.getHinh());
        Image img1 = iconTam1.getImage();
        ImageIcon icon1 = new ImageIcon(img1.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH));
        lblAnh.setIcon(icon1);
        lblAnh.setToolTipText(sp.getHinh());
    }
    boolean isUpdate = false;

    boolean checkDupKey(String maMon) {
        SanPham sp = spdao.selectID(maMon);
        if (sp != null) {
            return true;
        }
        return false;
    }

    boolean checkForm() {
        String mauGia = "[0-9]{1,20}";
        String mauMaMon = "[A-Za-z0-9]{1,20}";
        if (txtMaMon.getText().equals("") || txtTenMon.getText().equals("") || txtGia1.getText().equals("")) {
            MsgBox.alert(this, "Hãy nhập đủ dữ liệu sau đó ấn Thêm");
            return false;
        }

        if (!txtMaMon.getText().matches(mauMaMon)) {
            MsgBox.alert(this, "Mã món không chứ kí tự đặt biệt!");
            return false;

        }
        long giaMoi = 0;
        giaMoi = toMoneyLong(txtGia1.getText());
        String giaNew = String.valueOf(giaMoi);
        if (!giaNew.matches(mauGia)) {
            MsgBox.alert(this, "Giá vui lòng nhập số và lớn hơn 0");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaDM = new com.bar.customUI.TextField();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        txtTimKiem = new com.bar.customUI.TextField();
        cbDanhMuc_Loc = new com.bar.customUI.ComboBoxSuggestion();
        btnTim = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new com.bar.customUI.TableWhite_SanPham();
        jPanel1 = new RoundedPanel(30);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenMon = new com.bar.customUI.TextField();
        txtMaMon = new com.bar.customUI.TextField();
        jLabel4 = new javax.swing.JLabel();
        txtGia1 = new com.bar.customUI.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbDanhMuc = new com.bar.customUI.ComboBoxSuggestion();
        lblAnh = new javax.swing.JLabel();
        textAreaScroll1 = new com.bar.customUI.TextAreaScroll();
        areaGhiChu1 = new com.bar.customUI.TextArea();

        txtMaDM.setBackground(new java.awt.Color(153, 255, 153));
        txtMaDM.setUI(null);
        txtMaDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDMActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1835, 910));
        setPreferredSize(new java.awt.Dimension(1835, 910));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 120, 70));

        btnCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/update.png"))); // NOI18N
        btnCapNhat.setText("Sửa");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 130, 70));

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/trash.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 340, 120, 70));

        btnLamMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/refresh.png"))); // NOI18N
        btnLamMoi.setText("Mới");
        btnLamMoi.setBorder(null);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, 120, 70));

        btnLast.setBackground(new java.awt.Color(255, 255, 255));
        btnLast.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/right.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 790, 120, 60));

        btnNext.setBackground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 790, 120, 60));

        btnPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/pre.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 790, 130, 60));

        btnFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/left.png"))); // NOI18N
        btnFirst.setDefaultCapable(false);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 790, 120, 60));

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 330, 280, 80));

        cbDanhMuc_Loc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));
        cbDanhMuc_Loc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbDanhMuc_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDanhMuc_LocActionPerformed(evt);
            }
        });
        add(cbDanhMuc_Loc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 240, 70));

        btnTim.setBackground(new java.awt.Color(255, 255, 255));
        btnTim.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/search.png"))); // NOI18N
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(1710, 330, 90, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/bar-counter.png"))); // NOI18N
        jLabel7.setText(" ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 20, 260, 260));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/cocktail_1.png"))); // NOI18N
        jLabel8.setText(" ");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 120, 130));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/wine-glass-full-of-drink.png"))); // NOI18N
        jLabel6.setText(" ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 130, 140));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/wine-glasses.png"))); // NOI18N
        jLabel9.setText(" ");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 140));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/champagne (1).png"))); // NOI18N
        jLabel10.setText(" ");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 130, 140));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 1830, 320));

        jPanel1.setOpaque(false);
        jPanel1.setBackground(new java.awt.Color(37, 109, 133));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã Món:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên Món:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtTenMon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel1.add(txtTenMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 300, -1));

        txtMaMon.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel1.add(txtMaMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 300, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giá:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 60, -1));

        txtGia1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtGia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGia1ActionPerformed(evt);
            }
        });
        txtGia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGia1KeyTyped(evt);
            }
        });
        jPanel1.add(txtGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 300, -1));

        jLabel1.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ghi Chú:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Slab ExtraBold", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Danh Mục:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        cbDanhMuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbDanhMucMouseClicked(evt);
            }
        });
        cbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDanhMucActionPerformed(evt);
            }
        });
        jPanel1.add(cbDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 300, 50));

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Chọn Ảnh");
        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblAnh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAnh.setFocusable(false);
        lblAnh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAnh.setOpaque(true);
        lblAnh.setPreferredSize(new java.awt.Dimension(180, 160));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });
        jPanel1.add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 230, 200));

        textAreaScroll1.setLabelText("");

        areaGhiChu1.setColumns(20);
        areaGhiChu1.setRows(5);
        areaGhiChu1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        textAreaScroll1.setViewportView(areaGhiChu1);

        jPanel1.add(textAreaScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 300, 130));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 1210, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMonActionPerformed

    private void txtMaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaMonActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        isUpdate = false;
        if (checkDupKey(txtMaMon.getText()) == false) {
            if (checkForm()) {
                insertSanPham();
            }
        } else {
            MsgBox.alert(this, "Trùng mã món!");

        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        isUpdate = true;
        if (checkForm()) {
            updateSanPham();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteSanPham();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFormSanPham();
        this.fillTableSanPham();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        lastDanhMuc();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        nextDanhMuc();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        preDanhMuc();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firstDanhMuc();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void cbDanhMuc_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDanhMuc_LocActionPerformed
        locSanPham();
        clearFormSanPham();
    }//GEN-LAST:event_cbDanhMuc_LocActionPerformed

    private void txtMaDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDMActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        chonAnh();
    }//GEN-LAST:event_lblAnhMouseClicked

    private void cbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDanhMucActionPerformed
        System.out.println(getMaDM());
    }//GEN-LAST:event_cbDanhMucActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        timSanPham();
        this.clearFormSanPham();
        this.row_SanPham = -1;
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtGia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGia1ActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        if (evt.getClickCount() == 2) {
            this.row_SanPham = tblSanPham.getSelectedRow();
            this.editSanPham();
            lblAnh.setText("");
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cbDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDanhMucMouseClicked

    }//GEN-LAST:event_cbDanhMucMouseClicked

    private void txtGia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGia1KeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9')
                || (c == KeyEvent.VK_BACK_SPACE)
                || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtGia1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bar.customUI.TextArea areaGhiChu1;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private com.bar.customUI.ComboBoxSuggestion cbDanhMuc;
    private com.bar.customUI.ComboBoxSuggestion cbDanhMuc_Loc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private com.bar.customUI.TableWhite_SanPham tblSanPham;
    private com.bar.customUI.TextAreaScroll textAreaScroll1;
    private com.bar.customUI.TextField txtGia1;
    private com.bar.customUI.TextField txtMaDM;
    private com.bar.customUI.TextField txtMaMon;
    private com.bar.customUI.TextField txtTenMon;
    private com.bar.customUI.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
