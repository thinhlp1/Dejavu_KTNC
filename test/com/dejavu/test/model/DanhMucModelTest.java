/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test.model;

import com.bar.model.DanhMuc;
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
public class DanhMucModelTest {
    
    DanhMuc dm;
  
    @After
    public void tearDown() {
        dm = null;
    }
    
    @Test
    public void testModelConstructor(){  
        dm = new DanhMuc("DM10","Danh mục 10");
        assertEquals(dm.getMaDanhMuc(), "DM10");
        assertEquals(dm.getTenDanhMuc(), "Danh mục 10");
        
        
    }
    
      @Test
    public void testModelSetter(){  
        
        dm = new DanhMuc();
        dm.setMaDanhMuc("DM10");
        dm.setTenDanhMuc("Danh mục 10");
        assertEquals(dm.getMaDanhMuc(), "DM10");
        assertEquals(dm.getTenDanhMuc(), "Danh mục 10");
        
    }

}
