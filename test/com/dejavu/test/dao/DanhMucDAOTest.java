package com.dejavu.test.dao;

import com.bar.dao.DanhMucDAO;
import com.bar.model.DanhMuc;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.AfterClass;

public class DanhMucDAOTest {

    DanhMucDAO danhMucDAO;
    DanhMuc dm1;
    DanhMuc dm2;
    String ma1 = "";
    String ma2 = "";

    @Before
    public void setUp() {
        danhMucDAO = new DanhMucDAO();
        ma1 = "DM10";
        ma2 = "DM11";
        dm1 = new DanhMuc(ma1,"Danh mục test 1");
        dm2 = new DanhMuc(ma2, "Danh mục test 2");
    }

    @After
    public void tearDown() {

        dm1 = null;
        dm2 = null;
        
        danhMucDAO.delete(ma1);
        danhMucDAO.delete(ma2);
    }

    @Test
    public void testInsert_TC01() {
        ma1 = "DM10";
        dm1 = new DanhMuc(ma1,"Danh mục test 1");
        danhMucDAO.insert(dm1);
        DanhMuc result = danhMucDAO.selectID(ma1);
        assertNotNull(result);
    }
    
     @Test(expected = RuntimeException.class)
    public void testInsert_TR02() {
        ma1 = "DM11";
        dm1 = new DanhMuc();
        dm1.setMaDanhMuc(ma1);
        
        danhMucDAO.insert(dm1);
        DanhMuc result = danhMucDAO.selectID(ma1);
        assertNotNull(result);
    }
    
     @Test(expected = RuntimeException.class)
    public void testInsert_TR03() {
        ma1 = "DM10";
        dm1 = new DanhMuc(ma1,"Danh mục test 1");
        danhMucDAO.insert(dm1);
        
        ma2 = "DM10";
        dm2 = new DanhMuc(ma2, "Danh mục test 2");
        
        danhMucDAO.insert(dm2);
        DanhMuc result = danhMucDAO.selectID(ma2);
        assertNotNull(result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testInsert_TR04() {
        ma1 = "DM10";
        dm1 = new DanhMuc(ma1,"Danh mục test 1");
        danhMucDAO.insert(dm1);
        
        ma2 = "DM11";
        dm2 = new DanhMuc(ma2, "Danh mục test 1");
        
        danhMucDAO.insert(dm2);
        DanhMuc result = danhMucDAO.selectID(ma2);
        assertNotNull(result);
    }

    @Test
    public void testDelete_TC01() {
        danhMucDAO.insert(dm1);
        danhMucDAO.delete(ma1);
        DanhMuc result = danhMucDAO.selectID(ma1);
        assertNull(result);
    }
    
     @Test
    public void testDelete_TC02() {
        
        danhMucDAO.delete("DM01");
        DanhMuc result = danhMucDAO.selectID("DM01");
        assertNull(result);
    }

    @Test
    public void testUpdate_TC01() {
        danhMucDAO.insert(dm1);
        dm1.setTenDanhMuc("Danh mục Test Update");
        danhMucDAO.update(dm1);
        DanhMuc result = danhMucDAO.selectID(ma1);
        assertEquals(result.getMaDanhMuc(), dm1.getMaDanhMuc());
        assertNotNull(result);
    }
    
      @Test(expected = RuntimeException.class)
    public void testUpdate_TC02() {
        danhMucDAO.insert(dm1);
        danhMucDAO.insert(dm2);
     
        
        dm2.setMaDanhMuc(dm1.getMaDanhMuc());
        danhMucDAO.update(dm2);
        
        DanhMuc result = danhMucDAO.selectID(dm2.getMaDanhMuc());
        assertEquals(result.getMaDanhMuc(), dm2.getMaDanhMuc());
        assertNotNull(result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testUpdate_TC03() {
        danhMucDAO.insert(dm1);
        danhMucDAO.insert(dm2);
     
        
        dm2.setTenDanhMuc(dm1.getTenDanhMuc());
        danhMucDAO.update(dm2);
        
        DanhMuc result = danhMucDAO.selectID(dm2.getMaDanhMuc());
        assertEquals(result.getMaDanhMuc(), dm2.getMaDanhMuc());
        assertNotNull(result);
    }

    @Test
    public void testSelectAll() {
        danhMucDAO.insert(dm1);
        danhMucDAO.insert(dm2);
        List<DanhMuc> result = danhMucDAO.select();
        int lengthExpected = 7;
        for (DanhMuc danhMuc : result) {
            if (danhMuc.getMaDanhMuc().equals(dm1.getMaDanhMuc())) {
                assertEquals(danhMuc.getMaDanhMuc(), dm1.getMaDanhMuc());
            }
        }
        for (DanhMuc danhMuc : result) {
            if (danhMuc.getMaDanhMuc().equals(dm2.getMaDanhMuc())) {
                assertEquals(danhMuc.getMaDanhMuc(), dm2.getMaDanhMuc());
            }
        }
        assertEquals(lengthExpected, result.size());
    }

    @Test
    public void testSelectID() {
        danhMucDAO.insert(dm1);
        DanhMuc result = danhMucDAO.selectID(ma1);
        assertEquals(dm1.getMaDanhMuc(), result.getMaDanhMuc());
        assertEquals(dm1.getTenDanhMuc(), result.getTenDanhMuc());
      
    }

    @Test
    public void testSelectByKeyword() {
        danhMucDAO.insert(dm1);
        List<DanhMuc> result = danhMucDAO.selectByKeyword(dm1.getMaDanhMuc());
         for (DanhMuc danhMuc : result) {
            if (danhMuc.getMaDanhMuc().equals(dm1.getMaDanhMuc())) {
                assertEquals(danhMuc.getMaDanhMuc(), dm1.getMaDanhMuc());
            }
        }
    }

    @Test
    public void testSelectByKeyword2() {
        danhMucDAO.insert(dm1);
        danhMucDAO.insert(dm2);
        List<DanhMuc> result = danhMucDAO.selectMaDanhMuc("DM10");
       for (DanhMuc danhMuc : result) {
            if (danhMuc.getMaDanhMuc().equals(dm1.getMaDanhMuc())) {
                assertEquals(danhMuc.getMaDanhMuc(), dm1.getMaDanhMuc());
            }
        }

    }

}
