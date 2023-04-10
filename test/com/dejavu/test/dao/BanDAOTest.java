package com.dejavu.test.dao;

import com.bar.dao.BanDAO;
import com.bar.model.Ban;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BanDAOTest {

    BanDAO banDao;
    Ban b1;
    Ban b2;

    @Before
    public void setUp() {
        banDao = new BanDAO();

        b1 = new Ban("B21", false, "NV01");
        b2 = new Ban("B25", false, "NV01");
    }

      @After
    public void tearDown() {


        banDao.delete(b1.getMaBan());
        banDao.delete(b2.getMaBan());
    }

    @Test
    public void testInsert() {
        banDao.insert(b1);

        Ban result = banDao.selectID(b1.getMaBan());
        assertEquals(result.getMaBan(), b1.getMaBan());
      
    }

    @Test
    public void testDelete() {
        banDao.insert(b1);
        banDao.delete(b1.getMaBan());
        Ban result = banDao.selectID(b1.getMaBan());
        assertNull(result);
        
    }

    @Test
    public void testUpdate() {
        banDao.insert(b1);
        b1.setTrangThai(true);
        banDao.update(b1);
        Ban result = banDao.selectID(b1.getMaBan());
        assertEquals(result.isTrangThai(), b1.isTrangThai());
        assertNotNull(result);
      
    }

    @Test
    public void testSelectAll() {
        banDao.insert(b1);
        banDao.insert(b2);
        List<Ban> result = banDao.select();

        for (Ban ban : result) {
            if (ban.getMaBan().equals(b1.getMaBan())) {
                assertEquals(ban.getMaBan(), b1.getMaBan());
            }
        }
        for (Ban ban : result) {
            if (ban.getMaBan().equals(b2.getMaBan())) {
                assertEquals(ban.getMaBan(), b2.getMaBan());
            }
        }
    }

    @Test
    public void testSelectID() {
        banDao.insert(b1);
        Ban result = banDao.selectID(b1.getMaBan());
        assertEquals(b1.getMaBan(), result.getMaBan());
        assertEquals(b1.getMaNhanVien(), result.getMaNhanVien());
        
    }

}
