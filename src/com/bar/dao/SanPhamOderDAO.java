package com.bar.dao;

import com.bar.model.SanPham;
import com.bar.model.SanPham_GioHang;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamOderDAO extends DejavuDAO<SanPham_GioHang, String> {

    String INSERT_SQL = "INSERT INTO SanPham_GioHang (MaBan, Hinh, MaSP, TenSp, Gia , SoLuong) VALUES( ?,  ?,  ?, ?, ?, ?)";
    String DELETE_SQL_MABAN = "DELETE FROM SanPham_GioHang WHERE MaBan = ?";
    String DELETE_SQL_MASP = "DELETE FROM SanPham_GioHang WHERE MaSp = ?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham_GioHang";
    String selectByMaBan = "SELECT * FROM SanPham_GioHang WHERE MaBan=?";
    String selectMaBan = "SELECT MaBan FROM SanPham_GioHang";
    String updateSoLuong = "Updete SanPham_GioHang Set Soluong = ? Where Masp = ?";

    

    @Override
    public void insert(SanPham_GioHang model) {
        try {
            JdbcHelper.update(INSERT_SQL,
                    model.getMaBan(),
                    model.getHinh(),
                    model.getMasp(),
                    model.getTenSP(),
                    model.getGia(),
                    model.getSoLuong()
            );
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void delete(String MaBan) {
        try {
            JdbcHelper.update(DELETE_SQL_MABAN, MaBan);
        } catch (SQLException e) {
        }
    }

    public void deleteSP(String MaSp) {
        try {
            JdbcHelper.update(DELETE_SQL_MASP, MaSp);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(SanPham_GioHang model) {
        try {
            JdbcHelper.update(updateSoLuong,
                    model.getSoLuong(),
                    model.getMasp()
            );

        } catch (SQLException e) {
        }
    }


    @Override
    public List<SanPham_GioHang> select() {
        return this.selectBySql(selectMaBan);
    }

    @Override
    public SanPham_GioHang selectID(String MaBan) {
        List<SanPham_GioHang> list = this.selectBySql(selectByMaBan, MaBan);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    // select theo tung ma bàng hiện sp lên giỏ hàng
    public List<SanPham_GioHang> selectSpMaBan(String MaBan) {
        return this.selectBySql(selectByMaBan, MaBan);
    }

    @Override
    protected List<SanPham_GioHang> selectBySql(String sql, Object... args) {
        List<SanPham_GioHang> list = new ArrayList<SanPham_GioHang>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                SanPham_GioHang model = new SanPham_GioHang();
                model.setMaBan(result.getString("MaBan"));
                model.setHinh(result.getString("Hinh"));
                model.setGia(result.getLong("Gia"));
                model.setMasp(result.getString("MaSp"));
                model.setTenSP(result.getString("TenSP"));
                model.setSoLuong(result.getInt("SoLuong"));

                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
