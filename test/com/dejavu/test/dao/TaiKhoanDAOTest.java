package com.dejavu.test.dao;

import com.bar.dao.TaiKhoanDAO;
import com.bar.model.TaiKhoan;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PHUOCTAI
 */
public class TaiKhoanDAOTest {

    TaiKhoan taikhoan;
    TaiKhoanDAO tkDAO;
    String username;
    String password;
    String id;
//_______________________
    String userUpdate;
    String passUpdate;

    @Before
    public void setUp() {
        tkDAO = new TaiKhoanDAO();
        username = "usertest";
        password = "12345";
        id = "NV04";
        taikhoan = new TaiKhoan(username, password, id);
        userUpdate = "userUpdate";
        passUpdate = "passUpdate";
    }

    @After
    public void tearDown() {
        taikhoan = null;
        tkDAO.delete(username);
        tkDAO.delete(userUpdate);
    }

    @Test
    public void testAccountInsert() {
        tkDAO.insert(taikhoan);
        String expected1 = username; //usertest
        String expected2 = id; //usertest
        TaiKhoan tkTam = tkDAO.selectNAME(username); //get Account by Username
        assertEquals(expected1, tkTam.getTenTaiKhoan());
        assertEquals(expected2, tkTam.getMaNV());
    }

    @Test
    public void testAccountDelete() {
        tkDAO.insert(taikhoan);
        tkDAO.delete(taikhoan.getTenTaiKhoan());
        TaiKhoan rs = tkDAO.selectNAME(username); //get Account by Username
        assertNull(rs);
    }

    @Test
    public void testAccountUpdate() {
        tkDAO.insert(taikhoan);
        taikhoan.setTenTaiKhoan(userUpdate);
        tkDAO.update(taikhoan);
        TaiKhoan rs = tkDAO.selectNAME(userUpdate); //get Account by Username
        assertEquals(taikhoan.getTenTaiKhoan(), rs.getTenTaiKhoan());
        assertNotNull(rs);
    }

    @Test
    public void testAccountUpdatePass() {
        tkDAO.insert(taikhoan);
        taikhoan.setMatKhau(passUpdate);
        tkDAO.update_Pass(taikhoan);
        TaiKhoan rs = tkDAO.selectNAME(username); //get Account by Username
        assertEquals(taikhoan.getMatKhau(), rs.getMatKhau());
    }

    @Test
    public void testAccountSelectAll() {
        tkDAO.insert(taikhoan);
        List<TaiKhoan> list = tkDAO.select();
        for (TaiKhoan tk : list) {
            if (tk.getTenTaiKhoan().equals(taikhoan.getTenTaiKhoan())) {
                assertEquals(tk.getTenTaiKhoan(), taikhoan.getTenTaiKhoan());
            }
        }
        assertTrue(list.size() > 1);
    }

    @Test
    public void testAccountSelectID() {
        tkDAO.insert(taikhoan);
        TaiKhoan rs = tkDAO.selectID(id);
        String expected = id;
        assertEquals(expected, rs.getMaNV());
        assertEquals(taikhoan.getTenTaiKhoan(), rs.getTenTaiKhoan());
        assertNotNull(rs);
    }

    @Test
    public void testAccountSelectUsername() {
        tkDAO.insert(taikhoan);
        TaiKhoan rs = tkDAO.selectNAME(username);
        String expected = username;
        assertEquals(expected, rs.getTenTaiKhoan());
        assertEquals(taikhoan.getMaNV(), rs.getMaNV());
        assertNotNull(rs);
    }

}
