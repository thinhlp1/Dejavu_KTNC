
package com.dejavu.test.model;

import com.bar.model.NhanVien;
import org.junit.Test;
import static org.junit.Assert.*;

public class QLNhanVienTest {
    @Test
    public void testMaNhanVien(){
       NhanVien nv = new NhanVien();
        String expected = "NV01";
        assertEquals(expected, nv.checkMaNV("NV01"));
    }
        
    @Test
    public void testHoTenNhanVien(){
       NhanVien nv = new NhanVien();
        String expected = "Nguyễn Thị An";
        assertEquals(expected, nv.checkTenNV("Nguyễn","Thị An"));
    }    
        
    @Test
    public void testChucVu(){
       NhanVien nv = new NhanVien();
        assertTrue(nv.checkChucVu("Quản lý"));
    }    
}
