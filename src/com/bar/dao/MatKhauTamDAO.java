/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.dao;

import com.bar.model.MatKhauTam;
import com.bar.util.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class MatKhauTamDAO extends DejavuDAO<MatKhauTam, String> {

    String INSERT_SQL = "INSERT INTO MatKhauTam(PassTam,TenTaiKhoan) VALUES (?,?)";
    String DELETE_SQL = "DELETE FROM MatKhauTam";
    String UPDATE_SQL = "UPDATE MatKhauTam SET PassTam = ?, TenTaiKhoan = ?";
    String SELECT_ALL_PASSTAM = "SELECT * FROM MatKhauTam";

    @Override
    public void insert(MatKhauTam entity) {
        try {
            JdbcHelper.update(INSERT_SQL,
                    entity.getPassTam(),entity.getTenTaiKhoanTam());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(MatKhauTam entity) {
        try {
            JdbcHelper.update(UPDATE_SQL,
                    entity.getPassTam(),entity.getTenTaiKhoanTam());
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<MatKhauTam> select() {
        return this.selectBySql(SELECT_ALL_PASSTAM);
    }


    @Override
    protected List<MatKhauTam> selectBySql(String sql, Object... args) {
        List<MatKhauTam> list = new ArrayList<MatKhauTam>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                MatKhauTam entity = new MatKhauTam();
                entity.setPassTam(rs.getString("PassTam"));
                entity.setTenTaiKhoanTam(rs.getString("TenTaiKhoan"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
         try {
            JdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(MatKhauTamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public MatKhauTam selectID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
