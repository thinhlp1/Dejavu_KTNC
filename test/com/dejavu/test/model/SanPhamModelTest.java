/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test.model;

import com.bar.model.SanPham;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized;

/**
 *
 * @author ADMIN
 */
public class SanPhamModelTest {
    
    SanPham sp;
  
    @After
    public void tearDown() {
        sp = null;
    }
    
    @Test
    public void testModelConstructor(){  
        sp = new SanPham("SP88","Món 1",  1000,"DM01","Mo ta 1", "Hinh1.png");
        assertEquals(sp.getMaMon(), "SP88");
        assertEquals(sp.getTenMon(), "Món 1");
        assertEquals(sp.getDanhMuc(), "DM01");
        assertEquals(sp.getGia(), 1000);
        assertEquals(sp.getGhiChu(), "Mo ta 1");
        assertEquals(sp.getHinh(), "Hinh1.png");
        
    }
    
      @Test
    public void testModelSetter(){  
        sp = new SanPham();
        
        sp.setMaMon("SP88");
        sp.setTenMon("Món 1"); 
        sp.setDanhMuc("DM01"); 
        sp.setGhiChu("Mo ta 1"); 
        sp.setGia(1000); 
        sp.setHinh("Hinh1.png"); 
        
        assertEquals(sp.getMaMon(), "SP88");
        assertEquals(sp.getTenMon(), "Món 1");
        assertEquals(sp.getDanhMuc(), "DM01");
        assertEquals(sp.getGia(), 1000);
        assertEquals(sp.getGhiChu(), "Mo ta 1");
        assertEquals(sp.getHinh(), "Hinh1.png");
        
    }

}
