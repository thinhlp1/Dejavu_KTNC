/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test.utils;

import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;
import com.bar.util.Auth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PHUOCTAI
 */
public class AuthUtilsTest {

    Auth author = new Auth();
    NhanVien nv1;
    NhanVien nv2;
    TaiKhoan tk1;

    @Before
    public void setUp() {
        nv1 = new NhanVien("NV10", "Nguyễn Văn", "Test", "Quản lý");
        nv2 = new NhanVien("NV12", "Trần Thị", "Test", "Nhân viên");
        tk1 = new TaiKhoan("user1", "12345", "NV10");
        author.user = new NhanVien(nv1.getMaNV(), nv1.getHoTenLot(), nv1.getTenNV(), nv1.getChucVu());
        author.account = new TaiKhoan(tk1.getTenTaiKhoan(), tk1.getMatKhau(), tk1.getMaNV());
    }

    @Test
    public void testClear() {
        author.clear();
        assertNull(author.user);
        assertNull(author.account);
    }

    @Test
    public void testIsLogin() {
        String expected = "Quản lý";
        assertEquals(expected, author.isLogin());
        assertNotNull(author.user);
        assertNotNull(author.account);
    }

    @Test
    public void testGetUserName() {
        String expected = author.account.getTenTaiKhoan();
        assertEquals(expected, author.userName());
    }

    @Test
    public void testIsManagerRole() {
        //User 1
        assertTrue(author.isManager());
        //User 2
        author.user = new NhanVien(nv2.getMaNV(), nv2.getHoTenLot(), nv2.getTenNV(), nv2.getChucVu());
        assertFalse(author.isManager());
    }
}
