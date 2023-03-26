/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.dao;

import com.bar.util.JdbcHelper;
import com.bar.model.DanhMuc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DanhMucDAO extends DejavuDAO<DanhMuc, String>{
    String INSERT_SQL = "INSERT INTO DANHMUC (MADM, TENDM)VALUES( ?,  ?)";
    String UPDATE_SQL = "UPDATE DANHMUC SET TENDM = ? WHERE  MADM =  ? ";
    String DELETE_SQL = "DELETE FROM DANHMUC WHERE MADM=?";
    String SELECT_ALL_SQL = "SELECT * FROM DANHMUC";
    String selectById = "SELECT * FROM DANHMUC WHERE MADM=?"; 
    String SELECT_BY_KEYWORD = "SELECT * FROM DANHMUC WHERE TENDM LIKE ?";
    String selectMaDanhMuc = "SELECT MADM FROM DANHMUC WHERE TENDM = ?";
     
    @Override
    public void insert(DanhMuc model) {
         try {
            JdbcHelper.update(INSERT_SQL,
                    model.getMaDanhMuc(),
                    model.getTenDanhMuc());
                   
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
    public void update(DanhMuc model) {
         try {
            JdbcHelper.update(UPDATE_SQL,
                    model.getTenDanhMuc(),
                    model.getMaDanhMuc()
                    );
        } catch (SQLException e) {
        }
    }

    @Override
    public DanhMuc selectID(String id) {
        List<DanhMuc> list = this.selectBySql(selectById, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DanhMuc> select() {
        return this.selectBySql(SELECT_ALL_SQL);  //To change body of generated methods, choose Tools | Templates.
    }
    public List<DanhMuc>selectByKeyword(String keyword) {
        return this.selectBySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }
    public List<DanhMuc>selectMaDanhMuc(String keyword) {
        return this.selectBySql(selectMaDanhMuc, keyword );
    }
    @Override
    protected List<DanhMuc> selectBySql(String sql, Object... args) {
         List<DanhMuc> list = new ArrayList<DanhMuc>();
        try {
            ResultSet result = JdbcHelper.query(sql, args);
            while (result.next()) {
                DanhMuc model = new DanhMuc();
                model.setMaDanhMuc(result.getString("MADM"));
                model.setTenDanhMuc(result.getString("TENDM"));
                list.add(model);
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
}
