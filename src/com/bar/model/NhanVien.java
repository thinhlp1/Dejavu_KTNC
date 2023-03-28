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
        return hoTenLot + " " + tenNV;
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

//  CHECK NHANVIEN  
    public String checkMaNV(String maNV) {
        this.setMaNV(maNV);
        return this.getMaNV();
    }

    public String checkTenNV(String ho, String ten) {
        this.setHoTenLot(ho);
        this.setTenNV(ten);
        return this.getHoTenLot() + " " + this.getTenNV();
    }

    public boolean checkChucVu(String chucVu) {
        this.setChucVu(chucVu);
        return this.getChucVu().equals("Quản lý") || this.getChucVu().equals("Nhân viên") ? true : false;

    }
}
