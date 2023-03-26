package com.bar.model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {

    private String tenTaiKhoan;
    private String matKhau;
    private String maNV;


    public TaiKhoan() {
    }


    public TaiKhoan(String tenTaiKhoan, String matKhau, String maNV) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.maNV = maNV;
    }
    

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }



}
