/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.dao;

import com.bar.util.JdbcHelper;
import com.bar.model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends DejavuDAO<SanPham, String> {

    String INSERT_SQL = "INSERT INTO SANPHAM (MASP, TENSP, DONGIA, GHICHU, HINH, MADM)VALUES( ?,  ?,  ?,  ?,  ?,  ?)";
    String UPDATE_SQL = "UPDATE SANPHAM SET TENSP=?, DONGIA=?, GHICHU=?, HINH=? ,MADM =? WHERE  MaSP =  ? ";
    String DELETE_SQL = "DELETE FROM SANPHAM WHERE MaSP=?";
    String SELECT_ALL_SQL = "SELECT * FROM SANPHAM";
    String selectById = "SELECT * FROM SANPHAM WHERE MaSP=?";
    String SELECT_BY_KEYWORD = "SELECT * FROM SANPHAM WHERE MASP LIKE ?";
    String SELECT_BY_KEYWORD2 = "SELECT * FROM SANPHAM WHERE TENSP LIKE ?";
    String SELECT_BY_LOC = "SELECT * FROM SANPHAM WHERE MaDM = ?";
    String selectDM = "SELECT TENDM FROM DANHMUC";
    String selectMaDanhMuc = "SELECT * FROM SANPHAM WHERE MADM = ?";
    
//     public List<DanhMuc> selectDM() {
//         return this.selectBySql(SELECT_ALL_SQL);    }
    @Override
    public void insert(SanPham model) {
       try {
            JdbcHelper.update(INSERT_SQL,
                    model.getMaMon(),
                    model.getTenMon(),
                    model.getGia(),
                    model.getGhiChu(),
                    model.getHinh(),
                    model.getDanhMuc()
            );
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(String id) {
         try {
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(SanPham model) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                   
                    model.getTenMon(),
                    model.getGia(),
                    model.getGhiChu(),
                    model.getHinh(),
                    model.getDanhMuc(),
                    model.getMaMon());
                    
        } catch (SQLException e) {
        }

    }

     public List<SanPham> selectMaDanhMuc(String MaDM) {
         return this.selectBySql(selectMaDanhMuc , MaDM);  
  }
    public List<SanPham> selectAll() {
         return this.selectBySql(SELECT_ALL_SQL);    }
    @Override
    public List<SanPham> select() {
         return this.selectBySql(selectDM);    }
     public List<SanPham>selectByKeyword(String keyword) {
        return this.selectBySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }
     public List<SanPham>selectByKeyword2(String keyword) {
        return this.selectBySql(SELECT_BY_KEYWORD2, "%" + keyword + "%");
    }
     public List<SanPham>selectByLoc(String keyword){
         return this.selectBySql(SELECT_BY_LOC, keyword);
     }
    @Override
    public SanPham selectID(String id) {
         List<SanPham> list = this.selectBySql(selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
         List<SanPham> list = new ArrayList<SanPham>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                SanPham model = new SanPham();
                model.setMaMon(result.getString("MASP"));
                model.setTenMon(result.getString("TENSP"));
                model.setGia(result.getLong("DONGIA"));
                model.setGhiChu(result.getString("GHICHU"));
                model.setHinh(result.getString("HINH"));
                model.setDanhMuc(result.getString("MADM"));
                model.setGhiChu(result.getString("GHICHU"));
                model.setDanhMuc(result.getString("MADM"));
                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
