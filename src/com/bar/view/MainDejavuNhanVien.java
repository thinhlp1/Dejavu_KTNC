package com.bar.view;

import com.bar.util.Auth;
import com.bar.util.MsgBox;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainDejavuNhanVien extends javax.swing.JFrame {

    String tooltip = null;
    boolean check = true;

    public MainDejavuNhanVien() {
        initComponents();
        this.setLocationRelativeTo(null);
        callUserName();
        this.changeIcon();
    }

    void mouseEntered(JPanel pnl) {
        pnl.setBackground(Color.black);
    }

    public String getToolTip(JLabel label) {
        return label.getToolTipText();
    }

    void mouseEixted(JPanel pnl) {
        pnl.setBackground(new Color(243, 246, 253));
    }

    void callUserName() {
        if (Auth.account == null) {
            lblUsername.setText("No Login!");
        } else {
            lblUsername.setText(Auth.userName());
        }
    }

    private JPanel callPanel;

    private void showPanel(JPanel pnl) {
        callPanel = pnl;
        panelRoot.removeAll();
        panelRoot.add(callPanel);
        panelRoot.repaint();
        panelRoot.validate();
    }

    public void resetCard() {
        lblTabHome.setIcon(new ImageIcon(this.getClass().getResource("images/dashboard.png")));
        lblTabSanPham.setIcon(new ImageIcon(this.getClass().getResource("images/cocktail.png")));
        lblTabDanhMuc.setIcon(new ImageIcon(this.getClass().getResource("images/information.png")));
        lblTabHoaDon.setIcon(new ImageIcon(this.getClass().getResource("images/invoice.png")));
        lblTabBan.setIcon(new ImageIcon(this.getClass().getResource("images/table.png")));
        lblSetting.setIcon(new ImageIcon(this.getClass().getResource("images/setting.png")));
        pnlTabHome.setBackground(new Color(243, 246, 253));
        pnlTabBan.setBackground(new Color(243, 246, 253));
        pnlTabSanPham.setBackground(new Color(243, 246, 253));
        pnlTabDanhMuc.setBackground(new Color(243, 246, 253));
        pnlTabHoaDon.setBackground(new Color(243, 246, 253));
        pnlSetting.setBackground(new Color(243, 246, 253));
    }
    ImageIcon icon;

    public void changeIcon() {
        icon = new ImageIcon(this.getClass().getResource("images/DEjavu_LoGo_App.png"));
        setIconImage(icon.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        pnlTabHome = new RoundedPanel(100);
        lblTabHome = new javax.swing.JLabel();
        pnlTabBan = new RoundedPanel(100);
        lblTabBan = new javax.swing.JLabel();
        pnlTabSanPham = new RoundedPanel(100);
        lblTabSanPham = new javax.swing.JLabel();
        pnlTabDanhMuc = new RoundedPanel(100);
        lblTabDanhMuc = new javax.swing.JLabel();
        pnlTabHoaDon =  new RoundedPanel(100);
        lblTabHoaDon = new javax.swing.JLabel();
        pnlSetting =  new RoundedPanel(100);
        lblSetting = new javax.swing.JLabel();
        pnlTabThongKe =  new RoundedPanel(100);
        lblTabThongKe = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        panelRoot = new javax.swing.JPanel();
        pnlHome = new javax.swing.JPanel();
        pnlMenu_Ban = new RoundedPanel(20);
        jPanel6 = new RoundedPanel(100);
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlMenu_HoaDon = new RoundedPanel(20);
        jPanel14 = new RoundedPanel(100);
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlMenu_SanPham = new RoundedPanel(20);
        jPanel15 = new RoundedPanel(100);
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlMenu_DanhMuc = new RoundedPanel(20);
        jPanel17 = new RoundedPanel(100);
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1366, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlTitle.setBackground(new java.awt.Color(243, 246, 253));
        pnlTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 5, new java.awt.Color(243, 246, 253)));
        pnlTitle.setPreferredSize(new java.awt.Dimension(951, 60));

        jLabel1.setFont(new java.awt.Font("Dosis ExtraBold", 1, 36)); // NOI18N
        jLabel1.setText("Dejavu");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/logo.png"))); // NOI18N

        jPanel5.setBackground(new java.awt.Color(243, 246, 253));

        lblUsername.setBackground(new java.awt.Color(243, 246, 253));
        lblUsername.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username");

        jPanel13.setBackground(new java.awt.Color(243, 246, 253));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/man.png"))); // NOI18N
        jPanel13.add(jLabel10, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(848, 848, 848)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlTitle, java.awt.BorderLayout.PAGE_START);

        pnlMenu.setBackground(new java.awt.Color(243, 246, 253));
        pnlMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlMenu.setPreferredSize(new java.awt.Dimension(80, 100));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTabBan.setOpaque(true);
        pnlTabHome.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabHome.setOpaque(false);
        pnlTabHome.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabHome.setLayout(new java.awt.BorderLayout());

        lblTabHome.setForeground(new java.awt.Color(255, 255, 255));
        lblTabHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/dashboard.png"))); // NOI18N
        lblTabHome.setToolTipText("Home");
        lblTabHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabHomeMouseExited(evt);
            }
        });
        pnlTabHome.add(lblTabHome, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 60));

        pnlTabBan.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabBan.setMinimumSize(new java.awt.Dimension(24, 24));
        pnlTabBan.setOpaque(false);
        pnlTabBan.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabBan.setLayout(new java.awt.BorderLayout());

        lblTabBan.setForeground(new java.awt.Color(255, 255, 255));
        lblTabBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/table.png"))); // NOI18N
        lblTabBan.setToolTipText("Bàn");
        lblTabBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabBanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabBanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabBanMouseExited(evt);
            }
        });
        pnlTabBan.add(lblTabBan, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, 60));

        pnlTabSanPham.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabSanPham.setOpaque(false);
        pnlTabSanPham.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabSanPham.setLayout(new java.awt.BorderLayout());

        lblTabSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblTabSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/cocktail.png"))); // NOI18N
        lblTabSanPham.setToolTipText("Sản Phẩm");
        lblTabSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabSanPhamMouseExited(evt);
            }
        });
        pnlTabSanPham.add(lblTabSanPham, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 60, 60));

        pnlTabDanhMuc.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabDanhMuc.setOpaque(false);
        pnlTabDanhMuc.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabDanhMuc.setLayout(new java.awt.BorderLayout());

        lblTabDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        lblTabDanhMuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/information.png"))); // NOI18N
        lblTabDanhMuc.setToolTipText("Danh Mục");
        lblTabDanhMuc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabDanhMucMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabDanhMucMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabDanhMucMouseExited(evt);
            }
        });
        pnlTabDanhMuc.add(lblTabDanhMuc, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 60, 60));

        pnlTabHoaDon.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabHoaDon.setOpaque(false);
        pnlTabHoaDon.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabHoaDon.setLayout(new java.awt.BorderLayout());

        lblTabHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblTabHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/invoice.png"))); // NOI18N
        lblTabHoaDon.setToolTipText("Hóa Đơn");
        lblTabHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabHoaDonMouseExited(evt);
            }
        });
        pnlTabHoaDon.add(lblTabHoaDon, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 60, 60));

        pnlSetting.setBackground(new java.awt.Color(243, 246, 253));
        pnlSetting.setOpaque(false);
        pnlSetting.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlSetting.setLayout(new java.awt.BorderLayout());

        lblSetting.setForeground(new java.awt.Color(255, 255, 255));
        lblSetting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/setting.png"))); // NOI18N
        lblSetting.setToolTipText("Cài Đặt");
        lblSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSettingMouseExited(evt);
            }
        });
        pnlSetting.add(lblSetting, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 60));

        pnlTabThongKe.setBackground(new java.awt.Color(243, 246, 253));
        pnlTabThongKe.setOpaque(false);
        pnlTabThongKe.setPreferredSize(new java.awt.Dimension(80, 80));
        pnlTabThongKe.setLayout(new java.awt.BorderLayout());

        lblTabThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lblTabThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/analytics.png"))); // NOI18N
        lblTabThongKe.setToolTipText("Thống Kê");
        lblTabThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTabThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTabThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTabThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTabThongKeMouseExited(evt);
            }
        });
        pnlTabThongKe.add(lblTabThongKe, java.awt.BorderLayout.CENTER);

        pnlMenu.add(pnlTabThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 60, 60));

        getContentPane().add(pnlMenu, java.awt.BorderLayout.LINE_START);

        pnlFooter.setBackground(new java.awt.Color(243, 246, 253));
        pnlFooter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 5, new java.awt.Color(243, 246, 253)));
        pnlFooter.setPreferredSize(new java.awt.Dimension(951, 30));

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1329, Short.MAX_VALUE)
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFooter, java.awt.BorderLayout.PAGE_END);

        //panelRoot.setOpaque(false);
        panelRoot.setBackground(new java.awt.Color(255, 255, 255));
        panelRoot.setMinimumSize(new java.awt.Dimension(1080, 100));
        panelRoot.setPreferredSize(new java.awt.Dimension(1200, 630));
        panelRoot.setLayout(new java.awt.CardLayout());

        pnlHome.setBackground(new java.awt.Color(255, 255, 255));

        pnlMenu_Ban.setOpaque(false);
        pnlMenu_Ban.setBackground(new java.awt.Color(225, 230, 233));
        pnlMenu_Ban.setPreferredSize(new java.awt.Dimension(250, 250));
        pnlMenu_Ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu_BanMouseClicked(evt);
            }
        });

        //jPanel6.setOpaque(false);
        jPanel6.setBackground(new java.awt.Color(218, 224, 226));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(225, 230, 233));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/table.png"))); // NOI18N
        jPanel6.add(jLabel3, java.awt.BorderLayout.CENTER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Quản Lý Bàn");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlMenu_BanLayout = new javax.swing.GroupLayout(pnlMenu_Ban);
        pnlMenu_Ban.setLayout(pnlMenu_BanLayout);
        pnlMenu_BanLayout.setHorizontalGroup(
            pnlMenu_BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_BanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMenu_BanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        pnlMenu_BanLayout.setVerticalGroup(
            pnlMenu_BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_BanLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu_HoaDon.setOpaque(false);
        pnlMenu_HoaDon.setBackground(new java.awt.Color(249, 216, 227));
        pnlMenu_HoaDon.setPreferredSize(new java.awt.Dimension(250, 250));
        pnlMenu_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu_HoaDonMouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(243, 212, 222));
        jPanel14.setOpaque(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/invoice.png"))); // NOI18N
        jPanel14.add(jLabel6, java.awt.BorderLayout.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setText("Hóa Đơn");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlMenu_HoaDonLayout = new javax.swing.GroupLayout(pnlMenu_HoaDon);
        pnlMenu_HoaDon.setLayout(pnlMenu_HoaDonLayout);
        pnlMenu_HoaDonLayout.setHorizontalGroup(
            pnlMenu_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenu_HoaDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMenu_HoaDonLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        pnlMenu_HoaDonLayout.setVerticalGroup(
            pnlMenu_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_HoaDonLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pnlMenu_SanPham.setOpaque(false);
        pnlMenu_SanPham.setBackground(new java.awt.Color(182, 187, 235));
        pnlMenu_SanPham.setPreferredSize(new java.awt.Dimension(250, 250));
        pnlMenu_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu_SanPhamMouseClicked(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(190, 195, 248));
        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/cocktail.png"))); // NOI18N
        jPanel15.add(jLabel4, java.awt.BorderLayout.CENTER);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Sản Phẩm");
        jLabel14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlMenu_SanPhamLayout = new javax.swing.GroupLayout(pnlMenu_SanPham);
        pnlMenu_SanPham.setLayout(pnlMenu_SanPhamLayout);
        pnlMenu_SanPhamLayout.setHorizontalGroup(
            pnlMenu_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_SanPhamLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMenu_SanPhamLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        pnlMenu_SanPhamLayout.setVerticalGroup(
            pnlMenu_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_SanPhamLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pnlMenu_DanhMuc.setOpaque(false);
        pnlMenu_DanhMuc.setBackground(new java.awt.Color(0, 0, 0));
        pnlMenu_DanhMuc.setPreferredSize(new java.awt.Dimension(250, 250));
        pnlMenu_DanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu_DanhMucMouseClicked(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(32, 32, 32));
        jPanel17.setOpaque(false);
        jPanel17.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/images/information.png"))); // NOI18N
        jPanel17.add(jLabel5, java.awt.BorderLayout.CENTER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Danh Mục");
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(179, 179, 179)));

        javax.swing.GroupLayout pnlMenu_DanhMucLayout = new javax.swing.GroupLayout(pnlMenu_DanhMuc);
        pnlMenu_DanhMuc.setLayout(pnlMenu_DanhMucLayout);
        pnlMenu_DanhMucLayout.setHorizontalGroup(
            pnlMenu_DanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_DanhMucLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMenu_DanhMucLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        pnlMenu_DanhMucLayout.setVerticalGroup(
            pnlMenu_DanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu_DanhMucLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblWelcome.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("WELCOME BACK");

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGap(342, 342, 342)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMenu_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(pnlMenu_Ban, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMenu_SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(pnlMenu_DanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
                .addGap(329, 329, 329))
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenu_SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(pnlMenu_Ban, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenu_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(pnlMenu_DanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );

        panelRoot.add(pnlHome, "card2");

        getContentPane().add(panelRoot, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblTabBanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabBanMouseEntered
        mouseEntered(pnlTabBan);
        lblTabBan.setIcon(new ImageIcon(this.getClass().getResource("images/table_hover.png")));
    }//GEN-LAST:event_lblTabBanMouseEntered

    private void lblTabBanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabBanMouseExited
        if (tooltip == null || !tooltip.equals("Bàn")) {
            lblTabBan.setIcon(new ImageIcon(this.getClass().getResource("images/table.png")));
            mouseEixted(pnlTabBan);
        } else {
            resetCard();
            lblTabBanMouseEntered(evt);
        }
        System.out.println(tooltip);
    }//GEN-LAST:event_lblTabBanMouseExited

    private void lblTabSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabSanPhamMouseEntered
        mouseEntered(pnlTabSanPham);
        lblTabSanPham.setIcon(new ImageIcon(this.getClass().getResource("images/cocktail_hover.png")));
    }//GEN-LAST:event_lblTabSanPhamMouseEntered

    private void lblTabSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabSanPhamMouseExited
        if (tooltip == null || !tooltip.equals("Sản Phẩm")) {
            mouseEixted(pnlTabSanPham);
            lblTabSanPham.setIcon(new ImageIcon(this.getClass().getResource("images/cocktail.png")));
        } else {
            resetCard();
            lblTabSanPhamMouseEntered(evt);
        }
    }//GEN-LAST:event_lblTabSanPhamMouseExited

    private void lblTabDanhMucMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabDanhMucMouseEntered
        mouseEntered(pnlTabDanhMuc);
        lblTabDanhMuc.setIcon(new ImageIcon(this.getClass().getResource("images/information_hover.png")));
    }//GEN-LAST:event_lblTabDanhMucMouseEntered

    private void lblTabDanhMucMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabDanhMucMouseExited
        if (tooltip == null || !tooltip.equals("Danh Mục")) {
            lblTabDanhMuc.setIcon(new ImageIcon(this.getClass().getResource("images/information.png")));
            mouseEixted(pnlTabDanhMuc);
        } else {
            resetCard();
            lblTabDanhMucMouseEntered(evt);
        }
    }//GEN-LAST:event_lblTabDanhMucMouseExited

    private void lblTabHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHoaDonMouseEntered
        mouseEntered(pnlTabHoaDon);
        lblTabHoaDon.setIcon(new ImageIcon(this.getClass().getResource("images/invoice_hover.png")));
    }//GEN-LAST:event_lblTabHoaDonMouseEntered

    private void lblTabHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHoaDonMouseExited
        if (tooltip == null || !tooltip.equals("Hóa Đơn")) {
            lblTabHoaDon.setIcon(new ImageIcon(this.getClass().getResource("images/invoice.png")));
            mouseEixted(pnlTabHoaDon);
        } else {
            resetCard();
            lblTabHoaDonMouseEntered(evt);
        }
    }//GEN-LAST:event_lblTabHoaDonMouseExited

    private void lblSettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingMouseEntered
        mouseEntered(pnlSetting);
        lblSetting.setIcon(new ImageIcon(this.getClass().getResource("images/setting_hover.png")));
    }//GEN-LAST:event_lblSettingMouseEntered

    private void lblSettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingMouseExited
        if (tooltip == null || !tooltip.equals("Cài Đặt")) {
            mouseEixted(pnlSetting);
            lblSetting.setIcon(new ImageIcon(this.getClass().getResource("images/setting.png")));
        } else {
            resetCard();
            lblSettingMouseEntered(evt);
        }
    }//GEN-LAST:event_lblSettingMouseExited

    private void lblTabHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHomeMouseEntered
        mouseEntered(pnlTabHome);
        lblTabHome.setIcon(new ImageIcon(this.getClass().getResource("images/dashboard_hover.png")));
    }//GEN-LAST:event_lblTabHomeMouseEntered

    private void lblTabHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHomeMouseExited
        if (tooltip == null || !tooltip.equals("Home")) {
            lblTabHome.setIcon(new ImageIcon(this.getClass().getResource("images/dashboard.png")));
            mouseEixted(pnlTabHome);
        } else {
            resetCard();
            lblTabHomeMouseEntered(evt);
        }

    }//GEN-LAST:event_lblTabHomeMouseExited

    private void lblTabHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHomeMouseClicked
        tooltip = getToolTip(lblTabHome);
        showPanel(pnlHome);
    }//GEN-LAST:event_lblTabHomeMouseClicked

    private void lblTabBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabBanMouseClicked
        tooltip = getToolTip(lblTabBan);
        showPanel(new Panel_QLBanHang());
    }//GEN-LAST:event_lblTabBanMouseClicked

    private void lblTabSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabSanPhamMouseClicked
        tooltip = getToolTip(lblTabSanPham);
        showPanel(new PNL_SanPham());
    }//GEN-LAST:event_lblTabSanPhamMouseClicked

    private void lblTabDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabDanhMucMouseClicked
        tooltip = getToolTip(lblTabDanhMuc);
        showPanel(new PNL_DanhMuc());
    }//GEN-LAST:event_lblTabDanhMucMouseClicked

    private void lblTabHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabHoaDonMouseClicked
        tooltip = getToolTip(lblTabHoaDon);
        showPanel(new QL_HoaDon());
    }//GEN-LAST:event_lblTabHoaDonMouseClicked

    private void lblSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingMouseClicked
        tooltip = getToolTip(lblSetting);
        showPanel(new Setting());
    }//GEN-LAST:event_lblSettingMouseClicked

    private void pnlMenu_BanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu_BanMouseClicked
        lblTabBanMouseClicked(evt);
        resetCard();
        lblTabBanMouseEntered(evt);
    }//GEN-LAST:event_pnlMenu_BanMouseClicked

    private void pnlMenu_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu_HoaDonMouseClicked
        lblTabHoaDonMouseClicked(evt);
        resetCard();
        lblTabHoaDonMouseEntered(evt);
    }//GEN-LAST:event_pnlMenu_HoaDonMouseClicked

    private void pnlMenu_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu_SanPhamMouseClicked
        lblTabSanPhamMouseClicked(evt);
        resetCard();
        lblTabSanPhamMouseEntered(evt);
    }//GEN-LAST:event_pnlMenu_SanPhamMouseClicked

    private void pnlMenu_DanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu_DanhMucMouseClicked
        lblTabDanhMucMouseClicked(evt);
        resetCard();
        lblTabDanhMucMouseEntered(evt);
    }//GEN-LAST:event_pnlMenu_DanhMucMouseClicked

    private void lblTabThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabThongKeMouseClicked
        tooltip = getToolTip(lblTabThongKe);
        showPanel(new QL_ThongKe());
    }//GEN-LAST:event_lblTabThongKeMouseClicked

    private void lblTabThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabThongKeMouseEntered
        mouseEntered(pnlTabThongKe);
        lblTabThongKe.setIcon(new ImageIcon(this.getClass().getResource("images/analytics_hover.png")));
    }//GEN-LAST:event_lblTabThongKeMouseEntered

    private void lblTabThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTabThongKeMouseExited
        if (tooltip == null || !tooltip.equals("Thống Kê")) {
            mouseEixted(pnlTabThongKe);
            lblTabThongKe.setIcon(new ImageIcon(this.getClass().getResource("images/analytics.png")));
        } else {
            resetCard();
            lblTabThongKeMouseEntered(evt);
        }
    }//GEN-LAST:event_lblTabThongKeMouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        boolean isExit = MsgBox.confirm(this, "Bạn có chắc chắn muốn thoát khỏi chương trình ?");
        if (!isExit) {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    void btnExited(JButton btn) {
        btn.setForeground(Color.white);
        btn.setBackground(Color.black);
        btn.setOpaque(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDejavuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDejavuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDejavuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDejavuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                Welcome wel = new Welcome(new MainDejavu(), true);
//                wel.setVisible(true);
                new MainDejavuNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblSetting;
    private javax.swing.JLabel lblTabBan;
    private javax.swing.JLabel lblTabDanhMuc;
    private javax.swing.JLabel lblTabHoaDon;
    private javax.swing.JLabel lblTabHome;
    private javax.swing.JLabel lblTabSanPham;
    private javax.swing.JLabel lblTabThongKe;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel panelRoot;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenu_Ban;
    private javax.swing.JPanel pnlMenu_DanhMuc;
    private javax.swing.JPanel pnlMenu_HoaDon;
    private javax.swing.JPanel pnlMenu_SanPham;
    private javax.swing.JPanel pnlSetting;
    private javax.swing.JPanel pnlTabBan;
    private javax.swing.JPanel pnlTabDanhMuc;
    private javax.swing.JPanel pnlTabHoaDon;
    private javax.swing.JPanel pnlTabHome;
    private javax.swing.JPanel pnlTabSanPham;
    private javax.swing.JPanel pnlTabThongKe;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables
}
