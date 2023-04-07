/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejavu.test;

import com.bar.dao.DanhMucDAO;
import com.bar.model.DanhMuc;
import com.bar.view.PNL_DanhMuc;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DanhMucTest {
    
    private DanhMucDAO dmdao = new DanhMucDAO();
    private PNL_DanhMuc pNL_DanhMuc;
    private DanhMuc dm;
    
    @Before
    public void setUp() {
        pNL_DanhMuc = new PNL_DanhMuc();
    }
    
    @After
    public void tearDown() {
        dm = null;
    }

    //test thêm sản phẩm thành công
    @Test
    
    public void testInsertDanhMuc_TC01() throws Exception {
        // Call the insertDanhMuc() method with the parameterized DanhMuc object
        dm = new DanhMuc("DM15", "Danh mục 16");
        String resultString = pNL_DanhMuc.insertDanhMuc(dm);

        // Retrieve the inserted DanhMuc object from the database
        DanhMuc insertedDm = dmdao.selectID(dm.getMaDanhMuc());
        
        assertEquals(resultString, "Thêm mới danh muc thanh cong!");
        dmdao.delete(insertedDm.getMaDanhMuc());
    }
    
    @Test
    public void testInsertDanhMuc_TC02() throws Exception {
        String maDM = "DM15";
        String tenDM = "Danh mục 15";
        
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_DanhMuc.checkForm("", tenDM));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_DanhMuc.checkForm(maDM, ""));
        
    }
    
    @Test
    public void testInsertDanhMuc_TC03() throws Exception {
        String maDM = "DMP01";
        String tenDM = "Sản phẩm 1";
        
        assertEquals("Mã danh mục không chứ kí tự đặt biệt!", pNL_DanhMuc.checkForm(maDM, tenDM));
    }
    
    @Test(expected = SQLServerException.class)
    public void testInsertDanhMuc_TC05() throws Exception {
        
        DanhMuc dm = new DanhMuc("DM99", "Danh mục 99");
        pNL_DanhMuc.insertDanhMuc(dm);
        
        DanhMuc dm2 = new DanhMuc("DM98", "Danh mục 99");
        pNL_DanhMuc.insertDanhMuc(dm);
        
        dmdao.delete(dm.getMaDanhMuc());
        dmdao.delete(dm2.getMaDanhMuc());
        
    }
    
    @Test
    public void testInsertDanhMuc_TC04() throws Exception {
        String maDM = "DM01";
        assertTrue(pNL_DanhMuc.checkDupKey(maDM));
        
    }
//

    @Test
    public void testUpdateDanhMuc_TC01() throws Exception {
        // Call the insertDanhMuc() method with the parameterized DanhMuc object
        dm = new DanhMuc("DM15", "Danh mục 15 update");
        String resultString = pNL_DanhMuc.updateDanhMuc(dm);
        
        assertEquals(resultString, "Cap Nhat danh muc thanh cong!");
        dmdao.delete(dm.getMaDanhMuc());
    }

    @Test
    public void testUpdateDanhMuc_TC02() throws Exception {
        String maDM = "DM15";
        String tenDM = "Danh mục 15";
        
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_DanhMuc.checkForm("", tenDM));
        assertEquals("Hãy nhập đủ dữ liệu sau đó ấn Thêm", pNL_DanhMuc.checkForm(maDM, ""));
        
    }
    
    @Test(expected = SQLServerException.class)
    public void testUpdateDanhMuc_TC03() throws Exception {
        
        DanhMuc dm = new DanhMuc("DM99", "Danh mục 99");
        pNL_DanhMuc.insertDanhMuc(dm);
        
        DanhMuc dm2 = new DanhMuc("DM98", "Danh mục 98");
        pNL_DanhMuc.insertDanhMuc(dm);
        
        DanhMuc dm3 = dmdao.selectID("DM99");
        dm3.setTenDanhMuc("Danh mục 99");
        
        try {
            dmdao.update(dm3);
        } catch (Exception e) {
        }
        
        dmdao.delete(dm.getMaDanhMuc());
        dmdao.delete(dm2.getMaDanhMuc());
        
    }
    
    @Test
    public void testDeleteDanhMuc_TC01() throws Exception {
        // Call the insertDanhMuc() method with the parameterized DanhMuc object
        dm = new DanhMuc("DM15", "Danh mục 15");
        dmdao.insert(dm);
        String resultStr = pNL_DanhMuc.deleteDanhMuc(dm.getMaDanhMuc());
        assertEquals("Xóa thành công!", resultStr);
        dmdao.delete(dm.getMaDanhMuc());
    }
    
    @Test
    public void testDeleteDanhMuc_TC02() throws Exception {
        // Call the insertDanhMuc() method with the parameterized DanhMuc object
        dm = dmdao.selectID("DM01");
        String resultStr = pNL_DanhMuc.deleteDanhMuc(dm.getMaDanhMuc());
        assertEquals("Xóa thành công!", resultStr);
       
    }

    @Test
    public void testXemDanhMuc_TC01() throws Exception {
        // Call the insertDanhMuc() method with the parameterized DanhMuc object
        dm = dmdao.selectID("DM01");
        pNL_DanhMuc.setFormDanhMuc(dm);
        DanhMuc dm2 = pNL_DanhMuc.getFormDanhMuc();

        assertEquals(dm.getMaDanhMuc(), dm2.getMaDanhMuc());
        assertEquals(dm.getTenDanhMuc(), dm2.getTenDanhMuc());
     

    }
    
    @Test
    public void testXemDanhMuc_TC02() throws Exception {
        
        List<DanhMuc> list = dmdao.select();
        int slDanhMuc = pNL_DanhMuc.fillTableDanhMuc(list);
        int slExpectd = list.size();
        assertEquals(slExpectd, slDanhMuc);

    }
    
    @Test
    public void testTimKiemDanhMuc_TC01() throws Exception {
        List<DanhMuc> list = dmdao.selectByKeyword("CockTail");
        String strResult = pNL_DanhMuc.timDanhMuc("CockTail");
        String slExpectd = list.size() + "";
        assertEquals(slExpectd, strResult);

    }
   

}
