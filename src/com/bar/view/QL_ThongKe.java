package com.bar.view;

import com.bar.dao.ThongKeDao;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author PC
 */
public class QL_ThongKe extends javax.swing.JPanel {

    /**
     * Creates new form QL_ThongKe
     */
    public QL_ThongKe() {
        initComponents();
       if (Auth.user != null) {
            if (Auth.user.getChucVu().equalsIgnoreCase("Nhân Viên")) {
                 materialTabbed1.remove(ThongKeTheoNam);    
                 materialTabbed1.remove(ThongKeTheoThang);
              }
        }
    }

    ThongKeDao thongKeDao = new ThongKeDao();

    private void fillTableThongKeNgay() {
        DefaultTableModel model = (DefaultTableModel) tblDate.getModel();
        //       model.setRowCount(0);
        if (txtDate.getText() != null) {
            List<Object[]> list = thongKeDao.getThongKeTheoNgay(txtDate.getText());
            if (list.isEmpty()) {
                MsgBox.alert(this, "Ngày này chưa có hóa đơn!");
            }
            list.forEach((row) -> {
                model.addRow(row);

            });
        }
    }

    private void fillTableThongKeThang() {
        DefaultTableModel model = (DefaultTableModel) tblMonth.getModel();
        //       model.setRowCount(0);
        if (txtMonth.getText() != null) {

            List<Object[]> list = thongKeDao.getThongKeTheoThang(txtMonth.getText());
            if (list.isEmpty()) {
                MsgBox.alert(this, "Tháng này chưa có hóa đơn!");
            }
            list.forEach((row) -> {
                model.addRow(row);

            });
        }
    }

    private void fillTableThongKeNam() {
        DefaultTableModel model = (DefaultTableModel) tblYear.getModel();
        //      model.setRowCount(0);
        if (txtYear.getText() != null) {
            List<Object[]> list = thongKeDao.getThongKeTheoNam(txtYear.getText());
            if (list.isEmpty()) {
                MsgBox.alert(this, "Năm này chưa có hóa đơn!");
            }
            list.forEach((row) -> {
                model.addRow(row);
            });
        }
    }

    public void printExcelNgay() {
        if (tblDate.getSelectedRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất file Excel!");
            return;
        } else {
            JFileChooser fc = new JFileChooser("D:\\");
            fc.setDialogTitle("Doanh Thu");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            fc.setFileFilter(filter);
            int choose = fc.showSaveDialog(null);
            System.out.println("Vui lòng chờ...");

            if (choose == JFileChooser.APPROVE_OPTION) {
                try {
                    XSSFWorkbook wb = new XSSFWorkbook();
                    XSSFSheet sheet = wb.createSheet("Doanh Thu");
                    XSSFRow row = null;
                    Cell cell = null;

                    row = sheet.createRow(3);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue("Ngày");
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue("Doanh thu");
                    BigDecimal total = BigDecimal.ZERO;
                    if (tblDate != null) {
                        for (int i = 0; i < tblDate.getRowCount(); i++) {
                            row = sheet.createRow(4 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue((String) tblDate.getValueAt(i, 0));

                            final BigDecimal amount = (BigDecimal) tblDate.getValueAt(i, 1);
                            total = total.add(amount);
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(total.toString());
                        }
                    }

                    FileOutputStream fos1 = new FileOutputStream(fc.getSelectedFile() + ".xlsx");
                    wb.write(fos1);
                    MsgBox.alert(this, "Đã xuất file Excel thành công!");
                    fos1.close();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

//    public void PrintExcelNgay() {
//
//        FileOutputStream fis = null;
//        try {
//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet sheet = wb.createSheet("Doanh Thu");
//            XSSFRow row = null;
//            Cell cell = null;
//            row = sheet.createRow(3);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("Ngày");
//            cell = row.createCell(1, CellType.STRING);
//            cell.setCellValue("Doanh thu");
//            BigDecimal total = BigDecimal.ZERO;
//            if (tblDate != null) {
//                for (int i = 0; i < tblDate.getRowCount(); i++) {
//                    row = sheet.createRow(4 + i);
//
//                    cell = row.createCell(0, CellType.STRING);
//                    cell.setCellValue((String) tblDate.getValueAt(i, 0));
//
//                    final BigDecimal amount = (BigDecimal) tblDate.getValueAt(i, 1);
//                    total = total.add(amount);
//                    cell = row.createCell(1, CellType.STRING);
//                    cell.setCellValue(total.toString());
//                }
//            }
//
//            File f = new File("D://doanhthua.xlsx");
//            fis = new FileOutputStream(f);
//            wb.write(fis);
//            MsgBox.alert(this, "Đã xuất file Excel thành công!");
//            fis.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
// }
    public void printExcelThang() {
        if (tblMonth.getSelectedRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất file Excel!");
            return;
        } else {
            JFileChooser fc = new JFileChooser("D:\\");
            fc.setDialogTitle("Doanh Thu");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            fc.setFileFilter(filter);
            int choose = fc.showSaveDialog(null);
            System.out.println("Vui lòng chờ...");
            if (choose == JFileChooser.APPROVE_OPTION) {
                try {

                    XSSFWorkbook wb = new XSSFWorkbook();
                    XSSFSheet sheet = wb.createSheet("Doanh Thu");
                    XSSFRow row = null;
                    Cell cell = null;
                    row = sheet.createRow(3);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue("Ngày");
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue("Doanh thu");
                    BigDecimal total = BigDecimal.ZERO;
                    if (tblMonth != null) {
                        for (int i = 0; i < tblMonth.getRowCount(); i++) {
                            row = sheet.createRow(4 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue((String) tblMonth.getValueAt(i, 0));

                            final BigDecimal amount = (BigDecimal) tblMonth.getValueAt(i, 1);
                            total = total.add(amount);
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(total.toString());
                        }
                    }

                    FileOutputStream fos1 = new FileOutputStream(fc.getSelectedFile() + ".xlsx");
                    wb.write(fos1);
                    MsgBox.alert(this, "Đã xuất file Excel thành công!");
                    fos1.close();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void printExcelNam() {
        if (tblYear.getRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất file Excel!");
            return;
        } else {
            JFileChooser fc = new JFileChooser("D:\\");
            fc.setDialogTitle("Doanh Thu");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            fc.setFileFilter(filter);
            int choose = fc.showSaveDialog(null);
            System.out.println("Vui lòng chờ...");
            if (choose == JFileChooser.APPROVE_OPTION) {
                try {

                    XSSFWorkbook wb = new XSSFWorkbook();
                    XSSFSheet sheet = wb.createSheet("Doanh Thu");
                    XSSFRow row = null;
                    Cell cell = null;
                    row = sheet.createRow(3);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue("Ngày");
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue("Doanh thu");
                    BigDecimal total = BigDecimal.ZERO;
                    if (tblYear != null) {
                        for (int i = 0; i < tblYear.getRowCount(); i++) {
                            row = sheet.createRow(4 + i);

                            cell = row.createCell(0, CellType.STRING);
                            cell.setCellValue((Integer) tblYear.getValueAt(i, 0));

                            final BigDecimal amount = (BigDecimal) tblYear.getValueAt(i, 1);
                            total = total.add(amount);
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(total.toString());
                        }
                    }

                    FileOutputStream fos1 = new FileOutputStream(fc.getSelectedFile() + ".xlsx");
                    wb.write(fos1);
                    MsgBox.alert(this, "Đã xuất file Excel thành công!");
                    fos1.close();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                MsgBox.alert(this, "Bạn đã hủy lưu file Excel!");
                return;
            }

        }
    }

    //Xuất file PDF cho thống kê theo ngày
    public void exportPDFNgay() {
        if (tblDate.getRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất PDF!");
        } else {
            try {
                String path = "";
                JFileChooser j = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                j.setFileFilter(filter);
                //           j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int choose = j.showSaveDialog(null);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getPath();
                    Document doc = new Document();

                    PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
                    doc.open();

                    PdfPTable tbl = new PdfPTable(2);
                    tbl.addCell("Ngày");
                    tbl.addCell("Doanh thu");

                    for (int i = 0; i < tblDate.getRowCount(); i++) {
                        String date = tblDate.getValueAt(i, 0).toString();
                        String doanhThu = tblDate.getValueAt(i, 1).toString();

                        tbl.addCell(date);
                        tbl.addCell(doanhThu);

                    }
                    doc.add(tbl);
                    doc.close();
                } else {
                    MsgBox.alert(this, "Bạn đã hủy lưu file PDF!");
                    return;
                }
                MsgBox.alert(this, "Xuất file PDF thống kê theo ngày thành công!");
                return;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }
        }

    }

    //Xuất file PDF cho thống kê theo tháng
    public void exportPDFThang() {
        if (tblMonth.getRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất PDF!");
        } else {
            try {
                String path = "";
                JFileChooser j = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                j.setFileFilter(filter);
                //           j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int choose = j.showSaveDialog(null);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getPath();
                    Document doc = new Document();

                    PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
                    doc.open();

                    PdfPTable tbl = new PdfPTable(2);
                    tbl.addCell("Ngày");
                    tbl.addCell("Doanh thu");

                    for (int i = 0; i < tblMonth.getRowCount(); i++) {
                        String date = tblMonth.getValueAt(i, 0).toString();
                        String doanhThu = tblMonth.getValueAt(i, 1).toString();

                        tbl.addCell(date);
                        tbl.addCell(doanhThu);

                    }
                    doc.add(tbl);
                    doc.close();
                } else {
                    MsgBox.alert(this, "Bạn đã hủy lưu file PDF!");
                    return;
                }
                MsgBox.alert(this, "Xuất file PDF thống kê theo tháng thành công!");
                return;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }
        }

    }

    //Xuất file PDF cho thống kê theo năm
    public void exportPDFNam() {
        if (tblYear.getRowCount() == 0) {
            MsgBox.alert(this, "Chưa có dữ liệu để xuất PDF!");
        } else {
            try {
                String path = "";
                JFileChooser j = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                j.setFileFilter(filter);
                //           j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int choose = j.showSaveDialog(null);
                if (choose == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getPath();
                    Document doc = new Document();

                    PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
                    doc.open();

                    PdfPTable tbl = new PdfPTable(2);
                    tbl.addCell("Ngày");
                    tbl.addCell("Doanh thu");

                    for (int i = 0; i < tblYear.getRowCount(); i++) {
                        String date = tblYear.getValueAt(i, 0).toString();
                        String doanhThu = tblYear.getValueAt(i, 1).toString();

                        tbl.addCell(date);
                        tbl.addCell(doanhThu);

                    }
                    doc.add(tbl);
                    doc.close();
                } else {
                    MsgBox.alert(this, "Bạn đã hủy lưu file PDF!");
                    return;
                }
                MsgBox.alert(this, "Xuất file PDF thống kê theo năm thành công!");
                return;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new com.raven.datechooser.DateChooser();
        month = new com.raven.datechooser.DateChooser();
        year = new com.raven.datechooser.DateChooser();
        quy = new com.raven.datechooser.DateChooser();
        materialTabbed1 = new com.bar.customUI.MaterialTabbed();
        ThongKeTheoNgay = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new com.bar.customUI.TextField();
        btnDate = new javax.swing.JButton();
        btnThongKeNgay = new javax.swing.JButton();
        btnExcelNgay = new javax.swing.JButton();
        btnPDFNgay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDate = new com.bar.customUI.TableWhite();
        ThongKeTheoThang = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMonth = new com.bar.customUI.TextField();
        btnMonth = new javax.swing.JButton();
        btnThongKeThang = new javax.swing.JButton();
        btnExcelThang = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMonth = new com.bar.customUI.TableWhite();
        ThongKeTheoNam = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtYear = new com.bar.customUI.TextField();
        btnYear = new javax.swing.JButton();
        btnThongKeNam = new javax.swing.JButton();
        btnExcelNam = new javax.swing.JButton();
        btnPDFNam = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblYear = new com.bar.customUI.TableWhite();

        date.setDateFormat("yyyy-MM-dd");
        date.setTextRefernce(txtDate);

        month.setDateFormat("M-yyyy");
        month.setTextRefernce(txtMonth);

        year.setDateFormat("yyyy");
        year.setTextRefernce(txtYear);

        materialTabbed1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        ThongKeTheoNgay.setBackground(new java.awt.Color(250, 250, 250));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Chọn ngày cần thống kê");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, -1));

        txtDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 260, 60));

        btnDate.setBackground(new java.awt.Color(255, 255, 255));
        btnDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDate.setForeground(new java.awt.Color(153, 0, 0));
        btnDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/calendar.png"))); // NOI18N
        btnDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDate.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDateActionPerformed(evt);
            }
        });
        jPanel1.add(btnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 70, 60));

        btnThongKeNgay.setBackground(new java.awt.Color(255, 255, 255));
        btnThongKeNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThongKeNgay.setForeground(new java.awt.Color(37, 109, 133));
        btnThongKeNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/analysis.png"))); // NOI18N
        btnThongKeNgay.setText("Thống kê");
        btnThongKeNgay.setBorder(null);
        btnThongKeNgay.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnThongKeNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeNgayActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKeNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 70, 130, 60));

        btnExcelNgay.setBackground(new java.awt.Color(255, 255, 255));
        btnExcelNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcelNgay.setForeground(new java.awt.Color(37, 109, 133));
        btnExcelNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/sheets.png"))); // NOI18N
        btnExcelNgay.setText("Excel");
        btnExcelNgay.setBorder(null);
        btnExcelNgay.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnExcelNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelNgayActionPerformed(evt);
            }
        });
        jPanel1.add(btnExcelNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 70, 140, 60));

        btnPDFNgay.setBackground(new java.awt.Color(255, 255, 255));
        btnPDFNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPDFNgay.setForeground(new java.awt.Color(37, 109, 133));
        btnPDFNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/pdf.png"))); // NOI18N
        btnPDFNgay.setText("PDF");
        btnPDFNgay.setBorder(null);
        btnPDFNgay.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnPDFNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFNgayActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDFNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 70, 140, 60));

        tblDate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày", "Doanh thu"
            }
        ));
        jScrollPane1.setViewportView(tblDate);

        javax.swing.GroupLayout ThongKeTheoNgayLayout = new javax.swing.GroupLayout(ThongKeTheoNgay);
        ThongKeTheoNgay.setLayout(ThongKeTheoNgayLayout);
        ThongKeTheoNgayLayout.setHorizontalGroup(
            ThongKeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoNgayLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ThongKeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1785, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(25, 25, 25))
        );
        ThongKeTheoNgayLayout.setVerticalGroup(
            ThongKeTheoNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoNgayLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        materialTabbed1.addTab("Thống kê theo ngày", ThongKeTheoNgay);

        ThongKeTheoThang.setBackground(new java.awt.Color(250, 250, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Chọn tháng cần thống kê");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, -1));

        txtMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(txtMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 260, 60));

        btnMonth.setBackground(new java.awt.Color(255, 255, 255));
        btnMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMonth.setForeground(new java.awt.Color(153, 0, 0));
        btnMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/calendar.png"))); // NOI18N
        btnMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMonth.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonthActionPerformed(evt);
            }
        });
        jPanel2.add(btnMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 70, 60));

        btnThongKeThang.setBackground(new java.awt.Color(255, 255, 255));
        btnThongKeThang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThongKeThang.setForeground(new java.awt.Color(153, 0, 0));
        btnThongKeThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/analysis.png"))); // NOI18N
        btnThongKeThang.setText("Thống kê");
        btnThongKeThang.setBorder(null);
        btnThongKeThang.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnThongKeThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeThangActionPerformed(evt);
            }
        });
        jPanel2.add(btnThongKeThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 70, 130, 60));

        btnExcelThang.setBackground(new java.awt.Color(255, 255, 255));
        btnExcelThang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcelThang.setForeground(new java.awt.Color(153, 0, 0));
        btnExcelThang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/sheets.png"))); // NOI18N
        btnExcelThang.setText("Excel");
        btnExcelThang.setBorder(null);
        btnExcelThang.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnExcelThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelThangActionPerformed(evt);
            }
        });
        jPanel2.add(btnExcelThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 70, 140, 60));

        btnPDF.setBackground(new java.awt.Color(255, 255, 255));
        btnPDF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPDF.setForeground(new java.awt.Color(153, 0, 0));
        btnPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/pdf.png"))); // NOI18N
        btnPDF.setText("PDF");
        btnPDF.setBorder(null);
        btnPDF.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });
        jPanel2.add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 70, 140, 60));

        tblMonth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ));
        jScrollPane2.setViewportView(tblMonth);

        javax.swing.GroupLayout ThongKeTheoThangLayout = new javax.swing.GroupLayout(ThongKeTheoThang);
        ThongKeTheoThang.setLayout(ThongKeTheoThangLayout);
        ThongKeTheoThangLayout.setHorizontalGroup(
            ThongKeTheoThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoThangLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ThongKeTheoThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1785, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(25, 25, 25))
        );
        ThongKeTheoThangLayout.setVerticalGroup(
            ThongKeTheoThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoThangLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê theo tháng", ThongKeTheoThang);

        ThongKeTheoNam.setBackground(new java.awt.Color(250, 250, 250));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Chọn năm cần thống kê");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 210, -1));

        txtYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtYear.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel3.add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 260, 60));

        btnYear.setBackground(new java.awt.Color(255, 255, 255));
        btnYear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnYear.setForeground(new java.awt.Color(153, 0, 0));
        btnYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/calendar.png"))); // NOI18N
        btnYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnYear.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYearActionPerformed(evt);
            }
        });
        jPanel3.add(btnYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 70, 60));

        btnThongKeNam.setBackground(new java.awt.Color(255, 255, 255));
        btnThongKeNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThongKeNam.setForeground(new java.awt.Color(153, 0, 0));
        btnThongKeNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/analysis.png"))); // NOI18N
        btnThongKeNam.setText("Thống kê");
        btnThongKeNam.setBorder(null);
        btnThongKeNam.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnThongKeNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeNamActionPerformed(evt);
            }
        });
        jPanel3.add(btnThongKeNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 70, 130, 60));

        btnExcelNam.setBackground(new java.awt.Color(255, 255, 255));
        btnExcelNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExcelNam.setForeground(new java.awt.Color(153, 0, 0));
        btnExcelNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/sheets.png"))); // NOI18N
        btnExcelNam.setText("Excel");
        btnExcelNam.setBorder(null);
        btnExcelNam.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnExcelNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelNamActionPerformed(evt);
            }
        });
        jPanel3.add(btnExcelNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 70, 140, 60));

        btnPDFNam.setBackground(new java.awt.Color(255, 255, 255));
        btnPDFNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPDFNam.setForeground(new java.awt.Color(153, 0, 0));
        btnPDFNam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/pdf.png"))); // NOI18N
        btnPDFNam.setText("PDF");
        btnPDFNam.setBorder(null);
        btnPDFNam.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnPDFNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFNamActionPerformed(evt);
            }
        });
        jPanel3.add(btnPDFNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 70, 140, 60));

        tblYear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Doanh thu"
            }
        ));
        jScrollPane3.setViewportView(tblYear);

        javax.swing.GroupLayout ThongKeTheoNamLayout = new javax.swing.GroupLayout(ThongKeTheoNam);
        ThongKeTheoNam.setLayout(ThongKeTheoNamLayout);
        ThongKeTheoNamLayout.setHorizontalGroup(
            ThongKeTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoNamLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ThongKeTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1785, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(25, 25, 25))
        );
        ThongKeTheoNamLayout.setVerticalGroup(
            ThongKeTheoNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeTheoNamLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thống kê theo năm", ThongKeTheoNam);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 1835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDateActionPerformed
        date.showPopup();
    }//GEN-LAST:event_btnDateActionPerformed

    private void btnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthActionPerformed
        month.showPopup();
    }//GEN-LAST:event_btnMonthActionPerformed

    private void btnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYearActionPerformed
        year.showPopup();
    }//GEN-LAST:event_btnYearActionPerformed

    private void btnThongKeNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeNgayActionPerformed

        fillTableThongKeNgay();


    }//GEN-LAST:event_btnThongKeNgayActionPerformed

    private void btnThongKeNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeNamActionPerformed
        fillTableThongKeNam();
    }//GEN-LAST:event_btnThongKeNamActionPerformed

    private void btnThongKeThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeThangActionPerformed
        fillTableThongKeThang();
    }//GEN-LAST:event_btnThongKeThangActionPerformed

    private void btnExcelNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelNgayActionPerformed

        printExcelNgay();


    }//GEN-LAST:event_btnExcelNgayActionPerformed

    private void btnExcelThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelThangActionPerformed
        printExcelThang();
    }//GEN-LAST:event_btnExcelThangActionPerformed

    private void btnExcelNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelNamActionPerformed
        printExcelNam();
    }//GEN-LAST:event_btnExcelNamActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed

        exportPDFThang();
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnPDFNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFNamActionPerformed
        exportPDFNam();
    }//GEN-LAST:event_btnPDFNamActionPerformed

    private void btnPDFNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFNgayActionPerformed
        exportPDFNgay();
    }//GEN-LAST:event_btnPDFNgayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ThongKeTheoNam;
    private javax.swing.JPanel ThongKeTheoNgay;
    private javax.swing.JPanel ThongKeTheoThang;
    private javax.swing.JButton btnDate;
    private javax.swing.JButton btnExcelNam;
    private javax.swing.JButton btnExcelNgay;
    private javax.swing.JButton btnExcelThang;
    private javax.swing.JButton btnMonth;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnPDFNam;
    private javax.swing.JButton btnPDFNgay;
    private javax.swing.JButton btnThongKeNam;
    private javax.swing.JButton btnThongKeNgay;
    private javax.swing.JButton btnThongKeThang;
    private javax.swing.JButton btnYear;
    private com.raven.datechooser.DateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.bar.customUI.MaterialTabbed materialTabbed1;
    private com.raven.datechooser.DateChooser month;
    private com.raven.datechooser.DateChooser quy;
    private com.bar.customUI.TableWhite tblDate;
    private com.bar.customUI.TableWhite tblMonth;
    private com.bar.customUI.TableWhite tblYear;
    private com.bar.customUI.TextField txtDate;
    private com.bar.customUI.TextField txtMonth;
    private com.bar.customUI.TextField txtYear;
    private com.raven.datechooser.DateChooser year;
    // End of variables declaration//GEN-END:variables

}
