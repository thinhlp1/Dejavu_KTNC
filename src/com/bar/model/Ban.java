package com.bar.model;

// ma ban, vc , ghi chu , ma ban gop
public class Ban {

    private String MaBan;
    private boolean TrangThai;
    private String MaNhanVien;
    private String MaBanGop;

    public String getMaBan() {
        return MaBan;
    }

    public Ban() {
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getMaBanGop() {
        return MaBanGop;
    }

    public void setMaBanGop(String MaBanGop) {
        this.MaBanGop = MaBanGop;
    }

    public Ban(String MaBan, boolean TrangThai, String MaNhanVien, String MaBanGop) {
        this.MaBan = MaBan;
        this.TrangThai = TrangThai;
        this.MaNhanVien = MaNhanVien;
        this.MaBanGop = MaBanGop;
    }

    public Ban(String MaBan, boolean TrangThai, String MaNhanVien) {
        this.MaBan = MaBan;
        this.TrangThai = TrangThai;
        this.MaNhanVien = MaNhanVien;
    }

    @Override
    public String toString() {
        return MaBan;
    }

}
