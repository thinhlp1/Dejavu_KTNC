package com.dejavu.test.dao;

import com.bar.dao.BanOderDAO;
import com.bar.model.BanOder;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BanOderDAOTest {

    BanOderDAO banOderDao;
    BanOder b1;
    BanOder b2;

    @Before
    public void setUp() {
        banOderDao = new BanOderDAO();

        b1 = new BanOder("B05", "Hello", "B06");
        b2 = new BanOder("B17", "Hello2", "B20");
    }

    @After
    public void tearDown() {

        banOderDao.delete(b1.getMaBan());
        banOderDao.delete(b2.getMaBan());
    }

    @Test
    public void testInsert() {
        banOderDao.insert(b1);

        BanOder result = banOderDao.selectID(b1.getMaBan());
        assertEquals(result.getMaBan(), b1.getMaBan());

    }

    @Test
    public void testDelete() {
        banOderDao.insert(b1);
        banOderDao.delete(b1.getMaBan());
        BanOder result = banOderDao.selectID(b1.getMaBan());
        assertNull(result);

    }

    @Test
    public void testUpdate() {
        banOderDao.insert(b1);
        b1.setGhiChu("Hello new");
        banOderDao.updateGhiChu(b1.getGhiChu(), b1.getMaBan());
        BanOder result = banOderDao.selectID(b1.getMaBan());
        assertEquals(result.getGhiChu(), b1.getGhiChu());
        assertNotNull(result);

    }

    @Test
    public void testSelectAll() {
        banOderDao.insert(b1);
        banOderDao.insert(b2);
        List<BanOder> result = banOderDao.select();

        for (BanOder ban : result) {
            if (ban.getMaBan().equals(b1.getMaBan())) {
                assertEquals(ban.getMaBan(), b1.getMaBan());
            }
        }
        for (BanOder ban : result) {
            if (ban.getMaBan().equals(b2.getMaBan())) {
                assertEquals(ban.getMaBan(), b2.getMaBan());
            }
        }
    }

    @Test
    public void testSelectID() {
        banOderDao.insert(b1);
        BanOder result = banOderDao.selectID(b1.getMaBan());
        assertEquals(b1.getMaBan(), result.getMaBan());
        assertEquals(b1.getGhiChu(), result.getGhiChu());

    }

}
