package com.bar.dao;

import com.bar.model.TaiKhoan;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO extends DejavuDAO<TaiKhoan, String> {

    String INSERT_SQL = "INSERT INTO TaiKhoanNV(TenTK, MatKhau,  MaNV) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE TaiKhoanNV SET MatKhau = ? WHERE TenTK = ?";
    String UPDATE_USERNAME_SQL = "UPDATE TaiKhoanNV SET TenTK = ? WHERE MaNV = ?";
    String DELETE_SQL = "DELETE FROM TaiKhoanNV WHERE TenTK = ?";
    String SELECT_ALL_TK_SQL = "SELECT * FROM TAIKHOANNV";
    String SELECT_BY_NAME_ACCOUNT_SQL = "SELECT * FROM TAIKHOANNV WHERE TenTK = ?";
    String SELECT_BY_ID_SQL = "SELECT * FROM TAIKHOANNV WHERE MANV = ?";

    @Override
    public void insert(TaiKhoan entity) {
        try {
            JdbcHelper.update(INSERT_SQL,
                    entity.getTenTaiKhoan(), entity.getMatKhau(), entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(TaiKhoan entity) {
        try {
            JdbcHelper.update(UPDATE_USERNAME_SQL,
                    entity.getTenTaiKhoan(), entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update_Pass(TaiKhoan entity) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                    entity.getMatKhau(), entity.getTenTaiKhoan());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<TaiKhoan> select() {
        return this.selectBySql(SELECT_ALL_TK_SQL);
    }

    public TaiKhoan selectNAME(String id) {
        List<TaiKhoan> list = this.selectBySql(SELECT_BY_NAME_ACCOUNT_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public TaiKhoan selectID(String id) {
        List<TaiKhoan> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<TaiKhoan>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setTenTaiKhoan(rs.getString("TENTK"));
                entity.setMatKhau(rs.getString("MATKHAU"));
                entity.setMaNV(rs.getString("MANV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
