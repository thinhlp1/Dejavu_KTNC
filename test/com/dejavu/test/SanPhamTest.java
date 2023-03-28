/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test;
import com.bar.dao.SanPhamDAO;
import com.bar.model.SanPham;
import com.bar.view.PNL_SanPham;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import org.junit.After;

import static org.junit.Assert.*;
import org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class SanPhamTest {

    private SanPhamDAO spdao = new SanPhamDAO();
    private PNL_SanPham pNL_SanPham;
    private SanPham sp;

    // Define the parameterized test data
    @Parameterized.Parameters()
    public static Collection<Object[]> dataForInsertSuccess() {
        return Arrays.asList(new Object[][]{
                {new SanPham("SP88","Món 1",  1000,"DM01","Mo ta 1", "Hinh1.png")},
                {new SanPham("SP89","Món 2",  2000,"DM01","Mo ta 2", "Hinh2.png")},
                {new SanPham("SP99","Món 3",  3000,"DM01","Mo ta 3", "Hinh3.png")}
        });
    }
   
    // Constructor to set up the test data for each parameterized test case
    public SanPhamTest(SanPham sp) {
        this.sp = sp;
    }

    @Before
    public void setUp() {
        pNL_SanPham = new PNL_SanPham();
    }
    //test thêm sản phẩm thành công
    @Test
    public void testInsertSanPham_TC01() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        
       String resultString = pNL_SanPham.insertSanPham(sp);

        // Retrieve the inserted SanPham object from the database
        SanPham insertedSp = spdao.selectID(sp.getMaMon());
      
        assertEquals(resultString, "Thêm mới San Pham thanh cong!");
        
        // Verify that the inserted SanPham object has the correct properties
//        assertEquals(sp.getTenMon(), insertedSp.getTenMon());
//        assertEquals(sp.getDanhMuc(), insertedSp.getDanhMuc());
//        assertEquals(sp.getGia(), insertedSp.getGia(), 0.001);
//        assertEquals(sp.getGhiChu(), insertedSp.getGhiChu());
//        assertEquals(sp.getHinh(), insertedSp.getHinh());
        
        
        spdao.delete(insertedSp.getMaMon());
    }
    
    @Test
    public void testInsertSanPham_TC02() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        
       String resultString = pNL_SanPham.insertSanPham(sp);

        // Retrieve the inserted SanPham object from the database
        SanPham insertedSp = spdao.selectID(sp.getMaMon());
      
        assertEquals(resultString, "Thêm mới San Pham thanh cong!");
        

        spdao.delete(insertedSp.getMaMon());
    }
}

