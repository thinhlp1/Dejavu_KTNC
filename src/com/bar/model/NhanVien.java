
package com.bar.model;

/**
 *
 * @author PHUOCTAI
 */
public class NhanVien {
    String maNV;
    String hoTenLot;
    String tenNV;
    String chucVu;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTenLot, String tenNV, String chucVu) {
        this.maNV = maNV;
        this.hoTenLot = hoTenLot;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return hoTenLot + " "+ tenNV;
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenLot() {
        return hoTenLot;
    }

    public void setHoTenLot(String hoTenLot) {
        this.hoTenLot = hoTenLot;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
