/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bar.view;

import com.bar.dao.HoaDonChiTietDAO;
import com.bar.dao.HoaDonDAO;
import com.bar.dao.NhanVienDAO;
import com.bar.model.HoaDon;
import com.bar.model.HoaDonChiTiet;
import com.bar.model.NhanVien;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import com.bar.util.JdbcHelper;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nguye
 */
public class QL_HoaDon extends javax.swing.JPanel {

    HoaDonDAO hd = new HoaDonDAO();
    HoaDonChiTietDAO hdct = new HoaDonChiTietDAO();
    NhanVienDAO nv = new NhanVienDAO();
    List<HoaDon> listHD = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<HoaDonChiTiet>();
    List<NhanVien> listNV = nv.select();
    List listTenNV = new ArrayList();
    int indexTable = -1;

    DefaultTableModel tblModelHD;
    DefaultTableModel tblModelHDCT;

    public QL_HoaDon() {
        initComponents();
        if (Auth.user != null) {
            if (Auth.user.getChucVu().equalsIgnoreCase("Nhân Viên")) {
                lblXoa.setVisible(false);
            }
        }

        initTitleTableHD();
        initTitleTableHDCT();
        listHD = hd.select();
        listHDCT = hdct.select();
        fillTableHD();
        fillCBO_TenNV();
        fillCBO_TrangThaiThongKe();
        txtBoLoc_Ngay.setText("");
        documentChangedState_TextFieldDate();
        lblXuatHD.setEnabled(false);
    }

    private void initTitleTableHD() {
        String[] columnHD = {"Mã hóa đơn", "Ngày lập", "Trạng thái thanh toán", "Mã nhân viên", "Trạng thái thống kê", "Ghi Chú", "Tổng tiền"};
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setColumnIdentifiers(columnHD);
    }

    private void initTitleTableHDCT() {
        String[] columnHDCT = {"Mã hóa đơn chi tiết", "Tên sản phẩm", "Số lượng", "Đơn giá", "Mã hóa đơn", "Mã sản phẩm"};
        tblModelHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        tblModelHDCT.setColumnIdentifiers(columnHDCT);
    }

    private void fillTableHD() {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        for (HoaDon hoaDon : listHD) {

            String ngay = hoaDon.getNgayLapHD();
            String[] chuoiNam = ngay.split("-");
            String nam = chuoiNam[0];
            String thang = chuoiNam[1];
            String Ngay = chuoiNam[2];
            String ngayHienTai = Ngay + "-" + thang + "-" + nam;

            tblModelHD.addRow(new Object[]{
                hoaDon.getMaHD(),
                ngayHienTai,
                hoaDon.getTrangThaiThanhToan(),
                hoaDon.getMaNV(),
                hoaDon.isTrangThai_ThongKe() ? "Bình thường" : "Hủy",
                hoaDon.getGhiChu(),
                toMoneyString(hoaDon.getTongTien())

            });
        }
        tblModelHD.fireTableDataChanged();
    }

    private void fillTableHDCT() {
        tblModelHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        tblModelHDCT.setRowCount(0);
        for (HoaDonChiTiet hdct : listHDCT) {
            tblModelHDCT.addRow(new Object[]{
                hdct.getMaHDCT(),
                hdct.getTenSP(),
                hdct.getSoLuong(),
                hdct.getDonGia(),
                hdct.getMaHD(),
                hdct.getMaSP()
            });
        }
        tblModelHDCT.fireTableDataChanged();
    }

    private void fillCBO_TenNV() {
        cboTenNV.addItem("<Chọn tên nhân viên>");
        for (NhanVien nhanVien : listNV) {
            cboTenNV.addItem(nhanVien);
        }
    }

    private void fillCBO_TrangThaiThongKe() {
        cboTrangThaiThongKe.addItem("<Chọn trạng thái>");
        cboTrangThaiThongKe.addItem("Bình thường");
        cboTrangThaiThongKe.addItem("Hủy");
    }

    String getIDNV() {
        if (cboTenNV.getSelectedIndex() != 0) {
            return ((NhanVien) cboTenNV.getSelectedItem()).getMaNV();
        }
        return null;
    }

    int getTrangThaiThongKe() {
        if (cboTrangThaiThongKe.getSelectedIndex() > 0) {
            if (cboTrangThaiThongKe.getSelectedItem().toString().equalsIgnoreCase("Bình thường")) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    void search_TenNV() {

        listHD = hd.select();
        try {
            if (cboTenNV.getSelectedIndex() > 0) {
                String maNV = getIDNV();
                listHD = hd.selectBY_IDNV(maNV);
                fillTableHD();
            } else if (cboTenNV.getSelectedIndex() == 0) {
                listHD = hd.select();
                fillTableHD();
            }
        } catch (IndexOutOfBoundsException e) {
            MsgBox.alert(this, "Không có nhân viên!");
        }
    }

    void search_TrangThaiThongKe() {
        if (getTrangThaiThongKe() != -1) {
            if (getTrangThaiThongKe() == 1) {
                listHD = hd.selectBY_TrangThaiThongKe(1);
                fillTableHD();
            } else if (getTrangThaiThongKe() == 0) {
                listHD = hd.selectBY_TrangThaiThongKe(0);
                fillTableHD();
            }
        } else if (cboTrangThaiThongKe.getSelectedIndex() == 0) {
            listHD = hd.select();
            fillTableHD();
        }
    }

    void documentChangedState_TextFieldDate() {
        txtBoLoc_Ngay.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search_Ngay();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                search_Ngay();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
//                search_Ngay();
            }

            public void search_Ngay() {
                List<HoaDon> listNgay = new ArrayList<HoaDon>();
                listHD = hd.select();
                //  listHD.clear();
                SelectedDate ngay = dateChooser_BoLoc_Ngay.getSelectedDate();
                String soThuTu = null;
                if (ngay.getDay() < 10) {
                    soThuTu = "0";
                } else {
                    soThuTu = "";
                }

                txtBoLoc_Ngay.setToolTipText(soThuTu + ngay.getDay() + "-" + ngay.getMonth() + "-" + ngay.getYear());
                listHD = hd.selectBY_NgayLapHoaDon(ngay.getDay() + "", ngay.getMonth() + "", ngay.getYear() + "");
                System.out.println(listHD.size());

                if (listHD.size() > 0) {
                    fillTableHD();
                } else {
                    listHD = hd.select();
                    fillTableHD();
                }

            }
        });
    }

    HoaDon getFormHoaDon() {
        HoaDon hd = new HoaDon();
        int row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 0);
        hd.setMaHD(maHD);
        return hd;
    }

    void huyHoaDon() {
        HoaDon hoaDon = getFormHoaDon();
        hd.update(hoaDon);
        listHD = hd.select();
        fillTableHD();
    }

    void tinhTongTien() {
        int row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 0);
        List<HoaDonChiTiet> listTongTien = hdct.selectBy_MaHD(maHD);
        int sum = 0;
        for (HoaDonChiTiet hdct : listTongTien) {
            int soLuong = hdct.getSoLuong();
            long donGia = hdct.getDonGia();
            long tien = soLuong * donGia;
            sum += tien;
        }
        txtTongTien.setText("Tổng tiền : " + toMoneyString(sum) + " VND");
    }

    void xuatHoaDon(String maHD) {
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/com/bar/view/rptHoaDon.jrxml");
            map.put("MaHD", maHD);
            Connection conn = DriverManager.getConnection(JdbcHelper.getDburl(), JdbcHelper.getUsername(), JdbcHelper.getPassword());
            JasperPrint p = JasperFillManager.fillReport(report, map, conn);
            JasperViewer.viewReport(p, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    private void lblMouseEnter(JLabel lbl) {
        lbl.setBackground(new Color(188, 39, 73));
        lbl.setOpaque(true);
    }

    private void lblMouseExit(JLabel lbl) {
        lbl.setBackground(new Color(37, 109, 133));
        lbl.setOpaque(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser_NgayLapHD = new com.raven.datechooser.DateChooser();
        dateChooser_BoLoc_Ngay = new com.raven.datechooser.DateChooser();
        pnlHoaDonChiTiet = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new com.bar.customUI.TableWhite();
        cboTenNV = new com.bar.customUI.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new com.bar.customUI.TableWhite();
        jLabel15 = new javax.swing.JLabel();
        txtBoLoc_Ngay = new com.bar.customUI.TextField();
        jLabel16 = new javax.swing.JLabel();
        cboTrangThaiThongKe = new com.bar.customUI.ComboBoxSuggestion();
        lblXuatHD = new javax.swing.JLabel();
        lblFirst = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblLast = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        lblXoa = new javax.swing.JLabel();

        dateChooser_BoLoc_Ngay.setTextRefernce(txtBoLoc_Ngay);
        dateChooser_BoLoc_Ngay.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                dateChooser_BoLoc_NgayCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1835, 910));
        setPreferredSize(new java.awt.Dimension(1835, 910));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHoaDonChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDonChiTiet.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHoaDonChiTiet.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblHoaDonChiTiet.setRowHeight(30);
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout pnlHoaDonChiTietLayout = new javax.swing.GroupLayout(pnlHoaDonChiTiet);
        pnlHoaDonChiTiet.setLayout(pnlHoaDonChiTietLayout);
        pnlHoaDonChiTietLayout.setHorizontalGroup(
            pnlHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1656, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHoaDonChiTietLayout.setVerticalGroup(
            pnlHoaDonChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlHoaDonChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 560, 1690, 290));

        cboTenNV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cboTenNV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenNVItemStateChanged(evt);
            }
        });
        cboTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenNVActionPerformed(evt);
            }
        });
        add(cboTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 170, 44));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Tên nhân viên");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblHoaDon.setRowHeight(30);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1666, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnlHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 1700, 300));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setText("Ngày");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        txtBoLoc_Ngay.setToolTipText("");
        txtBoLoc_Ngay.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtBoLoc_Ngay.setLabelText("");
        txtBoLoc_Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBoLoc_NgayActionPerformed(evt);
            }
        });
        add(txtBoLoc_Ngay, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 190, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Trạng thái thống kê");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        cboTrangThaiThongKe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cboTrangThaiThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiThongKeActionPerformed(evt);
            }
        });
        add(cboTrangThaiThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 160, 46));

        lblXuatHD.setBackground(new java.awt.Color(37, 109, 133));
        lblXuatHD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblXuatHD.setForeground(new java.awt.Color(255, 255, 255));
        lblXuatHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXuatHD.setText("Xuất hóa đơn");
        lblXuatHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblXuatHD.setOpaque(true);
        lblXuatHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXuatHDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXuatHDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXuatHDMouseExited(evt);
            }
        });
        add(lblXuatHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 460, 130, 70));

        lblFirst.setBackground(new java.awt.Color(255, 255, 255));
        lblFirst.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFirst.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFirst.setText("|<");
        lblFirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFirstMouseClicked(evt);
            }
        });
        add(lblFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, 110, 80));

        lblBack.setBackground(new java.awt.Color(255, 255, 255));
        lblBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBack.setText("<<");
        lblBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, 110, 80));

        lblNext.setBackground(new java.awt.Color(255, 255, 255));
        lblNext.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNext.setText(">>");
        lblNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
        });
        add(lblNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 110, 80));

        lblLast.setBackground(new java.awt.Color(255, 255, 255));
        lblLast.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblLast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLast.setText(">|");
        lblLast.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLastMouseClicked(evt);
            }
        });
        add(lblLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 450, 110, 80));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtTongTien.setText("Tổng tiền :");
        add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 850, 400, 60));

        lblXoa.setBackground(new java.awt.Color(37, 109, 133));
        lblXoa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblXoa.setForeground(new java.awt.Color(255, 255, 255));
        lblXoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXoa.setText("Hủy hóa đơn");
        lblXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblXoa.setOpaque(true);
        lblXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXoaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXoaMouseExited(evt);
            }
        });
        add(lblXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 460, 150, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void lblXuatHDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHDMouseExited
        lblMouseExit(lblXuatHD);
    }//GEN-LAST:event_lblXuatHDMouseExited

    private void lblXuatHDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHDMouseEntered
        lblMouseEnter(lblXuatHD);
    }//GEN-LAST:event_lblXuatHDMouseEntered

    private void cboTenNVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenNVItemStateChanged

    }//GEN-LAST:event_cboTenNVItemStateChanged

    private void cboTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenNVActionPerformed
        search_TenNV();
    }//GEN-LAST:event_cboTenNVActionPerformed

    private void cboTrangThaiThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiThongKeActionPerformed
        search_TrangThaiThongKe();
    }//GEN-LAST:event_cboTrangThaiThongKeActionPerformed

    private void dateChooser_BoLoc_NgayCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dateChooser_BoLoc_NgayCaretPositionChanged
    }//GEN-LAST:event_dateChooser_BoLoc_NgayCaretPositionChanged

    private void txtBoLoc_NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBoLoc_NgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBoLoc_NgayActionPerformed

    private void lblXuatHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXuatHDMouseClicked
       int row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 0);
        xuatHoaDon(maHD);
    }//GEN-LAST:event_lblXuatHDMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        lblXuatHD.setEnabled(true);
        int row = tblHoaDon.getSelectedRow();
        String maHD = (String) tblHoaDon.getValueAt(row, 0);
        List<HoaDonChiTiet> listMaHD = new ArrayList<HoaDonChiTiet>();
        listMaHD = hdct.selectBy_MaHD(maHD);
        tblModelHDCT = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        tblModelHDCT.setRowCount(0);
        for (HoaDonChiTiet hoaDonChiTiet : listMaHD) {
            tblModelHDCT.addRow(new Object[]{
                hoaDonChiTiet.getMaHDCT(),
                hoaDonChiTiet.getTenSP(),
                hoaDonChiTiet.getSoLuong(),
                toMoneyString(hoaDonChiTiet.getDonGia()),
                hoaDonChiTiet.getMaHD(),
                hoaDonChiTiet.getMaSP()
            });
        }
        tblModelHDCT.fireTableDataChanged();
        tinhTongTien();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void lblFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFirstMouseClicked
        indexTable = 0;
        lblXuatHD.setEnabled(true);
        tblHoaDon.setRowSelectionInterval(indexTable, indexTable);
    }//GEN-LAST:event_lblFirstMouseClicked

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        indexTable--;
        if (indexTable < 0) {
            indexTable = tblHoaDon.getRowCount() - 1;
        }
        lblXuatHD.setEnabled(true);
        tblHoaDon.setRowSelectionInterval(indexTable, indexTable);
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        indexTable++;
        if (indexTable > tblHoaDon.getRowCount() - 1) {
            indexTable = 0;
        }
        lblXuatHD.setEnabled(true);
        tblHoaDon.setRowSelectionInterval(indexTable, indexTable);
    }//GEN-LAST:event_lblNextMouseClicked

    private void lblLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLastMouseClicked
        indexTable = tblHoaDon.getRowCount() - 1;
        lblXuatHD.setEnabled(true);
        tblHoaDon.setRowSelectionInterval(indexTable, indexTable);
    }//GEN-LAST:event_lblLastMouseClicked

    private void lblXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXoaMouseClicked
        huyHoaDon();
    }//GEN-LAST:event_lblXoaMouseClicked

    private void lblXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXoaMouseEntered
        lblMouseEnter(lblXoa);
    }//GEN-LAST:event_lblXoaMouseEntered

    private void lblXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXoaMouseExited
        lblMouseExit(lblXoa);
    }//GEN-LAST:event_lblXoaMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bar.customUI.ComboBoxSuggestion cboTenNV;
    private com.bar.customUI.ComboBoxSuggestion cboTrangThaiThongKe;
    private com.raven.datechooser.DateChooser dateChooser_BoLoc_Ngay;
    private com.raven.datechooser.DateChooser dateChooser_NgayLapHD;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblFirst;
    private javax.swing.JLabel lblLast;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblXoa;
    private javax.swing.JLabel lblXuatHD;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlHoaDonChiTiet;
    private com.bar.customUI.TableWhite tblHoaDon;
    private com.bar.customUI.TableWhite tblHoaDonChiTiet;
    private com.bar.customUI.TextField txtBoLoc_Ngay;
    private javax.swing.JLabel txtTongTien;
    // End of variables declaration//GEN-END:variables
}
