
package com.dejavu.test.model;
import com.bar.model.SanPham_GioHang;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


public class SanPhamGioHangModelTest {

    SanPham_GioHang spgh;

    @After
    public void tearDown() {
        spgh = null;
    }

    @Test
    public void testModelConstructor() {
        spgh = new SanPham_GioHang("MB01", "Hinh1.png", "SP01", "Món 1", 1000, 2);
        assertEquals(spgh.getMaBan(), "MB01");
        assertEquals(spgh.getHinh(), "Hinh1.png");
        assertEquals(spgh.getMasp(), "SP01");
        assertEquals(spgh.getTenSP(), "Món 1");
        assertEquals(spgh.getGia(), 1000);
        assertEquals(spgh.getSoLuong(), 2);
        

    }

    @Test
    public void testModelSetter() {
        spgh = new SanPham_GioHang();

        spgh.setMaBan("MB01");
        spgh.setHinh("Hinh1.png");
        spgh.setMasp("SP01");
        spgh.setTenSP("Món 1");
        spgh.setGia(1000);
        spgh.setSoLuong(2);

        assertEquals(spgh.getMaBan(), "MB01");
        assertEquals(spgh.getHinh(), "Hinh1.png");
        assertEquals(spgh.getMasp(), "SP01");
        assertEquals(spgh.getTenSP(), "Món 1");
        assertEquals(spgh.getGia(), 1000);
        assertEquals(spgh.getSoLuong(), 2);
    }

}
