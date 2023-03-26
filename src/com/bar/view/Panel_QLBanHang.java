package com.bar.view;

import com.bar.customUI.ScrollBarCustom;
import com.bar.dao.BanDAO;
import com.bar.dao.BanOderDAO;
import com.bar.dao.HoaDonChiTietDAO;
import com.bar.dao.HoaDonDAO;
import com.bar.dao.SanPhamDAO;
import com.bar.dao.SanPhamOderDAO;
import com.bar.model.Ban;
import com.bar.model.BanOder;
import com.bar.model.HoaDon;
import com.bar.model.HoaDonChiTiet;
import com.bar.model.SanPham;
import com.bar.model.SanPham_GioHang;
import com.bar.util.Auth;
import com.bar.util.MsgBox;
import com.bar.util.XDate;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thaih
 */
public class Panel_QLBanHang extends javax.swing.JPanel {

    List<SanPham> ListSp = new ArrayList<SanPham>();
    List<Ban> listBan = new ArrayList<Ban>();
    List<BanOder> listBan_MaBanGop = new ArrayList<BanOder>();
    SanPhamDAO spDao = new SanPhamDAO();
    BanDAO banDao = new BanDAO();
    BanOderDAO banOderDao = new BanOderDAO();
    SanPhamOderDAO spOderDao = new SanPhamOderDAO();
    Map<String, List> mapBan = new HashMap<String, List>();
    List<HoaDon> ListhoaDon = new ArrayList<HoaDon>();
    List<HoaDonChiTiet> ListhoaDonCT = new ArrayList<HoaDonChiTiet>();
    HoaDonDAO hoaDonDao = new HoaDonDAO();
    HoaDonChiTietDAO hoaDonCTDao = new HoaDonChiTietDAO();

    public Panel_QLBanHang() {
        initComponents();
        this.sanPhamToList("DM01");
        this.banToList();

        cp_cuon.setVerticalScrollBar(new ScrollBarCustom());
        jsc_GioHang.setVerticalScrollBar(new ScrollBarCustom());
        jcp_CuonBan.setVerticalScrollBar(new ScrollBarCustom());
        clickedDanhMuc_Ban(pnlDanhMuc_Cocktail, lblDanhMuc_Cocktail);

        this.fillRanderBan();
        this.fillRanderSP();

    }

    // ban
    public void banToList() {
        listBan = banDao.select();
        listBan_MaBanGop = banOderDao.selectMBGopNotNull();
        System.out.println(listBan_MaBanGop.size());
        System.out.println(listBan.size());
    }
    // rander ban

    public void fillRanderBan() {
        List<Pnl_RenderBan> listPanel = fromBan();
        pnl_RanderBan.removeAll();
        pnl_RanderBan.revalidate();
        pnl_RanderBan.repaint();
        int x = 15;
        int y = 15;
        int weight = 190;
        int height = 190;
        int xShift = 210;
        int yShift = 210;
        int row = listPanel.size() / 5;
        int du = listPanel.size() % 5;
        if (du > 0) {
            row++;
        }

        int indexComponent = 0;
        int pnlHeight = row * yShift;
        pnl_RanderBan.setPreferredSize(new Dimension(1100, pnlHeight));

        for (int i = 0; i < row; i++) {
            for (int index = 0; index < 5; index++) {
                //pnl_Rander.add(listPanel.get(indexComponent));
//test
                pnl_RanderBan.add(listPanel.get(indexComponent));

                // set vi tri cho no đứng trên pn cha
                listPanel.get(indexComponent).setBounds(x, y, weight, height);
                if (indexComponent == listPanel.size() - 1) {
                    break;
                } else {
                    indexComponent++;
                }
                x += xShift;
            }
            x = 15;
            y += yShift;
        }

    }
    private JPanel callPanel;

    private void showPanel(JPanel pnl) {
        callPanel = pnl;
        pnl_CardShow.removeAll();
        pnl_CardShow.add(callPanel);
        pnl_CardShow.repaint();
        pnl_CardShow.validate();
    }
    private JScrollPane scroll;

    private void showScoll(JScrollPane scrolls) {
        scroll = scrolls;
        pnl_CardShow.removeAll();
        pnl_CardShow.add(scroll);
        pnl_CardShow.repaint();
        pnl_CardShow.validate();
    }

    //Tao list ban order 
    public List<Pnl_RenderBan> fromBan() {
        List<Pnl_RenderBan> fBan = new ArrayList<Pnl_RenderBan>();
        listBan.clear();
        listBan_MaBanGop.clear();
        listBan = banDao.select();
        listBan_MaBanGop = banOderDao.selectMBGopNotNull();
        for (int i = 0; i < listBan.size(); i++) {
            Ban BanNew = listBan.get(i);
            Pnl_RenderBan FromCaiBan = new Pnl_RenderBan();
            FromCaiBan.setBan(BanNew);
            if (listBan.get(i).isTrangThai() == true) {
                FromCaiBan.setBackground(new Color(3, 155, 216));
                FromCaiBan.setVisible(true);
            }
            if (listBan.get(i).isTrangThai() == false) {
                FromCaiBan.setBackground(new Color(240, 240, 240));
                FromCaiBan.setVisible(false);
            }
            if (listBan.get(i).getMaBan().equalsIgnoreCase(lbl_MaBan.getText())) {
                FromCaiBan.setBackground(Color.red);
            }
            int check = -1;
            for (int j = 0; j < listBan_MaBanGop.size(); j++) {
                if (BanNew.getMaBan().equalsIgnoreCase(listBan_MaBanGop.get(j).getMaBan())) {
                    check = j;
                    break;
                }

            }
            if (check >= 0) {
                FromCaiBan.setMaBanGopOn(BanNew, listBan_MaBanGop.get(check).getMaBanGop());
                FromCaiBan.setViSiBleBanGop(true);
            } else {
                FromCaiBan.setRong();
                FromCaiBan.setViSiBleBanGop(false);
            }

            //    xử lý sự kiện action click ban
            FromCaiBan.setAction(new MouseAdapter() {

                public void actionPerformed(MouseEvent event) {

                }

                @Override
                public void mouseClicked(MouseEvent e
                ) {
                    lbl_MaBan.setText(FromCaiBan.getMaBan());
                    areaGhiChu1.setText("");
                    // fill de set mau khi chon
                    fillRanderBan();
                    // fill gio hang
                    fillGioHang(BanNew);
                    BanOder banOderTam = banOderDao.selectID(lbl_MaBan.getText());
                    if (banOderTam != null) {
                          if (banOderTam.getGhiChu() != null)
                          areaGhiChu1.setText(banOderTam.getGhiChu());
                    } else {
                       areaGhiChu1.setText("");
                    }

                    tinhTongTien();
                    //  }
                }

            }
            );
            FromCaiBan.setBanLenGiaoDien();

            fBan.add(FromCaiBan);
        }
        return fBan;

    }

    //
    //
// san pham
    public void sanPhamToList(String maDM) {
        ListSp = spDao.selectMaDanhMuc(maDM);
    }

    public void fillRanderSP() {
        List<PanelRanderSP> listPanel = fromsp();
        pnl_RanderSp.removeAll();
        pnl_RanderSp.revalidate();
        pnl_RanderSp.repaint();
        int x = 15;
        int y = 10;
        int weight = 200;
        int height = 300;
        int xShift = 205;
        int yShift = 310;
        int row = listPanel.size() / 5;
        int du = listPanel.size() % 5;
        if (du > 0) {
            row++;
        }

        int indexComponent = 0;
        int pnlHeight = row * yShift;
        pnl_RanderSp.setPreferredSize(new Dimension(1100, pnlHeight));

        for (int i = 0; i < row; i++) {

            for (int index = 0; index < 5; index++) {

                //pnl_Rander.add(listPanel.get(indexComponent));
//test
                pnl_RanderSp.add(listPanel.get(indexComponent));
                System.out.println(listPanel.get(indexComponent).sp.getTenMon());
                // set vi tri cho no đứng trên pn cha
                listPanel.get(indexComponent).setBounds(x, y, weight, height);
                if (indexComponent == listPanel.size() - 1) {
                    break;
                } else {
                    indexComponent++;
                }
                x += xShift;
            }
            x = 10;
            y += yShift;
        }

    }
///
///
// xu ly gio hang

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
    // list gio hang

    List<SanPham_GioHang> listGioHangSp = new ArrayList<SanPham_GioHang>();
//

    public List<PanelRanderSP> fromsp() {

        List<PanelRanderSP> fsp = new ArrayList<PanelRanderSP>();
        for (int i = 0; i < ListSp.size(); i++) {
            SanPham spnew = ListSp.get(i);
            PanelRanderSP Fromsp = new PanelRanderSP();
            Fromsp.setSp(spnew);

            // sử dụng mouseListener se bat ke thua het con mouseAdapter muốn sài nao kt đó
            Fromsp.setAction(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e
                ) {
                    // 
                    if (lbl_MaBan.getText().equals("")) {
                        MsgBox.alert(pnl_RanderSp, "Vui lÒng chọn bàn trước khi Other !");
                    } else {
                        // check ma trung tang so luong
                        boolean check = checkListGioHang(spnew);
                        if (check == false) {
                            addSpToGiohang(Fromsp);
                        }
                        //  fillTableGioHang;
                        fillRanderGioHang();
                        // tinh tien
                        tinhTongTien();
                    }
                    // click add sp vao bang

                }
            }
            );
            Fromsp.setSpLenGiaoDien();

            fsp.add(Fromsp);
        }
        return fsp;
    }
//    // lisst gio hang
    // khi click len san pham add vao gio hang

    public void addSpToGiohang(PanelRanderSP Fromsp) {
        String maMon = Fromsp.getMaMon();
        String TenMon = Fromsp.getTenSp();
        long giaMon = toMoneyLong(Fromsp.getGiaSp());
        String HinhMon = Fromsp.getHinh();
        String maBan = lbl_MaBan.getText();

        int soLuong = 1;
        SanPham_GioHang SpGh = new SanPham_GioHang(maBan, HinhMon, maMon, TenMon, giaMon, soLuong);
        listGioHangSp.add(SpGh);

    }

    public List<PanelSanPhamGioHang> fromSpGioHang() {
        List<PanelSanPhamGioHang> listNewFromSpGioHang = new ArrayList<PanelSanPhamGioHang>();

        for (int i = 0; i < listGioHangSp.size(); i++) {

            PanelSanPhamGioHang fromspGioHang = new PanelSanPhamGioHang();
            // set san pham cho moi đối tượng from

            fromspGioHang.setSP(listGioHangSp.get(i));
            SanPham_GioHang sanPhamGioHang = listGioHangSp.get(i);
            fromspGioHang.setSpOnFrom(sanPhamGioHang);

            fromspGioHang.setActionGiam(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e
                ) {

                    int SoLuong = fromspGioHang.spGioHang.getSoLuong();
                    SoLuong--;
                    if (SoLuong <= 0) {
                        // so luong ve khong thi xoa
                        pnl_RanderGioHang.remove(fromspGioHang);
                        pnl_RanderGioHang.validate();
                        pnl_RanderGioHang.repaint();
                        listNewFromSpGioHang.remove(fromspGioHang);
                        listGioHangSp.remove(sanPhamGioHang);
                        fillRanderGioHang();
                        // tinh tien
                        tinhTongTien();
                        //  SoLuong = 1;
                    }
                    System.out.println(SoLuong);

                    fromspGioHang.setSoLuong(SoLuong);
                    sanPhamGioHang.setSoLuong(SoLuong);
                    //     fromspGioHang.spGioHang.setSoLuong(SoLuong);

                    // fillRanderGioHang();
                    // tinh tien
                    tinhTongTien();
                }

            }
            );
            fromspGioHang.setActionTang(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e
                ) {

                    int SoLuong = fromspGioHang.spGioHang.getSoLuong();
                    SoLuong++;
                    if (SoLuong >= 10) {
                        SoLuong = 1;
                    }
                    System.out.println(SoLuong);

                    fromspGioHang.setSoLuong(SoLuong);
                    sanPhamGioHang.setSoLuong(SoLuong);
                    //      spOderDao.update(model);
                    //   fillRanderGioHang();
                    // tinh tien
                    tinhTongTien();
                }

            }
            );
            fromspGioHang.setActionXoa(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e
                ) {

                    pnl_RanderGioHang.remove(fromspGioHang);
                    pnl_RanderGioHang.validate();
                    pnl_RanderGioHang.repaint();

                    listNewFromSpGioHang.remove(fromspGioHang);
                    listGioHangSp.remove(sanPhamGioHang);
                    fillRanderGioHang();
                    // tinh tien
                    tinhTongTien();
                }

            }
            );
            listNewFromSpGioHang.add(fromspGioHang);
        }
        return listNewFromSpGioHang;
    }
//

    public void tinhTongTien() {
        long tongTien = 0;

        for (int i = 0; i < listGioHangSp.size(); i++) {
            long tongTienSp = listGioHangSp.get(i).getSoLuong() * listGioHangSp.get(i).getGia();
            tongTien += tongTienSp;
        }
        String tongTienLong = toMoneyString(tongTien);
        lbl_ThanhTien.setText(tongTienLong);
        lbl_TongTien.setText(tongTienLong);
    }

    public boolean checkListGioHang(SanPham sanPham) {
        for (int i = 0; i < listGioHangSp.size(); i++) {
            if (listGioHangSp.get(i).getMasp().equals(sanPham.getMaMon())) {
                listGioHangSp.get(i).setSoLuong(listGioHangSp.get(i).getSoLuong() + 1);
                return true;
            }
        }
        return false;

    }
//
//

    public void fillRanderGioHang() {
        List<PanelSanPhamGioHang> listGioHang = fromSpGioHang();
        pnl_RanderGioHang.removeAll();
        pnl_RanderGioHang.revalidate();
        pnl_RanderGioHang.repaint();
        int x = 0;
        int y = 10;
        int weight = 660;
        int height = 80;
        int xShift = 660;
        int yShift = 85;
        int row = listGioHang.size() / 1;
        int du = listGioHang.size() % 1;
        if (du > 0) {
            row++;
        }
        int indexComponent = 0;
        int pnlHeight = row * yShift;
        pnl_RanderGioHang.setPreferredSize(new Dimension(660, pnlHeight));

        for (int i = 0; i < row; i++) {
            for (int index = 0; index < 1; index++) {
                //pnl_Rander.add(listPanel.get(indexComponent));
                pnl_RanderGioHang.add(listGioHang.get(indexComponent));
                // set vi tri cho no đứng trên pn cha
                listGioHang.get(indexComponent).setBounds(x, y, weight, height);
                if (indexComponent == listGioHang.size() - 1) {
                    break;
                } else {
                    indexComponent++;
                }
                x += xShift;
            }
            x = 0;
            y += yShift;
        }
    }

    public void locSanPhamDanhMuc(String MaDM) {
        ListSp.clear();
        ListSp = spDao.selectMaDanhMuc(MaDM);
    }

    public void fillGioHang(Ban ban) {
        String maBan = ban.getMaBan();
        listGioHangSp.clear();
        try {
            listGioHangSp = spOderDao.selectSpMaBan(maBan);
            fillRanderGioHang();
        } catch (Exception e) {
        }

    }

    public void saveOderToData() {
        BanOder banOder = new BanOder();

        banOder.setMaBan(lbl_MaBan.getText());
       
            banOder.setGhiChu(areaGhiChu1.getText());
        

        Ban ban = new Ban();

        if (listGioHangSp.size() <= 0) {
            MsgBox.alert(pnl_RanderGioHang, "Vui lòng chọn món ăn !");

        } else {
            // check trung ma ban tren bang oder_ban trong db 
            List<BanOder> listCheckBanOder = banOderDao.select();
            int check = -1;
            for (int i = 0; i < listCheckBanOder.size(); i++) {
                if (listCheckBanOder.get(i).getMaBan().equalsIgnoreCase(lbl_MaBan.getText())) {
                    check = i;
                }
            }
            if (check < 0) {
                banOderDao.insert(banOder);
                ban.setTrangThai(true);
                ban.setMaBan(lbl_MaBan.getText());
                banDao.updateTrangThai(ban);
                for (SanPham_GioHang sanPham : listGioHangSp) {
                    spOderDao.insert(sanPham);
                }
                fillRanderBan();
            } else {
                spOderDao.delete(lbl_MaBan.getText());
                banOder = banOderDao.selectID(lbl_MaBan.getText());
                String ghiChu = areaGhiChu1.getText();
                if (ghiChu.equals("")) {
                 ghiChu = null;
                }
                banOderDao.updateGhiChu(ghiChu, banOder.getMaBan());
                if (banOder.getMaBanGop() != null) {
                    spOderDao.delete(banOder.getMaBanGop());
                    for (SanPham_GioHang sanPham : listGioHangSp) {
                        spOderDao.insert(sanPham);
                        spOderDao.insert(new SanPham_GioHang(banOder.getMaBanGop(), sanPham.getHinh(), sanPham.getMasp(), sanPham.getTenSP(), sanPham.getGia(), sanPham.getSoLuong()));
                    }
                } else {
                    for (SanPham_GioHang sanPham : listGioHangSp) {
                        spOderDao.insert(sanPham);
                    }

                }
            }

            MsgBox.alert(pnl_RanderGioHang, "Đã lưu vào giỏ hàng !");
            areaGhiChu1.setText("");
        }

    }

    public void deleteBan() {
        if (lbl_MaBan.getText().equals("")) {
            MsgBox.alert(pnl_RanderBan, "Vui lòng chọn một bàn cần xóa !");
            return;
        }
        Ban ban = banDao.selectTrangThai(lbl_MaBan.getText());
        if (ban.isTrangThai() == false) {
            boolean check = MsgBox.confirm(pnl_RanderBan, "Bạn chắc chắn xóa bàn " + lbl_MaBan.getText());
            if (check == true) {
                banDao.delete(lbl_MaBan.getText());
                MsgBox.alert(pnl_RanderBan, "Xóa thành công!");
                lbl_MaBan.setText("");
                fillRanderBan();
            }

        } else {
            MsgBox.alert(pnl_RanderBan, "Bàn đang có khách không được xóa !");
        }
    }

    // huy oder
    public void cancelOder() {
        if (lbl_MaBan.getText().equals("")) {
            MsgBox.alert(pnl_RanderGioHang, "Vui lòng chọn bàn để hủy oder !");
            return;
        }
        if (listGioHangSp.size() == 0) {
            MsgBox.alert(pnl_RanderGioHang, "Bàn này chưa oder không thể hủy !");
            return;
        }
        Ban ban = new Ban();
        BanOder banOder = new BanOder();
        banOder = banOderDao.selectID(lbl_MaBan.getText());
        if (banOder.getMaBanGop() != null) {
            spOderDao.delete(banOder.getMaBanGop());
            banOderDao.delete(banOder.getMaBanGop());
            Ban banSuaTrangThai = new Ban();
            banSuaTrangThai.setMaBan(banOder.getMaBanGop());
            banSuaTrangThai.setTrangThai(false);
            banDao.updateTrangThai(banSuaTrangThai);
        }
        spOderDao.delete(lbl_MaBan.getText());
        banOderDao.delete(lbl_MaBan.getText());
        listGioHangSp.clear();

        ban.setMaBan(lbl_MaBan.getText());
        ban.setTrangThai(false);
        banDao.updateTrangThai(ban);
        fillGioHang(ban);
        fillRanderGioHang();
        areaGhiChu1.setText("");
    }
    // save update trang thai

    public void updateStatusBan(Ban ban, boolean TrangThai) {
        ban.setTrangThai(TrangThai);
        banDao.updateTrangThai(ban);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        materialTabbed1 = new com.bar.customUI.MaterialTabbed();
        Panel_ChonBan = new javax.swing.JPanel();
        pnl_CardShow = new javax.swing.JPanel();
        jcp_CuonBan = new javax.swing.JScrollPane();
        pnl_RanderBan = new javax.swing.JPanel();
        pnlHandlerBan = new javax.swing.JPanel();
        pnlGopBan = new javax.swing.JPanel();
        icon_GopBan = new javax.swing.JLabel();
        lblGopBan = new javax.swing.JLabel();
        pnlTachBan = new javax.swing.JPanel();
        icon_TachBan = new javax.swing.JLabel();
        lblTachBan = new javax.swing.JLabel();
        pnl_ChuyenBan = new javax.swing.JPanel();
        lbl_ChuyenBan = new javax.swing.JLabel();
        icon_ChuyenBan = new javax.swing.JLabel();
        pnl_ThemBan = new javax.swing.JPanel();
        lbl_ThemBan = new javax.swing.JLabel();
        icon_ThemBan = new javax.swing.JLabel();
        pnl_XoaBan = new javax.swing.JPanel();
        lbl_XoaBan = new javax.swing.JLabel();
        icon_Xoa = new javax.swing.JLabel();
        pnl_MenuBan = new javax.swing.JPanel();
        icon_Ban = new javax.swing.JLabel();
        lbl_MenuBan = new javax.swing.JLabel();
        Panel_ChonMon = new javax.swing.JPanel();
        cp_cuon = new javax.swing.JScrollPane();
        pnl_RanderSp = new javax.swing.JPanel();
        pnlDanhMucSP = new javax.swing.JPanel();
        pnlDanhMuc_Cocktail = new javax.swing.JPanel();
        lbl_anhCocktail = new javax.swing.JLabel();
        lblDanhMuc_Cocktail = new javax.swing.JLabel();
        pnlDanhMuc_Beers = new javax.swing.JPanel();
        lbl_AnhBia = new javax.swing.JLabel();
        lblDanhMuc_Beers = new javax.swing.JLabel();
        pnlDanhMuc_Fruits = new javax.swing.JPanel();
        lbl_anhTraiCay = new javax.swing.JLabel();
        lblDanhMuc_Fruit = new javax.swing.JLabel();
        pnlDanhMuc_Foods = new javax.swing.JPanel();
        lbl_anhFood = new javax.swing.JLabel();
        lblDanhMuc_Foods = new javax.swing.JLabel();
        pnlDanhMuc_Other = new javax.swing.JPanel();
        lbl_anhOther = new javax.swing.JLabel();
        lblDanhMuc_Other = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlGioHang = new javax.swing.JPanel();
        pnlGioHangFooterr = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        pnl_ThanhToan = new RoundedPanel(50);
        lbl_ThanhToan = new javax.swing.JLabel();
        pnl_LuuOder = new RoundedPanel(50);
        lbl_LuuGioHang = new javax.swing.JLabel();
        pnl_HuyOder = new RoundedPanel(50);
        lbl_HuyOder = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbl_Thue = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_ThanhTien = new javax.swing.JLabel();
        pnlGioHangHeader = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lbl_MaBan = new javax.swing.JLabel();
        lbl_GhiChu = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txt_ghiChu = new com.bar.customUI.TextAreaScroll();
        areaGhiChu1 = new com.bar.customUI.TextArea();
        pnlGioHangBody = new javax.swing.JPanel();
        pnl_ThanhTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jsc_GioHang = new javax.swing.JScrollPane();
        pnl_RanderGioHang = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1835, 910));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        materialTabbed1.setBackground(new java.awt.Color(255, 255, 255));
        materialTabbed1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        materialTabbed1.setPreferredSize(new java.awt.Dimension(1100, 910));

        Panel_ChonBan.setBackground(new java.awt.Color(255, 255, 255));
        Panel_ChonBan.setForeground(new java.awt.Color(255, 51, 51));
        Panel_ChonBan.setPreferredSize(new java.awt.Dimension(1100, 880));
        Panel_ChonBan.setLayout(null);

        pnl_CardShow.setBackground(new java.awt.Color(255, 255, 255));
        pnl_CardShow.setPreferredSize(new java.awt.Dimension(1100, 650));
        pnl_CardShow.setLayout(new java.awt.CardLayout());

        jcp_CuonBan.setBorder(null);
        jcp_CuonBan.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnl_RanderBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_RanderBan.setPreferredSize(new java.awt.Dimension(1100, 650));

        javax.swing.GroupLayout pnl_RanderBanLayout = new javax.swing.GroupLayout(pnl_RanderBan);
        pnl_RanderBan.setLayout(pnl_RanderBanLayout);
        pnl_RanderBanLayout.setHorizontalGroup(
            pnl_RanderBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        pnl_RanderBanLayout.setVerticalGroup(
            pnl_RanderBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        jcp_CuonBan.setViewportView(pnl_RanderBan);

        pnl_CardShow.add(jcp_CuonBan, "card2");

        Panel_ChonBan.add(pnl_CardShow);
        pnl_CardShow.setBounds(0, 0, 1100, 650);

        pnlHandlerBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlHandlerBan.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(219, 233, 229)));
        pnlHandlerBan.setMinimumSize(new java.awt.Dimension(900, 100));
        pnlHandlerBan.setPreferredSize(new java.awt.Dimension(1080, 260));
        pnlHandlerBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlGopBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlGopBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlGopBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlGopBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGopBanMouseClicked(evt);
            }
        });
        pnlGopBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon_GopBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_GopBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/gopBan.png"))); // NOI18N
        icon_GopBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGopBanMouseClicked(evt);
            }
        });
        pnlGopBan.add(icon_GopBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblGopBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGopBan.setForeground(new java.awt.Color(164, 166, 168));
        lblGopBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGopBan.setText("Gộp Bàn");
        lblGopBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGopBanMouseClicked(evt);
            }
        });
        pnlGopBan.add(lblGopBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlHandlerBan.add(pnlGopBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 140, 67));

        pnlTachBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlTachBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlTachBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlTachBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTachBanMouseClicked(evt);
            }
        });
        pnlTachBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon_TachBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_TachBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/tachBan.png"))); // NOI18N
        icon_TachBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTachBanMouseClicked(evt);
            }
        });
        pnlTachBan.add(icon_TachBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblTachBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTachBan.setForeground(new java.awt.Color(164, 166, 168));
        lblTachBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTachBan.setText("Tách Bàn");
        lblTachBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTachBanMouseClicked(evt);
            }
        });
        pnlTachBan.add(lblTachBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlHandlerBan.add(pnlTachBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 140, 67));

        pnl_ChuyenBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ChuyenBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnl_ChuyenBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_ChuyenBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChuyenBanMouseClick(evt);
            }
        });
        pnl_ChuyenBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ChuyenBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ChuyenBan.setForeground(new java.awt.Color(164, 166, 168));
        lbl_ChuyenBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ChuyenBan.setText("Chuyển Bàn");
        lbl_ChuyenBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChuyenBanMouseClick(evt);
            }
        });
        pnl_ChuyenBan.add(lbl_ChuyenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        icon_ChuyenBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_ChuyenBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/chuyenBan.png"))); // NOI18N
        icon_ChuyenBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChuyenBanMouseClick(evt);
            }
        });
        pnl_ChuyenBan.add(icon_ChuyenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        pnlHandlerBan.add(pnl_ChuyenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 140, 67));

        pnl_ThemBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThemBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnl_ThemBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_ThemBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_themBanMouseClick(evt);
            }
        });
        pnl_ThemBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_ThemBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_ThemBan.setForeground(new java.awt.Color(164, 166, 168));
        lbl_ThemBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThemBan.setText("Thêm Bàn");
        lbl_ThemBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_themBanMouseClick(evt);
            }
        });
        pnl_ThemBan.add(lbl_ThemBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        icon_ThemBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_ThemBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/luuBan.png"))); // NOI18N
        icon_ThemBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_themBanMouseClick(evt);
            }
        });
        pnl_ThemBan.add(icon_ThemBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        pnlHandlerBan.add(pnl_ThemBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 140, 67));

        pnl_XoaBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_XoaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnl_XoaBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_XoaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_XoaBanMouseClicked(evt);
            }
        });
        pnl_XoaBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_XoaBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_XoaBan.setForeground(new java.awt.Color(164, 166, 168));
        lbl_XoaBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_XoaBan.setText("Xóa Bàn");
        lbl_XoaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_XoaBanMouseClicked(evt);
            }
        });
        pnl_XoaBan.add(lbl_XoaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        icon_Xoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/iconXoaBan.jpg"))); // NOI18N
        icon_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_XoaBanMouseClicked(evt);
            }
        });
        pnl_XoaBan.add(icon_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        pnlHandlerBan.add(pnl_XoaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 140, 67));

        pnl_MenuBan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_MenuBan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnl_MenuBan.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_MenuBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenuBanMouseClick(evt);
            }
        });
        pnl_MenuBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon_Ban.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_Ban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/table.png"))); // NOI18N
        icon_Ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenuBanMouseClick(evt);
            }
        });
        pnl_MenuBan.add(icon_Ban, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lbl_MenuBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_MenuBan.setForeground(new java.awt.Color(164, 166, 168));
        lbl_MenuBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_MenuBan.setText("Bàn");
        lbl_MenuBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenuBanMouseClick(evt);
            }
        });
        pnl_MenuBan.add(lbl_MenuBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlHandlerBan.add(pnl_MenuBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 140, 67));

        Panel_ChonBan.add(pnlHandlerBan);
        pnlHandlerBan.setBounds(10, 650, 1080, 190);

        materialTabbed1.addTab("    Bàn    ", Panel_ChonBan);

        Panel_ChonMon.setBackground(new java.awt.Color(255, 255, 255));
        Panel_ChonMon.setForeground(new java.awt.Color(255, 51, 51));
        Panel_ChonMon.setPreferredSize(new java.awt.Dimension(1100, 880));
        Panel_ChonMon.setLayout(null);

        cp_cuon.setBorder(null);
        cp_cuon.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cp_cuon.setPreferredSize(new java.awt.Dimension(1100, 650));

        pnl_RanderSp.setBackground(new java.awt.Color(255, 255, 255));
        pnl_RanderSp.setPreferredSize(new java.awt.Dimension(1100, 650));

        javax.swing.GroupLayout pnl_RanderSpLayout = new javax.swing.GroupLayout(pnl_RanderSp);
        pnl_RanderSp.setLayout(pnl_RanderSpLayout);
        pnl_RanderSpLayout.setHorizontalGroup(
            pnl_RanderSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        pnl_RanderSpLayout.setVerticalGroup(
            pnl_RanderSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        cp_cuon.setViewportView(pnl_RanderSp);

        Panel_ChonMon.add(cp_cuon);
        cp_cuon.setBounds(0, 0, 1080, 650);

        pnlDanhMucSP.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMucSP.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(219, 233, 229)));
        pnlDanhMucSP.setMinimumSize(new java.awt.Dimension(900, 100));
        pnlDanhMucSP.setPreferredSize(new java.awt.Dimension(1080, 260));

        pnlDanhMuc_Cocktail.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMuc_Cocktail.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlDanhMuc_Cocktail.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlDanhMuc_Cocktail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDanhMuc_CocktailMouseClicked(evt);
            }
        });
        pnlDanhMuc_Cocktail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_anhCocktail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_anhCocktail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/cocktail.png"))); // NOI18N
        lbl_anhCocktail.setToolTipText("DM02");
        lbl_anhCocktail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_anhCocktailMouseClicked(evt);
            }
        });
        pnlDanhMuc_Cocktail.add(lbl_anhCocktail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblDanhMuc_Cocktail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDanhMuc_Cocktail.setForeground(new java.awt.Color(164, 166, 168));
        lblDanhMuc_Cocktail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc_Cocktail.setText("Cocktail");
        lblDanhMuc_Cocktail.setToolTipText("DM02");
        lblDanhMuc_Cocktail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_anhCocktailMouseClicked(evt);
            }
        });
        pnlDanhMuc_Cocktail.add(lblDanhMuc_Cocktail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlDanhMuc_Beers.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMuc_Beers.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlDanhMuc_Beers.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlDanhMuc_Beers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDanhMuc_BeersMouseClicked(evt);
            }
        });
        pnlDanhMuc_Beers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_AnhBia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_AnhBia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/bia.png"))); // NOI18N
        lbl_AnhBia.setToolTipText("DM02");
        lbl_AnhBia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_BeersMouseClicked(evt);
            }
        });
        pnlDanhMuc_Beers.add(lbl_AnhBia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblDanhMuc_Beers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDanhMuc_Beers.setForeground(new java.awt.Color(164, 166, 168));
        lblDanhMuc_Beers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc_Beers.setText("Beers");
        lblDanhMuc_Beers.setToolTipText("DM02");
        lblDanhMuc_Beers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_BeersMouseClicked(evt);
            }
        });
        pnlDanhMuc_Beers.add(lblDanhMuc_Beers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlDanhMuc_Fruits.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMuc_Fruits.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlDanhMuc_Fruits.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlDanhMuc_Fruits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDanhMuc_FruitsMouseClicked(evt);
            }
        });
        pnlDanhMuc_Fruits.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_anhTraiCay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_anhTraiCay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/fruit.png"))); // NOI18N
        lbl_anhTraiCay.setToolTipText("DM04");
        lbl_anhTraiCay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_FruitMouseClicked(evt);
            }
        });
        pnlDanhMuc_Fruits.add(lbl_anhTraiCay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblDanhMuc_Fruit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDanhMuc_Fruit.setForeground(new java.awt.Color(164, 166, 168));
        lblDanhMuc_Fruit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc_Fruit.setText("Fruits");
        lblDanhMuc_Fruit.setToolTipText("DM04");
        lblDanhMuc_Fruit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_FruitMouseClicked(evt);
            }
        });
        pnlDanhMuc_Fruits.add(lblDanhMuc_Fruit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlDanhMuc_Foods.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMuc_Foods.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlDanhMuc_Foods.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlDanhMuc_Foods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDanhMuc_FoodsMouseClicked(evt);
            }
        });
        pnlDanhMuc_Foods.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_anhFood.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_anhFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/food.png"))); // NOI18N
        lbl_anhFood.setToolTipText("DM03");
        lbl_anhFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_FoodsMouseClicked(evt);
            }
        });
        pnlDanhMuc_Foods.add(lbl_anhFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblDanhMuc_Foods.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDanhMuc_Foods.setForeground(new java.awt.Color(164, 166, 168));
        lblDanhMuc_Foods.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc_Foods.setText("Foods");
        lblDanhMuc_Foods.setToolTipText("DM03");
        lblDanhMuc_Foods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_FoodsMouseClicked(evt);
            }
        });
        pnlDanhMuc_Foods.add(lblDanhMuc_Foods, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        pnlDanhMuc_Other.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhMuc_Other.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(179, 179, 179)));
        pnlDanhMuc_Other.setPreferredSize(new java.awt.Dimension(120, 30));
        pnlDanhMuc_Other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDanhMuc_OtherMouseClicked(evt);
            }
        });
        pnlDanhMuc_Other.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_anhOther.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_anhOther.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/more.png"))); // NOI18N
        lbl_anhOther.setToolTipText("DM05");
        lbl_anhOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_OtherMouseClicked(evt);
            }
        });
        pnlDanhMuc_Other.add(lbl_anhOther, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 140, 30));

        lblDanhMuc_Other.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDanhMuc_Other.setForeground(new java.awt.Color(164, 166, 168));
        lblDanhMuc_Other.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDanhMuc_Other.setText("Other");
        lblDanhMuc_Other.setToolTipText("DM05");
        lblDanhMuc_Other.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDanhMuc_OtherMouseClicked(evt);
            }
        });
        pnlDanhMuc_Other.add(lblDanhMuc_Other, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ADD PRODUCT");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhMucSPLayout = new javax.swing.GroupLayout(pnlDanhMucSP);
        pnlDanhMucSP.setLayout(pnlDanhMucSPLayout);
        pnlDanhMucSPLayout.setHorizontalGroup(
            pnlDanhMucSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucSPLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(pnlDanhMuc_Cocktail, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(pnlDanhMuc_Beers, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(pnlDanhMuc_Foods, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(pnlDanhMuc_Fruits, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(pnlDanhMucSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhMuc_Other, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(117, 117, 117))
        );
        pnlDanhMucSPLayout.setVerticalGroup(
            pnlDanhMucSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucSPLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlDanhMucSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDanhMuc_Fruits, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(pnlDanhMuc_Foods, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhMuc_Beers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhMuc_Cocktail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDanhMuc_Other, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        Panel_ChonMon.add(pnlDanhMucSP);
        pnlDanhMucSP.setBounds(10, 650, 1080, 190);

        materialTabbed1.addTab("Sản Phẩm", Panel_ChonMon);

        add(materialTabbed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 910));

        pnlGioHang.setBackground(new java.awt.Color(219, 233, 229));
        pnlGioHang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(164, 166, 168)));
        pnlGioHang.setLayout(new java.awt.BorderLayout());

        pnlGioHangFooterr.setBackground(new java.awt.Color(225, 230, 233));
        pnlGioHangFooterr.setPreferredSize(new java.awt.Dimension(720, 200));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel28.setText("Thuế:");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(164, 166, 168)));

        pnl_ThanhToan.setOpaque(false);
        pnl_ThanhToan.setBackground(new java.awt.Color(3, 155, 216));
        pnl_ThanhToan.setLayout(new java.awt.BorderLayout());

        lbl_ThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThanhToan.setText("Thanh toán");
        lbl_ThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ThanhToanMouseClicked(evt);
            }
        });
        pnl_ThanhToan.add(lbl_ThanhToan, java.awt.BorderLayout.CENTER);

        pnl_LuuOder.setOpaque(false);
        pnl_LuuOder.setBackground(new java.awt.Color(3, 155, 216));
        pnl_LuuOder.setLayout(new java.awt.BorderLayout());

        lbl_LuuGioHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_LuuGioHang.setForeground(new java.awt.Color(255, 255, 255));
        lbl_LuuGioHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_LuuGioHang.setText("Lưu Oder");
        lbl_LuuGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_LuuGioHangMouseClicked(evt);
            }
        });
        pnl_LuuOder.add(lbl_LuuGioHang, java.awt.BorderLayout.CENTER);

        pnl_HuyOder.setOpaque(false);
        pnl_HuyOder.setBackground(new java.awt.Color(3, 155, 216));
        pnl_HuyOder.setLayout(new java.awt.BorderLayout());

        lbl_HuyOder.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_HuyOder.setForeground(new java.awt.Color(255, 255, 255));
        lbl_HuyOder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_HuyOder.setText("Hủy Oder");
        lbl_HuyOder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HuyOderMouseClicked(evt);
            }
        });
        pnl_HuyOder.add(lbl_HuyOder, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl_HuyOder, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(pnl_LuuOder, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(pnl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnl_HuyOder, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_LuuOder, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel30.setText("Tổng tiền sản phẩm: ");

        lbl_Thue.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_Thue.setText("0");

        lbl_TongTien.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lbl_TongTien.setText("0");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel29.setText("Thành tiền");

        lbl_ThanhTien.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbl_ThanhTien.setText("0");

        javax.swing.GroupLayout pnlGioHangFooterrLayout = new javax.swing.GroupLayout(pnlGioHangFooterr);
        pnlGioHangFooterr.setLayout(pnlGioHangFooterrLayout);
        pnlGioHangFooterrLayout.setHorizontalGroup(
            pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangFooterrLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(lbl_Thue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGioHangFooterrLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(lbl_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGioHangFooterrLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlGioHangFooterrLayout.setVerticalGroup(
            pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangFooterrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addComponent(lbl_ThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGioHangFooterrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_Thue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlGioHang.add(pnlGioHangFooterr, java.awt.BorderLayout.PAGE_END);

        pnlGioHangHeader.setBackground(new java.awt.Color(225, 230, 233));
        pnlGioHangHeader.setPreferredSize(new java.awt.Dimension(720, 150));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Giỏ Hàng");

        lbl_MaBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_MaBan.setForeground(new java.awt.Color(0, 51, 51));

        lbl_GhiChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_GhiChu.setForeground(new java.awt.Color(0, 51, 51));
        lbl_GhiChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_GhiChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bar/view/images/ghiChu_ban.png"))); // NOI18N
        lbl_GhiChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_GhiChuMouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Mã Bàn: ");

        txt_ghiChu.setLabelText("");

        areaGhiChu1.setColumns(20);
        areaGhiChu1.setRows(5);
        areaGhiChu1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txt_ghiChu.setViewportView(areaGhiChu1);

        javax.swing.GroupLayout pnlGioHangHeaderLayout = new javax.swing.GroupLayout(pnlGioHangHeader);
        pnlGioHangHeader.setLayout(pnlGioHangHeaderLayout);
        pnlGioHangHeaderLayout.setHorizontalGroup(
            pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangHeaderLayout.createSequentialGroup()
                .addGroup(pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGioHangHeaderLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(lbl_MaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(lbl_GhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(pnlGioHangHeaderLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 239, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlGioHangHeaderLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(553, Short.MAX_VALUE)))
        );
        pnlGioHangHeaderLayout.setVerticalGroup(
            pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGioHangHeaderLayout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGioHangHeaderLayout.createSequentialGroup()
                        .addComponent(lbl_GhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGioHangHeaderLayout.createSequentialGroup()
                        .addGroup(pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_MaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))))
            .addGroup(pnlGioHangHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGioHangHeaderLayout.createSequentialGroup()
                    .addContainerGap(105, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)))
        );

        pnlGioHang.add(pnlGioHangHeader, java.awt.BorderLayout.PAGE_START);

        pnlGioHangBody.setBackground(new java.awt.Color(243, 246, 253));
        pnlGioHangBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_ThanhTitle.setBackground(new java.awt.Color(3, 155, 216));
        pnl_ThanhTitle.setPreferredSize(new java.awt.Dimension(666, 55));
        pnl_ThanhTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hình");
        pnl_ThanhTitle.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 84, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tên ");
        pnl_ThanhTitle.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 124, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Giá");
        pnl_ThanhTitle.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 69, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hủy");
        pnl_ThanhTitle.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 40, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Số Lượng");
        pnl_ThanhTitle.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        pnlGioHangBody.add(pnl_ThanhTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 55));

        jsc_GioHang.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnl_RanderGioHang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_RanderGioHang.setPreferredSize(new java.awt.Dimension(666, 480));

        javax.swing.GroupLayout pnl_RanderGioHangLayout = new javax.swing.GroupLayout(pnl_RanderGioHang);
        pnl_RanderGioHang.setLayout(pnl_RanderGioHangLayout);
        pnl_RanderGioHangLayout.setHorizontalGroup(
            pnl_RanderGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        pnl_RanderGioHangLayout.setVerticalGroup(
            pnl_RanderGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        jsc_GioHang.setViewportView(pnl_RanderGioHang);

        pnlGioHangBody.add(jsc_GioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, -1, 430));

        pnlGioHang.add(pnlGioHangBody, java.awt.BorderLayout.CENTER);

        add(pnlGioHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 670, 840));
    }// </editor-fold>//GEN-END:initComponents

    void clickedDanhMuc_Ban(JPanel pnl, JLabel lbl) {
        resetClickedBan();
        resetClickedDanhMuc();
        pnl.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, (new Color(3, 155, 216))));
        lbl.setForeground((new Color(3, 155, 216)));
    }

    void resetClickedDanhMuc() {
        pnlDanhMuc_Cocktail.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblDanhMuc_Cocktail.setForeground(Color.gray);
        pnlDanhMuc_Beers.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblDanhMuc_Beers.setForeground(Color.gray);
        pnlDanhMuc_Foods.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblDanhMuc_Foods.setForeground(Color.gray);
        pnlDanhMuc_Fruits.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblDanhMuc_Fruit.setForeground(Color.gray);
        pnlDanhMuc_Other.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblDanhMuc_Other.setForeground(Color.gray);
        pnl_ChuyenBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_ChuyenBan.setForeground(Color.gray);;
        pnl_MenuBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_MenuBan.setForeground(Color.gray);;

    }

    void resetClickedBan() {
        pnlGopBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblGopBan.setForeground(Color.gray);
        pnlTachBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lblTachBan.setForeground(Color.gray);
        pnl_ThemBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_ThemBan.setForeground(Color.gray);
        pnl_XoaBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_XoaBan.setForeground(Color.gray);;
        pnl_ChuyenBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_ChuyenBan.setForeground(Color.gray);;
        pnl_MenuBan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
        lbl_MenuBan.setForeground(Color.gray);;
    }

    int countHoaDon = 0;
    int countHDCT = 0;

    public void insertHoaDonNew() {
        ListhoaDon = hoaDonDao.select();

        countHoaDon = ListhoaDon.size() + 1;
        if (lbl_MaBan.getText().equals("")) {
            MsgBox.alert(pnl_RanderGioHang, "Vui lòng chọn bàn thanh toán !");
            return;
        }
        List<SanPham_GioHang> ListSanPham_HoaDon = listGioHangSp;
        if (ListSanPham_HoaDon.size() == 0) {
            MsgBox.alert(pnl_RanderGioHang, "Bàn này chưa oder không thể thanh toán !");
            return;
        }
        // mã hóa đơn, ngày lập ,trạng thái thanh toán , mã nhân viên trạng thái thống kê

        LocalDateTime current = LocalDateTime.now();
        //sử dụng class DateTimeFormatter để định dạng ngày giờ theo kiểu pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        //sử dụng phương thức format() để định dạng ngày giờ hiện tại rồi gán cho chuỗi formatted
        String formatted = current.format(formatter);
        //hiển thị chuỗi formatted ra màn hình
        System.out.println("\n\nNgày giờ hiện tại: " + formatted);

        String maNew = "HD" + countHoaDon;
        long tien = toMoneyLong(lbl_TongTien.getText());

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("utilDate:" + utilDate);
        System.out.println("sqlDate:" + sqlDate);
        String ghiChu = areaGhiChu1.getText();
        if (ghiChu.equals("")) {
            ghiChu = null;
        }
        HoaDon hoaDonNew = new HoaDon(maNew, formatted, "Đã thanh toán", "NV01", true, ghiChu, tien);
        hoaDonDao.insert(hoaDonNew);
        for (int i = 0; i < listGioHangSp.size(); i++) {
            String maHDCT = "";
            ListhoaDonCT = hoaDonCTDao.select();
            countHDCT = ListhoaDonCT.size() + 1;
            maHDCT = "HDCT" + countHDCT;
            HoaDonChiTiet hdct = new HoaDonChiTiet(maHDCT, listGioHangSp.get(i).getTenSP(), listGioHangSp.get(i).getSoLuong(), listGioHangSp.get(i).getGia(), maNew, listGioHangSp.get(i).getMasp());
            hoaDonCTDao.insert(hdct);
        }

        cancelOder();
        fillRanderBan();
        fillRanderGioHang();
        // showPanel(new QL_HoaDon());
    }


    private void pnlDanhMuc_CocktailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhMuc_CocktailMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Cocktail, lblDanhMuc_Cocktail);
        String tool = "DM01";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_pnlDanhMuc_CocktailMouseClicked

    private void pnlDanhMuc_BeersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhMuc_BeersMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Beers, lblDanhMuc_Beers);
        String tool = "DM02";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_pnlDanhMuc_BeersMouseClicked

    private void pnlDanhMuc_FoodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhMuc_FoodsMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Foods, lblDanhMuc_Foods);
        String tool = "DM03";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_pnlDanhMuc_FoodsMouseClicked

    private void pnlDanhMuc_FruitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhMuc_FruitsMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Fruits, lblDanhMuc_Fruit);
        String tool = "DM04";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_pnlDanhMuc_FruitsMouseClicked

    private void pnlDanhMuc_OtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDanhMuc_OtherMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Other, lblDanhMuc_Other);
        String tool = "DM05";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_pnlDanhMuc_OtherMouseClicked

    private void pnlGopBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGopBanMouseClicked
        clickedDanhMuc_Ban(pnlGopBan, lblGopBan);
        showPanel(new pnl_GopBan());
    }//GEN-LAST:event_pnlGopBanMouseClicked


    private void pnlTachBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTachBanMouseClicked
        clickedDanhMuc_Ban(pnlTachBan, lblTachBan);

        showPanel(new pnl_tachBan());
    }//GEN-LAST:event_pnlTachBanMouseClicked

    private void pnl_XoaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_XoaBanMouseClicked
        clickedDanhMuc_Ban(pnl_XoaBan, lbl_XoaBan);
        this.deleteBan();
    }//GEN-LAST:event_pnl_XoaBanMouseClicked

    private void lbl_anhCocktailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_anhCocktailMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Cocktail, lblDanhMuc_Cocktail);
        String tool = "DM01";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_lbl_anhCocktailMouseClicked

    private void lblDanhMuc_BeersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMuc_BeersMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Beers, lblDanhMuc_Beers);
        String tool = "DM02";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_lblDanhMuc_BeersMouseClicked

    private void lblDanhMuc_FoodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMuc_FoodsMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Foods, lblDanhMuc_Foods);
        String tool = "DM03";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_lblDanhMuc_FoodsMouseClicked

    private void lblDanhMuc_FruitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMuc_FruitMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Fruits, lblDanhMuc_Fruit);
        String tool = "DM04";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_lblDanhMuc_FruitMouseClicked

    private void lblDanhMuc_OtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDanhMuc_OtherMouseClicked
        clickedDanhMuc_Ban(pnlDanhMuc_Other, lblDanhMuc_Other);
        String tool = "DM05";
        locSanPhamDanhMuc(tool);
        fillRanderSP();
    }//GEN-LAST:event_lblDanhMuc_OtherMouseClicked

    private void lbl_LuuGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_LuuGioHangMouseClicked
        this.saveOderToData();
    }//GEN-LAST:event_lbl_LuuGioHangMouseClicked

    private void lbl_HuyOderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HuyOderMouseClicked
        boolean check = MsgBox.confirm(pnl_RanderGioHang, "Bạn có chắc muốn hủy Oder bàn " + lbl_MaBan.getText());
        if (check) {
            this.cancelOder();
            lbl_MaBan.setText("");
        }

    }//GEN-LAST:event_lbl_HuyOderMouseClicked
    int checkOnFormThemBan = 0;
    FormThemBan themBan = new FormThemBan();
    private void lbl_themBanMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_themBanMouseClick
        clickedDanhMuc_Ban(pnl_ThemBan, lbl_ThemBan);

        checkOnFormThemBan++;
        if (checkOnFormThemBan % 2 != 0) {
            themBan.pack();

            themBan.setVisible(true);
        } else {
            themBan.setVisible(false);
            themBan.dispose();
            fillRanderBan();
            resetClickedBan();
        }


    }//GEN-LAST:event_lbl_themBanMouseClick

    private void ChuyenBanMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChuyenBanMouseClick
        clickedDanhMuc_Ban(pnl_ChuyenBan, lbl_ChuyenBan);
        showPanel(new pnl_ChuyenBan());

    }//GEN-LAST:event_ChuyenBanMouseClick

    private void pnlMenuBanMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuBanMouseClick
        clickedDanhMuc_Ban(pnl_MenuBan, lbl_MenuBan);
        fillRanderBan();
        showScoll(jcp_CuonBan);
    }//GEN-LAST:event_pnlMenuBanMouseClick

    private void lbl_ThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThanhToanMouseClicked
        this.insertHoaDonNew();
    }//GEN-LAST:event_lbl_ThanhToanMouseClicked
    int demGhiChu = 1;
    private void lbl_GhiChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_GhiChuMouseClicked
        demGhiChu++;

        if (demGhiChu % 2 == 0) {
            txt_ghiChu.setVisible(true);

        } else {
            txt_ghiChu.setVisible(false);

            demGhiChu = 1;
        }

    }//GEN-LAST:event_lbl_GhiChuMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        showPanel(new PNL_SanPham());
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_ChonBan;
    private javax.swing.JPanel Panel_ChonMon;
    private com.bar.customUI.TextArea areaGhiChu1;
    private javax.swing.JScrollPane cp_cuon;
    private javax.swing.JLabel icon_Ban;
    private javax.swing.JLabel icon_ChuyenBan;
    private javax.swing.JLabel icon_GopBan;
    private javax.swing.JLabel icon_TachBan;
    private javax.swing.JLabel icon_ThemBan;
    private javax.swing.JLabel icon_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JScrollPane jcp_CuonBan;
    private javax.swing.JScrollPane jsc_GioHang;
    private javax.swing.JLabel lblDanhMuc_Beers;
    private javax.swing.JLabel lblDanhMuc_Cocktail;
    private javax.swing.JLabel lblDanhMuc_Foods;
    private javax.swing.JLabel lblDanhMuc_Fruit;
    private javax.swing.JLabel lblDanhMuc_Other;
    private javax.swing.JLabel lblGopBan;
    private javax.swing.JLabel lblTachBan;
    private javax.swing.JLabel lbl_AnhBia;
    private javax.swing.JLabel lbl_ChuyenBan;
    private javax.swing.JLabel lbl_GhiChu;
    private javax.swing.JLabel lbl_HuyOder;
    private javax.swing.JLabel lbl_LuuGioHang;
    private javax.swing.JLabel lbl_MaBan;
    private javax.swing.JLabel lbl_MenuBan;
    private javax.swing.JLabel lbl_ThanhTien;
    private javax.swing.JLabel lbl_ThanhToan;
    private javax.swing.JLabel lbl_ThemBan;
    private javax.swing.JLabel lbl_Thue;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JLabel lbl_XoaBan;
    private javax.swing.JLabel lbl_anhCocktail;
    private javax.swing.JLabel lbl_anhFood;
    private javax.swing.JLabel lbl_anhOther;
    private javax.swing.JLabel lbl_anhTraiCay;
    private com.bar.customUI.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel pnlDanhMucSP;
    private javax.swing.JPanel pnlDanhMuc_Beers;
    private javax.swing.JPanel pnlDanhMuc_Cocktail;
    private javax.swing.JPanel pnlDanhMuc_Foods;
    private javax.swing.JPanel pnlDanhMuc_Fruits;
    private javax.swing.JPanel pnlDanhMuc_Other;
    private javax.swing.JPanel pnlGioHang;
    private javax.swing.JPanel pnlGioHangBody;
    private javax.swing.JPanel pnlGioHangFooterr;
    private javax.swing.JPanel pnlGioHangHeader;
    private javax.swing.JPanel pnlGopBan;
    private javax.swing.JPanel pnlHandlerBan;
    private javax.swing.JPanel pnlTachBan;
    private javax.swing.JPanel pnl_CardShow;
    private javax.swing.JPanel pnl_ChuyenBan;
    private javax.swing.JPanel pnl_HuyOder;
    private javax.swing.JPanel pnl_LuuOder;
    private javax.swing.JPanel pnl_MenuBan;
    private javax.swing.JPanel pnl_RanderBan;
    private javax.swing.JPanel pnl_RanderGioHang;
    private javax.swing.JPanel pnl_RanderSp;
    private javax.swing.JPanel pnl_ThanhTitle;
    private javax.swing.JPanel pnl_ThanhToan;
    private javax.swing.JPanel pnl_ThemBan;
    private javax.swing.JPanel pnl_XoaBan;
    private com.bar.customUI.TextAreaScroll txt_ghiChu;
    // End of variables declaration//GEN-END:variables
}
