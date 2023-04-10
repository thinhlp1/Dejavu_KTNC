package com.bar.dao;

import com.bar.model.Ban;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanDAO extends DejavuDAO<Ban, String> {

    String INSERT = "Insert into Ban (MaBan , TrangThai , MaNV) Values (? ,? ,?)";
    String selectAll = "select * from ban";
    String deleteID = "delete from ban where maBan = ?";
    String selectID = "select * from ban where MaBan = ?";
    String updateSQL = "update ban set TrangThai = ? , MaNV = ? where MaBan = ?";
    String updateTrangThai = "update ban set TrangThai = ? where maBan = ?";
    String selectNhanVien = "select MaNV from ban";
    String selectTrangThai = "select TrangThai From ban where maBan = ?";
    String selectBanAll_BanOder = "select b.MABAN , b.MANV , b.TrangThai , bo.MaBanGop from ban b inner join BAN_ODER bo on b.maBan = bo.MaBan ";
    String selectBan = "select * from ban where TrangThai = ?";

    @Override
    public void insert(Ban model) {
        try {
            JdbcHelper.update(INSERT,
                    model.getMaBan(),
                    model.isTrangThai(),
                    model.getMaNhanVien());

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            JdbcHelper.update(deleteID, id);
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(Ban model) {
        try {
            JdbcHelper.update(updateSQL,
                    model.isTrangThai(),
                    model.getMaNhanVien(),
                    model.getMaBan()
            );
        } catch (SQLException e) {
        }
    }

    public void updateTrangThai(Ban model) {
        try {
            JdbcHelper.update(updateTrangThai,
                    model.isTrangThai(),
                    model.getMaBan()
            );
        } catch (SQLException e) {
        }
    }

    @Override
    public Ban selectID(String id) {
        List<Ban> list = this.selectBySql(selectID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Ban selectTrangThai(String ma) {
        List<Ban> list = this.selectBySql(selectID, ma);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Ban> select() {
        return this.selectBySql(selectAll);  //To change body of generated methods, choose Tools | Templates.
    }

    public List<Ban> selectALL_BanOder() {
        return this.selectBySql_BanOder(selectBanAll_BanOder);  //To change body of generated methods, choose Tools | Templates.
    }

    public List<Ban> selectBan(boolean trangThai) {
        return this.selectBySql(selectBan, trangThai);
    }

    @Override
    protected List<Ban> selectBySql(String sql, Object... args) {
        List<Ban> list = new ArrayList<Ban>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                Ban model = new Ban();
                model.setMaBan(result.getString("MaBan"));
                model.setTrangThai(result.getBoolean("TrangThai"));
                model.setMaNhanVien(result.getString("MaNV"));
                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }

    protected List<Ban> selectBySql_BanOder(String sql, Object... args) {
        List<Ban> list = new ArrayList<Ban>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                Ban model = new Ban();
                model.setMaBan(result.getString("MaBan"));
                model.setTrangThai(result.getBoolean("TrangThai"));
                model.setMaNhanVien(result.getString("MaNV"));
                model.setMaNhanVien(result.getString("MaBanGop"));
                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
}
