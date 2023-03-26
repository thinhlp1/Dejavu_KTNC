package com.bar.dao;

import com.bar.util.JdbcHelper;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> getThongKeTheoNgay(String ngayLapHoaDonNgay) {

        String sql = "{CALL sp_ThongKeTheoNgay(?)}";
        String[] cols = {"Ngay", "DoanhThu"};

        return this.getListOfArray(sql, cols, ngayLapHoaDonNgay);
    }

    public List<Object[]> getThongKeTheoThang(String ngayLapHoaDonThang) {

        String sql = "{CALL sp_ThongKeTheoThang(?)}";
        String[] cols = {"Thang", "DoanhThu"};

        return this.getListOfArray(sql, cols, ngayLapHoaDonThang);
    }

    public List<Object[]> getThongKeTheoNam(String nam) {

        String sql = "{CALL sp_ThongKeTheoNam(?)}";
        String[] cols = {"Nam", "DoanhThu"};

        return this.getListOfArray(sql, cols, nam);
    }


}
