/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test;

import com.bar.dao.SanPhamDAO;
import com.bar.model.SanPham;
import com.bar.view.PNL_SanPham;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SanPhamTest {

    private SanPhamDAO spdao = new SanPhamDAO();
    private PNL_SanPham pNL_SanPham;
    private SanPham sp;

    @Before
    public void setUp() {
        pNL_SanPham = new PNL_SanPham();
    }

    @After
    public void tearDown() {
        sp = null;
    }

    //test thêm sản phẩm thành công
    @Test

    public void testInsertSanPham_TC01() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        sp = new SanPham("SP88", "Món 1", 1000, "DM01", "Mo ta 1", "Hinh1.png");
        String resultString = pNL_SanPham.insertSanPham(sp);

        // Retrieve the inserted SanPham object from the database
        SanPham insertedSp = spdao.selectID(sp.getMaMon());

        assertEquals(resultString, "Thêm mới San Pham thanh cong!");
        spdao.delete(insertedSp.getMaMon());
    }

    @Test
    public void testInsertSanPham_TC02() throws Exception {
        String maSP = "SP01";
        String tenSP = "Sản phẩm 1";
        String gia = "100000";
        String hinh = "anh1.png";
        String ghiChu = "ghi chú 1";
        String danhMuc = "DM02";

        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm(maSP, tenSP, ""));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm(maSP, "", gia));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm("", tenSP, gia));

    }

    @Test
    public void testInsertSanPham_TC03() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        sp = new SanPham("SP88", "Món 1", 1000, "DM01", "Mo ta 1", "");
        sp.setHinh(null);
        String resultString = pNL_SanPham.insertSanPham(sp);
        assertEquals("Hình Không Được Để Trống", resultString);
    }

    @Test
    public void testInsertSanPham_TC04() throws Exception {
        String maSP = "SPP01";
        String tenSP = "Sản phẩm 1";
        String gia = "100000";

        assertEquals("Mã món không chứ kí tự đặt biệt!", pNL_SanPham.checkForm(maSP, tenSP, gia));
    }

    @Test
    public void testInsertSanPham_TC05_6() throws Exception {
        String maSP = "SP01";
        String tenSP = "Sản phẩm 1";
        String gia = "0";
        String gia2 = "fdsfd";

        assertEquals("Giá vui lòng nhập số và lớn hơn 0", pNL_SanPham.checkForm(maSP, tenSP, gia));
        assertEquals("Giá vui lòng nhập số và lớn hơn 0", pNL_SanPham.checkForm(maSP, tenSP, gia2));
    }

    @Test(expected = SQLServerException.class)
    public void testInsertSanPham_TC08() throws Exception {

        SanPham sp2 = new SanPham("SP89", "Sangria", 1000, "DM01", "Mo ta 1", "Hinh1.png");
        String resultString = pNL_SanPham.insertSanPham(sp2);
        
        spdao.delete(sp2.getMaMon());
        

    }

    @Test
    public void testInsertSanPham_TC07() throws Exception {
        String maSP = "SP01";
        assertTrue(pNL_SanPham.checkDupKey(maSP));

    }

    @Test
    public void testUpdateSanPham_TC01() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        sp = new SanPham("SP88", "Món 1 Update", 1000, "DM01", "Mo ta 1 udate", "Hinh1.png");
        String resultString = pNL_SanPham.updateSanPham(sp);

        assertEquals(resultString, "Cap Nhat San Pham thanh cong!");
        spdao.delete(sp.getMaMon());
    }

    @Test
    public void testUpdateSanPham_TC02() throws Exception {
        String maSP = "SP01";
        String tenSP = "Sản phẩm 1";
        String gia = "100000";
        String hinh = "anh1.png";
        String ghiChu = "ghi chú 1";
        String danhMuc = "DM02";

        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm(maSP, tenSP, ""));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm(maSP, "", gia));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_SanPham.checkForm("", tenSP, gia));

    }

    @Test
    public void testUpdateSanPham_TC03_4() throws Exception {
        String maSP = "SP01";
        String tenSP = "Sản phẩm 1";
        String gia = "0";
        String gia2 = "fdsfd";

        assertEquals("Giá vui lòng nhập số và lớn hơn 0", pNL_SanPham.checkForm(maSP, tenSP, gia));
        assertEquals("Giá vui lòng nhập số và lớn hơn 0", pNL_SanPham.checkForm(maSP, tenSP, gia2));
    }

    @Test(expected = SQLServerException.class)
    public void testUpdateSanPham_TC05() throws Exception {

        SanPham sp2 = new SanPham("SP89", "Sản phẩm 89", 1000, "DM01", "Mo ta 1", "Hinh1.png");
        String resultString = pNL_SanPham.insertSanPham(sp2);
        
        SanPham sp3 = new SanPham("SP99", "Sản phẩm 99", 1000, "DM01", "Mo ta 1", "Hinh1.png");
        String resultString3 = pNL_SanPham.insertSanPham(sp3);
        
        
        sp = spdao.selectID("SP99");
        sp.setTenMon("Sản phẩm 89");
        
        try {
              spdao.update(sp);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        spdao.delete(sp2.getMaMon());
        spdao.delete(sp3.getMaMon());

    }

    @Test
    public void testDeleteSanPham_TC01() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        sp = new SanPham("SP88", "Món 1", 1000, "DM01", "Mo ta 1", "Hinh1.png");
        spdao.insert(sp);
        String resultStr = pNL_SanPham.deleteSanPham(sp.getMaMon());
        assertEquals("Xóa thành công!", resultStr);
        spdao.delete(sp.getMaMon());
    }
    
      @Test
    public void testDeleteSanPham_TC02() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        
        String maSP = "SP02";
        String resultStr = pNL_SanPham.deleteSanPham(maSP);
        assertEquals("Xóa thành công!", resultStr);
    
    }

    @Test
    public void testXemSanPham_TC01() throws Exception {
        // Call the insertSanPham() method with the parameterized SanPham object
        sp = spdao.selectID("SP01");
        pNL_SanPham.setFormSanPham(sp);
        SanPham sp2 = pNL_SanPham.getFormSanPham();

        assertEquals(sp.getMaMon(), sp2.getMaMon());
        assertEquals(sp.getTenMon(), sp2.getTenMon());
        assertEquals(sp.getDanhMuc(), sp2.getDanhMuc());
        assertEquals(sp.getGia(), sp2.getGia());
        assertEquals(sp.getGhiChu(), sp2.getGhiChu());
        assertEquals(sp.getHinh(), sp2.getHinh());

    }
    
    @Test
    public void testXemSanPham_TC02() throws Exception {
        
        List<SanPham> list = spdao.selectAll();
        int slSanPham = pNL_SanPham.fillTableSanPham(list);
        int slExpectd = list.size();
        assertEquals(slExpectd, slSanPham);

    }
    
    
    @Test
    public void testTimKiemSanPham_TC01() throws Exception {
        List<SanPham> list = spdao.selectByKeyword2("Bia");
        String strResult = pNL_SanPham.timSanPham("Bia");
        String slExpectd = list.size() + "";
        assertEquals(slExpectd, strResult);

    }
    

}
