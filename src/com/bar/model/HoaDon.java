
package com.bar.model;

import java.sql.Date;

public class HoaDon {
    private String maHD;
    private String ngayLapHD;
    private String trangThaiThanhToan;
    private String maNV;
    private boolean trangThai_ThongKe;
    private String GhiChu;
    private long TongTien;

    public HoaDon() {
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV, boolean trangThai_ThongKe) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
        this.trangThai_ThongKe = trangThai_ThongKe;
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV, boolean trangThai_ThongKe, String GhiChu, long TongTien) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
        this.trangThai_ThongKe = trangThai_ThongKe;
        this.GhiChu = GhiChu;
        this.TongTien = TongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
  

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(String ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThai) {
        this.trangThaiThanhToan = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public boolean isTrangThai_ThongKe() {
        return trangThai_ThongKe;
    }

    public void setTrangThai_ThongKe(boolean trangThai_ThongKe) {
        this.trangThai_ThongKe = trangThai_ThongKe;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }


    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", trangThai=" + trangThaiThanhToan + ", maNV=" + maNV + ", trangThai_ThongKe=" + trangThai_ThongKe + '}';
    }
    
}