package com.bar.dao;

import com.bar.model.HoaDon;
import com.bar.model.HoaDonChiTiet;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO extends DejavuDAO<HoaDonChiTiet, String> {

    String SELECT_ALL_SQL = "SELECT * FROM HOADONCHITIET";
    String SELECT_BY_MAHD_SQL = "SELECT * FROM HOADONCHITIET WHERE MAHD = ?";
    String INSERT = "INSERT INTO HOADONCHITIET VALUES (?,?,?,?,?,?)";

    @Override
    public void insert(HoaDonChiTiet entity) {
        try {
            JdbcHelper.update(INSERT,
                    entity.getMaHDCT(),
                    entity.getTenSP(),
                    entity.getSoLuong(),
                    entity.getDonGia(),
                    entity.getMaHD(),
                    entity.getMaSP()
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
    public void update(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> select() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<HoaDonChiTiet> selectBy_MaHD(String maHD) {
        return this.selectBySql(SELECT_BY_MAHD_SQL, maHD);
    }

    @Override
    public HoaDonChiTiet selectID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<HoaDonChiTiet>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HoaDonChiTiet entity = new HoaDonChiTiet();
                entity.setMaHDCT(rs.getString(1));
                entity.setTenSP(rs.getString(2));
                entity.setSoLuong(rs.getInt(3));
                entity.setDonGia(rs.getLong(4));
                entity.setMaHD(rs.getString(5));
                entity.setMaSP(rs.getString(6));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
