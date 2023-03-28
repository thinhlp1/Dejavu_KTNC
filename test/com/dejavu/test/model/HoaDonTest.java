/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.model;

import com.bar.model.HoaDon;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class HoaDonTest {
    
    HoaDon hd;
    
    @Before
    public void setUp(){
        hd = new HoaDon();
    }
    
    @Test
    public void testGhiChu(){
        hd.setGhiChu("Nước ít đá");
        String expected = "Nước ít đá";
        String actual = hd.getGhiChu();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMaHD(){
        hd.setMaHD("HD001");
        String expected = "HD001";
        String actual = hd.getMaHD();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNgayLapHD(){
        hd.setNgayLapHD("28/03/2023");
        String expected = "28/03/2023";
        String actual = hd.getNgayLapHD();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTrangThaiThanhToan(){
        hd.setTrangThaiThanhToan("Đã thanh toán");
        String expected = "Đã thanh toán";
        String actual = hd.getTrangThaiThanhToan();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMaNV(){
        hd.setMaNV("NV01");
        String expected = "NV01";
        String actual = hd.getMaNV();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTrangThaiThongKe(){
        hd.setTrangThai_ThongKe(true);
        boolean expected = true;
        boolean actual = hd.isTrangThai_ThongKe();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTongTien(){
        hd.setTongTien(500000);
        long expected = 500000;
        long actual = hd.getTongTien();
        assertEquals(expected, actual);
    }
    
    //Tìm kiếm theo mã nhân viên
    @Test
    public void testSearchTheoTenNV(){
        String expected = "NV01";
        assertEquals(expected, hd.searchTheoTenNV(expected));
    }
    
    //Tìm kiếm theo mã nhân viên không hợp lệ
    @Test
    public void testSearchTheoTenNV_Invalid(){
        String expected = "NV100";
        assertNotEquals(expected, hd.searchTheoTenNV(expected));
    }
    
    //Tìm kiếm theo trạng thái thống kê hợp lệ
    @Test
    public void testSearchTTTK(){
        boolean expected = true;
        assertTrue(hd.searchTheoTTTK(expected));
    }
    
    //Tìm kiếm theo trạng thái thống kê không hợp lệ
    @Test
    public void testSearchTTTK_Invalid(){
        boolean expected = false;
        assertFalse(hd.searchTheoTTTK(expected));
    }
    
    //Tìm kiếm theo ngày hợp lệ
    @Test
    public void testSearchNgay(){
        String expected = "28/03/2023";
        assertEquals(expected, hd.searchTheoNgay(expected));
    }
    
    //Tìm kiếm theo trạng thái thống kê không hợp lệ
    @Test
    public void testSearchNgay_Invalid(){
        String expected = "26/03/2023";
        assertNotEquals(expected, hd.searchTheoNgay(expected));
    }
    
    //Hủy hóa đơn hợp lệ
    @Test
    public void testHuyHoaDon(){
        String validID = "HD001";
        boolean validState = false;
        String expectedResult = "Valid";
        assertEquals(expectedResult, hd.huyHoaDon(validID, validState));
    }  
    
     //Hủy hóa đơn không hợp lệ
    @Test
    public void testHuyHoaDon_Invalid(){
        String invalidID = "HD100";
        boolean invalidState = true;
        String expectedResult = "Invalid";
        assertEquals(expectedResult, hd.huyHoaDon(invalidID, invalidState));
    }
    
    //Hủy hóa đơn trạng thái thống kê không hợp lệ
    @Test
    public void testHuyHoaDon_InvalidState(){
        String validID = "HD001";
        boolean invalidState = true;
        String expectedResult = "Invalid";
        assertNotEquals(expectedResult, hd.huyHoaDon(validID, invalidState));
    }
    
    //Hủy hóa đơn trạng thái thống kê không hợp lệ
    @Test
    public void testHuyHoaDon_InvalidID(){
        String inValidID = "HD100";
        boolean validState = false;
        String expectedResult = "Invalid";
        assertEquals(expectedResult, hd.huyHoaDon(inValidID, validState));
    }
}
