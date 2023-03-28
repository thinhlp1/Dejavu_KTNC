/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.model;


import com.bar.model.HoaDonChiTiet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class HoaDonChiTietTest {
    
    HoaDonChiTiet hdct;
    
    @Before
    public void setUp(){
       hdct = new HoaDonChiTiet();
    }
    
    @Test
    public void testMaHDCT(){
        hdct.setMaHDCT("001");
        String expected = "001";
        String actual = hdct.getMaHDCT();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMaHDCT_Invalid(){
        hdct.setMaHDCT("001");
        int unexpected = 1;
        String actual = hdct.getMaHDCT();
        assertNotEquals(unexpected, actual);
    }
    
    @Test
    public void testTenSP(){
        hdct.setTenSP("Nước ngọt");
        String expected = "Nước ngọt";
        String actual = hdct.getTenSP();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTenSP_Invalid(){
        hdct.setTenSP("Nước ngọt");
        int unexpected = 123;
        String actual = hdct.getTenSP();
        assertNotEquals(unexpected, actual);
    }
    
     @Test
    public void testSoLuong(){
        hdct.setSoLuong(2);
        int expected = 2;
        int actual = hdct.getSoLuong();
        assertEquals(expected, actual);
    }
    
      @Test
    public void testSoLuong_Invalid(){
        hdct.setSoLuong(2);
        String unexpected = "abc";
        int actual = hdct.getSoLuong();
        assertNotEquals(unexpected, actual);
    }
    
     @Test
    public void testDonGia(){
        hdct.setDonGia(20000);
        long expected = 20000;
        long actual = hdct.getDonGia();
        assertEquals(expected, actual);
    }
    
      @Test
    public void testDonGia_Invalid(){
        hdct.setDonGia(20000);
        String unexpected = "abc";
        long actual = hdct.getDonGia();
        assertNotEquals(unexpected, actual);
    }
    
     @Test
    public void testMaSP(){
        hdct.setMaSP("SP01");
        String expected = "SP01";
        String actual = hdct.getMaSP();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMaSP_Invalid(){
        hdct.setMaSP("SP01");
        int unexpected = 2;
        String actual = hdct.getMaSP();
        assertNotEquals(unexpected, actual);
    }
}

