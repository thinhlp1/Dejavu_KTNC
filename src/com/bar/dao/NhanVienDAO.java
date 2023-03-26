package com.bar.dao;

import com.bar.model.NhanVien;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO extends DejavuDAO<NhanVien, String> {

    String INSERT_SQL = "";
    String UPDATE_SQL = "";
    String DELETE_SQL = "";
    String SELECT_ALL_NV_SQL = "SELECT * FROM NHANVIEN";
    String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE MaNV = ?";
    String CHECK_NV_EXSITED = "SELECT * FROM NHANVIEN WHERE MANV NOT IN (SELECT MANV FROM TAIKHOANNV)";

    @Override

    public void insert(NhanVien entity) {
        try {
            JdbcHelper.update(INSERT_SQL,
                    entity.getMaNV(), entity.getHoTenLot(), entity.getTenNV());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien entity) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                    entity.getMaNV(), entity.getHoTenLot(), entity.getTenNV());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhanVien> select() {
        return this.selectBySql(SELECT_ALL_NV_SQL);
    }
    
        public List<NhanVien> selectCheckNV() {
        return this.selectBySql(CHECK_NV_EXSITED);
    }

    @Override
    public NhanVien selectID(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MANV"));
                entity.setHoTenLot(rs.getString("HOTENLOT"));
                entity.setTenNV(rs.getString("TENNV"));
                entity.setChucVu(rs.getString("CHUCVU"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
