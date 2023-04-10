package com.dejavu.test.dao;

import com.bar.dao.SanPhamDAO;
import com.bar.model.SanPham;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.AfterClass;

public class SanPhamDAOTest {

    SanPhamDAO sanPhamDAO;
    SanPham sp1;
    SanPham sp2;
    String ma1 = "";
    String ma2 = "";

    @Before
    public void setUp() {
        sanPhamDAO = new SanPhamDAO();
        ma1 = "SP89";
        ma2 = "SP90";
        sp1 = new SanPham(ma1, "Bia Hanoi", 20000, "DM05", "Bia Hanoi 333", "hinh1.jpg");
        sp2 = new SanPham(ma2, "Bia Saigon", 25000, "DM05", "Bia Saigon", "hinh2.jpg");
    }

    @After
    public void tearDown() {

        sp1 = null;
        sp2 = null;

        sanPhamDAO.delete(ma1);
        sanPhamDAO.delete(ma2);
    }

    @Test
    public void testInsert() {
        sanPhamDAO.insert(sp1);
        SanPham result = sanPhamDAO.selectID(ma1);
        assertNotNull(result);
    }
    
    @Test(expected = RuntimeException.class)
    public void testInsert_TC02() {
        sp1.setMaMon(null);
        sanPhamDAO.insert(sp1);
        SanPham result = sanPhamDAO.selectID(ma1);
        assertNotNull(result);
    }
    
 

    @Test
    public void testDelete_TC01() {
        sanPhamDAO.insert(sp1);
        sanPhamDAO.delete(ma1);
        SanPham result = sanPhamDAO.selectID(ma1);
        assertNull(result);
    }
    
        @Test(expected = RuntimeException.class)
    public void testDelete_TC02() {
       
        sanPhamDAO.delete("SP02");
        SanPham result = sanPhamDAO.selectID("SP02");
        assertNull(result);
    }

    
    @Test
    public void testUpdate_TC01() {
        sanPhamDAO.insert(sp1);
        sp1.setTenMon("Bia Ha Noi 333");
        sanPhamDAO.update(sp1);
        SanPham result = sanPhamDAO.selectID(ma1);
        assertEquals(result.getTenMon(), sp1.getTenMon());
        assertNotNull(result);
    }
    
     @Test(expected = RuntimeException.class)
    public void testUpdate_TC02() {
        sp1.setTenMon(null);
        sanPhamDAO.insert(sp1);
        
        SanPham result = sanPhamDAO.selectID(ma1);
        assertEquals(result.getTenMon(), sp1.getTenMon());
        assertNotNull(result);
    }


    @Test
    public void testSelectAll() {
        sanPhamDAO.insert(sp1);
        sanPhamDAO.insert(sp2);
        List<SanPham> result = sanPhamDAO.selectAll();
        int lengthExpected = 46;
        for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp1.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp1.getMaMon());
            }
        }
        for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp2.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp2.getMaMon());
            }
        }
        assertEquals(lengthExpected, result.size());
    }

    @Test
    public void testSelectID() {
        sanPhamDAO.insert(sp1);
        SanPham result = sanPhamDAO.selectID(ma1);
        assertEquals(sp1.getMaMon(), result.getMaMon());
        assertEquals(sp1.getTenMon(), result.getTenMon());
        assertEquals(sp1.getGia(), result.getGia());
        assertEquals(sp1.getDanhMuc(), result.getDanhMuc());
        assertEquals(sp1.getHinh(), result.getHinh());
        assertEquals(sp1.getGhiChu(), result.getGhiChu());
    }

    @Test
    public void testSelectByKeyword() {
        sanPhamDAO.insert(sp1);
        List<SanPham> result = sanPhamDAO.selectByKeyword(sp1.getMaMon());
         for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp1.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp1.getMaMon());
            }
        }
     

    }

    @Test
    public void testSelectByKeyword2() {
        sanPhamDAO.insert(sp1);
        sanPhamDAO.insert(sp2);
        List<SanPham> result = sanPhamDAO.selectByKeyword2("Bia");
       for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp1.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp1.getMaMon());
            }
        }
        for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp2.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp2.getMaMon());
            }
        }
    }

    @Test
    public void testSelectByLoc() {
        sanPhamDAO.insert(sp1);
        sanPhamDAO.insert(sp2);
        List<SanPham> result = sanPhamDAO.selectByLoc("DM05");
         for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp1.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp1.getMaMon());
            }
        }
        for (SanPham sanPham : result) {
            if (sanPham.getMaMon().equals(sp2.getMaMon())) {
                assertEquals(sanPham.getMaMon(), sp2.getMaMon());
            }
        }
    }
}
