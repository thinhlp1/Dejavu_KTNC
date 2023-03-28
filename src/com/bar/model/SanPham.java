/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.model;

/**
 *
 * @author Lenovo
 */
public class SanPham {

    private String maMon;
    private String tenMon;
    private long gia;
    private String danhMuc;
    private String ghiChu;
    private String hinh;

    public SanPham() {
    }

    public SanPham(String maMon, String tenMon, long gia, String danhMuc, String ghiChu,String image) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.gia = gia;
        this.danhMuc = danhMuc;
        this.ghiChu = ghiChu;
        this.hinh = image;
    }

    public SanPham(String tenMon, String hinh, long gia) {
        this.tenMon = tenMon;
        this.hinh = hinh;
        this.gia = gia;
    }

    public SanPham(String tenMon, long gia) {
        this.tenMon = tenMon;
        this.gia = gia;
    }

//     @Override
//    public String toString() {
//        return danhMuc;
//    }
    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public SanPham(String hinh) {
        this.hinh = hinh;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
