
package com.dejavu.test.dao;

import com.bar.dao.ThongKeDao;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import org.junit.Test;


public class ThongKeDaoTest {
    
    private ThongKeDao thongKeDao;

    @Before
    public void setUp() {
        thongKeDao = new ThongKeDao();
    }

    @Test
    public void testGetThongKeTheoNgay() {
        List<Object[]> thongKe = thongKeDao.getThongKeTheoNgay("2022-03-14");
        assertEquals(1, thongKe.size());
        assertEquals("2022-03-14", thongKe.get(0)[0]);
        assertEquals(500000, thongKe.get(0)[1]);
    }

    @Test
    public void testGetThongKeTheoThang() {
        List<Object[]> thongKe = thongKeDao.getThongKeTheoThang("2022-03");
        assertEquals(1, thongKe.size());
        assertEquals("2022-03", thongKe.get(0)[0]);
        assertEquals(1500000, thongKe.get(0)[1]);
    }

    @Test
    public void testGetThongKeTheoNam() {
        List<Object[]> thongKe = thongKeDao.getThongKeTheoNam("2022");
        assertEquals(1, thongKe.size());
        assertEquals("2022", thongKe.get(0)[0]);
        assertEquals(3000000, thongKe.get(0)[1]);
    }
}
