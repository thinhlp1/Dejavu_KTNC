package com.dejavu.test.model;

import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;
import org.junit.Test;
import static org.junit.Assert.*;

public class DangNhapTest {

    NhanVien nhanVien = new NhanVien();

    @Test
    public void testNullUsername() {
        TaiKhoan taiKhoan = new TaiKhoan();
        assertTrue(taiKhoan.checkNullUsername(""));
    }

    @Test
    public void testNullPassword() {
        TaiKhoan taiKhoan = new TaiKhoan();
        assertTrue(taiKhoan.checkNullPass(""));
    }

    @Test
    public void testValidUser() {
        TaiKhoan taiKhoan = new TaiKhoan();
        String expected = "Valid";
        assertEquals(expected, taiKhoan.checkValidUsername("admin"));
    }
    
    @Test
    public void testValidPass() {
        TaiKhoan taiKhoan = new TaiKhoan();
        String expected = "Valid";
        assertEquals(expected, taiKhoan.checkValidPass("123333333333"));
    }
    
    @Test
    public void testLogin() {
        TaiKhoan taiKhoan = new TaiKhoan();
        assertTrue(taiKhoan.checkLogin("admin", "12345"));
    }

}
