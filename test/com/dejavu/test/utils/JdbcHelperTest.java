/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.utils;

import com.bar.util.JdbcHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nguye
 */
public class JdbcHelperTest {

    @Test
    public void testGetStmt() throws SQLException {
        String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
        String expectedMaHD = "HD001";
        PreparedStatement stmt = JdbcHelper.getStmt(sql, expectedMaHD);
        ResultSet rs = stmt.executeQuery();
        String actualMaHD = null;
        while (rs.next()) {
            actualMaHD = rs.getString("MaHD");
        }
        assertEquals(expectedMaHD, actualMaHD);
        rs.getStatement().getConnection().close();
    }

    @Test
    public void testUpdate() throws SQLException {
        String sql = "UPDATE HoaDon SET TrangThai = ? WHERE MaHD = ?";
        Object[] args = {"Đã thanh toán", "HD001"};
        int expectedRowsAffected = 1;
        int actualRowsAffected = JdbcHelper.update(sql, args);
        assertEquals(expectedRowsAffected, actualRowsAffected);
    }

    @Test
    public void testQuery() throws SQLException {
        String sql = "SELECT * FROM HoaDon WHERE MaHD = ?";
        String expectedMaHD = "HD001";
        ResultSet rs = JdbcHelper.query(sql, expectedMaHD);
        String actualMaHD = null;
        while (rs.next()) {
            actualMaHD = rs.getString("MaHD");
        }
        assertEquals(expectedMaHD, actualMaHD);
        rs.getStatement().getConnection().close();
    }

    @Test
    public void testValue() throws SQLException {
        String sql = "SELECT COUNT(*) FROM SanPham";
        int expectedCount = 46;
        Object actualCount = JdbcHelper.value(sql);
        assertEquals(expectedCount, actualCount);
    }
}
