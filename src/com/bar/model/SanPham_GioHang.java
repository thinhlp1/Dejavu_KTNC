
package com.bar.model;


public class SanPham_GioHang {
 
    private String maBan;
    private String Hinh;
    private String Masp;
    private String TenSP;
    private long gia;
    private int soLuong;

    public SanPham_GioHang(String maBan, String Hinh, String Masp, String TenSP,long gia, int soLuong) {
        this.maBan = maBan;
        this.Hinh = Hinh;
        this.Masp = Masp;
        this.TenSP = TenSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

  public SanPham_GioHang(String maBan, String Hinh, String Masp, String TenSP,long gia) {
        this.maBan = maBan;
        this.Hinh = Hinh;
        this.Masp = Masp;
        this.TenSP = TenSP;
        this.gia = gia;
       
    }
   
  
     
    public SanPham_GioHang() {
}
    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getMasp() {
        return Masp;
    }

    public void setMasp(String Masp) {
        this.Masp = Masp;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
    
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
   
}
