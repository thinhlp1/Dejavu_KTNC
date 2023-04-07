/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dejavu.test.dao;

import com.bar.dao.*;
import com.bar.model.HoaDonChiTiet;
import com.bar.util.JdbcHelper;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author nguye
 */
public class HoaDonChiTietDAOTest {

    HoaDonChiTietDAO daoHDCT;
    HoaDonChiTiet hdct;
    JdbcHelper helper;
    String SELECT_ALL_SQL = "SELECT * FROM HOADONCHITIET";
    String SELECT_BY_MAHD_SQL = "SELECT * FROM HOADONCHITIET WHERE MAHD = ?";
    String INSERT = "INSERT INTO HOADONCHITIET VALUES (?,?,?,?,?,?)";

    @Before
    public void setUp() {
        daoHDCT = new HoaDonChiTietDAO();
        helper = new JdbcHelper();
        hdct = new HoaDonChiTiet();
    }

    @Test
    public void testInsert() {
        // Create a new HoaDonChiTiet entity
        HoaDonChiTiet entity = new HoaDonChiTiet();
        entity.setMaHDCT("HDCT0020");
        entity.setTenSP("Pina Colada");
        entity.setSoLuong(10);
        entity.setDonGia(1000);
        entity.setMaHD("HD1");
        entity.setMaSP("SP01");

        // Call the insert method to insert the entity into the database
        daoHDCT.insert(entity);

        // Retrieve the entity from the database by its ID
        List<HoaDonChiTiet> insertedEntity = (List<HoaDonChiTiet>) daoHDCT.selectBy_MaHD(entity.getMaHD());

        // Check that the inserted entity has the expected values
        for (HoaDonChiTiet hoaDonChiTiet : insertedEntity) {
            if (hoaDonChiTiet.getMaHDCT().equalsIgnoreCase(entity.getMaHDCT())) {
                assertEquals("Pina Colada", hoaDonChiTiet.getTenSP());
                assertEquals(entity.getSoLuong(), hoaDonChiTiet.getSoLuong());
                assertEquals(entity.getDonGia(), hoaDonChiTiet.getDonGia(), 0.001);
                assertEquals(entity.getMaHD(), hoaDonChiTiet.getMaHD());
                assertEquals(entity.getMaSP(), hoaDonChiTiet.getMaSP());
                break;
            }
        }
    }

//
    @Test
    public void testSelect() {
        // Create a new HoaDonChiTiet entity and insert it into the database
        HoaDonChiTiet entity = new HoaDonChiTiet();
        entity.setMaHDCT("HDCT0021");
        entity.setTenSP("Product A");
        entity.setSoLuong(10);
        entity.setDonGia(1000);
        entity.setMaHD("HD1");
        entity.setMaSP("SP01");
        daoHDCT.insert(entity);

        // Call the select method to retrieve all entities from the database
        List<HoaDonChiTiet> selectedEntities = daoHDCT.select();

        // Assert that the list of entities is not null and contains at least one entity
        assertNotNull(selectedEntities);
        assertTrue(selectedEntities.size() >= 1);

        // Assert that the inserted entity is present in the list of selected entities
        boolean entityFound = false;
        for (HoaDonChiTiet hoaDonChiTiet : selectedEntities) {
            if (hoaDonChiTiet.getMaHDCT().equals(entity.getMaHDCT())) {
                assertEquals(entity.getTenSP(), hoaDonChiTiet.getTenSP());
                assertEquals(entity.getSoLuong(), hoaDonChiTiet.getSoLuong());
                assertEquals(entity.getDonGia(), hoaDonChiTiet.getDonGia(), 0.001);
                assertEquals(entity.getMaHD(), hoaDonChiTiet.getMaHD());
                assertEquals(entity.getMaSP(), hoaDonChiTiet.getMaSP());
                entityFound = true;
                break;
            }
        }
        assertTrue(entityFound);
    }
//

    @Test
    public void testSelectBy_MaHD() {
        // Insert a new HoaDonChiTiet entity into the database
        HoaDonChiTiet entity = new HoaDonChiTiet();
        entity.setMaHDCT("HDCT0018");
        entity.setTenSP("Product A");
        entity.setSoLuong(10);
        entity.setDonGia(1000);
        entity.setMaHD("HD1");
        entity.setMaSP("SP01");
        daoHDCT.insert(entity);

        // Retrieve the entity from the database by its MAHD
        List<HoaDonChiTiet> selectedEntity = daoHDCT.selectBy_MaHD(entity.getMaHD());

        // Check that the selected entity has the expected values
        for (HoaDonChiTiet hoaDonChiTiet : selectedEntity) {
            assertEquals(entity.getMaHD(), hoaDonChiTiet.getMaHD());
        }

    }

    
}
