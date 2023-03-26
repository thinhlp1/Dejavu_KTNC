package com.bar.dao;

import java.util.List;
import com.bar.model.HoaDon;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO extends DejavuDAO<HoaDon, String> {

    String UPDATE_TRANGTHAI_SQL = "UPDATE HOADON SET TRANGTHAI_THONGKE = ? WHERE MAHD = ?";
    String SELECT_ALL_SQL = "SELECT * FROM HOADON";
    String SELECT_BY_ID_SQL = "SELECT * FROM HOADON WHERE MAHD = ?";
    String SELECT_BY_IDNV_SQL = "SELECT * FROM HOADON WHERE MANV = ?";
    String SELECT_BY_TRANGTHAITHONGKE_SQL = "SELECT * FROM HOADON WHERE TRANGTHAI_THONGKE = ?";
    String SELECT_BY_NGAYLAPHOADON_SQL = "select * from HOADON where DAY(NGAYLAPHOADON) = ? and MONTH(NGAYLAPHOADON) = ? and YEAR(NGAYLAPHOADON) = ?";
    String INSERT = "INSERT INTO HOADON VALUES (?,?,?,?,?,?,?)";

    @Override
    public void insert(HoaDon entity) {
        try {
            JdbcHelper.update(INSERT, entity.getMaHD(),
                    entity.getNgayLapHD(),
                    entity.getTrangThaiThanhToan(),
                    entity.getMaNV(),
                    entity.isTrangThai_ThongKe(),
                    entity.getGhiChu(),
                    entity.getTongTien()
            );

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDon entity) {
        try {
            JdbcHelper.update(UPDATE_TRANGTHAI_SQL,
                    entity.isTrangThai_ThongKe(),
                    entity.getMaHD());
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<HoaDon> select() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectID(String id) {
        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<HoaDon> selectBY_IDNV(String id) {
        return this.selectBySql(SELECT_BY_IDNV_SQL, id);
    }

    public List<HoaDon> selectBY_TrangThaiThongKe(int thongKe) {
        return this.selectBySql(SELECT_BY_TRANGTHAITHONGKE_SQL, thongKe);
    }

    public List<HoaDon> selectBY_NgayLapHoaDon(String day , String month , String year) {
        return this.selectBySql(SELECT_BY_NGAYLAPHOADON_SQL, day , month , year);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString(1));
                entity.setNgayLapHD(String.valueOf(rs.getDate(2)));
                entity.setTrangThaiThanhToan(rs.getString(3));
                entity.setMaNV(rs.getString(4));
                entity.setTrangThai_ThongKe(rs.getBoolean(5));
                entity.setGhiChu(rs.getString(6));
                entity.setTongTien(rs.getLong(7));
                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
