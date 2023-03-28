/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.model;

import com.bar.dao.HoaDonDAO;
import com.bar.model.HoaDon;
import com.bar.view.MainDejavu;
import com.bar.view.QL_HoaDon;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author nguye
 */
public class QL_HoaDonTest {
    QL_HoaDon hd;
    HoaDon entityHD;
    HoaDonDAO entityDAO;
    @Before
    public void setUp() {
        hd = new QL_HoaDon();
        entityHD = new HoaDon();
        entityDAO = new HoaDonDAO();
        MainDejavu mainFrame = new MainDejavu();
        mainFrame.showPanel(hd);
    }
    
    @After
    public void tearDown() {
        hd = null;
    }
    
//    @Test
//    public void testXemHoaDon(){
//        //Kiểm tra list hóa đơn lớn hơn > 0
//        assertNotNull(hd.listHD);
//    }
//
//    @Test
//    public void testSearchTrangThaiThongKe_BinhThuong() {
//        //Pass nếu trạng thái thống kê trong bảng là "Bình thường"
//        boolean expectedState = true;
//        List<HoaDon> searchResult = hd.searchByState(expectedState);
//        assertNotNull(searchResult);    //Kiểm tra list hóa đơn lớn hơn > 0
////        assertFalse(searchResult.isEmpty());
//        for (HoaDon receipt : searchResult) {
//            assertEquals(expectedState, receipt.isTrangThai_ThongKe());
//        }
//    }
//    
//    @Test
//    public void testSearchTrangThaiThongKe_Huy() {
//        //Pass nếu trạng thái thống kê trong bảng là "Hủy"
//        boolean expectedState = false;
//        List<HoaDon> searchResult = hd.searchByState(expectedState);
//        assertNotNull(searchResult);    //Kiểm tra list hóa đơn lớn hơn > 0
////        assertFalse(searchResult.isEmpty());
//        for (HoaDon receipt : searchResult) {
//            assertEquals(expectedState, receipt.isTrangThai_ThongKe());
//        }
//    }
    
    @Test
    public void testHuyHoaDon() {
//        String maHD = hd.tblModelHD.getValueAt(0, 0);
        int row = 0;
        Boolean expected = false;
        String maHD = (String) hd.getTblHoaDon().getValueAt(row, 0);
        entityHD.setMaHD(maHD);
        entityDAO.update(entityHD);
        hd.setListHD(entityDAO.select());
        hd.fillTableHD();
        assertEquals(expected, hd.getListHD().get(0).isTrangThai_ThongKe());
//        hd.tblModelHD.setValueAt(false, 0, 4);
////        entityHD.setMaHD(maHD);
//        System.out.println(hd.tblModelHD.getValueAt(0, 4));
    }
    
//    @Test
//    public void testSearchNgay() {
//        //Pass nếu trạng thái thống kê trong bảng là "Hủy"
//        DateChooser expectedDate = new DateChooser();
//        
////        String actualDate = hd.documentChanged_Date(expectedDate);
//
////        System.out.println(actualDate);
//        List<HoaDon> searchResult = hd.documentChanged_Date(expectedDate);
////        assertNotNull(searchResult);    //Kiểm tra list hóa đơn lớn hơn > 0
//////        assertFalse(searchResult.isEmpty());
////        for (HoaDon receipt : searchResult) {
////            assertEquals(expectedState, receipt.isTrangThai_ThongKe());
////        }
//        System.out.println(searchResult);
//    }

//    @Test
//    public void testSearch_TenNV() {
//    }
//
//    @Test
//    public void testSearch_TrangThaiThongKe() {
//    }
//
//    @Test
//    public void testDocumentChangedState_TextFieldDate() {
//    }
//
//    @Test
//    public void testGetFormHoaDon() {
//    }
//
    
//
//    @Test
//    public void testTinhTongTien() {
//    }
//
//    @Test
//    public void testXuatHoaDon() {
//    }
//
//    @Test
//    public void testToMoneyString() {
//    }
//
//    @Test
//    public void testToMoneyLong() {
//    }

}
