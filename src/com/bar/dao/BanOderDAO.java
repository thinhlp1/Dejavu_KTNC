package com.bar.dao;

import com.bar.model.Ban;
import com.bar.model.BanOder;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanOderDAO extends DejavuDAO<BanOder, String> {

    String selectAll = "Select * from Ban_Oder";
    String INSERT = "INSERT INTO Ban_Oder (MaBan, GhiChu, MaBanGop, Voucher)VALUES( ?,  ?,  ?,  ?)";
    String deleteID = "DELETE FROM BAN_ODER WHERE MABAN = ?";
    String updateSQL = "";
    String selectById = "SELECT * FROM Ban_Oder WHERE MaBan=?";
    String updateMaBanGop = "Update Ban_oder set maBanGop = ? where MaBan = ?";
    String selectBanOderNotNull = "select * from BAN_ODER where MaBanGop  is not null";
    String updateGhiChu = "update Ban_oder set GhiChu = ? where MaBan = ?";

    @Override
    public void insert(BanOder model) {
        try {
            JdbcHelper.update(INSERT,
                    model.getMaBan(),
                    model.getGhiChu(),
                    model.getMaBanGop(),
                    model.getVoucher());

        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(String MaBan) {
        try {
            JdbcHelper.update(deleteID, MaBan);
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(BanOder model) {
        try {
            JdbcHelper.update(updateSQL,
                    model.getMaBan(),
                    model.getGhiChu(),
                    model.getMaBanGop(),
                    model.getVoucher()
            );
        } catch (SQLException e) {
        }
    }

    public void updateGhiChu(String ghiChu, String maBan) {
        try {
            JdbcHelper.update(updateGhiChu,
                ghiChu,
                maBan
        );
        } catch (Exception e) {
        }
        

    }

    public void updateMaBanOder(BanOder model) {
        try {
            JdbcHelper.update(updateMaBanGop,
                    model.getMaBanGop(),
                    model.getMaBan()
            );
        } catch (SQLException e) {
        }
    }

    @Override
    public BanOder selectID(String MaBan) {
        List<BanOder> list = this.selectBySql(selectById, MaBan);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BanOder> select() {
        return this.selectBySql(selectAll);  //To change body of generated methods, choose Tools | Templates.
    }

    public List<BanOder> selectMBGopNotNull() {
        return this.selectBySql(selectBanOderNotNull);  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<BanOder> selectBySql(String sql, Object... args) {
        List<BanOder> list = new ArrayList<BanOder>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                BanOder model = new BanOder();
                model.setMaBan(result.getString("MaBan"));
                model.setGhiChu(result.getString("GhiChu"));
                model.setMaBanGop(result.getString("MaBanGop"));
                model.setVoucher(result.getString("Voucher"));
                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
}
