/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.dao;

import com.bar.dao.*;
import com.bar.model.HoaDon;

import com.bar.util.JdbcHelper;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author nguye
 */
public class HoaDonDAOTest {

    HoaDonDAO dao;


    @Before
    public void setUp() {
        dao = new HoaDonDAO();
    }

    @Test
    public void testInsert() {
        // Create a new HoaDon object with test data
        HoaDon hd = new HoaDon();
        hd.setMaHD("HD001");
        hd.setNgayLapHD("2023-04-07");
        hd.setTrangThaiThanhToan("Đã thanh toán");
        hd.setMaNV("NV01");
        hd.setTrangThai_ThongKe(false);
        hd.setGhiChu("Test insert HoaDon");
        hd.setTongTien(100000);

        // Call the insert method
        dao.insert(hd);

        // Check if the record was inserted correctly by selecting it from the database
        HoaDon hdEx = dao.selectID(hd.getMaHD());
        assertEquals(hd.getMaHD(), hdEx.getMaHD());
        assertEquals(hd.getNgayLapHD(), hdEx.getNgayLapHD());
        assertEquals(hd.getTrangThaiThanhToan(), hdEx.getTrangThaiThanhToan());
        assertEquals(hd.getMaNV(), hdEx.getMaNV());
        assertEquals(true, hdEx.isTrangThai_ThongKe());
        assertEquals(hd.getGhiChu(), hdEx.getGhiChu());
        assertEquals(hd.getTongTien(), hdEx.getTongTien(), 0.01);
    }

//
    @Test
    public void testUpdate() {
        // Create a new HoaDon object with test data
        HoaDon hd = new HoaDon();
        hd.setMaHD("HD002");
        hd.setNgayLapHD("2023-04-07");
        hd.setTrangThaiThanhToan("Đã thanh toán");
        hd.setMaNV("NV01");
        hd.setTrangThai_ThongKe(true);

        // Insert the HoaDon object into the database
        dao.insert(hd);

        // Update the HoaDon object with new data
        hd.setTrangThai_ThongKe(false);

        // Call the update method
        dao.update(hd);

        // Check if the record was updated correctly by selecting it from the database
        HoaDon hdEx = dao.selectID(hd.getMaHD());
        assertEquals(hd.getMaHD(), hdEx.getMaHD());
        assertEquals(hd.getNgayLapHD(), hdEx.getNgayLapHD());
        assertEquals(hd.getTrangThaiThanhToan(), hdEx.getTrangThaiThanhToan());
        assertEquals(hd.getMaNV(), hdEx.getMaNV());
        assertEquals(hd.isTrangThai_ThongKe(), hdEx.isTrangThai_ThongKe());
    }
//

    @Test
    public void testSelect() {
        // Create a new HoaDon object and insert it into the database
        HoaDon hd = new HoaDon();
        hd.setMaHD("HD003");
        hd.setNgayLapHD("2023-04-07");
        hd.setTrangThaiThanhToan("Đã thanh toán");
        hd.setMaNV("NV01");
        hd.setTrangThai_ThongKe(false);
        hd.setGhiChu("Test select HoaDon");
        hd.setTongTien(100000);
        dao.insert(hd);

        // Call the select method to retrieve all objects from the database
        List<HoaDon> selectedHds = dao.select();

        // Assert that the list of objects is not null and contains at least one object
        assertNotNull(selectedHds);
        assertTrue(selectedHds.size() >= 1);

        // Assert that the inserted object is present in the list of selected objects
        boolean hdFound = false;
        for (HoaDon hoaDon : selectedHds) {
            if (hoaDon.getMaHD().equals(hd.getMaHD())) {
                assertEquals(hd.getNgayLapHD(), hoaDon.getNgayLapHD());
                assertEquals(hd.getTrangThaiThanhToan(), hoaDon.getTrangThaiThanhToan());
                assertEquals(hd.getMaNV(), hoaDon.getMaNV());
                assertEquals(hd.isTrangThai_ThongKe(), hoaDon.isTrangThai_ThongKe());
                assertEquals(hd.getGhiChu(), hoaDon.getGhiChu());
                assertEquals(hd.getTongTien(), hoaDon.getTongTien(), 0.01);
                hdFound = true;
                break;
            }
        }
        assertTrue(hdFound);
    }
//

    @Test
    public void testSelectID() {
        // Create a new HoaDon object and insert it into the database
        HoaDon hd = new HoaDon();
        hd.setMaHD("HD004");
        hd.setNgayLapHD("2023-04-07");
        hd.setTrangThaiThanhToan("Đã thanh toán");
        hd.setMaNV("NV01");
        hd.setTrangThai_ThongKe(false);
        hd.setGhiChu("Test selectID HoaDon");
        hd.setTongTien(200000);
        dao.insert(hd);

        // Call the selectID method to retrieve the inserted entity from the database
        HoaDon selectedEntity = dao.selectID(hd.getMaHD());

        // Assert that the selected entity is not null and its attributes match the inserted entity's attributes
        assertNotNull(selectedEntity);
        assertEquals(hd.getMaHD(), selectedEntity.getMaHD());
        assertEquals(hd.getNgayLapHD(), selectedEntity.getNgayLapHD());
        assertEquals(hd.getTrangThaiThanhToan(), selectedEntity.getTrangThaiThanhToan());
        assertEquals(hd.getMaNV(), selectedEntity.getMaNV());
        assertEquals(hd.isTrangThai_ThongKe(), selectedEntity.isTrangThai_ThongKe());
        assertEquals(hd.getGhiChu(), selectedEntity.getGhiChu());
        assertEquals(hd.getTongTien(), selectedEntity.getTongTien(), 0.01);
    }
//

    @Test
    public void testSelectBY_IDNV() {

        // Create a new HoaDon entity and insert it into the database
        HoaDon hd = new HoaDon();
        hd.setMaHD("HD0123");
        hd.setNgayLapHD("2023-04-07");
        hd.setTrangThaiThanhToan("Đã thanh toán");
        hd.setMaNV("NV01");
        hd.setTrangThai_ThongKe(false);
        hd.setGhiChu("Test insert HoaDon");
        hd.setTongTien(100000);
        dao.insert(hd);

        // Call the selectByMaNV method to retrieve all entities with the given MaNV from the database
        List<HoaDon> selectedEntities = dao.selectBY_IDNV(hd.getMaNV());

        // Assert that the list of entities is not null and contains at least one entity
        assertNotNull(selectedEntities);
        assertTrue(selectedEntities.size() >= 1);

        // Assert that the inserted entity is present in the list of selected entities
        boolean entityFound = false;
        for (HoaDon hoaDon : selectedEntities) {
            if (hoaDon.getMaHD().equals(hd.getMaHD())) {
                assertEquals(hd.getNgayLapHD(), hoaDon.getNgayLapHD());
                assertEquals(hd.getTrangThaiThanhToan(), hoaDon.getTrangThaiThanhToan());
                assertEquals(hd.getMaNV(), hoaDon.getMaNV());
                assertEquals(hd.isTrangThai_ThongKe(), hoaDon.isTrangThai_ThongKe());
                assertEquals(hd.getGhiChu(), hoaDon.getGhiChu());
                assertEquals(hd.getTongTien(), hoaDon.getTongTien(), 0.01);
                entityFound = true;
                break;
            }
        }
        assertTrue(entityFound);

    }

    @Test
    public void testSelectByTrangThaiThongKe() {
        // Create and insert test data
        HoaDon hd1 = new HoaDon();
        hd1.setMaHD("HD006");
        hd1.setNgayLapHD("2023-04-07");
        hd1.setTrangThaiThanhToan("Đã thanh toán");
        hd1.setMaNV("NV01");
        hd1.setTrangThai_ThongKe(true);
        hd1.setGhiChu("Test select HoaDon");
        hd1.setTongTien(100000);
        dao.insert(hd1);

        HoaDon hd2 = new HoaDon();
        hd2.setMaHD("HD003");
        hd2.setNgayLapHD("2023-04-08");
        hd2.setTrangThaiThanhToan("Chưa thanh toán");
        hd2.setMaNV("NV02");
        hd2.setTrangThai_ThongKe(false);
        hd2.setGhiChu("Test select HoaDon");
        hd2.setTongTien(200000);
        dao.insert(hd2);

        // Call the selectBY_TrangThaiThongKe method to retrieve all entities with thongKe = 0 from the database
        List<HoaDon> selectedEntities = dao.selectBY_TrangThaiThongKe(1);

        // Assert that the list of entities is not null and contains at least one entity
        assertNotNull(selectedEntities);
        assertTrue(selectedEntities.size() >= 1);

        // Assert that the expected entity is present in the list of selected entities
        boolean hd1Found = false;
        for (HoaDon hoaDon : selectedEntities) {
            if (hoaDon.getMaHD().equals(hd1.getMaHD())) {
                assertEquals(hd1.getNgayLapHD(), hoaDon.getNgayLapHD());
                assertEquals(hd1.getTrangThaiThanhToan(), hoaDon.getTrangThaiThanhToan());
                assertEquals(hd1.getMaNV(), hoaDon.getMaNV());
                assertEquals(hd1.isTrangThai_ThongKe(), hoaDon.isTrangThai_ThongKe());
                assertEquals(hd1.getGhiChu(), hoaDon.getGhiChu());
                assertEquals(hd1.getTongTien(), hoaDon.getTongTien(), 0.01);
                hd1Found = true;
                break;
            }
        }
        assertTrue(hd1Found);

        // Assert that the expected entity is not present in the list of selected entities
        boolean hd2Found = false;
        for (HoaDon hoaDon : selectedEntities) {
            if (hoaDon.getMaHD().equals(hd2.getMaHD())) {
                hd2Found = true;
                break;
            }
        }
        assertFalse(hd2Found);
    }

//
    @Test
    public void testSelectByNgayLapHoaDon() {
        // Create a new HoaDon entity and insert it into the database
        HoaDon entity = new HoaDon();
        entity.setMaHD("HD020");
        entity.setNgayLapHD("2023-04-07");
        entity.setTrangThaiThanhToan("Đã thanh toán");
        entity.setMaNV("NV01");
        entity.setTrangThai_ThongKe(false);
        entity.setGhiChu("Test insert HoaDon");
        entity.setTongTien(100000);
        dao.insert(entity);

        // Call the selectBY_NgayLapHoaDon method to retrieve all entities that match the given date
        List<HoaDon> selectedEntities = dao.selectBY_NgayLapHoaDon("07", "04", "2023");

        // Assert that the list of entities is not null and contains at least one entity
        assertNotNull(selectedEntities);
        assertTrue(selectedEntities.size() >= 1);

        // Assert that the inserted entity is present in the list of selected entities
        boolean entityFound = false;
        for (HoaDon hoaDon : selectedEntities) {
            if (hoaDon.getMaHD().equals(entity.getMaHD())) {
                assertEquals(entity.getNgayLapHD(), hoaDon.getNgayLapHD());
                assertEquals(entity.getTrangThaiThanhToan(), hoaDon.getTrangThaiThanhToan());
                assertEquals(entity.getMaNV(), hoaDon.getMaNV());
                assertEquals(entity.isTrangThai_ThongKe(), hoaDon.isTrangThai_ThongKe());
                assertEquals(entity.getGhiChu(), hoaDon.getGhiChu());
                assertEquals(entity.getTongTien(), hoaDon.getTongTien(), 0.01);
                entityFound = true;
                break;
            }
        }
        assertTrue(entityFound);
    }

//
   
}
