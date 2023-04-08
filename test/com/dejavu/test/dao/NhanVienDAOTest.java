/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test.dao;

import com.bar.dao.NhanVienDAO;
import com.bar.model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PHUOCTAI
 */
public class NhanVienDAOTest {

    NhanVienDAO nhanvienDAO;
    NhanVien nv;
    String maNV;
    List<NhanVien> list = new ArrayList<>();

    @Before
    public void setUp() {
        nhanvienDAO = new NhanVienDAO();
        maNV = "NV01";

    }

    @After
    public void tearDown() {
        maNV = null;
        nv = null;
    }

    @Test
    public void testSelectAll() {
        list = nhanvienDAO.select();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testSelectByID() {
        nv = nhanvienDAO.selectID(maNV);
        String expected = maNV;
        assertEquals(expected, nv.getMaNV());
    }

    @Test
    public void testNVHaveAnAccount() {
        list = nhanvienDAO.selectCheckNV();
        assertTrue(list.size() > 0);
    }

}
