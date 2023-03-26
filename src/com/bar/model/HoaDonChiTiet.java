
package com.bar.model;

public class HoaDonChiTiet {
    private String maHDCT;
    private String tenSP;
    private int soLuong;
    private long donGia;
    private String maHD;
    private String maSP;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHDCT, String tenSP, int soLuong, long donGia, String maHD, String maSP) {
        this.maHDCT = maHDCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maHD = maHD;
        this.maSP = maSP;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "maHDCT=" + maHDCT + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGia=" + donGia + ", maHD=" + maHD + ", maSP=" + maSP + '}';
    }
    
}
