package com.bar.model;

public class BanOder {

    private String maBan;
    private String GhiChu;
    private String MaBanGop;
    private String voucher;

    public BanOder(String maBan, String GhiChu, String MaBanGop, String voucher) {
        this.maBan = maBan;
        this.GhiChu = GhiChu;
        this.MaBanGop = MaBanGop;
        this.voucher = voucher;
    }

    public BanOder(String maBan, String GhiChu, String MaBanGop) {
        this.maBan = maBan;
        this.GhiChu = GhiChu;
        this.MaBanGop = MaBanGop;
    }

    public BanOder() {
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaBanGop() {
        return MaBanGop;
    }

    public void setMaBanGop(String MaBanGop) {
        this.MaBanGop = MaBanGop;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

}
